run: all
	java -cp bin/ org.ioopm.calculator.Calculator

all: ast parser calculator

test: ast
	javac -cp bin/ -d bin/ calculator/test/*.java
	java -cp bin/ org.ioopm.calculator.test.Test

calculator: ast parser
	javac -cp bin/ -d bin/ calculator/*.java

ast:
	javac -cp bin/ -d bin/ calculator/ast/*.java
parser:
	javac -cp bin/ -d bin/ calculator/parser/*.java

clean:
	rm -rf bin/

junit_ast_compile: ast
	javac -cp JUNIT/junit-4.13-rc-1.jar:bin/ -d bin/ TestAST.java

junit_ast_test:
	java -cp JUNIT/junit-4.13-rc-1.jar:JUNIT/hamcrest-core-1.3.jar:bin/ org.junit.runner.JUnitCore  TestAST

ast_test_clean:
	rm TestAST.class org/ioopm/calculator/ast/*.class

ast_test: junit_ast_compile junit_ast_test

clean:
	rm -rf bin/
