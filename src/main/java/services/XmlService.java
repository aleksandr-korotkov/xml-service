package services;

import entities.RootElement;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Optional;

public interface XmlService {

    Optional<RootElement> convertXmlToObject();

    void convertObjectToXml(RootElement element, String xmlFileName);

    void printProduct(RootElement element);
}
