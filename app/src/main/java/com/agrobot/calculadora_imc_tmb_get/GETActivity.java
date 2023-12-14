package com.agrobot.calculadora_imc_tmb_get;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.widget.SeekBar;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import com.agrobot.calculadora_imc_tmb_get.databinding.ActivityGetactivityBinding;
        import com.agrobot.calculadora_imc_tmb_get.databinding.DialogGetBinding;
        import com.agrobot.calculadora_imc_tmb_get.databinding.DialogInfoGetBinding;

        import java.text.DecimalFormat;

public class GETActivity extends AppCompatActivity {
    private ActivityGetactivityBinding binding;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botaoVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        int idade = 0;
        int altura = 0;
        double peso = 0.0;

        binding.seekBarIdade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textViewIdade.setText(progress + " anos");
                idade = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.seekBarAltura.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                altura = progress;
                binding.textViewAltura.setText(altura + "cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.seekBarPeso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                peso = progress / 100.00;
                binding.textViewPeso.setText(peso + "kg");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.botaoCalcularGet.setOnClickListener(v -> calcularGET(idade, altura, peso));
        binding.botaoInfo.setOnClickListener(v -> infoGET());
    }

    private void calcularGET(int idade, int altura, double peso) {
        double tmb = 0.0;

        if (binding.chipMasculino.isChecked()) {
            tmb = 66.47 + (13.75 * peso) + (5.0 * altura) - (6.76 * idade);
        } else if (binding.chipFeminino.isChecked()) {
            tmb = 655.1 + (9.56 * peso) + (1.85 * altura) - (4.68 * idade);
        }

        double get = 0.0;
        if (binding.chipNenhuma.isChecked()) {
            get = tmb * 1.2;
            dialogGET(get);
        } else if (binding.chipLeve.isChecked()) {
            get = tmb * 1.375;
            dialogGET(get);
        } else if (binding.chipModerada.isChecked()) {
            get = tmb * 1.55;
            dialogGET(get);
        } else if (binding.chipMuito.isChecked()) {
            get = tmb * 1.725;
            dialogGET(get);
        } else if (binding.chipExtrema.isChecked()) {
            get = tmb * 1.9;
            dialogGET(get);
        }
    }

    private void dialogGET(double get) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        DialogGetBinding dialogBinding = DialogGetBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());

        dialogBinding.textViewGET.setText(new DecimalFormat("#.##").format(get) + " calorias");
        dialogBinding.buttonGET.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void infoGET() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        DialogInfoGetBinding dialogBinding = DialogInfoGetBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());

        dialogBinding.button.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }
}
