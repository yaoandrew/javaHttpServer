package handlers;

import messages.Request;
import messages.Response;
import java.io.File;

public class DirectoryHandler implements RequestHandler {

    private File file;

    public DirectoryHandler (File file) {
        this.file = file;
    }

    public Response getResponse(Request request) {
        Response response = new Response();
        StringBuffer bodyContents = new StringBuffer();

        response.setStatusLine("HTTP/1.1 200 OK");
        response.setHeaders("Content-type: text/html; charset=utf-8");

        for (String item : file.list()){
            bodyContents.append(item + "\r\n");
        }

        response.setBody(bodyContents.toString().getBytes());
        return response;
    }

}

/*

> GET / HTTP/1.1
> Host: localhost:8000
> User-Agent: curl/7.54.0
> Accept:
        >
        * HTTP 1.0, assume close after body
< HTTP/1.0 200 OK
< Server: SimpleHTTP/0.6 Python/2.7.10
< Date: Mon, 12 Feb 2018 23:02:36 GMT
< Content-type: text/html; charset=utf-8
< Content-Length: 512
<
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN"><html>
<title>Directory listing for /</title>
<body>
<h2>Directory listing for /</h2>
<hr>
<ul>
<li><a href="file1">file1</a>
<li><a href="file2">file2</a>
<li><a href="image.gif">image.gif</a>
<li><a href="image.jpeg">image.jpeg</a>
<li><a href="image.png">image.png</a>
<li><a href="partial_content.txt">partial_content.txt</a>
<li><a href="patch-content.txt">patch-content.txt</a>
<li><a href="text-file.txt">text-file.txt</a>
</ul>
<hr>
</body>
</html>

 */