package org.ioopm.calculator.ast;


public class EvaluationVisitor implements Visitor {
    private Environment env = null;

    public SymbolicExpression evaluate(Symbolicexpression topLevel, Environment env) {
        this.env = env;
        return topLevel.accept(this);
    }

    // This method gets called from Addition.accept(Visitor v) -- you should
    // be able to see from the eval() methods how these should behave (i.e.,
    // compare this method with your Addition::eval() and Symbolic.addition)
    public SymbolicExpression visit(Addition n) {
        // Visit the left hand side and right hand side subexpressions
        SymbolicExpression left = n.getLHS().accept(this);
        SymbolicExpression right = n.getRHS().accept(this);
        // When we come back here, the visitor has visited all subexpressions,
        // meaning left and right point to newly created trees reduced to
        // the extent possible (best case -- both are constants)

        // If subexpressions are fully evaluated, replace them in
        // the tree with a constant whose value is the sub of the
        // subexpressions, if not, simply construct a new addition
        // node from the new subexpressions
        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }

    public SymbolicExpression visit(Assignment n){
      SymbolicExpression lhs = n.getLHS().accept(this);
      SymbolicExpression rhs = n.getRHS().accept(this);

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

    public SymbolicExpression visit(Constant n){
      return n;
    }

    public SymbolicExpression visit(Cos n){
      SymbolicExpression e = n.getSubTree().accept(this);
      if(e.isConstant()) {
          return new Constant(Math.cos(e.getValue()));
      }
      return new Cos(e);
    }

    public SymbolicExpression visit(Division n){

      SymbolicExpression lhs = n.getLHS().accept(this);
      SymbolicExpression rhs = n.getRHS().accept(this);

      if(lhs.isConstant() && rhs.isConstant()) {
          //Yes, I know you can't compare doubles like this
          //But such are the problems with rounding and computation
          if(rhs.getValue() == 0) {
              throw new IllegalExpressionException("Seriously, don't divide by zero...");
          }
          return new Constant(lhs.getValue() / rhs.getValue());
      }
      return new Division(lhs, rhs);

    }

    public SymbolicExpression visit(Exp n){
      SymbolicExpression e = n.getSubTree().accept(this);
      if(e.isConstant()) {
          return new Constant(Math.exp(e.getValue()));
      }
      return new Exp(e);
    }

    public SymbolicExpression visit(Log n){
      SymbolicExpression e = n.getSubTree().accept(this);
      if(e.isConstant()) {
          return new Constant(Math.log(e.getValue()));
      }
      return new Log(e);
    }

    public SymbolicExpression visit(Multiplication n){
      SymbolicExpression lhs = n.getLHS().accept(this);
      SymbolicExpression rhs = n.getRHS().accept(this);

      if(lhs.isConstant() && rhs.isConstant()) {
          return new Constant(lhs.getValue() * rhs.getValue());
      }
      return new Multiplication(lhs, rhs);
    }

    public SymbolicExpression visit(Negation n){
      SymbolicExpression e = n.getSubTree().accept(this);
      if(e.isConstant()) {
          return new Constant(-(e.getValue()));
      }
      return new Negation(e);
    }

    public SymbolicExpression visit(Quit n){
      return null;
    }

    public SymbolicExpression visit(Sin n){
      SymbolicExpression e = n.getSubTree().accept(this);
      if(e.isConstant()) {
          return new Constant(Math.sin(e.getValue()));
      }
      return new Sin(e);
    }

    public SymbolicExpression visit(Subtraction n){

      SymbolicExpression lhs = n.getLHS().accept(this);
      SymbolicExpression rhs = n.getRHS().accept(this);

      if(lhs.isConstant() && rhs.isConstant()) {
          return new Constant(lhs.getValue() - rhs.getValue());
      }
      return new Subtraction(lhs, rhs);

    }

// Här slutade jag!!
    public SymbolicExpression visit(Variable n){
      if(this.env.containsKey(this)) {
          return this.env.get(this).eval(env);
      }
      else {
          return this;
      }
    }

    public SymbolicExpression visit(Vars n){
      return null;
    }

}
