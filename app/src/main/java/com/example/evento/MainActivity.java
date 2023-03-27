package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass>userList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initRecyclerView();
    }

    private void initData() {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.listitem1,"Hemant","9:45 Pm"));
        userList.add(new ModelClass(R.drawable.listitem1,"Ramesh","9:45 Pm"));
        userList.add(new ModelClass(R.drawable.listitem1,"Suresh","9:45 Pm"));
        userList.add(new ModelClass(R.drawable.listitem1,"Samresh","9:45 Pm"));
        userList.add(new ModelClass(R.drawable.listitem1,"Kamlesh","9:45 Pm"));
        userList.add(new ModelClass(R.drawable.listitem1,"Mahesh","9:45 Pm"));

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}