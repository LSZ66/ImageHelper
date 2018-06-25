package cn.lsz.image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Bitmap select;
    public static String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (path != null) {
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(select);
        } else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnPrimaryColor(View view) {
        if (path != null)
            startActivity(new Intent(this, PrimaryColor.class));
        else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnFilter(View view) {
        if (path != null)
            startActivity(new Intent(this, PixelsEffect.class));
        else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }

    public void btnSelect(View view) {
        startActivity(new Intent(this, PicList.class));
    }

    public void saveImage(View view) {
        if (path != null) {
            MediaStore.Images.Media.insertImage(getContentResolver(), MainActivity.select, "title", "description");
            Toast.makeText(getApplicationContext(), "保存图片成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "请先选择图片", Toast.LENGTH_SHORT).show();
    }
}
