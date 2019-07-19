package com.paltaie.romannumerals;

public enum RomanNumeral {
    IV(4),
    IX(9),
    XL(40),
    XC(90),
    CD(400),
    CM(900),
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    public final int decimalValue;

    RomanNumeral(final int decimalValue) {
        this.decimalValue = decimalValue;
    }
}
