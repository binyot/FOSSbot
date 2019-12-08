package com.miemdynamics.fossbot.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miemdynamics.fossbot.data.entity.Program


/**
 * An Android Room database of [Program]s
 */
@Database(
    entities = [Program::class],
    version = 1,
    exportSchema = false
)
abstract class ProgramDatabase: RoomDatabase() {
    abstract fun programDao(): ProgramDao

    companion object {
        @Volatile private var instance: ProgramDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ProgramDatabase::class.java, "program.db")
                .build()
    }
}