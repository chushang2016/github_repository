package cn.zp.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseXML  extends DefaultHandler{
	private List<Student> list;
	private Student student;
	private String tagName;
	public List<Student> getList() {
		return list;
	}
	public void setList(List<Student> list) {
		this.list = list;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public void startDocument() throws SAXException {
		list=new ArrayList<Student>();
	}
	
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("student".equals(qName)){
			student=new Student();
			student.setId(attributes.getValue(0));
		}
		this.tagName=qName;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("student".equals(qName)){
			this.list.add(student);
		}
		this.tagName=null;
	}
	
	//只调用一次  
    @Override  
    public void endDocument() throws SAXException {  
    }  
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(this.tagName!=null){
			String elementValue=new String(ch,start,length);
			if("name".equals(this.tagName)){
				this.student.setName(elementValue);
			}else if("age".equals(this.tagName)){
				this.student.setAge(Integer.parseInt(elementValue));
			}else if("sex".equals(this.tagName)){
				this.student.setSex(elementValue);
			}else if("school".equals(this.tagName)){
				this.student.setSchool(elementValue);
			}
		}
	}
}
