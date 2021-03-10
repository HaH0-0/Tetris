package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button Btn_toLeft, Btn_toRight, Btn_toDown, Btn_Rotate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Btn_toLeft = findViewById(R.id.LEFT);
        Btn_toRight = findViewById(R.id.RIGHT);
        Btn_Rotate = findViewById(R.id.ROTATE);
        Btn_toDown = findViewById(R.id.DOWN);

        Btn_toLeft.setOnClickListener(v -> {
            if (BlockUnit.CanMoveLeft(Content.Bu_falling, Content.All_bu))
                BlockUnit.MoveLeft(Content.Bu_falling, Content.All_bu);

        });

        Btn_toRight.setOnClickListener(v -> {
            if (BlockUnit.CanMoveRight(Content.Bu_falling, Content.All_bu))
                BlockUnit.MoveRight(Content.Bu_falling, Content.All_bu);
        });

        Btn_toDown.setOnClickListener(v -> {
            if (BlockUnit.CanMoveDown(Content.Bu_falling, Content.All_bu))
                BlockUnit.MoveDown(Content.Bu_falling, Content.All_bu);
        });

        Btn_Rotate.setOnClickListener(v -> {
            if (BlockUnit.CanRotate(Content.Bu_falling, Content.All_bu))
                Content.Bu_falling = BlockUnit.Rotate(Content.Block_Tetris, Content.Bu_falling);
        });


    }
}

