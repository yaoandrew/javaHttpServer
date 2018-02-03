package handlers;

import messages.Request;
import messages.Response;

public class TeapotHandler implements RequestHandler {
  String teapot =
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
  String angryPot =
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

  public Response getResponse(Request request) {
    Response response = new Response();

    if (request.getSimpleUri().equals("/coffee")) {
      response.setStatusLine("HTTP/1.1 418 I'm a teapot\r\n");
      response.setBody("I'm a teapot\r\n" + angryPot);
    }

    if (request.getSimpleUri().equals("/tea")) {
      response.setStatusLine("HTTP/1.1 200 OK\r\n");
      response.setBody(teapot);
    }

    return response;
  }

}
