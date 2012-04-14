package at.stoegprer.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ItemPicker extends Activity {
	/**
	 * camera pic request code
	 */
	protected static final int CAMERA_PIC_REQUEST = 1;
	
	/**
	 * pic type
	 */
	public enum Type { ACCESSORIES, TOPS, BOTTOMS, SHOES }
	
	/**
	 * stores selected pic type
	 */
	protected Type currentPicType;
	
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
					ip.setType(ItemPicker.Type.ACCESSORIES);
				} else if (v == findViewById(R.id.tops)) {
					ip.setType(ItemPicker.Type.TOPS);
				} else if (v == findViewById(R.id.bottoms)) {
					ip.setType(ItemPicker.Type.BOTTOMS);
				} else if (v == findViewById(R.id.shoes)) {
					ip.setType(ItemPicker.Type.SHOES);
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
	public void setType(Type type) {
		currentPicType = type;
	}
	
	protected void startCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//		if (requestCode == CAMERA_PIC_REQUEST) {
		//			// do something
		//		}
		
		// TODO save pic to folder
		
		
		Log.i("ItemPicker", "Received image. Current pic type is " + currentPicType.toString());
	}
}
