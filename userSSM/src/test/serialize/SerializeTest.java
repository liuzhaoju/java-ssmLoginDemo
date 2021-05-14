package test.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.qicheng.ssm.domain.User;
import com.qicheng.ssm.utils.SerializeUtils;

public class SerializeTest {
	public static void main(String [] args) throws Exception {
		//map存储 对象序列化为String ,String反序列化为对象
		ObjTransString();
		//序列化 , 反序列化为文件
//		ObjTransFile();
    }
	
	//map存储 对象序列化为String ,String反序列化为对象
	public static void ObjTransString() throws Exception {
		Map map=new HashMap<String,String>();
		map.put("user1", SerializeUtils.serialize(new User(1,"tom","tom123")));
		map.put("user2", SerializeUtils.serialize(new User(4,"李四","李四12345")));
		map.put("user3", SerializeUtils.serialize(new User(6,"王五","王五123987")));
		Set<String> set = map.keySet();
        //使用增强for来遍历
        for (String key : set) {
            //调用map集合get方法根据key获取对应的value值
            String value = (String) map.get(key);
            System.out.println(key + ":" + value);
            //String反序列化为对象
            User user = (User)SerializeUtils.serializeToObject(value);
            System.out.println(user.toString());
        }

	}
	
	//序列化 , 反序列化为文件
	public static void ObjTransFile() throws Exception {
		User user=new User(1,"tom","tom123");

        //  序列化
        FileOutputStream fileOutputStream  = new FileOutputStream("E:\\user.txt");
        ObjectOutput objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);

        //  反序列化
        FileInputStream fileInputStream = new FileInputStream("E:\\user.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        User u = (User) object;
        u.setUsername("二哈");
        System.out.println(u.toString());
	}
}
