package com.syzible.aperto.common.networking;

public class Endpoints {

    public static String createWoeidEndpoint(float lat, float lng) {
        return "https://api.twitter.com/1.1/trends/closest.json?lat=" + lat + "&long=" + lng;
    }

    public static String createTopTweetsEndpoint(int woeid) {
        return "https://api.twitter.com/1.1/trends/place.json?id=" + woeid;
    }
}
