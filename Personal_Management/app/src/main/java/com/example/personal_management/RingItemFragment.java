package com.example.personal_management;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;


public class RingItemFragment extends ListFragment {

    public interface OnFragmentInteractionListener {
        public void onDeleteDialog(final String strId);
    }
    private OnFragmentInteractionListener mListener;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        refreshWordsList();
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refreshWordsList() {
        DBoperate dpoperate=DBoperate.getWordsDB();
        if (dpoperate != null) {
            ArrayList<Map<String, String>> items = dpoperate.FindALLRing();
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), items, R.layout.item,
                    new String[]{"ID","TIME","INFO"},
                    new int[]{R.id.ID, R.id.TIME,R.id.INF});

            setListAdapter(adapter);
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //为列表注册上下文菜单
        ListView mListView = (ListView) view.findViewById(android.R.id.list);    //
        //mListView.setOnCreateContextMenuListener(this);
        registerForContextMenu(mListView);
        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu3, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        TextView textId = null;
        AdapterView.AdapterContextMenuInfo info = null;
        View itemView = null;
        switch (item.getItemId()) {
            case R.id.cancel:
                //删除单词
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                itemView = info.targetView;
                textId = (TextView) itemView.findViewById(R.id.ID);
                if (textId != null) {
                    String strId = textId.getText().toString();
                    mListener.onDeleteDialog(strId);
                }
                break;}
                return true;

    }

}