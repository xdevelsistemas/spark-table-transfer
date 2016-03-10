package br.com.xdevel.tabletransfer

/* SimpleApp.scala */

import java.util.Properties

import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

object Main extends App{
  //copies a table from A to B
  // original table
  val ORIGINAL_HOST = args(0) //"localhost"
  val ORIGINAL_DATABASE = args(1) //"postgres"
  val ORIGINAL_USER = args(2) //"postgres"
  val ORIGINAL_PASSWORD = args(3) //"mysecretpassword"
  val ORIGINAL_CONNECTION_URL = s"jdbc:postgresql://$ORIGINAL_HOST/$ORIGINAL_DATABASE?user=$ORIGINAL_USER&password=$ORIGINAL_PASSWORD"
  val ORIGINAL_TABLE = args(4) //"public.table_name"

  //destination table
  val DESTINATION_HOST = args(5) //"localhost"
  val DESTINATION_DATABASE = args(6) //"postgres"
  val DESTINATION_USER = args(7) //"postgres"
  val DESTINATION_PASSWORD = args(8) //"mysecretpassword"
  val DESTINATION_CONNECTION_URL = s"jdbc:postgresql://$DESTINATION_HOST/$DESTINATION_DATABASE?user=$DESTINATION_USER&password=$DESTINATION_PASSWORD"
  val DESTINATION_TABLE = args(9) // "public.table_name"


  // spark!
  val conf = new SparkConf().setAppName(s"spark-table-transfer-from-$ORIGINAL_HOST/$ORIGINAL_DATABASE/$ORIGINAL_TABLE-to-$DESTINATION_HOST/$DESTINATION_DATABASE/$DESTINATION_TABLE").setMaster("local")
  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)

  // criar dataFrame da tabela e registrar ele
  val original_table = sqlContext.read.jdbc(
    ORIGINAL_CONNECTION_URL,
    ORIGINAL_TABLE,
    new Properties()
  )
  original_table.registerTempTable("original_table")

  // pegar todos os registros
  val results = sqlContext.sql("SELECT * FROM original_table")

  //inserir no novo bd todos os registros
  //obs ele cria a tabela se não for especificada
  results.write.mode(SaveMode.Overwrite).jdbc(
    DESTINATION_CONNECTION_URL,
    DESTINATION_TABLE,
    new Properties()
  )
}