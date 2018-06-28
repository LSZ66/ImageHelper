package cn.lsz.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static Bitmap select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (MainActivity.select != null) {
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(select);
        } else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnPrimaryColor(View view) {
        if (select != null)
            startActivity(new Intent(this, PrimaryColor.class));
        else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnFilter(View view) {
        if (select != null)
            startActivity(new Intent(this, PixelsEffect.class));
        else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnSelect(View view) {
        Crop.pickImage(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            try {
                MainActivity.select = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == Crop.REQUEST_CROP) {
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnCrop(View view) {
        if (select != null)
            startActivity(new Intent(this, RoundRetActivity.class));
        else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void saveImage(View view) {
        if (select != null) {
            MediaStore.Images.Media.insertImage(getContentResolver(), MainActivity.select, "title", "description");
            Toast.makeText(getApplicationContext(), "保存图片成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }
}
