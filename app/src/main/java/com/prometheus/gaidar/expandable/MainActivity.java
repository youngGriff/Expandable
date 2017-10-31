package com.prometheus.gaidar.expandable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView expandableListView = findViewById(R.id.expandableListView);
        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(this, expandableListView);
        expandableListView.setAdapter(adapter);
        adapter.setData(prepareData());
    }

    private List<PlayerAdsGroup> prepareData() {
        List<PlayerAdsGroup> groups = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PlayerAds playerAds = new PlayerAds();
            List<PlayerAdsItem> items = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                items.add(new PlayerAdsItem());
            }
            PlayerAdsGroup group = new PlayerAdsGroup(playerAds, items);
            groups.add(group);
        }
        return groups;
    }
}
