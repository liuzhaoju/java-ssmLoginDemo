package test.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {

		String srcFileName = "d:/dom4juser.xml";
		String destFileName = "d:/dom4juser123.xml";
		method3(srcFileName,destFileName);
		
//		copy("C:\\Users\\Public\\Videos", "E:\\ѧϰ����");
//
//		System.out.println("�������");
	}
	
	//������һ�ζ�дһ���ַ���
	public static void method5(String srcFileName,String destFileName) throws IOException {
		//�������뻺��������
		BufferedReader br = new BufferedReader(new FileReader(srcFileName));
		//�����������������
		BufferedWriter bw = new BufferedWriter(new FileWriter(destFileName));
		
		//һ�ζ�дһ���ַ���
		String line;
		while((line=br.readLine())!=null){
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		
		//�ͷ���Դ
		bw.close();
		br.close();
	}
	
	//������һ�ζ�дһ���ַ�����
	public static void method4(String srcFileName,String destFileName) throws IOException {
		//�������뻺��������
		BufferedReader br = new BufferedReader(new FileReader(srcFileName));
		//�����������������
		BufferedWriter bw = new BufferedWriter(new FileWriter(destFileName));
		
		//һ�ζ�дһ���ַ�����
		char[] chs = new char[1024];
		int len;
		while((len=br.read(chs))!=-1) {
			bw.write(chs,0,len);
		}
		
		//�ͷ���Դ
		bw.close();
		br.close();
	}
	
	//������һ�ζ�дһ���ַ�
	public static void method3(String srcFileName,String destFileName) throws IOException {
		//�������뻺��������
		BufferedReader br = new BufferedReader(new FileReader(srcFileName));
		//�����������������
		BufferedWriter bw = new BufferedWriter(new FileWriter(destFileName));
		
		//һ�ζ�дһ���ַ�
		int ch;
		while((ch=br.read())!=-1) {
			bw.write(ch);
		}
		
		//�ͷ���Դ
		bw.close();
		br.close();
	}
	
	//������һ�ζ�дһ���ַ�����
	public static void method2(String srcFileName,String destFileName) throws IOException {
		//��������������
		FileReader fr = new FileReader(srcFileName);
		//�������������
		FileWriter fw = new FileWriter(destFileName);
		
		//һ�ζ�дһ���ַ�����
		char[] chs = new char[1024];
		int len;
		while((len=fr.read(chs))!=-1) {
			fw.write(chs,0,len);
		}
		
		//�ͷ���Դ
		fw.close();
		fr.close();
	}
	
	//������һ�ζ�дһ���ַ�
	public static void method1(String srcFileName,String destFileName) throws IOException {
		//��������������
		FileReader fr = new FileReader(srcFileName);
		//�������������
		FileWriter fw = new FileWriter(destFileName);
		
		//һ�ζ�дһ���ַ�
		int ch;
		while((ch=fr.read())!=-1) {
			fw.write(ch);
		}
		
		//�ͷ���Դ
		fw.close();
		fr.close();
	}
	
	public static void copy(String src, String des) {
		File srcfile = new File(src);
		File desfile = new File(des);
		/*if (!srcfile.exists()) {
			System.out.println("Ҫ���Ƶ��ļ�������");
			return;
		}*/
		if (!desfile.exists()) {
			desfile.mkdirs();
		}
		File[] listFiles = srcfile.listFiles();
		for (File f : listFiles) {
			if (f.isDirectory()) {

				copy(f.getPath(), des + "\\" + f.getName());

			} else {
				try {
					copyFile(f.getPath(), des + "\\" + f.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	

		/*File fileSrc = new File(src);
		File fileDes = new File(des);
		File[] fs = fileSrc.listFiles();
		if (!fileDes.exists()) {
			fileDes.mkdirs();
		} 
		
	
		  for (File f : fs) {
	            if(f.isFile()){
	            	copyFile(f.getPath(), des + "\\" + f.getName());
	            }else if(f.isDirectory()){
	                copy(f.getPath(), des + "\\" + f.getName());
	            }
	        
		}
	
	*/
	}

	public static void copyFile(String src, String des) {
		File srcfile1 = new File(src);
		File desfile1 = new File(des);
		FileInputStream fis;
		FileOutputStream fos;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		try {
			fis = new FileInputStream(srcfile1);
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream(desfile1);
			bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024 * 8];
			int len;
			try {
				while ((len = bis.read(b)) != -1) {
					bos.write(b, 0, len);
					bos.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
