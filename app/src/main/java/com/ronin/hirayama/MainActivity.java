package com.ronin.hirayama;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener{

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
    private EditText calendar_Edit_Text;
    Calendar myCalendar = Calendar.getInstance();
    EditText time;

    private GoogleApiClient mGoogleApiClient;
    private final int PLACE_PICKER_REQUEST = 1;
    private String TAG = "place";

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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        calendar_Edit_Text.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //____________check for successfull result
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                //_________case for placepicker
                case PLACE_PICKER_REQUEST:

                    //______create place object from the received intent.
                    Place place = PlacePicker.getPlace(data, this);

                    //______get place name from place object
                    String toastMsg = String.format("Place: %s", place.getName());

                    //_________show toast message for selected place
                    Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Google Maps API

        //________initialize google client api
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();



        // Collection Amt Plus Image Btn Logic

//        increment_col_amt =(ImageButton)findViewById(R.id.collection_amt_plus);
//
//        col_amt =(EditText) findViewById(R.id.collection_amt_editText);
//        col_amt.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                updateAgentAmountTextView();
//            }
//        });
//        increment_col_amt.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                incrementWidgetLogic(col_amt);
//                updateAgentAmountTextView();
//            }
//        });

        // Collection Amt Minus Image Btn Logic

//        decrement_col_amt =(ImageButton)findViewById(R.id.collection_amt_minus);
//        decrement_col_amt.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                decrementWidgetLogic(col_amt);
//                updateAgentAmountTextView();
//            }
//        });

        // Agent Percentage Plus Image Btn Logic

//        increment_agent_percent =(ImageButton)findViewById(R.id.agent_percent_plus);
//
//        agent_percent =(EditText) findViewById(R.id.agent_percent_editText);
//        agent_percent.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                updateAgentAmountTextView();
//            }
//        });

//        agent_percent.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                updateAgentAmountTextView();
//            }
//        });
//
//        increment_agent_percent.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                incrementWidgetLogic(agent_percent);
//                updateAgentAmountTextView();
//            }
//        });

        // Agent Percentage Minus Image Btn Logic

//        decrement_agent_percent =(ImageButton)findViewById(R.id.agent_percent_minus);
//
//        decrement_agent_percent.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                decrementWidgetLogic(agent_percent);
//                updateAgentAmountTextView();
//            }
//        });

        // Agent Amount Plus Image Btn Logic

//        agent_amt =(TextView) findViewById(R.id.agent_amt_TextView);
//
//        agent_amt.setOnClickListener(new View.OnClickListener()   {
//            public void onClick(View v)  {
//                updateAgentAmountTextView();
//            }
//        });

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

        // Status Spinner Logic

        Spinner statusDynamicSpinner = (Spinner) findViewById(R.id.status_dynamic_spinner);

        String[] status_items = new String[] { "Open", "Assigned", "Collected" };

        ArrayAdapter<String> status_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, status_items);

        statusDynamicSpinner.setAdapter(status_adapter);

        statusDynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        // Date Picker Logic

        calendar_Edit_Text= (EditText) findViewById(R.id.calendar_date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        calendar_Edit_Text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Time Picker Logic

        //  initiate the edit text
        time = (EditText) findViewById(R.id.time);
        // perform click event listener on edit text
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
    }

    public void openPlacePicker(View view) {

        //___________create object of placepicker builder
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {

            //__________start placepicker activity for result
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
