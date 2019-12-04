package org.ioopm.calculator.parser;

import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.StringReader;
import org.ioopm.calculator.ast.*;

public class CalculatorParser {

    private StreamTokenizer st;

    public CalculatorParser() {
        
    }
    private boolean isCommand(String word) {
        switch(word) {
        case "quit": return true;
        case "vars": return true;
        case "clear": return true;
        default: return false;
        }
    }
    private boolean isUnary(String word) {
        switch(word) {
        case "exp": return true;
        case "log": return true;
        case "sin": return true;
        case "cos": return true;
        default: return false;
        }
    }
    /**
     * Creates StreamTokenizer of a input string and then send that to the rest of the parser
     * 
     * @param input the string which we want the parser to pars
     * @return SymbolicExpression the result of the parsed input string
     */
    public SymbolicExpression parse(String input) throws IOException {
        this.st = new StreamTokenizer(new StringReader(input));
        this.st.ordinaryChar('-');
        this.st.ordinaryChar('/');
        this.st.eolIsSignificant(true);
        return top_level();
    }
    

     /**
     * Check if current object is either a command or a expression.
     * 
     * @return SymbolicExpression the result which is either a expression or a command
     */
    private SymbolicExpression top_level() throws IOException {
        SymbolicExpression result;
        if(this.st.nextToken() == this.st.TT_WORD && isCommand(this.st.sval)) {
            this.st.pushBack();
            result = command();
        } else {
            this.st.pushBack();
            result = assignment();
        }        
        
        if((this.st.nextToken() != this.st.TT_EOL)) {
            this.st.pushBack();
            throw new SyntaxErrorException("Expected newline but got " + this.st.nextToken());
        }
        return result;
    }
    
    
     /**
     * Check if object after current object is a '=' and if it is then create new Assignment 
     *
     * @return SymbolicExpression the result which can be new Assignment
     */
    public SymbolicExpression assignment() throws IOException {
        SymbolicExpression lhs = expression();
        SymbolicExpression rhs = null;
        
        while(this.st.nextToken() == '=') {
            rhs = identifier();
            lhs = new Assignment(lhs, rhs);
        }
        this.st.pushBack();
        return lhs;
    }
    
     /**
     * Check if current object is a command. If it is then perform approriate method for that command
     * else throw IOException
     * 
     * @return Command the result command
     */

    public Command command() throws IOException {
        if(this.st.nextToken() == this.st.TT_WORD) {
            String r = this.st.sval;

            switch(r) {
            case "quit" : return Quit.instance();
            case "vars" : return Vars.instance();
            case "clear" : return Clear.instance();
            default : this.st.pushBack(); throw new SyntaxErrorException("Invalid command");
            }
        }
        this.st.pushBack();
        throw new SyntaxErrorException("Expected command");
    }

     /**
     * Check if a string is a valid identifer from a list of names that is not valid. 
     * 
     * @param s the string to check
     * @return boolean true if string is valid, else false
     */
    private boolean isValidIdentifier(String s) {
        if(isCommand(s) || isUnary(s)) {
            return false;
        }
        return true;
    }
    

     /**
     * Check is current object is a word and if it is valid. If it is then check if it is a namedConstant or a new variable
     * 
     * @return Variable the variable which is either a new variable or a aleady existing namedConstant
     */
    public SymbolicExpression identifier() throws IOException {
        if(this.st.nextToken() == this.st.TT_WORD && isValidIdentifier(this.st.sval)) {
            if(Constants.isNamedConstant(this.st.sval)) {
                return new NamedConstant(this.st.sval, Constants.getValue(this.st.sval));
            }
            return new Variable(this.st.sval);
        }
        throw new SyntaxErrorException("Invalid identifier");
    }
    
     /**
     * Sends current object to term method and then checks the next object.
     * If object is either '+' or '-' then send third object to term method and then use appropriate mathematical method
     * 
     * @return SymbolicExpression the result
     */
    public SymbolicExpression expression() throws IOException {
        SymbolicExpression result = term();
        while (st.ttype == '+' || st.ttype == '-') {
            int operation = st.ttype;
            if (operation == '+') {
                this.st.nextToken();
                result = new Addition(result, term());
            } else {
                this.st.nextToken();
                result = new Subtraction(result, term());
            }
        }
        st.pushBack();
        return result;
    }

     /**
     * Sends current object to factor mathod and then checks if the next object is either * or /.
     * If it is then use apporopriate mathimatical method and send the third object to factor method.
     * 
     * 
     * @return SymbolicExpression the result
     * 
     */
    /// This method works like expression, but with factors and * instead of terms and +/-
    private SymbolicExpression term() throws IOException {
        SymbolicExpression result = factor();
        this.st.nextToken();
        while (this.st.ttype == '*' || this.st.ttype == '/') {
            int operation = this.st.ttype;
            if(operation == '*') {
                result = new Multiplication(result, factor());
            } else {
                result = new Division(result, factor());
            }
            this.st.nextToken();
        }
        st.pushBack();
        return result;
    }
    

     /**
     * Check what object StreamTokenizer is standing on. Then use apporpriate method on that object.
     * 
     * @return SymbolicExpression the result of the method used on the object.
     * @see number
     * @see identifier
     * @see unary
     */
    private SymbolicExpression factor() throws IOException {
        SymbolicExpression result;
        /// If we encounter a (, we know we are reading a full expression, so we call back up 
        /// to that method, and then try to read a closing ) at the end 
        if (this.st.nextToken() == '(') {
            result = assignment();
            /// This captures unbalanced parentheses!
            if (this.st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
        }
	else if(this.st.ttype == '{'){
	    result = assignment();
	    if(this.st.nextToken() != '}'){
		throw new SyntaxErrorException("expected '}'");
	    } else {
		return new Scope(result);
	    }
	} else {
            int token = this.st.ttype;
            switch(token) {
                //TT_NUMBER
            case -2: this.st.pushBack(); result = number(); break;
                //NEGATION
            case -45: this.st.pushBack(); result = unary(); break;
                //TT_WORD
            default: this.st.pushBack(); try {result = unary();}
                catch(SyntaxErrorException e) {result = identifier();} break;
            }
            
        }
        return result;
    }
    
     /**
     * Check if StreamTokenizer is standing on a word. If check what unary class the word represents
     * else throw exception
     *
     * @return Unary the result of subclass of unarymethod
     */

    private Unary unary() throws IOException {
        if(this.st.nextToken() != this.st.TT_WORD) {
            if(this.st.ttype == '-') {
                return new Negation(factor());
            }
            throw new SyntaxErrorException("Expected unary operation");
        }
        
        Unary result = null;
        String functionName = this.st.sval;
        switch(functionName) {
        case "exp": result = new Exp(factor()); break;
        case "log": result = new Log(factor()); break;
        case "sin": result = new Sin(factor()); break;
        case "cos": result = new Cos(factor()); break;
        }
        if(result == null) {
            this.st.pushBack();
            throw new SyntaxErrorException("Invalid unary operation");
        }
        return result;
    }

     /**
     * Check if StreamTokenizer is standing on a number. If it is return that number as constant else throw exception
     *  
     * @return Constant the number 
     */
    private Constant number() throws IOException {
        if (this.st.nextToken() == this.st.TT_NUMBER) {
            return new Constant(this.st.nval);
        } else {
            throw new SyntaxErrorException("Expected number");
        }
    }
}
