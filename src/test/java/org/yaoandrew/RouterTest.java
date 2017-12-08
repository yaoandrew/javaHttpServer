package org.yaoandrew;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouterTest {

  @Test
  public void RouterReturnsTrueIfValidRoute() {
    Request request = new Request ("GET / HTTP/1.1\r\n");
    Router router = new Router (request);

    assertTrue(router.isValidRoute(request.getResource()));
  }

  @Test
  public void RouterReturnsFalseIfRouteNotValid() {
    Request request = new Request ("GET /foobar HTTP/1.1\r\n");
    Router router = new Router (request);

    assertFalse(router.isValidRoute(request.getResource()));
  }

  @Test
  public void RouterReturnsTrueIfMethodValid() {
    Request request = new Request("GET /foobar HTTP/1.1\r\n");
    Router router = new Router(request);

    assertTrue(router.isValidMethod(request.getHttpMethod()));
  }

  @Test
  public void RouterReturnsFalseIfMethodNotValid() {
    Request request = new Request("FOO /foobar HTTP/1.1\r\n");
    Router router = new Router(request);

    assertFalse(router.isValidMethod(request.getHttpMethod()));
  }
}
