package sparkkstreaming.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-15 14:51
 **/
public class JedisUtils {
    private static Logger logger= CommonUtil.getLogger(JedisUtils.class);
    //redis连接池
    private static JedisPool redisPool;
    //redis服务器ip
    private static String redisIp=ConfigurationManager.getProperty("redis.host");
    //redis 提供的端口
    private static Integer redisPort=ConfigurationManager.StringtoInteger(ConfigurationManager.getProperty("redis.port"));

    private static String redispassword=ConfigurationManager.getProperty("redis.password");
    // 最大连接数
    private static Integer maxTotal=ConfigurationManager.StringtoInteger(ConfigurationManager.getProperty("redis.maxToal"));
    //连接池中最大jedis实例个数
    private static Integer maxIdle=ConfigurationManager.StringtoInteger(ConfigurationManager.getProperty("redis.maxidle"));
    //连接池中最小jedis实例个数
    private static Integer minIdle=ConfigurationManager.StringtoInteger(ConfigurationManager.getProperty("redis.minidle"));
    private static Integer waitTimeMill=ConfigurationManager.StringtoInteger(ConfigurationManager.getProperty("redis.maxWaitMills"));
    //是否需要验证，如果为true，得到的jedis实例肯定可以用
    private static Boolean testOnBorrow=ConfigurationManager.StringtoBoolean(ConfigurationManager.getProperty("reids.testOnBorrow"));

    private static void initRedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        //是否用到密码
        redisPool=new JedisPool(jedisPoolConfig,redisIp,redisPort,waitTimeMill,redispassword);

    }
    static{
        initRedisPool();
    }


    /**
     *  对外开放获取jedis
     * @return Jedis
     */
    public static Jedis getJedis(){
        return redisPool.getResource();
    }


    /**
     * 关闭jedis连接
     * @param jedis jedis 连接
     */
    public static void closeRedis(Jedis jedis){
        if(jedis!=null)
            jedis.close();
    }


    /**
     *返回hash中指定存储位置的值 并且指定格式
     * 调用  jedis.get(key,field,Class)
     * @param key  hashkey
     * @param field  hashkey 对应字段
     * @param clazz 类型的实体
     * @param <T>  返回类型
     * @return T  类型
     */
    public static <T> T hgetKeyField(String key,String field,Class<T> clazz){
        logger.info("hget>> key + field :{},"+key+"/"+field);
        Jedis jedis=JedisUtils.getJedis();
        String hget = jedis.hget(key, field);
        T t =(T) JSONObject.parseObject(hget, clazz);
        closeRedis(jedis);
        return t;
    }



    /**
     * 获取hash中value集合，指定返回集合的类型
     * @param key key
     * @param clazz  类型
     * @param <T> 类型
     * @return
     */
     public static<T> List<T> hvals(String key,Class<T>  clazz){
         Jedis jedis=JedisUtils.getJedis();
         logger.info("hvals-->>key "+key);
         List<String> hvals = jedis.hvals(key);
         List<T> returnList =hvals.stream().map(value -> {
           T o = (T) JSONObject.parseObject(value, clazz);
           return o;
         }).collect(Collectors.toList());
         closeRedis(jedis);
         return returnList;
     }


    /**
     * 设置  hash key的过期时间
     * @param jedis  jedis连接
     * @param key   hash key
     * @param seconds  过期时间  可以设为 60*60*24*2   2天
     */
     public static void expire(Jedis jedis,String key,int seconds) {
         jedis.expire(key, seconds);
     }

    /**
     *通过key给指定的field的value加上给定的值
     * @param jedis jedis连接
     * @param key  hash key
     * @param field  hash field
     * @param value  value 累加值
     */
     public static void hincrby(Jedis jedis,String key,String field,Long value){
         jedis.hincrBy(key,field,value);
     }



    public static void main(String[] args) {
        Object o = hgetKeyField("ni", "wo", String.class);
        Jedis jedis = JedisUtils.getJedis();
        Map<String, String> stringStringMap = jedis.hgetAll("");
        System.out.println(o.toString());
    }





}















