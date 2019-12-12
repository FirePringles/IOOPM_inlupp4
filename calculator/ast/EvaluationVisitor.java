package org.ioopm.calculator.ast;

/**
  The class that will evaluate all the nodes in the Abstract syntax tree (AST)
  Except for the evaluate method the only method existing is the visit method.
  All nodes in the AST has its own visit method.


*/
import java.util.ArrayList;
import java.util.HashMap;

public class EvaluationVisitor implements Visitor {
    private Environment env = null;
    private ArrayList<Atom> funcConstants;
    private ArrayList<Environment> stack;
    private HashMap<String, FunctionDeclaration> funcDecList;

    /**
      The topLevel method that will start a chain of call to the visit method.

      @param topLevel the top level SymbolicExpression to be evaluated
      @param env the environment containing all variables
      @param funcDecList the list containing all known functions

      @return an evaluated SymbolicExpression

    */

    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env, HashMap<String, FunctionDeclaration> funcDecList) {
        this.env = env;
        this.funcDecList = funcDecList;
        this.stack = new ArrayList<Environment>();
        this.stack.add(0, env);
        return topLevel.accept(this);
    }

    public SymbolicExpression visit(Addition n) {

        SymbolicExpression left = n.getLHS().accept(this);
        SymbolicExpression right = n.getRHS().accept(this);

        if (left.isConstant() && right.isConstant()) {
            return new Constant(left.getValue() + right.getValue());
        } else {
            return new Addition(left, right);
        }
    }

    public SymbolicExpression visit(Assignment n){
        SymbolicExpression lhs = n.getLHS().accept(this);
        SymbolicExpression rhs = n.getRHS();

        this.stack.get(0).put((Variable)rhs, lhs);


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
        throw new RuntimeException("Can't evaluate vars");
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

    public SymbolicExpression visit(Variable n){
        for(Environment e : this.stack){
            if(e.containsKey(n)){
                return e.get(n).accept(this);
            }
        }
        return n;//new Variable(n.toString());
    }

    public SymbolicExpression visit(Scope n){
        // Push on stack
        this.stack.add(0, new Environment());

        SymbolicExpression exp = n.getExp().accept(this);

        // Pop on stack
        this.stack.remove(0);

        return exp;
    }

    public SymbolicExpression visit(Vars n){
        throw new RuntimeException("Can't evaluate vars");
    }

    /**
      


    */

    public SymbolicExpression visit(Conditional n){

        SymbolicExpression result = null;

        SymbolicExpression left = n.getLHS().accept(this);
        SymbolicExpression right = n.getRHS().accept(this);

        if(left.isConstant() && right.isConstant()){
            if(n.getOperation().equals(">=")){
                if(left.getValue() >= right.getValue()){
                    result = n.getS1().accept(this);
                } else {
                    result = n.getS2().accept(this);
                }
            } else if(n.getOperation().equals("<=")){
                if(left.getValue() <= right.getValue()){
                    result = n.getS1().accept(this);
                } else {
                    result = n.getS2().accept(this);
                }
            } else if(n.getOperation().equals(">")){
                if(left.getValue() > right.getValue()){
                    result = n.getS1().accept(this);
                } else {
                    result = n.getS2().accept(this);
                }
            } else if(n.getOperation().equals("<")){
                if(left.getValue() < right.getValue()){
                    result = n.getS1().accept(this);
                } else {
                    result = n.getS2().accept(this);
                }
            } else if(n.getOperation().equals("==")){
                if(left.getValue() == right.getValue()){
                    result = n.getS1().accept(this);
                } else {
                    result = n.getS2().accept(this);
                }
            }
        } else {
            throw new RuntimeException("Can't evaluate this, variables needs to have values");
        }
        return result;
    }

    public SymbolicExpression visit(FunctionDeclaration n){
        ArrayList<Variable> vars = n.getFunctionPara();

        for(int i = 0; i < vars.size(); i++){
	    this.stack.get(0).put(vars.get(i), this.funcConstants.get(i).accept(this));
        }
        SymbolicExpression sequence = n.getFunctionBody().accept(this);

        return sequence;
    }

    public SymbolicExpression visit(Sequence n){
        ArrayList<SymbolicExpression> body = n.getBody();
        SymbolicExpression result = new Constant(0);
        for(int i = 0; i<n.getBodySize()-1; i++){
            result = body.get(i).accept(this);
        }
        return result;
    }

    public SymbolicExpression visit(FunctionCall n){
        this.stack.add(0, new Environment());
        SymbolicExpression result;
        String name = n.getFunctionName();
        this.funcConstants = n.getFunctionArgs();

        if(this.funcDecList.containsKey(name) && (this.funcDecList.get(n.getFunctionName()).getArgLen() == n.getArgLen())){
          result = funcDecList.get(name).accept(this);

        } else {
           throw new IllegalExpressionException("That function does not exist or the arguments are wrong");
         }
	this.funcConstants = null;
        this.stack.remove(0);
        return result;
    }

}
