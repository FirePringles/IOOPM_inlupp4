package org.ioopm.calculator.ast;

/** Represents the sine operation.
*/
public class Sin extends Unary {

    public Sin(SymbolicExpression x) {
        super(x);
    }
    public String getName() {
        return "sin";
    }
    public int getPriority() {
        return 25;
    }


    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
