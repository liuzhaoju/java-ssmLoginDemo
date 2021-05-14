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
//		//ʹ��dom4j��ȡxml�ļ�
		readXmlByDom4J();
		//ʹ��dom4jдxml�ļ�
//		writeXmlByDom4J();
	}
	
	
	//xmlתjson
	public static String xml2jsonString() throws JSONException, IOException {
        InputStream in = XmlTest.class.getResourceAsStream("d:/dom4juser.xml");
        String xml = IOUtils.toString(in);
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
    }
	
	//ʹ��dom4j��ȡxml�ļ�
	private static void readXmlByDom4J() {
        try {
            // 1. ����org.dom4j.io.SAXReader����
            SAXReader saxReader = new SAXReader();
            InputStream ins = new FileInputStream("d:/dom4juser.xml");
            Document document =  saxReader.read(ins);
            Element rootElement = document.getRootElement();
            System.out.println("���ڵ�������ǣ�"+rootElement.getName());

            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                // ��ȡ����������������ֵ
                List<Attribute> attributesList = element.attributes();
                for (Attribute attribute : attributesList) {
                    System.out.println("��������"+attribute.getName()+"======����ֵ"+attribute.getValue());
                }

                // �����ӽڵ�
                Iterator childIterator = element.elementIterator();
                while (childIterator.hasNext()) {
                    Element child = (Element) childIterator.next();
                    System.out.println("��������"+child.getName()+"======����ֵ: "+child.getNamespaceURI());                   
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

	
	//ʹ��dom4jдxml�ļ�
	private static void writeXmlByDom4J() {
        try {
            // �����ĵ��������ĵ��ĸ�Ԫ�ؽڵ�   
            Element root = DocumentHelper.createElement("users");  
            Document document = DocumentHelper.createDocument(root);  
            //���ڵ�  
            root.addAttribute("name","usersvalue");  
            //�ӽڵ�  
            Element element1 = root.addElement("user");  
            element1.addAttribute( "id", "1" );
            element1.addElement("username", "С��"); 
            element1.addElement("password","22");

            Element element2 = root.addElement("user");  
            element2.addAttribute( "id", "2" );
            element2.addElement("username", "����"); 
            element2.addElement("password","23");
            //���  
            XMLWriter xmlwriter2 = new XMLWriter();  
            xmlwriter2.write(document);  
            //�����ļ�  
            OutputFormat format = new OutputFormat();  

            format = OutputFormat.createPrettyPrint();
            //�趨����
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
