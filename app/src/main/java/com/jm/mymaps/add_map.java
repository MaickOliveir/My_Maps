package com.jm.mymaps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class add_map extends AppCompatActivity {

    private static final String TAG = null ;
    private static final String MAP_NAME = "mapName";
    Button bt_add_file;
    FloatingActionButton bt_add_file_user;
    TextView tv_pdf_name;
    EditText editText_state;
    EditText editText_city;
    EditText editText_mapName;
    Spinner spinner;
    EditText editText_observation;
    StorageReference storageRef;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_map);

        storageRef = FirebaseStorage.getInstance().getReference();

        editText_mapName = findViewById(R.id.editText_mapName);
        editText_state = findViewById(R.id.editText_state);
        editText_city = findViewById(R.id.editText_city);
        spinner = findViewById(R.id.spinner);
        editText_observation = findViewById(R.id.editText_observation);


        tv_pdf_name = findViewById(R.id.tv_pdf_name);
        bt_add_file = findViewById(R.id.bt_add_file);
        bt_add_file.setOnClickListener(v -> selectPDF());

        bt_add_file_user = findViewById(R.id.bt_add_file_user);
        bt_add_file_user.setOnClickListener(v -> { storeUserData(); openUserFiles();


        });
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12 && resultCode == RESULT_OK && data != null && data.getData() != null) {

                uploadPDFFile(data.getData());
            }
        }


    @SuppressLint("SetTextI18n")
    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageRef.child("Uploads/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(taskSnapshot -> {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri uri = uriTask.getResult();

                    Toast.makeText(this, "File Uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();


                }).addOnProgressListener(snapshot -> {
        });

        // Display file Name/Size
       Cursor cursor = getContentResolver()
                .query(data, null, null, null, null, null);

        try {
            // moveToFirst() returns false if the cursor has 0 rows. Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name". This is
                // provider-specific, and might not necessarily be the file name.
                String displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

               int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
               String size;
                if (!cursor.isNull(sizeIndex)) {
                    size = cursor.getString(sizeIndex);
                   tv_pdf_name.setText(displayName + " (" + toMb(Integer.parseInt(size)) + ")");

                } else {

                }
            }
        } finally {
            cursor.close();
        }
    }

   private String toMb(int bytes) {
        return Formatter.formatFileSize(this, bytes);
    }


    private void storeUserData() {
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        String mapName = editText_mapName.getText().toString();
        String state = editText_state.getText().toString();
        String city = editText_city.getText().toString();
        String observation = editText_observation.getText().toString();
        String mapType = spinner.getSelectedItem().toString();

        DatabaseReference myRef = database.getReference("users").child(signInAccount.getId()).child(mapName);
        UserHelperClass helperClass = new UserHelperClass(mapName, state, city, observation, mapType);

        myRef.setValue(helperClass);
        // Add a new document with a generated ID
    }

    private void openUserFiles() {
        Intent intent = new Intent(this, UserFiles.class);
        startActivity(intent);
    }
}