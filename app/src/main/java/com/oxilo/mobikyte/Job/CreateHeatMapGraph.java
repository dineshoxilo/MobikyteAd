package com.oxilo.mobikyte.Job;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oxilo.mobikyte.ApplicationController;
import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.HeatmapDatum;
import com.oxilo.mobikyte.POJO.ModalAlreadyRegistred;
import com.oxilo.mobikyte.POJO.ModalGraph;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.event.HeatMapFinishedEvent;
import com.oxilo.mobikyte.holder.GroupItem;
import com.oxilo.mobikyte.logger.Log;
import com.oxilo.mobikyte.volley.VolleyErrorHelper;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.greenrobot.event.EventBus;

/**
 * Created by ericbasendra on 29/11/15.
 */
public class CreateHeatMapGraph extends Job{
    Context mContext;
    List<HeatmapDatum> heatmapDatumList;

    public CreateHeatMapGraph(Context mContext, List<HeatmapDatum> heatmapDatumList) {
        super(new Params(Priority.HIGH).requireNetwork().groupBy("fetch-heatmap"));
        this.mContext = mContext;
        this.heatmapDatumList = heatmapDatumList;
        Log.e("HETA MAP DATA SIZE","" + heatmapDatumList.size());
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        EventBus.getDefault().post(new HeatMapFinishedEvent(readItems()));
    }

    @Override
    protected void onCancel() {

    }

    private ArrayList<LatLng> readItems() throws JSONException {
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        double lat = 0.0;
        double lng = 0.0;
        for (int i = 0; i < heatmapDatumList.size(); i++) {
            if (!heatmapDatumList.get(i).getLat().toString().equals("")){
                lat = Double.parseDouble(heatmapDatumList.get(i).getLat().toString());
            }
            if (!heatmapDatumList.get(i).getLog().toString().equals("")){
                lng = Double.parseDouble(heatmapDatumList.get(i).getLog().toString());
            }
            list.add(new LatLng(lat, lng));
        }
        return list;
    }
}
