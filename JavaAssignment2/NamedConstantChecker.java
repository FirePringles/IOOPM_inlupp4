public class NamedConstantChecker implements Visitor {
  public SymbolicExpression visit(Addition a){
    a.getLHS().accept(this);
    a.getRHS().accept(this);
    return this;
  }
  public SymbolicExpression visit(Assignment a){
    a.getRHS().accept(this);
    if(a.getRHS().isNamedConstant()){

    }
    return this;
  }
}
