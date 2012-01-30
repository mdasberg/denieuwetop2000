package nl.jpoint.top2k.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by IntelliJ IDEA. User: jos
 */
public class SecurityUtil {

	public static String createSHAHash(final String toHash) {
		return new String(Hex.encodeHex(DigestUtils.sha(toHash)));
	}

}
