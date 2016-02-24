package com.example.jessicazeng.mvvm_todo_list;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jessicazeng.mvvm_todo_list.viewModel.TodoListViewModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TodoListViewModel todoListViewModel = new TodoListViewModel();

        fragmentTransaction.add(R.id.main_layout, todoListViewModel);
        fragmentTransaction.commit();
    }
}
