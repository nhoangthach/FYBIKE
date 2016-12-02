package com.android.app.fybike.Controller;

import com.android.app.fybike.model.ShopModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Tien on 11/27/2016.
 */

public class ShopController {

    static ShopController instance;
    public ShopController()
    {

    }

    public ArrayList<ShopModel> ParseObject(String jsonStr) {
        ArrayList<ShopModel> listShop = new ArrayList<ShopModel>();
        if (jsonStr != null)
        {
            try {
                JSONArray jsonObj = new JSONArray (jsonStr);
                for(int i=0;i<jsonObj.length();i++){
                    JSONObject e = jsonObj.getJSONObject(i);
                    ShopModel shop = new ShopModel();
                    shop.setNameShop(e.getString("title"));
                    shop.setAddressShop(e.getString("address"));
                    JSONArray location = e.getJSONArray("location");
                    shop.setLongitue(location.getDouble(0));
                    shop.setLatitue(location.getDouble(1));
                    listShop.add(shop);
                }
            }
            catch (JSONException e)
            {

            }
        }

        return listShop;
    }
    public static ShopController Instance()
    {
        if (instance != null)
        {
            return instance;
        }
        else
        {
            instance = new ShopController();
            return instance;
        }
    }
}
