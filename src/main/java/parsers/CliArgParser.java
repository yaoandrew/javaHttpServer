package parsers;

import java.util.HashMap;

public class CliArgParser {
    private static String switchIndicator = "-";
    HashMap<String, String> argumentMap = new HashMap<>();

    public CliArgParser(String[] args){
        parse(args);
    }

    private void parse(String[] arguments) {
        for (int i=0; i < arguments.length - 1; i++) {
          if (arguments[i].startsWith(switchIndicator)) {
            argumentMap.put (arguments[i], arguments[i+1]);
          }
        }
    }

    String getValue(String key){
        return argumentMap.get(key);
    }
}
