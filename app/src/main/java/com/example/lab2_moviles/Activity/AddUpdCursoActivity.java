package com.example.lab2_moviles.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_moviles.LogicaNegocio.Curso;
import com.example.lab2_moviles.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddUpdCursoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText creditosFld;
    private EditText horasFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_curso);
        editable = true;

        fBtn = findViewById(R.id.finishBtn);

        codFld = findViewById(R.id.codigoCurso);
        nomFld = findViewById(R.id.nombreCurso);
        creditosFld = findViewById(R.id.CantidadCreditosCurso);
        horasFld = findViewById(R.id.horasSemanalesCurso);
        codFld.setText("");
        nomFld.setText("");
        creditosFld.setText("");
        horasFld.setText("");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {
                Curso aux = (Curso) getIntent().getSerializableExtra("curso");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                creditosFld.setText(Integer.toString(aux.getCreditos()));
                horasFld.setText(Integer.toString(aux.getHoras()));
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }

    public void addCurso() {
        if (validateForm()) {
            Curso cur = new Curso(codFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            intent.putExtra("addCurso", cur);
            startActivity(intent);
            finish();
        }
    }
















    public void editCurso() {
        if (validateForm()) {
            Curso cur = new Curso(codFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            intent.putExtra("editCurso", cur);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.creditosFld.getText())) {
            creditosFld.setError("Creditos requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.horasFld.getText())) {
            horasFld.setError("Horas requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

