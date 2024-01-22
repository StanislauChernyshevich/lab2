package by.bsuir.phonetestsaxdom;
import by.bsuir.phonetestsaxdom.validator.Valid;
import java.util.*;
import java.io.*;
import by.bsuir.phonetestsaxdom.entity.TestResult;
import by.bsuir.phonetestsaxdom.sax.Sax;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import by.bsuir.phonetestsaxdom.dom.Dom;

public class Menu{
   
    private static final String out = "1 - Проверить валидность файла с тедефонами\n" +
            "2 - Поиск записи\n" +
            "3 - Добавление записи\n" +
            "4 - Удаление записи\n" +
            "5 - Просмотр телефонов\n" +
            "6 - Выход\n";
  
    public static int menu() throws IOException, 
           ParserConfigurationException,
           SAXException{
        Dom dom;
        switch(choice()){
            case 1: Valid.validate(); break;
            case 2: Sax.search(); break;
            case 3: dom = new Dom(); dom.addResults(); break;
            case 4: dom = new Dom(); dom.deleteResults(); break;
            case 5: Sax.view(); break;
            case 6: return 0; 
            default: System.out.println("Error"); break;
        }
        return 1;
    }

    public static int choice(){
        int ch = 0;
        System.out.println(out);
        Scanner in = new Scanner(System.in);
        try{
            ch = in.nextInt();
            System.out.println(ch);
        }
        catch(Exception ex)
        {
            System.out.println("Please enter correct data");
            choice();
        }

        return ch;
    }
}
