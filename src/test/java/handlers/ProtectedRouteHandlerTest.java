package handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import messages.HTTPStatus;
import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

public class ProtectedRouteHandlerTest {

  @Test
  public void ReturnsCorrectHandlerWhenNotAuthenticated() {
    String rawRequest = "GET /coffee HTTP/1.1";
    String realm = "Access to protected endpoint";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(rawRequest);
    TeapotHandler teapotHandler = new TeapotHandler();
    ProtectedRouteHandler protectedRouteHandler = new ProtectedRouteHandler(teapotHandler);

    assertEquals (HTTPStatus.UNAUTHORIZED.getStatusLine(), protectedRouteHandler.getResponse(request).getStatusLine());
    assertTrue (protectedRouteHandler.getResponse(request).getHeaders().contains("WWW-Authenticate: Basic realm=\"" + realm));
  }

  @Test
  public void ReturnsCorrectHandlerWhenRequestContainsAnyAuthenticationData() {
    String rawRequest = "GET /coffee HTTP/1.1\r\nAuthorization: Basic YWRtaW46aHVudGVyMg==\r\n";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(rawRequest);
    TeapotHandler teapotHandler = new TeapotHandler();
    ProtectedRouteHandler protectedRouteHandler = new ProtectedRouteHandler(teapotHandler);

    assertEquals(HTTPStatus.IM_A_TEAPOT.getStatusLine(), protectedRouteHandler.getResponse(request).getStatusLine());
  }
}
