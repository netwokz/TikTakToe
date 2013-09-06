package com.netwokz.tiktaktoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Stephen on 9/4/13.
 */
public class AfterGameDialog extends DialogFragment {

    ButtonListener mListener;

    public interface ButtonListener {
        public void newGame();

        public void returnToMainMenu();
    }

    public static AfterGameDialog newInstance() {
        AfterGameDialog fragment = new AfterGameDialog();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onViewSelected");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle mBundle = this.getArguments();
        int RESULT = -1;
        if (mBundle != null) {
            RESULT = mBundle.getInt("RESULT");
        }
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.after_game_dialog, null);
        TextView resultLabel = (TextView) view.findViewById(R.id.result_label);
        Button playAgain = (Button) view.findViewById(R.id.btn_play_again);
        Button mainMenu = (Button) view.findViewById(R.id.btn_main_menu);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.newGame();
            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.returnToMainMenu();
            }
        });

        switch (RESULT) {
            case -1:
                break;
            case 0:
                resultLabel.setText(getResources().getString(R.string.end_result_won));
                break;
            case 1:
                resultLabel.setText(getResources().getString(R.string.end_result_lose));
                break;
            case 2:
                resultLabel.setText(getResources().getString(R.string.end_result_tie));
                break;
        }
        mDialogBuilder.setView(view);
        return mDialogBuilder.create();
    }
}
