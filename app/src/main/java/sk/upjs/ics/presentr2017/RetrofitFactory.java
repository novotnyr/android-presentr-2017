package sk.upjs.ics.presentr2017;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitFactory {
    public static Retrofit getRetrofit() {
        String baseUrl = "http://ics.upjs.sk/~novotnyr/android/demo/presentr/index.php/";

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static Api getApi() {
        return getRetrofit().create(Api.class);
    }
}
