package io;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Test;

public class InputReaderTest {

  private String basicRequest = "GET /form HTTP/1.1";
  private String requestWithHeaders = "GET /form HTTP/1.1\r\nAccept: */*\r\nContent-Length: 3000\r\n\r\n";

  @Test
  public void ReadsBasicRequest() throws IOException {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(basicRequest.getBytes());
    InputReader reader = new InputReader(inputStream);

    reader.setupReader();

    assertEquals(reader.readLine(), basicRequest);
  }

  @Test
  public void ReadsMultiLineRequest() throws IOException {

    ByteArrayInputStream inputStream = new ByteArrayInputStream(requestWithHeaders.getBytes());
    InputReader reader = new InputReader(inputStream);

    reader.setupReader();

    assertThat(reader.readFullRequest(), equalTo(requestWithHeaders));
  }

}