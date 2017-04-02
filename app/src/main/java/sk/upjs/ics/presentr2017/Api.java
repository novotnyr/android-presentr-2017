package sk.upjs.ics.presentr2017;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("available-users")
    Call<Void> register();

    @GET("available-users")
    Call<List<User>> getUsers();
}
