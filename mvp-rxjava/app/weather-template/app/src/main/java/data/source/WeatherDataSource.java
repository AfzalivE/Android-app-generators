package <%= appPackage %>.data.source;

import java.util.List;

import <%= appPackage %>.data.Search;
import <%= appPackage %>.data.Weather;
import rx.Observable;

/**
 * Created by afzal on 2016-06-04.
 */
public interface WeatherDataSource {
    Observable<Weather> getWeather(Search search);
    void deleteAllRecentSearches();
    void saveRecentSearch(Search search);
    Observable<? extends List<Search>> getRecentSearches();
    void deleteRecentSearch(long timestamp);
}
