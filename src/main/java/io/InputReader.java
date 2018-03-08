package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputReader {

  private InputStream inputStream;
  private BufferedReader bufferedReader;

  public InputReader (InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public void setupReader() {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    bufferedReader = new BufferedReader(inputStreamReader);
  }

  public String readLine() throws IOException {
    return bufferedReader.readLine();
  }

  public String readFullRequest() throws IOException {
    String rawRequest = "";
    rawRequest += readLine();
    rawRequest += "\r\n";
    while (bufferedReader.ready()){
      rawRequest += (char) bufferedReader.read();
    }

    return rawRequest;
  }

  public void close() throws IOException {
    bufferedReader.close();
  }

}
