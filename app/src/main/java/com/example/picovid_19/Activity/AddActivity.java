package com.example.picovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.picovid_19.Model.NewsModel;
import com.example.picovid_19.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private DatabaseReference database;
    private EditText etNama, etEmail, etDesk;
    private ProgressDialog loading;
    private Button btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Deklarasi firebase dengan reference "news"
        database = FirebaseDatabase.getInstance().getReference("news");

        //Insial Id Layout dari activity_add
        etNama = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etDesk = findViewById(R.id.et_desk);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        //Aksi ketika tombol save di klik
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Deklarasi variabel sementara mengampung nilai id layout
                String Snama = etNama.getText().toString();
                String Semail = etEmail.getText().toString();
                String Sdesk = etDesk.getText().toString();

                    //Percabangan Inputan tidak boleh kosong
                    if (Snama.equals("")) {
                        etNama.setError("Silahkan masukkan nama");
                        etNama.requestFocus();
                    } else if (Semail.equals("")) {
                        etEmail.setError("Silahkan masukkan email");
                        etEmail.requestFocus();
                    } else if (Sdesk.equals("")) {
                        etDesk.setError("Silahkan masukkan deskripsi");
                        etDesk.requestFocus();
                    } else {
                        //tampilan proses dialog activity
                        loading = ProgressDialog.show(AddActivity.this,
                                null,
                                "Mohon Tunggu...",
                                true,
                                false);
                        //memanggil method saveData
                        saveData(new NewsModel(Snama,Semail,Sdesk,""));
                    }
            }
        });
        //Aksi ketika tombol cancel di klik
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Method ini berguna untuk mengambil Insialiasai dari newsModel dan menambahkan ke firebase
    private void saveData(NewsModel newsModel) {
        //membuat key uniq
        String userId = database.push().getKey();

        //memasukkan data dengan mengambil key Unik
        database.child(userId).setValue(newsModel).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();
                        etNama.setText("");
                        etEmail.setText("");
                        etDesk.setText("");

                        //Toast ketika berhasil data ditambahkan
                        Toast.makeText(AddActivity.this,
                                "Data Berhasil Ditambahkan",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
