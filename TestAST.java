import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.ioopm.calculator.ast.*;
import java.io.*;

public class TestAST extends TestCase {
        //Addition
    @Test
    public void testAddIsConstant() {
        Addition add = new Addition(new Constant(1), new Constant(1));
        assertFalse(add.isConstant());
    }

    @Test
    public void testAddGetName() {
        Addition add = new Addition(new Constant(1), new Constant(1));
        assertEquals(" + ", add.getName());
    }

    @Test
    public void testAddIsCommand() {
        Addition add = new Addition(new Constant(1), new Constant(1));
        assertFalse(add.isCommand());
    }

    @Test
    public void testAddGetPriority() {
        Addition add = new Addition(new Constant(1), new Constant(1));
        assertEquals(75, add.getPriority());
    }

        //Assignment
    @Test
    public void testAssIsConstant() {
	Assignment ass = new Assignment(new Constant(1), new Variable("x"));
	assertFalse(ass.isConstant());
    }
    @Test
    public void testAssgetName() {
	Assignment ass = new Assignment(new Constant(1), new Variable("x"));
	assertEquals(" := ",ass.getName());
    }
    @Test
    public void testAssIsCommand() {
	Assignment ass = new Assignment(new Constant(1), new Variable("x"));
	assertFalse(ass.isCommand());
    }
    @Test
    public void testAssGetPrio() {
	Assignment ass = new Assignment(new Constant(1), new Variable("x"));
	assertEquals(100, ass.getPriority());
    }

        //Subtraction
    @Test
    public void testSubIsConstant() {
        Subtraction sub = new Subtraction(new Constant(1), new Constant(1));
        assertFalse(sub.isConstant());
    }

    @Test
    public void testSubGetName() {
        Subtraction sub = new Subtraction(new Constant(1), new Constant(1));
        assertEquals(" - ", sub.getName());
    }

    @Test
    public void testSubIsCommand() {
        Subtraction sub = new Subtraction(new Constant(1), new Constant(1));
        assertFalse(sub.isCommand());
    }

    @Test
    public void testSubGetPriority() {
        Subtraction sub = new Subtraction(new Constant(1), new Constant(1));
        assertEquals(75, sub.getPriority());
    }

        //Division
    @Test
    public void testDivIsConstant() {
        Division div = new Division(new Constant(1), new Constant(1));
        assertFalse(div.isConstant());
    }

    @Test
    public void testDivGetName() {
        Division div = new Division(new Constant(1), new Constant(1));
        assertEquals(" / ", div.getName());
    }

    @Test
    public void testDivIsCommand() {
        Division div = new Division(new Constant(1), new Constant(1));
        assertFalse(div.isCommand());
    }

    @Test
    public void testDivGetPriority() {
        Division div = new Division(new Constant(1), new Constant(1));
        assertEquals(50, div.getPriority());
    }

        //Multiplication
    @Test
    public void testMulIsConstant() {
        Multiplication mul = new Multiplication(new Constant(1), new Constant(1));
        assertFalse(mul.isConstant());
    }

    @Test
    public void testMulGetName() {
        Multiplication mul = new Multiplication(new Constant(1), new Constant(1));
        assertEquals(" * ", mul.getName());
    }

    @Test
    public void testMulIsCommand() {
        Multiplication mul = new Multiplication(new Constant(1), new Constant(1));
        assertFalse(mul.isCommand());
    }

    @Test
    public void testMulGetPriority() {
        Multiplication mul = new Multiplication(new Constant(1), new Constant(1));
        assertEquals(50, mul.getPriority());
    }

         //Cos
    @Test
    public void testCosIsConstant() {
	Cos cos = new Cos(new Constant(1));
	assertFalse(cos.isConstant());
    }
    @Test
    public void testCosGetName() {
	Cos cos = new Cos(new Constant(1));
	assertEquals("cos",cos.getName());
    }
    @Test
    public void testCosIsCommand() {
	Cos cos = new Cos(new Constant(1));
	assertFalse(cos.isCommand());
    }
    @Test
    public void testCosGetPrio() {
	Cos cos = new Cos(new Constant(1));
	assertEquals(25,cos.getPriority());
    }
        //Exp
    @Test
    public void testExpIsConstant() {
	Exp exp = new Exp(new Constant(1));
	assertFalse(exp.isConstant());
    }
    @Test
    public void testExpGetName() {
	Exp exp = new Exp(new Constant(1));
	assertEquals("exp",exp.getName());
    }
    @Test
    public void testExpIsCommand() {
	Exp exp = new Exp(new Constant(1));
	assertFalse(exp.isCommand());
    }
    @Test
    public void testExpGetPrio() {
	Exp exp = new Exp(new Constant(1));
	assertEquals(25,exp.getPriority());
    }

        //Log
    @Test
    public void testLogIsConstant() {
	Log log = new Log(new Constant(1));
	assertFalse(log.isConstant());
    }
    @Test
    public void testLogGetName() {
	Log log = new Log(new Constant(1));
	assertEquals("log",log.getName());
    }
    @Test
    public void testLogIsCommand() {
	Log log = new Log(new Constant(1));
	assertFalse(log.isCommand());
    }
    @Test
    public void testLogGetPrio() {
	Log log = new Log(new Constant(1));
	assertEquals(25,log.getPriority());
    }

        //Negation
    @Test
    public void testNegIsConstant() {
	Negation neg = new Negation(new Constant(1));
	assertFalse(neg.isConstant());
    }
    @Test
    public void testNegGetName() {
	Negation neg = new Negation(new Constant(1));
	assertEquals("-",neg.getName());
    }
    @Test
    public void testNegIsCommand() {
	Negation neg = new Negation(new Constant(1));
	assertFalse(neg.isCommand());
    }
    @Test
    public void testNegGetPrio() {
	Negation neg = new Negation(new Constant(1));
	assertEquals(100,neg.getPriority());
    }

        //Sin
    @Test
    public void testSinIsConstant() {
	Sin sin = new Sin(new Constant(1));
	assertFalse(sin.isConstant());
    }
    @Test
    public void testSinGetName() {
	Sin sin = new Sin(new Constant(1));
	assertEquals("sin",sin.getName());
    }
    @Test
    public void testSinIsCommand() {
	Sin sin = new Sin(new Constant(1));
	assertFalse(sin.isCommand());
    }
    @Test
    public void testSinGetPrio() {
	Sin sin = new Sin(new Constant(1));
	assertEquals(25,sin.getPriority());
    }

        //Constant
    @Test
    public void testConstantIsConstant() {
	Constant con = new Constant(1);
	assertTrue(con.isConstant());
    }
    @Test
    public void testConstantIsCommand() {
	Constant con = new Constant(1);
	assertFalse(con.isCommand());
    }
    @Test
    public void testConstantGetPrio() {
	Constant con = new Constant(1);
	assertEquals(-1 ,con.getPriority());
    }

        //Variable
    @Test
    public void testVariableIsConstant() {
	Variable var = new Variable("x");
	assertFalse(var.isConstant());
    }
    @Test
    public void testVariableIsCommand() {
	Variable var = new Variable("x");
	assertFalse(var.isCommand());
    }
    @Test
    public void testVariableGetPrio() {
	Variable var = new Variable("x");
	assertEquals(-1,var.getPriority());
    }



         //toString
    @Test
    public void testToStringAtoms() {
	Constant c1 = new Constant(1);
	Variable v1 = new Variable("x");
	NamedConstant con = new NamedConstant("new", 41);
	assertEquals("1.0",c1.toString());
	assertEquals("x", v1.toString());
	assertEquals("41.0", con.toString());
    }

    @Test
    public void testToStringBinary() {
        Constant c1 = new Constant(1);
	Constant c2 = new Constant(2);
	Variable v1 = new Variable("x");
	Addition add = new Addition(c1,c2);
	Subtraction sub = new Subtraction(c1,c2);
	Division div = new Division(c1,c2);
	Multiplication mult = new Multiplication(c1,c2);
	Assignment ass1 = new Assignment(c1,v1);
	assertEquals("1.0 + 2.0", add.toString());
	assertEquals("1.0 - 2.0", sub.toString());
	assertEquals("1.0 / 2.0", div.toString());
	assertEquals("1.0 * 2.0", mult.toString());
	assertEquals("1.0 := x", ass1.toString());
    }
    @Test
    public void testToStringUnary() {
	Constant c1 = new Constant(1);
	Cos cos = new Cos(c1);
	Exp exp = new Exp(c1);
	Log log = new Log(c1);
	Negation neg = new Negation(c1);
	Sin sin = new Sin(c1);
	assertEquals("cos(1.0)", cos.toString());
	assertEquals("exp(1.0)", exp.toString());
	assertEquals("log(1.0)", log.toString());
	assertEquals("-(1.0)", neg.toString());
	assertEquals("sin(1.0)", sin.toString());
    }
    @Test
    public void testToStringCombined() {
      Constant c1 = new Constant(1);
      Constant c2 = new Constant(2);
      Constant c3 = new Constant(3);
      Variable v1 = new Variable("x");
      Addition add = new Addition(c1,c2);
      Addition add1 = new Addition(c2,c3);
      Addition add2 = new Addition(add1,c3);
      Multiplication mult = new Multiplication(add,c2);
      Multiplication mult1 = new Multiplication(mult,c3);
      Division div = new Division(add1,add1);
      Log log = new Log(c1);
      Negation neg = new Negation(c1);
      Subtraction sub = new Subtraction(neg,log);
      Multiplication mult2 = new Multiplication(add2,sub);
      assertEquals("((1.0 + 2.0) * 2.0) * 3.0", mult1.toString());
      assertEquals("(2.0 + 3.0) / (2.0 + 3.0)", div.toString());
      assertEquals("((2.0 + 3.0) + 3.0) * ((-(1.0)) - log(1.0))", mult2.toString());
    }
         //equals
    @Test
    public void testEqualsAtoms() {
	Constant c1 = new Constant(1);
	Variable v1 = new Variable("x");
	NamedConstant named = new NamedConstant("new", 52);
	assertTrue(c1.equals(new Constant(1)));
	assertFalse(c1.equals(new Constant(2)));
	assertTrue(v1.equals(new Variable("x")));
	assertFalse(v1.equals(new Variable("y")));
	assertTrue(named.equals(new NamedConstant("new", 52)));
	assertFalse(named.equals(new NamedConstant("no", 1)));
    }

    @Test
    public void testEqualsBinary() {
	Constant c1 = new Constant(1);
	Constant c2 = new Constant(2);
	Variable v1 = new Variable("x");
	Addition add = new Addition(c1,c2);
        Subtraction sub = new Subtraction(c1,c2);
	Division div = new Division(c1,c2);
	Multiplication mult = new Multiplication(c1,c2);
	Assignment ass1 = new Assignment(c1,v1);
	assertTrue(add.equals(new Addition(new Constant(1), new Constant(2))));
	assertFalse(add.equals(new Addition(new Constant(2), new Constant(2))));
	assertTrue(sub.equals(new Subtraction(new Constant(1), new Constant(2))));
	assertFalse(sub.equals(new Subtraction(new Constant(2), new Constant(2))));
	assertTrue(div.equals(new Division(new Constant(1), new Constant(2))));
	assertFalse(div.equals(new Division(new Constant(2), new Constant(2))));
	assertTrue(mult.equals(new Multiplication(new Constant(1), new Constant(2))));
	assertFalse(mult.equals(new Multiplication(new Constant(2), new Constant(2))));
	assertTrue(ass1.equals(new Assignment(new Constant(1), new Variable("x"))));
	assertFalse(ass1.equals(new Assignment(new Constant(2), new Variable("y"))));
    }

    @Test
    public void testEqualsUnary() {
	Constant c1 = new Constant(1);
	Cos cos = new Cos(c1);
	Exp exp = new Exp(c1);
	Log log = new Log(c1);
	Sin sin = new Sin(c1);
	Negation neg = new Negation(c1);
	assertTrue(cos.equals(new Cos(new Constant(1))));
	assertFalse(cos.equals(new Cos(new Constant(2))));
	assertTrue(exp.equals(new Exp(new Constant(1))));
	assertFalse(exp.equals(new Exp(new Constant(2))));
	assertTrue(log.equals(new Log(new Constant(1))));
	assertFalse(log.equals(new Log(new Constant(2))));
	assertTrue(sin.equals(new Sin(new Constant(1))));
	assertFalse(sin.equals(new Sin(new Constant(2))));
	assertTrue(neg.equals(new Negation(new Constant(1))));
	assertFalse(neg.equals(new Negation(new Constant(2))));
    }

    @Test
    public void testEqualsCombined(){
      Constant c1 = new Constant(1);
      Constant c2 = new Constant(2);
      Constant c3 = new Constant(3);
      Variable v1 = new Variable("x");
      Addition add = new Addition(c1,c2);
      Addition add1 = new Addition(c2,c3);
      Addition add2 = new Addition(add1,c3);
      Multiplication mult = new Multiplication(add,c2);
      Multiplication mult1 = new Multiplication(mult,c3);
      Division div = new Division(add1,add1);
      Log log = new Log(c1);
      Negation neg = new Negation(c1);
      Subtraction sub = new Subtraction(neg,log);
      Multiplication mult2 = new Multiplication(add2,sub);
      assertTrue(mult1.equals(new Multiplication(new Multiplication(new Addition(new Constant(1), new Constant(2)),new Constant(2)),new Constant(3))));
		 assertTrue(div.equals(new Division(new Addition(new Constant(2), new Constant(3)), new Addition(new Constant(2), new Constant(3)))));
		 assertTrue(mult2.equals(new Multiplication(new Addition(new Addition(new Constant(2), new Constant(3)), new Constant(3)), new Subtraction(new Negation(new Constant(1)), new Log(new Constant(1))))));
    }

         //eval
    @Test
    public void testEvalAtoms() {
	Constant c1 = new Constant(1);
	Variable v1 = new Variable("x");
	NamedConstant named = new NamedConstant("new", 52);
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	assertEquals("1.0",evaluator.evaluate(c1,env).toString());
	assertNotEquals("2.0",evaluator.evaluate(c1,env).toString());

	assertEquals("x",evaluator.evaluate(v1,env).toString());
	assertNotEquals("y",evaluator.evaluate(v1,env).toString());

	assertEquals("52.0",evaluator.evaluate(named,env).toString());
	assertNotEquals("53.0",evaluator.evaluate(named,env).toString());
    }

    @Test
    public void testEvalBinary() {
	Constant c1 = new Constant(4);
	Constant c2 = new Constant(2);
	Variable v1 = new Variable("x");
	Addition add = new Addition(c1,c2);
	Subtraction sub = new Subtraction(c1,c2);
	Division div = new Division(c1,c2);
	Multiplication mult = new Multiplication(c1,c2);
	Assignment ass1 = new Assignment(c1,v1);
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	assertEquals("6.0",evaluator.evaluate(add,env).toString());
	assertNotEquals("7.0",evaluator.evaluate(add,env).toString());

	assertEquals("2.0",evaluator.evaluate(sub,env).toString());
	assertNotEquals("6.0",evaluator.evaluate(sub,env).toString());

	assertEquals("2.0",evaluator.evaluate(div,env).toString());
	assertNotEquals("6.0",evaluator.evaluate(div,env).toString());

	assertEquals("8.0",evaluator.evaluate(mult,env).toString());
	assertNotEquals("6.0",evaluator.evaluate(mult,env).toString());

	assertEquals("4.0",evaluator.evaluate(ass1,env).toString());
	assertNotEquals("6.0",evaluator.evaluate(ass1,env).toString());
    }

    @Test
    public void testEvalUnary() {
	Constant c1 = new Constant(1);
	Cos cos = new Cos(c1);
	Exp exp = new Exp(c1);
	Log log = new Log(c1);
	Sin sin = new Sin(c1);
	Negation neg = new Negation(c1);
	Environment env = new Environment();
	final EvaluationVisitor evaluator = new EvaluationVisitor();
	assertEquals("0.5403023058681398",evaluator.evaluate(cos,env).toString());
	assertNotEquals("-1.0",evaluator.evaluate(cos,env).toString());
	
	assertEquals("2.718281828459045", evaluator.evaluate(exp,env).toString());
	assertNotEquals("0.5403023058681398", evaluator.evaluate(exp,env).toString());
	
	assertEquals("0.0", evaluator.evaluate(log,env).toString());
	assertNotEquals("0.5403023058681398", evaluator.evaluate(log,env).toString());
	
	assertEquals("0.8414709848078965", evaluator.evaluate(sin,env).toString());
	assertNotEquals("0.5403023058681398", evaluator.evaluate(sin,env).toString());
	
	assertEquals("-1.0", evaluator.evaluate(neg,env).toString());
	assertNotEquals("0.5403023058681398", evaluator.evaluate(neg,env).toString());
    }
    @Test
    public void testEvalCombined(){
      Constant c1 = new Constant(1);
      Constant c2 = new Constant(2);
      Constant c3 = new Constant(3);
      Variable v1 = new Variable("x");
      Addition add = new Addition(c1,c2);
      Addition add1 = new Addition(c2,c3);
      Addition add2 = new Addition(add1,c3);
      Multiplication mult = new Multiplication(add,c2);
      Multiplication mult1 = new Multiplication(mult,c3);
      Division div = new Division(add1,add1);
      Log log = new Log(c1);
      Negation neg = new Negation(c1);
      Subtraction sub = new Subtraction(neg,log);
      Multiplication mult2 = new Multiplication(add2,sub);
      Environment env = new Environment();
      final EvaluationVisitor evaluator = new EvaluationVisitor();
      
      assertEquals("18.0", evaluator.evaluate(mult1,env).toString());
      
      assertEquals("1.0", evaluator.evaluate(div,env).toString());
      
      assertEquals("-8.0", evaluator.evaluate(mult2,env).toString());
    }
}
