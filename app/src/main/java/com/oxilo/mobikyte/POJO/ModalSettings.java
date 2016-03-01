package com.oxilo.mobikyte.POJO;

/**
 * Created by ericbasendra on 23/01/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModalSettings implements Parcelable{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("clientid")
    @Expose
    private Integer clientid;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("website")
    @Expose
    private String website;

    protected ModalSettings(Parcel in) {
        status = in.readString();
        businessName = in.readString();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        password = in.readString();
        mobile = in.readString();
        website = in.readString();
    }

    public static final Creator<ModalSettings> CREATOR = new Creator<ModalSettings>() {
        @Override
        public ModalSettings createFromParcel(Parcel in) {
            return new ModalSettings(in);
        }

        @Override
        public ModalSettings[] newArray(int size) {
            return new ModalSettings[size];
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
     * The clientid
     */
    public Integer getClientid() {
        return clientid;
    }

    /**
     *
     * @param clientid
     * The clientid
     */
    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    /**
     *
     * @return
     * The businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     *
     * @param businessName
     * The business_name
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     * The mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     * The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     * The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(businessName);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(password);
        dest.writeString(mobile);
        dest.writeString(website);
    }
}

