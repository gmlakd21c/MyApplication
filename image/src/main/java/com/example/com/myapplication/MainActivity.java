package com.example.com.myapplication;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView1,textView2;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3;
    Button button1,button2;
    ImageView imageView;
    Switch swc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.setTitle("안드로이드 사진 보기");

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        imageView = (ImageView)findViewById(R.id.imageView);
        swc = (Switch)findViewById(R.id.switch1);

        textView2.setVisibility(View.INVISIBLE);
        radioButton1.setChecked(false);
        radioButton1.setVisibility(View.INVISIBLE);
        radioButton2.setChecked(false);
        radioButton2.setVisibility(View.INVISIBLE);
        radioButton3.setChecked(false);
        radioButton3.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);

        swc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    textView2.setVisibility(View.VISIBLE);
                    radioButton1.setChecked(false);
                    radioButton1.setVisibility(View.VISIBLE);
                    radioButton2.setChecked(false);
                    radioButton2.setVisibility(View.VISIBLE);
                    radioButton3.setChecked(false);
                    radioButton3.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                }
                else
                {
                    imageView.setImageDrawable(null);
                    textView2.setVisibility(View.INVISIBLE);
                    radioButton1.setChecked(false);
                    radioButton1.setVisibility(View.INVISIBLE);
                    radioButton2.setChecked(false);
                    radioButton2.setVisibility(View.INVISIBLE);
                    radioButton3.setChecked(false);
                    radioButton3.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imageView.setVisibility(View.VISIBLE);
                switch (checkedId) {
                    case R.id.radioButton1:
                        imageView.setImageResource(R.drawable.jellebeans);
                        break;
                    case R.id.radioButton2:
                        imageView.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.radioButton3:
                        imageView.setImageResource(R.drawable.lollipop);
                        break;
                }
            }
        });



        button1.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }

        });

        button2.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                textView2.setVisibility(View.INVISIBLE);
                radioButton1.setChecked(false);
                radioButton1.setVisibility(View.INVISIBLE);
                radioButton2.setChecked(false);
                radioButton2.setVisibility(View.INVISIBLE);
                radioButton3.setChecked(false);
                radioButton3.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                imageView.setImageDrawable(null);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                swc.setChecked(false);
            }

        });
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
