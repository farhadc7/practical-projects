package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Optional;

public class XmlRepository {

    public static String path ="C:\\Users\\farhad\\Desktop\\database.xml";
    public static Optional findByPath(String directoryPath){
        FileDTO dto= new FileDTO();

        try{
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= factory.newDocumentBuilder();
            Document doc= builder.parse(path);
            NodeList nodes= doc.getElementsByTagName("Address");
            for(int i=0; i<nodes.getLength(); i++){
                if(nodes.item(i).getTextContent().equals(directoryPath)){
                    Element parentNode =(Element)nodes.item(i).getParentNode();
                    dto.setAddress(parentNode.getElementsByTagName("Address").item(0).getTextContent());
                    dto.setCreateTime(Long.parseLong(parentNode.getElementsByTagName("CreateTime").item(0).getTextContent()));
                    dto.setLastModified(Long.parseLong(parentNode.getElementsByTagName("LastModified").item(0).getTextContent()));
                    dto.setFileName(parentNode.getElementsByTagName("Name").item(0).getTextContent());
                    dto.setFileSize(Long.parseLong(parentNode.getElementsByTagName("Size").item(0).getTextContent()));
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Optional<FileDTO> opt=Optional.ofNullable(dto);
        return opt;
    }
}
