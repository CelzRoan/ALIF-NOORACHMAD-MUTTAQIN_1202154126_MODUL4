package com.example.android.alifnoorachmadmuttaqin_1202154126_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {
    private ImageView downloadedImage;
    private ProgressDialog mProgressDialog;
    private EditText linkUrl;
    private Button imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);
        imageButton = findViewById(R.id.button_startAsyncTask);
        downloadedImage = findViewById(R.id.ImageView);
        linkUrl = findViewById(R.id.urlText);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String downloadUrl = linkUrl.getText().toString();
                if(downloadUrl.isEmpty()){
                    Toast.makeText(PencariGambar.this,"Masukkan Link Gambar",Toast.LENGTH_SHORT).show();
                }else {
                    new ImageDownload().execute(downloadUrl);
                }
            }
        });
    }
    private class ImageDownload extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(PencariGambar.this);
            mProgressDialog.setTitle("Mencari Gambar");
            mProgressDialog.setMessage("Sedang Mencari");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            downloadedImage.setImageBitmap(result);
            mProgressDialog.dismiss();
        }
    }
}
