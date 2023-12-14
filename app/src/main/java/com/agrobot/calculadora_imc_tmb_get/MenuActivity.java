package com.agrobot.calculadora_imc_tmb_get;

        import android.content.Intent;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import com.agrobot.calculadora_imc_tmb_get.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botaoImc.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        binding.botaoTmb.setOnClickListener(v -> {
            Intent intent = new Intent(this, TMBActivity.class);
            startActivity(intent);
        });

        binding.botaoGet.setOnClickListener(v -> {
            Intent intent = new Intent(this, GETActivity.class);
            startActivity(intent);
        });
    }
}