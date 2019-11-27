package org.ioopm.calculator.ast;

/** Represents the clear command.
*/
public class Clear extends Command {
    private static final Clear theInstance = new Clear();
    private Clear() {}

    /**
     * Gives the singleton instance of the particular command
     *
     * @return      The command that is asked for
     */
    public static Clear instance() {
        return theInstance;
    }

    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
