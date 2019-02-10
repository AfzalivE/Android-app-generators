package <%= packageName %>.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class to hold Item info
 */
@Entity
data class Item(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
