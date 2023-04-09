package ttu.cse.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<String> starChinese = new ArrayList<>();
    private List<String> starEnglish = new ArrayList<>();
    List<Friend> friendList = new ArrayList<>();
    private StarFragment firstFragment;
    public RecyclerView rv;
    Menu menu_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);



        //starChinese = getIntent().getStringArrayListExtra("star_ch");
        //starEnglish = getIntent().getStringArrayListExtra("star_en");


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        friendList= (List<Friend>) bundle.getSerializable("friends");
        //Toast.makeText(this,friendList.get(0).getName(),Toast.LENGTH_SHORT).show();
        LinearLayoutManager llm = new LinearLayoutManager(this);//(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv=findViewById(R.id.list);
        rv.setLayoutManager(llm);
        MyFriendRecyclerViewAdapter adapter=new MyFriendRecyclerViewAdapter(friendList);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu_1=menu;
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true; // super.onCreateOptionsMenu(menu);
    }

    // NOT necessary
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的假设是BACK，同一时候没有反复
            Intent it = new Intent(this, MainActivity.class);
            Bundle bundles = new Bundle();
            if(!friendList.isEmpty()) {
                bundles.putSerializable("tt", (Serializable) friendList);
                it.putExtras(bundles);
            }

            startActivity(it);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        MenuItem show=menu_1.findItem(R.id.showlist);
        MenuItem hide=menu_1.findItem(R.id.hidelist);
        switch (item.getItemId()) {
            case R.id.hidelist:

                item.setVisible(false);
                show.setVisible(true);
                show_or_hide();
                return true;
            case R.id.showlist:

                item.setVisible(false);
                hide.setVisible(true);
                show_or_hide();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_or_hide(MenuItem item) {
        if (rv.getVisibility() == View.VISIBLE) {
            rv.setVisibility(View.INVISIBLE);
            item.setTitle(getString(R.string.show));
        } else {
            rv.setVisibility(View.VISIBLE);
            item.setTitle(getString(R.string.hide));
        }
    }

    private void show_or_hide() {
        if (rv.getVisibility() == View.VISIBLE) {
            rv.setVisibility(View.INVISIBLE);

        } else {
            rv.setVisibility(View.VISIBLE);
        }
    }




}