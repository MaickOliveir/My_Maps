package com.jm.mymaps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class map_information extends AppCompatActivity {

    private static final String TAG = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView editText_state;
    TextView editText_city;
    TextView editText_mapName;
    Spinner spinner;
    TextView editText_observation;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_information);

        editText_mapName = findViewById(R.id.editText_mapName);
        editText_state = findViewById(R.id.editText_state);
        editText_city = findViewById(R.id.editText_city);
        spinner = findViewById(R.id.spinner);
        editText_observation = findViewById(R.id.editText_observation);


        String mapName = editText_mapName.getText().toString();
        String state = editText_state.getText().toString();
        String city = editText_city.getText().toString();
        String observation = editText_observation.getText().toString();
        String mapType = spinner.getSelectedItem().toString();

        UserHelperClass helperClass = new UserHelperClass(mapName, state, city, observation, mapType);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

    }
}