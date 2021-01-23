package everyDayJava.chapter11Optional;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-10-13 14:30
 **/
public class Car {
    private String name;
    private String weight;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "thisCarName :    "+this.name;
    }
}
