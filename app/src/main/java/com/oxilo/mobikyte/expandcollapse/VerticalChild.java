package com.oxilo.mobikyte.expandcollapse;

import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.InVoiceObject;

/**
 * Custom child list item.
 *
 * This is for demo purposes, although it is recommended having a separate
 * child list item from your parent list item.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class VerticalChild {
    private CampList campList;

    private InVoiceObject inVoiceObject;

    public CampList getCampList() {
        return campList;
    }

    public void setCampList(CampList campList) {
        this.campList = campList;
    }


    public InVoiceObject getInVoiceObject() {
        return inVoiceObject;
    }

    public void setInVoiceObject(InVoiceObject inVoiceObject) {
        this.inVoiceObject = inVoiceObject;
    }

}
