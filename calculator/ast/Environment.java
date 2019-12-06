package org.ioopm.calculator.ast;

import java.util.HashMap;
import java.util.TreeSet;

/** Represents an environment containing only variables.
*/
public class Environment extends HashMap<Variable, SymbolicExpression> {



  // Should be implemented with an itterator later on maybe
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Variables: ");
    TreeSet<Variable> vars = new TreeSet<>(this.keySet());
    for (Variable v : vars) {
      sb.append(v.toString());
      sb.append(" = ");
      sb.append(this.get(v));
      sb.append(", ");
    }
    return sb.toString();
  }
}
