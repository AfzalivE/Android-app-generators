package <%= packageName %>.data

import androidx.room.Database
import androidx.room.RoomDatabase
import <%= packageName %>.data.dao.ItemDao
import <%= packageName %>.data.entities.Item

/**
 * Database to store items
 */
@Database(entities = [Item::class], version = 1, exportSchema = true)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
