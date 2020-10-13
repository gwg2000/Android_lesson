package com.example.music;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.music.dummy.DummyContent;

import java.security.Provider;
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
        public void onDeleteDialog(String musicname);

    }

    ArrayList<Map<String,String>> list_total;
    private OnFragmentInteractionListener mListener;

    public void onAttach(Context context) {
        super.onAttach(context);
        for(int i=0;i<MainActivity.filenames.length;i++)
        {
            MainActivity.musicList.add(i,MainActivity.filenames[i]);
            MainActivity.musicing_list.add(i,MainActivity.filenames[i]);
        }
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

    //根据musicing_list构建当前列表
    public void refreshWordsList() {

        list_total=new ArrayList<Map<String,String>>();
        for(int i=0;i<MainActivity.musicing_list.size();i++)
        {
            Map<String,String> item=new HashMap<String,String>();
            item.put("Musicname",MainActivity.musicing_list.get(i));
            list_total.add(item);

        }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), list_total, R.layout.item,
                    new String[]{"Musicname"},
                    new int[]{R.id.music_name});

            setListAdapter(adapter);

        MusicService.totalnum=MainActivity.musicing_list.size();
        }


    public MusicItem() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshWordsList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ListView mListView = (ListView) view.findViewById(android.R.id.list);
        registerForContextMenu(mListView);
        return view;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_delete, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = null;
        View itemView = null;
        TextView textView=null;
        switch (item.getItemId()) {
            case R.id.delete:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                itemView = info.targetView;
                textView = (TextView) itemView.findViewById(R.id.music_name);
                if (textView != null) {
                    String str = textView.getText().toString();
                    mListener.onDeleteDialog(str);
                }
                break;
        }
        return true;
    }
}