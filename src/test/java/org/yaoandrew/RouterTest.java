package org.yaoandrew;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {

  @Test
  public void RouterReturnsCorrectHandlerForGoodRoute() {
    Request request = new Request("GET / HTTP/1.1\r\n");
    RequestHandler expected = new RootRequestHandler(new String []{"GET"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForBadRoute() {
    Request request = new Request("GET /foobar HTTP/1.1\r\n");
    RequestHandler expected = new BadRouteHandler();
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForForm() {
    Request request = new Request("GET /form HTTP/1.1\r\n");
    RequestHandler expected = new FormDataHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForOptions() {
    Request request = new Request("OPTIONS /method_options HTTP/1.1\r\n");
    RequestHandler expected = new OptionsRequestHandler(new String[]{"GET", "PUT", "POST"});
    Router router = new Router();

    assertEquals(expected.getClass(), router.getResponder(request).getClass());
  }
}
