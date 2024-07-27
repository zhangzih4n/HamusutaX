package hamusutax.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface BaseDao<T : Any> {
    @Insert
    suspend fun insert(item: T)

    @Update
    suspend fun update(item: T)

    @Upsert
    suspend fun upsert(item: T)

    @Delete
    suspend fun delete(item: T)

    @Insert
    suspend fun insertAll(items: List<T>)

    @Update
    suspend fun updateAll(items: List<T>)

    @Upsert
    suspend fun upsertAll(items: List<T>)

    @Delete
    suspend fun deleteAll(items: List<T>)
}
