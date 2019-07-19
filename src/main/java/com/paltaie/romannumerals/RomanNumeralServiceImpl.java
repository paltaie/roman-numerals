package com.paltaie.romannumerals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RomanNumeralServiceImpl implements RomanNumeralService {

    private Map<String, Integer> romanToDigit;

    public RomanNumeralServiceImpl() {
        this.romanToDigit = new HashMap<>();

        IntStream.range(0, 4000).forEach(i -> romanToDigit.put(decimalToRoman(i), i));
    }

    public int romanToDecimal(String roman) {
        final Integer decimal = romanToDigit.get(roman);
        if (decimal == null) {
            throw new IllegalArgumentException("Cannot convert: " + roman);
        }
        return decimal;
    }

    @Override
    public String decimalToRoman(int decimal) {

        return decimalToRoman(decimal, 1);
    }

    private String decimalToRoman(int decimal, int exp) {
        int r = decimal % 10;
        int q = decimal / 10;
        if (q > 0) {
            return decimalToRoman(q, exp + 1) + digitToRoman(r, exp);
        } else {
            return digitToRoman(r, exp);
        }
    }

    private String digitToRoman(int digit, int exp) {
        if (digit > 9) {
            throw new IllegalStateException("digit should be < 10: " + digit);
        }

        if (exp == 1) {
            switch (digit) {
                case 1:
                    return "I";
                case 2:
                    return "II";
                case 3:
                    return "III";
                case 4:
                    return "IV";
                case 5:
                    return "V";
                case 6:
                    return "VI";
                case 7:
                    return "VII";
                case 8:
                    return "VIII";
                case 9:
                    return "IX";
                case 0:
                    return "";
            }
        } else if (exp == 2) {
            switch (digit) {
                case 1:
                    return "X";
                case 2:
                    return "XX";
                case 3:
                    return "XXX";
                case 4:
                    return "XL";
                case 5:
                    return "L";
                case 6:
                    return "LX";
                case 7:
                    return "LXX";
                case 8:
                    return "LXXX";
                case 9:
                    return "XC";
                case 0:
                    return "";
            }
        } else if (exp == 3) {
            switch (digit) {
                case 1:
                    return "C";
                case 2:
                    return "CC";
                case 3:
                    return "CCC";
                case 4:
                    return "CD";
                case 5:
                    return "D";
                case 6:
                    return "DC";
                case 7:
                    return "DCC";
                case 8:
                    return "DCCC";
                case 9:
                    return "CM";
                case 0:
                    return "";
            }
        } else if (exp == 4) {
            switch (digit) {
                case 1:
                    return "M";
                case 2:
                    return "MM";
                case 3:
                    return "MMM";
                case 0:
                    return "";
                default:
                    throw new IllegalArgumentException("Number too big!");
            }
        }

        throw new IllegalArgumentException("Number too big!");
    }


}
