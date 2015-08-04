package com.fourstrategery.fourstratdroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fourstrategery.fourstratdroid.task.GameListTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameListFragment extends Fragment implements AdapterView.OnItemClickListener {


    private int status = 0;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameListFragment newInstance(String param1, String param2) {
        GameListFragment fragment = new GameListFragment();

        return fragment;
    }

    public GameListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameListTask task = new GameListTask(getActivity().getApplicationContext(),this,status);
        task.execute((Void) null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_game_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.gamelist,R.id.list_content,
                new ArrayList<String>());
        // Assign adapter to ListView
        ListView listView = (ListView) getView().findViewById(R.id.gameList);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.invalidateViews();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void listReceived(JSONObject result) {
        try {
            ListView listView = (ListView) getView().findViewById(R.id.gameList);

            JSONArray games = result.getJSONArray("games");
            GameListItem[] sgames = new GameListItem[games.length()];
            for (int i1=0;i1<games.length();i1++) {
                sgames[i1] = new GameListItem(games.getJSONObject(i1).getString("description"),games.getJSONObject(i1).getInt("gameId"));
            }

            ArrayAdapter<GameListItem> adapter = (ArrayAdapter<GameListItem>) listView.getAdapter();
            adapter.clear();
            adapter.addAll(sgames);

            // Assign adapter to ListView

            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setOnItemClickListener(this);

        }
        catch (JSONException je) {
            //
            //
        }
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("GameListView", "You clicked Item: " + id + " at position:" + position);

        Log.i("GameListView", l.getItemAtPosition(position).toString());

        GameListItem gli = (GameListItem) l.getItemAtPosition(position);
        Intent gameStatus = new Intent(getView().getContext(),GameStatusActivity.class);
        gameStatus.putExtra("gameId", gli.id);

        startActivity(gameStatus);
    }


    public void listCanceled() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

class GameListItem {
    String name;
    int id;

    GameListItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}