package com.paltaie.romannumerals;

public class RomanNumeralServiceImpl implements RomanNumeralService {

    public int romanToDecimal(String roman) {
        int result = 0;

        long ms = roman.chars().filter(c -> c == 'M').count();
        long ds = roman.chars().filter(c -> c == 'D').count();
        long ls = roman.chars().filter(c -> c == 'L').count();
        long cs = roman.chars().filter(c -> c == 'C').count();
        long xs = roman.chars().filter(c -> c == 'X').count();
        long vs = roman.chars().filter(c -> c == 'V').count();
        long is = roman.chars().filter(c -> c == 'I').count();

        if (roman.contains("IV")) result -= 2;
        if (roman.contains("IX")) result -= 2;
        if (roman.contains("XL")) result -= 10;
        if (roman.contains("CD")) result -= 200;
        if (roman.contains("CM")) result -= 200;

        result += ms * 1000;
        result += ds * 500;
        result += ls * 50;
        result += cs * 100;
        result += xs * 10;
        result += vs * 5;
        result += is;

        return result;
    }

    @Override
    public String decimalToRoman(int decimal) {

        return "I";
    }
}
