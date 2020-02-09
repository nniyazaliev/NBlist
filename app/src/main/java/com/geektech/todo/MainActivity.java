package com.geektech.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks = new ArrayList<>();
    TaskAdapter adapter;
    private Task task;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Task> savedTasks = Storage.read(this);
        tasks = savedTasks;

        adapter = new TaskAdapter(tasks);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                task = tasks.get(position);
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                intent.putExtra("key", task);
                startActivityForResult(intent, 1);
                Toast.makeText(MainActivity.this, "редактирование", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                tasks.remove(tasks.get(position));
                Storage.save(tasks, MainActivity.this);
                Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        Button button = findViewById(R.id.open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 42);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 42) {
            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
            Storage.save(tasks, this);
            adapter.notifyDataSetChanged();
        }

        if (resultCode == RESULT_OK && requestCode == 1) {
            this.position=position;
            Task task = (Task) data.getSerializableExtra("keys");
            tasks.remove(tasks.get(position));
            tasks.add(task);
            Storage.save(tasks, this);
            adapter.notifyDataSetChanged();
        }
    }



}
