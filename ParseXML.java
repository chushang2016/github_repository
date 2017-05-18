package cn.zp.sax;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseXML {
	public static void main(String[] args) {
		SAXParser saxParser=null;
		try {
			saxParser=SAXParserFactory.newInstance().newSAXParser();
			SaxParseXML saxParseXML=new SaxParseXML();
			InputStream ins=SaxParseXML.class.getClassLoader().getResourceAsStream("student.xml");
			saxParser.parse(ins, saxParseXML);
			List<Student> list=saxParseXML.getList();
			
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
