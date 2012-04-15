package at.stoegprer.android;

import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ItemPicker extends Activity {
	/**
	 * camera pic request code
	 */
	protected static final int CAMERA_PIC_REQUEST = 1;
	
	/**
	 * basic output directory for photos 
	 */
	protected static final String OUT_DIR             = "Style-o-matic/";
	
	/**
	 * output dir for accessories
	 */
	public static final String OUT_DIR_ACCESSORIES = OUT_DIR + "accessories";
	
	/**
	 * output dir for tops
	 */
	public static final String OUT_DIR_TOPS        = OUT_DIR + "tops";
	
	/**
	 * output dir for bottoms
	 */
	public static final String OUT_DIR_BOTTOMS     = OUT_DIR + "bottoms";
	
	/**
	 * output dir for shoes
	 */
	public static final String OUT_DIR_SHOES       = OUT_DIR + "shoes";
	
	/**
	 * pic type
	 */
	public enum PicType { ACCESSORIES, TOPS, BOTTOMS, SHOES }
	
	/**
	 * stores selected pic type
	 */
	private PicType currentPicType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itempicker);

		// create reusable onClickListener
		View.OnClickListener goCam = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ItemPicker ip = ((ItemPicker) v.getContext());
				if (v == findViewById(R.id.accessories)) {
					ip.setType(ItemPicker.PicType.ACCESSORIES);
				} else if (v == findViewById(R.id.tops)) {
					ip.setType(ItemPicker.PicType.TOPS);
				} else if (v == findViewById(R.id.bottoms)) {
					ip.setType(ItemPicker.PicType.BOTTOMS);
				} else if (v == findViewById(R.id.shoes)) {
					ip.setType(ItemPicker.PicType.SHOES);
				}
				startCamera();
			}
		};
		
		// link buttons to start camera
		Button accessoriesButton = (Button) findViewById(R.id.accessories);
		accessoriesButton.setOnClickListener(goCam);
		
		Button topsButton = (Button) findViewById(R.id.tops);
		topsButton.setOnClickListener(goCam);
		
		Button bottomsButton = (Button) findViewById(R.id.bottoms);
		bottomsButton.setOnClickListener(goCam);
		
		Button shoesButton = (Button) findViewById(R.id.shoes);
		shoesButton.setOnClickListener(goCam);
		
	}

	/**
	 * setter for current pic type
	 * @param type
	 */
	public void setType(PicType type) {
		currentPicType = type;
	}
	
	/**
	 * starts the camera and sets the storage dir for photos
	 */
	protected void startCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getPicUri(this)) );
        // TODO maybe hide action icons?
        
//		Intent cameraIntent = new Intent(
//				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}

	/**
	 * creates a file path for our new photo
	 * @param context
	 * @return File path on external storage
	 */
	private File getPicUri(Context context) {
		// TODO basically this should never happen...
		String outDir = OUT_DIR;
		
		switch (currentPicType) {
		case ACCESSORIES:
			outDir = OUT_DIR_ACCESSORIES;
			break;
		case TOPS:
			outDir = OUT_DIR_TOPS;
			break;
		case BOTTOMS:
			outDir = OUT_DIR_BOTTOMS;
			break;
		case SHOES:
			outDir = OUT_DIR_SHOES;
			break;
		}
		
		final File path = new File(Environment.getExternalStorageDirectory(),
				outDir);
		if (!path.exists()) {
			path.mkdirs();
		}
		
		Date date = new Date();
		return new File(path, date.getTime() + ".jpg");
	} 
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			Log.i("ItemPicker", "Problem when receiving image from camera intent");
			return;
		}
		//		if (requestCode == CAMERA_PIC_REQUEST) {
		//			// do something
		//		}
		
		// TODO save pic to folder
		
		
		Log.i("ItemPicker", "Received image. Current pic type is " + currentPicType.toString());
	}
}
