package com.azarianalip.wiridan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    TextView dzikir;
    int zikir = 0;
    Vibrator vibe;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);

        dzikir = (TextView) findViewById(R.id.dzikir);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void subhanallah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tasbih);
        mediaPlayer.start();
        zikir = zikir + 1;
        tampil(zikir);
        vibe.vibrate(100);
    }

    public void alhamdulillah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tahmid);
        mediaPlayer.start();
        zikir = zikir + 1;
        tampil(zikir);
        vibe.vibrate(100);
    }

    public void lailahaillallah(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tahlil);
        mediaPlayer.start();
        zikir = zikir + 1;
        tampil(zikir);
        vibe.vibrate(100);
    }

    public void allahuakbar(View view) {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.takbir);
        mediaPlayer.start();
        zikir = zikir + 1;
        tampil(zikir);
        vibe.vibrate(100);
    }

    public void tampil(int z) {
        dzikir.setText("" + z);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                mediaPlayer.stop();
                zikir = 0;
                tampil(zikir);
                return true;
            case R.id.logout:
                logoutKan();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutKan() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_ID, null);
        editor.putString(TAG_USERNAME, null);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Login.class);
        finish();
        startActivity(intent);
    }

}