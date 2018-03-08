package io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;

public class InputReaderTest {

  private String basicRequest = "GET /form HTTP/1.1";
  private String requestWithHeaders = "GET /form HTTP/1.1\r\nAccept: */*\r\nContent-Length: 3000\r\n\r\n";

  @Test
  public void InputReaderReadsBasicRequest() throws IOException {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(basicRequest.getBytes());
    InputReader reader = new InputReader(inputStream);

    assertEquals(reader.readLine(), basicRequest);
  }

}
