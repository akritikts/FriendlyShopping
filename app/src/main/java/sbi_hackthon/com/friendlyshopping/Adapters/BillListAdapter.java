package sbi_hackthon.com.friendlyshopping.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import sbi_hackthon.com.friendlyshopping.DataObjects.BillData;
import sbi_hackthon.com.friendlyshopping.R;
import sbi_hackthon.com.friendlyshopping.RowHolders.BillListHolder;

/**
 * Created by akriti on 20/6/17.
 */

public class BillListAdapter extends RecyclerView.Adapter<BillListHolder> {
    Context context;
    List<BillData> billDatas;

    public BillListAdapter(Context context, List<BillData> billDatas) {
        this.context = context;
        this.billDatas = billDatas;
    }

    @Override
    public BillListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_row, parent, false);
        return new BillListHolder(view);
    }

    @Override
    public void onBindViewHolder(BillListHolder holder, int position) {

        int pos = holder.getAdapterPosition();
        holder.cus_name.setText(billDatas.get(pos).getCustomer_name());
        holder.bil_amt.append(billDatas.get(pos).getBill_amount());
        holder.bil_msg.setText(billDatas.get(pos).getBill_msg());
        holder.bil_date.append(billDatas.get(pos).getBill_date());
    }

    @Override
    public int getItemCount() {
        return billDatas.size();
    }
}
