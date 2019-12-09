package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class FunctionCall extends SymbolicExpression{
    String name;
    ArrayList<Constant> arguments;

    public FunctionCall(String name, ArrayList<Constant> arguments){
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

    public ArrayList<Constant> getFunctionArgs(){
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
