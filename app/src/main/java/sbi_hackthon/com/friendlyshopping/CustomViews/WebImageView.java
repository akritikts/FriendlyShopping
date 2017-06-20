package sbi_hackthon.com.friendlyshopping.CustomViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ramesh p on 11/06/2017.
 */

public class WebImageView extends AppCompatImageView {
    public WebImageView(Context context) {
        super(context);
    }

    public WebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageFromURL(String url)
    {
        new LoadImageTask().execute(url);
    }

    private class LoadImageTask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected void onPostExecute(Bitmap receivedBitmap)
        {
            if(receivedBitmap != null)
            {
                setImageBitmap(receivedBitmap);
            }
        }

        @Override
        protected Bitmap doInBackground(String... params)
        {
            try
            {
                URL imageURL = new URL(params[0]);
                InputStream imageStream = imageURL.openConnection().getInputStream();
                Bitmap webBitmap = BitmapFactory.decodeStream(imageStream);
                imageStream.close();
                return webBitmap;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
