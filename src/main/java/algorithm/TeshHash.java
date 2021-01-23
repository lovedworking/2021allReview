package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fendoukaoziji
 * @time 2020-04-29 23:13
 * @action 功能描述
 **/
public class TeshHash {
    public static void main(String[] args) {
        int[]nums={1,3,2,2,5,2,3,7};
        int i = test1(nums);
        System.out.println(i);
    }
    public static int  test1(int[] nums){
        Map<Integer, Integer> countForNum = new HashMap<>();
        for (int num : nums) {
            countForNum.put(num, countForNum.getOrDefault(num, 0) + 1);
        }
        int longest = 0;
        for (int num : countForNum.keySet()) {
            if (countForNum.containsKey(num + 1)) {
                longest = Math.max(longest, countForNum.get(num + 1) + countForNum.get(num));
            }
        }
        return longest;
    }
}
