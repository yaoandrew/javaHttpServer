package server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyLoggerTest {

  @Test
  public void TakesLogAsStringInput() {
    MyLogger mylogger = new MyLogger();
    String requestString = "GET /log HTTP/1.1";

    mylogger.add(requestString);

    assertEquals(requestString, mylogger.get(0));
  }

  @Test
  public void CanReturnMultipleLinesOfInput() {
    MyLogger mylogger = new MyLogger();
    String requestStringOne = "GET /log HTTP/1.1";
    String requestStringTwo = "POST /log HTTP/1.1";
    String requestStringThree = "PUT /log HTTP/1.1";

    mylogger.add(requestStringOne);
    mylogger.add(requestStringTwo);
    mylogger.add(requestStringThree);

    assertEquals(
        requestStringOne + System.lineSeparator() + requestStringTwo + System.lineSeparator()
            + requestStringThree, mylogger.getLog());
  }

}
