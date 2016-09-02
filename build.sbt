name := "thepiratebay"
version := "3.0"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"


libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.github.tototoshi" %% "scala-csv" % "1.3.1",
  "org.scalikejdbc" %% "scalikejdbc" % "2.4.+"
)


fork in run := false

scalikejdbcSettings
