package services;

import entities.RootElement;

import javax.xml.bind.*;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XmlServiceImpl implements XmlService {

    private static final String XML_SOURCE = "src/main/resources/";
    private static final String PRODUCTS_XML_FILE_NAME = "products.xml";
    private static final String XML_ROOT_ELEMENT = "products";

    public Optional<RootElement> convertXmlToObject() {
        try {
            StreamSource xml = new StreamSource(XML_SOURCE + PRODUCTS_XML_FILE_NAME);
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader = xif.createXMLStreamReader(xml);
            xmlStreamReader.nextTag();
            while (!xmlStreamReader.getLocalName().equals(XML_ROOT_ELEMENT)) {
                xmlStreamReader.nextTag();
            }
            JAXBContext jc = JAXBContext.newInstance(RootElement.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<RootElement> jb = unmarshaller.unmarshal(xmlStreamReader, RootElement.class);
            xmlStreamReader.close();
            return Optional.of(jb.getValue());
        }
        catch (XMLStreamException e){
            e.printStackTrace();
        }
        catch (JAXBException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void printProduct(RootElement element){
        System.out.println(element.toString());
    }

    @Override
    public void convertObjectToXml(RootElement rootElement, String xmlFileName){
        try {
            File file = new File(XML_SOURCE + xmlFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(RootElement.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(rootElement, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

