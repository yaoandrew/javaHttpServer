package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class InputReader {

  private InputStream inputStream;

  public InputReader (InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public String readLine() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    return bufferedReader.readLine();
  }

}
