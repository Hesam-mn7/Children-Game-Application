package com.example.fragmenthesam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import github.nisrulz.screenshott.ScreenShott;

public class MainActivity extends AppCompatActivity implements HeadFragment.CallbackMyFragment , BodyFragment.CallbackMyFragment2 , FootFragment.CallbackMyFragmentFoot {

    ImageView btnrefresh , favorite , favoriteEmpty , btndelete , music , musicoff;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        
        //test commit

        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.effects);
        mediaPlayer.start();

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicoff.setVisibility(View.VISIBLE);
                music.setVisibility(View.INVISIBLE);
                mediaPlayer.pause();
            }
        });
        musicoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.setVisibility(View.VISIBLE);
                musicoff.setVisibility(View.INVISIBLE);
                mediaPlayer.start();
            }
        });

        favoriteEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteEmpty.setVisibility(View.INVISIBLE);
                favorite.setVisibility(View.VISIBLE);
            }
        });
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteEmpty.setVisibility(View.VISIBLE);
                favorite.setVisibility(View.INVISIBLE);
            }
        });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                ResultFragment fragment = (ResultFragment) manager.findFragmentById(R.id.fragmentResult);
                fragment.setParamBody(0);
                fragment.setParamFoot(0);
                fragment.setParam(0);

//        ResultFragment fragment = ResultFragment.newInstance(0);
//        ResultFragment fragment1 = ResultFragment.newInstanceBody(0);
//        ResultFragment fragment2 = ResultFragment.newInstanceFoot(0);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.fragmentResult,fragment,"fragment1");
//        transaction.replace(R.id.fragmentResult,fragment1,"fragment2");
//        transaction.replace(R.id.fragmentResult,fragment2,"fragment3");
//        transaction.addToBackStack(null);
//        transaction.commit();

            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitmap = ScreenShott.getInstance().takeScreenShotOfRootView(v);
//                img.setImageBitmap(bitmap);
                saveScreenshot();

            }
        });
    }
    private void init(){
        btnrefresh = findViewById(R.id.btnrefresh);
        favorite = findViewById(R.id.favorite);
        favoriteEmpty = findViewById(R.id.favoriteEmpty);
        btndelete = findViewById(R.id.btndelete);
        music = findViewById(R.id.music);
        musicoff = findViewById(R.id.musicoff);
    }

    @Override
    public void onclick(int HeadPicID) {

//        ResultFragment fragment = ResultFragment.newInstance(HeadPicID);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.fragmentResult,fragment,"fragment1");
//        transaction.addToBackStack(null);
//        transaction.commit();

        FragmentManager manager=getSupportFragmentManager();
        ResultFragment fragment = (ResultFragment) manager.findFragmentById(R.id.fragmentResult);
        fragment.setParam(HeadPicID);

    }

    @Override
    public void onclick2(int BodyPicID) {

//        ResultFragment fragment = ResultFragment.newInstanceBody(BodyPicID);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.fragmentResult,fragment,"fragment2");
//        transaction.addToBackStack(null);
//        transaction.commit();

        FragmentManager manager=getSupportFragmentManager();
        ResultFragment fragment = (ResultFragment) manager.findFragmentById(R.id.fragmentResult);
        fragment.setParamBody(BodyPicID);
        
    }

    @Override
    public void onclickFoot(int FootPicID) {

//        ResultFragment fragment = ResultFragment.newInstanceFoot(FootPicID);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.fragmentResult,fragment,"fragment3");
//        transaction.addToBackStack(null);
//        transaction.commit();

        FragmentManager manager=getSupportFragmentManager();
        ResultFragment fragment = (ResultFragment) manager.findFragmentById(R.id.fragmentResult);
        fragment.setParamFoot(FootPicID);

    }

    private void saveScreenshot() {
        try {
            File file = ScreenShott.getInstance()
                    .saveScreenshotToPicturesFolder(MainActivity.this, bitmap, "FragmentHesam");
//            Toast.makeText(MainActivity.this, "Bitmap Saved at " + file.getAbsolutePath(),
//                    Toast.LENGTH_SHORT).show();

            MediaPlayer mediaPlayer2;
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.clickax);
            mediaPlayer2.start();

            AlertDialog alertDialog;
            View alert= LayoutInflater.from(MainActivity.this).inflate(R.layout.alerdialogscreenshot,null);
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setView(alert);
            builder.setCancelable(false);
            alertDialog=builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans)));
            alertDialog.show();

            ImageView done = alert.findViewById(R.id.done);
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Screenshot was not successfully.",Toast.LENGTH_SHORT).show();
        }
    }
    //    private void takeScreenshot() {
//
//        try {
//
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
//
//            File myFile = Environment.getExternalStorageDirectory();
//            File folder = new File(myFile, "FragmentHesam");
//            if (!folder.exists())
//                folder.mkdir();
//            File file = new File(folder, generateString()+".jpg");
//
//            FileOutputStream outputStream = new FileOutputStream(file);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            MediaPlayer mediaPlayer2;
//            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.clickax);
//            mediaPlayer2.start();
//
//            AlertDialog alertDialog;
//            View alert= LayoutInflater.from(MainActivity.this).inflate(R.layout.alerdialogscreenshot,null);
//            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//            builder.setView(alert);
//            builder.setCancelable(false);
//            alertDialog=builder.create();
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans)));
//            alertDialog.show();
//
//            ImageView done = alert.findViewById(R.id.done);
//            done.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    alertDialog.dismiss();
//                }
//            });
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//            Toast.makeText(MainActivity.this,"Screenshot was not successfully.",Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    public static String generateString() {
//        String uuid = UUID.randomUUID().toString();
//        return uuid;
//    }

}
