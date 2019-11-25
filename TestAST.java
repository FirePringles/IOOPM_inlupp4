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

}
