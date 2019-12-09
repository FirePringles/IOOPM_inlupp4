package org.ioopm.calculator.ast;

public class NamedConstantChecker implements Visitor{

  private Environment env;

  public boolean checkNamedConstant(SymbolicExpression topLevel, Environment env){
    this.env = env;
    try{
      topLevel.accept(this);
      return true;
    } catch(IllegalExpressionException iee){
      System.out.println(iee.getMessage());
      return false;
    }
  }

  public SymbolicExpression visit(Addition a) {
      a.getLHS().accept(this);
      a.getRHS().accept(this);
      return a;
  }


  // Need better printing (if we are to follow the example on the website)
  public SymbolicExpression visit(Assignment a){
    String errorMSG = "";
    if(a.getRHS().isNamedConstant()){
      errorMSG = a.getLHS().toString() + " = " + a.getRHS().getConstName();
      throw new IllegalExpressionException("Illegal assignment \n" + "  " + errorMSG);
    } else {
      a.getLHS().accept(this);
      a.getRHS().accept(this);
      return a;
    }
  }

  public SymbolicExpression visit(Constant a){
    return a;
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
    throw new IllegalExpressionException("Cannot check types of Command class");
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
    a.getExp().accept(this);
    return a;
  }

  public SymbolicExpression visit(Vars a){
    throw new IllegalExpressionException("Cannot check types of Command class");
  }

  public SymbolicExpression visit(Conditional a){
    return a;
  }

  public SymbolicExpression visit(FunctionDeclaration a){
    return a;
  }

}
