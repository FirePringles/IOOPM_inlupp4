package org.ioopm.calculator.ast;

// SHOULD BE REMOVED. WE ALREADY OVERRIDE THIS ABSTRACT METHOD FROM SymbolicExpression


/**

  * @author Jonathan Gustafsson, Joachim Forsberg

*/

public interface Visitable{

  public SymbolicExpression accept(Visitor v);

}
