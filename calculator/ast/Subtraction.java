package org.ioopm.calculator.ast;

/** Represents a subtraction.
*/
public class Subtraction extends Binary implements Visitable{
    /**
     * Creates a subtraction object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Subtraction(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }
    public String getName() {
        return " - ";
    }
    public int getPriority() {
        return 75;
    }


    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
