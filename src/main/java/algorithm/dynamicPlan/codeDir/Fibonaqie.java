package algorithm.dynamicPlan.codeDir;

import java.util.HashMap;

/**
 * @author fendoukaoziji
 * @time 2021-01-25 22:28
 * @action 功能描述
 **/
public class Fibonaqie {
    public static void main(String[] args) {

    }

    //递归

    /****
     *                                                   f(20)
     *                                               f(19)    f(18)
     *                                             f(18)  f(17)     f(16)
     *                                          f(17)   f(16)      f(15)   f(14)
     *
     *                                          直到f(1)或者f(2) 时候，直接返回结果，递归树不再增长
     *                                          时间复杂度？  子问题个数*解决子问题需要时间
     *                                          人二叉树 O(2^n)
                                    大量重复子问题  比如 f(18)被计算两次  而每次耗费时间很多

     *
     */
    public static int fib1(int n){
        if(n==1||n==2)
            return 1;
        return fib1(n-1)+fib1(n-2);
    }

    /**
     *                                  f(20)
     *                               f(19)   f(18)
     *                                              f(17)   f(16)
     *                                                   f(15)    f(14)
     *                                                          f(13)  f(12)
     *                                                          ,,,,,
     *                              可以看出没有重复计算
     *                              计算的节点个数为f(n)个
     *                              按照子序列计算时间  f(n)*1
     *                             时间复杂度O(n)
     *                          比起暴力算法 ，降维打击啊
     *                          这种解法叫自顶向下  动态规划叫自底向上
     *
     *                          啥叫自底向上  直接从最底下 往上推直到我们想要的答案f(20)
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *





     */
    public static int  UseOnlyFib2(int n){
        if(n<1)
            return n;
        HashMap<Integer, Integer> memoMap = new HashMap<Integer, Integer>();
        return helper(memoMap,n);
    }

    private static int helper(HashMap<Integer, Integer> memoMap, int n) {
        //base case
        if(n==1||n==2){
            return 1;
        }
        //已经计算过
        if(memoMap.get(n)!=null){
            return memoMap.get(n);
        }
         memoMap.put(n,helper(memoMap, n - 1) + helper(memoMap, n - 2));
        return memoMap.get(n);
    }


}
