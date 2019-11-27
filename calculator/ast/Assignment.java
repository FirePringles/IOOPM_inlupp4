package org.ioopm.calculator.ast;

/** Represents a variable assignment.
*/
public class Assignment extends Binary {
    /**
     * Creates an assignment object
     *
     * @param e1 The left subtree
     * @param e2 The right subtree
     */
    public Assignment(SymbolicExpression e1, SymbolicExpression e2) {
        super(e1, e2);
    }

    public String getName() {
        return " := ";
    }

    public int getPriority() {
        return 100;
    }


    public SymbolicExpression eval(Environment env) {
        SymbolicExpression lhs = this.getLHS();
        SymbolicExpression rhs = this.getRHS();

        if(rhs instanceof NamedConstant) {
            throw new IllegalExpressionException("Cannot reassign constant");
        }

        lhs = lhs.eval(env);
        env.put((Variable) rhs, lhs);

        if(lhs.isConstant()) {
            return new Constant(lhs.getValue());
        }
        return lhs;
    }

    public SymbolicExpression accept(Visitor v){
      return v.visit(this);
    }
}
