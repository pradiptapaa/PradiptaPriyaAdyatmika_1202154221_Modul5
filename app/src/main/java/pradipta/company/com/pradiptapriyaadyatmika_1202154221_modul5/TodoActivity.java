package pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.database.DatabaseHelper;
import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.model.Todo;

public class TodoActivity extends AppCompatActivity {

    private EditText toDo, description, priority;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        toDo = (EditText) findViewById(R.id.todoInput);
        description = (EditText) findViewById(R.id.descriptionInput);
        priority = (EditText) findViewById(R.id.priorityInput);
        db = new DatabaseHelper(this);
    }

    public void add(View view) {
        if (db.insertTodo(new Todo(toDo.getText().toString(), description.getText().toString(), priority.getText().toString()))){
            startActivity(new Intent(TodoActivity.this, MainActivity.class)); //intent ke mainActivity
            this.finish(); //menutup aktivitas setelah intent selesai dijalankan
        }else {
            Toast.makeText(this, "To Do List Can't Empty", Toast.LENGTH_SHORT).show();
            toDo.setText(null);
            description.setText(null);
            priority.setText(null);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TodoActivity.this, MainActivity.class); //intent untuk pindah ke MainActivity
        startActivity(intent); //memulai intent
        this.finish(); //menutup aktivitas setelah intent selesai dijalankan
    }


}
