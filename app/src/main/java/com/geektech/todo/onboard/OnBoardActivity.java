package com.geektech.todo.onboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.geektech.todo.MainActivity;
import com.geektech.todo.R;

public class OnBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
    }

    public void start(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
