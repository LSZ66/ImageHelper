package cn.lsz.image;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PicList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private ArrayAdapter<String> adapter;

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_pic_list);
        ListView showPath = findViewById(R.id.lv_show_path);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.addAll(getImagePathFromSD());
        showPath.setAdapter(adapter);
        showPath.setOnItemClickListener(this);
    }

    private List<String> getImagePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator
                + "Pictures";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (File x : files)
            if (checkIsImageFile(x.getPath()))
                imagePathList.add(x.getPath());
        // 返回得到的图片列表
        return imagePathList;
    }

    private boolean checkIsImageFile(String fName) {
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        return FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity.select = BitmapFactory.decodeFile(MainActivity.path = adapter.getItem(position));
        finish();
    }
}
