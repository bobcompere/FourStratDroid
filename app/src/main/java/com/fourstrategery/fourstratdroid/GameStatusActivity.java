package com.fourstrategery.fourstratdroid;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.fourstrategery.fourstratdroid.model.GameActivity;
import com.fourstrategery.fourstratdroid.model.GameInfoResponse;
import com.fourstrategery.fourstratdroid.model.GameStatusModel;
import com.fourstrategery.fourstratdroid.model.Player;
import com.fourstrategery.fourstratdroid.model.PlayerDetail;
import com.fourstrategery.fourstratdroid.model.Unit;
import com.fourstrategery.fourstratdroid.model.Venue;
import com.fourstrategery.fourstratdroid.task.GameStatusTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GameStatusActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

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

        String moveMessage = gir.getMoveMessage();
        if (moveMessage != null) {
            Toast raisin;
            int duration = Toast.LENGTH_LONG;
            raisin = Toast.makeText(getApplicationContext(),moveMessage,duration);
            raisin.show();
        }

        fillPlayerGrid(gsm.getPlayers(), gsm.getPlayerDetails());
        fillUnitsGrid(gsm.getMyUnits(), gsm.getMyPlayerNumber(), gsm.getVenues(), gsm.getPlayers(), gsm.getPlayerDetails(),gsm.getGame().getId());
        fillActivityGrid(gsm.getActivities());
    }

    private void fillPlayerGrid(List<Player> players, List<PlayerDetail> playerDetails) {
        TableLayout table = (TableLayout) findViewById(R.id.playerList);
        table.removeAllViews();
        table.setStretchAllColumns(true);

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f));
        addText(row, "screenName", -1, "Screen Name");
        addText(row, "firstName", -1, "First Name");
        addText(row, "lastName", -1, "Last Name");
        addText(row, "units", -1, "Units");
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
        addText(row, fieldName, rowNum, value, false, -1);
    }

    private void addText(TableRow row, String fieldName ,int rowNum, String value, boolean wrap, double maxWidth) {
        TextView textView = new TextView(this);
        textView.setText(value);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(3, 3, 3, 3);
        textView.setBackgroundResource(R.drawable.cell_shape);
        if (wrap) {
            textView.setHorizontallyScrolling(false);
        }
        if (maxWidth != -1) {
            textView.setMaxWidth((int) ((getApplicationContext().getResources().getDisplayMetrics()).widthPixels * maxWidth));
        }
        row.addView(textView);
    }

    private void fillUnitsGrid(List<Unit> units, int myPlayerNumber, List<Venue> venues,List<Player> players, List<PlayerDetail> playerDetails, int gameId) {
        TableLayout table = (TableLayout) findViewById(R.id.myUnits);

        table.removeAllViews();
        TableRow headerRow = new TableRow(this);

        addText(headerRow,"Name",-1,"Name");
        addText(headerRow,"Troops",-1,"Troops");
        addText(headerRow,"Status", -1,"Status");

        table.addView(headerRow);

        for (int i1=0;i1<units.size();i1++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            Unit unit = units.get(i1);
            addText(row, "name", i1, unit.getName(),true,.30);
            addText(row,"troops",i1,unit.getTroops() +"",false,.20);

            Venue venue = null;
            int venuePos = -1;
            for (int i2=0;i2<venues.size();i2++) {
                venue = venues.get(i2);
                venuePos = i2;
                if (venue.getId().equals(unit.getVenueId())) break;
            }

            if (unit.getStatus().equals("GARRISONED")) {
                Spinner spinner = new Spinner(this,Spinner.MODE_DIALOG);
                spinner.setPrompt(unit.getName() + " " + unit.getTroops() + " Troops");
                spinner.setId(unit.getId());

                List<MoveInfo> values = new ArrayList<MoveInfo>();
                values.add(new MoveInfo(unit.getStatus() + " : " + venue.getName(), -1, null,gameId));

                for (int i2=0;i2<venues.size();i2++) {
                    Venue venue1 = venues.get(i2);
                    if (venue1.getId().equals(unit.getVenueId())) continue;

                    String occupant = "none";
                    int distance = -1;
                    if (venue1.getCurrentUnitPlayerNumber() == myPlayerNumber) {
                        occupant = "you";
                    }
                    else {
                        if (venue1.getCurrentUnitPlayerNumber() == 0) {
                            occupant = "none";
                        } else {
                            occupant = playerDetails.get(venue1.getCurrentUnitPlayerNumber() - 1).getScreenName();
                        }
                    }

                    distance = venue1.getDistances()[venuePos];

                    values.add(new MoveInfo("Move to: " + venue1.getName() + ", occupied by " + occupant + ", Distance " + distance + " miles",unit.getId(),venue1.getId(),gameId));
                }
                ArrayAdapter<MoveInfo> dataAdapter = new ArrayAdapter<MoveInfo>(this,
                        R.layout.multiline_spinner, values);
             
                dataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);
                spinner.setBackgroundResource(R.drawable.cell_shape);
                spinner.setOnItemSelectedListener(this);

               row.addView(spinner);
               // addText(row, "status", i1, unit.getStatus() + " : " + venue.getName(),true,.5);
            } else {
                addText(row, "status", i1, unit.getStatus() + " : " + venue.getName(),true,.5);
            }
            table.addView(row);
        }
    }

    private void fillActivityGrid(List<GameActivity> activities) {
        TableLayout table = (TableLayout) findViewById(R.id.activityList);
        table.removeAllViews();
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
            addText(row, "createdOn", i1, sdf.format(new Date(activity.getCreatedOn())),true,0.25);
            addText(row, "message", i1, activity.getMessage(),true,0.75);
            table.addView(row);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;
        MoveInfo moveInfo = (MoveInfo) spinner.getAdapter().getItem(position);
        if (moveInfo.getVenueId() != null) { // not movivg
            GameStatusTask gst = new GameStatusTask(getApplicationContext(), this, moveInfo.getGameId(), moveInfo);
            gst.execute();
        }
        System.out.println(moveInfo);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

