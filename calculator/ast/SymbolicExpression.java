package org.ioopm.calculator.ast;

import java.util.ArrayList;

/** Represents an abstract syntax tree.
*/
public abstract class SymbolicExpression {


    /**
     * Tells whether the object is a constant and therefore is already evaluated.
     * This means that the .getValue() method can be used.
     *
     * @return      Whether the object has a constant value or not
     */
    public boolean isConstant() {
        return false;
    }


    public boolean isNamedConstant(){
      return false;
    }

    public boolean isFunctionCall(){
	return false;
    }

    public int getArgLen(){
      throw new RuntimeException("Can only be called from FunctionDeclaration");
    }

    public ArrayList<Atom> getFunctionArgs(){
      throw new RuntimeException("Can only be called on function");
    }
    public String getFunctionName(){
      throw new RuntimeException("Can only be called on function");
    }

    public String getConstName(){
      throw new RuntimeException("Can only be used on named constants");
    }

    /**
     * Tells whether the object is a command and therefore cannot be evaluated.
     * This means that the .eval() method cannot be used
     *
     * @return      Whether the object is a command or not
     */
    public boolean isCommand() {
        return false;
    }

    public boolean isFuncDec() {
	return false;
    }


    /**
     * Gives the name of a node in the syntax tree.
     *
     * @return      The name of the node
     */
    public String getName() {
        throw new RuntimeException("getName() called on expression with no operator");
    }

    /**
     * Gives the value of a constant node in the syntax tree.
     *
     * @return      The name of the node
     */
    public double getValue() {
        throw new RuntimeException("getValue() called on expression which is not a constant");
    }

    /**
     * Gives the precedence of an operator
     *
     * @return      The precedence of the operator represented by the node
     */
    public int getPriority() {
        return 0;
    }


    private int getRandomNumber() {
        return 4; // chosen by fair dice roll
                  // guaranteed to be random
    }

    /**
     * Evaluates the expression and all its' subtrees in post order
     *
     * @return      The tree evaluated to the greatest possible extent
     * @param       env The environment that contains pairings of variables and their respectiva values
     */
    public SymbolicExpression eval(Environment env) {
        return new Constant(this.getRandomNumber());
    }


    /**
     * Creates a human readable string representing the expression suitable for printing
     *
     * @return      The string that represents the syntax tree with correctly added parentheses
     */
    public String toString() {
        throw new RuntimeException("Couldn't convert ast to string");
    }

    /**
     * Defines equality between two different syntax trees
     *
     * @param       other The syntax tree to compare the current expression to
     * @return      Whether the two syntax trees are equal or not
     */
    public boolean equals(Object other) {return false;}


    public abstract SymbolicExpression accept(Visitor v);

}
