package <%= packageName %>.data

import android.content.Context
import android.content.SharedPreferences
import timber.log.Timber

class Prefs(private val appContext: Context) {

    init {
        Timber.d("Initialized shared prefs")
    }

    var prefString: String
        get() {
            val str = getSharedPrefs().getString(prefString, "")!!
            Timber.d("Getting prefString str: $str")
            return str
        }
        set(value) {
            Timber.d("Setting prefString str to $value")
            getSharedPrefs().edit().putString(PREF_STRING, value).apply()
        }

    private fun getSharedPrefs(): SharedPreferences {
        return appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        const val PREFS_NAME = "prefString_prefs"
        const val PREF_STRING = "prefString"
    }
}
