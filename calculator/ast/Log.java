package org.ioopm.calculator.ast;

/** Represents natural logarithm operation.
*/
public class Log extends Unary {

    /**
     * Creates a log object
     *
     * @param x The subtree
     */
    public Log(SymbolicExpression x) {
        super(x);
    }

    @Override
    public String getName() {
        return "log";
    }


    @Override
    public int getPriority() {
        return 25;
    }


    @Override
    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
