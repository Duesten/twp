resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/maven-releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.2")

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "2.4.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.3")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.7")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")

addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.4.2")

addSbtPlugin("com.timushev.sbt"  % "sbt-updates"  % "0.1.10")
