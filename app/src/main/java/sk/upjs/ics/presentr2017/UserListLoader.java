package sk.upjs.ics.presentr2017;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class UserListLoader extends AbstractObjectLoader<List<User>> {
    public static final String TAG = UserListLoader.class.getCanonicalName();

    public UserListLoader(Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {
        try {
            Api api = RetrofitFactory.getApi();
            Call<List<User>> call = api.getUsers();
            return call.execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Unable to load user", e);
            return Collections.emptyList();
        }
    }
}
