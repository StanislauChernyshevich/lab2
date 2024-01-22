package by.bsuir.phonetestsaxdom.dom;

import java.io.File;
import java.io.IOException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.*;
public class Dom{
    
    private static final String filePath = "tests.xml";
    private static Document doc;
    private static Scanner in;
    public Dom(){
        Scanner in = new Scanner(System.in);
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void save(){
        try{
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("tests.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            //System.out.println("XML file updated successfully");
        }catch (TransformerException e1) {
            e1.printStackTrace();
        }           
    }
    
    public void deleteResults(){
        System.out.println("Введите название отеля");
        Scanner inp = new Scanner(System.in);
        String name = inp.nextLine();
        NodeList results = doc.getElementsByTagName("results");
        Element emp = null;
        int k = 0;
        for(int i=0; i < results.getLength();i++){
            emp = (Element) results.item(i);
            if(emp.getElementsByTagName("name").item(0).getTextContent().equals(name)){
                k++;
                emp.getParentNode().removeChild(emp);
            }
        }
        if(k == 0)
            System.out.println("Such a record exists\n");
        save();
    }

    public void addResults(){
        Scanner inp = new Scanner(System.in);
        System.out.println("Введите марку телефона:");
        String name = inp.nextLine();
        System.out.println("Введите модель телефона:");
        String type = inp.nextLine();
        int count = 0;
        int flag = 1;
        do{
            try{
                flag = 1;
                System.out.println("Введите стоимость телефона:");
                String temp = inp.next();
                count = Integer.parseInt(temp);
                if(count <= 0)
                    throw new Exception();
            }
            catch(Exception e){
                System.out.println("Please enter correct count");
                flag = 0;
            }
        }while(flag == 0);
        Element emp = checkResults(name);
        if(emp != null){
            Node countNode = emp.getElementsByTagName("count")
              .item(0).getFirstChild();
            countNode.setNodeValue(Integer.toString(Integer.parseInt(countNode.getNodeValue()) 
                + count));
        }else{
            Element resultsElement = doc.createElement("results");
            resultsElement.setAttribute("id", Integer.toString(count));
            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(name));
            resultsElement.appendChild(nameElement);   
            Element typeElement = doc.createElement("type");
            typeElement.appendChild(doc.createTextNode(type));
            resultsElement.appendChild(typeElement);
            Element countElement = doc.createElement("count");
            countElement.appendChild(doc.createTextNode(Integer.toString(count)));
            resultsElement.appendChild(countElement);
            Element root = (Element)doc.getElementsByTagName("warehouse").item(0);
            root.appendChild(resultsElement);
            resultsElement.setAttribute("id",Integer.toString((new Random()).nextInt(1000))); 
        }
        save();
    }

    public static Element checkResults(String name){
        NodeList results = doc.getElementsByTagName("results");
        for(int i=0; i<results.getLength();i++){
            Element emp = (Element)results.item(i);
            if(emp.getElementsByTagName("name").item(0)
                    .getTextContent().equals(name)){
                return emp;
            }
        }
        return null;
    }
}
