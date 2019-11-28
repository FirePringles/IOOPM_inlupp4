package org.ioopm.calculator.ast;

/** Represents a named variable.
*/
public class Variable extends Atom implements Visitable {
    private String identifier;

    /**
     * Creates a variable object
     *
     * @param name The identifier corresponding to the variable
     */
    public Variable(String name) {
        this.identifier = name;
    }

    public int getPriority() {
        return -1;
    }

    public String toString() {
        return this.identifier;
    }

    public boolean equals(Variable other) {
        return this.identifier.equals(other.identifier);
    }

    public boolean equals(Object other) {
        if(other instanceof Variable) {
            return this.equals((Variable) other);
        } else {
            return false;
        }
    }

    /**
     * Gives the hash code for a variable
     *
     * @return The hash code od the specific identifier
     */
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public SymbolicExpression eval(Environment env) {
        if(env.containsKey(this)) {
            return env.get(this).eval(env);
        }
        else {
            return this;
        }
    }
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
