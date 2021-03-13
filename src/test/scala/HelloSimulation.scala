import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait

object HelloSimulation {
  val numUsers = 20000
}

class HelloJVMSimulation extends Simulation {

  class JvmContainer extends GenericContainer[JvmContainer]("hello-zio-http-jvm")

  val jvmContainer = new JvmContainer()
    .waitingFor(Wait.forHttp("/"))
    .withExposedPorts(8080)

  jvmContainer.start()

  val httpProtocol = http.baseUrl(s"http://${jvmContainer.getHost}:${jvmContainer.getFirstMappedPort}")

  val scn = scenario("hello-jvm").exec(http("get").get("/"))

  setUp(scn.inject(atOnceUsers(HelloSimulation.numUsers)).protocols(httpProtocol))

  after {
    jvmContainer.stop()
  }

}

class HelloGraalVMSimulation extends Simulation {

  class GraalVMContainer extends GenericContainer[GraalVMContainer]("hello-zio-http-graalvm")

  val graalVMContainer = new GraalVMContainer()
    .waitingFor(Wait.forHttp("/"))
    .withExposedPorts(8080)

  graalVMContainer.start()

  val httpProtocol = http.baseUrl(s"http://${graalVMContainer.getHost}:${graalVMContainer.getFirstMappedPort}")

  val scn = scenario("hello-graalvm").exec(http("get").get("/"))

  setUp(scn.inject(atOnceUsers(HelloSimulation.numUsers)).protocols(httpProtocol))

  after {
    graalVMContainer.stop()
  }

}