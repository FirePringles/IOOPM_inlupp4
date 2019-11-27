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

    public SymbolicExpression eval(Environment env) {
        SymbolicExpression lhs = this.getLHS();
        SymbolicExpression rhs = this.getRHS();

        lhs = lhs.eval(env);
        rhs = rhs.eval(env);

        if(lhs.isConstant() && rhs.isConstant()) {
            //Yes, I know you can't compare doubles like this
            //But such are the problems with rounding and computation
            if(rhs.getValue() == 0) {
                throw new IllegalExpressionException("Seriously, don't divide by zero...");
            }
            return new Constant(lhs.getValue() / rhs.getValue());
        }
        return this;
    }

    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
