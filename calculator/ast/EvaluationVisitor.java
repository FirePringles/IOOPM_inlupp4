package org.ioopm.calculator.ast;

import java.util.ArrayList;
import java.util.HashMap;

public class EvaluationVisitor implements Visitor {
    private Environment env = null;
    private ArrayList<Atom> funcConstants;
    private ArrayList<Environment> stack;
    private HashMap<String, FunctionDeclaration> funcDecList;



    public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env, HashMap<String, FunctionDeclaration> funcDecList) {
        this.env = env;
        this.funcDecList = funcDecList;
        this.stack = new ArrayList<Environment>();
        this.stack.add(0, env);
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

    // This is also another beauty!

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
          this.stack.get(0).put(vars.get(i), this.funcConstants.get(i));

        }
        SymbolicExpression sequence = n.getFunctionBody().accept(this);

        return sequence;
    }

    public SymbolicExpression visit(Sequence n){
        ArrayList<SymbolicExpression> body = n.getBody();
        SymbolicExpression result = null;
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


        if(this.funcDecList.containsKey(name)){
          result = funcDecList.get(name).accept(this);
          this.funcConstants = null;
        } else {
           throw new RuntimeException("Cant evalute");
         }

        this.stack.remove(0);
        return result;
    }

}
