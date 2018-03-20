package parsers;

import java.util.HashMap;

public class CliArgParser {

  private static String switchIndicator = "-";
  private static String dirSwitch = "-d";
  private static String portSwitch = "-p";

  private HashMap<String, String> argumentMap = new HashMap<>();

  public CliArgParser(String[] args) {
    parse(args);
  }

  private void parse(String[] arguments) {
    for (int i = 0; i < arguments.length - 1; i++) {
      if (arguments[i].startsWith(switchIndicator)) {
        argumentMap.put(arguments[i], arguments[i + 1]);
      }
    }
  }

  public boolean hasUserDirSet() {
    return argumentMap.containsKey(dirSwitch);
  }

  public boolean hasUserPortSet() {
    return argumentMap.containsKey(portSwitch);
  }

  public String getUserDir() {
    return argumentMap.get(dirSwitch);
  }

  public String getUserPort() {
    return argumentMap.get(portSwitch);
  }

  String getValue(String key) {
    return argumentMap.get(key);
  }
}
