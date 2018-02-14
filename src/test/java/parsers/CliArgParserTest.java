package parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void CliArgParserReturnsDirSetting() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertTrue(cliArgParser.hasUserDirSet());
    }

    @Test
    public void CliArgParserReturnsPortSetting() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertTrue(cliArgParser.hasUserPortSet());
    }

    @Test
    public void CliArgParserReturnsFalseIfPortNotSet() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertFalse(cliArgParser.hasUserPortSet());
    }

    @Test
    public void CliArgParserReturnsFalseIfDirectoryNotSet() {
        String[] args = {"-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertFalse(cliArgParser.hasUserDirSet());
    }
}
