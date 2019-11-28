package org.ioopm.calculator.ast;

/** Represents an addition.
*/
public class Addition extends Binary implements Visitable{
    /**
     * Creates an addition object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Addition(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }

    public String getName() {
        return " + ";
    }

    public int getPriority() {
        return 75;
    }

    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }

}
