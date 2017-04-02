package sk.upjs.ics.presentr2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView userListView;

    private ArrayAdapter<User> userListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<User> users = Arrays.asList(new User("john"), new User("suzanne"), new User("ringo"), new User("helen"));
        this.userListViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);

        this.userListView = (ListView) findViewById(R.id.userListView);
        this.userListView.setAdapter(this.userListViewAdapter);
    }

    public void onFloatingActionButtonClick(View view) {
        String userName = "android";
        new IAmHereAsyncTask().execute(userName);
    }
}
