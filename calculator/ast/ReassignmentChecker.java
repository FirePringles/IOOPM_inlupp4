package org.ioopm.calculator.ast;

public class ReassignmentChecker implements Visitor{
  private Environment env;

  public boolean reasChecker(SymbolicExpression topLevel, Environment env){
    this.env = env;

    try{
      topLevel.accept(this);
      return true;
    } catch(IllegalExpressionException e){
      System.out.println("Error");
      return false;
    }

  }
  public SymbolicExpression visit(Addition a) {
    //Need to get this working.
    throw new IllegalExpressionException("Nono");
  }

  public SymbolicExpression visit(Assignment a){
      a.getLHS().accept(this);
      a.getRHS().accept(this);
      return a;
  }

  public SymbolicExpression visit(Constant a){
    return a;
  }

  public SymbolicExpression visit(Clear a){
    throw new IllegalExpressionException("Can't on command classes");
  }

  public SymbolicExpression visit(Cos a){
    a.getSubTree().accept(this);
    return a;
  }

  public SymbolicExpression visit(Division a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }

  public SymbolicExpression visit(Exp a){
    a.getSubTree().accept(this);
    return a;
  }

  public SymbolicExpression visit(Log a){
    a.getSubTree().accept(this);
    return a;
  }

  public SymbolicExpression visit(Multiplication a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }

  public SymbolicExpression visit(Negation a){
    a.getSubTree().accept(this);
    return a;
  }

  public SymbolicExpression visit(Quit a){
    throw new IllegalExpressionException("Can't on command classes");
  }

  public SymbolicExpression visit(Sin a){
    a.getSubTree().accept(this);
    return a;
  }

  public SymbolicExpression visit(Subtraction a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }

  public SymbolicExpression visit(Variable a){
    return a;
  }

  public SymbolicExpression visit(Scope a){
    stack.add(0, new ArrayList<SymbolicExpression>());
    a.getExp().accept(this);
    stack.remove(0);
    return a;
  }

  public SymbolicExpression visit(Vars a){
    throw new IllegalExpressionException("Can't on command classes");
  }
}
