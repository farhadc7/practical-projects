package Client;

import IU.FileTransmitter;
import IU.XmlConfig;
import IU.XmlToDto;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String Args[]) throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException, InterruptedException {
        XmlToDto xmlToDto=new XmlToDto();
        List<FileTransmitter> fileTransmitterList =xmlToDto.readTransaction("C:\\Users\\Auser\\Desktop\\banking\\transactionsClient1.xml");
        XmlConfig xmlConfig=xmlToDto.readConfig("C:\\Users\\Auser\\Desktop\\banking\\configurationClinet1.xml");
        Client1 c1=new Client1(fileTransmitterList,xmlConfig);
        c1.connection();
    }
}
