import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4JTest {
    @Test
    public void test1() throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/books.xml");
        System.out.println(document);

        Element rootElement = document.getRootElement();
        System.out.println(rootElement);

        List<Element> books = rootElement.elements();
        for (Element item : books) {
            System.out.println(item.asXML());
        }
    }
}
