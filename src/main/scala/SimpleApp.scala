/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "/home/immo/git/study/spark/spark-study/build.sbt" // Should be some file on your system

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)


    val logData = sc.textFile(logFile, 2)

    val numAs = logData.filter(line => line.contains("a"))
    val numBs = logData.filter(line => line.contains("b"))


    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}