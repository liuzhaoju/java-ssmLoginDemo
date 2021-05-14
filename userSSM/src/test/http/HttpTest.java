package test.http;

public class HttpTest {
	 public static void main(String[] args) {
	        //发送 GET 请求
//	        String s=HttpRequest.sendGet("http://localhost:8080/userSSM/user/findById", "id=1");
//	        System.out.println(s);
	        
	        //发送 POST 请求
	        String sr=HttpRequest.sendPost("http://localhost:8080/userSSM/user/findById", "id=1");
	        System.out.println(sr);
	    }
}
