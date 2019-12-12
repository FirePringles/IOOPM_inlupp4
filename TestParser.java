import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.ioopm.calculator.ast.*;
import java.io.*;
import org.ioopm.calculator.parser.*;
import java.util.ArrayList;


public class TestParser extends TestCase {

  @Test
  public void testAddition() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression add1 = new Addition(new Constant(1), new Variable("x"));
    SymbolicExpression add1Parsed = parser.parse(add1.toString()+"\n");
    assertTrue(add1.equals(add1Parsed));

    SymbolicExpression add1ParsedPar = parser.parse("("+ add1.toString() +")\n");
    assertTrue(add1.equals(add1ParsedPar));
  }

  @Test
  public void testSubtraction() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression sub1 = new Subtraction(new Variable("x"), new Constant(10));
    SymbolicExpression sub1Parsed = parser.parse("x-10\n");

    assertTrue(sub1.equals(sub1Parsed));

  }

  @Test
  public void testMultiplication() throws IOException{
    CalculatorParser parser = new CalculatorParser();
    SymbolicExpression mul1 = new Multiplication(new Multiplication(new Constant(10), new Constant(5)), new Variable("x"));
    SymbolicExpression mul1Parsed = parser.parse("(((" + mul1.toString() + ")))\n");

    assertTrue(mul1.equals(mul1Parsed));

  }

  @Test
  public void testDivision() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression div1 = new Division(new Variable("x"), new Constant(10));
    SymbolicExpression div1Parsed = parser.parse(div1.toString() + "\n");

    assertTrue(div1.equals(div1Parsed));

  }

  @Test
  public void testNeg() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression neg1 = new Negation(new Negation(new Constant(1337)));
    SymbolicExpression neg2 = new Negation(new Negation(new Negation(new Constant(10))));

    SymbolicExpression neg1Parsed = parser.parse(neg1.toString() + "\n");
    assertTrue(neg1.equals(neg1Parsed));
    assertFalse(neg2.equals(neg1Parsed));

  }

  @Test
  public void testSin() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression sin1 = new Sin(new Addition(new Constant(10), new Variable("x")));
    SymbolicExpression sin1Parsed = parser.parse("(((" + sin1.toString()+")))\n");

    assertTrue(sin1.equals(sin1Parsed));

  }

  @Test
  public void testCos() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression cos1 = new Cos(new Cos(new Cos(new Constant(10))));
    SymbolicExpression cos1Parsed = parser.parse(cos1.toString() + "\n");

    assertTrue(cos1.equals(cos1Parsed));

  }

  @Test
  public void testLog() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression log1 = new Log(new Negation(new Negation(new Constant(10))));
    SymbolicExpression log1Str = parser.parse(log1.toString() + "\n");

    assertTrue(log1.equals(log1Str));

  }

  @Test
  public void testExp() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression exp1 = new Exp(new Constant(100));
    SymbolicExpression exp1Parsed = parser.parse(exp1.toString() + "\n");

    assertTrue(exp1.equals(exp1Parsed));

  }


  @Test
  public void testQuit() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression quit = Quit.instance();
    SymbolicExpression quitParsed = parser.parse("quit\n");

    assertTrue(quit.equals(quitParsed));
    assertFalse(quit.equals(Clear.instance()));

  }

  @Test
  public void testClear() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression clear = Clear.instance();
    SymbolicExpression clearParsed = parser.parse("clear\n");

    assertTrue(clear.equals(clearParsed));
  }

  @Test
  public void testVars() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression vars = Vars.instance();
    SymbolicExpression varsParsed = parser.parse("vars\n");

    assertEquals(vars, varsParsed);

  }

  public void testNamedConstants() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression pi = new Constant(Math.PI);
    SymbolicExpression piParsed = parser.parse("pi\n");

    SymbolicExpression e = new Constant(Math.E);
    SymbolicExpression eParsed = parser.parse("e\n");


     assertTrue(pi.equals(piParsed));
     assertTrue(e.equals(eParsed));

  }

  @Test
  public void testFunctionDec() throws IOException{
      CalculatorParser parser = new CalculatorParser();
      ArrayList<Variable> para = new ArrayList<Variable>();

      ArrayList<SymbolicExpression> body = new ArrayList<SymbolicExpression>();
      Sequence seq = new Sequence(body);

      SymbolicExpression func = new FunctionDeclaration("x",para,seq);
      SymbolicExpression funcParsed = parser.parse("function x()\n");

      assertTrue(func.equals(funcParsed));	  
  }

  @Test
  public void testFunctionCall() throws IOException{
      CalculatorParser parser = new CalculatorParser();
      ArrayList<Atom> arg = new ArrayList<Atom>();

      SymbolicExpression funcCall = new FunctionCall("x",arg);
      SymbolicExpression funcCallParsed = parser.parse("x()\n");

      assertTrue(funcCall.equals(funcCallParsed));
	  
  }

  @Test
  public void testConditional() throws IOException{
      CalculatorParser parser = new CalculatorParser();
      SymbolicExpression exp1 = new Constant(2);
      SymbolicExpression exp2 = new Constant(1);
      SymbolicExpression res1 = new Constant(2);
      SymbolicExpression res2 = new Constant(1);
      String op = "<";

      
      SymbolicExpression cond = new Conditional(exp1,exp2,res1,res2,op);
      SymbolicExpression condParsed = parser.parse("if 2 < 1 {2} else {1}\n");

      assertTrue(cond.equals(condParsed));
	  
  }
}
