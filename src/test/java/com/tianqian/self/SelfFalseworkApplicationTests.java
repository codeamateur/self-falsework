//package com.tianqian.self;
//
//import com.tianqian.self.model.entity.user.SysUser;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.TimeUnit;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SelfFalseworkApplicationTests {
//
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//	@Autowired
//	private RedisTemplate  redisTemplate;
//
//	@Test
//	public void contextLoads() {
//	}
//
//	@Test
//	public void test() throws Exception {
//		// 保存字符串
//		stringRedisTemplate.opsForValue().set("aaa", "111");
//		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//	}
//
//	@Test
//	public void test2() throws Exception {
//		ValueOperations<String, SysUser> operations = redisTemplate.opsForValue();
//		boolean hasKey = redisTemplate.hasKey("test");
//		if (!hasKey){
//			SysUser user = new SysUser();
//			user.setLoginName("zjx");
//			user.setSex(true);
//			// 保存字符串
//			operations.set("test",user,10, TimeUnit.MINUTES);
//		}
//		Assert.assertEquals("zjx", operations.get("test").getLoginName());
//	}
//
//}
