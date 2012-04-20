package at.docstoegprer.android;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class CamView extends Activity implements SurfaceHolder.Callback {

	private Camera camera;
	private boolean isPreviewRunning = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setup window features
		Window win = getWindow();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		win.setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.camera);

		// prepare camera
		SurfaceView camView = (SurfaceView) findViewById(R.id.cam);
		if (camView != null) {
			SurfaceHolder holder = camView.getHolder();
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			holder.addCallback(this);
		}

	}

	/**
	 * will initialize the camera when the surface is initialized
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera = Camera.open();
		} catch (Exception e) {
			Log.e("CamView", e.getMessage());
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

	/**
	 * release camera when the surface is destroyed
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}

		isPreviewRunning = false;
	}
}
