package com.prometheus.gaidar.expandable;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<PlayerAdsGroup> allItems;


    public CustomExpandableListAdapter(Context context, List<PlayerAdsGroup> expandableListTitle) {
        this.context = context;
        this.allItems = expandableListTitle;


    }


    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.allItems.get(listPosition).getItems().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return listPosition * expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final PlayerAdsItem expandedListText = (PlayerAdsItem) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.player_ads_child_item, null);
        }


        Log.i("devoloTEst", "getChildView: " + expandedListPosition);
        Log.i("devoloTEst", "getChildView:listPosition - " + listPosition);


        int result = expandedListPosition + 1;
//        for (int i = 0; i < listPosition + 1; i++) {
//            result += getChildrenCount(i);
//        }

        if (result % 2 == 0) {
            convertView.setBackgroundResource(R.color.colorPrimary);
        } else {
            convertView.setBackgroundResource(R.color.colorPrimaryDark);
        }


        TextView genreName = (TextView) convertView.findViewById(R.id.player_name);


        genreName.setText("Rick Sanches 1");


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.allItems.get(listPosition).getItems().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.allItems.get(listPosition).getPlayerAds();
    }

    @Override
    public int getGroupCount() {
        return this.allItems.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {
        PlayerAds listTitle = (PlayerAds) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.player_ads_category_item
                    , null);
        }


        View mainContainer = convertView.findViewById(R.id.main_container);


        final TextView pitchAll = convertView.findViewById(R.id.pitch_btn);


        final ImageView arrowImage = convertView.findViewById(R.id.arrow_image);

        Log.i("devoloTEst", "getGroupView: " + listPosition);
        Log.i("devoloTEst", "getGroupView: count  -  " + getChildrenCount(listPosition));
        int result = listPosition + 1;
        if (isExpanded) {
            result += getChildrenCount(listPosition);
        }
        if (listPosition % 2 == 0) {
            mainContainer.setBackgroundResource(R.color.player_dark_bg);
        } else {
            mainContainer.setBackgroundResource(R.color.player_light_bg);
        }


        pitchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    ((ExpandableListView) parent).collapseGroup(listPosition);

                    arrowImage.setColorFilter(context.getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
                    pitchAll.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));


                } else {
                    ((ExpandableListView) parent).expandGroup(listPosition, true);
                    pitchAll.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                    arrowImage.setColorFilter(context.getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);

                }

            }
        });


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void setData(List<PlayerAdsGroup> expandableListTitle) {
        this.allItems = expandableListTitle;
        notifyDataSetChanged();
    }

}