package org.ioopm.calculator.ast;

import java.util.ArrayList;


public class FunctionDeclaration extends SymbolicExpression{

    private String functionName;
    private ArrayList<String> functionParameters;
    private Sequence functionBody;
    
    public FunctionDeclaration(String name,ArrayList<String> params, Sequence body){
	this.functionName = name;
	this.functionParameters = params;
	this.functionBody = body;
    }

    @Override
    public String getName(){
	return "function";
    }

    @Override
    public boolean isFuncDec(){
	return true;
    }

    public String getFunctionName(){
	return this.functionName;
    }

    public ArrayList<String> getFunctionPara(){
	return this.functionParameters;
    }
    
    public Sequence getFunctionBody(){
	return this.functionBody;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
	return v.visit(this);
    }

    
}
