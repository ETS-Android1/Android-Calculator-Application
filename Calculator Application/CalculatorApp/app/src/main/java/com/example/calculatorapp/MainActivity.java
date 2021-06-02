package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double currentNum;
    double nextNum;
    double result;
    boolean add = false;
    boolean subtract = false;
    boolean multiply = false;
    boolean division = false;
    boolean power = false;
    boolean Nroot = false;
    boolean equal = false;
    boolean number = false;


    public static TextView textView;
    public static final String HISTORY_EXTRA = "com.example.listview.HISTORY";
    public static final String HISTORY_ITEM_VALUE = "";
    private ArrayList<String> history;
    private ArrayList<String> items;
    private HistoryFileModel historyFileModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");

        ArrayList<String> item = getIntent().getStringArrayListExtra(History.itemSelected);
        //String item = getIntent().getStringExtra(History.itemSelected);
        String indexSelected = getIntent().getStringExtra(History.index);
        //int index_selected = Integer.parseInt(indexSelected);

        try{
            historyFileModel = new HistoryFileModel(this);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        //history = new ArrayList<>();
        //items = new ArrayList<>();
        items = DataHolder.getInstance().people;
        history = historyFileModel.getHistory();

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        final MutableLiveData<String> numberData = viewModel.getNumberData("");
        //numberData.setValue(textView.getText().toString());
        textView.setText(numberData.getValue());

            /*String v = numberData.getValue();
            textView.setText(v == null ? "" : v);*/


        for(int i = 0; i < 10; i++){
            int resID = getResources().getIdentifier( "button"+i,  "id", getPackageName());
            final Button button = (Button) findViewById(resID);
            button.setBackgroundColor(Color.LTGRAY);
            button.setTextColor(Color.BLACK);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view){
                    number = true;
                    String currentText = textView.getText().toString();
                    String buttonText = button.getText().toString();

                    if(equal == true){
                        textView.setText(buttonText);
                        numberData.setValue(buttonText);
                        equal = false;
                    }else{
                        textView.setText(currentText+buttonText);
                        numberData.setValue(currentText+buttonText);
                    }

                }
            });
        }

        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = textView.getText().toString();
                String buttonText = buttonDot.getText().toString();
                if(textView.getText().toString().contains(".")){
                    textView.setText(buttonText);
                    equal = false;
                }

                else if(equal == true){
                    textView.setText(buttonText);
                    //numberData.setValue(buttonText);
                    equal = false;
                }else if(number == true){
                    textView.setText(currentText+buttonText);
                    number = false;
                }
                else{
                    textView.setText(buttonText+currentText);
                    //numberData.setValue(buttonText+currentText);
                }
            }
        });

        Button buttonAdd = findViewById(R.id.buttonPlus);
        Button buttonSub = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivided = findViewById(R.id.buttonDivided);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        Button buttonRoot = findViewById(R.id.buttonRoot);
        Button buttonSquare = findViewById(R.id.buttonSquare);
        Button buttonLog = findViewById(R.id.buttonLog);
        Button buttonLn = findViewById(R.id.buttonLn);
        Button buttonPower = findViewById(R.id.buttonPower);
        Button buttonNroot = findViewById(R.id.buttonRootRoot);
        Button buttonHistory = findViewById(R.id.buttonHistory);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });

        buttonClear.setBackgroundColor(Color.LTGRAY);
        buttonClear.setTextColor(Color.BLACK);
        buttonHistory.setBackgroundColor(Color.LTGRAY);
        buttonHistory.setTextColor(Color.BLACK);
        buttonAdd.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonAdd.setTextColor(Color.WHITE);
        buttonSub.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonSub.setTextColor(Color.WHITE);
        buttonMultiply.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonMultiply.setTextColor(Color.WHITE);
        buttonDivided.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonDivided.setTextColor(Color.WHITE);
        buttonPlusMinus.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonPlusMinus.setTextColor(Color.WHITE);
        buttonEqual.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonEqual.setTextColor(Color.WHITE);
        buttonDot.setBackgroundColor(getResources().getColor(R.color.pink));
        buttonDot.setTextColor(Color.WHITE);

        buttonPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = textView.getText().toString();
                String buttonText = "-";

                textView.setText(buttonText + currentText);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNum = Double.parseDouble(textView.getText().toString());
                textView.setText("");
                add = true;
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNum = Double.parseDouble(textView.getText().toString());
                textView.setText("");
                subtract = true;
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNum = Double.parseDouble(textView.getText().toString());
                textView.setText("");
                multiply = true;
            }
        });

        buttonDivided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNum = Double.parseDouble(textView.getText().toString());
                textView.setText("");
                division = true;
            }
        });

        if(buttonRoot == null){

        }else{
            buttonRoot.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonRoot.setTextColor(Color.WHITE);
            buttonRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    result = Math.sqrt(currentNum);
                    textView.setText(String.valueOf(result));
                    //numberData.setValue(String.valueOf(result));
                    history.add("2" + "√" + currentNum);
                    items.add(String.valueOf(result));
                }
            });
        }

        if(buttonSquare == null){

        }else{
            buttonSquare.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonSquare.setTextColor(Color.WHITE);
            buttonSquare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    result = Math.pow(currentNum, 2);
                    textView.setText(String.valueOf(result));
                    //numberData.setValue(String.valueOf(result));
                    history.add(currentNum + "^2");
                    items.add(String.valueOf(result));

                }
            });
        }

        if(buttonLog == null){
        }else{
            buttonLog.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonLog.setTextColor(Color.WHITE);
            buttonLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    result = Math.log10(currentNum);
                    textView.setText(String.valueOf(result));
                    //numberData.setValue(String.valueOf(result));
                    history.add("log(" + currentNum + ")");
                    items.add(String.valueOf(result));
                }
            });
        }

        if(buttonLn == null){

        }else{
            buttonLn.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonLn.setTextColor(Color.WHITE);
            buttonLn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    result = Math.log(currentNum);
                    textView.setText(String.valueOf(result));
                    //numberData.setValue(String.valueOf(result));
                    history.add("ln(" + currentNum + ")");
                    items.add(String.valueOf(result));

                }
            });
        }

        if(buttonPower == null){

        }else{
            buttonPower.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonPower.setTextColor(Color.WHITE);
            buttonPower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    textView.setText("");
                    power = true;

                }
            });
        }

        if(buttonNroot == null){
        }else{
            buttonNroot.setBackgroundColor(getResources().getColor(R.color.pink));
            buttonNroot.setTextColor(Color.WHITE);
            buttonNroot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNum = Double.parseDouble(textView.getText().toString());
                    textView.setText("");
                    Nroot = true;
                    history.add(nextNum + "√" + currentNum);
                }
            });
        }

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equal = true;
                nextNum = Double.parseDouble(textView.getText().toString());
                if(add == true){
                    result = currentNum + nextNum;
                    add = false;
                    history.add(currentNum + " + " + nextNum);
                    items.add(String.valueOf(result));
                    Log.d("history", "Selected: " + items);
                }
                if(subtract == true){
                    result = currentNum - nextNum;
                    subtract = false;
                    history.add(currentNum + " - " + nextNum);
                    items.add(String.valueOf(result));
                }
                if (multiply == true){
                    result = currentNum * nextNum;
                    multiply = false;
                    history.add(currentNum + " * " + nextNum);
                    items.add(String.valueOf(result));
                }
                if(division == true){
                    result = currentNum / nextNum;
                    division = false;
                    history.add(currentNum + " ÷ " + nextNum);
                    items.add(String.valueOf(result));
                }
                if(power == true){
                    result = Math.pow(currentNum, nextNum);
                    power = false;
                    history.add(currentNum + "^" + nextNum);
                    items.add(String.valueOf(result));
                }
                if(Nroot == true){
                    double n = 1/nextNum;
                    result = Math.pow(currentNum, n);
                    Nroot = false;
                    items.add(String.valueOf(result));
                }
                textView.setText(String.valueOf(result));
                numberData.setValue(String.valueOf(result));
            }
        });

        try {
            if (history.size() != 0) {
                textView.setText(item.get(Integer.parseInt(indexSelected)));
                //textView.setText("88");
                //textView.setText(item.get(2));
                //textView.setText(item);
            }
        }catch(IndexOutOfBoundsException io){

        }catch(NullPointerException n){}
        catch(NumberFormatException N){}

        Log.d("history", "Selected: " + item);



    }

    @Override
    protected void onPause() {  // x
        try{
            historyFileModel.save();
        }catch(IOException e){
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Intent intent = getIntent();
        String x = intent.getStringExtra(History.itemSelected);
        textView.setText(x);
        super.onRestart();
    }

    public void toHistory(View view){
        Intent intent = new Intent(MainActivity.this, History.class);
        intent.putStringArrayListExtra(HISTORY_EXTRA, history);
        intent.putStringArrayListExtra(HISTORY_ITEM_VALUE, items);
        startActivity(intent);
    }

    public void addHistory(View view){
        String record = textView.getText().toString();
        if(!record.isEmpty()){
            history.add(record);
        }
    }



}