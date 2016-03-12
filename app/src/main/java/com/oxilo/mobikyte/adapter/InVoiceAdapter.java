package com.oxilo.mobikyte.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oxilo.mobikyte.POJO.InVoiceObject;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.logger.Log;
import com.oxilo.mobikyte.utility.ActivityUtils;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ericbasendra on 22/11/15.
 */
public class InVoiceAdapter extends RecyclerView.Adapter<InVoiceAdapter.ViewHolder>{

    private List<InVoiceObject> inVoiceObjectList;
    private ModalLogin modalLogin;
    public InVoiceAdapter(List<InVoiceObject> inVoiceObjectList,ModalLogin modalLogin) {
        this.inVoiceObjectList = inVoiceObjectList;
        this.modalLogin = modalLogin;
    }

    public void addItem(InVoiceObject dataObj, int index) {
        inVoiceObjectList.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        inVoiceObjectList.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recyle_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InVoiceObject inVoiceObject = inVoiceObjectList.get(position);
        String ss = "" + inVoiceObject.getCreateDate();

//        String[] splited = ss.split("\\s+");
//        holder.createDateView.setText(splited[0]+"\n"+splited[1]);
        holder.createDateView.setText(ActivityUtils.GetMonthDate(ss.toString()));
        holder.inVoiceView.setText("\n"+inVoiceObject.getInvoiceId());
        holder.orderView.setText("\n"+inVoiceObject.getOrderId());
        holder.discountView.setText("\n" + inVoiceObject.getDiscount());
        holder.campaignView.setText("\n"+inVoiceObject.getCampaignid());
        holder.orderAmountView.setText("\n"+inVoiceObject.getOrderAmount());
        holder.campaignTitle.setText(""+inVoiceObject.getCampaignname());
        holder.userName.setText(""+inVoiceObject.getCustName());
    }

    @Override
    public int getItemCount() {
        if (inVoiceObjectList!=null)
            return inVoiceObjectList.size();
        else
            return 0;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView inVoiceView;
        protected TextView orderView;
        protected TextView campaignView;
        protected TextView createDateView;
        protected TextView discountView;
        protected TextView orderAmountView;
        protected TextView campaignTitle;
        protected TextView userName;
        public ViewHolder(View itemView) {
            super(itemView);
            inVoiceView = (TextView) itemView.findViewById(R.id.action_invoice_id);
            orderView = (TextView) itemView.findViewById(R.id.action_order_id);
            campaignView = (TextView) itemView.findViewById(R.id.action_campaign_id);
            createDateView = (TextView) itemView.findViewById(R.id.action_create_date);
            discountView = (TextView) itemView.findViewById(R.id.action_discount);
            orderAmountView = (TextView) itemView.findViewById(R.id.action_order_amount);
            campaignTitle = (TextView)itemView.findViewById(R.id.action_invoice_title);
            userName = (TextView)itemView.findViewById(R.id.action_invoice_name);
        }
    }
}
