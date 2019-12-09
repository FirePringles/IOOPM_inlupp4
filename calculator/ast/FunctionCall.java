package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class FunctionCall extends SymbolicExpression{

  private String name;
  private ArrayList<SymbolicExpression> parList;

  public FunctionCall(String name, ArrayList<SymbolicExpression> parList){
    this.name = name;
    this.parList = parList;
  }


  @Override
  public SymbolicExpression accept(Visitor v){
    return v.visit(this);
  }

}
