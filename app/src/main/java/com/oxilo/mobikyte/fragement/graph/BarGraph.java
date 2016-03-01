package com.oxilo.mobikyte.fragement.graph;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.oxilo.mobikyte.POJO.ImprDatum;
import com.oxilo.mobikyte.POJO.IspDatum;
import com.oxilo.mobikyte.utility.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbasendra on 01/01/16.
 */
public class BarGraph {

    List<ImprDatum> imprDatumList;
    Context mContext;

    public BarGraph(){

    }

    public BarGraph(Context mContext, List<ImprDatum> imprDatumList){
        this.imprDatumList = imprDatumList;
        this.mContext = mContext;
    }


    public ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
            ArrayList<BarEntry> valueSet1 = new ArrayList<>();
            for (int i =0;i<imprDatumList.size();i++){
                if (i < 10){
                    ImprDatum ispDatum = imprDatumList.get((imprDatumList.size()-1) - i);
                    BarEntry barEntry = new BarEntry(Integer.parseInt(ispDatum.getCount()),i);
                    valueSet1.add(barEntry);
                }
            }

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "");
            barDataSet1.setBarSpacePercent(75f);
            barDataSet1.setColor(Color.rgb(0, 155, 0));
            barDataSet1.setDrawValues(false);

            dataSets = new ArrayList<>();
            dataSets.add(barDataSet1);



        return dataSets;
    }

    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
            for (int i = 0;i<imprDatumList.size();i++){
                if (i < 10){
                    String xAxis1 = imprDatumList.get((imprDatumList.size()-1) - i).getDate();
//                String xAxis1 = ActivityUtils.GetDayFromDate(imprDatumList.get(10 - i).getDate()) + "-" + ActivityUtils.GetMonth(imprDatumList.get(10 - i).getDate()) + "-" + ActivityUtils.GetYear(imprDatumList.get(10 - i).getDate());
                    xAxis.add(xAxis1);
                }

            }


        return xAxis;
    }
}
