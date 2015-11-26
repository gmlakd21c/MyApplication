package com.example.com.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Integer[] posterID = {R.drawable.m0, R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4, R.drawable.m5,
            R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9};

    String[] posterName = {"신세계", "구르믈버서난달처럼","레미제라블","메트릭스","내머리속지우개","블랙스완",
    "님은먼곳에", "어바웃타임", "숨바꼭질", "후궁"};
    ImageView tmpView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView);

        MyAdapter myAdapter = new MyAdapter(this);

        gv.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter{

        Context context;
        public MyAdapter(Context c)
        {
            context = c;
        }

        public int getCount()
        {
            return posterID.length;
        }

        public Object getItem(int id)
        {
            return null;
        }

        public long getItemId(int arg)
        {
            return 0;
        }

        public View getView(int position, View view, ViewGroup viewGroup)
        {
            final int tmp = position;

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, viewGroup, false);
            view.setLayoutParams(new GridView.LayoutParams(200,200));
            LinearLayout tmpLi = (LinearLayout)view.findViewById(R.id.posterLayout);
            TextView textView = (TextView)view.findViewById(R.id.posterName);
            textView.setText(posterName[tmp]);
            ImageView imageView = (ImageView)view.findViewById(R.id.posterPicture);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageResource(posterID[tmp]);

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                    ImageView tmpView = (ImageView) dialogView.findViewById(R.id.poster);
                    tmpView.setImageResource(posterID[tmp]);
                    dig.setIcon(R.drawable.m11);
                    dig.setTitle(posterName[tmp]);
                    dig.setView(dialogView);
                    dig.setNegativeButton("닫기", null);
                    dig.show();
                }
            });
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
