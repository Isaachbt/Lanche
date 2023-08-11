package com.curso.lanche.config;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatterValor {

    public static String format(double valor)
    {
        String format = "00.00";
        NumberFormat formatter = new DecimalFormat(format);
        return formatter.format(valor);
    }
}
