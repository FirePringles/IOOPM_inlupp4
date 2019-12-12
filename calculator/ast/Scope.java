package org.ioopm.calculator.ast;

/**
  * A Scope instance will have local variables and will not affect the program outside
  * its scope. Functions will have their own scopes.
  *
  * @see FunctionDeclaration
  * @see FunctionCall
  *
  * @author Jonathan Gustafsson, Joachim Forsberg
*/

public class Scope extends SymbolicExpression{

  private SymbolicExpression exp;

    public Scope(SymbolicExpression exp){
      this.exp = exp;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }

    /**
      * @return SymbolicExpression that is inside the scope
    */

    public SymbolicExpression getExp(){
      return this.exp;
    }

}
