package org.yaoandrew;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouterTest {

  @Test
  public void RouterReturnsTrueIfValidRoute() {
    Request request = new Request ("GET / HTTP/1.1\r\n");
    Router router = new Router (request);

    assertTrue(router.isValidRoute());
  }

  @Test
  public void RouterReturnsFalseIfRouteNotValid() {
    Request request = new Request ("GET /foobar HTTP/1.1\r\n");
    Router router = new Router (request);

    assertFalse(router.isValidRoute());
  }

  @Test
  public void RouterReturnsTrueIfMethodValid() {
    Request request = new Request("GET /foobar HTTP/1.1\r\n");
    Router router = new Router(request);

    assertTrue(router.isValidMethod());
  }

  @Test
  public void RouterReturnsFalseIfMethodNotValid() {
    Request request = new Request("FOO /foobar HTTP/1.1\r\n");
    Router router = new Router(request);

    assertFalse(router.isValidMethod());
  }

  @Test
  public void RouterReturnsCorrectHandlerForGet() {
    Request request = new Request("GET /foobar HTTP/1.1\r\n");
    RequestHandler expected = new GetRequestHandler(true);
    Router router = new Router(request);

    assertEquals(expected.getClass(), router.getResponder().getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForPost() {
    Request request = new Request("POST /foobar HTTP/1.1\r\n");
    RequestHandler expected = new PostRequestHandler();
    Router router = new Router(request);

    assertEquals(expected.getClass(), router.getResponder().getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForPut() {
    Request request = new Request("PUT /foobar HTTP/1.1\r\n");
    RequestHandler expected = new PutRequestHandler();
    Router router = new Router(request);

    assertEquals(expected.getClass(), router.getResponder().getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForHead() {
    Request request = new Request("HEAD /foobar HTTP/1.1\r\n");
    RequestHandler expected = new HeadRequestHandler(true);
    Router router = new Router(request);

    assertEquals(expected.getClass(), router.getResponder().getClass());
  }

  @Test
  public void RouterReturnsCorrectHandlerForOptions() {
    Request request = new Request("OPTIONS / HTTP/1.1\r\n");
    RequestHandler expected = new OptionsRequestHandler(request.getResource());
    Router router = new Router(request);

    assertEquals(expected.getClass(), router.getResponder().getClass());
  }
}
