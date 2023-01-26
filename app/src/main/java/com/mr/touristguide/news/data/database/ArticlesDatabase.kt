package com.mr.touristguide.news.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(ArticleEntity::class)], version = 2, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

//    companion object{
//        @Volatile
//        private var instance: ArticlesDatabase? = null
//
//        fun getInstance(context: Context): ArticlesDatabase{
//            synchronized(this){
//                var temp = instance
//                if(temp==null){
//                    temp = Room.databaseBuilder(
//                        context.applicationContext,
//                        ArticlesDatabase::class.java,
//                        "articles_database"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//                    instance = temp
//                }
//                return temp
//            }
//        }
//    }
}