package org.ioopm.calculator.ast;

/** Represents a subtraction.
*/
public class Subtraction extends Binary implements Visitable{
    /**
     * Creates a subtraction object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Subtraction(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }
    public String getName() {
        return " - ";
    }
    public int getPriority() {
        return 75;
    }
    
    public SymbolicExpression eval(Environment env) {
        SymbolicExpression lhs = this.getLHS();
        SymbolicExpression rhs = this.getRHS();

        lhs = lhs.eval(env);
        rhs = rhs.eval(env);

        if(lhs.isConstant() && rhs.isConstant()) {
            return new Constant(lhs.getValue() - rhs.getValue());
        }
        return this;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
