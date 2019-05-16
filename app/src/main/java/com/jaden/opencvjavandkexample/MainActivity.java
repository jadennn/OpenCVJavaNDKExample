package com.jaden.opencvjavandkexample;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static final String TAG = "OpenCV_Test";

    private ImageView mSrcIV;
    private ImageView mDstIV;
    private Button mSwitchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initUI(){
        mSrcIV = findViewById(R.id.src_img);
        mDstIV = findViewById(R.id.dst_img);
        mSwitchBtn = findViewById(R.id.switch_btn);
        mSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSrcIV.setDrawingCacheEnabled(true);
                Bitmap src = mSrcIV.getDrawingCache();
                int w = src.getWidth();
                int h = src.getHeight();
                Bitmap dst = convertAry2Bitmap(rgb2Gray(convertBitmap2Ary(src, w, h),w, h ), w, h);
                mDstIV.setImageBitmap(dst);
                mSrcIV.setDrawingCacheEnabled(false);
            }
        });
    }

    private int[] convertBitmap2Ary(Bitmap bitmap, int w, int h){
        int[] pixel = new int[w * h];
        bitmap.getPixels(pixel, 0, w, 0, 0, w, h);
        return pixel;
    }

    private Bitmap convertAry2Bitmap(int [] src, int w, int h){
        Bitmap dst = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        dst.setPixels(src, 0, w, 0, 0, w, h);
        return dst;
    }

    /**
     * change a rgb ary to gray ary
     */
    public native int[] rgb2Gray(int[] src, int w, int h);
}
