package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.Direction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.TimerInternal;
import com.google.appinventor.components.runtime.util.Vector2D;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SimpleObject
/* loaded from: classes.dex */
public abstract class Sprite extends VisibleComponent implements AlarmHandler, OnDestroyListener, Deleteable {
    private static final boolean DEFAULT_ENABLED = true;
    private static final int DEFAULT_HEADING = 0;
    private static final int DEFAULT_INTERVAL = 100;
    protected static final String DEFAULT_ORIGIN = "(0.0, 0.0)";
    protected static final boolean DEFAULT_ORIGIN_AT_CENTER = false;
    private static final float DEFAULT_SPEED = 0.0f;
    protected static final double DEFAULT_U = 0.0d;
    protected static final double DEFAULT_V = 0.0d;
    private static final boolean DEFAULT_VISIBLE = true;
    private static final double DEFAULT_Z = 1.0d;
    private static final int DIRECTION_NONE = 0;
    private static final String LOG_TAG = "Sprite";
    private final Handler androidUIHandler;
    protected final Canvas canvas;
    protected Form form;
    protected double heading;
    protected double headingCos;
    protected double headingRadians;
    protected double headingSin;
    protected boolean initialized;
    protected int interval;
    protected boolean originAtCenter;
    private final Set<Sprite> registeredCollisions;
    protected float speed;
    private final TimerInternal timerInternal;
    protected double u;
    protected double userHeading;
    protected double v;
    protected boolean visible;
    protected double xLeft;
    protected double xOrigin;
    protected double yOrigin;
    protected double yTop;
    protected double zLayer;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onDraw(android.graphics.Canvas canvas);

    protected Sprite(ComponentContainer container, Handler handler) {
        this.initialized = false;
        this.visible = true;
        this.androidUIHandler = handler;
        if (!(container instanceof Canvas)) {
            throw new IllegalArgumentError("Sprite constructor called with container " + container);
        }
        Canvas canvas = (Canvas) container;
        this.canvas = canvas;
        canvas.addSprite(this);
        this.registeredCollisions = new HashSet();
        this.timerInternal = new TimerInternal(this, true, 100, handler);
        this.form = container.$form();
        OriginAtCenter(false);
        Heading(0.0d);
        Enabled(true);
        Interval(100);
        Speed(0.0f);
        Visible(true);
        Z(DEFAULT_Z);
        U(0.0d);
        V(0.0d);
        container.$form().registerForOnDestroy(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Sprite(ComponentContainer container) {
        this(container, new Handler());
    }

    public void Initialize() {
        this.initialized = true;
        this.canvas.registerChange(this);
    }

    @SimpleProperty(description = "Controls whether the %type% moves and can be interacted with through collisions, dragging, touching, and flinging.")
    public boolean Enabled() {
        return this.timerInternal.Enabled();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Enabled(boolean enabled) {
        this.timerInternal.Enabled(enabled);
    }

    @SimpleProperty(description = "Returns the %type%'s heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.")
    public double Heading() {
        return this.userHeading;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = Component.TYPEFACE_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void Heading(double userHeading) {
        this.userHeading = userHeading;
        double d = -userHeading;
        this.heading = d;
        double radians = Math.toRadians(d);
        this.headingRadians = radians;
        this.headingCos = Math.cos(radians);
        this.headingSin = Math.sin(this.headingRadians);
        registerChange();
    }

    @SimpleProperty(description = "The interval in milliseconds at which the %type%'s position is updated.  For example, if the interval is 50 and the speed is 10, then every 50 milliseconds the sprite will move 10 pixels in the heading direction.")
    public int Interval() {
        return this.timerInternal.Interval();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @DesignerProperty(defaultValue = "100", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void Interval(int interval) {
        this.timerInternal.Interval(interval);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of pixels that the %type% should move every interval, if enabled.")
    @DesignerProperty(defaultValue = "0.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void Speed(float speed) {
        this.speed = speed;
    }

    @SimpleProperty(description = "The speed at which the %type% moves. The %type% moves this many pixels every interval if enabled.")
    public float Speed() {
        return this.speed;
    }

    @SimpleProperty(description = "Whether the %type% is visible.")
    public boolean Visible() {
        return this.visible;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Visible(boolean visible) {
        this.visible = visible;
        registerChange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double X() {
        return this.xOrigin;
    }

    protected double xLeftToOrigin(double xLeft) {
        double Width = Width();
        double d = this.u;
        Double.isNaN(Width);
        return (Width * d) + xLeft;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double xOriginToLeft(double xOrigin) {
        double Width = Width();
        double d = this.u;
        Double.isNaN(Width);
        return xOrigin - (Width * d);
    }

    private void updateX(double x) {
        this.xOrigin = x;
        this.xLeft = xOriginToLeft(x);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "0.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void X(double x) {
        updateX(x);
        registerChange();
    }

    protected double yTopToOrigin(double yTop) {
        double Height = Height();
        double d = this.v;
        Double.isNaN(Height);
        return (Height * d) + yTop;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double yOriginToTop(double yOrigin) {
        double Height = Height();
        double d = this.v;
        Double.isNaN(Height);
        return yOrigin - (Height * d);
    }

    private void updateY(double y) {
        this.yOrigin = y;
        this.yTop = yOriginToTop(y);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "0.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void Y(double y) {
        updateY(y);
        registerChange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double Y() {
        return this.yOrigin;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    @DesignerProperty(defaultValue = "1.0", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void Z(double layer) {
        this.zLayer = layer;
        this.canvas.changeSpriteLayer(this);
    }

    @SimpleProperty(description = "How the %type% should be layered relative to other Balls and ImageSprites, with higher-numbered layers in front of lower-numbered layers.")
    public double Z() {
        return this.zLayer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void OriginAtCenter(boolean b) {
        this.originAtCenter = b;
        if (b) {
            this.v = 0.5d;
            this.u = 0.5d;
        } else {
            this.v = 0.0d;
            this.u = 0.0d;
        }
        this.xLeft = xOriginToLeft(this.xOrigin);
        this.yTop = yOriginToTop(this.yOrigin);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void U(double u) {
        this.u = u;
        this.xLeft = xOriginToLeft(this.xOrigin);
        registerChange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double U() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void V(double v) {
        this.v = v;
        this.yTop = yOriginToTop(this.yOrigin);
        registerChange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double V() {
        return this.v;
    }

    protected void postEvent(final Sprite sprite, final String eventName, final Object... args) {
        this.androidUIHandler.post(new Runnable() { // from class: com.google.appinventor.components.runtime.Sprite.1
            @Override // java.lang.Runnable
            public void run() {
                EventDispatcher.dispatchEvent(sprite, eventName, args);
            }
        });
    }

    @SimpleEvent
    public void CollidedWith(Sprite other) {
        if (!this.registeredCollisions.contains(other)) {
            this.registeredCollisions.add(other);
            postEvent(this, "CollidedWith", other);
        }
    }

    @SimpleEvent(description = "Event handler called when a %type% is dragged. On all calls, the starting coordinates are where the screen was first touched, and the \"current\" coordinates describe the endpoint of the current line segment. On the first call within a given drag, the \"previous\" coordinates are the same as the starting coordinates; subsequently, they are the \"current\" coordinates from the prior call. Note that the %type% won't actually move anywhere in response to the Dragged event unless MoveTo is explicitly called. For smooth movement, each of its coordinates should be set to the sum of its initial value and the difference between its current and previous values.")
    public void Dragged(float startX, float startY, float prevX, float prevY, float currentX, float currentY) {
        postEvent(this, "Dragged", Float.valueOf(startX), Float.valueOf(startY), Float.valueOf(prevX), Float.valueOf(prevY), Float.valueOf(currentX), Float.valueOf(currentY));
    }

    @SimpleEvent(description = "Event handler called when the %type% reaches an edge of the screen. If Bounce is then called with that edge, the %type% will appear to bounce off of the edge it reached. Edge here is represented as an integer that indicates one of eight directions north (1), northeast (2), east (3), southeast (4), south (-1), southwest (-2), west (-3), and northwest (-4).")
    public void EdgeReached(@Options(Direction.class) int edge) {
        Direction dir = Direction.fromUnderlyingValue(Integer.valueOf(edge));
        if (dir == null) {
            return;
        }
        EdgeReachedAbstract(dir);
    }

    public void EdgeReachedAbstract(Direction edge) {
        postEvent(this, "EdgeReached", edge.toUnderlyingValue());
    }

    @SimpleEvent(description = "Event handler called when a pair of sprites (Balls and ImageSprites) are no longer colliding.")
    public void NoLongerCollidingWith(Sprite other) {
        this.registeredCollisions.remove(other);
        postEvent(this, "NoLongerCollidingWith", other);
    }

    @SimpleEvent(description = "Event handler called when the user touches an enabled %type% and then immediately lifts their finger. The provided x and y coordinates are relative to the upper left of the canvas.")
    public void Touched(float x, float y) {
        postEvent(this, "Touched", Float.valueOf(x), Float.valueOf(y));
    }

    @SimpleEvent(description = "Event handler called when a fling gesture (quick swipe) is made on an enabled %type%. This provides the x and y coordinates of the start of the fling (relative to the upper left of the canvas), the speed (pixels per millisecond), the heading (-180 to 180 degrees), and the x and y velocity components of the fling's vector.")
    public void Flung(float x, float y, float speed, float heading, float xvel, float yvel) {
        postEvent(this, "Flung", Float.valueOf(x), Float.valueOf(y), Float.valueOf(speed), Float.valueOf(heading), Float.valueOf(xvel), Float.valueOf(yvel));
    }

    @SimpleEvent(description = "Event handler called when the user stops touching an enabled %type% (lifting their finger after a TouchDown event). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchUp(float x, float y) {
        postEvent(this, "TouchUp", Float.valueOf(x), Float.valueOf(y));
    }

    @SimpleEvent(description = "Event handler called when the user begins touching an enabled %type% (placing their finger on a %type% and leaving it there). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchDown(float x, float y) {
        postEvent(this, "TouchDown", Float.valueOf(x), Float.valueOf(y));
    }

    @SimpleFunction(description = "Makes the %type% bounce, as if off a wall. For normal bouncing, the edge argument should be the one returned by EdgeReached.")
    public void Bounce(@Options(Direction.class) int edge) {
        Direction dir = Direction.fromUnderlyingValue(Integer.valueOf(edge));
        if (dir == null) {
            return;
        }
        BounceAbstract(dir);
    }

    public void BounceAbstract(Direction edge) {
        MoveIntoBounds();
        double normalizedAngle = this.userHeading % 360.0d;
        if (normalizedAngle < 0.0d) {
            normalizedAngle += 360.0d;
        }
        if ((edge == Direction.East && (normalizedAngle < 90.0d || normalizedAngle > 270.0d)) || (edge == Direction.West && normalizedAngle > 90.0d && normalizedAngle < 270.0d)) {
            Heading(180.0d - normalizedAngle);
            return;
        }
        if ((edge == Direction.North && normalizedAngle > 0.0d && normalizedAngle < 180.0d) || (edge == Direction.South && normalizedAngle > 180.0d)) {
            Heading(360.0d - normalizedAngle);
            return;
        }
        if ((edge == Direction.Northeast && normalizedAngle > 0.0d && normalizedAngle < 90.0d) || ((edge == Direction.Northwest && normalizedAngle > 90.0d && normalizedAngle < 180.0d) || ((edge == Direction.Southwest && normalizedAngle > 180.0d && normalizedAngle < 270.0d) || (edge == Direction.Southeast && normalizedAngle > 270.0d)))) {
            Heading(180.0d + normalizedAngle);
        }
    }

    @SimpleFunction(description = "Indicates whether a collision has been registered between this %type% and the passed sprite (Ball or ImageSprite).")
    public boolean CollidingWith(Sprite other) {
        return this.registeredCollisions.contains(other);
    }

    @SimpleFunction(description = "Moves the %type% back in bounds if part of it extends out of bounds, having no effect otherwise. If the %type% is too wide to fit on the canvas, this aligns the left side of the %type% with the left side of the canvas. If the %type% is too tall to fit on the canvas, this aligns the top side of the %type% with the top side of the canvas.")
    public void MoveIntoBounds() {
        moveIntoBounds(this.canvas.Width(), this.canvas.Height());
    }

    public void MoveTo(double x, double y) {
        updateX(x);
        updateY(y);
        registerChange();
    }

    @SimpleFunction(description = "Moves the origin of %type% to the position of the cooordinates given  by the list formatted as [x-coordinate, y-coordinate].")
    public void MoveToPoint(YailList coordinates) {
        MoveTo(coerceToDouble(coordinates.getObject(0)), coerceToDouble(coordinates.getObject(1)));
    }

    protected static double coerceToDouble(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        try {
            return Double.parseDouble(o.toString());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    @SimpleFunction(description = "Turns the %type% to point towards a designated target sprite (Ball or ImageSprite). The new heading will be parallel to the line joining the origins of the two sprites.")
    public void PointTowards(Sprite target) {
        Heading(-Math.toDegrees(Math.atan2(target.yOrigin - this.yOrigin, target.xOrigin - this.xOrigin)));
    }

    @SimpleFunction(description = "Sets the heading of the %type% toward the point with the coordinates (x, y).")
    public void PointInDirection(double x, double y) {
        Heading(-Math.toDegrees(Math.atan2(y - this.yOrigin, x - this.xOrigin)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerChange() {
        if (!this.initialized) {
            this.canvas.getView().invalidate();
            return;
        }
        Direction edge = hitEdgeAbstract();
        if (edge != null) {
            EdgeReachedAbstract(edge);
        }
        this.canvas.registerChange(this);
    }

    protected int hitEdge() {
        Direction edge = hitEdgeAbstract();
        if (edge == null) {
            return 0;
        }
        return edge.toUnderlyingValue().intValue();
    }

    protected int hitEdge(int canvasWidth, int canvasHeight) {
        Direction edge = hitEdgeAbstract(canvasWidth, canvasHeight);
        if (edge == null) {
            return 0;
        }
        return edge.toUnderlyingValue().intValue();
    }

    protected Direction hitEdgeAbstract() {
        if (!this.canvas.ready()) {
            return null;
        }
        return hitEdgeAbstract(this.canvas.Width(), this.canvas.Height());
    }

    protected Direction hitEdgeAbstract(int canvasWidth, int canvasHeight) {
        boolean west = overWestEdge();
        boolean north = overNorthEdge();
        boolean east = overEastEdge(canvasWidth);
        boolean south = overSouthEdge(canvasHeight);
        if (!north && !south && !east && !west) {
            return null;
        }
        MoveIntoBounds();
        if (west) {
            if (north) {
                return Direction.Northwest;
            }
            if (south) {
                return Direction.Southwest;
            }
            return Direction.West;
        }
        if (east) {
            if (north) {
                return Direction.Northeast;
            }
            if (south) {
                return Direction.Southeast;
            }
            return Direction.East;
        }
        if (north) {
            return Direction.North;
        }
        return Direction.South;
    }

    protected final void moveIntoBounds(int canvasWidth, int canvasHeight) {
        boolean moved = false;
        if (Width() > canvasWidth) {
            if (this.xLeft != 0.0d) {
                this.xLeft = 0.0d;
                this.xOrigin = xLeftToOrigin(0.0d);
                moved = true;
            }
        } else if (overWestEdge()) {
            this.xLeft = 0.0d;
            this.xOrigin = xLeftToOrigin(0.0d);
            moved = true;
        } else if (overEastEdge(canvasWidth)) {
            double Width = canvasWidth - Width();
            this.xLeft = Width;
            this.xOrigin = xLeftToOrigin(Width);
            moved = true;
        }
        if (Height() > canvasHeight) {
            if (this.yTop != 0.0d) {
                this.yTop = 0.0d;
                this.yOrigin = yTopToOrigin(0.0d);
                moved = true;
            }
        } else if (overNorthEdge()) {
            this.yTop = 0.0d;
            this.yOrigin = yTopToOrigin(0.0d);
            moved = true;
        } else if (overSouthEdge(canvasHeight)) {
            double Height = canvasHeight - Height();
            this.yTop = Height;
            this.yOrigin = yTopToOrigin(Height);
            moved = true;
        }
        if (moved) {
            registerChange();
        }
    }

    protected void updateCoordinates() {
        double d = this.xOrigin;
        double d2 = this.speed;
        double d3 = this.headingCos;
        Double.isNaN(d2);
        double d4 = d + (d2 * d3);
        this.xOrigin = d4;
        this.xLeft = xOriginToLeft(d4);
        double d5 = this.yOrigin;
        double d6 = this.speed;
        double d7 = this.headingSin;
        Double.isNaN(d6);
        double d8 = d5 + (d6 * d7);
        this.yOrigin = d8;
        this.yTop = yOriginToTop(d8);
    }

    private final boolean overWestEdge() {
        return this.xLeft < 0.0d;
    }

    private final boolean overEastEdge(int canvasWidth) {
        double d = this.xLeft;
        double Width = Width();
        Double.isNaN(Width);
        return d + Width > ((double) canvasWidth);
    }

    private final boolean overNorthEdge() {
        return this.yTop < 0.0d;
    }

    private final boolean overSouthEdge(int canvasHeight) {
        double d = this.yTop;
        double Height = Height();
        Double.isNaN(Height);
        return d + Height > ((double) canvasHeight);
    }

    public BoundingBox getBoundingBox(int border) {
        double d = this.xLeft;
        double d2 = border;
        Double.isNaN(d2);
        double d3 = d - d2;
        double d4 = this.yTop;
        double d5 = border;
        Double.isNaN(d5);
        double d6 = d4 - d5;
        double Width = Width();
        Double.isNaN(Width);
        double d7 = (d + Width) - DEFAULT_Z;
        double d8 = border;
        Double.isNaN(d8);
        double d9 = d8 + d7;
        double d10 = this.yTop;
        double Height = Height();
        Double.isNaN(Height);
        double d11 = (d10 + Height) - DEFAULT_Z;
        double d12 = border;
        Double.isNaN(d12);
        return new BoundingBox(d3, d6, d9, d11 + d12);
    }

    public static boolean colliding(Sprite sprite1, Sprite sprite2) {
        if ((sprite1 instanceof Ball) && (sprite2 instanceof Ball)) {
            return collidingBalls((Ball) sprite1, (Ball) sprite2);
        }
        if ((sprite1 instanceof ImageSprite) && (sprite2 instanceof ImageSprite)) {
            return collidingImageSprites((ImageSprite) sprite1, (ImageSprite) sprite2);
        }
        if (sprite1 instanceof Ball) {
            return collidingBallAndImageSprite((Ball) sprite1, (ImageSprite) sprite2);
        }
        return collidingBallAndImageSprite((Ball) sprite2, (ImageSprite) sprite1);
    }

    private static boolean collidingBalls(Ball ball1, Ball ball2) {
        double d = ball1.xLeft;
        double Width = ball1.Width();
        Double.isNaN(Width);
        double xCenter1 = d + (Width / 2.0d);
        double d2 = ball1.yTop;
        double Height = ball1.Height();
        Double.isNaN(Height);
        double yCenter1 = d2 + (Height / 2.0d);
        double d3 = ball2.xLeft;
        double Width2 = ball2.Width();
        Double.isNaN(Width2);
        double xCenter2 = d3 + (Width2 / 2.0d);
        double d4 = ball2.yTop;
        double Height2 = ball2.Height();
        Double.isNaN(Height2);
        double yCenter2 = d4 + (Height2 / 2.0d);
        double centerToCenterDistanceSquared = ((xCenter1 - xCenter2) * (xCenter1 - xCenter2)) + ((yCenter1 - yCenter2) * (yCenter1 - yCenter2));
        return centerToCenterDistanceSquared <= Math.pow((double) (ball1.Radius() + ball2.Radius()), 2.0d);
    }

    private static boolean collidingImageSprites(ImageSprite sprite1, ImageSprite sprite2) {
        List<Vector2D> axes = sprite1.getNormalAxes();
        axes.addAll(sprite2.getNormalAxes());
        for (Vector2D a : axes) {
            double minA = sprite1.getMinProjection(a);
            double maxA = sprite1.getMaxProjection(a);
            double minB = sprite2.getMinProjection(a);
            double maxB = sprite2.getMaxProjection(a);
            if (maxA < minB || maxB < minA) {
                return false;
            }
        }
        return true;
    }

    private static boolean collidingBallAndImageSprite(Ball ball, ImageSprite imageSprite) {
        List<Vector2D> axes = imageSprite.getNormalAxes();
        List<Vector2D> imageCorners = imageSprite.getExtremityVectors();
        Vector2D ballCenter = ball.getCenterVector();
        Vector2D closestCorner = ballCenter.getClosestVector(imageCorners);
        Vector2D ballCenterToClosestCorner = Vector2D.difference(closestCorner, ballCenter);
        axes.add(ballCenterToClosestCorner);
        for (Vector2D a : axes) {
            double minA = imageSprite.getMinProjection(a);
            double maxA = imageSprite.getMaxProjection(a);
            double minB = ball.getMinProjection(a);
            double maxB = ball.getMaxProjection(a);
            if (maxA < minB || maxB < minA) {
                return false;
            }
        }
        return true;
    }

    public boolean intersectsWith(BoundingBox rect) {
        BoundingBox rect1 = getBoundingBox(0);
        if (!rect1.intersectDestructively(rect)) {
            return false;
        }
        for (double x = rect1.getLeft(); x < rect1.getRight(); x += DEFAULT_Z) {
            for (double y = rect1.getTop(); y < rect1.getBottom(); y += DEFAULT_Z) {
                if (containsPoint(x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsPoint(double qx, double qy) {
        double d = this.xLeft;
        if (qx >= d) {
            double Width = Width();
            Double.isNaN(Width);
            if (qx < d + Width) {
                double d2 = this.yTop;
                if (qy >= d2) {
                    double Height = Height();
                    Double.isNaN(Height);
                    if (qy < d2 + Height) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.appinventor.components.runtime.AlarmHandler
    public void alarm() {
        if (this.initialized && this.speed != 0.0f) {
            updateCoordinates();
            registerChange();
        }
    }

    @Override // com.google.appinventor.components.runtime.Component
    public HandlesEventDispatching getDispatchDelegate() {
        return this.canvas.$form();
    }

    @Override // com.google.appinventor.components.runtime.OnDestroyListener
    public void onDestroy() {
        this.timerInternal.Enabled(false);
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        this.timerInternal.Enabled(false);
        this.canvas.removeSprite(this);
    }
}
