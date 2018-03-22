package parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CliArgParserTest {

    @Test
    public void ReturnsPublicDirLocation() {
        String[] args = {"-p", "5000", "-d",
            "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals ("/Users/andrew/Documents/code/8thlight/cob_spec/public",
            cliArgParser.getValue("-d"));
    }

    @Test
    public void ReturnsPort() {
        String[] args = {"-p", "5000", "-d",
            "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals("5000", cliArgParser.getValue("-p"));
    }

    @Test
    public void DoesntCareAboutArgOrder() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertEquals("5000", cliArgParser.getValue("-p"));
    }

    @Test
    public void ReturnsDirSetting() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertTrue(cliArgParser.hasUserDirSet());
    }

    @Test
    public void ReturnsPortSetting() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public",
            "-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertTrue(cliArgParser.hasUserPortSet());
    }

    @Test
    public void ReturnsFalseIfPortNotSet() {
        String[] args = {"-d", "/Users/andrew/Documents/code/8thlight/cob_spec/public"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertFalse(cliArgParser.hasUserPortSet());
    }

    @Test
    public void ReturnsFalseIfDirectoryNotSet() {
        String[] args = {"-p", "5000"};

        CliArgParser cliArgParser = new CliArgParser(args);

        assertFalse(cliArgParser.hasUserDirSet());
    }
}
