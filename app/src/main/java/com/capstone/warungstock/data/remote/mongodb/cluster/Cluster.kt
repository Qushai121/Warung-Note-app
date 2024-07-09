package com.capstone.warungstock.data.remote.mongodb.cluster


//class Cluster {
//    data class Movie(val title: String, val year: Int, val cast: List<String>)
//
//    fun main() {
//        // Replace the placeholder with your MongoDB deployment's connection string
//        val uri = "mongodb+srv://Qushai:kanekeni121@cluster0.jg4hf1l.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
//
//        val mongoClient = MongoClient.create(uri)
//        val database = mongoClient.getDatabase("sample_mflix")
//        // Get a collection of documents of type Movie
//        val collection = database.getCollection<Movie>("movies")
//
//        runBlocking {
//            val doc = collection.find(eq("title", "Back to the Future")).firstOrNull()
//            if (doc != null) {
//                Log.i("mongo",doc.toString())
//            } else {
//                Log.i("mongo","No matching documents found.")
//            }
//        }
//
//        mongoClient.close()
//    }
//
//
//}