package org.ioopm.calculator.ast;

/**

  @author Jonathan Gustafsson, Joachim Forsberg

*/


public class NamedConstantChecker implements Visitor{

  private Environment env;

  /**
    * The topLevel method that will start a chain of call to the visit method.
    *
    * @param topLevel the top level SymbolicExpression to be evaluated
    * @param env the environment containing all variables
    * @return an evaluated SymbolicExpression

  */

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


  /**
    * @param a assignment to be checked
    * @return SymbolicExpression that is checked
  */

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

  /**
    * There is no need to check whether this is a named constant or not.
    *This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Constant a){
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Cos a){
    a.getSubTree().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Division a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Exp a){
    a.getSubTree().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Log a){
    a.getSubTree().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */


  public SymbolicExpression visit(Multiplication a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Negation a){
    a.getSubTree().accept(this);
    return a;
  }

  /**
    * You should not visit a class of the type Command
    * @exception IllegalExpressionException if visited
  */

  public SymbolicExpression visit(Quit a){
    throw new IllegalExpressionException("Cannot check types of Command class");
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Sin a){
    a.getSubTree().accept(this);
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Subtraction a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return a;
  }


  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Variable a){
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Scope a){
    a.getExp().accept(this);
    return a;
  }

  /**
    *You should not visit a class of the type Command
    * @exception IllegalExpressionException if visited
  */

  public SymbolicExpression visit(Vars a){
    throw new IllegalExpressionException("Cannot check types of Command class");
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(Conditional a){
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

  public SymbolicExpression visit(FunctionDeclaration a){
    return a;
  }

  /**
    * There is no need to check whether this is a named constant or not.
    * This will just return the same instance of a.
    * @param a to be checked
    * @return the same SymbolicExpression
  */

 public SymbolicExpression visit(Sequence a){
	return a;
    }

    /**
      * There is no need to check whether this is a named constant or not.
      * This will just return the same instance of a.
      * @param a to be checked
      * @return the same SymbolicExpression
    */

    public SymbolicExpression visit(FunctionCall a){
	return a;
    }

}
