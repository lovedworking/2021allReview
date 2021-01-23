package topN;

/**
 * @author fendoukaoziji
 * @time 2019-11-20 16:22
 * @action 功能描述
 **/
public class SaleMan  implements  Comparable{
    private Integer saleMoney;
    private String  saleName;

    public SaleMan(Integer saleMoney, String saleName) {
        this.saleMoney = saleMoney;
        this.saleName = saleName;
    }

    public Integer getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Integer saleMoney) {
        this.saleMoney = saleMoney;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public int compareTo(Object object) {
        SaleMan othersaleMan = (SaleMan) object;
        if(this.saleMoney>othersaleMan.saleMoney){
            return -1;
        }else if(this.saleMoney<othersaleMan.saleMoney){
            return 1;
        }
        return  0;
    }
}
