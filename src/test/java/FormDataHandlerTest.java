import org.junit.Test;
import static org.junit.Assert.assertEquals;

import handlers.FormDataHandler;

public class FormDataHandlerTest {

  FormDataHandler fdh = new FormDataHandler(new String[] {"PUT", "POST"});
  String expected = "HTTP/1.1 200 OK\r\n";
  String actual = fdh.getResponse().getStatusLine();

  @Test
  public void FormDataHandlerReturnsCorrectResponse(){
   assertEquals(expected, actual);
  }

}
