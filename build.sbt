lazy val root = (project in file(".")).
  settings(
    name := "spark-table-transfer",
    version := "1.0",
    scalaVersion := "2.11.7",
    mainClass in Compile := Some("br.com.xdevel.tabletransfer.Main")
  )
// name := "spark-table-transfer"

// version := "1.0"

// scalaVersion := "2.11.7"

classpathTypes ~= (_ + "orbit")


libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.11" % "1.6.0",
                        "org.apache.spark" % "spark-sql_2.11" % "1.6.0",
                        "postgresql" % "postgresql" % "9.1-901-1.jdbc4")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
{
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x => old(x)
}
}
