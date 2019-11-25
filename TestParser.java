import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.ioopm.calculator.ast.*;
import java.io.*;
import org.ioopm.calculator.parser.*;


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

  }

  @Test
  public void testMultiplication() throws IOException{
    CalculatorParser parser = new CalculatorParser();

  }

  @Test
  public void testDivision() throws IOException{
    CalculatorParser parser = new CalculatorParser();

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

  }

  @Test
  public void testLog() throws IOException{

  }

}
