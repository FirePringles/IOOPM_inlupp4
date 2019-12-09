package org.ioopm.calculator.ast;

import java.util.ArrayList;


public class FunctionDeclaration extends SymbolicExpression{

  private ArrayList<SymbolicExpression> parList;
  private ArrayList<SymbolicExpression> seqList;
  private String name;

  public FunctionDeclaration(String name, ArrayList<SymbolicExpression> parList, ArrayList<SymbolicExpression> seqList){
    this.name = name;
    this.parList = parList;
    this.seqList = seqList;
  }

  @Override
  public void addToSeqList(SymbolicExpression s){
    this.seqList.add(s);
  }

  @Override
  public boolean isFunctionDec(){
    return true;
  }

  @Override
  public SymbolicExpression accept(Visitor v){
    return v.visit(this);
  }

}
