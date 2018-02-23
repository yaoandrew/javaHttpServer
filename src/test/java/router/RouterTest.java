package router;

import java.io.IOException;
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
  public void RouterReturnsCorrectHandlerForGoodRoute() throws IOException {
    Request request = parser.parse("GET / HTTP/1.1\r\n");
    RequestHandler expected = new DirectoryHandler(tempFolder.newFolder());
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForBadRoute() {
    Request request = parser.parse("GET /foobar HTTP/1.1\r\n");
    RequestHandler expected = new BadRouteHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForForm() {
    Request request = parser.parse("GET /form HTTP/1.1\r\n");
    RequestHandler expected = new FormDataHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForOptions() {
    Request request = parser.parse("OPTIONS /method_options HTTP/1.1\r\n");
    RequestHandler expected = new OptionsRequestHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForCookies() {
    Request request = parser.parse("GET /cookie HTTP/1.1\r\n");
    RequestHandler expected = new CookieHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForParameterRequests() {
    Request request = parser.parse("GET /parameters?variable1=abc HTTP/1.1\r\n");
    RequestHandler expected = new ParameterHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForRedirect() {
    Request request = parser.parse("GET /redirect HTTP/1.1\r\n");
    RequestHandler expected = new RedirectHandler();
    Router router = new Router(serverDir);

    assertEquals(expected.getClass(), router.getHandler(request).getClass());
  }
}
