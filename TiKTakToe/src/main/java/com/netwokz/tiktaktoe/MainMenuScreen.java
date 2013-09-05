package com.netwokz.tiktaktoe;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Stephen on 8/30/13.
 */
public class MainMenuScreen extends Fragment implements View.OnClickListener {

    MainMenu mMenuScreen;
    Button onePlayer, twoPlayer, exitGame;

    public interface MainMenu {
        public void startGame(boolean gameType);

        public void exitGame();

        public void dismissMainMenu();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mMenuScreen = (MainMenu) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onViewSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        onePlayer = (Button) view.findViewById(R.id.btn_one_player);
        twoPlayer = (Button) view.findViewById(R.id.btn_two_player);
        exitGame = (Button) view.findViewById(R.id.btn_exit);

        onePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        exitGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one_player:
                mMenuScreen.dismissMainMenu();
                mMenuScreen.startGame(true);
                break;
            case R.id.btn_two_player:
                mMenuScreen.dismissMainMenu();
                mMenuScreen.startGame(false);
                break;
            case R.id.btn_exit:
                mMenuScreen.exitGame();
                break;
        }
    }
}
