package com.example.calculatorapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryFileModel {

    public static final String FILE_NAME = "history_file_model.json";
    public static final String HISTORY_KEY = "history";
    private ArrayList<String> history;
    private Context context;
    private File file;
    private Gson gson;

    public HistoryFileModel(Context context) throws IOException{
        this.context = context;
        this.gson = new Gson();
        this.file = new File(context.getFilesDir(), FILE_NAME);
        load();
    }

    public void save() throws IOException{
        HashMap<String, Object> map = new HashMap<>();
        map.put(HISTORY_KEY, history);

        String jsonStr = gson.toJson(map);

        write(jsonStr);
    }

    public void load() throws IOException{
        String jsonStr = read();

        Map jsonObj = gson.fromJson(jsonStr, Map.class);

        if(history == null){
            List list = (List)jsonObj.get(HISTORY_KEY);
            if(list == null){
                list = new ArrayList<>();
            }
            history = new ArrayList<>(list);
        }else{
            history.clear();
            history.addAll((List)jsonObj.get(HISTORY_KEY));

        }//history.clear();


    }

    public ArrayList<String> getHistory(){
        return history;
    }

    public void createEmptyFile() throws IOException{
        Log.d("history_file", "Creating empty file");

        HashMap<String, Object> map = new HashMap<>();
        map.put(HISTORY_KEY, new ArrayList<>());

        String jsonStr = gson.toJson(map);

        write(jsonStr);
    }

    private String read() throws IOException{
        Log.d("history_file", "Reading from file");

        FileReader fileReader = null;
        try{
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while(line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            return stringBuilder.toString();
        } catch(FileNotFoundException e){
            Log.d("history_file", "File not found");
            createEmptyFile();
            return read();
        }finally {
            if(fileReader != null){
                fileReader.close();
            }
        }
    }

    private void write(String jsonStr) throws IOException{
        Log.d("history_file", "Writing to file");

        FileWriter fileWriter = null;

        try{
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonStr);
            bufferedWriter.flush();
        }finally {
            if(fileWriter != null){
                fileWriter.close();
            }
        }
    }


}
