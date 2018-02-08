package parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CliArgParserTest {

    @Test
    public void CliArgParserReturnsPublicDirLocation() {
        String[] args = {"-p", "5000", "-d",
            "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals ("/Users/andrew/Documents/code/8thlight/cob_spec/public",
            cliArgParser.getValue("-d"));
    }

    @Test
    public void CliArgParserReturnsPort() {
        String[] args = {"-p", "5000", "-d",
            "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals("5000", cliArgParser.getValue("-p"));
    }

    @Test
    public void CliArgParserDoesntCareAboutArgOrder() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals("5000", cliArgParser.getValue("-p"));
    }
}
