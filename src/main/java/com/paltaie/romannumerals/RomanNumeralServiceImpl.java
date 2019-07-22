package com.paltaie.romannumerals;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.vavr.collection.Stream;

public class RomanNumeralServiceImpl implements RomanNumeralService {

    private static final Stream<RomanNumeral> ROMAN_NUMERALS =
        Stream.of(RomanNumeral.values());

    private static final Stream<RomanNumeral> ROMAN_NUMERALS_BY_VALUE =
        Stream.of(RomanNumeral.values())
            .sorted(Comparator.comparing(num -> num.decimalValue))
            .reverse();

    private final Map<String, Integer> romanToDecimalCache
        = new ConcurrentHashMap<>();

    private final Map<Integer, String> decimalToRomanCache
        = new ConcurrentHashMap<>();

    public int romanToDecimal(String roman) {
        return romanToDecimalCache.computeIfAbsent(roman, romanValue -> {
            int decimalValue = convertRomanToDecimal(romanValue);
            decimalToRomanCache.put(decimalValue, romanValue);
            return decimalValue;
        });
    }

    private int convertRomanToDecimal(final String roman) {
        String current = roman;
        int sum = 0;
        while (!current.isEmpty()) {
            RomanNumeral currentValue = nextNumeral(current);
            sum += currentValue.decimalValue;
            current = current.substring(currentValue.name().length());
        }
        return sum;
    }

    private RomanNumeral nextNumeral(final String current) {
        return ROMAN_NUMERALS
            .find(num -> current.startsWith(num.name()))
            .get();
    }

    @Override
    public String decimalToRoman(int decimal) {
        return decimalToRomanCache.computeIfAbsent(decimal, decimalValue -> {
            String romanValue = convertDecimalToRoman(decimalValue);
            romanToDecimalCache.put(romanValue, decimalValue);
            return romanValue;
        });
    }

    private String convertDecimalToRoman(final int decimal) {
        int current = decimal;
        StringBuilder converted = new StringBuilder();
        while (current > 0) {
            RomanNumeral currentValue = nextNumeral(current);
            converted.append(currentValue.name());
            current -= currentValue.decimalValue;
        }
        return converted.toString();
    }

    private RomanNumeral nextNumeral(final int current) {
        return ROMAN_NUMERALS_BY_VALUE.find(num -> current >= num.decimalValue).get();
    }
}
