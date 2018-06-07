package de.madcyph3r.example.example.functionally.noClosePrevDrawerActivity;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.madcyph3r.example.R;

public class NoCloseActivity extends AppCompatActivity {

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.no_close_activity);

    }
}