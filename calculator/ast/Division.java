package org.ioopm.calculator.ast;

/** Represents a division.
*/
public class Division extends Binary implements Visitable{

    /**
     * Creates a division object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Division(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }
    public String getName() {
        return " / ";
    }
    public int getPriority() {
        return 50;
    }


    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
