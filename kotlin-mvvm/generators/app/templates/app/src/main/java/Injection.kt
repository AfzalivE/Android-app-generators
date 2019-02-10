package <%= packageName %>

import android.annotation.SuppressLint
import android.content.Context
<% if (locals.addRoom) { -%>
import androidx.room.Room
import <%= packageName %>.data.ItemDataStore
import <%= packageName %>.data.ItemDatabase
<% } -%>
import <%= packageName %>.data.Prefs

class Injection private constructor(private val appContext: Context) {

<% if (locals.addRoom) { -%>
    private val itemDatabase: ItemDatabase by lazy {
        Room.databaseBuilder(appContext, ItemDatabase::class.java, DATABASE_FILE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
<% } -%>

    val sharedPrefs: Prefs by lazy {
        Prefs(appContext)
    }

<% if (locals.addRoom) { -%>
    val itemDataStore: ItemDataStore by lazy {
        ItemDataStore(itemDatabase)
    }
<% } -%>

    companion object {
<% if (locals.addRoom) { -%>
        private const val DATABASE_FILE_NAME = "item_db"
<% } -%>

        @SuppressLint("StaticFieldLeak", "Keeping app context is safe here")
        @Volatile
        private lateinit var INSTANCE: Injection

        fun init(appContext: Context) {
            INSTANCE = Injection(appContext)
        }

        fun get(): Injection {
            return INSTANCE
        }
    }
}
