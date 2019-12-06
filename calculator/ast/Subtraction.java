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

    @Override
    public String getName() {
        return " - ";
    }

    @Override
    public int getPriority() {
        return 75;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
