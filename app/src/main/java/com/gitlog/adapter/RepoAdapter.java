package com.gitlog.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gitlog.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private ArrayList<String> dataset;
    private OnRepoListener onRepoListener;

    public RepoAdapter(ArrayList<String> dataset, OnRepoListener onRepoListener) {
        this.dataset = dataset;
        this.onRepoListener = onRepoListener;
    }

    @NotNull
    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v, onRepoListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        // - получает элемент из dataset в этой позиции
        // - меняет местами содержимое contest с этим элементом
        holder.textView.setText(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface OnRepoListener {
        void onRepoClick(int position);
    }

    // Указывает ссылку на представления для каждого элемента данных,
    // поскольку для сложных элементов данных может потребоваться более одного представления для каждого элемента, и
    // ты предоставляешь доступ ко всем представлениям для элемента данных в view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // в данном случае каждый элемент данных - это просто строка
        TextView textView;
        OnRepoListener onRepoListener;

        ViewHolder(TextView v, OnRepoListener onRepoListener) {
            super(v);
            textView = v;
            this.onRepoListener = onRepoListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRepoListener.onRepoClick(getAdapterPosition());
        }
    }
}
