package com.example.wordapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapp.dummy.WordContent;

/**
 * A fragment representing a list of Items.
 */
public class WordItemFragment extends Fragment {
    OnItemClickListener mOnItemClickListener;
    MyItemRecyclerViewAdapter mMyItemRecyclerViewAdapter;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public interface OnItemClickListener{
        void onItemClick(WordContent.WordItem wordItem);
    }

    public WordItemFragment() {
    }

    public static WordItemFragment newInstance(int columnCount) {
        WordItemFragment fragment = new WordItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOnItemClickListener=(OnItemClickListener) context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_item_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            mMyItemRecyclerViewAdapter=new MyItemRecyclerViewAdapter(WordContent.ITEMS);
            mMyItemRecyclerViewAdapter.setOnItemClickListener(mOnItemClickListener);

            recyclerView.setAdapter(mMyItemRecyclerViewAdapter);

        }

        return view;

    }




}