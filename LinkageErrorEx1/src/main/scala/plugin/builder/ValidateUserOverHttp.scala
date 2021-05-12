package plugin.builder

import spray.json._
import DefaultJsonProtocol._

trait ValidateUserOverHttp {

  def validateUserOverHttp(name : String) : User = {


    //rest call over network like http
    val fetchedJsonOverHttp = """{ "name": "John" }"""

    implicit val userJsonFormat = jsonFormat1(User)
    val user = fetchedJsonOverHttp.parseJson.convertTo[User]
    user
  }
}
