package <%= packageName %>

import android.annotation.SuppressLint
import android.content.Context
import com.afzaln.test.data.Prefs

class Injection private constructor(private val appContext: Context) {

    val sharedPrefs: Prefs by lazy {
        Prefs(appContext)
    }

    companion object {

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
