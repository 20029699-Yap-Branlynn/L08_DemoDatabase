package sg.edu.rp.c346.id20029699.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnGetTasks;
    TextView tvResults;
    ListView lw;
    EditText etTask, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lw = findViewById(R.id.listView);
        etTask = findViewById(R.id.editTextTask);
        etDate = findViewById(R.id.editTextDate);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String taskData = etTask.getText().toString();
                String dateData = etDate.getText().toString();

                // Insert a task
                db.insertTask(taskData, dateData);

                }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i+1 + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                DBHelper db2 = new DBHelper(MainActivity.this);
                ArrayList<Task> a1 = new ArrayList<Task>(db2.getTasks());
                db2.close();
                ArrayAdapter list = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, a1);
                lw.setAdapter(list);
            }
        });

    }
}