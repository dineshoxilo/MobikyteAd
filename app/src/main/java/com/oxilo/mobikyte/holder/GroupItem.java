package com.oxilo.mobikyte.holder;

import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.ClickDatum;
import com.oxilo.mobikyte.POJO.DeviceDatum;
import com.oxilo.mobikyte.POJO.HeatmapDatum;
import com.oxilo.mobikyte.POJO.ImprDatum;
import com.oxilo.mobikyte.POJO.IspDatum;
import com.oxilo.mobikyte.POJO.Plan;

import java.util.ArrayList;
import java.util.List;

public class GroupItem {
	public String title;
	public List<InVoiceObject> items = new ArrayList<InVoiceObject>();
	public List<CampList> campLists = new ArrayList<CampList>();
	public List<ClickDatum> clickDataList = new ArrayList<>();
	public List<DeviceDatum> deviceDatumsList = new ArrayList<DeviceDatum>();
	public List<ImprDatum> imprDatumsList = new ArrayList<ImprDatum>();
	public List<IspDatum> ispDatumList = new ArrayList<IspDatum>();
	public List<HeatmapDatum>heatmapDatumList = new ArrayList<>();
	public List<Plan>planList = new ArrayList<>();
}
