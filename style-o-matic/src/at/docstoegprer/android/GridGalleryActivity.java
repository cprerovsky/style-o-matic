package at.docstoegprer.android;

import at.docstoegprer.android.PositionHolder.POSITION_TYPE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridGalleryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(this.getIntent().getExtras().getInt("LayoutID"));
		
		final POSITION_TYPE type = (POSITION_TYPE) this.getIntent().getExtras().getSerializable("Type");
		
        final GridView gridViewTops = (GridView) findViewById(this.getIntent().getExtras().getInt("GridViewID"));
        gridViewTops.setAdapter(new GridImageAdapter(this, this.getIntent().getExtras().getString("Path")));
        
        gridViewTops.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
        		Intent myIntent = new Intent(arg1.getContext(), Main.class);
        		PositionHolder.getInstance().setPosition(type, position);
                startActivityForResult(myIntent, 0);
				
			}
        	
		});
		
	}

}
