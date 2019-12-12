package org.ioopm.calculator.ast;

import java.util.ArrayList;

/**
  Will make sure that an expression will not assign to same variable twice
  in the same expression

  @author Jonathan Gustafsson, Joachim Forsberg

*/


public class ReassignmentChecker implements Visitor{
  private Environment env;

  // Think it is working, feel like we could do a better solution however, idk
  private ArrayList<SymbolicExpression> checkList = new ArrayList<SymbolicExpression>();
  private ArrayList<ArrayList<SymbolicExpression>> stack = new ArrayList<ArrayList<SymbolicExpression>>();


  /**
    The topLevel method that will start a chain of call to the visit method.

    @param topLevel the top level SymbolicExpression to be evaluated
    @param env the environment containing all variables

    @return an evaluated SymbolicExpression

  */


  public boolean reassignedCheck(SymbolicExpression topLevel, Environment env){
    this.env = env;
    checkList.clear();
    stack.add(checkList);

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


  /**

    @param a assignment to be checked
    @return SymbolicExpression that is checked

  */

    // Need better printing (if we are to follow the example on the website)
  public SymbolicExpression visit(Assignment a){
    if(this.stack.get(0).contains(a.getRHS())){

      throw new IllegalExpressionException(a.toString() + " is already assigned in this expression");
    } else {
      stack.get(0).add(a.getRHS());
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
    stack.add(0, new ArrayList<SymbolicExpression>());
    a.getExp().accept(this);
    stack.remove(0);
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

    public SymbolicExpression visit(Sequence a){
	return a;
    }

    public SymbolicExpression visit(FunctionCall a){
	return a;
    }
}
