package lambda.part1.fileterApple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-22 15:54
 **/
public class FilterApples {
    public static void main(String[] args) {
        List<Apple> inventory= Arrays.asList(new Apple(80,"green"),
                new Apple(155,"red"),
                new Apple(120,"green"));
       //run1(inventory);
        // runSteamSeq(inventory);
        runStreamParrel(inventory);


    }
    public static void runStreamParrel(List<Apple> inventory){
        List<Apple> list=inventory.parallelStream().filter((Apple apple) -> apple.getWeight() > 120)
                .collect(toList());
        System.out.println(list);
    }
    public static void runSteamSeq(List<Apple> inventory){
        List<Apple> list = inventory.stream().filter((Apple apple) -> apple.getWeight() > 120)
                .collect(toList());
        System.out.println(list);
    }

    public static void run1(List<Apple> inventory){
        List<Apple> greenApples = fiterApples(inventory, FilterApples::isGreenApple);
        System.out.println(greenApples);

        //一种使用Predicate 一种是lambda表达式
        List<Apple> greenApples2 = fiterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        List<Apple> heavyApples = fiterApples(inventory, FilterApples::isHeavyApple);
        System.out.println(heavyApples);

        List<Apple> heavyApples2 = fiterApples(inventory, (Apple b)->b.getWeight()>100);
        System.out.println(heavyApples2);

        List<Apple> heavyApples3 = fiterApples(inventory, (Apple b)->b.getWeight()>100&&b.getColor().equals("red"));
        System.out.println(heavyApples3);
    }



    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());

    }
    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight()>150;
    }
    public static List<Apple> fiterHeavyApples(List<Apple> inventro){
        List<Apple> result=new ArrayList<>();
        for (Apple apple : inventro) {
            if(apple.getWeight()>150){
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterGreenApples(List<Apple> inventor){
        List<Apple> result=new ArrayList<>();
        for (Apple apple : inventor) {
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return  result;
    }
    public static List<Apple> fiterApples(List<Apple> inventory, Predicate<Apple>p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple))
                result.add(apple);
        }
        return  result;
    }
    public static class Apple{
        private int weight=0;
        private String color="";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
