package org.ioopm.calculator.ast;

/** Represents a command.
*/
public abstract class Command extends SymbolicExpression {
    public int getPriority() {
        return -2;
    }

    /**
     * Defines equality between two different syntax trees
     *
     * @param       other The syntax tree to compare the current expression to
     * @return      Whether the two syntax trees are equal or not
     */
    public boolean equals(Object other) {
        return this.getClass().equals(other.getClass());
    }

    public boolean isCommand() {
        return true;
    }

}
