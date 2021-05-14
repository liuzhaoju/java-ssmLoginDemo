package test.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlTest {
	public static void main(String [] args) throws Exception {
//		//使用dom4j读取xml文件
		readXmlByDom4J();
		//使用dom4j写xml文件
//		writeXmlByDom4J();
	}
	
	
	//xml转json
	public static String xml2jsonString() throws JSONException, IOException {
        InputStream in = XmlTest.class.getResourceAsStream("d:/dom4juser.xml");
        String xml = IOUtils.toString(in);
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
    }
	
	//使用dom4j读取xml文件
	private static void readXmlByDom4J() {
        try {
            // 1. 创建org.dom4j.io.SAXReader对象
            SAXReader saxReader = new SAXReader();
            InputStream ins = new FileInputStream("d:/dom4juser.xml");
            Document document =  saxReader.read(ins);
            Element rootElement = document.getRootElement();
            System.out.println("根节点的名称是："+rootElement.getName());

            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                // 获取所有属性名和属性值
                List<Attribute> attributesList = element.attributes();
                for (Attribute attribute : attributesList) {
                    System.out.println("属性名："+attribute.getName()+"======属性值"+attribute.getValue());
                }

                // 遍历子节点
                Iterator childIterator = element.elementIterator();
                while (childIterator.hasNext()) {
                    Element child = (Element) childIterator.next();
                    System.out.println("属性名："+child.getName()+"======属性值: "+child.getNamespaceURI());                   
                }

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	
	//使用dom4j写xml文件
	private static void writeXmlByDom4J() {
        try {
            // 创建文档并设置文档的根元素节点   
            Element root = DocumentHelper.createElement("users");  
            Document document = DocumentHelper.createDocument(root);  
            //根节点  
            root.addAttribute("name","usersvalue");  
            //子节点  
            Element element1 = root.addElement("user");  
            element1.addAttribute( "id", "1" );
            element1.addElement("username", "小王"); 
            element1.addElement("password","22");

            Element element2 = root.addElement("user");  
            element2.addAttribute( "id", "2" );
            element2.addElement("username", "老王"); 
            element2.addElement("password","23");
            //添加  
            XMLWriter xmlwriter2 = new XMLWriter();  
            xmlwriter2.write(document);  
            //创建文件  
            OutputFormat format = new OutputFormat();  

            format = OutputFormat.createPrettyPrint();
            //设定编码
            format.setEncoding("UTF-8");
            XMLWriter xmlwriter = new XMLWriter(new FileOutputStream("d:/dom4juser.xml"), format);
            xmlwriter.write(document);
            xmlwriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }  

}
