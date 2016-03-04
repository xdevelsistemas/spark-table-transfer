/* SimpleApp.scala */

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SimpleSQL {
  def main(args: Array[String]) {
    val logFile = "/home/immo/git/study/spark/spark-study/build.sbt" // Should be some file on your system

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val df = sqlContext.read.json("people.json")

    df.show()

    df.printSchema()

    df.select("name").show()

    df.select(df("name"), df("age") + 1).show()

    df.filter(df("age") > 21).show()

    df.groupBy("age").count().show()

  }
}