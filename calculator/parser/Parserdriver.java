package org.ioopm.calculator.parser;

import org.ioopm.calculator.ast.*;

import java.io.IOException;

class ParserDriver {
    public static void main(String[] args) {
        CalculatorParser p = new CalculatorParser();

        System.out.println("Welcome to the parser!");

        try {
            Environment env = new Environment();
            while(true) {
                System.out.print("Please enter an expression: ");
                String input = System.console().readLine();
                SymbolicExpression result = p.parse(input + "\n");
                System.out.println("result: " + result);
                System.out.println("eval: " + result.eval(env));
            }
        } catch(SyntaxErrorException e) {
            System.out.print("Syntax Error: ");
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.err.println("IO Exception!");
        }
    }
}
