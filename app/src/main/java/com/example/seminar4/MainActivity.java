package com.example.seminar4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private Button btnDeschideAdaugare, btnDeschideAdaugareAI;

    private final ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        AutomobilZian automobil = bundle.getParcelable("AUTOMOBIL_KEY");
                        if (automobil != null) {
                            tvRezultat.setText(automobil.toString());
                            Toast.makeText(MainActivity.this, "Obiect primit:\n" + automobil.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRezultat = findViewById(R.id.tvRezultat);
        btnDeschideAdaugare = findViewById(R.id.btnDeschideAdaugare);
        btnDeschideAdaugareAI = findViewById(R.id.btnDeschideAdaugareAI);

        btnDeschideAdaugare.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddObjectActivity.class);
            addActivityLauncher.launch(intent);
        });

        btnDeschideAdaugareAI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddObjectAiActivity.class);
            addActivityLauncher.launch(intent);
        });
    }
}