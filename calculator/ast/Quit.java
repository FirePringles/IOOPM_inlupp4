package org.ioopm.calculator.ast;

/** Represents the quit command.
*/
public class Quit extends Command {
    private static final Quit theInstance = new Quit();
    private Quit() {}

    /**
     * Gives the singleton instance of the particular command
     *
     * @return      The command that is asked for
     */
    public static Quit instance() {
        return theInstance;
    }

    @Override
    public SymbolicExpression accept(Visitor v){
      throw new RuntimeException("Can't be visited");
    }
}
