package com.oxilo.mobikyte.POJO;

/**
 * Created by ericbasendra on 21/11/15.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InvoiceList {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("invoice_list")
        @Expose
        private List<InVoiceObject> invoiceList = new ArrayList<InVoiceObject>();

        /**
         * @return The status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return The invoiceList
         */
        public List<InVoiceObject> getInvoiceList() {
            return invoiceList;
        }

        /**
         * @param invoiceList The invoice_list
         */
        public void setInvoiceList(List<InVoiceObject> invoiceList) {
            this.invoiceList = invoiceList;
        }
}