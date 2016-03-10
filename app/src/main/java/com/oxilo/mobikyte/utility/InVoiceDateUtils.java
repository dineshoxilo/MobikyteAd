package com.oxilo.mobikyte.utility;

import android.app.Activity;
import android.net.ParseException;

import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.InVoiceObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ericbasendra on 09/12/15.
 */
public final class InVoiceDateUtils<T> implements Comparator<T> {
    List<T> campLists;

    @Override
    public int compare(T t, T t1) {

        Date date1 = null,date2 = null;
        try {
            //Pass String Date Format To Set UserDefined Date
            //Parse given STRING date to DATE format through df
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(((InVoiceObject)t).getCreateDate().toString());
            long millisecond1 = date1.getTime();
            date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(((InVoiceObject)t1).getCreateDate().toString());
            long millisecond2 = date2.getTime();


            if(millisecond1 > millisecond2)
            {
                return -1;
            }
            else
            {
                return 1;
            }

        }catch (ParseException e) {

            e.printStackTrace();
        }catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public List<T> getCampLists() {
        return campLists;
    }

    public void setCampLists(List<T> campLists) {
        this.campLists = campLists;
    }
}
