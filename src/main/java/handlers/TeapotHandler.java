package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class TeapotHandler extends RequestHandler {

  private String teapot =
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
  private String angryPot =
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

    if (request.getSimpleUri().equals("/coffee")) {
      response.setStatusLine(HTTPStatus.IM_A_TEAPOT.getStatusLine());
      response.setBody(("I'm a teapot\r\n" + angryPot).getBytes());
    }

    if (request.getSimpleUri().equals("/tea")) {
      response.setStatusLine(HTTPStatus.OK.getStatusLine());
      response.setBody(teapot.getBytes());
    }

    return response;
  }

}
