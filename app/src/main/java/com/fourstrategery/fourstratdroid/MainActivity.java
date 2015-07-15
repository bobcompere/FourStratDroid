package com.fourstrategery.fourstratdroid;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;


public class MainActivity extends FragmentActivity implements GameListFragment.OnFragmentInteractionListener,AdapterView.OnItemClickListener {

    private final int CURRENT_GAMES = 0;
    private final int COMPLETED_GAMES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SaveSharedPreference.getUser(getApplicationContext()) == -1) {
            Intent login = new Intent(getBaseContext(),LoginActivity.class);

            startActivity(login);
        }

        setContentView(R.layout.activity_main);

        String[] commands = {"Current","Completed","NEW"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1, android.R.id.text1,commands);


        // Assign adapter to ListView
        ListView listView = (ListView) findViewById(R.id.commandList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(this);

        installGameList(CURRENT_GAMES);

     }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        if (l.getId() == R.id.commandList){
            if (position == 0) {
                installGameList(CURRENT_GAMES);
            }
            if (position == 1) {
                installGameList(COMPLETED_GAMES);
            }
        }

    }


    private void installGameList(int status) {
        if (findViewById(R.id.SubScreen) != null) {
            GameListFragment gameFrag = new GameListFragment();
            gameFrag.setStatus(status);
            gameFrag.setArguments(getIntent().getExtras());
            FrameLayout frame = (FrameLayout) findViewById(R.id.SubScreen);
            frame.removeAllViews();
           getSupportFragmentManager().beginTransaction().add(R.id.SubScreen, gameFrag).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
