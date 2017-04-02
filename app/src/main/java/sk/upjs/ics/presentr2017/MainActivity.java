package sk.upjs.ics.presentr2017;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<User>>, SwipeRefreshLayout.OnRefreshListener {
    private ListView userListView;

    private ArrayAdapter<User> userListViewAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RefreshUsersBroadcastReceiver refreshUsersBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userListViewAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1);

        this.userListView = (ListView) findViewById(R.id.userListView);
        this.userListView.setAdapter(this.userListViewAdapter);

        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        getLoaderManager().restartLoader(0, Bundle.EMPTY, this);

        PresenceScheduler.schedule(this);

        this.refreshUsersBroadcastReceiver = new RefreshUsersBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter("PRESENTR_REFRESH_USERS");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.refreshUsersBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.refreshUsersBroadcastReceiver);

        super.onPause();
    }

    public void onFloatingActionButtonClick(View view) {
        String userName = "android";
        new IAmHereAsyncTask().execute(userName);
    }

    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        return new UserListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> users) {
        this.userListViewAdapter.clear();
        this.userListViewAdapter.addAll(users);
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {
        this.userListViewAdapter.clear();
    }

    @Override
    public void onRefresh() {
        this.getLoaderManager().restartLoader(0, Bundle.EMPTY, this);
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private class RefreshUsersBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<User> users = (List<User>) intent.getSerializableExtra("USERS");
            MainActivity.this.userListViewAdapter.clear();
            MainActivity.this.userListViewAdapter.addAll(users);
        }
    }
}
