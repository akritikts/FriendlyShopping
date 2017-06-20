package sbi_hackthon.com.friendlyshopping.RowHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sbi_hackthon.com.friendlyshopping.R;

/**
 * Created by akriti on 20/6/17.
 */

public class BillListHolder extends RecyclerView.ViewHolder {
   public TextView cus_name, bil_amt, bil_msg, bil_date;
    public BillListHolder(View itemView) {
        super(itemView);
        cus_name = (TextView)itemView.findViewById(R.id.cus_name);
        bil_amt = (TextView)itemView.findViewById(R.id.bil_amt);
        bil_msg = (TextView)itemView.findViewById(R.id.bil_msg);
        bil_date = (TextView)itemView.findViewById(R.id.bil_date);
    }
}
