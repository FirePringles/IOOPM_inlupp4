package org.ioopm.calculator.ast;

/** Represents a constant.
*/
public class Constant extends Atom {
    private double value;

    /**
     * Creates a constant object with the given value
     *
     * @param val The value that the constant represents
     */
    public Constant(double val) {
        this.value = val;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
    /**
     * Defines equality between two different binary expressions
     *
     * @param       other The binary expression to compare the current expression to
     * @return      Whether the two binary expressions are equal or not
     */
    public boolean equals(Constant other) {
        return Math.round(this.value) == Math.round(other.value);
    }

    /**
     * Defines equality between two different syntax trees
     *
     * @param       other The syntax tree to compare the current expression to
     * @return      Whether the two syntax trees are equal or not
     */

    @Override
    public boolean equals(Object other) {
        if(other instanceof Constant) {
            return this.equals((Constant) other);
        } else {
            return false;
        }
    }

    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }

}
