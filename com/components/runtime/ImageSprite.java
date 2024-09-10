package com.google.appinventor.components.runtime;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.Vector2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UsesPermissions(permissionNames = "android.permission.INTERNET")
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>Ball</code>s and other <code>ImageSprite</code>s) and the edge of the Canvas, and move according to its property values.  Its appearance is that of the image specified in its <code>Picture</code> property (unless its <code>Visible</code> property is <code>False</code>).</p> <p>To have an <code>ImageSprite</code> move 10 pixels to the left every 1000 milliseconds (one second), for example, you would set the <code>Speed</code> property to 10 [pixels], the <code>Interval</code> property to 1000 [milliseconds], the <code>Heading</code> property to 180 [degrees], and the <code>Enabled</code> property to <code>True</code>.  A sprite whose <code>Rotates</code> property is <code>True</code> will rotate its image as the sprite's <code>Heading</code> changes.  Checking for collisions with a rotated sprite currently checks the sprite's unrotated position so that collision checking will be inaccurate for tall narrow or short wide sprites that are rotated.  Any of the sprite properties can be changed at any time under program control.</p> ", iconName = "images/imageSprite.png", version = 10)
@SimpleObject
/* loaded from: classes.dex */
public class ImageSprite extends Sprite {
    private BitmapDrawable drawable;
    private final Form form;
    private int heightHint;
    private String picturePath;
    private boolean rotates;
    private int widthHint;

    public ImageSprite(ComponentContainer container) {
        super(container);
        this.widthHint = -1;
        this.heightHint = -1;
        this.picturePath = "";
        this.form = container.$form();
        this.rotates = true;
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    public void onDraw(android.graphics.Canvas canvas) {
        if (this.drawable != null && this.visible) {
            int xinit = (int) (((float) Math.round(this.xLeft)) * this.form.deviceDensity());
            int yinit = (int) (((float) Math.round(this.yTop)) * this.form.deviceDensity());
            int w = (int) (Width() * this.form.deviceDensity());
            int h = (int) (Height() * this.form.deviceDensity());
            this.drawable.setBounds(xinit, yinit, xinit + w, yinit + h);
            if (!this.rotates) {
                this.drawable.draw(canvas);
                return;
            }
            canvas.save();
            canvas.rotate((float) (-Heading()), xinit + (w * ((float) this.u)), yinit + (h * ((float) this.v)));
            this.drawable.draw(canvas);
            canvas.restore();
        }
    }

    protected Vector2D getCenterVector() {
        double d = this.xLeft;
        double Width = Width();
        Double.isNaN(Width);
        double xCenter = d + (Width / 2.0d);
        double d2 = this.yTop;
        double Height = Height();
        Double.isNaN(Height);
        double yCenter = d2 + (Height / 2.0d);
        Vector2D center = new Vector2D(xCenter, yCenter);
        return getVectorRotated(center);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Vector2D> getNormalAxes() {
        List<Vector2D> corners = getExtremityVectors();
        List<Vector2D> normalAxes = new ArrayList<>();
        Vector2D leftRightEdge = Vector2D.difference(corners.get(0), corners.get(1));
        Vector2D topDownEdge = Vector2D.difference(corners.get(1), corners.get(2));
        Vector2D leftRightNormal = leftRightEdge.getNormalVector();
        Vector2D topDownNormal = topDownEdge.getNormalVector();
        normalAxes.add(leftRightNormal);
        normalAxes.add(topDownNormal);
        return normalAxes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double getMinProjection(Vector2D axis) {
        List<Vector2D> corners = getExtremityVectors();
        double minimum = Vector2D.dotProduct(axis, corners.get(0));
        for (Vector2D point : corners) {
            double projectionMagnitude = Vector2D.dotProduct(axis, point);
            if (projectionMagnitude < minimum) {
                minimum = projectionMagnitude;
            }
        }
        return minimum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double getMaxProjection(Vector2D axis) {
        List<Vector2D> corners = getExtremityVectors();
        double maximum = Vector2D.dotProduct(axis, corners.get(0));
        for (Vector2D point : corners) {
            double projectionMagnitude = Vector2D.dotProduct(axis, point);
            if (projectionMagnitude > maximum) {
                maximum = projectionMagnitude;
            }
        }
        return maximum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Vector2D> getExtremityVectors() {
        List<Vector2D> corners = new ArrayList<>();
        char c = 0;
        char c2 = 1;
        int[][] delta = {new int[]{0, 0}, new int[]{1, 0}, new int[]{1, 1}, new int[]{0, 1}};
        int length = delta.length;
        int i = 0;
        while (i < length) {
            int[] d = delta[i];
            double dx = d[c] * Width();
            double dy = d[c2] * Height();
            double d2 = this.xLeft;
            Double.isNaN(dx);
            double d3 = this.yTop;
            Double.isNaN(dy);
            Vector2D corner = new Vector2D(d2 + dx, d3 + dy);
            corners.add(getVectorRotated(corner));
            i++;
            c = 0;
            c2 = 1;
        }
        return corners;
    }

    private Vector2D getVectorRotated(Vector2D toRotate) {
        if (this.rotates) {
            Vector2D origin = new Vector2D(this.xOrigin, this.yOrigin);
            Vector2D originToPoint = Vector2D.difference(toRotate, origin);
            originToPoint.rotate(this.headingRadians);
            return Vector2D.addition(origin, originToPoint);
        }
        return toRotate;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The picture that determines the ImageSprite's appearance.")
    public String Picture() {
        return this.picturePath;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
    public void Picture(@Asset String path) {
        String str = path == null ? "" : path;
        this.picturePath = str;
        try {
            this.drawable = MediaUtil.getBitmapDrawable(this.form, str);
        } catch (IOException e) {
            Log.e("ImageSprite", "Unable to load " + this.picturePath);
            this.drawable = null;
        }
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty(description = "The height of the ImageSprite in pixels.")
    public int Height() {
        int i = this.heightHint;
        if (i == -1 || i == -2 || i <= -1000) {
            if (this.drawable == null) {
                return 0;
            }
            return (int) (r0.getBitmap().getHeight() / this.form.deviceDensity());
        }
        return i;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Height(int height) {
        this.heightHint = height;
        this.yTop = yOriginToTop(this.yOrigin);
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public void HeightPercent(int pCent) {
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty(description = "The width of the ImageSprite in pixels.")
    public int Width() {
        int i = this.widthHint;
        if (i == -1 || i == -2 || i <= -1000) {
            if (this.drawable == null) {
                return 0;
            }
            return (int) (r0.getBitmap().getWidth() / this.form.deviceDensity());
        }
        return i;
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    @SimpleProperty
    public void Width(int width) {
        this.widthHint = width;
        this.xLeft = xOriginToLeft(this.xOrigin);
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.VisibleComponent
    public void WidthPercent(int pCent) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the image should rotate to match the ImageSprite's heading. The sprite rotates around its origin.")
    public boolean Rotates() {
        return this.rotates;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Rotates(boolean rotates) {
        this.rotates = rotates;
        registerChange();
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    @SimpleProperty(description = "The horizontal coordinate of the origin of the ImageSprite, increasing as the ImageSprite moves right.")
    public double X() {
        return super.X();
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    @SimpleProperty(description = "The vertical coordinate of the origin of the ImageSprite, increasing as the ImageSprite moves down.")
    public double Y() {
        return super.Y();
    }

    @SimpleProperty
    public double OriginX() {
        return super.U();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "0.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_UNIT_COORDINATE)
    public void OriginX(double u) {
        super.U(u);
    }

    @SimpleProperty
    public double OriginY() {
        return super.V();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "0.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_UNIT_COORDINATE)
    public void OriginY(double v) {
        super.V(v);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Mark the origin of the image sprite using a draggable marker")
    @DesignerProperty(defaultValue = "(0.0, 0.0)", editorType = PropertyTypeConstants.PROPERTY_TYPE_ORIGIN)
    public void MarkOrigin(String originCoordinates) {
        double u = Double.parseDouble(originCoordinates.substring(1, originCoordinates.indexOf(",")));
        double v = Double.parseDouble(originCoordinates.substring(originCoordinates.indexOf(",") + 2, originCoordinates.length() - 1));
        super.U(u);
        super.V(v);
    }

    @Override // com.google.appinventor.components.runtime.Sprite
    @SimpleFunction(description = "Moves the ImageSprite so that its origin is at the specified x and y coordinates.")
    public void MoveTo(double x, double y) {
        super.MoveTo(x, y);
    }
}
