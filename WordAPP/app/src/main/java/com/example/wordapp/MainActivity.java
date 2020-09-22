package com.example.wordapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wordapp.dummy.WordContent;

public class MainActivity extends AppCompatActivity implements WordItemFragment.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(WordContent.WordItem wordItem) {
        DetailFragment detailFragment = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.wordDetail);
        if (detailFragment != null) {
            detailFragment.updateView(wordItem);
        } else {
            DetailFragment newFragment = new DetailFragment();
            Bundle args = new Bundle();
            args.putSerializable(DetailFragment.ARG_PARAM,wordItem);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.wordDetail, newFragment);
            // Commit the transaction

            transaction.commit();

        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this,"你选择了增加" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(this,"你选择了查询",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}