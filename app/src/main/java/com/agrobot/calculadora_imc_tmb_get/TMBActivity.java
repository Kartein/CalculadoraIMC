package com.agrobot.calculadora_imc_tmb_get;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.widget.SeekBar;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import com.agrobot.calculadora_imc_tmb_get.databinding.ActivityTmbactivityBinding;
        import com.agrobot.calculadora_imc_tmb_get.databinding.DialogInfoTmbBinding;
        import com.agrobot.calculadora_imc_tmb_get.databinding.DialogTmbBinding;

        import java.text.DecimalFormat;

public class TMBActivity extends AppCompatActivity {
    private ActivityTmbactivityBinding binding;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTmbactivityBinding.inflate(getLayoutInflater());
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

        binding.botaoCalcularTmb.setOnClickListener(v -> calcularTMB(idade, altura, peso));
        binding.botaoInfo.setOnClickListener(v -> infoTMB());
    }

    private void calcularTMB(int idade, int altura, double peso) {
        double tmb = 0.0;

        if (binding.chipMasculino.isChecked()) {
            tmb = 66.47 + (13.75 * peso) + (5.0 * altura) - (6.76 * idade);
            dialogTMB(tmb);
        } else if (binding.chipFeminino.isChecked()) {
            tmb = 655.1 + (9.56 * peso) + (1.85 * altura) - (4.68 * idade);
            dialogTMB(tmb);
        }
    }

    private void dialogTMB(double tmb) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        DialogTmbBinding dialogBinding = DialogTmbBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());

        String formattedTmb = new DecimalFormat("#.##").format(tmb);
        dialogBinding.textViewTMB.setText(formattedTmb.replace(".", ",") + " calorias");
        dialogBinding.buttonTMB.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void infoTMB() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_Dialog);
        DialogInfoTmbBinding dialogBinding = DialogInfoTmbBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());

        dialogBinding.button.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog = builder.create();
        alertDialog.show();
    }
}