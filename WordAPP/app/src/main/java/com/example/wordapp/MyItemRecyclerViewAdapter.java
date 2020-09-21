package com.example.wordapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordapp.dummy.WordContent;
import com.example.wordapp.dummy.WordContent.WordItem;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<WordContent.WordItem> mValues;
    public MyItemRecyclerViewAdapter(List<WordContent.WordItem> items) {
        mValues = items;
    }

    WordItemFragment.OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(WordItemFragment.OnItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mWordView.setText(mValues.get(position).word);

        if(mOnItemClickListener!=null){
            holder.mWordView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnItemClickListener.onItemClick(mValues.get(position));

                }

            });

        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mWordView;
        public WordContent.WordItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_id);
            mWordView = (TextView) view.findViewById(R.id.item_word);
        }



        @Override

        public String toString() {

            return super.toString() + " '" + mWordView.getText() + "'";

        }

    }
}