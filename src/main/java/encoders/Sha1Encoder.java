package encoders;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Encoder {

  public static String encode(byte[] data) {
    String hashFunction = "SHA-1";
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(hashFunction);
      byte[] hash = messageDigest.digest(data);
      StringBuffer stringBuffer = new StringBuffer();
      for (byte b : hash) {
        stringBuffer.append(String.format("%02x", b));
      }
      return stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Could not find encoding algorithm");
    }
    return "";
  }
}
