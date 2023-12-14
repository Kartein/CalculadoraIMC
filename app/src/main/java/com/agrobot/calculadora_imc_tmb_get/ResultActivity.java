package com.agrobot.calculadora_imc_tmb_get;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import com.agrobot.calculadora_imc_tmb_get.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botaoVoltar.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        double resultado = Double.parseDouble(getIntent().getStringExtra("imc"));
        binding.resultadoIMC.setText(String.format("%.1f", resultado));

        if (resultado < 16.0) {
            binding.imagemMedidor.setImageResource(R.drawable.magreza_grave);
            binding.magrezaGrave.setTextColor(Color.parseColor("#0C4D8B"));
            binding.intervaloMagrezaGrave.setTextColor(Color.parseColor("#0C4D8B"));
        } else if (resultado >= 16 && resultado < 17) {
            binding.imagemMedidor.setImageResource(R.drawable.magreza_moderada);
            binding.magrezaModerada.setTextColor(Color.parseColor("#0377BE"));
            binding.intervaloMagrezaModerada.setTextColor(Color.parseColor("#0377BE"));
        } else if (resultado >= 17 && resultado < 18.5) {
            binding.imagemMedidor.setImageResource(R.drawable.abaixo_do_peso);
            binding.magrezaLeve.setTextColor(Color.parseColor("#2196F3"));
            binding.intervaloMagrezaLeve.setTextColor(Color.parseColor("#2196F3"));
        } else if (resultado >= 18.5 && resultado < 25) {
            binding.imagemMedidor.setImageResource(R.drawable.saudavel);
        } else if (resultado >= 25 && resultado < 30) {
            binding.imagemMedidor.setImageResource(R.drawable.sobrepeso);
        } else if (resultado >= 30 && resultado < 35) {
            binding.imagemMedidor.setImageResource(R.drawable.obesidade_grau_i);
        } else if (resultado >= 35 && resultado < 40) {
            binding.imagemMedidor.setImageResource(R.drawable.obesidade_grau_ii);
        } else {
            binding.imagemMedidor.setImageResource(R.drawable.obesidade_grau_iii);
        }
    }
}