package test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception, IOException {
		InetAddress byName = InetAddress.getByName("192.168.2.231");
		InetAddress localHost = InetAddress.getLocalHost();
		Socket socket = new Socket(byName, 8868);
		Scanner sc = new Scanner(System.in);
		String line="";
		InputStream stream=null;
		OutputStream outputStream=null;
		while (!line.equals("quit")) {
			stream = socket.getInputStream();
			byte[] b = new byte[1024];
			int i = stream.read(b);
			System.out.println(new String(b, 0, i));

			outputStream = socket.getOutputStream();
			line = sc.nextLine();
			outputStream.write(line.getBytes());
			outputStream.flush();
		}
		try {
			stream.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
