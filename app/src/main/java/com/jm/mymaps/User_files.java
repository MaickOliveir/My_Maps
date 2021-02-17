package com.jm.mymaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User_files extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    FirebaseDatabase rootNode;
    FirebaseFirestore firebaseFirestore;
    RecyclerView mRecyclerView;
    FirestoreRecyclerAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_files);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mRecyclerView = findViewById(R.id.mrecyclerView);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        CollectionReference query = firebaseFirestore.collection("users").document(signInAccount.getId()).collection("user_maps");
        //recycler options
        FirestoreRecyclerOptions<UserHelperClass> options = new FirestoreRecyclerOptions.Builder<UserHelperClass>()
                .setQuery(query, UserHelperClass.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<UserHelperClass, viewholderHelperclass>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewholderHelperclass holder, int position, @NonNull UserHelperClass model) {

                holder.tv_recyclerView.setText(model.getMapName());

            }

            @NonNull
            @Override
            public viewholderHelperclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new viewholderHelperclass(view);
            }
        };

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    private static class viewholderHelperclass extends RecyclerView.ViewHolder {

        private final TextView tv_recyclerView;
        private ImageView image;
        private ImageButton edit_item_recycler;


        public viewholderHelperclass(@NonNull View itemView) {
            super(itemView);

            tv_recyclerView = itemView.findViewById(R.id.tv_recyclerView);
            image = itemView.findViewById(R.id.image);
            edit_item_recycler = itemView.findViewById(R.id.edit_item_recycler);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public void show_menu_recyclerView(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_recyclerview);
        popupMenu.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_menu_recycler:
                openMapInformation();
                return true;
            case R.id.delete_menu_recycler:
                return true;
            default:
                return false;
        }
    }

    private void openMapInformation() {
        Intent intent = new Intent(this, map_information.class);
        startActivity(intent);
    }

    public void open_addMaps(View view) {
        Intent i= new Intent(this, add_map.class);
        startActivity(i);
    }
}


