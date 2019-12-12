package org.ioopm.calculator.ast;

import java.util.ArrayList;

/**
  * Will be used when a function types in a function. For this to be able to work
  * the function typed need to be previously declared. There will be some PredefinedFunctions.
  *
  * @see FunctionDeclaration
  * @see PredefinedFunctions
  *
  * @author Jonathan Gustafsson, Joachim Forsberg

*/


public class FunctionCall extends SymbolicExpression{

    private String name;
    private ArrayList<Atom> arguments;

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

    public ArrayList<Atom> getFunctionArgs(){
        return this.arguments;
    }

    public int getArgumentsSize(){
        return this.arguments.size();
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
