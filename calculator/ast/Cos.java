package org.ioopm.calculator.ast;

/** Represents the cosine operation.
*/
public class Cos extends Unary {

    /**
     * Creates a cosine object
     *
     * @param x The subtree
     */
    public Cos(SymbolicExpression x) {
        super(x);
    }
    public String getName() {
        return "cos";
    }

    public int getPriority() {
        return 25;
    }


    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
