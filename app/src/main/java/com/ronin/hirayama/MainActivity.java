package com.ronin.hirayama;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private ImageButton increment_col_amt;
    private ImageButton decrement_col_amt;
    private EditText col_amt;

    private ImageButton increment_agent_percent;
    private ImageButton decrement_agent_percent;
    private EditText agent_percent;

    private ImageButton increment_agent_amt;
    private ImageButton decrement_agent_amt;
    private TextView agent_amt;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    public Double calculateAgentAmount(Double principal, Double percent){

        BigDecimal base = new BigDecimal(principal);
        BigDecimal percentage = new BigDecimal(percent);

        return base.multiply(percentage).divide(new BigDecimal(100)).doubleValue();

    }

    public void updateAgentAmountTextView(){
        if(!col_amt.getText().toString().isEmpty() && (!agent_percent.getText().toString().isEmpty())){
            Double agent_amount = calculateAgentAmount(Double.parseDouble(col_amt.getText().toString()),Double.parseDouble(agent_percent.getText().toString()));
            agent_amt.setText(" "+agent_amount);
        }
    }

    public void incrementWidgetLogic(EditText editText){
        Double result = Double.parseDouble(editText.getText().toString());
        result++;
        editText.setText(" "+result);
    }

    public void decrementWidgetLogic(EditText editText){
        Double result = Double.parseDouble(editText.getText().toString());
        if(result > 0.0d){
            result--;
        }
        editText.setText(" "+result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Collection Amt Plus Image Btn Logic

        increment_col_amt =(ImageButton)findViewById(R.id.collection_amt_plus);

        col_amt =(EditText) findViewById(R.id.collection_amt_editText);
        col_amt.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                updateAgentAmountTextView();
            }
        });
        increment_col_amt.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                incrementWidgetLogic(col_amt);
                updateAgentAmountTextView();
            }
        });

        // Collection Amt Minus Image Btn Logic

        decrement_col_amt =(ImageButton)findViewById(R.id.collection_amt_minus);
        decrement_col_amt.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                decrementWidgetLogic(col_amt);
                updateAgentAmountTextView();
            }
        });

        // Agent Percentage Plus Image Btn Logic

        increment_agent_percent =(ImageButton)findViewById(R.id.agent_percent_plus);

        agent_percent =(EditText) findViewById(R.id.agent_percent_editText);
        agent_percent.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                updateAgentAmountTextView();
            }
        });

        agent_percent.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                updateAgentAmountTextView();
            }
        });

        increment_agent_percent.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                incrementWidgetLogic(agent_percent);
                updateAgentAmountTextView();
            }
        });

        // Agent Percentage Minus Image Btn Logic

        decrement_agent_percent =(ImageButton)findViewById(R.id.agent_percent_minus);

        decrement_agent_percent.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                decrementWidgetLogic(agent_percent);
                updateAgentAmountTextView();
            }
        });

        // Agent Amount Plus Image Btn Logic

        agent_amt =(TextView) findViewById(R.id.agent_amt_TextView);

        agent_amt.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                updateAgentAmountTextView();
            }
        });

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.payment_dynamic_spinner);

        String[] items = new String[] { "Cash", "Credit Card", "Online Banking" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Spinner currencyDynamicSpinner = (Spinner) findViewById(R.id.currency_dynamic_spinner);

        String[] currency_items = new String[] { "Rupee" };

        ArrayAdapter<String> currency_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, currency_items);

        currencyDynamicSpinner.setAdapter(currency_adapter);

        currencyDynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

}
