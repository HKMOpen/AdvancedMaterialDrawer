package de.madcyph3r.example.example.functionally.closePrevDrawerActivity;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.madcyph3r.example.R;

public class CloseActivity extends AppCompatActivity {

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.close_activity);
    }
}