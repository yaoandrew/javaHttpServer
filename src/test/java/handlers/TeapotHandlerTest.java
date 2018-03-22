package handlers;

import static junit.framework.TestCase.assertEquals;

import messages.Response;
import org.junit.Test;
import parsers.RequestParser;

public class TeapotHandlerTest {

  private RequestParser parser = new RequestParser();
  private static final String TEAPOT =
      "                       (\n"
          + "            _           ) )\n"
          + "         _,(_)._        ((\n"
          + "    ___,(_______).        )\n"
          + "  ,'__.   /       \\    /\\_\n"
          + " /,' /  |\"\"|       \\  /  /\n"
          + "| | |   |__|       |,'  /\n"
          + " \\`.|                  /\n"
          + "  `. :           :    /\n"
          + "    `.            :.,'\n"
          + "      `-.________,-'";
  private static final String ANGRY_POT =
      "                              $ZZZ+\n"
          + "                              ZZZZI\n"
          + "                              IZZZ:  .\n"
          + "           :          =OZZZZZZZZZZZZZZOZ:\n"
          + "        ~ZZZZZ     . ZZZZZZZZZZZZZZZZZZZZZ?\n"
          + "        ?ZZZZZZ     OZZZZZZZZZZZZZZZZZZZZZZZ$\n"
          + "        ~ZZZZZZ~    ZZZZZZZZZZZZZZZZZZZZZZZZZZZ~\n"
          + "           OZZZ7   777777ZZZZZZZZZZZZZZZZZZZZZO$I:.\n"
          + "          . ZZZZ  ZZZZ777777777777777777777777777ZZZZZ\n"
          + "            ZZZZ  ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ?\n"
          + "            =ZZZZ ZZZNMMDZZZZZZZZZZZZZZZZZZZZZZZZOZZZZZZ~\n"
          + "             OZZZZZZZZNMMMZZZZZZZOMMZZZZZZZZZZZZ$   ZZZZZ\n"
          + "              ZZZZZZZZNMNMNZZZZZDMMNZZZZZZZZZZZZ      ?ZZO\n"
          + "                ZZZZZZZZZNDZZZMMMNZZZZZZZZZZZZZO     .IZZZ\n"
          + "                  ZZZZZZZZZZZZZZNZZZZZZZZZZZZZZ=.  :$ZZZZZ~\n"
          + "                  ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ~\n"
          + "                  ZZZZZZZNMMMMMNZZZZZZZZZZZZZZZZZZZZZZZZZZ\n"
          + "                  ~ZZZZZMMMMMMMMZZZZZZZZZZZZZZZZZOZZOI :\n"
          + "                    ZZZZMMMMMMMMZZZZZZZZZZZZ$\n"
          + "                     OZZZMMMMMMMZZZZZZZZZZZ=\n"
          + "                       $ZZZZZZZZZZZZZZZZZI\n"
          + "                          :7ZZZZZZZZZZ$~\n";

  @Test
  public void TeapotHandlerReturnsStatus418() {
    String coffeeRequestString = "GET /coffee HTTP/1.1";
    RequestHandler tph = new TeapotHandler();
    String expectedStatus = "HTTP/1.1 418 I'm a Teapot";

    Response response = tph.getResponse(parser.parse(coffeeRequestString));

    assertEquals(expectedStatus, response.getStatusLine());
  }

  @Test
  public void TeapotHandlerReturnsCorrectBodyContent() {
    String coffeeRequestString = "GET /coffee HTTP/1.1";
    RequestHandler tph = new TeapotHandler();

    Response response = tph.getResponse(parser.parse(coffeeRequestString));

    assertEquals("I'm a teapot\r\n" + ANGRY_POT, new String (response.getBody()));
  }

  @Test
  public void TeapotHandlerReturnsCorrectStatusForTea() {
    String teaRequestString = "GET /tea HTTP/1.1";
    RequestHandler tph = new TeapotHandler();
    String expectedStatus = "HTTP/1.1 200 OK";

    Response response = tph.getResponse(parser.parse(teaRequestString));

    assertEquals(expectedStatus, response.getStatusLine());
  }

  @Test
  public void TeapotHandlerReturnsCorrectBodyContentForTea() {
    String teaRequestString = "GET /tea HTTP/1.1";
    RequestHandler tph = new TeapotHandler();

    Response response = tph.getResponse(parser.parse(teaRequestString));

    assertEquals(TEAPOT, new String (response.getBody()));
  }

}
