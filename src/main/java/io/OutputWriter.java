package io;

import messages.Response;

import java.io.IOException;
import java.io.OutputStream;

public class OutputWriter {

  private OutputStream outputStream;

  public OutputWriter(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public void write(Response response) throws IOException {
    outputStream.write(response.getStatusLine().getBytes());
    outputStream.write(System.lineSeparator().getBytes());

    if (responseHasHeaders(response)) {
        outputStream.write(response.getHeaders().getBytes());
    }

    outputStream.write(response.getSeparator().getBytes());

    if (responseHasDataInBody(response)) {
        outputStream.write(response.getBody());
    }

  }

  public void close() throws IOException {
    outputStream.close();
  }

  private boolean responseHasHeaders(Response response) {
    return response.getHeaders().length() > 1;
  }

  private boolean responseHasDataInBody(Response response) {
    return response.getBody() != null;
  }
}
