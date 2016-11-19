package <%= appPackage %>.data.source.remote;

import <%= appPackage %>.data.models.Post;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Retrofit HTTP API Interface
 * https://square.github.io/retrofit/
 */

public interface RemoteApiService {
    /**
     * Get 100 posts
     *
     * @return RxObservable
     */
    @GET("/posts")
    Observable<List<Post>> getPosts();
}
