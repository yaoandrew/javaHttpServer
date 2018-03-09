package io;

import java.io.IOException;
import java.io.OutputStream;

public class OutputWriter {

  private OutputStream outputStream;

  public OutputWriter(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void write(byte[] data) throws IOException {
    outputStream.write(data);
  }

  public void close() throws IOException {
    outputStream.close();
  }

}
