package org.ioopm.calculator.ast;

/** An exception to be used if an undefined expression is found, e.g. 3.2 = pi or 1/0.
*/
public class IllegalExpressionException extends RuntimeException {
    /**
     * Creates the exception
     *
     * @param msg The message to be passed upwards with the exception
     */
    public IllegalExpressionException(String msg) {
        super(msg);
    }
}
