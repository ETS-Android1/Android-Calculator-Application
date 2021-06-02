package com.example.calculatorapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> numberData;

    public MutableLiveData<String> getNumberData(String defaultValue){
        if(numberData == null){
            numberData = new MutableLiveData<>();
            numberData.setValue(defaultValue);
        }
        return numberData;
    }

}