package sk.upjs.ics.presentr2017;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("available-users/{userName}")
    Call<Void> register(@Path("username") String username);

    @GET("available-users")
    Call<List<User>> getUsers();
}
