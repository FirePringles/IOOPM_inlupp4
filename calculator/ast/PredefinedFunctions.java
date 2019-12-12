package org.ioopm.calculator.ast;

// NOT DONE


import java.util.ArrayList;

public class PredefinedFunctions{

  private ArrayList<FunctionDeclaration> function;

  public PredefinedFunctions(){
    this.function = new ArrayList<FunctionDeclaration>();
    this.function.add(max());
  }

  private FunctionDeclaration max(){

    ArrayList<Variable> par = new ArrayList<Variable>();
    SymbolicExpression x = new Variable("x");
    SymbolicExpression y = new Variable("y");

    par.add((Variable) x);
    par.add((Variable) y);

    ArrayList<SymbolicExpression> seq = new ArrayList<SymbolicExpression>();
    Conditional cond = new Conditional(x, y, x, y, ">");
    seq.add(cond);

    String name = "max";

    Sequence s = new Sequence(seq);
    return new FunctionDeclaration(name, par, s);

  }

  public ArrayList getPredefinedFunctions(){
    return this.function;
  }

}
