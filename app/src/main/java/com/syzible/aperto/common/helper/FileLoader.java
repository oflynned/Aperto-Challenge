package com.syzible.aperto.common.helper;

import android.content.Context;

import com.syzible.aperto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class FileLoader {
    public static JSONArray loadSampleResponse(Context context, int resource) throws IOException, JSONException {
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = context.getResources().openRawResource(resource)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }

        String jsonString = writer.toString();
        return new JSONArray(jsonString);
    }
}
