package com.miemdynamics.fossbot.data.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miemdynamics.fossbot.data.entity.Program

/**
 * A DAO for [Program]
 * Methods that return LiveData are not [suspend]
 */
@Dao
interface ProgramDao {
    /**
     * Insert a [program] into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(program: Program)

    /**
     * @return a live list of all programs in the database
     */
    @Query("select * from program_table")
    fun getAllLive(): LiveData<List<Program>>

    /**
     * @return a list of all programs in the database
     */
    @Query("select * from program_table")
    fun getAll(): List<Program>

    /**
     * @return a program with the [name]
     */
    @Query("select * from program_table where name = :name")
    fun getByName(name: String): Program?

    /**
     * Delete all programs in the database
     */
    @Query("delete from program_table")
    suspend fun deleteAll()

    /**
     * Delete program with the [name] from the database
     * @param name a name of the program to be deleted
     */
    @Query("delete from program_table where name = :name")
    suspend fun deleteByName(name: String)
}