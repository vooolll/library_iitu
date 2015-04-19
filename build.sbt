import com.github.bigtoast.sbtliquibase.LiquibasePlugin

name := "diploma"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.7",
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.148",
  "org.liquibase" % "liquibase-core" % "2.0.2",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.scalatra" %% "scalatra" % "2.3.1",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,compile",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016",
  "org.scalaz" %% "scalaz-core" % "7.1.1"
)

lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

TwirlKeys.templateImports += "user._"

Seq(webSettings :_*)

seq(LiquibasePlugin.liquibaseSettings: _*).settings

liquibaseUsername := "homestead"

liquibasePassword := "secret"

liquibaseDriver := "org.postgresql.Driver"

liquibaseUrl := "jdbc:postgresql://127.0.0.1:54320/library"

publishTo := Some(Resolver.file("bigtoast.github.com", file(Path.userHome + "/Projects/Destroyer/bigtoast.github.com/repo")))
