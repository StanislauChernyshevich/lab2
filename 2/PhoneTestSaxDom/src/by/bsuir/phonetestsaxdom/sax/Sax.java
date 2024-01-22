package by.bsuir.phonetestsaxdom.sax;
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.*;  
import java.io.File;
import by.bsuir.phonetestsaxdom.entity.TestResult;

public class Sax extends DefaultHandler{

    private TestResult mat = new TestResult(); 
    private String thisElement = ""; 
    private static List<TestResult> matList = new ArrayList<TestResult>(); 
    private static String temp;
    
    @Override 
    public void startDocument() throws SAXException { 
        matList.clear();
    }

    @Override 
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException { 
        temp = "";
        if (qName.equalsIgnoreCase("results")) {
            mat = new TestResult();
        }
    }


    @Override 
    public void characters(char[] ch, int start, int length)throws SAXException { 
        temp = new String(ch, start, length);
    }

    @Override 
    public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException { 
        if (qName.equalsIgnoreCase("results")) {
            matList.add(mat);
        } else if (qName.equalsIgnoreCase("name")) {
            mat.setName(temp);
        } else if (qName.equalsIgnoreCase("type")) {
            mat.setType(temp);
        } else if (qName.equalsIgnoreCase("count")) {
            mat.setCount(Integer.parseInt(temp));
        }
    }

     
    @Override 
    public void endDocument() { 
    }  


    public static void search() 
                throws ParserConfigurationException,
                       SAXException,
                       IOException{
       Scanner in = new Scanner(System.in);
       System.out.println("Enter type of results");
       String type = in.next();
       int count = 0;
       int flag = 1;
       do{
            try{
                System.out.println("Enter count of results");
                count = in.nextInt();
                if(count <= 0)
                    throw new Exception();
           }catch(Exception ex){
                System.out.println("Please enter correct data");
                flag = 0;
           }
       }while(flag == 0);
       matList = new ArrayList<TestResult>();
       parse();
       List<TestResult> returnList = new ArrayList<TestResult>();
       for (TestResult mat : matList) {
           if(mat.getType().equals(type) && mat.getCount() == count){
                returnList.add(mat);
           }
       }
       print(returnList);
       matList.clear();
    }

    public static void print(List<TestResult> list){
        for(TestResult mat : list){
            System.out.println("Name : " + mat.getName());
            System.out.println("Type : " + mat.getType());
            System.out.println("Price : " + mat.getCount() + "\n");
       }

    }

    public static void parse() throws ParserConfigurationException,
                       SAXException,
                       IOException{
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();  
        Sax matchHandler = new Sax();  
        saxParser.parse(new File("tests.xml"), matchHandler);  

    }

    public static void view() throws SAXException, ParserConfigurationException,
           IOException{
        parse();
        print(matList);
        matList.clear();
    }

}

