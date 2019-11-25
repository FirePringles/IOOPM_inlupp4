package org.ioopm.calculator.ast;

import java.util.HashMap;

/** Represents an environment containing only variables.
*/
public class Environment extends HashMap<Variable, SymbolicExpression> {}
