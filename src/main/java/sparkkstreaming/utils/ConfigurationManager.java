package sparkkstreaming.utils;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * 通过 #ConfigurationManager.getProperty(String s)  获取配置项
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-14 11:06
 **/
public class ConfigurationManager {

    /**
     * 设置为private 是防止外部类通过 ConfigurationManager.prop方式调用配置
     */
    private static Properties prop = new Properties();


    static {
        try {
            // 通过一个“类名.class”的方式，就可以获取到这个类在JVM中对应的Class对象
            // 然后再通过这个Class对象的getClassLoader()方法，就可以获取到当初加载这个类的JVM
            // 中的类加载器（ClassLoader），然后调用ClassLoader的getResourceAsStream()这个方法
            // 就可以用类加载器，去加载类加载路径中的指定的文件
            // 最终可以获取到一个，针对指定文件的输入流（InputStream）
            InputStream in = ConfigurationManager.class
                    .getClassLoader().getResourceAsStream("my.properties");

            // 调用Properties的load()方法，给它传入一个文件的InputStream输入流
            // 即可将文件中的符合“key=value”格式的配置项，都加载到Properties对象中
            // 加载过后，此时，Properties对象中就有了配置文件中所有的key-value对了
            // 然后外界其实就可以通过Properties对象获取指定key对应的value
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取指定key 对应的value
     * @param key   key-value 对应key
     * @return   String 对应的value
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


    /**
     * 获取Interger 类型的配置   如果没有对应Key  返回0
     * @return  Integer
     */
    public static Integer StringtoInteger(String s){
        try {
            return  Optional.of(Integer.parseInt(s)).get();
        } catch (Exception e) {
            //添加打印日志
            CommonUtil.getLogger(ConfigurationManager.class).error("配置属性为空，转为Integer发生异常");
            e.printStackTrace();
        }
        return  0;
    }

    /**
     * 获取Long 类型的配置   如果没有对应Key  返回0
     * @return  Long
     */
    public static Long StringtoLong(String s){
        try {
            return  Optional.of(Long.valueOf(s)).get();
        } catch (Exception e) {
            //添加打印日志
            CommonUtil.getLogger(ConfigurationManager.class).error("配置属性为空，转为Long发生异常");
            e.printStackTrace();
        }
        return  0L;
    }


    /**
     * 获取Boolean类型配置项
     * @param s  传递key
     * @return   默认false
     */
    public static Boolean  StringtoBoolean(String s){
        try {
            return  Optional.of(Boolean.valueOf(s)).get();
        } catch (Exception e) {
            //打印日志
            CommonUtil.getLogger(ConfigurationManager.class).error("配置属性为空，转为Boolean发生异常");
            e.printStackTrace();
        }
        return  false;
    }




}
