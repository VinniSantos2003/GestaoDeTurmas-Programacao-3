package utilities;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.staticModels.Lista;

import java.io.File;

public class xmlHandler {
    public void exportarXml(){
        try{
            TempXMLList t = new TempXMLList();
            t.exportMode();
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(new File("dadosXML.xml"),t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void importarXml(File arquivo){
        try{
            XmlMapper xmlMapper = new XmlMapper();
            TempXMLList tXML = new TempXMLList();
            tXML = xmlMapper.readValue(arquivo,tXML.getClass());
            tXML.importMode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
