package com.netwokz.tiktaktoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Stephen on 9/4/13.
 */
public class StatsDialog extends DialogFragment {

    TextView mTotalPlayedView;
    TextView mTotalTimeView;
    TextView mTotalGamesWonView;
    TextView mTotalGamesLostView;
    TextView mTotalGamesTieView;

    SharedPreferences mPrefs;
    SharedPreferences.Editor mPrefsEditor;

    long mGamesPlayed;
    long mGameTime;
    long mGamesWon;
    long mGamesLost;
    long mGamesTie;

    private final String STAT_ALL_TIME_GAMES_PLAYED = "all_time_games_played";
    private final String STAT_ALL_TIME_GAMES_WON = "all_time_games_won";
    private final String STAT_ALL_TIME_GAMES_LOST = "all_time_games_lost";
    private final String STAT_ALL_TIME_TIME_PLAYED = "all_time_time_played";
    private final String STAT_ALL_TIME_GAMES_TIE = "all_time_games_tie";

    public static StatsDialog newInstance() {
        StatsDialog fragment = new StatsDialog();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.stats, null);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPrefsEditor = mPrefs.edit();

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());
        initializeViews(view);
        getStats();
        updateStatViews();
        mDialogBuilder.setView(view);
        return mDialogBuilder.create();
    }

    private void initializeViews(View view) {
        mTotalPlayedView = (TextView) view.findViewById(R.id.total_games);
        mTotalTimeView = (TextView) view.findViewById(R.id.total_time_value);
        mTotalGamesWonView = (TextView) view.findViewById(R.id.total_games_won);
        mTotalGamesLostView = (TextView) view.findViewById(R.id.total_games_lost);
        mTotalGamesTieView = (TextView) view.findViewById(R.id.total_games_tied);
    }

    public void getStats() {
        mGamesPlayed = mPrefs.getLong(STAT_ALL_TIME_GAMES_PLAYED, 0);
        mGamesWon = mPrefs.getLong(STAT_ALL_TIME_GAMES_WON, 0);
        mGamesLost = mPrefs.getLong(STAT_ALL_TIME_GAMES_LOST, 0);
        mGameTime = mPrefs.getLong(STAT_ALL_TIME_TIME_PLAYED, 0);
        mGamesTie = mPrefs.getLong(STAT_ALL_TIME_GAMES_TIE, 0);
    }

    public void updateStatViews() {
        mTotalPlayedView.setText(String.valueOf(mGamesPlayed));
        mTotalTimeView.setText(formatTime(mGameTime));
        mTotalGamesWonView.setText(String.valueOf(mGamesWon));
        mTotalGamesLostView.setText(String.valueOf(mGamesLost));
        mTotalGamesTieView.setText(String.valueOf(mGamesTie));
    }

    /**
     * Convert a millisecond duration to a string format
     *
     * @param timeInMillis A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    static String formatTime(long timeInMillis) {
        long hours = timeInMillis / (1000 * 60 * 60);
        long minutes = timeInMillis / (1000 * 60) % 60;
        long days = hours / 24;
        long seconds = timeInMillis / 1000;
        hours = hours % 24;

        String daySeq = null;
        if (days == 0) {
            daySeq = "";
        } else if (days == 1) {
            daySeq = Long.toString(days) + " day, ";
        } else if (days > 1) {
            daySeq = Long.toString(days) + " days, ";
        }

        String hourSeq = null;
        if (hours == 0) {
            hourSeq = "";
        } else if (hours == 1) {
            hourSeq = Long.toString(hours) + " hour, ";
        } else if (hours > 1) {
            hourSeq = Long.toString(hours) + " hours, ";
        }

        String minSeq = null;
        if (minutes == 0) {
            minSeq = "";
        } else if (minutes == 1) {
            minSeq = Long.toString(minutes) + " minute, ";
        } else if (minutes > 1) {
            minSeq = Long.toString(minutes) + " minutes, ";
        }

        String secSeq = null;
        if (seconds == 0) {
            secSeq = "";
        } else if (seconds == 1) {
            secSeq = Long.toString(seconds) + " second ";
        } else if (seconds > 1) {
            secSeq = Long.toString(seconds) + " seconds ";
        }

        StringBuilder sb = new StringBuilder();
        if (daySeq != null) {
            sb.append(daySeq);
        }
        if (daySeq != null) {
            sb.append(hourSeq);
        }
        if (daySeq != null) {
            sb.append(minSeq);
        }
        if (daySeq != null) {
            sb.append(secSeq);
        }
        return sb.toString();
    }
}
