package com.example.a109590024_hw7;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a109590024_hw7.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<HashMap<String, String>> _data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        createData();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void createData() {
        _data = new LinkedList<>();
        HashMap<String, String> row = new HashMap<>();
        row.put("title", "First Recipe");
        row.put("data", "Donut");
        row.put("img_url", String.valueOf(R.drawable.donut_circle));
        _data.add(row);
        row = new HashMap<>();
        row.put("title", "Second Recipe");
        row.put("data", "Froyo");
        row.put("img_url", String.valueOf(R.drawable.froyo_circle));
        _data.add(row);
        row = new HashMap<>();
        row.put("title", "Third Recipe");
        row.put("data", "Icecream");
        row.put("img_url", String.valueOf(R.drawable.icecream_circle));
        _data.add(row);
        row = new HashMap<>();
        row.put("title", "Fourth Recipe");
        row.put("data", "Donut");
        row.put("img_url", String.valueOf(R.drawable.donut_circle));
        _data.add(row);
        row = new HashMap<>();
        row.put("title", "Fifth Recipe");
        row.put("data", "Froyo");
        row.put("img_url", String.valueOf(R.drawable.froyo_circle));
        _data.add(row);
        row = new HashMap<>();
        row.put("title", "Sixth Recipe");
        row.put("data", "Icecream");
        row.put("img_url", String.valueOf(R.drawable.icecream_circle));
        _data.add(row);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        class ViewHolder extends RecyclerView.ViewHolder {
            public View itemView;
            public TextView title;
            public TextView data;

            public ViewHolder(View view) {
                super(view);
                itemView =  view;
                title = itemView.findViewById(R.id.item_title);
                data = itemView.findViewById(R.id.item_data);
            }
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.title.setText(_data.get(position).get("title"));
            holder.data.setText(_data.get(position).get("data"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Recipe.class);
                    intent.putExtra("img_url", _data.get(position).get("img_url"));
                    intent.putExtra("data", _data.get(position).get("data"));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return _data.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}