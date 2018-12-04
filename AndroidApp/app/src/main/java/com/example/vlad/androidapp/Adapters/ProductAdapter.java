package com.example.vlad.androidapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vlad.androidapp.Entities.Product;
import com.example.vlad.androidapp.ExApplication;
import com.example.vlad.androidapp.R;
import com.example.vlad.androidapp.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> products;
    private RecyclerItemClickListener recyclerItemClickListener;

    public ProductAdapter(RecyclerItemClickListener recyclerItemClickListener) {
        this.products = new ArrayList<>();
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Product product = products.get(position);

        holder.textViewTitle.setText(product.getName());
        String price = "$" + product.getPriceInCents() / 100.0;
        holder.textViewPrice.setText(price);

        Picasso.get().load(product.getImageUrl()).fit().into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExApplication.getInstance().setProductId(product.getId());
                recyclerItemClickListener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listview_title)
        TextView textViewTitle;
        @BindView(R.id.listview_price)
        TextView textViewPrice;
        @BindView(R.id.listview_image)
        ImageView imageView;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
