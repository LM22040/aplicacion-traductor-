package com.google.appinventor.components.runtime;

import android.hardware.SensorEvent;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
/* loaded from: classes.dex */
public abstract class BufferedSingleValueSensor extends SingleValueSensor {
    private AveragingBuffer buffer;

    public BufferedSingleValueSensor(ComponentContainer container, int sensorType, int bufferSize) {
        super(container.$form(), sensorType);
        this.buffer = new AveragingBuffer(bufferSize);
    }

    @Override // com.google.appinventor.components.runtime.SingleValueSensor, android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled && sensorEvent.sensor.getType() == this.sensorType) {
            float[] values = sensorEvent.values;
            this.buffer.insert(Float.valueOf(values[0]));
            super.onSensorChanged(sensorEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getAverageValue() {
        return this.buffer.getAverage();
    }

    /* loaded from: classes.dex */
    private class AveragingBuffer {
        private Float[] data;
        private int next;

        private AveragingBuffer(int size) {
            this.data = new Float[size];
            this.next = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void insert(Float datum) {
            Float[] fArr = this.data;
            int i = this.next;
            int i2 = i + 1;
            this.next = i2;
            fArr[i] = datum;
            if (i2 == fArr.length) {
                this.next = 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float getAverage() {
            double d;
            double sum = 0.0d;
            int count = 0;
            int i = 0;
            while (true) {
                Float[] fArr = this.data;
                if (i >= fArr.length) {
                    break;
                }
                Float f = fArr[i];
                if (f != null) {
                    double floatValue = f.floatValue();
                    Double.isNaN(floatValue);
                    sum += floatValue;
                    count++;
                }
                i++;
            }
            if (count == 0) {
                d = sum;
            } else {
                double d2 = count;
                Double.isNaN(d2);
                d = sum / d2;
            }
            return (float) d;
        }
    }
}
