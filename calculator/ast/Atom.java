package org.ioopm.calculator.ast;

/** Represents an atomic expression.
*/
public abstract class Atom extends SymbolicExpression {

    @Override
    public int getPriority() {
        return 0;
    }
}
