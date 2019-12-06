package org.ioopm.calculator.ast;

public class Scope extends SymbolicExpression{

  private SymbolicExpression exp;

    public Scope(SymbolicExpression exp){
      this.exp = exp;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }

    protected SymbolicExpression getExp(){
      return this.exp;
    }

}
