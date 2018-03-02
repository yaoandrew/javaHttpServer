package servers;

import java.util.ArrayList;

public class MyLogger {

  private static MyLogger instance;
  private ArrayList<String> log = new ArrayList<>();

  protected MyLogger(){

  }

  public static MyLogger getInstance() {
    if(instance == null) {
      instance = new MyLogger();
    }
    return instance;
  }

  public void add(String logRecord) {
    log.add(logRecord);
  }

  public String get(int index) {
    return log.get(index);
  }

  public String getLog() {
    return String.join (System.lineSeparator(), log);
  }

  public void clearLog() {
    log.clear();
  }

}