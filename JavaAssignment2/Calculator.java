public class Calculator{

  private static Environment env;

  private static void command(Command command) {
      if(command == Quit.instance()) {
          System.out.printf("Commands executed: %s\nCommands successfully evaluated: %s\nFully evaluated commands: %s\n", Calculator.commands, Calculator.successfulCommands, Calculator.fullyEvaluatedCommands);
          System.exit(0);
      } else if(command == Vars.instance()) {
          System.out.println(env);
      } else if(command == Clear.instance()) {
          Calculator.env.clear();
      } else {
          throw new RuntimeException("Invalid command");
      }
  }
}
