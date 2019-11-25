package org.ioopm.calculator.ast;

/** Represents the sine operation.
*/
public class Sin extends Unary {

    public Sin(SymbolicExpression x) {
        super(x);
    }
    public String getName() {
        return "sin";
    }
    public int getPriority() {
        return 25;
    }

    public SymbolicExpression eval(Environment env) {
        SymbolicExpression e = this.getSubTree().eval(env);
        this.setSubTree(e);
        if(e.isConstant()) {
            return new Constant(Math.sin(e.getValue()));
        } 
        return this;
    }
}
