package test.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8868);
		Socket accept = serverSocket.accept();
		OutputStream outputStream = accept.getOutputStream();
		outputStream.write("ª∂”≠∑√Œ xxxx".getBytes());
		Scanner sc = new Scanner(System.in);
		while (true) {
			
			InputStream inputStream = accept.getInputStream();
			byte[] b = new byte[1024];
//			inputStream.read();
			int i = inputStream.read(b);
			System.out.println(new String(b, 0, i));


			OutputStream outputStream2 = accept.getOutputStream();
//			outputStream2.write("ª∂”≠∑√Œ xxxx".getBytes());
			String line = sc.nextLine();
			outputStream2.write(line.getBytes());
			
		}

	}
}
