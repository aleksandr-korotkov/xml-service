import entities.RootElement;
import services.JsonServiceImpl;
import services.XmlService;
import services.XmlServiceImpl;

public class Main {
    public static void main(String[] args){
        XmlService service = new XmlServiceImpl();
        RootElement rootElementFromXml = service.convertXmlToObject().orElseThrow(NullPointerException::new);
        service.printProduct(rootElementFromXml);

        JsonServiceImpl convertService = new JsonServiceImpl();
        convertService.convertObjectToJSon(rootElementFromXml);
        RootElement rootElementFromJson = convertService.convertJsonToObject().orElseThrow(NullPointerException::new);

        System.out.println(rootElementFromXml.equals(rootElementFromJson));

        service.convertObjectToXml(rootElementFromJson, "new.xml");
    }
}