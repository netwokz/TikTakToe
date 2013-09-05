package com.netwokz.tiktaktoe;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by Stephen on 9/4/13.
 */
public class StatsFragment extends DialogFragment {

    long mTotalGamesPlayed;
    long mTotalGameTime;
    long mGamesWon;
    long mGamesLost;
    long mGamesTie;

    SharedPreferences mPrefs;
    SharedPreferences.Editor mPrefsEditor;

    private final String STAT_ALL_TIME_GAMES_PLAYED = "all_time_games_played";
    private final String STAT_ALL_TIME_GAMES_WON = "all_time_games_won";
    private final String STAT_ALL_TIME_GAMES_LOST = "all_time_games_lost";
    private final String STAT_ALL_TIME_TIME_PLAYED = "all_time_time_played";
    private final String STAT_ALL_TIME_GAMES_TIE = "all_time_games_tie";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.stats, null);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext());
        mPrefsEditor = mPrefs.edit();


    }

    public void getStats() {
        mTotalGamesPlayed = mPrefs.getLong(STAT_ALL_TIME_GAMES_PLAYED, -1);
        mTotalGameTime = mPrefs.getLong(STAT_ALL_TIME_GAMES_WON, -1);
        mGamesWon = mPrefs.getLong(STAT_ALL_TIME_GAMES_LOST, -1);
        mGamesLost = mPrefs.getLong(STAT_ALL_TIME_TIME_PLAYED, -1);
        mGamesTie = mPrefs.getLong(STAT_ALL_TIME_GAMES_TIE, -1);
    }
}
