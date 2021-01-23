package javabase;

/***
 *
 * socket是网络上运行的两个程序之间双向通信的一个endpoint ,客户端和服务端创建步骤大体相同。
 * java通过调用java.net包来实现
 * 客户端
 * 1 通过指定ip和端口调用Socket来创建一个socket客户端实例
 * 2 获得输入流input stream 和输出流 output stream
 * 3 接下来就是接收和发送采用多种方式
 * 4 关闭连接，关闭socket
 *
 * 服务端
 *  1 通过指定端口调用serversocket 创建一个服务端socket实例
 *  2 服务端socket通过accept接收客户端消息
 *  3 获得输入流和输出流
 *  4  关闭连接
 *
 *  单元测试设计
 *  测试大体分为单元测试  冒烟测试  sit系统测试  uat验收测试
 *  单元测试试程序测试的一个重要环节，也是保证程序代码质量和可维护的关键步骤
 *  单元测试主要包括code review 静态代码扫描 以及单元测试用例编写
 *  主要注意事项有
 *  1 选择合适的单元测试框架和工具
 *  2 开发环境要和单元测试环境隔离
 *  3 测试使用的数据集应该接近于生产的数据集
 *  4 应该编写独立的测试用例
 *  5 在修复缺陷之前，编写相关暴露缺陷的测试
 *  6 单元测试也要纳入版本控制管理如git
 *  7 持续频繁地进行单元测试
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












import java.util.Scanner;

/**
 * @author fendoukaoziji
 * @time 2020-05-24 17:05
 * @action 功能描述
 **/
public class InitValue {
    static int x,y;
    static{
        int x=5;
    }

    public static void main(String[] args) {
        x--;
        myMethod();
        System.out.println(x+y + ++x);
      //  test1();
        System.out.println(1%3);
        System.out.println(2%3);
        System.out.println();

        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String str = sc.nextLine();
//            System.out.printf("%s\n",str);
//        }

        System.out.println(Math.round(11.46));
        System.out.println(Math.round(-11.46));
        System.out.println(Math.round(11.76));
        System.out.println(Math.round(-11.76));

    }
    public static void myMethod(){
        y=x++ + ++x;
    }

    public static void test1(){
        int k=1,n=0;
        while (k<=5){
            switch (k){
                case 1:n+=1;
                case 2: n+=2;
                case 3:n+=3;
            }
            k++;
        }
        System.out.println(n);
    }
}

















