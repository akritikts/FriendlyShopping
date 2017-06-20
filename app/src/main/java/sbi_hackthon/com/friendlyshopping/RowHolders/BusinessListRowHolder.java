package sbi_hackthon.com.friendlyshopping.RowHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sbi_hackthon.com.friendlyshopping.CustomViews.WebImageView;
import sbi_hackthon.com.friendlyshopping.R;

/**
 * Created by ramesh p on 10/06/2017.
 */

public class BusinessListRowHolder extends RecyclerView.ViewHolder {
    public WebImageView imageView;
    public TextView name, description;
    public BusinessListRowHolder(View itemView) {
        super(itemView);
        imageView = (WebImageView) itemView.findViewById(R.id.imImage);
        name = (TextView) itemView.findViewById(R.id.tvName);
        description = (TextView) itemView.findViewById(R.id.tvDesc);
    }
}
