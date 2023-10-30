package geneziokotlin

import com.google.gson.Gson
import com.mongodb.*
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlin.random.Random


@Serializable
data class Cat_Fact(
            var fact:String,
            var length:Int
            )
class TaskService {
    val uri = "mongodb+srv://Sorzys:matamata4521@cluster0.ud1qpdn.mongodb.net/?retryWrites=true&w=majority"
    var collection:MongoCollection<Cat_Fact>
    var gson=Gson()
    init {
        runBlocking {
            val serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build()
            val settings = MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(uri))
                .serverApi(serverApi)
                .build()
            val mongoClient = MongoClient.create(settings)
            val database = mongoClient.getDatabase("CatFacts")

            collection=database.getCollection<Cat_Fact>("CatFacts")
        }
    }

    fun fetchFact(): String {
        var res: String
        runBlocking {
            val Facts=ArrayList<Cat_Fact>(collection.find<Cat_Fact>().toList())
            res=gson.toJson(Facts[Random.nextInt(0,Facts.size)])

        }
        return res
    }


}
