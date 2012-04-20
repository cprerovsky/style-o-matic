package at.docstoegprer.android;

import java.io.File;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import at.docstoegprer.android.R;

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
    private Context mContext;
	private Uri[] imgUris;
	private File picDirectory;


    // TODO integer parameter rausschmeiﬂen!!!!
    public ImageAdapter(Context c, String type) {
        mContext = c;
        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = attr.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        attr.recycle();
        this.picDirectory = new File(Environment.getExternalStorageDirectory(), type);
        init();
    }
    
    private void init(){
//    	File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	    if (picDirectory != null  && picDirectory.listFiles() != null) {
	    	imgUris = new Uri[picDirectory.listFiles().length];
	        for(int i = 0; i < picDirectory.listFiles().length; i++){
	        	imgUris[i] = Uri.parse(picDirectory.listFiles()[i].getAbsolutePath());
	        }
	    }
    }

    public int getCount() {
    	 // wieder einkommentieren
//    	File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
	    if (picDirectory != null && picDirectory.listFiles() != null) {
	        return picDirectory.listFiles().length;
	    }
	    return 0;
//        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);

//        File path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(picDirectory != null || picDirectory.listFiles() == null){
        	init();
        }
	    if (picDirectory != null) {
	    	imageView.setImageURI(imgUris[position]);
	    }
	    
        imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(mGalleryItemBackground);

        return imageView;

    }
}
