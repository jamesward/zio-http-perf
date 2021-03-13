lazy val helloJVM = ProjectRef(uri(s"git://github.com/jamesward/hello-zio-http.git"), "hello-zio-http")
lazy val helloGraalVM = ProjectRef(uri(s"git://github.com/jamesward/hello-zio-http.git#graalvm"), "hello-zio-http")

scalaVersion := "2.12.13"

aggregateProjects(helloJVM, helloGraalVM)

enablePlugins(GatlingPlugin)

libraryDependencies := Seq(
  "org.testcontainers"    % "testcontainers"            % "1.15.2" % Test,
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.2.1"  % Test,
  "io.gatling"            % "gatling-test-framework"    % "3.2.1"  % Test,
)

val helloJVMBuild = TaskKey[Unit]("helloJVMBuild")
helloJVMBuild := (helloJVM / Docker / publishLocal).value

val helloGraalVMBuild = TaskKey[Unit]("helloGraalVMBuild")
helloGraalVMBuild := (helloGraalVM / Docker / publishLocal).value

Gatling / test := (Gatling / test).dependsOn(helloJVMBuild, helloGraalVMBuild).value
