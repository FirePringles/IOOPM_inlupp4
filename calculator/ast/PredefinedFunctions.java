package org.ioopm.calculator.ast;

import java.util.ArrayList;

/**
  * Predefined functions that will be loaded into the program when it starts
  *
  * The current predefined functions are max, min
  * @see FunctionDeclaration
  *
  *
  * @author Jonathan Gustafsson, Joachim Forsberg
*/

public class PredefinedFunctions{

  private ArrayList<FunctionDeclaration> function;

  public PredefinedFunctions(){
    this.function = new ArrayList<FunctionDeclaration>();
    this.function.add(max());
    this.function.add(min());
    // this.function.add(fact());
  }

  private FunctionDeclaration max(){

    ArrayList<Variable> par = new ArrayList<Variable>();
    Variable x = new Variable("x");
    Variable y = new Variable("y");

    par.add(x);
    par.add(y);

    ArrayList<SymbolicExpression> seq = new ArrayList<SymbolicExpression>();
    Conditional cond = new Conditional(x, y, x, y, ">");
    seq.add(cond);
    seq.add(new Variable("end"));

    Sequence s = new Sequence(seq);
    return new FunctionDeclaration("max", par, s);

  }

  private FunctionDeclaration min(){

    ArrayList<Variable> par = new ArrayList<Variable>();
    Variable x = new Variable("x");
    Variable y = new Variable("y");

    par.add(x);
    par.add(y);

    ArrayList<SymbolicExpression> seq = new ArrayList<SymbolicExpression>();
    Conditional cond = new Conditional(x, y, y, x, ">");
    seq.add(cond);
    seq.add(new Variable("end"));

    Sequence s = new Sequence(seq);
    return new FunctionDeclaration("min", par, s);

  }

  // Not working
  private FunctionDeclaration fact(){
    ArrayList<Variable> par = new ArrayList<Variable>();
    ArrayList<Atom> args = new ArrayList<Atom>();
    ArrayList<SymbolicExpression> seq = new ArrayList<SymbolicExpression>();

    Variable n = new Variable("n");
    String name = "factorial";
    par.add(n);

    Variable m = new Variable("m");
    SymbolicExpression mm = new Assignment(m, new Subtraction(n, new Constant(1)));
    seq.add(mm);
    args.add(m);
    Conditional cond = new Conditional(n, new Constant(1), new Multiplication(new FunctionCall(name, args), n), new Constant(1), ">");
    seq.add(cond);
    seq.add(new Variable("end"));

    Sequence s = new Sequence(seq);
    return new FunctionDeclaration(name, par, s);
  }

  /**
    @return ArrayList of predefined functions
  */
  public ArrayList getPredefinedFunctions(){
    return this.function;
  }

}
