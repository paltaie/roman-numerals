package com.paltaie.romannumerals;

public class RomanNumeralServiceImpl implements RomanNumeralService {

    public int romanToDecimal(String roman) {

        int length = roman.length();
        roman = roman + " ";

        int result = 0;
        for (int i = 0; i < length; i++) {
            char c = roman.charAt(i);
            char nc = roman.charAt(i + 1);

            if (c == 'M') {
                result += 1000;
            } else if (c == 'C') {
                if (nc == 'M' || nc == 'D') {
                    result += charConvert(nc) - 100;
                    i++;
                } else {
                    result += 100;
                }
            } else if (c == 'D') {
                result += 500;
            } else if (c == 'X') {
                if (nc == 'C' || nc == 'L') {
                    result += charConvert(nc) - 10;
                    i++;
                } else {
                    result += 10;
                }
            } else if (c == 'L') {
                result += 50;
            } else if (c == 'I') {
                if (nc == 'X' || nc == 'V') {
                    result += charConvert(nc) - 1;
                    i++;
                } else {
                    result++;
                }
            } else {
                result += 5;
            }
        }
        return result;
    }

    private int charConvert(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }

        return 0;
    }


    @Override
    public String decimalToRoman(int decimal) {
        if(decimal < 1)
            return "WHAT THE HELL DOOOD";

        return String.valueOf(new char[decimal]).replace('\0', 'I')
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }
}
