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

    @Override
    public String getName() {
        return "cos";
    }

    @Override
    public int getPriority() {
        return 25;
    }


    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
