package org.ioopm.calculator.ast;

import java.util.HashMap;

/** Represents an environment containing only variables.
*/
public class Environment extends HashMap<Variable, SymbolicExpression> {

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Variables:\n");
    TreeSet<Variable> vars = new TreeSet<>(this.keySet());
    for (Variable v: vars) {
        sb.append(v.getName());
        sb.append(" = ");
        sb.append(this.get(v));
        sb.append("\n");
    }
    return sb.toString();
  }

}
