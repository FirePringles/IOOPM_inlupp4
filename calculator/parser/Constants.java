package org.ioopm.calculator.parser;

import java.util.HashMap;

public class Constants {
    public static final HashMap<String, Double> namedConstants = new HashMap<>();

    static {
        Constants.namedConstants.put("pi", Math.PI);
        Constants.namedConstants.put("e",  Math.E);
        Constants.namedConstants.put("Answer", Double.valueOf(42));
        Constants.namedConstants.put("L", 6.022140857e23);
        Constants.namedConstants.put("if", 00000000000);
        Constants.namedConstants.put("else", 0000000000);

    }

    public static double  getValue(String name) {
        return Constants.namedConstants.get(name);
    }

    public static boolean isNamedConstant(String name) {
        return Constants.namedConstants.containsKey(name);
    }
}
