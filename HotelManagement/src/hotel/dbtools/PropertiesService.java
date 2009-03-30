package hotel.dbtools;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *  Ù–‘π§æﬂ
 * 
 * @author qy
 * 
 */
public class PropertiesService {

	private static Element properties;

	static {
		try {
			loadPropertyFile();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getProperty(String tagName, String attributeName) {
		NodeList nl = properties.getElementsByTagName(tagName);
		Element ele = (Element) nl.item(0);
		return ele.getAttribute(attributeName);
	}

	private static void loadPropertyFile() throws SAXException, IOException,
			ParserConfigurationException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream("./properties.xml");
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(is);
		properties = doc.getDocumentElement();
	}

	public static void main(String[] args) {
		String str = getProperty("Database", "SQLStatement");
		System.out.println(str);
	}

}
