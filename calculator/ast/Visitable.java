package org.ioopm.calculator.ast;

public interface Visitable{

  public SymbolicExpression accept(Visitor v);

}