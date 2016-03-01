package com.oxilo.mobikyte.POJO;

/**
 * Created by ericbasendra on 23/01/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModalProductsPlans implements Parcelable{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("plans")
    @Expose
    private List<Plan> plans = new ArrayList<Plan>();

    protected ModalProductsPlans(Parcel in) {
        status = in.readString();
        plans = in.createTypedArrayList(Plan.CREATOR);
    }

    public static final Creator<ModalProductsPlans> CREATOR = new Creator<ModalProductsPlans>() {
        @Override
        public ModalProductsPlans createFromParcel(Parcel in) {
            return new ModalProductsPlans(in);
        }

        @Override
        public ModalProductsPlans[] newArray(int size) {
            return new ModalProductsPlans[size];
        }
    };

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The plans
     */
    public List<Plan> getPlans() {
        return plans;
    }

    /**
     *
     * @param plans
     * The plans
     */
    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(plans);
    }
}
