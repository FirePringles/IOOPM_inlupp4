package org.ioopm.calculator.ast;

/** Represents a unary expression.
*/
public abstract class Unary extends SymbolicExpression{
    private SymbolicExpression sub;

    /**
     * Creates a unary expression, meant to be used by subclasses
     *
     * @param e The subtree
     */
    public Unary(SymbolicExpression e) {
        this.sub = e;
    }

    /**
     * Gets the subtree of a unary expression
     *
     * @return      The subtree of the Expression.
     */
    protected SymbolicExpression getSubTree() {
        return this.sub;
    }

    
    /**
     * Sets the subtree of a unary expression
     *
     * @param e The ast that is to be set as the subtree
     */
    protected void setSubTree(SymbolicExpression e) {
        this.sub = e;
    }

    /**
     * Creates a human readable string representing the expression suitable for printing
     *
     * @return      The string that represents the syntax tree with correctly added parentheses
     */
    public String toString() {
        return this.getName() + "(" + this.sub.toString() + ")";
    }

    /**
     * Defines equality between two different unary expressions
     *
     * @param       other The unary expression to compare the current expression to
     * @return      Whether the two unary expressions are equal or not
     */
    public boolean equals(Unary other) {
        return this.getName().equals(other.getName()) && this.sub.equals(other.sub);
    }

    /**
     * Defines equality between two different syntax trees
     *
     * @param       other The syntax tree to compare the current expression to
     * @return      Whether the two syntax trees are equal or not
     */
    public boolean equals(Object other) {
        if(!(other instanceof Unary)) {
            return false;
        } else {
            return this.equals((Unary) other);
        }
    }
}
