package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class FunctionCall extends SymbolicExpression{
    String name;
    ArrayList<Atom> arguments;

    public FunctionCall(String name, ArrayList<Atom> arguments){
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public boolean isFunctionCall(){
        return true;
    }

    @Override
    public String getName(){
        return "FunctionCall";
    }

    @Override
    public String getFunctionName(){
        return this.name;
    }

    @Override
    public String toString(){
	String expr = "";
	ArrayList<Atom> para = this.getFunctionArgs();
	expr = expr + this.getFunctionName() + "(";

	for(int i = 0; i<para.size(); i++){
	    if(i+1 == para.size()){
		expr = expr + para.get(i).toString();
		break;
	    }
	    expr = expr + para.get(i).toString() + ",";
	}

	expr = expr + ")";
	
	return expr;
    }

    public ArrayList<Atom> getFunctionArgs(){
        return this.arguments;
    }

    public int getArgumentsSize(){
        return this.arguments.size();
    }

    public boolean equals(FunctionCall other) {
        return this.getFunctionName().equals(other.getFunctionName()) && this.getFunctionArgs().equals(other.getFunctionArgs());
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof FunctionCall)) {
            return false;
        } else {
            return this.equals((FunctionCall) other);
        }
    }

    @Override
    public int getArgLen(){
      return this.arguments.size();
    }

    @Override
    public SymbolicExpression accept(Visitor v){
        return v.visit(this);
    }


}
