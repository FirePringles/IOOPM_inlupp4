package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.io.IOException;
import java.util.function.BiConsumer;

public class Calculator {

    private static Environment env;
    private static int commands = 0;
    private static int successfulCommands = 0;
    private static int fullyEvaluatedCommands = 0;

    private static void command(Command command) {
        if(command == Quit.instance()) {
            System.out.printf("Commands executed: %s\nCommands successfully evaluated: %s\nFully evaluated commands: %s\n", Calculator.commands, Calculator.successfulCommands, Calculator.fullyEvaluatedCommands);
            System.exit(0);
        } else if(command == Vars.instance()) {
            BiConsumer<Variable, SymbolicExpression> printer = (var, num)->System.out.println(var+": "+num);
            Calculator.env.forEach(printer);
        } else if(command == Clear.instance()) {
            Calculator.env.clear();
        } else {
            throw new RuntimeException("Invalid command");
        }
    }
    
    public static void main(String[] args) {
        final CalculatorParser parser = new CalculatorParser();
        final Environment env = new Environment();
        final Variable ans = new Variable("ans");


        String input;
        SymbolicExpression result;

        Calculator.env = new Environment();
        while(true) {
            System.out.print("Please enter an expression: ");
            try {
                input = System.console().readLine();
                result = parser.parse(input + "\n");

                Calculator.commands++;
                
                if(result.isCommand()) {
                    command((Command) result);
                } else {
                    System.out.println("tree: " + result);
                    result = result.eval(Calculator.env);
                    System.out.println("eval: " + result);
                    (new Assignment(result, ans)).eval(Calculator.env);
                    Calculator.successfulCommands++;
                    if(result.isConstant()) {
                        Calculator.fullyEvaluatedCommands++;
                    }
                }
            } catch(SyntaxErrorException e) {
                System.out.print("Syntax Error: ");
                System.out.println(e.getMessage());     
            } catch(IOException e) {
                System.err.println("IO Exception!");
            } catch(IllegalExpressionException e) {
                System.out.println(e);
            }
        }
    }
}
