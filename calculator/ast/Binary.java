package org.ioopm.calculator.ast;

/** Represents a binary expression.
*/
public abstract class Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;

    /**
     * Creates a Binary expression, meant to be used by subclasses
     *
     * @param l The left subtree
     * @param r The right subtree
     */
    public Binary(SymbolicExpression l, SymbolicExpression r) {
        this.lhs = l;
        this.rhs = r;
    }

    /**
     * Gets the left subtree of a binary expression
     *
     * @return      The left subtree of the Expression.
     */
    protected SymbolicExpression getLHS() {
        return this.lhs;
    }
    /**
     * Gets the right subtree of a binary expression
     *
     * @return      The right subtree of the Expression.
     */
    protected SymbolicExpression getRHS() {
        return this.rhs;
    }

    /**
     * Sets the left subtree of a binary expression
     *
     * @param e The ast that is to be set as the left subtree
     */
    protected void setLHS(SymbolicExpression e) {
        this.lhs = e;
    }

    /**
     * Sets the right subtree of a binary expression
     *
     * @param e The ast that is to be set as the right subtree
     */
    protected void setRHS(SymbolicExpression e) {
        this.rhs = e;
    }

    /**
     * Creates a human readable string representing the expression suitable for printing
     *
     * @return      The string that represents the syntax tree with correctly added parentheses
     */

    @Override
    public String toString() {
        String expr = "";
        if(lhs.getPriority() >= this.getPriority()) {
            expr = expr + "(" + this.lhs.toString() + ")";
        } else {
            expr = expr + this.lhs.toString();
        }

        expr = expr + this.getName();

        if(rhs.getPriority() >= this.getPriority()) {
            expr = expr + "(" + this.rhs.toString() + ")";
        } else {
            expr = expr + this.rhs.toString();
        }
        return expr;
    }

    /**
     * Defines equality between two different binary expressions
     *
     * @param       other The binary expression to compare the current expression to
     * @return      Whether the two binary expressions are equal or not
     */
    public boolean equals(Binary other) {
        return this.getName().equals(other.getName())
            && this.lhs.equals(other.lhs) && this.rhs.equals(other.rhs);
    }

    /**
     * Defines equality between two different syntax trees
     *
     * @param       other The syntax tree to compare the current expression to
     * @return      Whether the two syntax trees are equal or not
     */

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Binary)) {
            return false;
        } else {
            return this.equals((Binary) other);
        }
    }
}
