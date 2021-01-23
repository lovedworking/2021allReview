package streams.chpater5;

/**
 * @program: ThreadJvm
 * @description
 * @author: tkk fendoukaoziji
 * @create: 2019-09-30 20:32
 **/
public class Transations {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transations(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transation :"+this.trader+"year: "+this.year+"value"+this.value;
    }
}





















