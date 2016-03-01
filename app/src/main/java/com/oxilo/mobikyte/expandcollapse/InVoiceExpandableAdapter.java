package com.oxilo.mobikyte.expandcollapse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.InVoiceObject;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.ui.CustomTextView;
import com.oxilo.mobikyte.utility.ActivityUtils;

import java.util.List;

/**
 * An example custom implementation of the ExpandableRecyclerAdapter.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class InVoiceExpandableAdapter extends ExpandableRecyclerAdapter<VerticalParentViewHolder, InVoiceExpandableAdapter.VerticalChildViewHolder> {

    private LayoutInflater mInflater;
    private static DashBoard myClickListener;
    private Context mContext;
    private ModalLogin modalLogin;
    /**
     * Public primary constructor.
     *
     * @param parentItemList the list of parent items to be displayed in the RecyclerView
     */
    public InVoiceExpandableAdapter(Context context, List<? extends ParentListItem> parentItemList, final DashBoard dashBoard,ModalLogin modalLogin) {
        super(parentItemList);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.myClickListener = dashBoard;
        this.modalLogin  = modalLogin;
    }

//    public void setOnItemClickListener(DashBoard myClickListener) {
//        this.myClickListener = myClickListener;
//    }

    /**
     * OnCreateViewHolder implementation for parent items. The desired ParentViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @Override
    public VerticalParentViewHolder onCreateParentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.list_item_parent_vertical, parent, false);
        return new VerticalParentViewHolder(view);
    }

    /**
     * OnCreateViewHolder implementation for child items. The desired ChildViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @Override
    public VerticalChildViewHolder onCreateChildViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.recyle_row, parent, false);
        return new VerticalChildViewHolder(view);
    }

    /**
     * OnBindViewHolder implementation for parent items. Any data or view modifications of the
     * parent view should be performed here.
     *
     * @param parentViewHolder the ViewHolder of the parent item created in OnCreateParentViewHolder
     * @param position the position in the RecyclerView of the item
     */
    @Override
    public void onBindParentViewHolder(VerticalParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        VerticalParent verticalParent = (VerticalParent) parentListItem;
        parentViewHolder.bind(verticalParent.getParentNumber(), verticalParent.getParentText());
    }

    /**
     * OnBindViewHolder implementation for child items. Any data or view modifications of the
     * child view should be performed here.
     *
     * @param childViewHolder the ViewHolder of the child item created in OnCreateChildViewHolder
     * @param position the position in the RecyclerView of the item
     */
    @Override
    public void onBindChildViewHolder(VerticalChildViewHolder childViewHolder, int position, Object childListItem) {
        VerticalChild verticalChild = (VerticalChild) childListItem;
        childViewHolder.bind(verticalChild.getInVoiceObject());
    }


    /**
     * Custom child ViewHolder. Any views should be found and set to public variables here to be
     * referenced in your custom ExpandableAdapter later.
     *
     * Must extend ChildViewHolder.
     *
     */
    public class VerticalChildViewHolder extends ChildViewHolder implements View.OnClickListener{

        protected TextView inVoiceView;
        protected TextView orderView;
        protected TextView campaignView;
        protected TextView createDateView;
        protected TextView discountView;
        protected TextView orderAmountView;
        protected TextView campaignTitle;
        protected TextView userName;
        /**
         * Public constructor for the custom child ViewHolder
         *
         * @param itemView the child ViewHolder's view
         */
        public VerticalChildViewHolder(View itemView) {
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
        public void bind(InVoiceObject inVoiceObject) {

            String ss = "" + inVoiceObject.getCreateDate();

            //   String[] splited = ss.split("\\s+");
//               createDateView.setText(splited[0]+"\n"+splited[1]);

            inVoiceView.setText("\n"+inVoiceObject.getInvoiceId());
            orderView.setText("\n"+inVoiceObject.getOrderId());
            createDateView.setText("\n"+ActivityUtils.GetMonthDate(ss.toString()));
            discountView.setText("\n" + inVoiceObject.getDiscount());
            campaignView.setText("\n"+inVoiceObject.getCampaignid());
            orderAmountView.setText("\n"+inVoiceObject.getOrderAmount());
            campaignTitle.setText("\n"+inVoiceObject.getCampaignname());
            userName.setText("\n"+ modalLogin.getName());

        }
        @Override

        public void onClick(View view) {

            myClickListener.onItemClick(getLayoutPosition(), view);
        }}
    public interface DashBoard {

        public void onItemClick(int position, View v);

    }


//    public String getStatus(String status){

//        if (status.toString().trim().equals(mContext.getResources().getString(R.string.status_paused))){
//            return "paused";
//        }
//        else if (status.toString().trim().equals(mContext.getResources().getString(R.string.status_awaiting))){
//            return "awaiting";
//        }
//        else if(status.toString().trim().equals(mContext.getResources().getString(R.string.status_completed))){
//            return "completed";
//        }
//        else if(status.toString().trim().equals(mContext.getResources().getString(R.string.status_running))){
//            return "running";
//        }
//        else if(status.toString().trim().equals(mContext.getResources().getString(R.string.status_unpaid))){
//            return "unpaid";
//        }
//        else if(status.toString().trim().equals(mContext.getResources().getString(R.string.status_compliance))){
//            return "compliance";
//        }
//        else{
//            return "compliance";
//        }
//    }
}