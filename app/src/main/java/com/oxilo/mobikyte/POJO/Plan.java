package com.oxilo.mobikyte.POJO;

/**
 * Created by ericbasendra on 23/01/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plan implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("viewer")
    @Expose
    private Integer viewer;
    @SerializedName("inr_price")
    @Expose
    private Integer inrPrice;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("free_plan")
    @Expose
    private String freePlan;
    @SerializedName("price_in_usd")
    @Expose
    private String priceInUsd;
    @SerializedName("currency")
    @Expose
    private String currency;

    private boolean isChecked;

    protected Plan(Parcel in) {
        id= in.readInt();
        viewer = in.readInt();
        message = in.readString();
        freePlan = in.readString();
        priceInUsd = in.readString();
        currency = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The viewer
     */
    public Integer getViewer() {
        return viewer;
    }

    /**
     *
     * @param viewer
     * The viewer
     */
    public void setViewer(Integer viewer) {
        this.viewer = viewer;
    }

    /**
     *
     * @return
     * The inrPrice
     */
    public Integer getInrPrice() {
        return inrPrice;
    }

    /**
     *
     * @param inrPrice
     * The inr_price
     */
    public void setInrPrice(Integer inrPrice) {
        this.inrPrice = inrPrice;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The freePlan
     */
    public String getFreePlan() {
        return freePlan;
    }

    /**
     *
     * @param freePlan
     * The free_plan
     */
    public void setFreePlan(String freePlan) {
        this.freePlan = freePlan;
    }

    /**
     *
     * @return
     * The priceInUsd
     */
    public String getPriceInUsd() {
        return priceInUsd;
    }

    /**
     *
     * @param priceInUsd
     * The price_in_usd
     */
    public void setPriceInUsd(String priceInUsd) {
        this.priceInUsd = priceInUsd;
    }

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean getChecked(){
        return isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(viewer);
        dest.writeString(message);
        dest.writeString(freePlan);
        dest.writeString(priceInUsd);
        dest.writeString(currency);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
