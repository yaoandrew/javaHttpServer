package io;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Ignore;
import org.junit.Test;

public class InputReaderTest {

  private String basicRequest = "GET /form HTTP/1.1";
  private String requestWithHeaders = "GET /form HTTP/1.1\r\nAccept: */*\r\nContent-Length: 3000\r\n\r\n";
  private InputReader testInputReader = new InputReader();


  @Test
  public void InputReaderReadsBasicRequest() throws IOException {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(basicRequest.getBytes());
    InputStreamReader isr = new InputStreamReader(inputStream);
    BufferedReader br = new BufferedReader(isr);
    testInputReader.reader = br;

    assertEquals(testInputReader.reader.readLine() , basicRequest);
  }

  @Ignore
  public void InputReaderReadsRequestWithHeaders() throws IOException {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(requestWithHeaders.getBytes());
    InputStreamReader isr = new InputStreamReader(inputStream);
    BufferedReader br = new BufferedReader(isr);
    testInputReader.reader = br;

    assertEquals(testInputReader.reader.readLine() , requestWithHeaders);
  }



}
