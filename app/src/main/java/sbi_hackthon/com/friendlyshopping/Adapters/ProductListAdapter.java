package sbi_hackthon.com.friendlyshopping.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sbi_hackthon.com.friendlyshopping.DataObjects.PoductData;
import sbi_hackthon.com.friendlyshopping.R;
import sbi_hackthon.com.friendlyshopping.RowHolders.ProductListRowHolder;

/**
 * Created by ramesh p on 10/06/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListRowHolder> {
    Context context;
    List<PoductData> businessDatas;
    public ProductListAdapter(Context context, List<PoductData> poductDatas) {
        this.context = context;
        this.businessDatas = poductDatas;
    }

    @Override
    public ProductListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_product_list, parent, false);
        return new ProductListRowHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListRowHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.name.setText(businessDatas.get(pos).getName());
        holder.description.setText(businessDatas.get(pos).getDescription());
        holder.imageView.setImageFromURL(businessDatas.get(pos).getImage());
    }

    @Override
    public int getItemCount() {
        return businessDatas.size();
    }
}
