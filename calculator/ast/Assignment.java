package org.ioopm.calculator.ast;

/** Represents a variable assignment.
*/
public class Assignment extends Binary {
    /**
     * Creates an assignment object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Assignment(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }

    @Override
    public String getName() {
        return " := ";
    }

    @Override
    public int getPriority() {
        return 100;
    }


    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
