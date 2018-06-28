package cn.lsz.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class RoundRetActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private Bitmap ori, change;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_ret);

        imageView = findViewById(R.id.round);
        ori = MainActivity.select;
        imageView.setImageBitmap(ori);

        SeekBar angle = findViewById(R.id.angle);
        angle.setMax(Math.min(ori.getWidth(), ori.getHeight()) / 2);
        angle.setProgress(0);
        angle.setOnSeekBarChangeListener(this);
    }

    public void btnSave(View view) {
        MainActivity.select = change;
        Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        change = ImageHelper.roundRect(ori, progress);
        imageView.setImageBitmap(change);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
