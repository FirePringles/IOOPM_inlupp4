package org.ioopm.calculator.ast;

/** Represents an addition.
*/
public class Addition extends Binary {
    /**
     * Creates an addition object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Addition(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }
    @Override
    public String getName() {
        return " + ";
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
