package org.ioopm.calculator.ast;

/** Represents the e^x operation.
*/
public class Exp extends Unary {

    /**
     * Creates an exp object
     *
     * @param x The subtree
     */
    public Exp(SymbolicExpression x) {
        super(x);
    }

    @Override
    public String getName() {
        return "exp";
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
