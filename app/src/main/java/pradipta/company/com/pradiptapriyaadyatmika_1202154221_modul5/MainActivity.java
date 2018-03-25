package pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.adapter.TodoAdapter;
import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.database.DatabaseHelper;
import pradipta.company.com.pradiptapriyaadyatmika_1202154221_modul5.model.Todo;

public class MainActivity extends AppCompatActivity {
    //deklarasikan variabel yang akan digunakan
    private TodoAdapter mAdapter;
    private ArrayList<Todo> todosList = new ArrayList<>();
    private RecyclerView recyclerView;
    //deklarasikan class database
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pasang recyclerview pada variabel
        recyclerView = findViewById(R.id.recyclerView);

        //buat object database
        db = new DatabaseHelper(this);

        //panggil data dari database berdasarkan list
        db.readdata(todosList);

        //buat variabel SharedPreference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);

        //buat variabel color untuk kontainer dari sharedpreference warna
        int color = sharedP.getInt("bgColor", R.color.white);


        //buat objek
        mAdapter = new TodoAdapter(this, todosList, color); //membuat adapter baru

        //recyclerview memiliki ukuran yang tetap
        recyclerView.setHasFixedSize(true);

        //pasang layout pada recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pasang adapter pada recyclerview
        recyclerView.setAdapter(mAdapter); //inisiasi adapter untuk recycler view

        //buat variabel FloatingButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //ketika fab ditekan
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TodoActivity.class));
            }
        });

        //jalankan method swipe
        swipe();
    }

    //method swipe
    public void swipe() {
        //buat objek dari ItemTouchHelp.SimpleCallback ke kiri
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //ketika digeser
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //ambil posisi adapter dari viewHolder
                int position = viewHolder.getAdapterPosition();

                //ambil data berdasarkan posisi dari adapter
                Todo current = mAdapter.getData(position);

                //jika arahnya ke kiri
                if (direction == ItemTouchHelper.LEFT) { //apabila item di swipe ke arah kiri

                    //hapus data dari database dengan todonya sebagai primary key
                    if (db.removedata(current.getTodo())) {
                        //hapus data
                        mAdapter.deleteData(position);
                    }
                }
            }
        };

        //buat objek touch helper
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);

        //pasang ke recyclerview
        touchhelp.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //pindah ke Setting Activity
            startActivity(new Intent(MainActivity.this, SettingActivity.class));

            //matikan activity
            finish();

        }

        return true;
    }
}
