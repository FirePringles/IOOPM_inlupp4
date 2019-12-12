package org.ioopm.calculator;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


/**
  * The main program. A symbolic calculator with its own parser. Will get input
  * from the terminal and parser them and create an Abstract Syntax Tree (AST)
  * that will be evaluated.
  * <p>
  * Curren operations supported are Addition, Subtraction, Multiplication, Division
  * Sin, Cos, Log, Exp, Negation.
  * </p>
  * <p>
  * You can also assign values to variables during runtime which you can use.
  * There are some predefined variables (named constants) that you can use right away.
  * These are pi, e, Answer and L
  * </p>
  * <p>
  * The calculator also support conditional calculations.
  * </p>
  * <p>
  * You can also create functions that you can later on can use. There are some predefined
  * functions you can use right away, like max and min.
  * </p>
  * @author Jonathan Gustafsson, Joachim Forsberg
*/


public class Calculator {

    //* Environment containing assign variables */
    private static Environment env;

    //* Number of commands */
    private static int commands = 0;

    //* Numner of commands that were successfull */
    private static int successfulCommands = 0;

    //* Number of commands that were fully evaluated */
    private static int fullyEvaluatedCommands = 0;

    //* Instance of the EvaluationVisitor */
    final static EvaluationVisitor evaluator = new EvaluationVisitor();

    //* Scanner intance */
    static Scanner sc = new Scanner(System.in);


    private static void command(Command command) {
        if(command == Quit.instance()) {
            System.out.printf("Commands executed: %s\nCommands successfully evaluated: %s\nFully evaluated commands: %s\n", Calculator.commands, Calculator.successfulCommands, Calculator.fullyEvaluatedCommands);
            System.exit(0);
        } else if(command == Vars.instance()) {
            System.out.println(env.toString());
        } else if(command == Clear.instance()) {
            Calculator.env.clear();
        } else {
            throw new RuntimeException("Invalid command");
        }
    }

    private static FunctionDeclaration functionDec(FunctionDeclaration func, CalculatorParser parser){

	      boolean looper = true;
	      String new_input;

        while(looper == true){
            new_input = sc.nextLine();
            if(new_input.equals("end"))
                {
                    looper = false;
                }

            try {
                SymbolicExpression result = parser.parse(new_input + "\n");
                func.getFunctionBody().addToBody(result);
            } catch(SyntaxErrorException e) {
                System.out.print("Syntax Error: ");
                System.out.println(e.getMessage());
            } catch(IOException e) {
                System.err.println("IO Exception!");
            } catch(IllegalExpressionException e) {
                System.out.println(e);
            }
	      }
        return func;
    }

    /**
      Main function
    */
    public static void main(String[] args) {

        /** Instance of the parser */
        final CalculatorParser parser = new CalculatorParser();

        /** Instance of variables */
        Calculator.env = new Environment();


        /** A variables that will contain the last expression */
        final Variable ans = new Variable("ans");

        /** Instance of NamedConstantChecker */
        final NamedConstantChecker checker = new NamedConstantChecker();

        /** Instance of ReassignmentChecker */
        final ReassignmentChecker reassChecker = new ReassignmentChecker();

        /** */
        String input;

        /** */
        SymbolicExpression result;

        /** Will contain declared funtions during runtime */
        HashMap<String, FunctionDeclaration> funcDecList = new HashMap<>();

        /** Instance of our predefined functions. Will be loaded into our funcDecList at the start of the program */
        PredefinedFunctions pf = new PredefinedFunctions();

        ArrayList<FunctionDeclaration> fd = pf.getPredefinedFunctions();
        for(FunctionDeclaration f : fd){
          funcDecList.put(f.getFunctionName(), f);
        }

        while(true) {
            System.out.print("Please enter an expression: ");
            try {
                input = sc.nextLine();
                result = parser.parse(input + "\n");
                Calculator.commands++;

                if(result.isCommand()) {
                    command((Command) result);
                } else if(result.isFuncDec()){
                    FunctionDeclaration new_func = functionDec((FunctionDeclaration) result, parser);
                    funcDecList.put(new_func.getFunctionName(), new_func);

                } else if(result.isFunctionCall()){
                    System.out.println("function call");
                    result = evaluator.evaluate(result, env, funcDecList);
                    System.out.println("eval: " + result);
                    env.put((Variable) ans, result);

                }
                else {
                    if(checker.checkNamedConstant(result, env) && reassChecker.reassignedCheck(result, env)){
                        result = evaluator.evaluate(result, env, funcDecList);
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
