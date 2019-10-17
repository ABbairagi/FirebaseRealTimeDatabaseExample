package c.tatastrive.firebaserealtimedatabaseexample;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.DateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_UI extends AppCompatActivity {
    public static String test = "Your Task App";
    String uId = "Testing";
    static DatabaseReference databaseReference;

    EditText e_title,e_notes,editTextTitleUpdate,editTextNotesUpdate ;
    Button btn_save,btn_update;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_input_field);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TaskNote").child(uId);
        e_title=findViewById(R.id.edit_Title);
        e_notes=findViewById(R.id.edit_Notes);
        btn_save=findViewById(R.id.btn_Save);

        editTextNotesUpdate=findViewById(R.id.)


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_title = e_title.getText().toString().trim();
                String s_notes = e_notes.getText().toString().trim();
                if (TextUtils.isEmpty(s_title)) {
                    e_title.setError("Required Field.. ");
                    return;
                }
                if (TextUtils.isEmpty(s_notes)) {
                    e_notes.setError("Required Field.. ");
                    return;
                }

                String s_id = databaseReference.push().getKey();
                String s_date = DateFormat.getDateInstance().format(new Date());
                Data data = new Data(s_title, s_notes, s_date, s_id);
                if (s_id != null) {
                    databaseReference.child(s_id).setValue(data);
                }
                Toast.makeText(Activity_UI.this, "Data Inserted ", Toast.LENGTH_SHORT).show();
                //alertDialog.dismiss();

            }
        });
    }
}
