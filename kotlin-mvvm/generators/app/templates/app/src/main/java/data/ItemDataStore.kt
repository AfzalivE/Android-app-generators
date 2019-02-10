package <%= packageName %>.data

import <%= packageName %>.data.ItemDatabase
import <%= packageName %>.data.entities.Item
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Store for the user's items
 */
class ItemDataStore(private val database: ItemDatabase) {
    val items: ArrayList<Item> = ArrayList()

    suspend fun getAllItems(): List<Item> {
        return withContext(IO) {
            database.itemDao().getAll()
        }
    }

    /**
     * Used for creating a new item
     */
    suspend fun addItem(item: Item): Long {
        return withContext(IO) {
            val itemId = database.itemDao().insert(item)
            return@withContext itemId
        }
    }

    /**
     * Used for creating a new item
     */
    suspend fun deleteItem(item: Item) {
        return withContext(IO) {
            database.itemDao().delete(item)
        }
    }

    /**
     * Used for changing core item properties
     * like name
     */
    fun updateItem(item: Item) {
        // TODO if name changed, insert a christening event
        // TODO if photo added/changed, insert a photo event
        database.itemDao().update(item)
    }

    suspend fun getItem(id: Long): Item {
        return withContext(IO) {
            database.itemDao().getById(id)
        }
    }
}
