package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.Scanner;
import java.util.ArrayList;

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
            //BiConsumer<Variable, SymbolicExpression > printer = (var, num)->System.out.println(var+": "+num);
            //Calculator.env.forEach(printer);
            System.out.println(env.toString());
        } else if(command == Clear.instance()) {
            Calculator.env.clear();
        } else {
            throw new RuntimeException("Invalid command");
        }
    }

    public static void functionDec(FunctionDeclaration func, CalculatorParser parser){
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	
	boolean looper = true;
	Scanner sc = new Scanner(System.in);
	String new_input;

	while(looper == true){
	    new_input = sc.nextLine();
	    if(new_input.equals("end"))
		{
		    looper = false;
		}
	    try{
	    SymbolicExpression result = parser.parse(new_input + "\n");
	    Sequence body = func.getFunctionBody();
	    body.addToBody(result);
	    
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

    public static void main(String[] args) {
        final CalculatorParser parser = new CalculatorParser();
        env = new Environment();
        final Variable ans = new Variable("ans");
        final EvaluationVisitor evaluator = new EvaluationVisitor();
        final NamedConstantChecker checker = new NamedConstantChecker();
        final ReassignmentChecker reassChecker = new ReassignmentChecker();
        Scanner sc = new Scanner(System.in);
        String input;
        SymbolicExpression result;

        Calculator.env = new Environment();

        while(true) {
            System.out.print("Please enter an expression: ");
            try {
		input = sc.nextLine();
		result = parser.parse(input + "\n");
                Calculator.commands++;

                if(result.isCommand()) {
                    command((Command) result);
                } else if(result.isFuncDec()){
		    functionDec((FunctionDeclaration) result, parser);
		}
		else {
                    if(checker.checkNamedConstant(result, env) && reassChecker.reassignedCheck(result, env)){
                      result = evaluator.evaluate(result, env);
                      System.out.println("eval: " + result);                
                      env.put((Variable) ans, result);
                      Calculator.successfulCommands++;
                    }
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
