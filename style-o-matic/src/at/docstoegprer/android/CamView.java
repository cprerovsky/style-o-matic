package at.docstoegprer.android;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class CamView extends Activity implements SurfaceHolder.Callback {

	private Camera camera;
	private boolean isPreviewRunning = false;
	private ImageView accessories;
	private ImageView tops;
	private ImageView bottoms;
	private ImageView shoes;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setup window features
		Window win = getWindow();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		win.setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.camera);

		// init context menu

		
		// initialize overlays
		initOverlays();
		
		// prepare camera
		SurfaceView camView = (SurfaceView) findViewById(R.id.cam);
		if (camView != null) {
			SurfaceHolder holder = camView.getHolder();
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			holder.addCallback(this);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.cammenu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // hide all items first
		accessories.setVisibility(View.INVISIBLE);
		tops.setVisibility(View.INVISIBLE);
		bottoms.setVisibility(View.INVISIBLE);
		shoes.setVisibility(View.INVISIBLE);
		
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_accessories:
			accessories.setVisibility(View.VISIBLE);
			break;
		case R.id.menu_tops:
			tops.setVisibility(View.VISIBLE);
			break;
		case R.id.menu_bottoms:
			bottoms.setVisibility(View.VISIBLE);
			break;
		case R.id.menu_shoes:
			shoes.setVisibility(View.VISIBLE);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		
		return true;
	}
	/**
	 * initializes references for overlays
	 */
	private void initOverlays() {
		accessories = (ImageView) findViewById(R.id.accessories);
		accessories.setVisibility(View.VISIBLE);
		tops = (ImageView) findViewById(R.id.tops);
		bottoms = (ImageView) findViewById(R.id.bottoms);
		shoes = (ImageView) findViewById(R.id.shoes);
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

//		Camera.Parameters camParams = camera.getParameters();
//		camParams.setPreviewSize(w, h);
		
//		camera.setParameters(camParams);

		camera.setDisplayOrientation(180); 
		
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
