package at.stoegprer.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ViewFlipper;

public class Main extends Activity {
	
	ViewFlipper flipper;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // switch to camera activity
        Button cameraButton = (Button) findViewById(R.id.button1);
        cameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent goCamera = new Intent(v.getContext(), ItemPicker.class);
				startActivityForResult(goCamera, 0);
			}
		});
        
        
        Gallery galleryAccessories = (Gallery) findViewById(R.id.galleryAccessories);
        galleryAccessories.setAdapter(new ImageAdapter(this, OutDir.ACCESSORIES));
        
        final Gallery galleryTops = (Gallery) findViewById(R.id.galleryTops);
        galleryTops.setAdapter(new ImageAdapter(this, OutDir.TOPS));
        
        GridView gridViewTops = (GridView) findViewById(R.id.gridviewTops);
        gridViewTops.setAdapter(new GridImageAdapter(this, OutDir.TOPS));
        
        Gallery galleryBottoms = (Gallery) findViewById(R.id.galleryBottoms);
        galleryBottoms.setAdapter(new ImageAdapter(this, OutDir.BOTTOMS));
        
//        GridView gridViewBottoms = (GridView) findViewById(R.id.gridviewBottoms);
//        gridViewBottoms.setAdapter(new GridImageAdapter(this, OutDir.BOTTOMS));
        
        Gallery galleryShoes = (Gallery) findViewById(R.id.galleryShoes);
        galleryShoes.setAdapter(new ImageAdapter(this, OutDir.SHOES));
        
        flipper = (ViewFlipper)findViewById(R.id.flipper);
        flipper.setInAnimation(this, android.R.anim.fade_in);
        flipper.setOutAnimation(this, android.R.anim.fade_out);
        
        
        galleryTops.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView parent, View v, int position, long id) {
              flipper.showNext();
          }
		});
        
        
        gridViewTops.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				System.out.println(galleryTops.getSelectedItemPosition());
				galleryTops.setSelection(position);
				flipper.showPrevious();
				
			}
        	
		});
    }
}