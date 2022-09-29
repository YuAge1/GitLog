package com.gitlog.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gitlog.Link;
import com.gitlog.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private ArrayList<Link> dataset;
    private OnRepoListener onRepoListener;

    public FileAdapter(ArrayList<Link> dataset, OnRepoListener onRepoListener) {
        this.dataset = dataset;
        this.onRepoListener = onRepoListener;
    }

    @NotNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder(v, onRepoListener);
        return vh;
    }

    // Заменит содержимое представления (вызывается менеджером компоновки)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        // - получает элемент из dataset в этой позиции
        // - меняет местами содержимое contest с этим элементом
        Link element = dataset.get(position);
        boolean isDirectory = element.getType().equals("dir");
        String text = element.getName() + (isDirectory ? " -> " : "");
        holder.textView.setText(text);
        // добавляет ячейки ссылочного типа
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(isDirectory ? R.drawable.ic_launcher_background : R.drawable.ic_launcher_foreground, 0, 0, 0);
        holder.textView.setCompoundDrawablePadding(50);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public interface OnRepoListener {
        void onRepoClick(int position);
    }

    // Указывает ссылку на представления для каждого элемента данных
    // Сложным элементам данных может потребоваться более одного представления для каждого элемента, и
    // ты предоставляешь доступ ко всем представлениям для элемента данных в view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
