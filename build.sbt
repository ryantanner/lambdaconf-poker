name := "lambdaconf-poker"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases")
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.1"
)