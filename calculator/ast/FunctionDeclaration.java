package org.ioopm.calculator.ast;

import java.util.ArrayList;


/**
  * For function declarations.
  *
  * @see FunctionCall
  * @see Sequence
  *
  * @author Jonathan Gustafsson, Joachim Forsberg
*/



public class FunctionDeclaration extends SymbolicExpression{

    /** The name of the function */
    private String functionName;

    /** The parameters of the function */
    private ArrayList<Variable> functionParameters;

    /** All the statements withing the function */
    private Sequence functionBody;

    public FunctionDeclaration(String name,ArrayList<Variable> params, Sequence body){
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
    
    @Override
    public String getFunctionName(){
        return this.functionName;
    }

    public ArrayList<Variable> getFunctionPara(){
        return this.functionParameters;
    }

    public Sequence getFunctionBody(){
        return this.functionBody;
    }

    @Override
    public String toString(){
	ArrayList<Variable> args = this.getFunctionPara();
	Sequence seq = this.getFunctionBody();
	String expr = "";
	ArrayList<SymbolicExpression> body = seq.getBody();
        expr = expr + this.getFunctionName() + "(";

	for(int i = 0; i<args.size(); i++){
	    if(i+1 == args.size()){
		expr = expr + args.get(i).toString();
		break;
	    }
	    expr = expr + args.get(i).toString() + ",";
	}

	expr = expr + ")\n";

	for(int i = 0; i<seq.getBodySize(); i++){
	    expr = expr + body.get(i).toString() + "\n";
	}
	return expr;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
        return v.visit(this);
    }

    @Override
    public int getArgLen(){
        return this.functionParameters.size();
    }

    public boolean equals(FunctionDeclaration other) {
        return this.getFunctionName().equals(other.getFunctionName()) && this.getFunctionPara().equals(other.getFunctionPara()) && this.getFunctionBody().equals(other.getFunctionBody());
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof FunctionDeclaration)) {
            return false;
        } else {
            return this.equals((FunctionDeclaration) other);
        }
    }


}
