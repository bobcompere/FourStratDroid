package com.fourstrategery.fourstratdroid;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fourstrategery.fourstratdroid.model.GameActivity;
import com.fourstrategery.fourstratdroid.model.GameInfoResponse;
import com.fourstrategery.fourstratdroid.model.GameStatusModel;
import com.fourstrategery.fourstratdroid.model.Player;
import com.fourstrategery.fourstratdroid.model.PlayerDetail;
import com.fourstrategery.fourstratdroid.task.GameStatusTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class GameStatusActivity extends ActionBarActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_status);

        GameStatusTask task = new GameStatusTask(getApplicationContext(),this,getIntent().getIntExtra("gameId",-1));
        task.execute((Void) null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_status, menu);
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

    public void dataReceived(GameInfoResponse gir) {
        TextView gameName = (TextView)findViewById(R.id.gameDescription);

        GameStatusModel gsm = gir.getGameStatusModel();
        gameName.setText((CharSequence) gsm.getGame().getDescription());

        fillPlayerGrid(gsm.getPlayers(), gsm.getPlayerDetails());
        fillUnitsGrid();
        fillActivityGrid(gsm.getActivities());
    }

    private void fillPlayerGrid(List<Player> players, List<PlayerDetail> playerDetails) {
        TableLayout table = (TableLayout) findViewById(R.id.playerList);
        table.setStretchAllColumns(true);

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f));
        addText(row, "screenName", -1, "Screen Name");
        addText(row, "firstName", -1, "First Name");
        addText(row, "lastName", -1, "Last Name");
        addText(row, "units", -1, "Active Units");
        addText(row, "score", -1, "Score");
        table.addView(row);

        for (int i1=0;i1<players.size();i1++) {
            Player player = players.get(i1);
            PlayerDetail playerDetail = playerDetails.get(i1);

            row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f));

            addText(row, "screenName", i1, playerDetail.getScreenName());
            addText(row, "firstName", i1, playerDetail.getFirstName());
            addText(row, "lastName", i1, playerDetail.getLastName());
            addText(row, "units", i1, player.getActiveUnitCount() + "");
            addText(row, "score", i1, player.getScore() + "");

            table.addView(row);
        }
    }

    private void addText(TableRow row, String fieldName ,int rowNum, String value) {
        addText(row,fieldName,rowNum,value,false);
    }

    private void addText(TableRow row, String fieldName ,int rowNum, String value, boolean wrap) {
        TextView textView = new TextView(this);
        textView.setText(value);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(3, 3, 3, 3);
        textView.setBackgroundResource(R.drawable.cell_shape);
        if (wrap) {
            textView.setHorizontallyScrolling(false);
            textView.setMaxWidth((int) ((getApplicationContext().getResources().getDisplayMetrics()).widthPixels * 0.50));
        }
        row.addView(textView);
    }

    private void fillUnitsGrid() {
        TableLayout table = (TableLayout) findViewById(R.id.myUnits);

        TableRow testRow = new TableRow(this);

        TextView testText = new TextView(this);
        testText.setText("U-N-I-T-S");
        testText.setTextColor(Color.BLACK);

        testRow.addView(testText);
        table.addView(testRow);
    }

    private void fillActivityGrid(List<GameActivity> activities) {
        TableLayout table = (TableLayout) findViewById(R.id.activityList);
        table.setStretchAllColumns(true);

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
        addText(row, "createdOn", -1, "When");
        addText(row, "message", -1, "Message");
        table.addView(row);

        for (int i1=0;i1<activities.size();i1++) {
            GameActivity activity = activities.get(i1);
            row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            addText(row, "createdOn", i1, sdf.format(new Date(activity.getCreatedOn())));
            addText(row, "message", i1, activity.getMessage(),true);
            table.addView(row);
        }
    }
}
