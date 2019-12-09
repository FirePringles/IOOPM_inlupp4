package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class Sequence extends SymbolicExpression{
    
    public ArrayList<SymbolicExpression> functionBody;

    public Sequence(ArrayList<SymbolicExpression> body){
	this.functionBody = body;
    }

    public ArrayList<SymbolicExpression> getBody(){
	return this.functionBody;
    }

    public int getBodySize(){
	return this.functionBody.size();
    }

    public void addToBody(SymbolicExpression exp){
	this.functionBody.add(exp);
    }

    @Override
    public SymbolicExpression accept(Visitor v){
	return v.visit(this);
    }
}
