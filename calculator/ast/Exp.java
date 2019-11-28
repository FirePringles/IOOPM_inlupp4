package org.ioopm.calculator.ast;

/** Represents the e^x operation.
*/
public class Exp extends Unary implements Visitable {

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

    
    public SymbolicExpression eval(Environment env) {
        SymbolicExpression e = this.getSubTree().eval(env);
        this.setSubTree(e);
        if(e.isConstant()) {
            return new Constant(Math.exp(e.getValue()));
        } 
        return this;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }

}
