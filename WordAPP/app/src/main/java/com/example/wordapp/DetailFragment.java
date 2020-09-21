package com.example.wordapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordapp.dummy.WordContent;


public class DetailFragment extends Fragment {

    public static final String ARG_PARAM = "wordItem";

    private WordContent.WordItem mWordItem;
    TextView mTextViewId;
    TextView mTextViewWord;
    TextView mTextViewMeaning;
    TextView mTextViewSample;


    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(WordContent.WordItem wordItem) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM,wordItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWordItem = (WordContent.WordItem)getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail, container, false);

        mTextViewId=view.findViewById(R.id.textviewId);

        mTextViewWord=view.findViewById(R.id.textviewWord);

        mTextViewMeaning=view.findViewById(R.id.textviewMeaning);

        mTextViewSample=view.findViewById(R.id.textviewSample);

        return view;
    }
    public void updateView(WordContent.WordItem wordItem){

        mTextViewId.setText(wordItem.id);

        mTextViewWord.setText(wordItem.word);

        mTextViewMeaning.setText(wordItem.meaning);

        mTextViewSample.setText(wordItem.sample);



    }
}