package org.ioopm.calculator.ast;

public class Scope extends SymbolicExpression{

    private SymbolicExpression exp;

    public Scope(SymbolicExpression exp){
	this.exp = exp;
    }

    @Override
    public String getName() {
	return " {} ";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    public SymbolicExpression getExp() {
	return this.exp;
    }
}
