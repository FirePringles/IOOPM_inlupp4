package org.ioopm.calculator.ast;

/** Represents a named variable.
*/
public class Variable extends Atom implements Comparable<Variable>{
    private String identifier;

    /**
     * Creates a variable object
     *
     * @param name The identifier corresponding to the variable
     */
    public Variable(String name) {
        this.identifier = name;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    public boolean equals(Variable other) {
        return this.identifier.equals(other.identifier);
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Variable var){
	return this.toString().compareTo(var.toString());
    }

    /**
     * Gives the hash code for a variable
     *
     * @return The hash code od the specific identifier
     */
    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
