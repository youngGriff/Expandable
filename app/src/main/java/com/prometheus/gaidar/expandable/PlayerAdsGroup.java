package com.prometheus.gaidar.expandable;

import java.util.List;

/**
 * Created by Andrew on 30.10.2017.
 */

class PlayerAdsGroup {
    private PlayerAds playerAds;
    private List<PlayerAdsItem> items;

    public PlayerAdsGroup(PlayerAds playerAds, List<PlayerAdsItem> items) {
        this.playerAds = playerAds;
        this.items = items;
    }

    public List<PlayerAdsItem> getItems() {
        return items;
    }

    public PlayerAds getPlayerAds() {
        return playerAds;
    }
}
