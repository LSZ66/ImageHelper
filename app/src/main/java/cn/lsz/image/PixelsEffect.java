package cn.lsz.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PixelsEffect extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap, change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);
        imageView = findViewById(R.id.imageView);
        change = bitmap = MainActivity.select;
        imageView.setImageBitmap(bitmap);
    }

    public void btnOld(View view) {
        change = ImageHelper.handleImagePixelsOldPhoto(bitmap);
        imageView.setImageBitmap(change);
    }

    public void btnNegative(View view) {
        change = ImageHelper.handleImageNegative(bitmap);
        imageView.setImageBitmap(change);
    }

    public void btnRelief(View view) {
        change = ImageHelper.handleImagePixelsRelief(bitmap);
        imageView.setImageBitmap(change);
    }

    public void reset(View view) {
        change = MainActivity.select;
        imageView.setImageBitmap(change);
    }

    public void save(View view) {
        MainActivity.select = change;
        Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_SHORT).show();
        finish();
    }
}
