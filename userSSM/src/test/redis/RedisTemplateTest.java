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
	// //保存key value
	// redisTemplate.opsForValue().set("city", "北京", 30, TimeUnit.SECONDS);
	//
	// System.out.println(redisTemplate.opsForValue().get("city"));
	// }
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("conf/applicationContext-catch.xml");
		RedisTemplate<String, String> template = applicationContext.getBean(RedisTemplate.class);
		
		//序列化String
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer );
        template.setValueSerializer(stringSerializer );
        template.setHashKeySerializer(stringSerializer );
        template.setHashValueSerializer(stringSerializer );
		// 保存key value
		template.opsForValue().set("city", "北京", 30, TimeUnit.SECONDS);

		System.out.println(template.opsForValue().get("city"));
		String[] strarrays = new String[] { "strarr1", "sgtarr2" };
		System.out.println(template.opsForSet().add("setTest", strarrays));
		//为集合"setTest" 设置过期时间30秒
		template.expire("setTest", 30, TimeUnit.SECONDS);
		//返回集合中的所有成员
		System.out.println(template.opsForSet().members("setTest"));
	}
}
