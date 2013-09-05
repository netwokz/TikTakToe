package com.netwokz.tiktaktoe;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Stephen on 9/1/13.
 */
public class MyProgressDialog extends ProgressDialog {

    ProgressDialog mProgressDialog = null;
    Context mContext;


    public MyProgressDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(mContext.getResources().getString(R.string.pd_title));
        mProgressDialog.setMessage(mContext.getResources().getString(R.string.pd_message));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    public void showDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    public void dismissDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
