package code

import code.lib.ObjectIdStringSerializer
import net.liftweb.json._
import org.scalatest.{Inspectors, Matchers, WordSpec}

import scala.reflect.Manifest

class ObjectIdExtractSpec extends WordSpec with Matchers with Inspectors  {
  "Extract" should {
    "return same ObjectId if I don't have an implicit ObjectIdStringSerializer in scope and pass format directly" in {
      val format = DefaultFormats
      val j = JString("57b5b4a321e65efe0b7b5fd5")
      Extraction.extract[org.bson.types.ObjectId](j)(format, Manifest.classType(classOf[org.bson.types.ObjectId])) should be("57b5b4a321e65efe0b7b5fd5")
    }

    "return same ObjectId if I don't have an implicit ObjectIdStringSerializer in scope" in {
      implicit val format = DefaultFormats
      val j = JString("57b5b4a321e65efe0b7b5fd5")
      j.extract[org.bson.types.ObjectId] should be("57b5b4a321e65efe0b7b5fd5")
    }

    "return same ObjectId if I do have an implicit ObjectIdStringSerializer in scope" in {
      implicit val format = DefaultFormats + new ObjectIdStringSerializer
      val j = JString("57b5b4a321e65efe0b7b5fd5")
      j.extract[org.bson.types.ObjectId] should be("57b5b4a321e65efe0b7b5fd5")
    }
  }
}
