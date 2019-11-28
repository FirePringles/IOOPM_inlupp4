package org.ioopm.calculator.ast;

/** Represents a named constant such as pi.
*/
public class NamedConstant extends Constant implements Visitable {
    private String identifier;

    /**
     * Creates a named constant that cannot be reassigned
     *
     * @param name The name of the constant
     * @param value The value of the constant
     */
    public NamedConstant(String name, double value) {
        super(value);
        this.identifier = name;
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
