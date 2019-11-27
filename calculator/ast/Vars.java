package org.ioopm.calculator.ast;

/** Represents the vars command.
*/
public class Vars extends Command {
    private static final Vars theInstance = new Vars();
    private Vars() {}

    /**
     * Gives the singleton instance of the particular command
     *
     * @return      The command that is asked for
     */
    public static Vars instance() {
        return theInstance;
    }

    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
