package org.ioopm.calculator.ast;

/** Represents the vars command.
*/
public class Vars extends Command implements Visitable {
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
    @Override
    public SymbolicExpression accept(Visitor v) {
	return v.visit(this);
    }
}
