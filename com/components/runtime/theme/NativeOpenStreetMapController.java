package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.SVG;
import com.google.appinventor.components.common.MapType;
import com.google.appinventor.components.common.ScaleUnits;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.view.ZoomControlView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.modules.IFilesystemCache;
import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.tileprovider.modules.MapTileSqlCacheProvider;
import org.osmdroid.tileprovider.modules.TileWriter;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.CopyrightOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayWithIW;
import org.osmdroid.views.overlay.OverlayWithIWVisitor;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.IOrientationProvider;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.infowindow.OverlayInfoWindow;
import org.osmdroid.views.overlay.mylocation.IMyLocationConsumer;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class NativeOpenStreetMapController implements MapFactory.MapController, MapListener {
    private static final long SPECIFIED_FILL = 1;
    private static final long SPECIFIED_FILL_OPACITY = 4;
    private static final long SPECIFIED_STROKE = 8;
    private static final long SPECIFIED_STROKE_OPACITY = 16;
    private static final long SPECIFIED_STROKE_WIDTH = 32;
    private boolean caches;
    private RelativeLayout containerView;
    private OverlayInfoWindow defaultInfoWindow;
    private final Form form;
    private final AppInventorLocationSensorAdapter locationProvider;
    private ScaleBarOverlay scaleBar;
    private MapType tileType;
    private TouchOverlay touch;
    private final MyLocationNewOverlay userLocation;
    private MapView view;
    private boolean zoomControlEnabled;
    private ZoomControlView zoomControls;
    private boolean zoomEnabled;
    private static final String TAG = NativeOpenStreetMapController.class.getSimpleName();
    private static final float[] ANCHOR_HORIZONTAL = {Float.NaN, 0.0f, 1.0f, 0.5f};
    private static final float[] ANCHOR_VERTICAL = {Float.NaN, 0.0f, 0.5f, 1.0f};
    private CompassOverlay compass = null;
    private RotationGestureOverlay rotation = null;
    private Set<MapFactory.MapEventListener> eventListeners = new HashSet();
    private Map<MapFactory.MapFeature, OverlayWithIW> featureOverlays = new HashMap();
    private SVG defaultMarkerSVG = null;
    private boolean ready = false;
    private float lastAzimuth = Float.NaN;
    private Set<MapFactory.MapFeatureCollection> hiddenFeatureCollections = new HashSet();
    private Set<MapFactory.MapFeature> hiddenFeatures = new HashSet();

    /* loaded from: classes.dex */
    private static class AppInventorLocationSensorAdapter implements IMyLocationProvider, LocationSensor.LocationSensorListener {
        private IMyLocationConsumer consumer;
        private boolean enabled;
        private Location lastLocation;
        private LocationSensor source;

        private AppInventorLocationSensorAdapter() {
            this.enabled = false;
        }

        @Override // com.google.appinventor.components.runtime.LocationSensor.LocationSensorListener
        public void setSource(LocationSensor source) {
            LocationSensor locationSensor = this.source;
            if (locationSensor == source) {
                return;
            }
            if (locationSensor != null) {
                locationSensor.Enabled(false);
            }
            this.source = source;
            if (source != null) {
                source.Enabled(this.enabled);
            }
        }

        @Override // com.google.appinventor.components.runtime.LocationSensor.LocationSensorListener
        public void onTimeIntervalChanged(int time) {
        }

        @Override // com.google.appinventor.components.runtime.LocationSensor.LocationSensorListener
        public void onDistanceIntervalChanged(int distance) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            this.lastLocation = location;
            IMyLocationConsumer iMyLocationConsumer = this.consumer;
            if (iMyLocationConsumer != null) {
                iMyLocationConsumer.onLocationChanged(location, this);
            }
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String s) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String s) {
        }

        public boolean startLocationProvider(IMyLocationConsumer consumer) {
            this.consumer = consumer;
            LocationSensor locationSensor = this.source;
            if (locationSensor != null) {
                locationSensor.Enabled(true);
                this.enabled = true;
            }
            return this.enabled;
        }

        public void stopLocationProvider() {
            LocationSensor locationSensor = this.source;
            if (locationSensor != null) {
                locationSensor.Enabled(false);
            }
            this.enabled = false;
        }

        public Location getLastKnownLocation() {
            return this.lastLocation;
        }

        public void destroy() {
            this.consumer = null;
        }
    }

    /* loaded from: classes.dex */
    private class TouchOverlay extends Overlay {
        private boolean scrollEnabled;

        private TouchOverlay() {
            this.scrollEnabled = true;
        }

        public void draw(Canvas arg0, MapView arg1, boolean arg2) {
        }

        public boolean onFling(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY, MapView mapView) {
            return !this.scrollEnabled;
        }

        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY, MapView mapView) {
            return !this.scrollEnabled;
        }

        public boolean onLongPress(MotionEvent pEvent, MapView pMapView) {
            IGeoPoint p = pMapView.getProjection().fromPixels((int) pEvent.getX(), (int) pEvent.getY());
            double lat = p.getLatitude();
            double lng = p.getLongitude();
            for (MapFactory.MapEventListener l : NativeOpenStreetMapController.this.eventListeners) {
                l.onLongPress(lat, lng);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    private class MapReadyHandler extends Handler {
        private MapReadyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (!NativeOpenStreetMapController.this.ready && NativeOpenStreetMapController.this.form.canDispatchEvent(null, "MapReady")) {
                        NativeOpenStreetMapController.this.ready = true;
                        NativeOpenStreetMapController.this.form.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.MapReadyHandler.1
                            @Override // java.lang.Runnable
                            public void run() {
                                for (MapFactory.MapEventListener l : NativeOpenStreetMapController.this.eventListeners) {
                                    l.onReady(NativeOpenStreetMapController.this);
                                }
                            }
                        });
                    }
                    NativeOpenStreetMapController.this.view.invalidate();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CustomMapView extends MapView {
        public CustomMapView(Context context) {
            super(context, (MapTileProviderBase) null, new MapReadyHandler());
        }

        public CustomMapView(Context context, MapTileProviderBase tileProvider) {
            super(context, tileProvider, new MapReadyHandler());
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            scrollTo(getScrollX() + ((oldw - w) / 2), getScrollY() + ((oldh - h) / 2));
            super.onSizeChanged(w, h, oldw, oldh);
        }

        public void onDetach() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CustomMapTileProviderBasic extends MapTileProviderBasic {
        public CustomMapTileProviderBasic(Context context, ITileSource tileSource, IFilesystemCache cacheWriter) {
            super(context, tileSource, cacheWriter);
            for (int i = 0; i < this.mTileProviderList.size(); i++) {
                if (this.mTileProviderList.get(i) instanceof MapTileSqlCacheProvider) {
                    this.mTileProviderList.set(i, new MapTileFilesystemProvider(this.mRegisterReceiver, tileSource));
                }
            }
        }
    }

    CustomMapView createCustomMapView(Context context) {
        return new CustomMapView(context, new CustomMapTileProviderBasic(context, TileSourceFactory.DEFAULT_TILE_SOURCE, new TileWriter()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeOpenStreetMapController(Form form) {
        byte b = 0;
        this.touch = null;
        this.defaultInfoWindow = null;
        this.zoomControls = null;
        OpenStreetMapTileProviderConstants.setUserAgentValue(form.getApplication().getPackageName());
        File file = new File(form.getCacheDir(), "osmdroid");
        if (file.exists() || file.mkdirs()) {
            Configuration.getInstance().setOsmdroidBasePath(file);
            File file2 = new File(file, "tiles");
            if (file2.exists() || file2.mkdirs()) {
                Configuration.getInstance().setOsmdroidTileCache(file2);
                this.caches = true;
            }
        }
        this.form = form;
        this.touch = new TouchOverlay();
        this.view = createCustomMapView(form.getApplicationContext());
        AppInventorLocationSensorAdapter appInventorLocationSensorAdapter = new AppInventorLocationSensorAdapter();
        this.locationProvider = appInventorLocationSensorAdapter;
        this.defaultInfoWindow = new OverlayInfoWindow(this.view);
        this.view.setTilesScaledToDpi(true);
        this.view.setMapListener(this);
        this.view.getOverlayManager().add(new CopyrightOverlay(form));
        this.view.getOverlayManager().add(this.touch);
        this.view.addOnTapListener(new MapView.OnTapListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.1
            public void onSingleTap(MapView view, double latitude, double longitude) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onSingleTap(latitude, longitude);
                }
            }

            public void onDoubleTap(MapView view, double latitude, double longitude) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onDoubleTap(latitude, longitude);
                }
            }
        });
        this.zoomControls = new ZoomControlView(this.view);
        this.userLocation = new MyLocationNewOverlay(appInventorLocationSensorAdapter, this.view);
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(this.view);
        this.scaleBar = scaleBarOverlay;
        scaleBarOverlay.setAlignBottom(true);
        this.scaleBar.setAlignRight(true);
        this.scaleBar.disableScaleBar();
        this.view.getOverlayManager().add(this.scaleBar);
        RelativeLayout relativeLayout = new RelativeLayout(form);
        this.containerView = relativeLayout;
        relativeLayout.setClipChildren(true);
        this.containerView.addView((View) this.view, (ViewGroup.LayoutParams) new RelativeLayout.LayoutParams(-1, -1));
        this.containerView.addView(this.zoomControls);
        this.zoomControls.setVisibility(8);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public View getView() {
        return this.containerView;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public double getLatitude() {
        return this.view.getMapCenter().getLatitude();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public double getLongitude() {
        return this.view.getMapCenter().getLongitude();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setCenter(double latitude, double longitude) {
        this.view.getController().setCenter(new GeoPoint(latitude, longitude));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setZoom(int zoom) {
        this.view.getController().setZoom(zoom);
        this.zoomControls.updateButtons();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public int getZoom() {
        return (int) this.view.getZoomLevel(true);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setZoomEnabled(boolean enable) {
        this.zoomEnabled = enable;
        this.view.setMultiTouchControls(enable);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isZoomEnabled() {
        return this.zoomEnabled;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setMapType(MapFactory.MapType type) {
        MapType mapType = MapType.fromUnderlyingValue(Integer.valueOf(type.ordinal()));
        if (mapType != null) {
            setMapTypeAbstract(mapType);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public MapFactory.MapType getMapType() {
        return MapFactory.MapType.values()[this.tileType.toUnderlyingValue().intValue()];
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setMapTypeAbstract(MapType type) {
        this.tileType = type;
        switch (AnonymousClass14.$SwitchMap$com$google$appinventor$components$common$MapType[type.ordinal()]) {
            case 1:
                this.view.setTileSource(TileSourceFactory.MAPNIK);
                return;
            case 2:
                this.view.setTileSource(TileSourceFactory.USGS_SAT);
                return;
            case 3:
                this.view.setTileSource(TileSourceFactory.USGS_TOPO);
                return;
            default:
                return;
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public MapType getMapTypeAbstract() {
        return this.tileType;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setCompassEnabled(boolean enabled) {
        if (enabled && this.compass == null) {
            this.compass = new CompassOverlay(this.view.getContext(), this.view);
            this.view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    float density = NativeOpenStreetMapController.this.view.getContext().getResources().getDisplayMetrics().density;
                    NativeOpenStreetMapController.this.compass.setCompassCenter((NativeOpenStreetMapController.this.view.getMeasuredWidth() / density) - 35.0f, 35.0f);
                    return true;
                }
            });
            this.view.getOverlayManager().add(this.compass);
        }
        CompassOverlay compassOverlay = this.compass;
        if (compassOverlay != null) {
            if (enabled) {
                if (compassOverlay.getOrientationProvider() != null) {
                    this.compass.enableCompass();
                } else {
                    this.compass.enableCompass(new InternalCompassOrientationProvider(this.view.getContext()));
                }
                this.compass.onOrientationChanged(this.lastAzimuth, (IOrientationProvider) null);
                return;
            }
            this.lastAzimuth = compassOverlay.getOrientation();
            this.compass.disableCompass();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isCompassEnabled() {
        CompassOverlay compassOverlay = this.compass;
        return compassOverlay != null && compassOverlay.isCompassEnabled();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setZoomControlEnabled(boolean enabled) {
        if (this.zoomControlEnabled != enabled) {
            this.zoomControls.setVisibility(enabled ? 0 : 8);
            this.zoomControlEnabled = enabled;
            this.containerView.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isZoomControlEnabled() {
        return this.zoomControlEnabled;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setShowUserEnabled(boolean enable) {
        this.userLocation.setEnabled(enable);
        if (enable) {
            this.userLocation.enableMyLocation();
            this.view.getOverlayManager().add(this.userLocation);
        } else {
            this.userLocation.disableMyLocation();
            this.view.getOverlayManager().remove(this.userLocation);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isShowUserEnabled() {
        MyLocationNewOverlay myLocationNewOverlay = this.userLocation;
        return myLocationNewOverlay != null && myLocationNewOverlay.isEnabled();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setRotationEnabled(boolean enabled) {
        if (enabled && this.rotation == null) {
            this.rotation = new RotationGestureOverlay(this.view);
        }
        RotationGestureOverlay rotationGestureOverlay = this.rotation;
        if (rotationGestureOverlay != null) {
            rotationGestureOverlay.setEnabled(enabled);
            if (enabled) {
                this.view.getOverlayManager().add(this.rotation);
            } else {
                this.view.getOverlayManager().remove(this.rotation);
            }
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isRotationEnabled() {
        RotationGestureOverlay rotationGestureOverlay = this.rotation;
        return rotationGestureOverlay != null && rotationGestureOverlay.isEnabled();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setPanEnabled(boolean enable) {
        this.touch.scrollEnabled = enable;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isPanEnabled() {
        return this.touch.scrollEnabled;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void panTo(double latitude, double longitude, int zoom, double seconds) {
        Animation animation;
        this.view.getController().animateTo(new GeoPoint(latitude, longitude));
        if (this.view.getController().zoomTo(zoom) && (animation = this.view.getAnimation()) != null) {
            animation.setDuration((long) (1000.0d * seconds));
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addEventListener(MapFactory.MapEventListener listener) {
        this.eventListeners.add(listener);
        if ((this.ready || ViewCompat.isAttachedToWindow(this.view)) && this.form.canDispatchEvent(null, "MapReady")) {
            this.ready = true;
            listener.onReady(this);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addFeature(final MapFactory.MapMarker aiMarker) {
        createNativeMarker(aiMarker, new AsyncCallbackPair<Marker>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.3
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                Log.e(NativeOpenStreetMapController.TAG, "Unable to create marker: " + message);
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(Marker overlay) {
                overlay.setOnMarkerClickListener(new Marker.OnMarkerClickListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.3.1
                    public boolean onMarkerClick(Marker marker, MapView mapView) {
                        for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                            listener.onFeatureClick(aiMarker);
                        }
                        if (aiMarker.EnableInfobox()) {
                            marker.showInfoWindow();
                            return false;
                        }
                        return false;
                    }

                    public boolean onMarkerLongPress(Marker marker, MapView mapView) {
                        for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                            listener.onFeatureLongPress(aiMarker);
                        }
                        return false;
                    }
                });
                overlay.setOnMarkerDragListener(new Marker.OnMarkerDragListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.3.2
                    public void onMarkerDrag(Marker marker) {
                        for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                            listener.onFeatureDrag(aiMarker);
                        }
                    }

                    public void onMarkerDragEnd(Marker marker) {
                        GeoPoint position = marker.getPosition();
                        aiMarker.updateLocation(position.getLatitude(), position.getLongitude());
                        for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                            listener.onFeatureStopDrag(aiMarker);
                        }
                    }

                    public void onMarkerDragStart(Marker marker) {
                        for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                            listener.onFeatureStartDrag(aiMarker);
                        }
                    }
                });
                if (aiMarker.Visible()) {
                    NativeOpenStreetMapController.this.showOverlay(overlay);
                } else {
                    NativeOpenStreetMapController.this.hideOverlay(overlay);
                }
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addFeature(final MapFactory.MapLineString aiPolyline) {
        OverlayWithIW createNativePolyline = createNativePolyline(aiPolyline);
        this.featureOverlays.put(aiPolyline, createNativePolyline);
        createNativePolyline.setOnClickListener(new Polyline.OnClickListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.4
            public boolean onClick(Polyline arg0, MapView arg1, GeoPoint arg2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureClick(aiPolyline);
                }
                if (aiPolyline.EnableInfobox()) {
                    arg0.showInfoWindow(arg2);
                    return true;
                }
                return true;
            }

            public boolean onLongClick(Polyline arg0, MapView arg1, GeoPoint arg2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureLongPress(aiPolyline);
                }
                return true;
            }
        });
        createNativePolyline.setOnDragListener(new Polyline.OnDragListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.5
            public void onDragStart(Polyline polyline) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureStartDrag(aiPolyline);
                }
            }

            public void onDrag(Polyline polyline) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureDrag(aiPolyline);
                }
            }

            public void onDragEnd(Polyline polyline) {
                aiPolyline.updatePoints(polyline.getPoints());
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureStopDrag(aiPolyline);
                }
            }
        });
        if (aiPolyline.Visible()) {
            showOverlay(createNativePolyline);
        } else {
            hideOverlay(createNativePolyline);
        }
    }

    private void configurePolygon(final MapFactory.MapFeature component, Polygon polygon) {
        this.featureOverlays.put(component, polygon);
        polygon.setOnClickListener(new Polygon.OnClickListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.6
            public boolean onLongClick(Polygon arg0, MapView arg1, GeoPoint arg2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureLongPress(component);
                }
                return true;
            }

            public boolean onClick(Polygon arg0, MapView arg1, GeoPoint arg2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureClick(component);
                }
                if (component.EnableInfobox()) {
                    arg0.showInfoWindow(arg2);
                    return true;
                }
                return true;
            }
        });
        polygon.setOnDragListener(new Polygon.OnDragListener() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.7
            public void onDragStart(Polygon polygon2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureStartDrag(component);
                }
            }

            public void onDrag(Polygon polygon2) {
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureDrag(component);
                }
            }

            public void onDragEnd(Polygon polygon2) {
                MapFactory.MapFeature mapFeature = component;
                if (mapFeature instanceof MapFactory.MapCircle) {
                    double latitude = 0.0d;
                    double longitude = 0.0d;
                    int count = polygon2.getPoints().size();
                    for (GeoPoint p : polygon2.getPoints()) {
                        latitude += p.getLatitude();
                        longitude += p.getLongitude();
                    }
                    if (count > 0) {
                        MapFactory.MapCircle mapCircle = (MapFactory.MapCircle) component;
                        double d = count;
                        Double.isNaN(d);
                        double d2 = count;
                        Double.isNaN(d2);
                        mapCircle.updateCenter(latitude / d, longitude / d2);
                    } else {
                        ((MapFactory.MapCircle) component).updateCenter(0.0d, 0.0d);
                    }
                } else if (mapFeature instanceof MapFactory.MapRectangle) {
                    double north = -90.0d;
                    double east = -180.0d;
                    double west = 180.0d;
                    double south = 90.0d;
                    for (GeoPoint p2 : polygon2.getPoints()) {
                        double lat = p2.getLatitude();
                        double lng = p2.getLongitude();
                        north = Math.max(north, lat);
                        south = Math.min(south, lat);
                        east = Math.max(east, lng);
                        west = Math.min(west, lng);
                    }
                    ((MapFactory.MapRectangle) component).updateBounds(north, west, south, east);
                } else {
                    ((MapFactory.MapPolygon) mapFeature).updatePoints(((MultiPolygon) polygon2).getMultiPoints());
                    ((MapFactory.MapPolygon) component).updateHolePoints(((MultiPolygon) polygon2).getMultiHoles());
                }
                for (MapFactory.MapEventListener listener : NativeOpenStreetMapController.this.eventListeners) {
                    listener.onFeatureStopDrag(component);
                }
            }
        });
        if (component.Visible()) {
            showOverlay(polygon);
        } else {
            hideOverlay(polygon);
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addFeature(MapFactory.MapPolygon aiPolygon) {
        configurePolygon(aiPolygon, createNativePolygon(aiPolygon));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addFeature(MapFactory.MapCircle aiCircle) {
        configurePolygon(aiCircle, createNativeCircle(aiCircle));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void addFeature(MapFactory.MapRectangle aiRectangle) {
        configurePolygon(aiRectangle, createNativeRectangle(aiRectangle));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void removeFeature(MapFactory.MapFeature aiFeature) {
        this.view.getOverlayManager().remove(this.featureOverlays.get(aiFeature));
        this.featureOverlays.remove(aiFeature);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeaturePosition(MapFactory.MapMarker aiMarker) {
        Marker marker = this.featureOverlays.get(aiMarker);
        if (marker != null) {
            marker.setAnchor(ANCHOR_HORIZONTAL[aiMarker.AnchorHorizontal()], ANCHOR_VERTICAL[aiMarker.AnchorVertical()]);
            marker.setPosition(new GeoPoint(aiMarker.Latitude(), aiMarker.Longitude()));
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeaturePosition(MapFactory.MapLineString aiPolyline) {
        Polyline overlay = this.featureOverlays.get(aiPolyline);
        if (overlay != null) {
            overlay.setPoints(aiPolyline.getPoints());
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeaturePosition(MapFactory.MapPolygon aiPolygon) {
        MultiPolygon polygon = this.featureOverlays.get(aiPolygon);
        if (polygon != null) {
            polygon.setMultiPoints(aiPolygon.getPoints());
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureHoles(MapFactory.MapPolygon aiPolygon) {
        MultiPolygon polygon = this.featureOverlays.get(aiPolygon);
        if (polygon != null) {
            polygon.setMultiHoles(aiPolygon.getHolePoints());
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeaturePosition(MapFactory.MapCircle aiCircle) {
        GeoPoint center = new GeoPoint(aiCircle.Latitude(), aiCircle.Longitude());
        Polygon polygon = this.featureOverlays.get(aiCircle);
        if (polygon != null) {
            List<GeoPoint> geopoints = Polygon.pointsAsCircle(center, aiCircle.Radius());
            polygon.setPoints(geopoints);
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeaturePosition(MapFactory.MapRectangle aiRectangle) {
        Polygon polygon = this.featureOverlays.get(aiRectangle);
        if (polygon != null) {
            List<GeoPoint> geopoints = Polygon.pointsAsRect(new org.osmdroid.util.BoundingBox(aiRectangle.NorthLatitude(), aiRectangle.EastLongitude(), aiRectangle.SouthLatitude(), aiRectangle.WestLongitude()));
            polygon.setPoints(geopoints);
            this.view.invalidate();
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureFill(final MapFactory.HasFill aiFeature) {
        OverlayWithIW overlay = this.featureOverlays.get(aiFeature);
        if (overlay == null) {
            return;
        }
        overlay.accept(new OverlayWithIWVisitor() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.8
            public void visit(final Marker marker) {
                NativeOpenStreetMapController.this.getMarkerDrawable((MapFactory.MapMarker) aiFeature, new AsyncCallbackPair<Drawable>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.8.1
                    @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
                    public void onFailure(String message) {
                        Log.e(NativeOpenStreetMapController.TAG, "Unable to update fill color for marker: " + message);
                    }

                    @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
                    public void onSuccess(Drawable result) {
                        marker.setIcon(result);
                        NativeOpenStreetMapController.this.view.invalidate();
                    }
                });
            }

            public void visit(Polyline polyline) {
            }

            public void visit(Polygon polygon) {
                polygon.setFillColor(aiFeature.FillColor());
                NativeOpenStreetMapController.this.view.invalidate();
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureStroke(final MapFactory.HasStroke aiFeature) {
        OverlayWithIW overlay = this.featureOverlays.get(aiFeature);
        if (overlay == null) {
            return;
        }
        overlay.accept(new OverlayWithIWVisitor() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.9
            public void visit(final Marker marker) {
                NativeOpenStreetMapController.this.getMarkerDrawable((MapFactory.MapMarker) aiFeature, new AsyncCallbackPair<Drawable>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.9.1
                    @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
                    public void onFailure(String message) {
                        Log.e(NativeOpenStreetMapController.TAG, "Unable to update stroke color for marker: " + message);
                    }

                    @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
                    public void onSuccess(Drawable result) {
                        marker.setIcon(result);
                        NativeOpenStreetMapController.this.view.invalidate();
                    }
                });
            }

            public void visit(Polyline polyline) {
                DisplayMetrics metrics = new DisplayMetrics();
                NativeOpenStreetMapController.this.form.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                polyline.setColor(aiFeature.StrokeColor());
                polyline.setWidth(aiFeature.StrokeWidth() * metrics.density);
                NativeOpenStreetMapController.this.view.invalidate();
            }

            public void visit(Polygon polygon) {
                DisplayMetrics metrics = new DisplayMetrics();
                NativeOpenStreetMapController.this.form.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                polygon.setStrokeColor(aiFeature.StrokeColor());
                polygon.setStrokeWidth(aiFeature.StrokeWidth() * metrics.density);
                NativeOpenStreetMapController.this.view.invalidate();
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureText(MapFactory.MapFeature aiFeature) {
        OverlayWithIW overlay = this.featureOverlays.get(aiFeature);
        if (overlay != null) {
            overlay.setTitle(aiFeature.Title());
            overlay.setSnippet(aiFeature.Description());
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureDraggable(MapFactory.MapFeature aiFeature) {
        OverlayWithIW overlay = this.featureOverlays.get(aiFeature);
        if (overlay != null) {
            overlay.setDraggable(aiFeature.Draggable());
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureImage(MapFactory.MapMarker aiMarker) {
        final Marker marker = this.featureOverlays.get(aiMarker);
        if (marker == null) {
            return;
        }
        getMarkerDrawable(aiMarker, new AsyncCallbackPair<Drawable>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.10
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                Log.e(NativeOpenStreetMapController.TAG, "Unable to update feature image: " + message);
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(Drawable result) {
                marker.setIcon(result);
                NativeOpenStreetMapController.this.view.invalidate();
            }
        });
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void updateFeatureSize(MapFactory.MapMarker aiMarker) {
        final Marker marker = this.featureOverlays.get(aiMarker);
        if (marker == null) {
            return;
        }
        getMarkerDrawable(aiMarker, new AsyncCallbackPair<Drawable>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.11
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                Log.wtf(NativeOpenStreetMapController.TAG, "Cannot find default marker");
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(Drawable result) {
                marker.setIcon(result);
                NativeOpenStreetMapController.this.view.invalidate();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMarkerDrawable(MapFactory.MapMarker aiMarker, AsyncCallbackPair<Drawable> callback) {
        String assetPath = aiMarker.ImageAsset();
        if (assetPath == null || assetPath.length() == 0 || assetPath.endsWith(".svg")) {
            getMarkerDrawableVector(aiMarker, callback);
        } else {
            getMarkerDrawableRaster(aiMarker, callback);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getMarkerDrawableVector(com.google.appinventor.components.runtime.util.MapFactory.MapMarker r7, com.google.appinventor.components.runtime.util.AsyncCallbackPair<android.graphics.drawable.Drawable> r8) {
        /*
            r6 = this;
            r0 = 0
            com.caverock.androidsvg.SVG r1 = r6.defaultMarkerSVG
            java.lang.String r2 = "Unable to read Marker asset"
            java.lang.String r3 = "Invalid SVG in Marker asset"
            if (r1 != 0) goto L3d
            org.osmdroid.views.MapView r1 = r6.view     // Catch: java.io.IOException -> L1c com.caverock.androidsvg.SVGParseException -> L23
            android.content.Context r1 = r1.getContext()     // Catch: java.io.IOException -> L1c com.caverock.androidsvg.SVGParseException -> L23
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.io.IOException -> L1c com.caverock.androidsvg.SVGParseException -> L23
            java.lang.String r4 = "marker.svg"
            com.caverock.androidsvg.SVG r1 = com.caverock.androidsvg.SVG.getFromAsset(r1, r4)     // Catch: java.io.IOException -> L1c com.caverock.androidsvg.SVGParseException -> L23
            r6.defaultMarkerSVG = r1     // Catch: java.io.IOException -> L1c com.caverock.androidsvg.SVGParseException -> L23
            goto L29
        L1c:
            r1 = move-exception
            java.lang.String r4 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            android.util.Log.e(r4, r2, r1)
            goto L2a
        L23:
            r1 = move-exception
            java.lang.String r4 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            android.util.Log.e(r4, r3, r1)
        L29:
        L2a:
            com.caverock.androidsvg.SVG r1 = r6.defaultMarkerSVG
            if (r1 == 0) goto L35
            com.caverock.androidsvg.SVG$Svg r1 = r1.getRootElement()
            if (r1 == 0) goto L35
            goto L3d
        L35:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Unable to load SVG from assets"
            r1.<init>(r2)
            throw r1
        L3d:
            java.lang.String r1 = r7.ImageAsset()
            if (r1 == 0) goto L97
            int r4 = r1.length()
            if (r4 == 0) goto L97
            org.osmdroid.views.MapView r4 = r6.view     // Catch: java.io.IOException -> L59 com.caverock.androidsvg.SVGParseException -> L60
            android.content.Context r4 = r4.getContext()     // Catch: java.io.IOException -> L59 com.caverock.androidsvg.SVGParseException -> L60
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.io.IOException -> L59 com.caverock.androidsvg.SVGParseException -> L60
            com.caverock.androidsvg.SVG r4 = com.caverock.androidsvg.SVG.getFromAsset(r4, r1)     // Catch: java.io.IOException -> L59 com.caverock.androidsvg.SVGParseException -> L60
            r0 = r4
        L58:
            goto L67
        L59:
            r4 = move-exception
            java.lang.String r5 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            android.util.Log.e(r5, r2, r4)
            goto L67
        L60:
            r4 = move-exception
            java.lang.String r5 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            android.util.Log.e(r5, r3, r4)
            goto L58
        L67:
            if (r0 != 0) goto L97
            r4 = 0
            com.google.appinventor.components.runtime.Form r5 = r6.form     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e com.caverock.androidsvg.SVGParseException -> L86
            java.io.InputStream r5 = com.google.appinventor.components.runtime.util.MediaUtil.openMedia(r5, r1)     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e com.caverock.androidsvg.SVGParseException -> L86
            r4 = r5
            com.caverock.androidsvg.SVG r2 = com.caverock.androidsvg.SVG.getFromInputStream(r4)     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L7e com.caverock.androidsvg.SVGParseException -> L86
            r0 = r2
            java.lang.String r2 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r2, r4)
        L7b:
            goto L97
        L7c:
            r2 = move-exception
            goto L91
        L7e:
            r3 = move-exception
            java.lang.String r5 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG     // Catch: java.lang.Throwable -> L7c
            android.util.Log.e(r5, r2, r3)     // Catch: java.lang.Throwable -> L7c
            goto L8d
        L86:
            r2 = move-exception
            java.lang.String r5 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG     // Catch: java.lang.Throwable -> L7c
            android.util.Log.e(r5, r3, r2)     // Catch: java.lang.Throwable -> L7c
        L8d:
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r5, r4)
            goto L7b
        L91:
            java.lang.String r3 = com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.TAG
            com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r3, r4)
            throw r2
        L97:
            if (r0 != 0) goto L9b
            com.caverock.androidsvg.SVG r0 = r6.defaultMarkerSVG
        L9b:
            android.graphics.drawable.Drawable r2 = r6.rasterizeSVG(r7, r0)     // Catch: java.lang.Exception -> La3
            r8.onSuccess(r2)     // Catch: java.lang.Exception -> La3
            goto Lab
        La3:
            r2 = move-exception
            java.lang.String r3 = r2.getMessage()
            r8.onFailure(r3)
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.getMarkerDrawableVector(com.google.appinventor.components.runtime.util.MapFactory$MapMarker, com.google.appinventor.components.runtime.util.AsyncCallbackPair):void");
    }

    private void getMarkerDrawableRaster(final MapFactory.MapMarker aiMarker, final AsyncCallbackPair<Drawable> callback) {
        MediaUtil.getBitmapDrawableAsync(this.form, aiMarker.ImageAsset(), aiMarker.Width(), aiMarker.Height(), new AsyncCallbackPair<BitmapDrawable>() { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.12
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                callback.onSuccess(NativeOpenStreetMapController.this.getDefaultMarkerDrawable(aiMarker));
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(BitmapDrawable result) {
                result.setAlpha(Math.round(aiMarker.FillOpacity() * 255.0f));
                callback.onSuccess(result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable getDefaultMarkerDrawable(MapFactory.MapMarker aiMarker) {
        return rasterizeSVG(aiMarker, this.defaultMarkerSVG);
    }

    private static float getBestGuessWidth(SVG.Svg svg) {
        if (svg.width != null) {
            return svg.width.floatValue();
        }
        if (svg.viewBox != null) {
            return svg.viewBox.width;
        }
        return 30.0f;
    }

    private static float getBestGuessHeight(SVG.Svg svg) {
        if (svg.height != null) {
            return svg.height.floatValue();
        }
        if (svg.viewBox != null) {
            return svg.viewBox.height;
        }
        return 50.0f;
    }

    private Drawable rasterizeSVG(MapFactory.MapMarker aiMarker, SVG markerSvg) {
        SVG.Svg svg;
        SVG.Svg svg2 = markerSvg.getRootElement();
        float density = this.view.getContext().getResources().getDisplayMetrics().density;
        float height = aiMarker.Height() <= 0 ? getBestGuessHeight(svg2) : aiMarker.Height();
        float width = aiMarker.Width() <= 0 ? getBestGuessWidth(svg2) : aiMarker.Width();
        float scaleH = height / getBestGuessHeight(svg2);
        float scaleW = width / getBestGuessWidth(svg2);
        float scale = (float) Math.sqrt((scaleH * scaleH) + (scaleW * scaleW));
        Paint fillPaint = new Paint();
        Paint strokePaint = new Paint();
        PaintUtil.changePaint(fillPaint, aiMarker.FillColor());
        PaintUtil.changePaint(strokePaint, aiMarker.StrokeColor());
        SVG.Length strokeWidth = new SVG.Length(aiMarker.StrokeWidth() / scale);
        for (SVG.SvgConditionalElement svgConditionalElement : svg2.getChildren()) {
            if (!(svgConditionalElement instanceof SVG.SvgConditionalElement)) {
                svg = svg2;
            } else {
                SVG.SvgConditionalElement path = svgConditionalElement;
                svg = svg2;
                path.baseStyle.fill = new SVG.Colour(fillPaint.getColor());
                path.baseStyle.fillOpacity = Float.valueOf(fillPaint.getAlpha() / 255.0f);
                path.baseStyle.stroke = new SVG.Colour(strokePaint.getColor());
                path.baseStyle.strokeOpacity = Float.valueOf(strokePaint.getAlpha() / 255.0f);
                path.baseStyle.strokeWidth = strokeWidth;
                path.baseStyle.specifiedFlags = 61L;
                if (path.style != null) {
                    if ((path.style.specifiedFlags & SPECIFIED_FILL) == 0) {
                        path.style.fill = new SVG.Colour(fillPaint.getColor());
                        path.style.specifiedFlags |= SPECIFIED_FILL;
                    }
                    if ((path.style.specifiedFlags & SPECIFIED_FILL_OPACITY) == 0) {
                        path.style.fillOpacity = Float.valueOf(fillPaint.getAlpha() / 255.0f);
                        path.style.specifiedFlags |= SPECIFIED_FILL_OPACITY;
                    }
                    if ((path.style.specifiedFlags & SPECIFIED_STROKE) == 0) {
                        path.style.stroke = new SVG.Colour(strokePaint.getColor());
                        path.style.specifiedFlags |= SPECIFIED_STROKE;
                    }
                    if ((path.style.specifiedFlags & SPECIFIED_STROKE_OPACITY) == 0) {
                        path.style.strokeOpacity = Float.valueOf(strokePaint.getAlpha() / 255.0f);
                        path.style.specifiedFlags |= SPECIFIED_STROKE_OPACITY;
                    }
                    if ((path.style.specifiedFlags & SPECIFIED_STROKE_WIDTH) == 0) {
                        path.style.strokeWidth = strokeWidth;
                        path.style.specifiedFlags |= SPECIFIED_STROKE_WIDTH;
                    }
                }
            }
            svg2 = svg;
        }
        Picture picture = markerSvg.renderToPicture();
        Picture scaledPicture = new Picture();
        Canvas canvas = scaledPicture.beginRecording((int) (((aiMarker.StrokeWidth() * 2.0f) + width) * density), (int) (((aiMarker.StrokeWidth() * 2.0f) + height) * density));
        canvas.scale(density * scaleW, density * scaleH);
        canvas.translate(strokeWidth.floatValue(), strokeWidth.floatValue());
        picture.draw(canvas);
        scaledPicture.endRecording();
        return new PictureDrawable(scaledPicture);
    }

    private void createNativeMarker(MapFactory.MapMarker aiMarker, AsyncCallbackPair<Marker> callback) {
        final OverlayWithIW marker = new Marker(this.view);
        this.featureOverlays.put(aiMarker, marker);
        marker.setDraggable(aiMarker.Draggable());
        marker.setTitle(aiMarker.Title());
        marker.setSnippet(aiMarker.Description());
        marker.setPosition(new GeoPoint(aiMarker.Latitude(), aiMarker.Longitude()));
        marker.setAnchor(0.5f, 1.0f);
        getMarkerDrawable(aiMarker, new AsyncCallbackFacade<Drawable, Marker>(callback) { // from class: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController.13
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackFacade, com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                this.callback.onFailure(message);
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(Drawable result) {
                marker.setIcon(result);
                this.callback.onSuccess(marker);
            }
        });
    }

    private Polyline createNativePolyline(MapFactory.MapLineString aiLineString) {
        Polyline osmLine = new Polyline();
        osmLine.setDraggable(aiLineString.Draggable());
        osmLine.setTitle(aiLineString.Title());
        osmLine.setSnippet(aiLineString.Description());
        osmLine.setPoints(aiLineString.getPoints());
        osmLine.setColor(aiLineString.StrokeColor());
        osmLine.setWidth(aiLineString.StrokeWidth());
        osmLine.setInfoWindow(this.defaultInfoWindow);
        return osmLine;
    }

    private void createPolygon(Polygon osmPolygon, MapFactory.MapFeature aiFeature) {
        osmPolygon.setDraggable(aiFeature.Draggable());
        osmPolygon.setTitle(aiFeature.Title());
        osmPolygon.setSnippet(aiFeature.Description());
        osmPolygon.setStrokeColor(((MapFactory.HasStroke) aiFeature).StrokeColor());
        osmPolygon.setStrokeWidth(((MapFactory.HasStroke) aiFeature).StrokeWidth());
        osmPolygon.setFillColor(((MapFactory.HasFill) aiFeature).FillColor());
        osmPolygon.setInfoWindow(this.defaultInfoWindow);
    }

    private MultiPolygon createNativePolygon(MapFactory.MapPolygon aiPolygon) {
        MultiPolygon osmPolygon = new MultiPolygon();
        createPolygon(osmPolygon, aiPolygon);
        osmPolygon.setMultiPoints(aiPolygon.getPoints());
        osmPolygon.setMultiHoles(aiPolygon.getHolePoints());
        return osmPolygon;
    }

    private Polygon createNativeCircle(MapFactory.MapCircle aiCircle) {
        Polygon osmPolygon = new Polygon();
        createPolygon(osmPolygon, aiCircle);
        osmPolygon.setPoints(Polygon.pointsAsCircle(new GeoPoint(aiCircle.Latitude(), aiCircle.Longitude()), aiCircle.Radius()));
        return osmPolygon;
    }

    private Polygon createNativeRectangle(MapFactory.MapRectangle aiRectangle) {
        org.osmdroid.util.BoundingBox bbox = new org.osmdroid.util.BoundingBox(aiRectangle.NorthLatitude(), aiRectangle.EastLongitude(), aiRectangle.SouthLatitude(), aiRectangle.WestLongitude());
        Polygon osmPolygon = new Polygon();
        createPolygon(osmPolygon, aiRectangle);
        osmPolygon.setPoints(new ArrayList(Polygon.pointsAsRect(bbox)));
        return osmPolygon;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void showFeature(MapFactory.MapFeature feature) {
        if (!this.hiddenFeatures.contains(feature)) {
            showOverlay(this.featureOverlays.get(feature));
        }
    }

    protected void showOverlay(OverlayWithIW overlay) {
        this.view.getOverlayManager().add(overlay);
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void hideFeature(MapFactory.MapFeature feature) {
        hideOverlay(this.featureOverlays.get(feature));
    }

    protected void hideOverlay(OverlayWithIW overlay) {
        this.view.getOverlayManager().remove(overlay);
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isFeatureVisible(MapFactory.MapFeature feature) {
        OverlayWithIW overlay = this.featureOverlays.get(feature);
        return overlay != null && this.view.getOverlayManager().contains(overlay);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isFeatureCollectionVisible(MapFactory.MapFeatureCollection collection) {
        return !this.hiddenFeatureCollections.contains(collection);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setFeatureCollectionVisible(MapFactory.MapFeatureCollection collection, boolean visible) {
        if (visible || !this.hiddenFeatureCollections.contains(collection)) {
            if (visible && !this.hiddenFeatureCollections.contains(collection)) {
                return;
            }
            if (visible) {
                this.hiddenFeatureCollections.remove(collection);
                for (MapFactory.MapFeature feature : collection) {
                    this.hiddenFeatures.remove(feature);
                    if (feature.Visible()) {
                        showFeature(feature);
                    }
                }
                return;
            }
            this.hiddenFeatureCollections.add(collection);
            for (MapFactory.MapFeature feature2 : collection) {
                this.hiddenFeatures.add(feature2);
                hideFeature(feature2);
            }
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void showInfobox(MapFactory.MapFeature feature) {
        Polyline polyline = (OverlayWithIW) this.featureOverlays.get(feature);
        if (polyline instanceof Marker) {
            polyline.showInfoWindow();
        } else if (polyline instanceof Polyline) {
            Polyline polyOverlay = polyline;
            polyOverlay.showInfoWindow(feature.getCentroid());
        } else {
            Polygon polyOverlay2 = (Polygon) polyline;
            polyOverlay2.showInfoWindow(feature.getCentroid());
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void hideInfobox(MapFactory.MapFeature feature) {
        OverlayWithIW overlay = this.featureOverlays.get(feature);
        overlay.closeInfoWindow();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isInfoboxVisible(MapFactory.MapFeature feature) {
        OverlayWithIW overlay = this.featureOverlays.get(feature);
        return overlay != null && overlay.isInfoWindowOpen();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public org.osmdroid.util.BoundingBox getBoundingBox() {
        return this.view.getBoundingBox();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setBoundingBox(org.osmdroid.util.BoundingBox bbox) {
        this.view.getController().setCenter(bbox.getCenter());
        this.view.getController().zoomToSpan(bbox.getLatitudeSpan(), bbox.getLongitudeSpan());
    }

    public boolean onScroll(ScrollEvent event) {
        for (MapFactory.MapEventListener listener : this.eventListeners) {
            listener.onBoundsChanged();
        }
        return true;
    }

    public boolean onZoom(ZoomEvent event) {
        this.zoomControls.updateButtons();
        for (MapFactory.MapEventListener listener : this.eventListeners) {
            listener.onZoom();
        }
        return true;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public LocationSensor.LocationSensorListener getLocationListener() {
        return this.locationProvider;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public int getOverlayCount() {
        System.err.println(this.view.getOverlays());
        return this.view.getOverlays().size();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setRotation(float Rotation) {
        this.view.setMapOrientation(Rotation);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public float getRotation() {
        return this.view.getMapOrientation();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setScaleVisible(boolean show) {
        this.scaleBar.setEnabled(show);
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public boolean isScaleVisible() {
        return this.scaleBar.isEnabled();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setScaleUnits(MapFactory.MapScaleUnits units) {
        switch (AnonymousClass14.$SwitchMap$com$google$appinventor$components$runtime$util$MapFactory$MapScaleUnits[units.ordinal()]) {
            case 1:
                this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.metric);
                break;
            case 2:
                this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.imperial);
                break;
            default:
                throw new IllegalArgumentException("Unallowable unit system: " + units);
        }
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public MapFactory.MapScaleUnits getScaleUnits() {
        switch (AnonymousClass14.$SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[this.scaleBar.getUnitsOfMeasure().ordinal()]) {
            case 1:
                return MapFactory.MapScaleUnits.IMPERIAL;
            case 2:
                return MapFactory.MapScaleUnits.METRIC;
            default:
                throw new IllegalStateException("Somehow we have an unallowed unit system");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.appinventor.components.runtime.util.NativeOpenStreetMapController$14, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass14 {
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$common$MapType;
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$common$ScaleUnits;
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$runtime$util$MapFactory$MapScaleUnits;
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure;

        static {
            int[] iArr = new int[ScaleUnits.values().length];
            $SwitchMap$com$google$appinventor$components$common$ScaleUnits = iArr;
            try {
                iArr[ScaleUnits.Metric.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$common$ScaleUnits[ScaleUnits.Imperial.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            int[] iArr2 = new int[ScaleBarOverlay.UnitsOfMeasure.values().length];
            $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure = iArr2;
            try {
                iArr2[ScaleBarOverlay.UnitsOfMeasure.imperial.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[ScaleBarOverlay.UnitsOfMeasure.metric.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr3 = new int[MapFactory.MapScaleUnits.values().length];
            $SwitchMap$com$google$appinventor$components$runtime$util$MapFactory$MapScaleUnits = iArr3;
            try {
                iArr3[MapFactory.MapScaleUnits.METRIC.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MapFactory$MapScaleUnits[MapFactory.MapScaleUnits.IMPERIAL.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            int[] iArr4 = new int[MapType.values().length];
            $SwitchMap$com$google$appinventor$components$common$MapType = iArr4;
            try {
                iArr4[MapType.Road.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$common$MapType[MapType.Aerial.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$common$MapType[MapType.Terrain.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public void setScaleUnitsAbstract(ScaleUnits units) {
        switch (AnonymousClass14.$SwitchMap$com$google$appinventor$components$common$ScaleUnits[units.ordinal()]) {
            case 1:
                this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.metric);
                break;
            case 2:
                this.scaleBar.setUnitsOfMeasure(ScaleBarOverlay.UnitsOfMeasure.imperial);
                break;
        }
        this.view.invalidate();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapController
    public ScaleUnits getScaleUnitsAbstract() {
        switch (AnonymousClass14.$SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[this.scaleBar.getUnitsOfMeasure().ordinal()]) {
            case 1:
                return ScaleUnits.Imperial;
            case 2:
                return ScaleUnits.Metric;
            default:
                throw new IllegalStateException("Somehow we have an unallowed unit system");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MultiPolygon extends Polygon {
        private List<Polygon> children = new ArrayList();
        private Polygon.OnClickListener clickListener;
        private Polygon.OnDragListener dragListener;
        private boolean draggable;

        MultiPolygon() {
        }

        public void showInfoWindow() {
            if (this.children.size() > 0) {
                this.children.get(0).showInfoWindow();
            }
        }

        public void draw(Canvas canvas, MapView mapView, boolean b) {
            for (Polygon child : this.children) {
                child.draw(canvas, mapView, b);
            }
        }

        public List<List<GeoPoint>> getMultiPoints() {
            List<List<GeoPoint>> result = new ArrayList<>();
            for (Polygon p : this.children) {
                result.add(p.getPoints());
            }
            return result;
        }

        public void setMultiPoints(List<List<GeoPoint>> points) {
            Iterator<Polygon> polygonIterator = this.children.iterator();
            Iterator<List<GeoPoint>> pointIterator = points.iterator();
            while (polygonIterator.hasNext() && pointIterator.hasNext()) {
                polygonIterator.next().setPoints(pointIterator.next());
            }
            while (polygonIterator.hasNext()) {
                polygonIterator.next();
                polygonIterator.remove();
            }
            while (pointIterator.hasNext()) {
                Polygon p = new Polygon();
                p.setPoints(pointIterator.next());
                p.setStrokeColor(getStrokeColor());
                p.setFillColor(getFillColor());
                p.setStrokeWidth(getStrokeWidth());
                p.setInfoWindow(getInfoWindow());
                p.setDraggable(this.draggable);
                p.setOnClickListener(this.clickListener);
                p.setOnDragListener(this.dragListener);
                this.children.add(p);
            }
        }

        public List<List<List<GeoPoint>>> getMultiHoles() {
            List<List<List<GeoPoint>>> result = new ArrayList<>();
            for (Polygon p : this.children) {
                result.add(p.getHoles());
            }
            return result;
        }

        public void setMultiHoles(List<List<List<GeoPoint>>> holes) {
            if (holes == null || holes.isEmpty()) {
                for (Polygon child : this.children) {
                    child.setHoles(Collections.emptyList());
                }
                return;
            }
            if (holes.size() != this.children.size()) {
                throw new IllegalArgumentException("Holes and points are not of the same arity.");
            }
            Iterator<Polygon> polygonIterator = this.children.iterator();
            Iterator<List<List<GeoPoint>>> holeIterator = holes.iterator();
            while (polygonIterator.hasNext() && holeIterator.hasNext()) {
                polygonIterator.next().setHoles(holeIterator.next());
            }
        }

        public void setDraggable(boolean draggable) {
            super.setDraggable(draggable);
            this.draggable = draggable;
            for (Polygon child : this.children) {
                child.setDraggable(draggable);
            }
        }

        public void setOnClickListener(Polygon.OnClickListener listener) {
            super.setOnClickListener(listener);
            this.clickListener = listener;
            for (Polygon child : this.children) {
                child.setOnClickListener(listener);
            }
        }

        public void setOnDragListener(Polygon.OnDragListener listener) {
            super.setOnDragListener(listener);
            this.dragListener = listener;
            for (Polygon child : this.children) {
                child.setOnDragListener(listener);
            }
        }

        public void setStrokeWidth(float strokeWidth) {
            super.setStrokeWidth(strokeWidth);
            for (Polygon child : this.children) {
                child.setStrokeWidth(strokeWidth);
            }
        }

        public void setStrokeColor(int strokeColor) {
            super.setStrokeColor(strokeColor);
            for (Polygon child : this.children) {
                child.setStrokeColor(strokeColor);
            }
        }

        public void setFillColor(int fillColor) {
            super.setFillColor(fillColor);
            for (Polygon child : this.children) {
                child.setFillColor(fillColor);
            }
        }

        public void setTitle(String title) {
            super.setTitle(title);
            for (Polygon child : this.children) {
                child.setTitle(title);
            }
        }

        public void setSnippet(String snippet) {
            super.setSnippet(snippet);
            for (Polygon child : this.children) {
                child.setSnippet(snippet);
            }
        }

        public boolean onSingleTapConfirmed(MotionEvent event, MapView mapView) {
            for (Polygon child : this.children) {
                if (child.onSingleTapConfirmed(event, mapView)) {
                    return true;
                }
            }
            return false;
        }

        public boolean contains(MotionEvent event) {
            for (Polygon child : this.children) {
                if (child.contains(event)) {
                    return true;
                }
            }
            return false;
        }

        public boolean onLongPress(MotionEvent event, MapView mapView) {
            boolean touched = contains(event);
            if (touched) {
                if (this.mDraggable) {
                    this.mIsDragged = true;
                    closeInfoWindow();
                    this.mDragStartPoint = event;
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDragStart(this);
                    }
                    moveToEventPosition(event, this.mDragStartPoint, mapView);
                } else if (this.mOnClickListener != null) {
                    this.mOnClickListener.onLongClick(this, mapView, mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY()));
                }
            }
            return touched;
        }

        public void moveToEventPosition(MotionEvent event, MotionEvent start, MapView view) {
            for (Polygon child : this.children) {
                child.moveToEventPosition(event, start, view);
            }
        }

        public void finishMove(MotionEvent start, MotionEvent end, MapView view) {
            for (Polygon child : this.children) {
                child.finishMove(start, end, view);
            }
        }

        public boolean onTouchEvent(MotionEvent event, MapView mapView) {
            if (this.mDraggable && this.mIsDragged) {
                if (event.getAction() == 1) {
                    this.mIsDragged = false;
                    finishMove(this.mDragStartPoint, event, mapView);
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDragEnd(this);
                    }
                    return true;
                }
                if (event.getAction() == 2) {
                    moveToEventPosition(event, this.mDragStartPoint, mapView);
                    if (this.mOnDragListener != null) {
                        this.mOnDragListener.onDrag(this);
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
