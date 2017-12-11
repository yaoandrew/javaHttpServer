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
    Router router = new Router(request);

    assertEquals("handleGet", router.getResponder());
  }

  @Test
  public void RouterReturnsCorrectHandlerForOptions() {
    Request request = new Request("OPTIONS / HTTP/1.1\r\n");
    Router router = new Router(request);

    assertEquals("handleOptions", router.getResponder());
  }
}