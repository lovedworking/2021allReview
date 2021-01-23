package compareWithJava

/**
 * @author fendoukaoziji
 * @create 2019-10-22 12:52
 * @description 功能描述
 **/
object Hello {
  def main(args: Array[String]): Unit = {
   /* var n:Int=2
    while(n<=6){
      println(s"Hello $n  bottles of beer")
      n+=1
    }
    2 to 10  foreach(x=>println(x))*/

    //map  val  read-only   var  read-write
    val authorsToAge=Map("ni"->23)
    //tuple
    val booke=(2018, "modern java inAction ","Manning")
    println(booke._2)

  }

}




















