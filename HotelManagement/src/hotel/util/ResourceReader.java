package hotel.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceReader {
	
	private static final Log log = LogFactory.getLog(ResourceReader.class);
	
	private static Map cache = new HashMap();

	public static Image getImageFromJar(String sPath, Class clazz) {
		Image image = (Image) cache.get(sPath);
		if (image == null) {
			InputStream inputstream = clazz.getClassLoader().getResourceAsStream(sPath);
			log.debug(inputstream);
			if (inputstream != null) {
				ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
				try {
					byte abyte0[] = new byte[1024];
					for (int i = 0; (i = inputstream.read(abyte0)) >= 0;) {
						bytearrayoutputstream.write(abyte0, 0, i);
					}

					image = Toolkit.getDefaultToolkit().createImage(
							bytearrayoutputstream.toByteArray());
				} catch (IOException ioexception) {
					throw new RuntimeException(ioexception);
				}
			}
			cache.put(sPath, image);
			return image;
		}
		return image;
	}

}
