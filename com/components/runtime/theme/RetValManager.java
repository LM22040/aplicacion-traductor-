package com.google.appinventor.components.runtime.util;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.runtime.PhoneStatus;
import com.google.appinventor.components.runtime.ReplForm;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class RetValManager {
    private static final String LOG_TAG = "RetValManager";
    private static final long TENSECONDS = 10000;
    private static final Object semaphore = new Object();
    private static ArrayList<JSONObject> currentArray = new ArrayList<>(10);

    private RetValManager() {
    }

    public static void appendReturnValue(String blockid, String ok, String item) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, ok);
                retval.put("type", "return");
                retval.put("value", item);
                retval.put("blockid", blockid);
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void sendError(String error) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, "OK");
                retval.put("type", "error");
                retval.put("value", error);
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void pushScreen(String screenName, Object value) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, "OK");
                retval.put("type", "pushScreen");
                retval.put("screen", screenName);
                if (value != null) {
                    retval.put("value", value.toString());
                }
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void popScreen(String value) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, "OK");
                retval.put("type", "popScreen");
                if (value != null) {
                    retval.put("value", value.toString());
                }
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void assetTransferred(String name) {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, "OK");
                retval.put("type", "assetTransferred");
                if (name != null) {
                    retval.put("value", name.toString());
                }
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static void extensionsLoaded() {
        Object obj = semaphore;
        synchronized (obj) {
            JSONObject retval = new JSONObject();
            try {
                retval.put(NotificationCompat.CATEGORY_STATUS, "OK");
                retval.put("type", "extensionsLoaded");
                boolean sendNotify = currentArray.isEmpty();
                currentArray.add(retval);
                if (PhoneStatus.getUseWebRTC()) {
                    webRTCsendCurrent();
                } else if (sendNotify) {
                    obj.notifyAll();
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error building retval", e);
            }
        }
    }

    public static String fetch(boolean block) {
        String jSONObject;
        long startTime = System.currentTimeMillis();
        synchronized (semaphore) {
            while (currentArray.isEmpty() && block) {
                long time = System.currentTimeMillis();
                if (time - startTime > 9900) {
                    break;
                }
                try {
                    semaphore.wait(TENSECONDS);
                } catch (InterruptedException e) {
                }
            }
            JSONArray arrayoutput = new JSONArray((Collection) currentArray);
            JSONObject output = new JSONObject();
            try {
                output.put(NotificationCompat.CATEGORY_STATUS, "OK");
                output.put("values", arrayoutput);
                currentArray.clear();
                jSONObject = output.toString();
            } catch (JSONException e2) {
                Log.e(LOG_TAG, "Error fetching retvals", e2);
                return "{\"status\" : \"BAD\", \"message\" : \"Failure in RetValManager\"}";
            }
        }
        return jSONObject;
    }

    private static void webRTCsendCurrent() {
        try {
            JSONObject output = new JSONObject();
            output.put(NotificationCompat.CATEGORY_STATUS, "OK");
            output.put("values", new JSONArray((Collection) currentArray));
            ReplForm.returnRetvals(output.toString());
            currentArray.clear();
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error building retval", e);
        }
    }
}
