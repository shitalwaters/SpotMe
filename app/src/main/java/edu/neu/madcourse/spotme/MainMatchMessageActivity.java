package edu.neu.madcourse.spotme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import edu.neu.madcourse.spotme.database.models.Match;

public class MainMatchMessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<MessageMatchModel> matchList;
    private MatchesRViewAdapter adapter;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private Query query;

    private FirebaseAuth mAuth;
    private String loginId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_matches);
        recyclerView = findViewById(R.id.matchedRView);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        loginId = mAuth.getCurrentUser().getEmail();
        query = firebaseFirestore.collection("matches").document(loginId).collection("swiped").whereEqualTo("match", true);
        initialRView();
    }

    private void initialRView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        FirestoreRecyclerOptions<Match> options = new FirestoreRecyclerOptions.Builder<Match>()
                .setQuery(query, Match.class)
                .build();
        adapter = new MatchesRViewAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    private void initialDate() {
//
//        matchList = new ArrayList<>();
//
//        matchList.add(new MessageMatchModel("Adam Smith", "10-10-1000",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_one_24));
//
//        matchList.add(new MessageMatchModel("Human Being", "20-20-2000",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_one_24));
//
//        matchList.add(new MessageMatchModel("Another Being", "11-11-1111",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_two_24));
//
//        matchList.add(new MessageMatchModel("Yup Human", "90-09-1590",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_3_24));
//
//        matchList.add(new MessageMatchModel("Adam Smith", "10-10-1000",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_one_24));
//
//        matchList.add(new MessageMatchModel("Human Being", "20-20-2000",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_one_24));
//
//        matchList.add(new MessageMatchModel("Another Being", "11-11-1111",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_two_24));
//
//        matchList.add(new MessageMatchModel("Yup Human", "90-09-1590",
//                R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_looks_3_24));
//
//    }

}