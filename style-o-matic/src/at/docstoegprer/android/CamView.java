package at.docstoegprer.android;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CamView extends Activity implements SurfaceHolder.Callback {

	private Camera camera;
	private TextView logView;
	private boolean isPreviewRunning = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window win = getWindow();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		win.setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.camera);

		
//		logView = (TextView) findViewById(R.id.log);
//
//		if (logView != null) {
//			logView.bringToFront();
//			bindMotionListeners((View) logView);
//		}

		SurfaceView camView = (SurfaceView) findViewById(R.id.cam);

		if (camView != null) {
			// Install SurfaceHolder.Callback to listen for create, change, and
			// delete notifications on underlying surface
			SurfaceHolder holder = camView.getHolder();
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			holder.addCallback(this);

//			bindMotionListeners((View) camView);
		}

	}

//	public void bindMotionListeners(View view) {
//		if (view != null) {
//			view.setLongClickable(true);
//			view.setOnLongClickListener(this);
//			view.setOnTouchListener(this);
//		}
//	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera = Camera.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		if (camera == null) {
			return;
		}

		// calling stopPreview will crash if preview is not running
		if (isPreviewRunning) {
			camera.stopPreview();
		}

		Camera.Parameters camParams = camera.getParameters();
		camParams.setPreviewSize(w, h);

		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}

		camera.startPreview();

		isPreviewRunning = true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}

		isPreviewRunning = false;
	}
}
