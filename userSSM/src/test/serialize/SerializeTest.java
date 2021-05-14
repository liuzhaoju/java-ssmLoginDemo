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
		//map�洢 �������л�ΪString ,String�����л�Ϊ����
		ObjTransString();
		//���л� , �����л�Ϊ�ļ�
//		ObjTransFile();
    }
	
	//map�洢 �������л�ΪString ,String�����л�Ϊ����
	public static void ObjTransString() throws Exception {
		Map map=new HashMap<String,String>();
		map.put("user1", SerializeUtils.serialize(new User(1,"tom","tom123")));
		map.put("user2", SerializeUtils.serialize(new User(4,"����","����12345")));
		map.put("user3", SerializeUtils.serialize(new User(6,"����","����123987")));
		Set<String> set = map.keySet();
        //ʹ����ǿfor������
        for (String key : set) {
            //����map����get��������key��ȡ��Ӧ��valueֵ
            String value = (String) map.get(key);
            System.out.println(key + ":" + value);
            //String�����л�Ϊ����
            User user = (User)SerializeUtils.serializeToObject(value);
            System.out.println(user.toString());
        }

	}
	
	//���л� , �����л�Ϊ�ļ�
	public static void ObjTransFile() throws Exception {
		User user=new User(1,"tom","tom123");

        //  ���л�
        FileOutputStream fileOutputStream  = new FileOutputStream("E:\\user.txt");
        ObjectOutput objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);

        //  �����л�
        FileInputStream fileInputStream = new FileInputStream("E:\\user.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        User u = (User) object;
        u.setUsername("����");
        System.out.println(u.toString());
	}
}
