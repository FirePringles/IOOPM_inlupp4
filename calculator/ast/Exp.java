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
    public String getName() {
        return "exp";
    }
    public int getPriority() {
        return 25;
    }


    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }

}
