package ttu.cse.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private List<Integer>icon=new ArrayList<>();
    private List<String> starChinese = new ArrayList<>();
    private List<String> starEnglish = new ArrayList<>();
    String  ZodiacC;
    int iconk;
    static String date;
    String  ZodiacE;
    private static int m, d;
    private TextView tv;
    private static EditText ed;
    Bundle bundles;
    private String[] starC, starE;
    private MyFriendRecyclerViewAdapter adapter;
    private RecyclerView rv;
    List<Friend> friendList;
    int[] icons={R.mipmap.capricorn,R.mipmap.aquarius,R.mipmap.pisces,R.mipmap.aries,R.mipmap.taurus,R.mipmap.gemini,R.mipmap.cancer,R.mipmap.leo,R.mipmap.virgo,R.mipmap.libra,R.mipmap.scorpio,R.mipmap.sagittarius};
    Object o=new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView2);
        // rv.addItemDecoration(this, new RecyclerView.ItemDecoration()); // VERTICAL
        ed = findViewById(R.id.editTextDate);
        ed.setOnEditorActionListener(this);
        starC = getResources().getStringArray(R.array.starCH);
        starE = getResources().getStringArray(R.array.starEN);
        Intent inten = this.getIntent();
        bundles = new Bundle();
        if (bundles != null && inten.hasExtra("tt"))
        {

            bundles = inten.getExtras();

                friendList= (List<Friend>) bundles.getSerializable("tt");

        }
        else {

            friendList = new ArrayList<>();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; // super.onCreateOptionsMenu(menu);
    }

    // NOT necessary
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.history:

                Intent it = new Intent(this, HistoryActivity.class);
                //it.putStringArrayListExtra("star_ch", (ArrayList<String>) starChinese);
                //it.putStringArrayListExtra("star_en", (ArrayList<String>) starEnglish);
                Bundle bundle=new Bundle();
                bundle.putSerializable("friends", (Serializable) friendList);
                it.putExtras(bundle);

                startActivity(it);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_or_hide(MenuItem item) {
        if (tv.getVisibility() == View.VISIBLE) {
            tv.setVisibility(View.INVISIBLE);
            item.setTitle(getString(R.string.show));
        } else {
            tv.setVisibility(View.VISIBLE);
            item.setTitle(getString(R.string.hide));
        }
    }

    private void show_or_hide() {
        if (tv.getVisibility() == View.VISIBLE) {
            tv.setVisibility(View.INVISIBLE);

        } else {
            tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        boolean handled = false;


        if (actionId == EditorInfo.IME_ACTION_SEND) {
            query(m, d);
            handled = true;
        }
        return handled;
    }

    private void query(int month, int day) {


        int pos;
        int[] bound = {20, 19, 21, 20, 21, 22, 21, 20, 19, 22, 21, 21};
        if(m!=0) {
            if (d < bound[m - 1])
                pos = m - 1;
            else
                pos = m == 12 ? 0 : m;
            ZodiacC = starC[pos];
            ZodiacE = starE[pos];
            iconk = icons[pos];
            tv.setText(starC[pos] + ":" + starE[pos]);
            friendList.add(new Friend(ZodiacC, iconk, date, ZodiacE));
        }
        //adapter.notifyDataSetChanged();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            m = month + 1;
            d = day;
            date=year + "/" + m + "/" + d;
            ed.setText(year + "/" + m + "/" + d);
        }
    }

    public void datePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}