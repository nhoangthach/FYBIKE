package Map;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.app.fybike.Controller.ShopController;
import com.android.app.fybike.MainActivity;
import com.android.app.fybike.MainMapFragment;
import com.android.app.fybike.model.ShopModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tien on 11/27/2016.
 */

public class MapHandler extends AsyncTask<Double, Void ,String> {
    final String API_URL = "https://for-your-bike-map.herokuapp.com/api/query";
    String server_response;
    ArrayList<ShopModel> listShop;
    GoogleMap myGGMap;
    Context guiContext;
    public MapHandler(GoogleMap mGoogleMap, Context context) {
        myGGMap = mGoogleMap;
        guiContext = context;
    }

    @Override
    protected String doInBackground(Double... arg) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            JSONArray types = new JSONArray();
            types.put(0, "Motobike");
            url = new URL(API_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setDoInput (true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();
            JSONObject jsonparam = new JSONObject();
            jsonparam.put("longitude", arg[0]);
            jsonparam.put("latitude", arg[1]);
            jsonparam.put("distance", 1000);
            jsonparam.put("types", types);
            jsonparam.put("minRating", 1);
            jsonparam.put("maxRating", 5);
            Log.e("Post", "" + jsonparam.toString());
            BufferedWriter out =
                    new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            out.write(jsonparam.toString());
            out.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            server_response = readStream(in);
            Log.e("Respone", "" + server_response);
            ShopController shopController = ShopController.Instance();
            listShop = shopController.ParseObject(server_response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
         }
        return server_response;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + server_response);
        for (ShopModel shop : listShop)
        {
            LatLng latLng = new LatLng(shop.getLatitue(), shop.getLongitue());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(shop.getNameShop());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            myGGMap.addMarker(markerOptions);
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
