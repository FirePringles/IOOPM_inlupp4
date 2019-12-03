package org.ioopm.calculator.ast;

/** Represents the unary negation operation.
*/
public class Negation extends Unary implements Visitable {


    /**
     * Creates a negation object
     *
     * @param x The subtree
     */
    public Negation(SymbolicExpression x) {
        super(x);
    }

    @Override
    public String getName() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public String toString() {
        return this.getName() + "(" + this.getSubTree().toString() + ")";
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
