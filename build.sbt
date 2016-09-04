name := "thepiratebay"
version := "3.0"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases"

lazy val root = (project in file(".")).enablePlugins(PlayScala).enablePlugins(SbtWeb)

scalaVersion := "2.11.7"


libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.scalikejdbc" %% "scalikejdbc"         % "2.4.2",
  "org.scalikejdbc" %% "scalikejdbc-config"  % "2.4.2",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1",
  "org.scalikejdbc"      %% "scalikejdbc-play-fixture"      % "2.5.1",
  "com.github.tototoshi" %% "scala-csv" % "1.3.1",
  "org.flywaydb"         %% "flyway-play"                   % "3.0.+"
)


fork in run := false

scalikejdbcSettings
