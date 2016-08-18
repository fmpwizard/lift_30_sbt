
Issue with lift json extract
=======

We wrote a custom json Serializer for Mongo's ObjectId, so that if we got a String("id here") we could call

payload.extract[ObjectId] and get a ObjectId("value here")

this works well as long as we have the correct implicit in scope, but if I forget to include it, instead of getting an exception, I simply get a fake objectId value.
