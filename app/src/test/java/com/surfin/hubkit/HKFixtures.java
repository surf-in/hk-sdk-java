package com.surfin.hubkit;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surfin.hubkit.models.HKToken;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HKFixtures {

    static String   account() {
        return "{\"identifier\":\"d039c7a3-c514-43a9-b502-59bd02c57e38\"," +
                "\"email\":\"loic@hubkit.io\"," +
                "\"projects\":[]}";
    }

    static String   accountWithProjects() {
        return "{\"identifier\":\"d039c7a3-c514-43a9-b502-59bd02c57e38\"," +
                "\"email\":\"loic@hubkit.io\"," +
                "\"projects\":[" +
                project() +
                "]}";
    }

    static String   activity() {
        return "{\"identifier\":\"d039c7a3-c514-43a9-b502-59bd02c57e38\"," +
                "\"name\":\"Football\"}";
    }

    static String   device() {
        return "{\"identifier\":\"15ddf330-c6f7-4d3e-89b7-84174b578074\"," +
                "\"externalIdentifier\":\"My external identifier\"," +
                "\"name\":\"TIBTOP (54)\"," +
                "\"factoryTest\":\"OK\"," +
                "\"macAddress\":\"19:6e:36:59:0c:d9\"," +
                "\"hardwareVersion\":\"1.0\"," +
                "\"firmwareVersion\":\"0.26\"," +
                "\"manualMode\":true," +
                "\"latitude\":44.84454710000001," +
                "\"longitude\":-0.5794340999999577," +
                "\"sensorType\":\"TIBTOP GPS\"," +
                "\"battery\":98," +
                "\"activated\":false}";
    }

    static String   project() {
        return "{\"identifier\":\"320c1df2-4488-400a-81c7-042e8805b695\"," +
                "\"name\":\"Tibtop\"}";
    }

    static String   projectWithEmptyDevices() {
        return "{\"identifier\":\"320c1df2-4488-400a-81c7-042e8805b695\"," +
                "\"name\":\"Tibtop\"," +
                "\"devices\":[]}";
    }

    static String   projectWithDevices() {
        return "{\"identifier\":\"320c1df2-4488-400a-81c7-042e8805b695\"," +
                "\"name\":\"Tibtop\"," +
                "\"devices\":[" +
                device() +
                "]}";
    }

    static String   projectWithActivity() {
        return "{\"identifier\":\"320c1df2-4488-400a-81c7-042e8805b695\"," +
                "\"activity\":" +
                activity() +
                "," +
                "\"name\":\"Tibtop\"}";
    }

    static String   rawData() {
        return "{\"identifier\":\"7a19070c-a7ad-43a2-b851-59656328e6b7\"," +
                "\"device\":\"2a6c50ef-2b6f-48fe-89c9-77044cd2d584\"," +
                "\"session\":\"5f743e5a-9969-4cd4-8769-81fa71734578\"}";
    }

    static String   session() {
        return "{\"identifier\":\"5f743e5a-9969-4cd4-8769-81fa71734578\"," +
                "\"state\":\"NEW\"," +
                "\"project\":\"a48edeb7-a44d-437f-8f11-c83942d4d4eb\"," +
                "\"capturedAt\":\"2018-09-24T20:04:30+02:00\"}";
    }

    static String   jwt() {
        return "{\"token\":\"MyJwtToken\"}";
    }

    static String   apiKey() {
        return "a49686ae-6501-4c37-a3cd-e22bc205bd8f";
    }

    static Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    }
}
