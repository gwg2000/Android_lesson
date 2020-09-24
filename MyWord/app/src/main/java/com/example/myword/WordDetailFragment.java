package com.example.myword;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myword.dummy.Words;


public class WordDetailFragment extends Fragment {
     public static final String ARG_ID = "id";
     public interface OnFragmentInteractionListener {
         public void onWordDetailClick(Uri uri);
     }

     private String mID;//单词主键
     private OnFragmentInteractionListener mListener;//本Fragment所在的Activity

     public void onAttach(Context context) {
         super.onAttach(context);
         mListener = (OnFragmentInteractionListener) getActivity();
     }

     public void onDetach() {
         super.onDetach();
         mListener = null;
     }

     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
             mID = getArguments().getString(ARG_ID);
         }
     }

     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
     {
         View view= inflater.inflate(R.layout.fragment_word_detail, container, false);
        WordsDB wordsDB=WordsDB.getWordsDB();
        if(wordsDB!=null && mID!=null){
            TextView textViewWord=(TextView)view.findViewById(R.id.word);
            TextView textViewWordMeaning=(TextView)view.findViewById(R.id.wordmeaning);
            TextView textViewWordSample=(TextView)view.findViewById(R.id.wordsample);
            Words.WordDescription item=wordsDB.getSingleWord(mID);
            if(item!=null){
                textViewWord.setText(item.word);
                textViewWordMeaning.setText(item.meaning);
                textViewWordSample.setText(item.sample);
            }
            else{
                textViewWord.setText("");
                textViewWordMeaning.setText("");
                textViewWordSample.setText("");
            }
        }
        return view;}


 }