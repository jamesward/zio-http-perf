import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.GenericContainer

class HelloSimulation extends Simulation {

  class JvmContainer extends GenericContainer[JvmContainer]("jvm-server")

  val jvmContainer = new JvmContainer().withExposedPorts(8080)

  jvmContainer.start()

  val httpProtocol = http.baseUrl(s"http://${jvmContainer.getHost}:${jvmContainer.getFirstMappedPort}")

  val scn = scenario("hello-jvm").exec(http("get").get("/"))

  val numUsers = 1000

  setUp(scn.inject(atOnceUsers(numUsers)).protocols(httpProtocol))

  after {
    jvmContainer.stop()
  }

}