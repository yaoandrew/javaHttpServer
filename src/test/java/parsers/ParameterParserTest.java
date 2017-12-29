package parsers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParameterParserTest {

  String requestUri = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

  @Test
  public void ParamParserReturnsRawParamsFromUri() {
    String expected = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
    String actual = ParameterParser.parseUri(requestUri);

    assertTrue(actual.equals(expected));
  }

  @Test
  public void ParamParserReturnsParam1() {
    String rawParams = ParameterParser.parseUri(requestUri);

    String expected = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
    String actual = ParameterParser.parseRawParams(rawParams)[0];

    assertTrue(actual.equals(expected));
  }

  @Test
  public void ParamParserReturnsParam2() {
    String rawParams = ParameterParser.parseUri(requestUri);

    String expected = "variable_2=stuff";
    String actual = ParameterParser.parseRawParams(rawParams)[1];

    assertTrue(actual.equals(expected));
  }
}
