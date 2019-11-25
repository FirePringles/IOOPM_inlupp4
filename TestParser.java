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
    assertEquals(add1, add1Parsed);

  }

  public void testNegation() throws IOException{
    CalculatorParser parser = new CalculatorParser();

    SymbolicExpression neg1 = new Negation(new Negation(new Constant(1337)));
    SymbolicExpression neg2 = new Negation(new Negation(new Negation(new Constant(10))));

    SymbolicExpression neg1Parsed = parser.parse(neg1.toString() + "\n");
    assertEquals(neg1, neg1Parsed);
    assertNotEquals(neg2, neg1Parsed);

  }

  

}
