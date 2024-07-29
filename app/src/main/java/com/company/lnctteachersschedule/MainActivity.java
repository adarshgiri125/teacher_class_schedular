package com.company.lnctteachersschedule;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Spinner daySpinner = findViewById(R.id.days);
        Spinner periodSpinner = findViewById(R.id.period);
        Button submitButton = findViewById(R.id.button);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<Teacher> teachers = new ArrayList<>();
        TeacherAdapter adapter = new TeacherAdapter(teachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        submitButton.setOnClickListener(v -> {
            String inputDay = daySpinner.getSelectedItem().toString();
            String inputPeriod = periodSpinner.getSelectedItem().toString();

            if (!inputDay.isEmpty() && !inputPeriod.isEmpty()) {
                String value = inputDay + "_" + inputPeriod;
                Log.d("checkmate", "onCreate: " + value);

                db.collection("teachers")
                        .whereEqualTo(value, "")
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            List<Teacher> teachersList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                String name = document.getString("Name");
                                String teacherCode = document.getString("Teacher_code");
                                String contactNo = document.getString("contact_no");

                                if (name != null && teacherCode != null && contactNo != null) {
                                    Teacher teacher = new Teacher(name, teacherCode, contactNo);
                                    teachersList.add(teacher);
                                } else {
                                    Log.d("checkmac", "Some fields are missing in the document " + document.getId());
                                }
                            }
                            adapter.updateData(teachersList);
                        })
                        .addOnFailureListener(e -> Log.w("checkmac", "Error getting documents: ", e));

                Toast toast = Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT);
                int toastDurationInMilliseconds = 500; // 0.5 seconds

                new Handler().postDelayed(toast::cancel, toastDurationInMilliseconds);

                toast.show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter a valid day and period.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
