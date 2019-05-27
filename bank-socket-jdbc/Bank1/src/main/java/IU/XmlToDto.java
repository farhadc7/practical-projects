package IU;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlToDto {
    private List<FileTransmitter> listOfDtos=new ArrayList<FileTransmitter>();

    public List<FileTransmitter> readTransaction(String path) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile=new File(path);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc=builder.parse(xmlFile);
        doc.normalizeDocument();
        NodeList nd=doc.getElementsByTagName("transaction");
        for(int i=0; i<nd.getLength(); i++){
            FileTransmitter fileTransmitter =new FileTransmitter();
            Element e=(Element)nd.item(i);
            fileTransmitter.setId(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()));
            fileTransmitter.setAmount(Long.parseLong(e.getElementsByTagName("amount").item(0).getTextContent()));
            fileTransmitter.setAccountId(Integer.parseInt(e.getElementsByTagName("accountId").item(0).getTextContent()));
            listOfDtos.add(fileTransmitter);
        }
        return listOfDtos;
    }
    public XmlConfig readConfig(String config) throws ParserConfigurationException, IOException, SAXException {
        XmlConfig xmlObj=new XmlConfig();
        File xmlFile=new File(config);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc=builder.parse(xmlFile);
        doc.normalizeDocument();
        NodeList nd=doc.getElementsByTagName("configuration");
        Element e=(Element)nd.item(0);
        xmlObj.setServerIp((e.getElementsByTagName("serverIp").item(0).getTextContent()));
        xmlObj.setServerPort(Integer.parseInt(e.getElementsByTagName("serverPort").item(0).getTextContent()));
        xmlObj.setName((e.getElementsByTagName("clientName").item(0).getTextContent()));
        xmlObj.setServerToken(Long.parseLong(e.getElementsByTagName("serverToken").item(0).getTextContent()));
        return xmlObj;

    }

}
