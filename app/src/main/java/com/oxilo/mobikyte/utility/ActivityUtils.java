package com.oxilo.mobikyte.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.desarrollodroide.libraryfragmenttransactionextended.FragmentTransactionExtended;
import com.oxilo.mobikyte.ApplicationController;
import com.oxilo.mobikyte.MODAL.MobiKytePrefs;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.POJO.ModalSettings;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.activity.MapsActivity;
import com.oxilo.mobikyte.logger.Log;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ericbasendra on 15/11/15.
 */
public class ActivityUtils {

    /**
     * Finish the Activity After showing the Toast
     */
    public static void finishMyCurrentActivityAfterShowingToast(final Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((AppCompatActivity)mContext).finish();
            }
        }, ApplicationController.SHORT_DELAY);
    }

    public static void launcFragement(Fragment fragment,Context context){
        FragmentManager fragmentManager = ((AppCompatActivity)context).getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.main_content, fragment);
        MapsActivity.fragmentStack.lastElement().onPause();
        ft.hide(MapsActivity.fragmentStack.lastElement());
        MapsActivity.fragmentStack.push(fragment);
        ft.commit();
    }

    public static void launchFragementWithAnimation(Fragment secondFragment,Context context){
        Fragment currentFragment = ((AppCompatActivity)context).getFragmentManager().findFragmentById(R.id.main_content);
        FragmentManager fm =  ((AppCompatActivity)context).getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(context, fragmentTransaction, currentFragment, secondFragment, R.id.main_content);
        fragmentTransactionExtended.addTransition(FragmentTransactionExtended.GLIDE);
        fragmentTransactionExtended.commit();
    }


    public static String GetDayFromDate(String date) {
        try {
            //Use "SimpleDateFormat" to format dates and times into a human-readable string.
            //Use "EEE" for days SHORT_FORM  && "EEEE" for FULL_FORM
            SimpleDateFormat sdf = new SimpleDateFormat("EEE");
            SimpleDateFormat sd = new SimpleDateFormat("EEEEE");

            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 = df.parse(date);

            //now format this 'd1' to string
            // for getting "DAY of week" in short form use 'sdf' to format date object 'd1' in string
            String dayOfTheWeek = sdf.format(d1);
            //For FullName of Day
            String dayOfTheWeekfull = sdf.format(d1);

            Log.d("DAY FROM DATE FORMATE:", "" + dayOfTheWeek);
            Log.d("DAY FROM DATE FORMATE:", "" + dayOfTheWeekfull);

            return dayOfTheWeek;

            //needs try/catch to handle parse Exception
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long GetDate(String date) {
        try {
            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 = df.parse(date);
            Long day = Long.parseLong((String) android.text.format.DateFormat.format("dd-mm-yyyy", d1)); //20

            return day;

            //needs try/catch to handle parse Exception
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetYear(String date) {
        try {
            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 = df.parse(date);
            String year = (String) android.text.format.DateFormat.format("yyyy", d1); //2013

            return year;

            //needs try/catch to handle parse Exception
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetMonthDate(String date){
        try {
            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 = df.parse(date);
            String stringMonth = (String) android.text.format.DateFormat.format("MMM d,yyyy", d1);
            return stringMonth;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetMonth(String date) {
        try {
            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 = df.parse(date);
            String stringMonth = (String) android.text.format.DateFormat.format("MMM", d1); //Jun

            return stringMonth;

            //needs try/catch to handle parse Exception
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String GetDateTime(long millisecond) {
        //Pass String Date Format To Set UserDefined Date
        String dateTime = "uu";
        try {
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, h:mm a", Locale.ENGLISH);
            //Parse given STRING date to DATE format through df
            Date d1 = new Date(millisecond * 1000);
            dateTime = df.format(d1.getTime());
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return dateTime;

    }
    public static String GetReportTime(long millisecond) {
        //Pass String Date Format To Set UserDefined Date
        String dateTime = "uu";
        try {
            DateFormat df = new SimpleDateFormat("d MMM, h:mm", Locale.ENGLISH);
            //Parse given STRING date to DATE format through df
            Date d1 = new Date(millisecond * 1000);
            dateTime = df.format(d1.getTime());
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return dateTime;

    }
    public static String GetReportDate(long millisecond) {
        try {
            //Pass String Date Format To Set UserDefined Date
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Parse given STRING date to DATE format through df
            Date d1 =new Date(millisecond * 1000);
            String stringMonth = (String) android.text.format.DateFormat.format("MMM d,yyyy", d1);
            return stringMonth;

        } catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static String GetCurrentTime(String s) {

        final Calendar c = Calendar.getInstance();

        return(new StringBuilder()
                .append(c.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(c.get(Calendar.MINUTE)).append(":")).toString();
    }

//    public static String GetCurrentTime(long millisecond) {
//        //Pass String Date Format To Set UserDefined Date
//        DateFormat df = new SimpleDateFormat("EEE, hh:mm a", Locale.ENGLISH);
//        //Parse given STRING date to DATE format through df
//        Date d1 = new Date(millisecond * 1000);
//        String dateTime = df.format(d1.getTime());
//
//        return dateTime;
//
//    }
    public static String GetStartDate(long millisecond) {
        //Pass String Date Format To Set UserDefined Date
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
        //Parse given STRING date to DATE format through df
        Date d1 = new Date(millisecond * 1000);
        String dateTime = df.format(d1.getTime());

        return dateTime;
    }
    public static String GetDate(long millisecond) {
        //Pass String Date Format To Set UserDefined Date
        DateFormat df = new SimpleDateFormat("EEE, d MMMh:mm", Locale.ENGLISH);
        //Parse given STRING date to DATE format through df
        Date d1 = new Date(millisecond * 1000);
        String stringdate = (String) android.text.format.DateFormat.format("MMM d,yyyy", d1);

        return stringdate;
    }

    public static long getMillFromDate(String date,Context mContext){
        String dateseperator = "-";
        String timeSeperator = ":";
        String input = date;
        long milliseconds = 0;
        long millisecondsFromNow = 0;
        Date mDate = null;
        try {
            mDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH).parse(input);
            milliseconds = mDate.getTime();
            millisecondsFromNow = milliseconds - (new Date()).getTime();
//            Toast.makeText(mContext, "Milliseconds to future date="+millisecondsFromNow, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return milliseconds;
    }
    public static String GetCreateDate(long millisecond) {
        //Pass String Date Format To Set UserDefined Date
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
        //Parse given STRING date to DATE format through df
        Date d1 = new Date(millisecond);
        String dateTime = df.format(d1.getTime());

        return dateTime;
    }
    public static long getMiliFromDate(String date){
        String dateseperator = "-";
        String timeSeperator = ":";
        String input = date;
        long milliseconds = 0;
        long millisecondsFromNow = 0;
        Date mDate = null;
        try {
            mDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH).parse(input);
            milliseconds = mDate.getTime();
            millisecondsFromNow = milliseconds - (new Date()).getTime();
//            Toast.makeText(mContext, "Milliseconds to future date="+millisecondsFromNow, Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;
    }

    public static String Convert24to12(String time)
    {
        String convertedTime ="";
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = parseFormat.parse(time);
            convertedTime=displayFormat.format(date);
            System.out.println("convertedTime : "+convertedTime);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
        //Output will be 10:23 PM
    }

    public static void showSettingsAlert(final Context mContext){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("Location Setting");

        // Setting Dialog Message
        alertDialog.setMessage("SityAds would like to access the location. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static void okAlert(final Context mContext){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);


        // Setting Dialog Title
        alertDialog.setTitle("Message");


        // Setting Dialog Message
        alertDialog.setMessage("select at least one plan");

        alertDialog.setCancelable(false);

        // On pressing Settings button
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        // Showing Alert Message
        alertDialog.show();
    }

    public static void setupUI(View view,final Context mContext) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(v,mContext);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView,mContext);
            }
        }
    }

    public static void hideSoftKeyboard(View view, Context mConext) {
        InputMethodManager inputMethodManager =(InputMethodManager)mConext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void resetPrefs(){
        MobiKytePrefs mobiKytePrefs = ApplicationController.getInstance().getMobiKytePrefs();
        if(mobiKytePrefs != null) {
            mobiKytePrefs.clear();
            ModalLogin modalLogin = new ModalLogin();
            modalLogin.setEmailid("");
            mobiKytePrefs.putObject("user", modalLogin);
            mobiKytePrefs.commit();
        } else {
            android.util.Log.e("MOBIKYTE PREFS==", "Preference is null");
        }
    }

    public static void updateLoginPrefsOnSettingUpdate(ModalSettings modalSettings, ModalLogin modalLogin1){
        MobiKytePrefs mobiKytePrefs = ApplicationController.getInstance().getMobiKytePrefs();
        if(mobiKytePrefs != null) {
            ModalLogin modalLogin = new ModalLogin();
            modalLogin1.setStatus(modalSettings.getStatus());
            modalLogin1.setClientid("" + modalSettings.getClientid());
            modalLogin1.setBusinessName(!modalSettings.getBusinessName().equals(JSONObject.NULL) ? modalSettings.getBusinessName() : "Mobikyte");
            modalLogin1.setName(!modalSettings.getName().equals(JSONObject.NULL) ? modalSettings.getName() : "Mobikyte");
            modalLogin1.setEmailid(!modalLogin1.getEmailid().equals(JSONObject.NULL) ? modalLogin1.getEmailid() : "us@us.com");
            modalLogin1.setPassword(!modalSettings.getPassword().equals(JSONObject.NULL) ? modalSettings.getPassword() : "usa");
            modalLogin1.setMobile(!modalSettings.getMobile().equals(JSONObject.NULL) ? modalSettings.getMobile() : modalSettings.getMobile());
            modalLogin1.setAddress(!modalSettings.getAddress().equals(JSONObject.NULL) ? modalSettings.getAddress() : "India");
            modalLogin1.setState(!modalSettings.getState().equals(JSONObject.NULL) ? modalSettings.getState() : "Delhi");
            modalLogin1.setCity(!modalSettings.getCity().equals(JSONObject.NULL) ? modalSettings.getCity() : "Delhi");
            modalLogin1.setWebsite(!modalSettings.getWebsite().equals(JSONObject.NULL) ? modalSettings.getWebsite() : "http://www.mobikyte.com");
            modalLogin1.setZip(!modalSettings.getZip().equals(JSONObject.NULL) ? modalSettings.getZip() : "302021");
//            mobiKytePrefs.clear();
//            mobiKytePrefs.putObject("user", modalLogin);
//            mobiKytePrefs.commit();
        } else {
            android.util.Log.e("MOBIKYTE PREFS==", "Preference is null");
        }
    }

}