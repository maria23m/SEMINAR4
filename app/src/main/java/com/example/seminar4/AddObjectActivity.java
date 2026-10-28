package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddObjectActivity extends AppCompatActivity {

    private EditText etMarca, etAnFabricatie;
    private CheckBox cbEsteNou;
    private RadioGroup rgCombustibil;
    private Spinner spCategorie;
    private RatingBar ratingBar;
    private Switch swInmatriculat;
    private Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);

        etMarca = findViewById(R.id.etMarca);
        etAnFabricatie = findViewById(R.id.etAnFabricatie);
        cbEsteNou = findViewById(R.id.cbEsteNou);
        rgCombustibil = findViewById(R.id.rgCombustibil);
        spCategorie = findViewById(R.id.spCategorie);
        ratingBar = findViewById(R.id.ratingBar);
        swInmatriculat = findViewById(R.id.swInmatriculat);
        btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] categorii = {"Sedan", "SUV", "Hatchback", "Coupe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorii);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategorie.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> salveazaSiTrimiteData());
    }

    private void salveazaSiTrimiteData() {
        String marca = etMarca.getText().toString().trim();
        String anStr = etAnFabricatie.getText().toString().trim();

        if (marca.isEmpty() || anStr.isEmpty()) {
            Toast.makeText(this, "Completați marca și anul!", Toast.LENGTH_SHORT).show();
            return;
        }

        int anFabricatie = Integer.parseInt(anStr);
        boolean esteNou = cbEsteNou.isChecked();

        int selectedRadioId = rgCombustibil.getCheckedRadioButtonId();
        RadioButton rbSelectat = findViewById(selectedRadioId);
        String tipCombustibil = rbSelectat != null ? rbSelectat.getText().toString() : "Nespecificat";

        String categorie = spCategorie.getSelectedItem().toString();
        float rating = ratingBar.getRating();
        boolean esteInmatriculat = swInmatriculat.isChecked();

        AutomobilZian automobil = new AutomobilZian(marca, anFabricatie, esteNou, tipCombustibil, categorie, rating, esteInmatriculat);

        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("AUTOMOBIL_KEY", automobil);
        resultIntent.putExtras(bundle);

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}