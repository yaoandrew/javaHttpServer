package router;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import messages.Request;
import handlers.*;
import parsers.RequestParser;

public class RouterTest {

  RequestParser parser = new RequestParser();

  @Test
  public void RouterReturnsCorrectHandlerForGoodRoute() {
    Request request = parser.parse("GET / HTTP/1.1\r\n");
    RequestHandler expected = new RootRequestHandler(new String []{"GET"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForBadRoute() {
    Request request = parser.parse("GET /foobar HTTP/1.1\r\n");
    RequestHandler expected = new BadRouteHandler();
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForForm() {
    Request request = parser.parse("GET /form HTTP/1.1\r\n");
    RequestHandler expected = new FormDataHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForOptions() {
    Request request = parser.parse("OPTIONS /method_options HTTP/1.1\r\n");
    RequestHandler expected = new OptionsRequestHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForCookies() {
    Request request = parser.parse("GET /cookie HTTP/1.1\r\n");
    RequestHandler expected = new CookieHandler(request);
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForParameterRequests() {
    Request request = parser.parse("GET /parameters?variable1=abc HTTP/1.1\r\n");
    RequestHandler expected = new ParameterHandler(request);
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }
}
