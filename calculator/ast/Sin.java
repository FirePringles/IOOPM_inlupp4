package org.ioopm.calculator.ast;

/** Represents the sine operation.
*/
public class Sin extends Unary {

    public Sin(SymbolicExpression x) {
        super(x);
    }

    @Override
    public String getName() {
        return "sin";
    }

    @Override
    public int getPriority() {
        return 25;
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
