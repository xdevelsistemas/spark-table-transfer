/* SimpleApp.scala */

import java.util.Properties

import org.apache.spark.sql.{SaveMode, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkTableTransfer {
  def main(args: Array[String]) {
    //copies a table from A to B
    val ORIGINAL_TABLE = "public.table_name"
    val DESTINATION_TABLE = "public.new_table"
    val ORIGINAL_CONNECTION_URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=mysecretpassword"
    val DESTINATION_CONNECTION_URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=mysecretpassword"

    // in this example the code queries a json file
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    // criar dataFrame da tabela e registrar ele
    val table_name = sqlContext.read.jdbc(
      ORIGINAL_CONNECTION_URL,
      ORIGINAL_TABLE,
      new Properties()
    )
    table_name.registerTempTable("table_name")

    val results = sqlContext.sql("SELECT * FROM table_name") //the query
    results.foreach(println) //print the structure, getting only the column of index 1

    //inserir no novo bd
    //obs ele cria a tabela se não for especificada
    results.write.mode(SaveMode.Overwrite).jdbc(
      DESTINATION_CONNECTION_URL,
      DESTINATION_TABLE,
      new Properties()
    )

  }
}