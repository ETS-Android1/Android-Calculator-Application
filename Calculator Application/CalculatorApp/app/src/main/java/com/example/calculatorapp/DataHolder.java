package com.example.calculatorapp;

import java.util.ArrayList;

class DataHolder {
    final ArrayList<String> people = new ArrayList<>();

    private DataHolder() {}

    static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}