package com.syzible.aperto.common.networking;

public class EndpointsFactory {

    private static final String WOEID = "";

    private static final String TWEETS = "";

    public static String createWoeidEndpoint(float lat, float lng) {
        return WOEID;
    }

    public static String createTopTweetsEndpoint(int woeid) {
        return TWEETS;
    }
}
