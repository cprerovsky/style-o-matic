package at.stoegprer.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class Camera extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.camera);
    
        CameraPreview preview = new CameraPreview(this);
        ((FrameLayout) findViewById(R.id.preview)).addView(preview);
        
//        preview = new CameraPreview(this);
//		((FrameLayout) findViewById(R.id.preview)).addView(preview);
    
    }
}
