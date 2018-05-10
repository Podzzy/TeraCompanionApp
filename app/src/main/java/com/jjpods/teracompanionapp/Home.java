package com.jjpods.teracompanionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button guideButton, craftingButton, brokerButton, questButton, characterButton, socialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        guideButton = findViewById(R.id.guideButton);
        craftingButton = findViewById(R.id.craftingButton);
        brokerButton = findViewById(R.id.brokerButton);
        questButton = findViewById(R.id.questButton);
        characterButton = findViewById(R.id.characterButton);
        socialButton = findViewById(R.id.socialButton);

        brokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Broker.class);
                startActivity(i);
            }
        });
    }
}
