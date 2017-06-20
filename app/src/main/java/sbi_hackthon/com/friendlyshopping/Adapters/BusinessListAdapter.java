package sbi_hackthon.com.friendlyshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sbi_hackthon.com.friendlyshopping.DataObjects.BusinessData;
import sbi_hackthon.com.friendlyshopping.ProductListActivity;
import sbi_hackthon.com.friendlyshopping.R;
import sbi_hackthon.com.friendlyshopping.RowHolders.BusinessListRowHolder;
import sbi_hackthon.com.friendlyshopping.Utlis.BillActivity;

/**
 * Created by ramesh p on 10/06/2017.
 */

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListRowHolder> {
    Context context;
    List<BusinessData> businessDatas;
    public BusinessListAdapter(Context context, List<BusinessData> businessDatas) {
        this.context = context;
        this.businessDatas = businessDatas;
    }

    @Override
    public BusinessListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_business_list, parent, false);
        return new BusinessListRowHolder(view);
    }

    @Override
    public void onBindViewHolder(final BusinessListRowHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        holder.name.setText(businessDatas.get(pos).getName());
        holder.description.setText(businessDatas.get(pos).getDescription());
        holder.imageView.setImageFromURL(businessDatas.get(pos).getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BillActivity.class);
                intent.putExtra("BUSID",businessDatas.get(pos).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return businessDatas.size();
    }
}
