package test.http;

public class HttpTest {
	 public static void main(String[] args) {
	        //���� GET ����
//	        String s=HttpRequest.sendGet("http://localhost:8080/userSSM/user/findById", "id=1");
//	        System.out.println(s);
	        
	        //���� POST ����
	        String sr=HttpRequest.sendPost("http://localhost:8080/userSSM/user/findById", "id=1");
	        System.out.println(sr);
	    }
}
