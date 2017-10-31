package com.prometheus.gaidar.expandable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<PlayerAdsGroup> allItems;
    private ExpandableListView expandableListView;
    private boolean isLight = false;
    List<Boolean> groupState = new ArrayList<>();
    List<List<Boolean>> childState = new ArrayList<>();


    public CustomExpandableListAdapter(Context context, ExpandableListView list) {
        this.context = context;
        allItems = new ArrayList<>();
        expandableListView = list;
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                refresh();
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                refresh();
            }
        });


    }

    private void refresh() {
        isLight = false;
        updateState();
        notifyDataSetChanged();
    }


    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        Log.i("debug", "child has been got " + expandedListPosition + " parent pos is" + listPosition);
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
        if (expandableListView.isGroupExpanded(listPosition))
            setChildColors(convertView, listPosition, expandedListPosition);


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
        Log.i("debug", "Group is " + listPosition);
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
        setGroupColors(convertView, listPosition);
        final ImageView arrowImage = convertView.findViewById(R.id.arrow_image);

        Log.i("devoloTEst", "getGroupView: " + listPosition);
        Log.i("devoloTEst", "getGroupView: count  -  " + getChildrenCount(listPosition));
        int result = listPosition + 1;
        if (isExpanded) {
            result += getChildrenCount(listPosition);
        }


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

        refresh();
    }


    private void updateState() {
        groupState.clear();
        childState.clear();

        for (int i = 0; i < getGroupCount(); i++) {
            groupState.add(toogleLight());
            childState.add(new ArrayList<Boolean>());
            if (expandableListView.isGroupExpanded(i)) {
                for (int j = 0; j < getChildrenCount(i); j++) {
                    childState.get(i).add(toogleLight());
                }
            }
        }
    }

    private boolean toogleLight() {
        isLight = !isLight;
        return isLight;
    }

    private void setGroupColors(View mainContainer, int pos) {
        boolean isLight = groupState.get(pos);
        if (isLight) {
            mainContainer.setBackgroundResource(R.color.player_light_bg);
        } else {

            mainContainer.setBackgroundResource(R.color.player_dark_bg);
        }

    }

    private void setChildColors(View mainContainer, int parentPos, int childPos) {
        boolean isLight = childState.get(parentPos).get(childPos);
        if (isLight) {
            mainContainer.setBackgroundResource(R.color.player_light_bg);
        } else {

            mainContainer.setBackgroundResource(R.color.player_dark_bg);
        }
    }

}



