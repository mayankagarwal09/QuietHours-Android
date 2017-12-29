package com.mayanktechnologies.alarmmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class About_Activity extends AppCompatActivity {

    ImageView imageView_developer,imageView_designer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_);

        imageView_developer=(ImageView) findViewById(R.id.imageview_developer);
        imageView_designer=(ImageView) findViewById(R.id.imageview_designer);

        imageView_developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/1562742487"));
                    startActivity(intent);
                }catch (Exception e){
                    Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/Mayankthecool/"));
                    startActivity(intent);

                }

            }
        });

        imageView_designer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/1284341777"));
                    startActivity(intent);
                }catch (Exception e){
                    Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/profile.php?id=1284341777"));
                    startActivity(intent);
                }


            }
        });

    }
}
