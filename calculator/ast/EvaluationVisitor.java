package org.ioopm.calculator.ast;

import java.util.ArrayList;

public class EvaluationVisitor implements Visitor{
    
  private Environment env = null;
    private ArrayList<Environment> stack;
  public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env){
      this.env = env;
      this.stack = new ArrayList<Environment>();
      this.stack.add(0,env);
      return topLevel.accept(this);
  }

  public SymbolicExpression visit(Addition n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS().accept(this);

    if(left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() + right.getValue());
    } else {
      return new Addition(left,right);
    }
  }
  public SymbolicExpression visit(Subtraction n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS().accept(this);

    if(left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() - right.getValue());
    } else {
      return new Subtraction(left,right);
    }
  }
  public SymbolicExpression visit(Division n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS().accept(this);

    if(left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() / right.getValue());
    } else {
      return new Division(left,right);
    }
  }
  public SymbolicExpression visit(Multiplication n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS().accept(this);

    if(left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() * right.getValue());
    } else {
      return new Multiplication(left,right);
    }
  }
  public SymbolicExpression visit(Assignment n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS();

    this.stack.get(0).put((Variable)right,left);

    if(left.isConstant()){
      return new Constant(left.getValue());
    } else {
	return left;
    }
  }
  public SymbolicExpression visit(Cos n){
    SymbolicExpression sub = n.getSubTree().accept(this);
    if(sub.isConstant()){
      return new Constant(Math.cos(sub.getValue()));
    } else {
      return new Cos(sub);
    }
  }
  public SymbolicExpression visit(Exp n){
    SymbolicExpression sub = n.getSubTree().accept(this);
    if(sub.isConstant()){
      return new Constant(Math.exp(sub.getValue()));
    } else {
      return new Exp(sub);
    }
  }
  public SymbolicExpression visit(Log n){
    SymbolicExpression sub = n.getSubTree().accept(this);
    if(sub.isConstant()){
      return new Constant(Math.log(sub.getValue()));
    } else {
      return new Log(sub);
    }
  }
  public SymbolicExpression visit(Sin n){
    SymbolicExpression sub = n.getSubTree().accept(this);
    if(sub.isConstant()){
      return new Constant(Math.sin(sub.getValue()));
    } else {
      return new Sin(sub);
    }
  }
  public SymbolicExpression visit(Negation n){
    SymbolicExpression sub = n.getSubTree().accept(this);
    if(sub.isConstant()){
      return new Constant(-sub.getValue());
    } else {
      return new Negation(sub);
    }
  }
  public SymbolicExpression visit(Quit n){
    throw new RuntimeException("eval method can't be used on comands!");
  }
  public SymbolicExpression visit(Vars n){
    throw new RuntimeException("eval method can't be used on comands!");
  }
  public SymbolicExpression visit(Clear n){
    throw new RuntimeException("eval method can't be used on comands!");
  }
  public SymbolicExpression visit(Constant n){
    return new Constant(n.getValue());
  }
  public SymbolicExpression visit(Variable n){
    for(Environment envs : this.stack){
        if(envs.containsKey(n)){
	    return envs.get(n).accept(this);
	}
    }
      return n;
  }
  public SymbolicExpression visit(Scope n){
      this.stack.add(0, new Environment());

      SymbolicExpression scope = n.getExp().accept(this);
      
      System.out.println(scope.toString());

      this.stack.remove(0);

      return scope;
  }
}
