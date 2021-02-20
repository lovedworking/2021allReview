package algorithm.recall;

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
}
