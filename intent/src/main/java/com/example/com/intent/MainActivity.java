package com.example.com.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
    RadioGroup rGroup;
    RadioButton radioSum,radioSub,radioMul,radioDiv;
    Button calcBtn;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            int total = data.getIntExtra("Rusult", 0);
            Toast.makeText(getApplication(),"계산결과 : " + String.valueOf(total), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);
        rGroup = (RadioGroup)findViewById(R.id.rGroup);
        radioSum = (RadioButton)findViewById(R.id.radioSum);
        radioSub = (RadioButton)findViewById(R.id.radioSub);
        radioMul = (RadioButton)findViewById(R.id.radioMul);
        radioDiv = (RadioButton)findViewById(R.id.radioDiv);
        calcBtn = (Button)findViewById(R.id.calcBtn);


        final Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton tmp = (RadioButton) findViewById(rGroup.getCheckedRadioButtonId());
                String operation = tmp.getText().toString();
                intent.putExtra("Operation", operation);
                Toast.makeText(getApplicationContext(),operation,Toast.LENGTH_SHORT).show();
            }
        });

        calcBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                intent.putExtra("Num1", Integer.parseInt(num1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(num2.getText().toString()));
                startActivityForResult(intent, 0);
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
