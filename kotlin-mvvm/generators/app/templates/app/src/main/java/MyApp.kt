package <%= packageName %>

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.beta.Beta
import io.fabric.sdk.android.Fabric
import timber.log.Timber

/**
 * App-wide initializations
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Injection.init(this)
        Fabric.with(this, Beta(), Crashlytics())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
