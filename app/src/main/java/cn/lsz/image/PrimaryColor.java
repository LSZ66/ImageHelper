package cn.lsz.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class PrimaryColor extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int MAX = 255;
    private static final int MID = 127;
    private ImageView mImageView;
    private float mHue, mSat, mLum;
    private Bitmap bitmap, change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeekBar hueSeekBar, satSeekBar, lumSeekBar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);
        bitmap = MainActivity.select;

        mImageView = findViewById(R.id.imageView);
        hueSeekBar = findViewById(R.id.hue);
        satSeekBar = findViewById(R.id.sat);
        lumSeekBar = findViewById(R.id.lum);

        hueSeekBar.setOnSeekBarChangeListener(this);
        satSeekBar.setOnSeekBarChangeListener(this);
        lumSeekBar.setOnSeekBarChangeListener(this);

        hueSeekBar.setMax(MAX);
        satSeekBar.setMax(MAX);
        lumSeekBar.setMax(MAX);

        hueSeekBar.setProgress(MID);
        satSeekBar.setProgress(MID);
        lumSeekBar.setProgress(MID);

        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.hue:
                mHue = (progress - MID) * 1.0F / MID * 180;
                break;
            case R.id.sat:
                mSat = progress * 1.0F / MID;
                break;
            case R.id.lum:
                mLum = progress * 1.0F / MID;
                break;
        }
        change = ImageHelper.handleImageEffect(bitmap, mHue, mSat, mLum);
        mImageView.setImageBitmap(change);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void btnSave(View view) {
        MainActivity.select = change;
        Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_SHORT).show();
        finish();
    }
}
