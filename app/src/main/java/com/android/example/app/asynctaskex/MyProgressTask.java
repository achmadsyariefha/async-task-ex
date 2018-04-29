package com.android.example.app.asynctaskex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyProgressTask extends AsyncTask<Void, Integer, String>  {

    Context context;
    ProgressDialog progressDialog;

    public MyProgressTask(Context context){

        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait");
        progressDialog.setMax(10);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(10000);
                Log.i("Thread", "Execute"+i);
                publishProgress(i);
            }
            return "Successful";
        }
        catch (Exception e){
            Log.i("Exception", e.getMessage());
            return "Failure";
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int myValue = values[0];
        progressDialog.setProgress(myValue);
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}
