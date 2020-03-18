import entities.Category;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class Main {
    public static void main(String[] args) throws XMLStreamException, JAXBException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource("src/main/resources/products.xml");
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);
        xsr.nextTag();
        while(!xsr.getLocalName().equals("return")) {
            xsr.nextTag();
        }
        JAXBContext jc = JAXBContext.newInstance(Category.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<Category> jb = unmarshaller.unmarshal(xsr, Category.class);
        xsr.close();

        Category category = jb.getValue();
//        System.out.println(category.id);
//        System.out.println(category.firstName);
//        System.out.println(category.lastName);
    }
}