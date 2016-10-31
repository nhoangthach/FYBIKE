package com.android.app.fybike;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.fybike.model.ShopModel;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<ShopModel> mShopSet;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameShop;
        TextView txtAddressShop;
        ImageView imgPhotoOfShop;

        public MyViewHolder(View itemView){
            super(itemView);
            txtNameShop = (TextView) itemView.findViewById(R.id.txtNameShop);
            txtAddressShop = (TextView) itemView.findViewById(R.id.txtAddressShop);
            imgPhotoOfShop = (ImageView) itemView.findViewById(R.id.imgPhotoOfShop);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(mContext, DetailShopActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TextView textViewName = holder.txtNameShop;
        TextView textViewAddress = holder.txtAddressShop;
        ImageView imageView = holder.imgPhotoOfShop;

        textViewName.setText(mShopSet.get(position).getNameShop());
        textViewAddress.setText(mShopSet.get(position).getAddressShop());
        imageView.setImageResource(mShopSet.get(position).getPhotoOfShop());
    }

    public ShopAdapter(Context _context, ArrayList<ShopModel> _shopSet){
        mContext = _context;
        mShopSet = _shopSet;
    }
    @Override
    public int getItemCount() {
        return mShopSet.size();
    }
}
