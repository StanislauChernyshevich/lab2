package by.bsuir.phonetestsaxdom.validator;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
import java.io.*;

public class Valid {

    private static final String file = "tests.xml";
    private static final String shema = "testresult.xsd";


    public static boolean validate() throws IOException
    {
        Source schemaFile = new StreamSource(new File(shema));
      Source xmlFile = new StreamSource(new File(file));

      SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      

      try{
          Schema schema = schemaFactory.newSchema(schemaFile);
      Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
            return true;
      }
      catch (SAXException e) 
      {
            System.out.println(xmlFile.getSystemId() + " is NOT valid");
            System.out.println("Reason: " + e.getLocalizedMessage());
            return false;
      }
    } 
}
