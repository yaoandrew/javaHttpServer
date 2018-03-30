package router;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import messages.Request;
import handlers.*;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class RouterTest {

  RequestParser parser = new RequestParser();
  String serverDir = "/tmp";

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void ReturnsCorrectHandlerForGoodRoute() throws IOException {
    Request request = parser.parse("GET / HTTP/1.1\r\n");
    RequestHandler expected = new FileSystemHandler(serverDir, request);
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void ReturnsCorrectHandlerForForm() {
    Request request = parser.parse("GET /form HTTP/1.1\r\n");
    RequestHandler expected = new FormDataHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void ReturnsCorrectHandlerForOptions() {
    Request request = parser.parse("OPTIONS /method_options HTTP/1.1\r\n");
    RequestHandler expected = new OptionsRequestHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void ReturnsCorrectHandlerForCookies() {
    Request request = parser.parse("GET /cookie HTTP/1.1\r\n");
    RequestHandler expected = new CookieHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void ReturnsCorrectHandlerForParameterRequests() {
    Request request = parser.parse("GET /parameters?variable1=abc HTTP/1.1\r\n");
    RequestHandler expected = new ParameterHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void ReturnsCorrectHandlerForRedirect() {
    Request request = parser.parse("GET /redirect HTTP/1.1\r\n");
    RequestHandler expected = new RedirectHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }
}
