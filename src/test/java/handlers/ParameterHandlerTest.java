package handlers;

import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

import static org.junit.Assert.assertTrue;


public class ParameterHandlerTest {
  String requestUri = "GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1";
  RequestParser parser = new RequestParser();
  Request parsedRequest = parser.parse(requestUri);
  ParameterHandler ph = new ParameterHandler(parsedRequest);

  String expectedBodyVar1 = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
  String expectedBodyVar2 = "variable_2 = stuff";

  @Test
  public void ParameterHandlerResponseContainsVariable1() {
    String actualBody = ph.getResponse().getBody();

    assertTrue(actualBody.contains(expectedBodyVar1));
  }

  @Test
  public void ParameterHandlerResponseContainsVariable2() {
    String actualBody = ph.getResponse().getBody();

    assertTrue(actualBody.contains(expectedBodyVar2));
  }
}