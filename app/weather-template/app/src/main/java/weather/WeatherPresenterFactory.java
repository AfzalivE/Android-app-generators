package <%= appPackage %>.weather;

import android.content.Context;

import <%= appPackage %>.Injection;
import <%= appPackage %>.util.PresenterFactory;

/**
 * Factory to create a presenter with all the parameters
 */
public class WeatherPresenterFactory extends PresenterFactory<WeatherPresenter> {
    @Override
    public WeatherPresenter create(Context context) {
        return new WeatherPresenter(
                Injection.provideWeatherRepository(context.getApplicationContext()),
                Injection.provideLocationProvider(context.getApplicationContext())
        );
    }

}
