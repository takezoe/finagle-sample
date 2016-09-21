name := "finagle-sample"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "hopper-repo" at "http://nexus.lab.mtl/nexus/content/repositories/releases/"

resolvers += "twttr" at "http://maven.twttr.com/"

libraryDependencies += "com.twitter" %% "finagle-http" % "6.37.0"
