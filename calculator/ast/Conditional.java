package org.ioopm.calculator.ast;

/**

  A class that will represent the conditional nodes in the Abstract Syntax Tree

  @author Jonathan Gustafsson, Joachim Forsberg

*/


public class Conditional extends SymbolicExpression{

  private SymbolicExpression lhs;
  private SymbolicExpression rhs;
  private String operation;
  private SymbolicExpression s1;
  private SymbolicExpression s2;


  public Conditional(SymbolicExpression lhs, SymbolicExpression rhs, SymbolicExpression s1, SymbolicExpression s2, String operation){
    this.lhs = lhs;
    this.rhs = rhs;
    this.operation = operation;
    this.s1 = s1;
    this.s2 = s2;
  }

  /**
    @return SymbolicExpression on the left side of comparison
  */
  public SymbolicExpression getLHS(){
    return this.lhs;
  }

  /**
    @return SymbolicExpression on the right side of comparison
  */
  public SymbolicExpression getRHS(){
    return this.rhs;
  }

  /**
    @return SymbolicExpression that will be returned if the conditional check
            hold. Will be of type Scope
  */
  public SymbolicExpression getS1(){
    return this.s1;
  }


  /**
    @return SymbolicExpression that will be returned if the conditional check
            do not hold. Will be of type Scope
  */
  public SymbolicExpression getS2(){
    return this.s2;
  }

  /**
    @return String that tells what conditional operation is used in the instance
  */
  public String getOperation(){
    return this.operation;
  }

  @Override
  public SymbolicExpression accept(Visitor v){
    return v.visit(this);
  }
}
