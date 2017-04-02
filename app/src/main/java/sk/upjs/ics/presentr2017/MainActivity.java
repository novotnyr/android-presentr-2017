package sk.upjs.ics.presentr2017;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<User>> {
    private ListView userListView;

    private ArrayAdapter<User> userListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userListViewAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1);

        this.userListView = (ListView) findViewById(R.id.userListView);
        this.userListView.setAdapter(this.userListViewAdapter);

        getLoaderManager().restartLoader(0, Bundle.EMPTY, this);
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
}
