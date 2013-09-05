package com.netwokz.tiktaktoe;

import java.util.Random;

public class TikTakToeGame {

    private char mBoard[];
    private final static int BOARD_SIZE = 9;
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static final char ANDROID_PLAYER = 'O';
    public static final char EMPTY_SPACE = ' ';

    private Random mRand;

    public TikTakToeGame() {
        mBoard = new char[BOARD_SIZE];
        mRand = new Random();
        for (int i = 0; i < BOARD_SIZE; i++) {
            mBoard[i] = EMPTY_SPACE;
        }
    }

    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            mBoard[i] = EMPTY_SPACE;
        }
    }

    public char[] getGameState() {
        return mBoard;
    }

    // set the move for a player
    public void setMove(char player, int location) {
        mBoard[location] = player;
    }

    public int getAndroidMove() {
        // run through board and check for ANDROID_PLAYER winning move;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != ANDROID_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = ANDROID_PLAYER;
                if (checkForWinner() == 3) {
                    setMove(ANDROID_PLAYER, i);
                    return i;
                } else {
                    mBoard[i] = curr;
                }
            }
        }

        // run through board and check for PLAYER_ONE winning move;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != ANDROID_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = PLAYER_ONE;
                if (checkForWinner() == 2) {
                    setMove(ANDROID_PLAYER, i);
                    return i;
                } else {
                    mBoard[i] = curr;
                }
            }
        }

        int move;
        do {
            move = mRand.nextInt(getBoardSize());
        } while (mBoard[move] == PLAYER_ONE || mBoard[move] == ANDROID_PLAYER);

        setMove(ANDROID_PLAYER, move);
        return move;
    }

    public int checkForWinner() {

        for (int i = 0; i <= 6; i += 3) {
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i + 1] == PLAYER_ONE &&
                    mBoard[i + 2] == PLAYER_ONE)
                return 2;
            if (mBoard[i] == ANDROID_PLAYER &&
                    mBoard[i + 1] == ANDROID_PLAYER &&
                    mBoard[i + 2] == ANDROID_PLAYER)
                return 3;
        }

        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == PLAYER_ONE &&
                    mBoard[i + 3] == PLAYER_ONE &&
                    mBoard[i + 6] == PLAYER_ONE)
                return 2;
            if (mBoard[i] == ANDROID_PLAYER &&
                    mBoard[i + 3] == ANDROID_PLAYER &&
                    mBoard[i + 6] == ANDROID_PLAYER)
                return 3;
        }

        if ((mBoard[0] == PLAYER_ONE && mBoard[4] == PLAYER_ONE && mBoard[8] == PLAYER_ONE) ||
                mBoard[2] == PLAYER_ONE && mBoard[4] == PLAYER_ONE && mBoard[6] == PLAYER_ONE)
            return 2;
        if ((mBoard[0] == ANDROID_PLAYER && mBoard[4] == ANDROID_PLAYER && mBoard[8] == ANDROID_PLAYER) ||
                mBoard[2] == ANDROID_PLAYER && mBoard[4] == ANDROID_PLAYER && mBoard[6] == ANDROID_PLAYER)
            return 3;

        for (int i = 0; i < getBoardSize(); i++) {
            if (mBoard[i] != PLAYER_ONE && mBoard[i] != ANDROID_PLAYER) {
                return 0;
            }
        }
        return 1;
    }
}
