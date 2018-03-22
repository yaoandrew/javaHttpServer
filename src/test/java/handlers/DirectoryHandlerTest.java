package handlers;

import static org.hamcrest.CoreMatchers.containsString;
import java.io.IOException;
import messages.Request;
import messages.Response;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class DirectoryHandlerTest {

  private String rawRequest = "GET /hello.txt HTTP/1.1\r\n";
  private RequestParser parser = new RequestParser();
  private Request request = parser.parse(rawRequest);

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void HasFileInDirectoryList() throws IOException {

    tempFolder.newFile("hello.txt");
    String fileNameInDir= "<a href=\"/hello.txt\">hello.txt";

    DirectoryHandler directoryHandler = new DirectoryHandler(tempFolder.getRoot());
    Response response = directoryHandler.getResponse(request);

    Assert.assertThat(new String(response.getBody()), containsString(fileNameInDir));
  }
}
