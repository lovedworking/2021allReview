package streams.chpater5;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 20:29
 **/
public class Trader {
    private  final String name;
    private  final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return  "Trader "+this.name+"in"+ this.city;
    }
}






















