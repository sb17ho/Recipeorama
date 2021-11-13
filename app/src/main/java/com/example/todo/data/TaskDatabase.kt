package com.example.todo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Task::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null

        fun getTaskDatabase(context: Context): TaskDatabase {
            val tempDatabase = INSTANCE

            if (tempDatabase != null) {
                return tempDatabase
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).addMigrations(migration_1_2, migration_2_3).build()
                INSTANCE = instance
                return instance
            }
        }

        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo_task ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE todo_task ADD COLUMN isTrash INTEGER NOT NULL DEFAULT 0")
            }
        }

        private val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo_task ADD COLUMN dd INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE todo_task ADD COLUMN mm INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE todo_task ADD COLUMN yy INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}