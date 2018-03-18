package com.example.android.alifnoorachmadmuttaqin_1202154126_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;


public class ListNamaMahasiswa extends AppCompatActivity {
    private ListView mListView;
    private ProgressBar mProgressBar;
    private Button mStartAsyncTask;
    private String [] namaMahasiswa= {
            "Naruto", "Sasuke", "Luffy", "Zoro", "Gaara",
            "Sakura", "Robin", "Chopper", "Ussop", "Nami",
            "Franky", "Brook"};
    private AddItemToListView mAddItemToListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);
        mProgressBar = findViewById(R.id.progressbar);
        mListView = findViewById(R.id.listView);
        mStartAsyncTask = findViewById(R.id.button_startAsyncTask);
        mListView.setVisibility(View.GONE);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }
    public class AddItemToListView  extends AsyncTask<Void, String, Void> {
        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMahasiswa.this);
        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : namaMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int)((counter/(float)namaMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }
        @Override
        protected void onPostExecute(Void Void) {
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }


    }
}
