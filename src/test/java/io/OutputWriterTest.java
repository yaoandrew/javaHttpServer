package io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Test;

public class OutputWriterTest {

  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

  @Test
  public void WriterTakesOutputStream() throws IOException {
    OutputWriter outputWriter = new OutputWriter(byteArrayOutputStream);
    outputWriter.write("Hello World".getBytes());

    assertEquals("Hello World", byteArrayOutputStream.toString());

  }
}