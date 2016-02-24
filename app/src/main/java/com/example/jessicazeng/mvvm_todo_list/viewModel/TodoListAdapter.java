package com.example.jessicazeng.mvvm_todo_list.viewModel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jessicazeng.mvvm_todo_list.R;
import com.example.jessicazeng.mvvm_todo_list.databinding.TodoListRowBinding;
import com.example.jessicazeng.mvvm_todo_list.model.TodoItem;

import java.util.List;

/**
 * Created by jessicazeng on 2/10/16.
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private List<TodoItem> todoLists;
    private final LayoutInflater inflater;
    private Context context;
    private TodoListRowBinding binding;

    public TodoListAdapter(Context context, List<TodoItem> todoLists) {
        inflater = LayoutInflater.from(context);
        this.todoLists = todoLists;
        this.context = context;
    }

    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.todo_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoListAdapter.ViewHolder holder, int position) {
        TodoItem item = todoLists.get(position);
        holder.takeItem(item);
    }

    @Override
    public int getItemCount() {
        if(todoLists != null) {
            return todoLists.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return DataBindingUtil.getBinding(itemView);
        }

        public void takeItem(final TodoItem item) {
            getBinding().setVariable(com.example.jessicazeng.mvvm_todo_list.BR.todoitem, item);
            getBinding().executePendingBindings();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.state.get().equals(context.getString(R.string.undo_state))) {
                        item.state.set(context.getString(R.string.done_state));
                    } else {
                        item.state.set(context.getString(R.string.undo_state));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    todoLists.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return false;
                }
            });
        }
    }


    public List<TodoItem> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoItem> todoLists) {
        this.todoLists = todoLists;
    }
}
