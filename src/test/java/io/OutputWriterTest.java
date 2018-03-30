package io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import messages.HTTPStatus;
import messages.Response;
import org.junit.Test;

public class OutputWriterTest {

  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
  Response response = new Response();

  @Test
  public void WritesResponseWithBodyData() throws IOException {
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setBody("Hello".getBytes());
    OutputWriter outputWriter = new OutputWriter(byteArrayOutputStream);
    outputWriter.write(response);

    assertEquals("HTTP/1.1 200 OK\n\n\nHello", byteArrayOutputStream.toString());
  }

  @Test
  public void WritesResponseWithHeader() throws IOException {
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders("content-type: text");
    OutputWriter outputWriter = new OutputWriter(byteArrayOutputStream);
    outputWriter.write(response);

    assertEquals("HTTP/1.1 200 OK\ncontent-type: text\n\n", byteArrayOutputStream.toString());
  }
}
