package encoders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Sha1EncoderTest {

    @Test
    public void ReturnsCorrectSha1Value() {
        byte[] dataToBeEncoded = "default content".getBytes();
        String expectedCode = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";

        assertEquals(Sha1Encoder.encode(dataToBeEncoded), expectedCode);
    }
}
