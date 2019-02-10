package <%= packageName %>.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import <%= packageName %>.data.entities.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE id LIKE :id LIMIT 1")
    fun getById(id: Long): Item

    @Insert(onConflict = REPLACE)
    fun insert(item: Item): Long

    // Not sure where this would be useful
    @Insert
    fun insertAll(itemss: List<Item>)

    @Update(onConflict = REPLACE)
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM item")
    fun count(): Int
}
