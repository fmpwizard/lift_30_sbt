package code
package lib

import net.liftweb.json._
import net.liftweb.mongodb.record._
import org.bson.types.ObjectId

class ObjectIdStringSerializer extends Serializer[ObjectId] {
  private val ObjectIdClass = classOf[ObjectId]

  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ObjectId] = {
    case (TypeInfo(ObjectIdClass, _), json) => json match {
      case JString(s) if (ObjectId.isValid(s)) =>
        new ObjectId(s)
      case x => throw new MappingException("Can't convert " + x + " to ObjectId")
    }
  }

  def serialize(implicit formats: Formats): PartialFunction[Any, JValue] = {
    case x: ObjectId => JString(x.toString)
  }
}
