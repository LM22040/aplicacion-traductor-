package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.MapFeature;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GeometryUtil;
import com.google.appinventor.components.runtime.util.MapFactory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "LineString is a component for drawing an open, continuous sequence of lines on a Map. To add new points to a LineString in the designer, drag the midpoint of any segment away from the line to introduce a new vertex. Move a vertex by clicking and dragging the vertex to a new location. Clicking on a vertex will delete the vertex, unless only two remain.", iconName = "images/linestring.png", version = 2)
@SimpleObject
/* loaded from: classes.dex */
public class LineString extends MapFeatureBase implements MapFactory.MapLineString {
    private static final String TAG = LineString.class.getSimpleName();
    private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() { // from class: com.google.appinventor.components.runtime.LineString.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapMarker marker, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(marker, (LineString) arguments[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(marker, (LineString) arguments[0]));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapLineString lineString, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(lineString, (LineString) arguments[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(lineString, (LineString) arguments[0]));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapPolygon polygon, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString) arguments[0], polygon));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString) arguments[0], polygon));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapCircle circle, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString) arguments[0], circle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString) arguments[0], circle));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapRectangle rectangle, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((LineString) arguments[0], rectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((LineString) arguments[0], rectangle));
        }
    };
    private List<GeoPoint> points;

    public LineString(MapFactory.MapFeatureContainer container) {
        super(container, distanceComputation);
        this.points = new ArrayList();
        StrokeWidth(3);
        container.addFeature(this);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeature
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the map feature. For LineString, this returns MapFeature.LineString (\"LineString\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.LineString;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapLineString
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A list of latitude and longitude pairs that represent the line segments of the polyline.")
    public YailList Points() {
        return GeometryUtil.pointsListToYailList(this.points);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapLineString
    @SimpleProperty
    public void Points(YailList points) {
        if (points.size() < 2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(points.length() - 1));
            return;
        }
        try {
            this.points = GeometryUtil.pointsFromYailList(points);
            clearGeometry();
            this.map.getController().updateFeaturePosition(this);
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", e.getErrorCode(), e.getArguments());
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTAREA)
    public void PointsFromString(String points) {
        int i = 1;
        try {
            List<GeoPoint> geopoints = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(points);
                try {
                    if (array.length() < 2) {
                        throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, Integer.valueOf(array.length()));
                    }
                    int length = array.length();
                    int i2 = 0;
                    while (i2 < length) {
                        JSONArray point = array.optJSONArray(i2);
                        if (point == null) {
                            throw new DispatchableError(ErrorMessages.ERROR_EXPECTED_ARRAY_AT_INDEX, Integer.valueOf(i2), array.get(i2).toString());
                        }
                        if (point.length() < 2) {
                            throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_FIELDS, Integer.valueOf(i2), Integer.valueOf(points.length()));
                        }
                        double latitude = point.optDouble(0, Double.NaN);
                        double longitude = point.optDouble(i, Double.NaN);
                        if (!GeometryUtil.isValidLatitude(latitude)) {
                            Object[] objArr = new Object[2];
                            objArr[0] = Integer.valueOf(i2);
                            objArr[1] = array.get(0).toString();
                            throw new DispatchableError(ErrorMessages.ERROR_INVALID_LATITUDE_IN_POINT_AT_INDEX, objArr);
                        }
                        try {
                            if (!GeometryUtil.isValidLongitude(longitude)) {
                                throw new DispatchableError(ErrorMessages.ERROR_INVALID_LONGITUDE_IN_POINT_AT_INDEX, Integer.valueOf(i2), array.get(1).toString());
                            }
                            geopoints.add(new GeoPoint(latitude, longitude));
                            i2++;
                            i = 1;
                        } catch (DispatchableError e) {
                            e = e;
                            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", e.getErrorCode(), e.getArguments());
                        } catch (JSONException e2) {
                            e = e2;
                            String functionName = TAG;
                            Log.e(functionName, "Malformed string to LineString.PointsFromString", e);
                            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_LINESTRING_PARSE_ERROR, e.getMessage());
                        }
                    }
                    this.points = geopoints;
                    clearGeometry();
                    this.map.getController().updateFeaturePosition(this);
                } catch (DispatchableError e3) {
                    e = e3;
                } catch (JSONException e4) {
                    e = e4;
                }
            } catch (DispatchableError e5) {
                e = e5;
                this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", e.getErrorCode(), e.getArguments());
            } catch (JSONException e6) {
                e = e6;
                String functionName2 = TAG;
                Log.e(functionName2, "Malformed string to LineString.PointsFromString", e);
                this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_LINESTRING_PARSE_ERROR, e.getMessage());
            }
        } catch (DispatchableError e7) {
            e = e7;
        } catch (JSONException e8) {
            e = e8;
        }
    }

    @Override // com.google.appinventor.components.runtime.MapFeatureBase, com.google.appinventor.components.runtime.util.MapFactory.HasStroke
    @SimpleProperty
    @DesignerProperty(defaultValue = Component.TYPEFACE_MONOSPACE)
    public void StrokeWidth(int width) {
        super.StrokeWidth(width);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapLineString
    public List<GeoPoint> getPoints() {
        return this.points;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeature
    public <T> T accept(MapFactory.MapFeatureVisitor<T> visitor, Object... arguments) {
        return visitor.visit(this, arguments);
    }

    @Override // com.google.appinventor.components.runtime.MapFeatureBase
    protected Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.points);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapLineString
    public void updatePoints(List<GeoPoint> points) {
        this.points = points;
        clearGeometry();
    }
}
