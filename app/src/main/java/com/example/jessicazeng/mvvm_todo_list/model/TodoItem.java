package com.example.jessicazeng.mvvm_todo_list.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;


/**
 * Created by jessicazeng on 2/10/16.
 */
public class TodoItem extends BaseObservable{
    public final ObservableField<String> state = new ObservableField<>();
    public final ObservableField<String> event = new ObservableField<>();
}
