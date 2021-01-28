package algorithm.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fendoukaoziji
 * @time 2021-01-28 23:09
 * @action 功能描述
 **/
public class BackTrace {
   List<List<Integer>>  res=new LinkedList<>();

   //输入一组不重复的数字，返回他们的全排列
   List<List<Integer>> permute(int[] nums){
       //记录【路径】
       LinkedList<Integer> track=new LinkedList<>();
       backtack(nums,track);
       return res;
   }
    //路径记录在track中，
    //选择列表，nums不存在于track的那些元素
    //结束条件 nums中的元素全都在track中出现
   void backtack(int[] nums, LinkedList<Integer> track) {
        //触发结束条件
       if(nums.length==track.size()){
           res.add(new LinkedList<>(track));
           return;
       }
       for (int i = 0; i <nums.length; i++) {
           //排除不合法的选择
           if(track.contains(nums[i])){
               continue;
           }
           //做选择
           track.add(nums[i]);
           //进入一下层决策树
           backtack(nums,track);
           track.removeLast();
       }
    }

















}
