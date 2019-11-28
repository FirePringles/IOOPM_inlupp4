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
    public String getName() {
        return "-";
    }

    public int getPriority() {
        return 100;
    }

    public String toString() {
        return this.getName() + "(" + this.getSubTree().toString() + ")";
    }

    public SymbolicExpression eval(Environment env) {
        SymbolicExpression e = this.getSubTree().eval(env);
        if(e.isConstant()) {
            return new Constant(-(e.getValue()));
        } 
        return this;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
