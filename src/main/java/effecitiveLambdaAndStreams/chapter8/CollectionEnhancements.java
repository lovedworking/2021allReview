package effecitiveLambdaAndStreams.chapter8;

import java.util.*;
import java.util.logging.Logger;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-09 19:34
 **/
public class CollectionEnhancements {
    public static void main(String[] args) {
        testMap();
    }
    public static void makeCollections(){
        // not allowed add allowed set
        List<String> strings = Arrays.asList("xiaoming", "xiaoli");
        Set<String> peoples = new HashSet<>(Arrays.asList("xiaoming", "xiaoli"));

    }

    public static void testInnerToLambda(){
        int a=10;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int a=20;
                System.out.println("innerClass");
            }
        };

        Runnable r2 = () -> {
            //int a=20;  //报错误
            System.out.println("Lambda");
        };
    }

    public static void testMap(){
        Map<String, String> ageMap = new HashMap<>();
        ageMap.put("xiaoli","21");
        ageMap.put("xiaoming","qunide");
        //forEach
        ageMap.forEach((name,age)-> System.out.println("name:"+name+"|| age:"+age));

        //sorting
        ageMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        //getOrDefault
        String longnv = ageMap.getOrDefault("longnv", "99");
        System.out.println(longnv);
        System.out.println(ageMap.getOrDefault("xiaoli","88"));

        //computeIfAbsent
        Map<String,List<String>> peopleToMovies=new HashMap<>();
        peopleToMovies.computeIfAbsent("rabbit",name->new ArrayList<>())
                .add("wars");
        peopleToMovies.computeIfAbsent("tony",name->new ArrayList<>())
                .add("huoxing");
        peopleToMovies.computeIfAbsent("tony",name->new ArrayList<>())
                .add("xiongmap");
        System.out.println(peopleToMovies);

        System.out.println(peopleToMovies.remove("rabbit"));

        Logger logger = Logger.getLogger(String.valueOf(CollectionEnhancements.class));
        logger.info("start print ageMap");
        //replaceAll
        ageMap.replaceAll((name,age)->age+"??///");
        System.out.println(ageMap);
    }
























}
