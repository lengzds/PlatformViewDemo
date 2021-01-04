package com.baidu.tieba;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Random;

public class MainActivity extends Activity implements ImageReader.OnImageAvailableListener {

    final String TAG = "MainActivity";

    Random mRandom = new Random();

    DisplayManager mDisplayManager;
    DisplayMetrics mDisplayRealMetrics;

    int mVirtualDispalyWidth = 512;
    int mVirtualDisplayHeight = 512;
    VirtualDisplay mVirtualDisplay;

    ImageReader mImageReader;
    CounterPresentation mCounterPresentation;
    SingleViewPresentation mFlutterPresentation;

    private int densityDpi;

    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewGroup content = findViewById(R.id.content);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClickButton");
//                button.setText(String.format("Random %d", mRandom.nextInt(512)));
                setupImageReader();
                setupVirtualDisplay();
            }
        });


        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                Log.e("mydemo", "metrics " + metrics.toString());
                Log.e("mydemo", "densityDpi " + metrics.densityDpi);
            }
        });


        final Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = getResources().getDisplayMetrics();

//                metrics.density = 3.0f;
//                metrics.widthPixels = 1080;
//                metrics.heightPixels = 2159;
//                metrics.scaledDensity = 3.0f;
//                metrics.xdpi = 409.432f;
//                metrics.ydpi = 409.323f;
//                metrics.densityDpi = 480;

                metrics.xdpi = 409.432f;
                metrics.ydpi = 409.323f;
                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatWidthPixels");
                    field.setAccessible(true);
                    field.setInt(metrics,720);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }
                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatWidthPixels");
                    field.setAccessible(true);
                    field.setInt(metrics,720);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }
                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatWidthPixels");
                    field.setAccessible(true);
                    field.setInt(metrics,720);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }

                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatHeightPixels");
                    field.setAccessible(true);
                    field.setInt(metrics,1439);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }

                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatDensity");
                    field.setAccessible(true);
                    field.setFloat(metrics,2.0f);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }

                try {
                    Field field = DisplayMetrics.class.getDeclaredField("noncompatDensityDpi");
                    field.setAccessible(true);
                    field.setInt(metrics,320);
                } catch (Exception e) {
                    Log.e("mydemo", "Exception: " + e);
                    e.printStackTrace();
                }
                Log.e("mydemo", "metrics " + metrics.toString());

                imageView = new ImageView(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200);
                imageView.setLayoutParams(lp);
                imageView.setImageResource(R.color.colorPrimary);
                content.addView(imageView);

            }
        });

        densityDpi = getResources().getDisplayMetrics().densityDpi;
    }

    private void setupImageReader() {
        mImageReader = ImageReader.newInstance(mVirtualDispalyWidth, mVirtualDisplayHeight, PixelFormat.RGBA_8888, 4);
        mImageReader.setOnImageAvailableListener(this, new Handler(getMainLooper()));
    }

    private void validateVirtualDisplayDimensions(int width, int height) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        if (height > metrics.heightPixels || width > metrics.widthPixels) {
            String message =
                    "Creating a virtual display of size: "
                            + "["
                            + width
                            + ", "
                            + height
                            + "] may result in problems"
                            + "(https://github.com/flutter/flutter/issues/2897)."
                            + "It is larger than the device screen size: "
                            + "["
                            + metrics.widthPixels
                            + ", "
                            + metrics.heightPixels
                            + "].";
            Log.w(TAG, message);
        }
    }

    private int toPhysicalPixels(double logicalPixels) {
        float density = getResources().getDisplayMetrics().density;
        return (int) Math.round(logicalPixels * density);
    }


    private void setupVirtualDisplay() {

        // Virtual Display
        mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        mDisplayRealMetrics = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(mDisplayRealMetrics);
        mDisplayRealMetrics = getResources().getDisplayMetrics();

        mVirtualDisplay = mDisplayManager.createVirtualDisplay("Test Virtual Display" + mRandom.nextInt(512),
                mVirtualDispalyWidth, mVirtualDisplayHeight, mDisplayRealMetrics.densityDpi,
                mImageReader.getSurface(), DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION,
                null, null
        );

        Log.w(TAG, String.format("create virtual display %dx%d (%d)", mVirtualDispalyWidth, mVirtualDisplayHeight, mDisplayRealMetrics.densityDpi));

        if (mVirtualDisplay != null) {
            Log.i(TAG, "create virtual display success");

            if (mCounterPresentation != null) {
                mCounterPresentation.dismiss();
            }

            mCounterPresentation = new CounterPresentation(this, mVirtualDisplay.getDisplay());
            mCounterPresentation.show();
        } else {
            Log.i(TAG, "create virtual display fail");
        }
    }

    private void setupVirtualDisplayFlutter() {
        int physicalWidth = toPhysicalPixels(mVirtualDispalyWidth);
        int physicalHeight = toPhysicalPixels(mVirtualDisplayHeight);
        validateVirtualDisplayDimensions(physicalWidth, physicalHeight);
        // Virtual Display
        mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        mDisplayRealMetrics = new DisplayMetrics();

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(mDisplayRealMetrics);

        Surface surface = new Surface(new SurfaceTexture(mRandom.nextInt(512)));

        mVirtualDisplay =
                mDisplayManager.createVirtualDisplay("flutter-vd", physicalWidth, physicalHeight, densityDpi, surface, DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION);

        Log.w(TAG, String.format("create virtual display %dx%d (%d)", mVirtualDispalyWidth, mVirtualDisplayHeight, mDisplayRealMetrics.densityDpi));

        if (mVirtualDisplay != null) {
            Log.i(TAG, "create virtual display success");

            if (mFlutterPresentation != null) {
                mFlutterPresentation.dismiss();
            }

            mFlutterPresentation = new SingleViewPresentation(this, mVirtualDisplay.getDisplay(), null);
            mFlutterPresentation.show();
        } else {
            Log.i(TAG, "create virtual display fail");
        }
    }

    @SuppressLint("NewApi")
    public Bitmap acquireNextImage(ImageReader imageReader) {
        long captureBeginTime = System.currentTimeMillis();
        Image image = imageReader.acquireNextImage();

        int width = image.getWidth();
        int height = image.getHeight();
        final Image.Plane plane0 = image.getPlanes()[0];
        final ByteBuffer buffer = plane0.getBuffer();
        int pixelStride = plane0.getPixelStride();
        int rowStride = plane0.getRowStride();
        int rowPadding = rowStride - pixelStride * width;
        Bitmap bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
        bitmap.copyPixelsFromBuffer(buffer);

        image.close();
        return bitmap;
    }

    @Override
    @SuppressLint("NewApi")
    public void onImageAvailable(ImageReader reader) {
        Log.i(TAG, "onImageAvailable");

        Bitmap bitmap = acquireNextImage(reader);

        ImageView imageView2 = findViewById(R.id.image_view);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView2.setImageBitmap(bitmap);
        }
    }
}
