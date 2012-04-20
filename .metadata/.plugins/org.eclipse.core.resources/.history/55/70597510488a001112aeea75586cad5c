package at.stoegprer.android;

import java.io.File;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridImageAdapter extends BaseAdapter {

	private Context mContext;
	private Uri[] imgUris;
	private File picDirectory;

    public GridImageAdapter(Context c, String type) {
        mContext = c;
        this.picDirectory =new File(Environment.getExternalStorageDirectory(), type);
        init();
    }
    
    private void init(){
//    	File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	    if (picDirectory != null && picDirectory.listFiles() != null) {
	    	imgUris = new Uri[picDirectory.listFiles().length];
	        for(int i = 0; i < picDirectory.listFiles().length; i++){
	        	imgUris[i] = Uri.parse(picDirectory.listFiles()[i].getAbsolutePath());
	        }
	    }
    }

    public int getCount() {
    	// anzahl der fotos die irgendwo abgespeichert sind (cleeeemeeeens)
//    	File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    	if (picDirectory != null && picDirectory.listFiles() != null) {
	        return picDirectory.listFiles().length;
	    }
	    
	    return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        
//        File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (picDirectory != null && picDirectory.listFiles() != null) {
	        imageView.setImageURI(imgUris[position]);
	    }
	    
        return imageView;
    }


}
