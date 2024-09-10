package com.google.appinventor.components.runtime;

import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
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
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.locationtech.jts.geom.Geometry;
import org.osmdroid.util.GeoPoint;

@DesignerComponent(category = ComponentCategory.MAPS, description = "Polygon encloses an arbitrary 2-dimensional area on a Map. Polygons can be used for drawing a perimeter, such as a campus, city, or country. Polygons begin as basic triangles. New vertices can be created by dragging the midpoint of a polygon away from the edge. Clicking on a vertex will remove the vertex, but a minimum of 3 vertices must exist at all times.", iconName = "images/polygon.png", version = 2)
@SimpleObject
/* loaded from: classes.dex */
public class Polygon extends PolygonBase implements MapFactory.MapPolygon {
    private static final String TAG = Polygon.class.getSimpleName();
    private static final MapFactory.MapFeatureVisitor<Double> distanceComputation = new MapFactory.MapFeatureVisitor<Double>() { // from class: com.google.appinventor.components.runtime.Polygon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapMarker marker, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(marker, (Polygon) arguments[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(marker, (Polygon) arguments[0]));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapLineString lineString, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(lineString, (Polygon) arguments[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(lineString, (Polygon) arguments[0]));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapPolygon polygon, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids(polygon, (Polygon) arguments[0]));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges(polygon, (Polygon) arguments[0]));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapCircle circle, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((Polygon) arguments[0], circle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((Polygon) arguments[0], circle));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeatureVisitor
        public Double visit(MapFactory.MapRectangle rectangle, Object... arguments) {
            if (((Boolean) arguments[1]).booleanValue()) {
                return Double.valueOf(GeometryUtil.distanceBetweenCentroids((Polygon) arguments[0], rectangle));
            }
            return Double.valueOf(GeometryUtil.distanceBetweenEdges((Polygon) arguments[0], rectangle));
        }
    };
    private List<List<List<GeoPoint>>> holePoints;
    private boolean initialized;
    private boolean multipolygon;
    private List<List<GeoPoint>> points;

    public Polygon(MapFactory.MapFeatureContainer container) {
        super(container, distanceComputation);
        this.points = new ArrayList();
        this.holePoints = new ArrayList();
        this.multipolygon = false;
        this.initialized = false;
        container.addFeature(this);
    }

    public void Initialize() {
        this.initialized = true;
        clearGeometry();
        this.map.getController().updateFeaturePosition(this);
        this.map.getController().updateFeatureHoles(this);
        this.map.getController().updateFeatureText(this);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeature
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the type of the feature. For polygons, this returns MapFeature.Polygon (\"Polygon\").")
    public String Type() {
        return TypeAbstract().toUnderlyingValue();
    }

    public MapFeature TypeAbstract() {
        return MapFeature.Polygon;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw the polygon.")
    public YailList Points() {
        if (this.points.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (this.multipolygon) {
            List<YailList> result = new LinkedList<>();
            for (List<GeoPoint> part : this.points) {
                result.add(GeometryUtil.pointsListToYailList(part));
            }
            return YailList.makeList((List) result);
        }
        return GeometryUtil.pointsListToYailList(this.points.get(0));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    @SimpleProperty
    public void Points(YailList points) {
        try {
            if (GeometryUtil.isPolygon(points)) {
                this.multipolygon = false;
                this.points.clear();
                this.points.add(GeometryUtil.pointsFromYailList(points));
            } else if (GeometryUtil.isMultiPolygon(points)) {
                this.multipolygon = true;
                this.points = GeometryUtil.multiPolygonFromYailList(points);
            } else {
                throw new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition(this);
            }
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "Points", e.getErrorCode(), e.getArguments());
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Constructs a polygon from the given list of coordinates.")
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTAREA)
    public void PointsFromString(String pointString) {
        if (TextUtils.isEmpty(pointString)) {
            this.points = new ArrayList();
            this.map.getController().updateFeaturePosition(this);
            return;
        }
        try {
            JSONArray content = new JSONArray(pointString);
            if (content.length() == 0) {
                this.points = new ArrayList();
                this.multipolygon = false;
                this.map.getController().updateFeaturePosition(this);
                return;
            }
            List<List<GeoPoint>> multiPolygonToList = GeometryUtil.multiPolygonToList(content);
            this.points = multiPolygonToList;
            this.multipolygon = multiPolygonToList.size() > 1;
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeaturePosition(this);
            }
        } catch (DispatchableError e) {
            getDispatchDelegate().dispatchErrorOccurredEvent(this, "PointsFromString", e.getErrorCode(), e.getArguments());
        } catch (JSONException e2) {
            this.container.$form().dispatchErrorOccurredEvent(this, "PointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, e2.getMessage());
        }
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets or sets the sequence of points used to draw holes in the polygon.")
    public YailList HolePoints() {
        if (this.holePoints.isEmpty()) {
            return YailList.makeEmptyList();
        }
        if (this.multipolygon) {
            List<YailList> result = new LinkedList<>();
            for (List<List<GeoPoint>> polyholes : this.holePoints) {
                result.add(GeometryUtil.multiPolygonToYailList(polyholes));
            }
            return YailList.makeList((List) result);
        }
        return GeometryUtil.multiPolygonToYailList(this.holePoints.get(0));
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    @SimpleProperty
    public void HolePoints(YailList points) {
        try {
            if (points.size() == 0) {
                this.holePoints = new ArrayList();
            } else if (this.multipolygon) {
                this.holePoints = GeometryUtil.multiPolygonHolesFromYailList(points);
            } else if (GeometryUtil.isMultiPolygon(points)) {
                List<List<List<GeoPoint>>> holes = new ArrayList<>();
                holes.add(GeometryUtil.multiPolygonFromYailList(points));
                this.holePoints = holes;
            } else {
                throw new DispatchableError(ErrorMessages.ERROR_POLYGON_PARSE_ERROR, "Unable to determine the structure of the points argument.");
            }
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
        } catch (DispatchableError e) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HolePoints", e.getErrorCode(), e.getArguments());
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Constructs holes in a polygon from a given list of coordinates per hole.")
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_TEXTAREA)
    public void HolePointsFromString(String pointString) {
        if (TextUtils.isEmpty(pointString)) {
            this.holePoints = new ArrayList();
            this.map.getController().updateFeatureHoles(this);
            return;
        }
        try {
            JSONArray content = new JSONArray(pointString);
            if (content.length() == 0) {
                this.holePoints = new ArrayList();
                this.map.getController().updateFeatureHoles(this);
                return;
            }
            this.holePoints = GeometryUtil.multiPolygonHolesToList(content);
            if (this.initialized) {
                clearGeometry();
                this.map.getController().updateFeatureHoles(this);
            }
            Log.d(TAG, "Points: " + this.points);
        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse point string", e);
            this.container.$form().dispatchErrorOccurredEvent(this, "HolePointsFromString", ErrorMessages.ERROR_POLYGON_PARSE_ERROR, e.getMessage());
        }
    }

    @Override // com.google.appinventor.components.runtime.MapFeatureBase
    @SimpleFunction(description = "Returns the centroid of the Polygon as a (latitude, longitude) pair.")
    public YailList Centroid() {
        return super.Centroid();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    public List<List<GeoPoint>> getPoints() {
        return this.points;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    public List<List<List<GeoPoint>>> getHolePoints() {
        return this.holePoints;
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapFeature
    public <T> T accept(MapFactory.MapFeatureVisitor<T> visitor, Object... arguments) {
        return visitor.visit(this, arguments);
    }

    @Override // com.google.appinventor.components.runtime.MapFeatureBase
    protected Geometry computeGeometry() {
        return GeometryUtil.createGeometry(this.points, this.holePoints);
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    public void updatePoints(List<List<GeoPoint>> points) {
        this.points.clear();
        this.points.addAll(points);
        clearGeometry();
    }

    @Override // com.google.appinventor.components.runtime.util.MapFactory.MapPolygon
    public void updateHolePoints(List<List<List<GeoPoint>>> points) {
        this.holePoints.clear();
        this.holePoints.addAll(points);
        clearGeometry();
    }

    boolean isInitialized() {
        return this.initialized;
    }
}
