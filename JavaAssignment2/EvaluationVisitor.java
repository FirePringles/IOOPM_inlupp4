public class EvaluationVisitor implements Visitor{
  private Environment env = null;

  public SymbolicExpression evaluate(SymbolicExpression topLevel, Environment env){
    this.env = env;
    return topLevel.accept(this);
  }

  public SymbolicExpression visit(Addition n){
    SymbolicExpression left = n.getLHS().accept(this);
    SymbolicExpression right = n.getRHS().accept(this);

    if(left.isConstant() && right.isConstant()){
      return new Constant(left.getValue() + right.getValue());
    } else {
      return new Addtion(left,right);
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
    SymbolicExpression left = n.lhs().accept(this);
    SymbolicExpression right = n.rhs().accept(this);

    if(right instanceOf NamedConstant){
      throw new IllegalExpressionException("Cannot resign this named constant");
    }

    if(left.isConstant(){
      return new Constant(left.getValue());
    } else {
      return new Assignment(left,rigt);
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
      return new Eos(sub);
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
      return new Constant(-sub.getValue()));
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
    if(env.containsKey(n)){
      return env.get(n).accept(this);
    }
    return new Variable(n.getName());
  }
  public SymbolicExpression visit(NamedConstant n){
    return new Variable(n.getName()); //Dodge
  }
}
