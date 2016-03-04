/* SimpleApp.scala */

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SQLProgrammatically {
  def main(args: Array[String]) {
    // in this example the code queries a json file
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    case class Person(name: String, age: Int)

    val people = sqlContext.read.json("people.json") //reads the json into a data frame
    people.registerTempTable("people") //turns the data frame into a temporary table so we can query it using SQL

    val teens = sqlContext.sql("SELECT * FROM people WHERE age < 18") //the query
    teens.foreach(r => println(s"name ${r.get(1)}")) //print the structure, getting only the column of index 1
    teens.printSchema()
  }
}