package com.google.appinventor.components.runtime;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kawa.lang.SyntaxForms;

@SimpleObject
@UsesPermissions(permissionNames = "android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_MOCK_LOCATION,android.permission.ACCESS_LOCATION_EXTRA_COMMANDS")
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component providing location information, including longitude, latitude, altitude (if supported by the device), speed (if supported by the device), and address.  This can also perform \"geocoding\", converting a given address (not necessarily the current one) to a latitude (with the <code>LatitudeFromAddress</code> method) and a longitude (with the <code>LongitudeFromAddress</code> method).</p>\n<p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have location sensing enabled through wireless networks or GPS satellites (if outdoors).</p>\nLocation information might not be immediately available when an app starts.  You'll have to wait a short time for a location provider to be found and used, or wait for the LocationChanged event", iconName = "images/locationSensor.png", nonVisible = SyntaxForms.DEBUGGING, version = 3)
/* loaded from: classes.dex */
public class LocationSensor extends AndroidNonvisibleComponent implements Component, OnStopListener, OnResumeListener, Deleteable, RealTimeDataSource<String, Float> {
    private static final String LOG_TAG = LocationSensor.class.getSimpleName();
    public static final int UNKNOWN_VALUE = 0;
    private List<String> allProviders;
    private double altitude;
    private final Handler androidUIHandler;
    private Set<DataSourceChangeListener> dataSourceObservers;
    private int distanceInterval;
    private boolean enabled;
    private Geocoder geocoder;
    private final Handler handler;
    private boolean hasAltitude;
    private boolean hasLocationData;
    private boolean havePermission;
    private boolean initialized;
    private Location lastLocation;
    private double latitude;
    private final Set<LocationSensorListener> listeners;
    private boolean listening;
    private final Criteria locationCriteria;
    private final LocationManager locationManager;
    private LocationProvider locationProvider;
    private double longitude;
    private MyLocationListener myLocationListener;
    private boolean providerLocked;
    private String providerName;
    private float speed;
    private int timeInterval;

    /* loaded from: classes.dex */
    public interface LocationSensorListener extends LocationListener {
        void onDistanceIntervalChanged(int i);

        void onTimeIntervalChanged(int i);

        void setSource(LocationSensor locationSensor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyLocationListener implements LocationListener {
        private MyLocationListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            LocationSensor.this.lastLocation = location;
            LocationSensor.this.longitude = location.getLongitude();
            LocationSensor.this.latitude = location.getLatitude();
            LocationSensor.this.speed = location.getSpeed();
            if (location.hasAltitude()) {
                LocationSensor.this.hasAltitude = true;
                LocationSensor.this.altitude = location.getAltitude();
            }
            if (LocationSensor.this.longitude != 0.0d || LocationSensor.this.latitude != 0.0d) {
                LocationSensor.this.hasLocationData = true;
                final double argLatitude = LocationSensor.this.latitude;
                final double argLongitude = LocationSensor.this.longitude;
                final double argAltitude = LocationSensor.this.altitude;
                final float argSpeed = LocationSensor.this.speed;
                LocationSensor.this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.LocationSensor.MyLocationListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LocationSensor.this.LocationChanged(argLatitude, argLongitude, argAltitude, argSpeed);
                        for (LocationSensorListener listener : LocationSensor.this.listeners) {
                            listener.onLocationChanged(location);
                        }
                    }
                });
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
            LocationSensor.this.StatusChanged(provider, "Disabled");
            LocationSensor.this.stopListening();
            if (LocationSensor.this.enabled) {
                LocationSensor.this.RefreshProvider("onProviderDisabled");
            }
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
            LocationSensor.this.StatusChanged(provider, "Enabled");
            LocationSensor.this.RefreshProvider("onProviderEnabled");
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case 0:
                    LocationSensor.this.StatusChanged(provider, "OUT_OF_SERVICE");
                    if (provider.equals(LocationSensor.this.providerName)) {
                        LocationSensor.this.stopListening();
                        LocationSensor.this.RefreshProvider("onStatusChanged");
                        return;
                    }
                    return;
                case 1:
                    LocationSensor.this.StatusChanged(provider, "TEMPORARILY_UNAVAILABLE");
                    return;
                case 2:
                    LocationSensor.this.StatusChanged(provider, "AVAILABLE");
                    if (!provider.equals(LocationSensor.this.providerName) && !LocationSensor.this.allProviders.contains(provider)) {
                        LocationSensor.this.RefreshProvider("onStatusChanged");
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public LocationSensor(ComponentContainer container) {
        this(container, true);
    }

    public LocationSensor(ComponentContainer container, boolean enabled) {
        super(container.$form());
        this.dataSourceObservers = new HashSet();
        this.listeners = new HashSet();
        this.providerLocked = false;
        this.initialized = false;
        this.listening = false;
        this.longitude = 0.0d;
        this.latitude = 0.0d;
        this.altitude = 0.0d;
        this.speed = 0.0f;
        this.hasLocationData = false;
        this.hasAltitude = false;
        this.androidUIHandler = new Handler();
        this.enabled = true;
        this.havePermission = false;
        this.enabled = enabled;
        this.handler = new Handler();
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.timeInterval = 60000;
        this.distanceInterval = 5;
        Context context = container.$context();
        this.geocoder = new Geocoder(context);
        this.locationManager = (LocationManager) context.getSystemService("location");
        this.locationCriteria = new Criteria();
        this.myLocationListener = new MyLocationListener();
        this.allProviders = new ArrayList();
        Enabled(enabled);
    }

    public void Initialize() {
        this.initialized = true;
        Enabled(this.enabled);
    }

    @SimpleEvent(description = "Indicates that a new location has been detected.")
    public void LocationChanged(double latitude, double longitude, double altitude, float speed) {
        notifyDataObservers(PropertyTypeConstants.PROPERTY_TYPE_LATITUDE, (Object) Double.valueOf(latitude));
        notifyDataObservers(PropertyTypeConstants.PROPERTY_TYPE_LONGITUDE, (Object) Double.valueOf(longitude));
        notifyDataObservers("altitude", (Object) Double.valueOf(altitude));
        notifyDataObservers("speed", (Object) Float.valueOf(speed));
        EventDispatcher.dispatchEvent(this, "LocationChanged", Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(altitude), Float.valueOf(speed));
    }

    @SimpleEvent
    public void StatusChanged(String provider, String status) {
        if (this.enabled) {
            EventDispatcher.dispatchEvent(this, "StatusChanged", provider, status);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ProviderName() {
        String str = this.providerName;
        if (str == null) {
            return "NO PROVIDER";
        }
        return str;
    }

    @SimpleProperty
    public void ProviderName(String providerName) {
        this.providerName = providerName;
        if (!empty(providerName) && startProvider(providerName)) {
            return;
        }
        RefreshProvider("ProviderName");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean ProviderLocked() {
        return this.providerLocked;
    }

    @SimpleProperty
    public void ProviderLocked(boolean lock) {
        this.providerLocked = lock;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "60000", editorType = PropertyTypeConstants.PROPERTY_TYPE_SENSOR_TIME_INTERVAL)
    public void TimeInterval(int interval) {
        if (interval < 0 || interval > 1000000) {
            return;
        }
        this.timeInterval = interval;
        if (this.enabled) {
            RefreshProvider("TimeInterval");
        }
        for (LocationSensorListener listener : this.listeners) {
            listener.onTimeIntervalChanged(this.timeInterval);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.")
    public int TimeInterval() {
        return this.timeInterval;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "5", editorType = PropertyTypeConstants.PROPERTY_TYPE_SENSOR_DIST_INTERVAL)
    public void DistanceInterval(int interval) {
        if (interval < 0 || interval > 1000) {
            return;
        }
        this.distanceInterval = interval;
        if (this.enabled) {
            RefreshProvider("DistanceInterval");
        }
        for (LocationSensorListener listener : this.listeners) {
            listener.onDistanceIntervalChanged(this.distanceInterval);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.")
    public int DistanceInterval() {
        return this.distanceInterval;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasLongitudeLatitude() {
        return this.hasLocationData && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAltitude() {
        return this.hasAltitude && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HasAccuracy() {
        return Accuracy() != 0.0d && this.enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Longitude() {
        return this.longitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double Latitude() {
        return this.latitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The most recently available altitude value, in meters.  If no value is available, 0 will be returned.")
    public double Altitude() {
        return this.altitude;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The most recent measure of accuracy, in meters.  If no value is available, 0 will be returned.")
    public double Accuracy() {
        Location location = this.lastLocation;
        if (location != null && location.hasAccuracy()) {
            return this.lastLocation.getAccuracy();
        }
        if (this.locationProvider != null) {
            return r0.getAccuracy();
        }
        return 0.0d;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Enabled(boolean enabled) {
        this.enabled = enabled;
        if (!this.initialized) {
            return;
        }
        if (!enabled) {
            stopListening();
        } else {
            RefreshProvider("Enabled");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        if (r11.longitude <= 180.0d) goto L37;
     */
    @com.google.appinventor.components.annotations.SimpleProperty(category = com.google.appinventor.components.annotations.PropertyCategory.BEHAVIOR, description = "Provides a textual representation of the current address or \"No address available\".")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String CurrentAddress() {
        /*
            r11 = this;
            boolean r0 = r11.hasLocationData
            if (r0 == 0) goto L23
            double r0 = r11.latitude
            r2 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L23
            r2 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L23
            double r0 = r11.longitude
            r2 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L2e
        L23:
            double r0 = r11.longitude
            r2 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto Lb3
        L2e:
            android.location.Geocoder r5 = r11.geocoder     // Catch: java.lang.Exception -> L6c
            double r6 = r11.latitude     // Catch: java.lang.Exception -> L6c
            double r8 = r11.longitude     // Catch: java.lang.Exception -> L6c
            r10 = 1
            java.util.List r0 = r5.getFromLocation(r6, r8, r10)     // Catch: java.lang.Exception -> L6c
            if (r0 == 0) goto L6b
            int r1 = r0.size()     // Catch: java.lang.Exception -> L6c
            r2 = 1
            if (r1 != r2) goto L6b
            r1 = 0
            java.lang.Object r1 = r0.get(r1)     // Catch: java.lang.Exception -> L6c
            android.location.Address r1 = (android.location.Address) r1     // Catch: java.lang.Exception -> L6c
            if (r1 == 0) goto L6b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6c
            r2.<init>()     // Catch: java.lang.Exception -> L6c
            r3 = 0
        L51:
            int r4 = r1.getMaxAddressLineIndex()     // Catch: java.lang.Exception -> L6c
            if (r3 > r4) goto L66
            java.lang.String r4 = r1.getAddressLine(r3)     // Catch: java.lang.Exception -> L6c
            r2.append(r4)     // Catch: java.lang.Exception -> L6c
            java.lang.String r4 = "\n"
            r2.append(r4)     // Catch: java.lang.Exception -> L6c
            int r3 = r3 + 1
            goto L51
        L66:
            java.lang.String r3 = r2.toString()     // Catch: java.lang.Exception -> L6c
            return r3
        L6b:
            goto Lb3
        L6c:
            r0 = move-exception
            boolean r1 = r0 instanceof java.lang.IllegalArgumentException
            if (r1 != 0) goto L97
            boolean r1 = r0 instanceof java.io.IOException
            if (r1 != 0) goto L97
            boolean r1 = r0 instanceof java.lang.IndexOutOfBoundsException
            if (r1 == 0) goto L7a
            goto L97
        L7a:
            java.lang.String r1 = com.google.appinventor.components.runtime.LocationSensor.LOG_TAG
            java.lang.String r2 = r0.getMessage()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected exception thrown by getting current address "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
            goto Lb3
        L97:
            java.lang.String r1 = com.google.appinventor.components.runtime.LocationSensor.LOG_TAG
            java.lang.String r2 = r0.getMessage()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Exception thrown by getting current address "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r1, r2)
        Lb3:
            java.lang.String r0 = "No address available"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.LocationSensor.CurrentAddress():java.lang.String");
    }

    @SimpleFunction(description = "Derives latitude of given address")
    public double LatitudeFromAddress(String locationName) {
        try {
            List<Address> addressObjs = this.geocoder.getFromLocationName(locationName, 1);
            Log.i(LOG_TAG, "latitude addressObjs size is " + addressObjs.size() + " for " + locationName);
            if (addressObjs == null || addressObjs.size() == 0) {
                throw new IOException("");
            }
            return addressObjs.get(0).getLatitude();
        } catch (IOException e) {
            this.form.dispatchErrorOccurredEvent(this, "LatitudeFromAddress", 101, locationName);
            return 0.0d;
        }
    }

    @SimpleFunction(description = "Derives longitude of given address")
    public double LongitudeFromAddress(String locationName) {
        try {
            List<Address> addressObjs = this.geocoder.getFromLocationName(locationName, 1);
            Log.i(LOG_TAG, "longitude addressObjs size is " + addressObjs.size() + " for " + locationName);
            if (addressObjs == null || addressObjs.size() == 0) {
                throw new IOException("");
            }
            return addressObjs.get(0).getLongitude();
        } catch (IOException e) {
            this.form.dispatchErrorOccurredEvent(this, "LongitudeFromAddress", 102, locationName);
            return 0.0d;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public List<String> AvailableProviders() {
        return this.allProviders;
    }

    public void RefreshProvider(final String caller) {
        if (this.initialized) {
            stopListening();
            if (!this.havePermission) {
                this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.LocationSensor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        me.form.askPermission(new BulkPermissionRequest(me, "RefreshProvider", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION") { // from class: com.google.appinventor.components.runtime.LocationSensor.1.1
                            @Override // com.google.appinventor.components.runtime.util.BulkPermissionRequest
                            public void onGranted() {
                                me.havePermission = true;
                                me.RefreshProvider(caller);
                                Log.d(LocationSensor.LOG_TAG, "Permission Granted");
                            }
                        });
                    }
                });
            }
            if (this.providerLocked && !empty(this.providerName)) {
                this.listening = startProvider(this.providerName);
                return;
            }
            this.allProviders = this.locationManager.getProviders(true);
            String bProviderName = this.locationManager.getBestProvider(this.locationCriteria, true);
            if (bProviderName != null && !bProviderName.equals(this.allProviders.get(0))) {
                this.allProviders.add(0, bProviderName);
            }
            for (String providerN : this.allProviders) {
                boolean startProvider = startProvider(providerN);
                this.listening = startProvider;
                if (startProvider) {
                    if (!this.providerLocked) {
                        this.providerName = providerN;
                        return;
                    }
                    return;
                }
            }
        }
    }

    private boolean startProvider(String providerName) {
        this.providerName = providerName;
        LocationProvider tLocationProvider = this.locationManager.getProvider(providerName);
        if (tLocationProvider == null) {
            Log.d(LOG_TAG, "getProvider(" + providerName + ") returned null");
            return false;
        }
        stopListening();
        this.locationProvider = tLocationProvider;
        this.locationManager.requestLocationUpdates(providerName, this.timeInterval, this.distanceInterval, this.myLocationListener);
        this.listening = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListening() {
        if (this.listening) {
            this.locationManager.removeUpdates(this.myLocationListener);
            this.locationProvider = null;
            this.listening = false;
        }
    }

    @Override // com.google.appinventor.components.runtime.OnResumeListener
    public void onResume() {
        if (this.enabled) {
            RefreshProvider("onResume");
        }
    }

    @Override // com.google.appinventor.components.runtime.OnStopListener
    public void onStop() {
        stopListening();
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        stopListening();
    }

    public void addListener(LocationSensorListener listener) {
        listener.setSource(this);
        this.listeners.add(listener);
    }

    public void removeListener(LocationSensorListener listener) {
        this.listeners.remove(listener);
        listener.setSource(null);
    }

    private boolean empty(String s) {
        return s == null || s.length() == 0;
    }

    @Override // com.google.appinventor.components.runtime.ObservableDataSource
    public void addDataObserver(DataSourceChangeListener dataComponent) {
        this.dataSourceObservers.add(dataComponent);
    }

    @Override // com.google.appinventor.components.runtime.ObservableDataSource
    public void removeDataObserver(DataSourceChangeListener dataComponent) {
        this.dataSourceObservers.remove(dataComponent);
    }

    @Override // com.google.appinventor.components.runtime.ObservableDataSource
    public void notifyDataObservers(String key, Object value) {
        for (DataSourceChangeListener dataComponent : this.dataSourceObservers) {
            dataComponent.onReceiveValue(this, key, value);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.appinventor.components.runtime.DataSource
    public Float getDataValue(String key) {
        char c;
        switch (key.hashCode()) {
            case -1439978388:
                if (key.equals(PropertyTypeConstants.PROPERTY_TYPE_LATITUDE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 109641799:
                if (key.equals("speed")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 137365935:
                if (key.equals(PropertyTypeConstants.PROPERTY_TYPE_LONGITUDE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2036550306:
                if (key.equals("altitude")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return Float.valueOf((float) this.latitude);
            case 1:
                return Float.valueOf((float) this.longitude);
            case 2:
                return Float.valueOf((float) this.altitude);
            case 3:
                return Float.valueOf(this.speed);
            default:
                return Float.valueOf(0.0f);
        }
    }
}
