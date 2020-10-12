package com.example.music;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.music.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 */
public class MusicItem extends ListFragment {

    public interface OnFragmentInteractionListener {
        public void onWordItemClick(String musicname);

        public void onDeleteDialog(String strId);

    }
    //String music[]={"南山南","走马","飘向北方"};
    ArrayList<Map<String,String>> list_total,list_xian;

    private OnFragmentInteractionListener mListener;

    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentInteractionListener) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (null != mListener) {
            //通知Fragment所在的Activity，用户单击了列表的position项
            TextView textView = (TextView) v.findViewById(R.id.music_name);
            if (textView != null) {

                mListener.onWordItemClick(textView.getText().toString());
            }
        }
    }

    public void refreshWordsList() {

        list_total=new ArrayList<Map<String,String>>();
        //list_xian=new ArrayList<Map<String,String>>();
        for(int i=0;i<MainActivity.filenames.length;i++)
        {
            Map<String,String> item=new HashMap<String,String>();
            item.put("Musicname",MainActivity.filenames[i]);
            list_total.add(item);
            //list_xian.add(item);
            MusicService.musicList.add(i,MainActivity.filenames[i]);
        }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), list_total, R.layout.item,
                    new String[]{"Musicname"},
                    new int[]{R.id.music_name});

            setListAdapter(adapter);
        }

    public MusicItem() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        refreshWordsList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}