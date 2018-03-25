package pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.R;
import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.model.Todo;

/**
 * Created by monoc on 3/24/2018.
 */
//extend recyclerview->adapter-myviewholder
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    //deklarasikan variabel yang akan digunakan
    private Context mContext;
    private List<Todo> todosList;
    int color;

    //kelas MyViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //deklarasikan variabel yang akan panggil/digunakan pada adapter
        public TextView tvTodo;
        public TextView tvDescription;
        public TextView tvPriority;

        public CardView cardView;

        //pasang variabel dengan view yang ada pada layout
        public MyViewHolder(View view) {
            super(view);
            tvTodo = view.findViewById(R.id.todoList);
            tvDescription = view.findViewById(R.id.descriptionList);
            tvPriority = view.findViewById(R.id.priorityList);
            cardView = view.findViewById(R.id.cardview);
        }
    }


    //buat konstruktor kelas TodoAdapter
    public TodoAdapter(Context context, List<Todo> todosList, int color) {
        this.mContext = context;
        this.todosList = todosList;
        this.color = color;
    }

    //saat ViewHolder dibuat ...
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //tiup view yang sudah didefinisikan ke layout todo_list_row
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        //kembalikan nilai MyViewHolder yang baru
        return new MyViewHolder(itemView);
    }

    //saat mengikat ViewHolder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //ambil posisi List
        Todo todo = todosList.get(position);

        //pasang view yang sudah dipasang dengan variabel ke holder
        holder.tvTodo.setText(todo.getTodo());
        holder.tvDescription.setText(todo.getDescription());
        holder.tvPriority.setText(todo.getPriority());
        holder.cardView.setBackgroundColor(mContext.getResources().getColor(this.color));
    }

    //ambil ukuran list
    @Override
    public int getItemCount() {
        return todosList.size();
    }

    //method ambil data
    public Todo getData(int position){
        return todosList.get(position);
    }


    //method hapus data
    public void deleteData(int i){
        todosList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, todosList.size());
    }
}