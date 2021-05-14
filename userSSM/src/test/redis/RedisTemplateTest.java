package test.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTemplateTest {

	// @Autowired
	// private static RedisTemplate<String,String> redisTemplate;

	// @Test
	// public void testRedis(){
	// //����key value
	// redisTemplate.opsForValue().set("city", "����", 30, TimeUnit.SECONDS);
	//
	// System.out.println(redisTemplate.opsForValue().get("city"));
	// }
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext-catch.xml");
		RedisTemplate<String, String> template = applicationContext.getBean(RedisTemplate.class);
		
		//���л�String
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer );
        template.setValueSerializer(stringSerializer );
        template.setHashKeySerializer(stringSerializer );
        template.setHashValueSerializer(stringSerializer );
		// ����key value
		template.opsForValue().set("city", "����", 30, TimeUnit.SECONDS);

		System.out.println(template.opsForValue().get("city"));
		String[] strarrays = new String[] { "strarr1", "sgtarr2" };
		System.out.println(template.opsForSet().add("setTest", strarrays));
		//Ϊ����"setTest" ���ù���ʱ��30��
		template.expire("setTest", 30, TimeUnit.SECONDS);
		//���ؼ����е����г�Ա
		System.out.println(template.opsForSet().members("setTest"));
	}
}
