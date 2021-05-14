package com.qicheng.ssm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qicheng.ssm.domain.User;
public class XmlUtils {
	//ʹ��dom4j��ȡxml�ļ�
		public static String readXmlByDom4J(MultipartFile userXml) {
			StringBuffer msg =new StringBuffer();
	        try {
	            // 1. ����org.dom4j.io.SAXReader����
	            SAXReader saxReader = new SAXReader();
//	            InputStream ins = new FileInputStream("d:/dom4juser.xml");
	          //��ȡ������
			    InputStream ins;
				ins = userXml.getInputStream();
	            Document document =  saxReader.read(ins);
	            Element rootElement = document.getRootElement();
	            System.out.println("���ڵ�������ǣ�"+rootElement.getName());
	            msg.append(rootElement.getName());
	            Iterator iterator = rootElement.elementIterator();
	            while (iterator.hasNext()) {
	                Element element = (Element) iterator.next();
	                // ��ȡ����������������ֵ
	                List<Attribute> attributesList = element.attributes();
	                for (Attribute attribute : attributesList) {
	                    System.out.println("��������"+attribute.getName()+"======����ֵ"+attribute.getValue());
	                    msg.append("<br/>").append(attribute.getName()).append(":").append(attribute.getValue());
	                }

	                // �����ӽڵ�
	                Iterator childIterator = element.elementIterator();
	                while (childIterator.hasNext()) {
	                    Element child = (Element) childIterator.next();
	                    System.out.println("��������"+child.getName()+"======����ֵ: "+child.getNamespaceURI());  
	                    msg.append("  ;  ").append(child.getName()).append(":").append(child.getNamespaceURI());
	                }
	            }
//	            return msg;
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (DocumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return msg.toString();
	    }

		
		//ʹ��dom4jдxml�ļ�
		public static void writeXmlByDom4J(HttpServletResponse response,List<User> list) {
	        try {
	            // �����ĵ��������ĵ��ĸ�Ԫ�ؽڵ�   
	            Element root = DocumentHelper.createElement("users");  
	            Document document = DocumentHelper.createDocument(root);  
	            //���ڵ�  
	            root.addAttribute("name","usersvalue");  
	            //�ӽڵ�  
	            for (int i = 0; i < list.size(); i++) {
					User user = list.get(i);
					Element element1 = root.addElement("user");  
		            element1.addAttribute( "id", new Integer(user.getId()).toString() );
		            element1.addElement("username", user.getUsername()); 
		            element1.addElement("password",user.getPassword());
				}
	            //���  
	            XMLWriter xmlwriter2 = new XMLWriter();  
	            xmlwriter2.write(document);  
	            //�����ļ�  
	            OutputFormat format = new OutputFormat();  

	            format = OutputFormat.createPrettyPrint();
	            //�趨����
	            format.setEncoding("UTF-8");
	         // ��������ʱ�ͻ���xml������ ������ע�͵ĸĽ��汾����������Ĳ�֧�֣�
	    		response.setContentType("application/octet-stream;charset=utf-8");
	    		response.setHeader("Content-Disposition",
	    				"attachment;filename=" + new String("�û�����".getBytes(), "iso-8859-1") + ".xml");
	    		OutputStream ouputStream = response.getOutputStream();
	    		 XMLWriter xmlwriter = new XMLWriter(ouputStream, format);
	            xmlwriter.write(document);
	            xmlwriter.flush();
	            ouputStream.flush();
	    		ouputStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }  
	    }  
}
