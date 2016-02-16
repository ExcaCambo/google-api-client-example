package com.example.googleapiclientexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.games.Games;

public class MainActivity extends BaseActivity {

    private View contentView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentView = findViewById(android.R.id.content);
        textView = (TextView) findViewById(R.id.text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        buildGoogleApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        contentView.setBackgroundResource(R.color.colorPrimaryDark);
        textView.setText(R.string.client_connecting);
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (googleApiClient.hasConnectedApi(Games.API)) {
            contentView.setBackgroundResource(android.R.color.white);
            String accountName = Games.getCurrentAccountName(googleApiClient);
            textView.setText(getString(R.string.game_api_connected_with, accountName));
        } else {
            contentView.setBackgroundResource(R.color.colorAccent);
            textView.setText(R.string.game_api_not_connected);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        contentView.setBackgroundResource(R.color.colorPrimaryDark);
        textView.setText(R.string.client_connection_suspended);
    }

    public void onFabClicked(View target) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
