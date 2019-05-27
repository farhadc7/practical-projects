import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Optional;

public class XmlPresistance {
    private String path;
    public XmlPresistance(String path){
        this.path=path;
    }
    public void addTag(String tag , String path) {
        try {

            File inputFile = new File(this.path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Address");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if(nNode.getTextContent().equals(path)){
                    NodeList infoNode=nNode.getParentNode().getChildNodes();

                    Node tags=null;
                    for(int i=0;i<infoNode.getLength();i++){
                        if(infoNode.item(i).getNodeName().equals("tags")){
                            tags=infoNode.item(i);
                        }
                    }
                    Element Tag=doc.createElement("Tag");
                    Tag.appendChild(doc.createTextNode(tag));
                    tags.appendChild(Tag);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(String.valueOf(inputFile)));
            transformer.transform(source, result);
        }
        catch (Exception e){
            e.getMessage();
        }
    }
    public ArrayList<FileDTO> findByTag(String tag) {
        FileDTO DTO;
        ArrayList<FileDTO> fileDTOS=new ArrayList<>();
        try {
            File XMLFile = new File(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBulder = docFactory.newDocumentBuilder();
            Document doc = docBulder.parse(XMLFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList=doc.getElementsByTagName("file");
            for (int i = 0; i <nodeList.getLength() ; i++) {
                Node node=nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if(eElement.getElementsByTagName("Tag").item(0).getTextContent().equals(tag)){
                        DTO=new FileDTO();
                        DTO.setAddress(eElement.getElementsByTagName("Address").item(0).getTextContent());
                        DTO.setFileName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                        DTO.setCreateTime(Long.parseLong(eElement.getElementsByTagName("CreateTime").item(0).getTextContent()));
                        DTO.setLastModified(Long.parseLong(eElement.getElementsByTagName("LastModified").item(0).getTextContent()));
                        DTO.setFileSize(Long.parseLong(eElement.getElementsByTagName("Size").item(0).getTextContent()));
                        fileDTOS.add(DTO);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileDTOS;
    }

    public  Optional findByPath(String directoryPath){
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
	public Boolean updateFile(FileDTO newDTO){
        try{
            File xmlFile= new File(path);
            DocumentBuilderFactory builder= DocumentBuilderFactory.newInstance();
            DocumentBuilder Dbuilder= builder.newDocumentBuilder();
            Document doc= Dbuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodes= doc.getElementsByTagName("info");
            for( int i=0 ; i<nodes.getLength(); i++){
                Node node= nodes.item(i);
                Element e= (Element) node;
                if(e.getElementsByTagName("Address").item(0).getTextContent().equals(newDTO.getAddress())){
                    e.getElementsByTagName("Name").item(0).setTextContent(newDTO.getFileName());
                    e.getElementsByTagName("CreateTime").item(0).setTextContent(Long.toString(newDTO.getCreateTime()));
                    e.getElementsByTagName("LastModified").item(0).setTextContent(Long.toString(newDTO.getLastModified()));
                    e.getElementsByTagName("Size").item(0).setTextContent(Long.toString(newDTO.getFileSize()));
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.getCause();
        }
        return true;
    }
}
