package com.cumucore.collapseingtoolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names = new ArrayList<>();
        names.add("Jhon");
        names.add("+35855 55 555");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView  = findViewById(R.id.recycler_view_profile);
        ProfileViewAdapter adapter = new ProfileViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setUserList(names);
    }

    public void showCollapsibleToolbar(View view) {
        Intent intent = new Intent(this, ScrollBarActivity.class);
        startActivity(intent);
    }


    /**
     * adapter for recycler view
     */
    class ProfileViewAdapter extends RecyclerView.Adapter<ProfileViewAdapter.ProfileViewHolder> {

        List<String> userList;

        ProfileViewAdapter(List<String> list) {
            userList = list;
        }
        ProfileViewAdapter() {

        }
        @NonNull
        @Override
        public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(R.layout.profile_layout, parent, false);

            return new ProfileViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
            holder.textView.setText(userList.get(position));
        }


        @Override
        public int getItemCount() {
            if (userList != null)
                return userList.size();
            return 0;
        }


        class ProfileViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ProfileViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.profile_text_view);
            }
        }

        public void setUserList(List<String> userList) {
            this.userList = userList;
            notifyDataSetChanged();
        }

    }

}
