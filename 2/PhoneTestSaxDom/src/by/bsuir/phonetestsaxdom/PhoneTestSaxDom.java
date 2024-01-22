/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.phonetestsaxdom;

import by.bsuir.phonetestsaxdom.sax.Sax;
import by.bsuir.phonetestsaxdom.validator.Valid;
import java.io.*;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import by.bsuir.phonetestsaxdom.dom.Dom;


public class PhoneTestSaxDom {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance(); 
        SAXParser parser = factory.newSAXParser(); 
        Sax saxp = new Sax(); 
        parser.parse(new File("tests.xml"), saxp); 
        while(true){
            if(Menu.menu() == 0){
              break;
            }
        }
    }
}
