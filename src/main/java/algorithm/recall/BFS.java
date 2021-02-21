package algorithm.recall;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    /**
     *
     *
     *

     //伪代码 计算从起点start到target的最短距离
     int BFS(Node first,Node target){
        Queue<Node> q;//核心数据结构
        Set<Node> visited;//记录所有走过的

        q.offer(start);//起点加入队列
        visited.add(start);
        int step=0;  //记录扩散的次数

        while(q not empty){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                Node curr=q.poll();
                if(cur is target)
                    return step;
                for(Node x:cur.adj()){
                    if(x not in visited){
                        q.offer(x);
                        visited.add(x);
                     }
                 }
             }
             //在这里更新步数
            step++;
     }




     }











     *
     *
     *
     */

    public int  openLock(String[] deadends,String target){
        //记录需要跳过的死亡密码；
        Set<String> deads = new HashSet<>();
        for (String dead : deads) {
            deads.add(dead);
        }
        //记录已经穷举过的密码，防止走回头路；
        Set<String> visited=new HashSet<>();
        Queue<String> q=new LinkedList<>();
        //从起点开始扩散
        int step=0;
        q.offer("0000");
        visited.add("0000");

        while(!q.isEmpty()){
            int sz=q.size();
            //将当前队列中的所有节点向周围扩散
            for (int i = 0; i < sz; i++) {
                String cur=q.poll();
                //判断是否到达终点
                if(deads.contains(cur)){
                    continue;
                }
                if(cur.equals(target)){
                    return  step;
                }

                for (int j = 0; j < 4; j++) {
                    String up=plusOne(cur,j);
                    if(!visited.contains(up)){
                        q.offer(up);
                        visited.add(up);
                    }
                    String down=minusOne(cur,j);
                    if(!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }

            }
            //增加步数  齐头并进
            step++;
        }
        //穷举完没找到目标
        return -1;
    }

    private String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    private String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }


}
