package com.agrobot.calculadora_imc_tmb_get;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.widget.Toast;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import com.agrobot.calculadora_imc_tmb_get.databinding.ActivityMainBinding;
        import com.agrobot.calculadora_imc_tmb_get.databinding.DialogInfoImcBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botaoVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        binding.botaoCalcular.setOnClickListener(v -> calcular());
        binding.botaoInfo.setOnClickListener(v -> infoIMC());
    }

    private void calcular() {
        Double peso = parseDouble(binding.campoPeso.getText().toString().replace(",", "."));
        Double altura = parseDouble(binding.campoAltura.getText().toString().replace(",", "."));
        double imc;

        if (peso == null || altura == null) {
            Toast.makeText(this, "Por favor preencha todos os campos.", Toast.LENGTH_SHORT).show();
        } else {
            imc = peso / (altura * altura); // IMC = peso (kg) / altura (m) x altura (m)

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("imc", Double.toString(imc));

            startActivity(intent);
        }
    }

    private void infoIMC() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        DialogInfoImcBinding dialogBinding = DialogInfoImcBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());

        dialogBinding.button.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}