logLevel := Level.Warn

resolvers += "bigtoast-github" at "http://bigtoast.github.com/repo/"

addSbtPlugin("com.github.bigtoast" % "sbt-liquibase" % "0.5")

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "0.7.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.0.4")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")