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

    public boolean equals(Sequence other) {
        return this.getBody().equals(other.getBody());
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Sequence)) {
            return false;
        } else {
            return this.equals((Sequence) other);
        }
    }
}
