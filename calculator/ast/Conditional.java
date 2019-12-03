package org.ioopm.calculator.ast;


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

  protected SymbolicExpression getLHS(){
    return this.lhs;
  }

  protected SymbolicExpression getRHS(){
    return this.rhs;
  }

  protected SymbolicExpression getS1(){
    return this.s1;
  }

  protected SymbolicExpression getS2(){
    return this.s2;
  }

  protected String getOperation(){
    return this.operation;
  }

  @Override
  public SymbolicExpression accept(Visitor v){
    return v.visit(this);
  }
}
