package lambda.part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-26 21:51
 **/
public class Sorting {
    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight()-o2.getWeight();
        }
    }

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)));
        //1 传统方式比较
        appleList.sort(new AppleComparator());
        System.out.println(appleList);

        // reshuffling things a little
        appleList.set(1, new Apple(30, Color.GREEN));
        //进化2 匿名内部类
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });
        System.out.println(appleList);

        //进化3 ：使用lambda
        appleList.set(1,new Apple(99,Color.GREEN));
        appleList.sort((a1,a2)->a1.getWeight()-a2.getWeight());
        System.out.println(appleList);
        appleList.sort(comparing(apple -> apple.getWeight()));

        //进化4  使用方法调用
        appleList.set(2,new Apple(199,Color.GREEN));
        appleList.sort(comparing(Apple::getWeight));
        System.out.println(appleList);
    }
}




















