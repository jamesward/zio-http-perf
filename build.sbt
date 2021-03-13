lazy val helloJVM = ProjectRef(uri(s"git://github.com/jamesward/hello-zio-http.git"), "hello-zio-http")
lazy val helloGraalVM = ProjectRef(uri(s"git://github.com/jamesward/hello-zio-http.git#graalvm"), "hello-zio-http")

/*
lazy val root = (project in file(".")).settings(
  name := "Hello",
  publish / skip := true,
  helloGraalVM / graalVMNativeImageGraalVersion := Some("21.0.0.2")
).dependsOn(helloJvm, helloGraalVM).enablePlugins(GatlingPlugin)
 */

dependsOn(helloJVM, helloGraalVM)

enablePlugins(GatlingPlugin, GraalVMNativeImagePlugin)

libraryDependencies ++= Seq(
  "org.testcontainers"    % "testcontainers"            % "1.15.2" % Test,
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.2.1"  % Test,
  "io.gatling"            % "gatling-test-framework"    % "3.2.1"  % Test,
)

//state := state.value.put(graalVMNativeImageGraalVersion.key, Some("21.0.0.2"))

//helloJvm / Docker / packageName := "hello-zio-http-jvm"


//helloGraalVM / graalVMNativeImageGraalVersion := Some("21.0.0.2")
/*
Global / onLoad := {
  val old = (Global / onLoad).value
  old.andThen { s =>
    println("asdfasdfasdf")
    s.getSetting(helloGraalVM / graalVMNativeImageGraalVersion).fold {
      val extracted = Project.extract(s)
      val newState = extracted.appendWithSession(Seq(helloGraalVM / graalVMNativeImageGraalVersion := Some("20.0.0")), s)
      newState
    } { _ =>
      s
    }
  }
}

 */

/*
Global / onLoad := {
  val old = (Global / onLoad).value
  // compose the new transition on top of the existing one
  // in case your plugins are using this hook.
  //startupTransition compose old
  println("asdfasdf")
  old
}
 */

/*
Global / onLoad := {
  val old = (Global / onLoad).value
  old.andThen { state =>
    println("asdfasdfasdf")
    state
    state.put((helloGraalVM / graalVMNativeImageGraalVersion).key, Some("21.0.0.2"))
    state
  }
}
 */

/*
helloGraalVM / onLoad := { state =>

  println(state)
  state.put(graalVMNativeImageGraalVersion.key, Some("21.0.0.2"))

  state
}
 */

  /*
  val old = (helloGraalVM / onLoad).value

  println("zxcvzxcv")

  { state: State =>
    println("asdfasdfasdf")
    state.put(graalVMNativeImageGraalVersion.key, Some("21.0.0.2"))
  } // compose old
  */

  /*
  old.compose { state: State =>
    println("qwerqwer")
    state
  }
   */

  /*
  old.andThen { state =>
    println("asdfasdfas")
    state
  }
   */

/*
~= (_.andThen { state =>
  println("onLoad")
  state
})
 */

  /*
  )
  println("onLoad")
  state
}

   */

val helloGraalVMBuild = TaskKey[Unit]("helloGraalVMBuild")

/*
helloBuild := Def.task {
  println("zxcvzxcvzxcv")
  println((helloGraalVM / graalVMNativeImageGraalVersion).value)
  //(helloGraalVM / GraalVMNativeImage / packageBin).value
}.value
 */
helloGraalVMBuild := (helloGraalVM / Docker / publishLocal).value

/*
helloGraalVMBuild := {
  val s = state.value
  val extracted = Project.extract(s)
  val sn = helloGraalVM / name := "hello-zio-http-graalvm"
  val newState = extracted.appendWithoutSession(Seq(sn), s)
  Project.runTask(helloGraalVM / Docker / publishLocal, newState)

 */
  /*
  val scbi = helloGraalVM / GraalVMNativeImage / containerBuildImage := Some("ghcr.io/graalvm/graalvm-ce:21.0.0")
  val gnio = (helloGraalVM / graalVMNativeImageOptions).value.filterNot(_.contains("--static")).filterNot(_.contains("--libc"))
  val so = helloGraalVM / graalVMNativeImageOptions := gnio
  val newState = extracted.appendWithoutSession(Seq(sn, scbi, so), s)
  val result = Project.runTask(helloGraalVM / GraalVMNativeImage / packageBin, newState)
  println(result.get._2)
   */
//}

val helloJVMBuild = TaskKey[Unit]("helloJVMBuild")

helloJVMBuild := (helloJVM / Docker / publishLocal).value

/*
helloJVMBuild := {
  val s = state.value
  val extracted = Project.extract(s)
  val sn = helloJvm / name := "hello-zio-http-jvm"
  val newState = extracted.appendWithoutSession(Seq(sn), s)
  Project.runTask(helloJvm / Docker / publishLocal, newState)
}

 */

  /*
  val s = (helloGraalVM / state).value
  val extracted = Project.extract(s)
  val newState = extracted.appendWithoutSession(Seq(helloGraalVM / graalVMNativeImageGraalVersion := Some("20.0.0")), s)
  //Project.extract(newState).runTask(GraalVMNativeImage / packageBin, newState)

  //newState.unsafeRunTask()//GraalVMNativeImage / packageBin)

  newState.unsafeRunTask(compile)
  */

  /*
  val newState = extracted.append(Seq(version := "newVersion"), state)
  val (s, _) = Project.extract(newState).runTask(publish in Compile, newState)
  s

  val s = (helloGraalVM / state).value  //.put(graalVMNativeImageGraalVersion.key, Some("20.0.0"))
  */
/*
  println("zcvzxcvzxcv")
  println(newState.setting(helloGraalVM / graalVMNativeImageGraalVersion))
  println(newState.setting(helloGraalVM / graalVMNativeImageOptions))

 */
//  println(s.setting(graalVMNativeImageOptions))


  //.unsafeRunTask(GraalVMNativeImage / packageBin)

  /*
  println(state.get((helloGraalVM / graalVMNativeImageGraalVersion)))
 // (helloJvm / Docker / publishLocal).value
  println((helloGraalVM / graalVMNativeImageGraalVersion).value)
  //(helloGraalVM / GraalVMNativeImage / packageBin).value
   */

//docker:publishLocal

/*
lazy val zhttp = ProjectRef(uri(s"git://github.com/jamesward/zio-http.git"), "zhttp")

dependsOn(zhttp)

enablePlugins(GraalVMNativeImagePlugin)

name := "hello-zio-http"

scalaVersion := "2.13.5"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-explaintypes",
  "-feature",
  "-Wconf:any:error",
  "-Wunused",
  "-Wvalue-discard",
)

libraryDependencies ++= Seq(
  "org.scalameta" %% "svm-subs"     % "20.2.0",
)

Global / sources in (Compile,doc) := Seq.empty
Global / publishArtifact in (Compile, packageDoc) := false

graalVMNativeImageOptions ++= Seq(
  "--verbose",
  "--no-server",
  "--allow-incomplete-classpath",
  "--no-fallback",
  "--static",
  "--install-exit-handlers",
  "--libc=musl",
  "-H:+ReportExceptionStackTraces",
  "-H:+RemoveSaturatedTypeFlows",
  "--initialize-at-run-time=io.netty.channel.epoll.Epoll",
  "--initialize-at-run-time=io.netty.channel.epoll.Native",
  "--initialize-at-run-time=io.netty.channel.epoll.EpollEventLoop",
  "--initialize-at-run-time=io.netty.channel.epoll.EpollEventArray",
  "--initialize-at-run-time=io.netty.channel.DefaultFileRegion",
  "--initialize-at-run-time=io.netty.channel.kqueue.KQueueEventArray",
  "--initialize-at-run-time=io.netty.channel.kqueue.KQueueEventLoop",
  "--initialize-at-run-time=io.netty.channel.kqueue.Native",
  "--initialize-at-run-time=io.netty.channel.unix.Errors",
  "--initialize-at-run-time=io.netty.channel.unix.IovArray",
  "--initialize-at-run-time=io.netty.channel.unix.Limits",
  "--initialize-at-run-time=io.netty.util.internal.logging.Log4JLogger",
)
*/