package geneziokotlin

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mongodb.*
import com.mongodb.client.result.InsertOneResult
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.bson.BsonInt64
import org.bson.BsonValue
import org.bson.Document
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import kotlin.random.Random



@Serializable
data class Cat_Fact(
            var Fact:String,
            var length:Int
            )
class TaskService {
    val mapper = jacksonObjectMapper()
    val Cat_Facts=ArrayList<Cat_Fact>()
    init {
        runBlocking {
            for (i in 1..9) {
                val inter=Cat_Fact(i.toString(),i)
                Cat_Facts.add(inter)
            }
        }
    }

    fun fetchFact(): String {
        var res: String

        runBlocking{

            res=mapper.writeValueAsString(Cat_Facts[Random.nextInt(0,Cat_Facts.size)])

        }
        return res
    }


}
