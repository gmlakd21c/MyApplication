package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] numBtn;
    Button[] operationBtn;
    TextView resultText;
    EditText num1,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);


        operationBtn = new Button[4];
        operationBtn[0] = (Button)findViewById(R.id.sumBtn);
        operationBtn[1] = (Button)findViewById(R.id.subBtn);
        operationBtn[2] = (Button)findViewById(R.id.mulBtn);
        operationBtn[3] = (Button)findViewById(R.id.divBtn);

        for(int i=0; i<4; i++)
        {
            operationBtn[i].setOnClickListener(new MyOperationListener());
        }

        numBtn = new Button[10];
        numBtn[0] = (Button)findViewById(R.id.zeroBtn);
        numBtn[1] = (Button)findViewById(R.id.oneBtn);
        numBtn[2] = (Button)findViewById(R.id.twoBtn);
        numBtn[3] = (Button)findViewById(R.id.threeBtn);
        numBtn[4] = (Button)findViewById(R.id.fourBtn);
        numBtn[5] = (Button)findViewById(R.id.fiveBtn);
        numBtn[6] = (Button)findViewById(R.id.sixBtn);
        numBtn[7] = (Button)findViewById(R.id.sevenBtn);
        numBtn[8] = (Button)findViewById(R.id.eightBtn);
        numBtn[9] = (Button)findViewById(R.id.nineBtn);

        for(int i=0; i<9 ;i++)
        {
            numBtn[i].setOnClickListener(new MyClickListener());
        }

        resultText = (TextView)findViewById(R.id.resultText);
        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);

    }

    public class MyClickListener implements OnClickListener
    {
        public void onClick(View v)
        {
            Button tmp = (Button)findViewById(v.getId());
            String tmpNum="" , tmpNum2="";

            tmpNum2 = tmp.getText().toString();

            if(num1.isFocused())
            {
                tmpNum = num1.getText().toString() + tmpNum2;
                num1.setText(tmpNum);
            }

            else if(num2.isFocused())
            {
                tmpNum = num2.getText().toString() + tmpNum2;
                num2.setText(tmpNum);
            }
        }
    }

    public class MyOperationListener implements OnClickListener
    {
        public void onClick(View v)
        {
            int tmp;
            switch(v.getId())
            {
                case R.id.sumBtn:
                    tmp = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
                    resultText.setText(String.valueOf(tmp));
                    break;
                case R.id.subBtn:
                    tmp = Integer.parseInt(num1.getText().toString()) - Integer.parseInt(num2.getText().toString());
                    resultText.setText(String.valueOf(tmp));
                    break;
                case R.id.mulBtn:
                    tmp = Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString());
                    resultText.setText(String.valueOf(tmp));
                    break;
                case R.id.divBtn:
                    tmp = Integer.parseInt(num1.getText().toString()) / Integer.parseInt(num2.getText().toString());
                    resultText.setText(String.valueOf(tmp));
                    break;
            }
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
