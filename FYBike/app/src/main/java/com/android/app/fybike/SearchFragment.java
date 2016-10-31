package com.android.app.fybike;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.fybike.model.ShopModel;

import java.util.ArrayList;


public class SearchFragment  extends Fragment{

    private RecyclerView recyclerView;
    private ArrayList<ShopModel> arrShop;
    private ShopAdapter shopAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerViewShop);
        arrShop = new ArrayList<>();
        shopAdapter = new ShopAdapter(getActivity(), arrShop);

        RecyclerView.LayoutManager recyLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(shopAdapter);
        prepareShop();

    }

    public void prepareShop(){
        int[] photo = new int[]{
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
                R.mipmap.ic_sua_xe,
        };

        ShopModel s1 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[0]);
        arrShop.add(s1);
        ShopModel s2 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[1]);
        arrShop.add(s2);
        ShopModel s3 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[2]);
        arrShop.add(s3);
        ShopModel s4 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[3]);
        arrShop.add(s4);
        ShopModel s5 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[4]);
        arrShop.add(s5);
        ShopModel s6 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[5]);
        arrShop.add(s6);
        ShopModel s7 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[6]);
        arrShop.add(s7);
        ShopModel s8 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[7]);
        arrShop.add(s8);
        ShopModel s9 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[8]);
        arrShop.add(s9);
        ShopModel s10 = new ShopModel("Sửa xe Mr.white", "33 Nguyễn Đình Chính, Phường 15, Quận Phú Nhuận, TP.HCM", photo[9]);
        arrShop.add(s10);

        shopAdapter.notifyDataSetChanged();

    }
}
