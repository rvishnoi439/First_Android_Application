package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> data;
    Button buttonAdd;
    EditText textField;
    RecyclerView itemList;
    ItemAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.ButtonAdd);
        textField = findViewById(R.id.TextField);
        itemList = findViewById(R.id.ItemList);

        textField.setText("Hello World!!!");
        loadData();

        ItemAdapter.OnLongClickListener onLongClickListener = new ItemAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(int position) {
                data.remove(position);
                listAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item removed from the List", Toast.LENGTH_SHORT).show();
                saveData();
            }
        };

        listAdapter = new ItemAdapter(data, onLongClickListener);
        itemList.setAdapter(listAdapter);
        itemList.setLayoutManager(new LinearLayoutManager(this));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAdd = textField.getText().toString();
                data.add(toAdd);
                listAdapter.notifyItemInserted(data.size() - 1);
                textField.setText("");
                Toast.makeText(getApplicationContext(), "Item added to List", Toast.LENGTH_SHORT).show();
                saveData();

            }
        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    private void loadData(){
        try {
            data = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            System.err.println("file not found");
            data = new ArrayList<String>();
        }
    }

    private void saveData(){
        try {
            FileUtils.writeLines(getDataFile(), data);
        } catch (IOException e) {
            System.err.println("file not found");
        }
    }
}