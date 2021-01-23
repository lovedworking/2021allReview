package sparkkstreaming.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Optional;

/**此类下面的所有方法均为线程安全
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-14 08:31
 **/
public class DateUtil {
    public static void main(String[] args) {

        //testTransToDate();
       /* DateTimeFormatter dateTimeFormatter = generateComplexDateTimeFormatter("-");
        System.out.println(dateTimeFormatter);
        String format = LocalDate.of(2019, 11, 11).format(dateTimeFormatter);
        System.out.println(format);*/
        //测试转为string
      /*  String s = DateTransToString(LocalDate.now());
        System.out.println(s);*/

        DateTimeFormatter formatterStr = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime t = LocalDateTime.of(2019, 11, 20, 03,32,34);
        String str=t.format(formatterStr);
        System.out.println(str);
        LocalDateTime parse = LocalDateTime.parse(str,formatterStr);
        LocalDate localDate = parse.toLocalDate();
        LocalTime localTime = parse.toLocalTime();
        System.out.println(parse);
        String s = DateTimeTransToString(t, formatterStr);
        System.out.println(s);
        String s1 = DateTimeTransToString(t);
        System.out.println(s1);
    }

    public static void testTransToDate(){
        String str="20191120";
        LocalDate localDate = StringTransToDate(str, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
        LocalDate localDate1 = StringTransToDate("2019-11-20");
        System.out.println(localDate1);

    }

    /**
     * 自定义复杂的时间转换器格式   比如    20-June-2019   格式之间用-拼接
     * @param splicer  字符串拼接符
     * @return 时间转换器
     */
    public static DateTimeFormatter generateComplexDateTimeFormatter(String splicer){
        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(splicer)
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(splicer)
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CANADA);
        return complexFormatter;
    }

    /**
     * 如果不传转换格式 默认按照ISO_Local_DATE转换   但日期必须指定为 yyyy-MM-DD格式
     * @param dateStr  日期字符串
     * @return  LocalDate
     */
    public static LocalDate  StringTransToDate(String dateStr){
        return StringTransToDate(dateStr,DateTimeFormatter.ISO_LOCAL_DATE);
    }
    /**
     * LocalDate 是线程安全类  不用担心多线程问题
     * 如果 传入20191014  需要指定格式 DateTimeFormatter.BASIC_ISO_DATE
     * @param dateStr  时间字符串
     * @param formatter  时间转换格式
     * @return  时间
     */
    public static LocalDate  StringTransToDate(String dateStr, DateTimeFormatter formatter){
        Optional<DateTimeFormatter> opFormatter = Optional.ofNullable(formatter);
        DateTimeFormatter dateTimeFormatter = opFormatter.orElse(DateTimeFormatter.ISO_LOCAL_DATE);
        return LocalDate.parse(dateStr, dateTimeFormatter);
    }


    /**
     *  按照默认方式
     * @param dateTimeStr  2019-11-20-03-32-34
     * @return   LocalDateTime
     */
    public static LocalDateTime  StringTransToDateTime(String dateTimeStr){
        return StringTransToDateTime( dateTimeStr,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**2019-11-20-03-32-34  转为 2019-11-20T03:32:34
     * @param dateTimeStr  2019-11-20-03-32-34
     * @param formatter   2019-11-20T03:32:34
     * @return  LocalDateTime
     */
    public static LocalDateTime  StringTransToDateTime(String dateTimeStr,DateTimeFormatter formatter){
        Optional<DateTimeFormatter> opFormatter = Optional.ofNullable(formatter);
        DateTimeFormatter dateTimeFormatter = opFormatter.orElse(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return LocalDateTime.parse(dateTimeStr,dateTimeFormatter);
    }



    /**
     *
     * @param date  LocalDate
     * @return  字符串 yyyy-MM-dd
     */
    public static String  DateTransToString( LocalDate date){
        return DateTransToString(date,DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 将指定时间格式转为 字符串
     * @param date  LocalDate
     * @param formatter  转换器
     * @return  字符串   默认    yyyy-MM-dd
     */
    public static String  DateTransToString( LocalDate date,DateTimeFormatter formatter){
        DateTimeFormatter dateTimeFormatter = Optional.ofNullable(formatter).orElse(DateTimeFormatter.ISO_LOCAL_DATE);
        return date.format(dateTimeFormatter);
    }


    /**
     * 默认使用 2019-11-20-03-32-34
     * @param date  LocalDateTime  2019-11-20T03:32:34
     * @return  String      2019-11-20-03-32-34
     */
    public static String  DateTimeTransToString( LocalDateTime date){
        return DateTimeTransToString(date,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     *
     * @param date  LocalDateTime
     * @param formatter  转换器
     * @return  String
     */
    public static String  DateTimeTransToString( LocalDateTime date,DateTimeFormatter formatter){
        DateTimeFormatter dateTimeFormatter = Optional.ofNullable(formatter).orElse(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return date.format(dateTimeFormatter);
    }




}
