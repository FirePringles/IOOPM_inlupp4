package org.ioopm.calculator.ast;

/** Represents natural logarithm operation.
*/
public class Log extends Unary implements Visitable{

    /**
     * Creates a log object
     *
     * @param x The subtree
     */
    public Log(SymbolicExpression x) {
        super(x);
    }
    public String getName() {
        return "log";
    }

    public int getPriority() {
        return 25;
    }

    
    public SymbolicExpression eval(Environment env) {
        SymbolicExpression e = this.getSubTree().eval(env);
        this.setSubTree(e);
        if(e.isConstant()) {
            return new Constant(Math.log(e.getValue()));
        } 
        return this;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
