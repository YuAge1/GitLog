package com.gitlog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gitlog.adapter.RepoAdapter;

import java.util.ArrayList;

public class RepoList extends AppCompatActivity implements RepoAdapter.OnRepoListener {
    ArrayList<String> repos = new ArrayList<>();
    private String username;
    private String authHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        Intent intent = getIntent();
        repos = intent.getStringArrayListExtra("repos");
        username = intent.getStringExtra("username");
        authHeader = intent.getStringExtra("authorization");

        if (repos == null) finish();

        setTitle(username);

        RecyclerView recyclerView = findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter<RepoAdapter.ViewHolder> adapter = new RepoAdapter(repos, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRepoClick(int position) {
        String repoName = repos.get(position);

        Intent intent = new Intent(this, FileExplorer.class);
        intent.putExtra("repoName", repoName);
        intent.putExtra("username", username);
        intent.putExtra("authorization", authHeader);
        startActivity(intent);
    }
}
