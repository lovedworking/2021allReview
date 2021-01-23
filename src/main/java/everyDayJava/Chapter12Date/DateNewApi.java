package everyDayJava.Chapter12Date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-11 14:17
 **/
public class DateNewApi {
    public static void main(String[] args) {
       // testOldDate();
        testNewTime();
        //testWorkDate();
        //testParseDate();
        //testComplexFormatter();
       // testTimeZone();
    }
    public static void testOldDate(){
        Date date = new Date(117, 8, 21);
        System.out.println(date);
    }
    public static void testNewTime(){
        LocalDate nowDate = LocalDate.of(2019, 10, 11);
        System.out.println(nowDate);
        int year = nowDate.getYear();
        System.out.println(year);
        System.out.println("*******************************************");
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalTime nowTime = LocalTime.of(11, 30, 00);
        System.out.println(nowTime);

        LocalDate parse = LocalDate.parse("2019-02-19");
        System.out.println(parse);

        Period between = Period.between(LocalDate.of(2019, 11, 11), LocalDate.of(2019, 12, 25));
        System.out.println(between);

        Duration duration = Duration.ofMinutes(3);
        System.out.println(duration);
    }
    public static void testWorkDate(){

        LocalDate date1 = LocalDate.of(2019, 11, 11);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.FRIDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());
        System.out.println(date3);
    }

    public static void testParseDate(){
        LocalDate now = LocalDate.now();
        String s1Iso = now.format(DateTimeFormatter.BASIC_ISO_DATE);//20191011
        System.out.println(s1Iso);
        String localDateStr = now.format(DateTimeFormatter.ISO_LOCAL_DATE);//2019-10-11
        System.out.println(localDateStr);

        System.out.println("*****************************");
        LocalDate date1 = LocalDate.parse(s1Iso, DateTimeFormatter.BASIC_ISO_DATE);//2019-10-11
        LocalDate date2 = LocalDate.parse(localDateStr, DateTimeFormatter.ISO_LOCAL_DATE);//2019-10-11
        System.out.println(date1);
        System.out.println(date2);

        System.out.println("*********    自定义     ***********");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateNowFormatter = now.format(formatter);  //11/10/2019
        System.out.println(dateNowFormatter);
        LocalDate forMatterStr = LocalDate.parse(dateNowFormatter, formatter);//2019-10-11
        System.out.println(forMatterStr);
    }

    public static void testComplexFormatter(){
        LocalDate nowDate = LocalDate.of(2018, 6, 12);
        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("-")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("-")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CANADA);
        String dateStr = nowDate.format(complexFormatter);
        System.out.println(dateStr);//12-June-2018
        LocalDate parseDate = LocalDate.parse("20-June-2019", complexFormatter);
        System.out.println(parseDate);//2019-06-20
    }
    public static void testTimeZone(){
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(zoneId);
    }


}

















