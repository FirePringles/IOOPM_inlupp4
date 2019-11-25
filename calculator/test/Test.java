package org.ioopm.calculator.test;


import org.ioopm.calculator.ast.*;
import java.util.HashMap;

public class Test {
    
     /**
     * A test for checking if a string and a symbolicexpression is equal. Prints result directly into terminal.
     * 
     * @param expected This is the string which the symbolicExpression is supposed to be equal to
     * @param e This is the symbolicExpression
     * @see SymbolicExpression
     */
    public static void testPrinting(String expected, SymbolicExpression e) {
        if (expected.equals("" + e)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

     /**
     * Testing if a SymbolicExpression equals to an evaluated SymbolicExpression e.  Prints results directly in terminal
     * 
     * @param SymbolicExpression expected which is what the test is going to use in equals method
     * @param SymbolicExpression e is going to be first evaluated and then equals will check if e is equal to expected
     * @param Environment env is the hashmap where variables are stored.
     * @see SymbolicExpression
     */
    public static void testEvaluating(SymbolicExpression expected, SymbolicExpression e, Environment env) {
        SymbolicExpression r = e.eval(env);
        if (r.equals(expected)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

     /**
     * Main method which makes use of testEvaluating and testPrinting methods with diffrent arguments.
     * 
     * @param args Unused
     * @see testEvauating
     * @see testPrinting
     */
    public static void main(String[] args) {
        Environment env = new Environment();
        
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);
        Variable v = new Variable("x");
        Addition a = new Addition(c1, v);
        Multiplication m = new Multiplication(a, c2);

        Addition a1 = new Addition(c1, new Negation(v));
        Multiplication m1 = new Multiplication(a1, c2);

        Multiplication m2 = new Multiplication(a1, new Sin(c2));

        
        testPrinting("(5.0 + x) * 2.0", m);
        testPrinting("(5.0 + (-(x))) * 2.0", m1);
        testPrinting("(5.0 + (-(x))) * sin(2.0)", m2);

        testEvaluating(m, m, env);
        env = new Environment();
        testEvaluating(m1, m1, env);
        env = new Environment();
        testEvaluating(m2, m2, env);
        env = new Environment();
        testEvaluating(new Constant(10), new Constant(10), env);
        env = new Environment();
        testEvaluating(new Constant(10), new Multiplication(c1, c2), env);
        env = new Environment();
        Assignment var = new Assignment(c1, v);
        testEvaluating(new Constant(5), var, env);
        testEvaluating(new Constant(25), new Multiplication(v, v), env);
        testEvaluating((new Sin(new Constant(25))).eval(env), new Sin(new Multiplication(v, v)), env);
        testEvaluating(new Constant(20), m, env);
        env = new Environment();

        Assignment badAssignment = new Assignment(new Constant(3), new NamedConstant("pi", 3.2));
        try {
            badAssignment.eval(env);
            System.out.println("Failed: named constant-test");
        } catch(IllegalExpressionException e) {
            System.out.println("Passed: named constant-test");
        }
    }
}
