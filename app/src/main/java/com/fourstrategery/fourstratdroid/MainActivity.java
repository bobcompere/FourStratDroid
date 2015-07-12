package com.fourstrategery.fourstratdroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity implements GameListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SaveSharedPreference.getUser(getApplicationContext()) == -1) {
            Intent login = new Intent(getBaseContext(),LoginActivity.class);

            startActivity(login);
        }

        setContentView(R.layout.activity_main);

        installGameList();

     }


    private void installGameList() {
        if (findViewById(R.id.SubScreen) != null) {
            GameListFragment gameFrag = new GameListFragment();
            gameFrag.setArguments(getIntent().getExtras());

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
