package org.ioopm.calculator.ast;

import java.util.ArrayList;

/**

  * A Sequence is used together with FunctionDeclaration to create a function.
  * The Sequence will act as the body of the function. It will later on be evaluated
  * in the order it was parsed, meaning that the last expression will be the return value
  * of the function
  *
  * @see FunctionCall
  * @see FunctionDeclaration
  *
  * @author Jonathan Gustafsson, Joachim Forsberg
*/




public class Sequence extends SymbolicExpression{

    /** The body of the function. Will contain zero or more SymbolicExpression */
    public ArrayList<SymbolicExpression> functionBody;

    public Sequence(ArrayList<SymbolicExpression> body){
	this.functionBody = body;
    }

    /**
      * @return ArrayList representing the body of the function
    */

    public ArrayList<SymbolicExpression> getBody(){
	return this.functionBody;
    }

    /**
      * @return int the size of the body, i.e the number of SymbolicExpression in the body
    */
    public int getBodySize(){
	return this.functionBody.size();
    }

    /**
      * @param exp to add to the body
    */

    public void addToBody(SymbolicExpression exp){
	this.functionBody.add(exp);
    }

    @Override
    public SymbolicExpression accept(Visitor v){
	return v.visit(this);
    }

    public boolean equals(Sequence other) {
        return this.getBody().equals(other.getBody());
    }
    
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Sequence)) {
            return false;
        } else {
            return this.equals((Sequence) other);
        }
    }
}
