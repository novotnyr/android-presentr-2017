package sk.upjs.ics.presentr2017;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class UserListLoaderService extends IntentService {
    public static final String TAG = UserListLoaderService.class.getName();

    public UserListLoaderService() {
        super(UserListLoaderService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent == null) {
            return;
        }
        try {
            Api api = RetrofitFactory.getApi();
            Call<List<User>> call = api.getUsers();
            List<User> users = call.execute().body();
            triggerNotification(users);
        } catch (IOException e) {
            Log.e(TAG, "Unable to load user", e);
        }
    }

    @SuppressWarnings("deprecation")
    private void triggerNotification(List<User> users) {
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Presentr")
                .setContentText("There are " + users.size() + " people in the room")
                .setSmallIcon(R.mipmap.ic_launcher)
                .getNotification();

        NotificationManager notificationManager
                = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
