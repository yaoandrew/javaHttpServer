package servers;

import java.util.ArrayList;

public class MyLogger {

  ArrayList<String> log = new ArrayList<>();


  public void add(String logRecord) {
    log.add(logRecord);
  }

  String get(int index) {
    return log.get(index);
  }

  String getLog() {
    return String.join (System.lineSeparator(), log);
  }

  void clearLog() {
    log.clear();
  }

}
