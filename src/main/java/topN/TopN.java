package topN;

import java.util.*;

/**
 * @author fendoukaoziji
 * @time 2019-11-20 16:16
 * @action 功能描述
 **/
public class TopN {
    public static void main(String[] args) {
        Map<SaleMan, String> compareMap = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            SaleMan saleMan = new SaleMan(i* new Random().nextInt(5),  i+"号销售员");
            compareMap.put(saleMan,saleMan.getSaleName());
        }
        compareMap.forEach((saleMan, s) -> System.out.println("销售额:"+saleMan.getSaleMoney()+"||姓名:"+s));
        SortedMap<SaleMan, String> sortMap = new TreeMap<>();
        compareMap.forEach((saleMan, s) -> {
            sortMap.put(saleMan,s);
            if(sortMap.size()>5)
                sortMap.remove(sortMap.lastKey());
        });
        System.out.println("排序后");
        sortMap.forEach((saleMan, s) -> System.out.println("销售额:"+saleMan.getSaleMoney()+"||"+s));
    }
}
