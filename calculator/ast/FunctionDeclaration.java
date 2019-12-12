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
    public SymbolicExpression accept(Visitor v){
        return v.visit(this);
    }

    @Override
    public int getArgLen(){
        return this.functionParameters.size();
    }


}
