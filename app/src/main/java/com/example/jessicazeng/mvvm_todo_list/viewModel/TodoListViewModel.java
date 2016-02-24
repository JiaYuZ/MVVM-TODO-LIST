package com.example.jessicazeng.mvvm_todo_list.viewModel;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jessicazeng.mvvm_todo_list.R;
import com.example.jessicazeng.mvvm_todo_list.databinding.TodoListViewBinding;
import com.example.jessicazeng.mvvm_todo_list.model.TodoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jessicazeng on 2/10/16.
 */
public class TodoListViewModel extends Fragment {
    public List<TodoItem> todoLists;

    public TodoListAdapter adapter;

    TodoListViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.todo_list_view, container, false);

        binding = DataBindingUtil.setContentView(getActivity(), R.layout.todo_list_view);

        todoLists = new ArrayList<>();
        adapter = new TodoListAdapter(getActivity(), todoLists);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(LayoutManager);
        binding.recyclerView.setAdapter(adapter);

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddItem(v);
            }
        });

        return view;
    }

    public void onAddItem(View view) {
        String inputStr = binding.editNewItem.getText().toString();

        if(TextUtils.isEmpty(inputStr)) {
            Toast.makeText(getActivity(), R.string.empty_input, Toast.LENGTH_LONG).show();
            return;
        }

        TodoItem todoItem = new TodoItem();

        todoItem.event.set(inputStr);
        todoItem.state.set(getResources().getString(R.string.undo_state));

        todoLists.add(todoItem);

        adapter.notifyDataSetChanged();
    }
}
