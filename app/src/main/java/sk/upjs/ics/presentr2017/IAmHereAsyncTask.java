package sk.upjs.ics.presentr2017;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;

public class IAmHereAsyncTask extends AsyncTask<String, Void, Boolean> {
    public static final String TAG = IAmHereAsyncTask.class.getCanonicalName();

    @Override
    protected Boolean doInBackground(String... userNames) {
        try {
            if (userNames == null || userNames.length != 1) {
                throw new IllegalArgumentException("A single user name must be provided");
            }
            String userName = userNames[0];

            Api api = RetrofitFactory.getApi();
            Call<Void> call = api.register(userName);
            int code = call.execute().code();
            return code == 200;
        } catch (IOException e) {
            return false;
        }
    }

}
