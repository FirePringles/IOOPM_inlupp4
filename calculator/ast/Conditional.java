package org.ioopm.calculator.ast;

public class Conditional extends SymbolicExpression{

    private SymbolicExpression expression1;
    private String operator;
    private SymbolicExpression expression2;
    private SymbolicExpression result1;
    private SymbolicExpression result2;

    public Conditional(SymbolicExpression exp1,String op, SymbolicExpression exp2, SymbolicExpression res1, SymbolicExpression res2){
	this.expression1 = exp1;
	this.operator = op;
	this.expression2 = exp2;
	this.result1 = res1;
	this.result2 = res2;
    }

    public SymbolicExpression getExp1() {
	return this.expression1;
    }
    
    public String getOp() {
	return this.operator;
    }
    
    public SymbolicExpression getExp2() {
	return this.expression2;
    }
    
    public SymbolicExpression getRes1() {
	return this.result1;
    }
    
    public SymbolicExpression getRes2() {
	return this.result2;
    }

    @Override
    public String toString() {
	return "if " + expression1 + operator + expression2 + " then: " + result1 + " else: " + result2;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

    @Override
    public String getName(){
	return "Conditional";
    }
}
