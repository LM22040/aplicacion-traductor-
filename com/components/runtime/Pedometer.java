package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import java.util.HashSet;
import java.util.Set;
import kawa.lang.SyntaxForms;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "A Component that acts like a Pedometer. It senses motion via the Accelerometer and attempts to determine if a step has been taken. Using a configurable stride length, it can estimate the distance traveled as well. ", iconName = "images/pedometer.png", nonVisible = SyntaxForms.DEBUGGING, version = 3)
@SimpleObject
/* loaded from: classes.dex */
public class Pedometer extends AndroidNonvisibleComponent implements Component, SensorEventListener, Deleteable, RealTimeDataSource<String, Float> {
    private static final int INTERVAL_VARIATION = 250;
    private static final int NUM_INTERVALS = 2;
    private static final float PEAK_VALLEY_RANGE = 40.0f;
    private static final String PREFS_NAME = "PedometerPrefs";
    private static final float STRIDE_LENGTH = 0.73f;
    private static final String TAG = "Pedometer";
    private static final int WIN_SIZE = 100;
    private int avgPos;
    private float[] avgWindow;
    private final Context context;
    private Set<DataSourceChangeListener> dataSourceObservers;
    private boolean foundNonStep;
    private boolean foundValley;
    private int intervalPos;
    private float lastValley;
    private float[] lastValues;
    private int numStepsRaw;
    private int numStepsWithFilter;
    private boolean pedometerPaused;
    private long prevStopClockTime;
    private final SensorManager sensorManager;
    private boolean startPeaking;
    private long startTime;
    private long[] stepInterval;
    private long stepTimestamp;
    private int stopDetectionTimeout;
    private float strideLength;
    private float totalDistance;
    private int winPos;

    public Pedometer(ComponentContainer container) {
        super(container.$form());
        this.stopDetectionTimeout = 2000;
        this.winPos = 0;
        this.intervalPos = 0;
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.lastValley = 0.0f;
        this.lastValues = new float[100];
        this.strideLength = STRIDE_LENGTH;
        this.totalDistance = 0.0f;
        this.stepInterval = new long[2];
        this.stepTimestamp = 0L;
        this.startTime = 0L;
        this.prevStopClockTime = 0L;
        this.foundValley = false;
        this.startPeaking = false;
        this.foundNonStep = true;
        this.pedometerPaused = true;
        this.avgWindow = new float[10];
        this.avgPos = 0;
        this.dataSourceObservers = new HashSet();
        Activity $context = container.$context();
        this.context = $context;
        this.winPos = 0;
        this.startPeaking = false;
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.foundValley = true;
        this.lastValley = 0.0f;
        this.sensorManager = (SensorManager) $context.getSystemService("sensor");
        SharedPreferences settings = $context.getSharedPreferences(PREFS_NAME, 0);
        this.strideLength = settings.getFloat("Pedometer.stridelength", STRIDE_LENGTH);
        this.totalDistance = settings.getFloat("Pedometer.distance", 0.0f);
        this.numStepsRaw = settings.getInt("Pedometer.prevStepCount", 0);
        this.prevStopClockTime = settings.getLong("Pedometer.clockTime", 0L);
        this.numStepsWithFilter = this.numStepsRaw;
        this.startTime = System.currentTimeMillis();
        Log.d(TAG, "Pedometer Created");
    }

    @SimpleFunction(description = "Start counting steps")
    public void Start() {
        if (this.pedometerPaused) {
            this.pedometerPaused = false;
            SensorManager sensorManager = this.sensorManager;
            sensorManager.registerListener(this, sensorManager.getSensorList(1).get(0), 0);
            this.startTime = System.currentTimeMillis();
        }
    }

    @SimpleFunction(description = "Stop counting steps")
    public void Stop() {
        if (!this.pedometerPaused) {
            this.pedometerPaused = true;
            this.sensorManager.unregisterListener(this);
            Log.d(TAG, "Unregistered listener on pause");
            this.prevStopClockTime += System.currentTimeMillis() - this.startTime;
        }
    }

    @SimpleFunction(description = "Resets the step counter, distance measure and time running.")
    public void Reset() {
        this.numStepsWithFilter = 0;
        this.numStepsRaw = 0;
        this.totalDistance = 0.0f;
        this.prevStopClockTime = 0L;
        this.startTime = System.currentTimeMillis();
    }

    @SimpleFunction(description = "Resumes counting, synonym of Start.")
    @Deprecated
    public void Resume() {
        Start();
    }

    @SimpleFunction(description = "Pause counting of steps and distance.")
    @Deprecated
    public void Pause() {
        Stop();
    }

    @SimpleFunction(description = "Saves the pedometer state to the phone. Permits permits accumulation of steps and distance between invocations of an App that uses the pedometer. Different Apps will have their own saved state.")
    public void Save() {
        SharedPreferences settings = this.context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("Pedometer.stridelength", this.strideLength);
        editor.putFloat("Pedometer.distance", this.totalDistance);
        editor.putInt("Pedometer.prevStepCount", this.numStepsRaw);
        if (this.pedometerPaused) {
            editor.putLong("Pedometer.clockTime", this.prevStopClockTime);
        } else {
            editor.putLong("Pedometer.clockTime", this.prevStopClockTime + (System.currentTimeMillis() - this.startTime));
        }
        editor.putLong("Pedometer.closeTime", System.currentTimeMillis());
        editor.commit();
        Log.d(TAG, "Pedometer state saved.");
    }

    @SimpleEvent(description = "This event is run when a raw step is detected.")
    public void SimpleStep(int simpleSteps, float distance) {
        notifyDataObservers("SimpleSteps", (Object) Integer.valueOf(simpleSteps));
        notifyDataObservers("Distance", (Object) Float.valueOf(distance));
        EventDispatcher.dispatchEvent(this, "SimpleStep", Integer.valueOf(simpleSteps), Float.valueOf(distance));
    }

    @SimpleEvent(description = "This event is run when a walking step is detected. A walking step is a step that appears to be involved in forward motion.")
    public void WalkStep(int walkSteps, float distance) {
        notifyDataObservers("WalkSteps", (Object) Integer.valueOf(walkSteps));
        notifyDataObservers("Distance", (Object) Float.valueOf(distance));
        EventDispatcher.dispatchEvent(this, "WalkStep", Integer.valueOf(walkSteps), Float.valueOf(distance));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the average stride length in meters.")
    @DesignerProperty(defaultValue = "0.73", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)
    public void StrideLength(float length) {
        this.strideLength = length;
    }

    @SimpleProperty
    public float StrideLength() {
        return this.strideLength;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The duration in milliseconds of idleness (no steps detected) after which to go into a \"stopped\" state")
    @DesignerProperty(defaultValue = "2000", editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void StopDetectionTimeout(int timeout) {
        this.stopDetectionTimeout = timeout;
    }

    @SimpleProperty
    public int StopDetectionTimeout() {
        return this.stopDetectionTimeout;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The approximate distance traveled in meters.")
    public float Distance() {
        return this.totalDistance;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Time elapsed in milliseconds since the pedometer was started.")
    public long ElapsedTime() {
        if (this.pedometerPaused) {
            return this.prevStopClockTime;
        }
        return this.prevStopClockTime + (System.currentTimeMillis() - this.startTime);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of simple steps taken since the pedometer has started.")
    public int SimpleSteps() {
        return this.numStepsRaw;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "the number of walk steps taken since the pedometer has started.")
    public int WalkSteps() {
        return this.numStepsWithFilter;
    }

    private boolean areStepsEquallySpaced() {
        float avg = 0.0f;
        int num = 0;
        for (long interval : this.stepInterval) {
            if (interval > 0) {
                num++;
                avg += (float) interval;
            }
        }
        float avg2 = avg / num;
        for (long j : this.stepInterval) {
            if (Math.abs(((float) j) - avg2) > 250.0f) {
                return false;
            }
        }
        return true;
    }

    private boolean isPeak() {
        int mid = (this.winPos + 50) % 100;
        for (int i = 0; i < 100; i++) {
            if (i != mid) {
                float[] fArr = this.lastValues;
                if (fArr[i] > fArr[mid]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValley() {
        int mid = (this.winPos + 50) % 100;
        for (int i = 0; i < 100; i++) {
            if (i != mid) {
                float[] fArr = this.lastValues;
                if (fArr[i] < fArr[mid]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "Accelerometer accuracy changed.");
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != 1) {
            return;
        }
        float[] values = event.values;
        float magnitude = 0.0f;
        for (float v : values) {
            magnitude += v * v;
        }
        int mid = (this.winPos + 50) % 100;
        if (this.startPeaking && isPeak() && this.foundValley && this.lastValues[mid] - this.lastValley > PEAK_VALLEY_RANGE) {
            long timestamp = System.currentTimeMillis();
            long[] jArr = this.stepInterval;
            int i = this.intervalPos;
            jArr[i] = timestamp - this.stepTimestamp;
            this.intervalPos = (i + 1) % 2;
            this.stepTimestamp = timestamp;
            if (areStepsEquallySpaced()) {
                if (this.foundNonStep) {
                    this.numStepsWithFilter += 2;
                    this.totalDistance += this.strideLength * 2.0f;
                    this.foundNonStep = false;
                }
                int i2 = this.numStepsWithFilter + 1;
                this.numStepsWithFilter = i2;
                WalkStep(i2, this.totalDistance);
                this.totalDistance += this.strideLength;
            } else {
                this.foundNonStep = true;
            }
            int i3 = this.numStepsRaw + 1;
            this.numStepsRaw = i3;
            SimpleStep(i3, this.totalDistance);
            this.foundValley = false;
        }
        if (this.startPeaking && isValley()) {
            this.foundValley = true;
            this.lastValley = this.lastValues[mid];
        }
        float[] fArr = this.avgWindow;
        int i4 = this.avgPos;
        fArr[i4] = magnitude;
        this.avgPos = (i4 + 1) % fArr.length;
        this.lastValues[this.winPos] = 0.0f;
        for (float m : fArr) {
            float[] fArr2 = this.lastValues;
            int i5 = this.winPos;
            fArr2[i5] = fArr2[i5] + m;
        }
        float[] fArr3 = this.lastValues;
        int i6 = this.winPos;
        float length = fArr3[i6] / this.avgWindow.length;
        fArr3[i6] = length;
        boolean z = this.startPeaking;
        if (z || i6 > 1) {
            int i7 = this.winPos - 1;
            if (i7 < 0) {
                i7 += 100;
            }
            float f = length + (fArr3[i7] * 2.0f);
            fArr3[i6] = f;
            int i8 = i7 - 1;
            if (i8 < 0) {
                i8 += 100;
            }
            float f2 = f + fArr3[i8];
            fArr3[i6] = f2;
            fArr3[i6] = f2 / 4.0f;
        } else if (!z && i6 == 1) {
            fArr3[1] = (fArr3[1] + fArr3[0]) / 2.0f;
        }
        long elapsedTimestamp = System.currentTimeMillis();
        if (elapsedTimestamp - this.stepTimestamp > this.stopDetectionTimeout) {
            this.stepTimestamp = elapsedTimestamp;
        }
        int i9 = this.winPos;
        if (i9 == 99 && !this.startPeaking) {
            this.startPeaking = true;
        }
        this.winPos = (i9 + 1) % 100;
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        this.sensorManager.unregisterListener(this);
    }

    @SimpleEvent(description = "This event has been deprecated.")
    @Deprecated
    public void StartedMoving() {
    }

    @SimpleEvent(description = "This event has been deprecated.")
    @Deprecated
    public void StoppedMoving() {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property has been deprecated.")
    @Deprecated
    public void UseGPS(boolean gps) {
    }

    @SimpleEvent(description = "This event has been deprecated.")
    @Deprecated
    public void CalibrationFailed() {
    }

    @SimpleEvent(description = "This event has been deprecated.")
    @Deprecated
    public void GPSAvailable() {
    }

    @SimpleEvent(description = "This event has been deprecated.")
    @Deprecated
    public void GPSLost() {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property has been deprecated.")
    @Deprecated
    public void CalibrateStrideLength(boolean cal) {
    }

    @SimpleProperty(description = "This property has been deprecated.")
    @Deprecated
    public boolean CalibrateStrideLength() {
        return false;
    }

    @SimpleProperty(description = "This property has been deprecated.")
    @Deprecated
    public boolean Moving() {
        return false;
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
            case -871160130:
                if (key.equals("WalkSteps")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 237934709:
                if (key.equals("SimpleSteps")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 353103893:
                if (key.equals("Distance")) {
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
                return Float.valueOf(this.numStepsRaw);
            case 1:
                return Float.valueOf(this.numStepsWithFilter);
            case 2:
                return Float.valueOf(this.totalDistance);
            default:
                return Float.valueOf(0.0f);
        }
    }
}
