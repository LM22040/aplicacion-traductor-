package com.google.appinventor.components.runtime;

import android.os.Handler;
import androidx.constraintlayout.solver.LinearSystem;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import kawa.lang.SyntaxForms;

@DesignerComponent(category = ComponentCategory.LEGOMINDSTORMS, description = "A component that provides both high- and low-level interfaces to a LEGO MINDSTORMS EV3 robot, with functions that can control the motors.", iconName = "images/legoMindstormsEv3.png", nonVisible = SyntaxForms.DEBUGGING, version = 1)
@SimpleObject
/* loaded from: classes.dex */
public class Ev3Motors extends LegoMindstormsEv3Base {
    private static final String DEFAULT_MOTOR_PORTS = "ABC";
    private static final double DEFAULT_WHEEL_DIAMETER = 4.32d;
    private static final int DELAY_MILLISECONDS = 50;
    private boolean directionReversed;
    private Handler eventHandler;
    private boolean ifReset;
    private int motorPortBitField;
    private int previousValue;
    private boolean regulationEnabled;
    private final Runnable sensorValueChecker;
    private boolean stopBeforeDisconnect;
    private boolean tachoCountChangedEventEnabled;
    private double wheelDiameter;

    public Ev3Motors(ComponentContainer container) {
        super(container, "Ev3Motors");
        this.motorPortBitField = 1;
        this.wheelDiameter = DEFAULT_WHEEL_DIAMETER;
        this.directionReversed = false;
        this.regulationEnabled = true;
        this.stopBeforeDisconnect = true;
        this.tachoCountChangedEventEnabled = false;
        this.previousValue = 0;
        this.ifReset = false;
        this.eventHandler = new Handler();
        Runnable runnable = new Runnable() { // from class: com.google.appinventor.components.runtime.Ev3Motors.1
            @Override // java.lang.Runnable
            public void run() {
                if (Ev3Motors.this.bluetooth != null && Ev3Motors.this.bluetooth.IsConnected()) {
                    Ev3Motors ev3Motors = Ev3Motors.this;
                    int sensorValue = ev3Motors.getOutputCount("", 0, ev3Motors.motorPortBitField);
                    if (!Ev3Motors.this.ifReset) {
                        if (sensorValue != Ev3Motors.this.previousValue && Ev3Motors.this.tachoCountChangedEventEnabled) {
                            Ev3Motors.this.TachoCountChanged(sensorValue);
                        }
                    } else {
                        Ev3Motors.this.ifReset = false;
                    }
                    Ev3Motors.this.previousValue = sensorValue;
                }
                Ev3Motors.this.eventHandler.postDelayed(this, 50L);
            }
        };
        this.sensorValueChecker = runnable;
        this.eventHandler.post(runnable);
        MotorPorts(DEFAULT_MOTOR_PORTS);
        StopBeforeDisconnect(true);
        EnableSpeedRegulation(true);
        ReverseDirection(false);
        WheelDiameter(DEFAULT_WHEEL_DIAMETER);
        TachoCountChangedEventEnabled(false);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The motor ports that the motors are connected to. The ports are specified by a sequence of port letters.", userVisible = LinearSystem.FULL_DEBUG)
    public String MotorPorts() {
        return bitFieldToMotorPortLetters(this.motorPortBitField);
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = DEFAULT_MOTOR_PORTS, editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void MotorPorts(String motorPortLetters) {
        try {
            this.motorPortBitField = motorPortLettersToBitField(motorPortLetters);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "MotorPorts", ErrorMessages.ERROR_EV3_ILLEGAL_MOTOR_PORT, motorPortLetters);
        }
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "4.32", editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT)
    public void WheelDiameter(double diameter) {
        this.wheelDiameter = diameter;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The diameter of the wheels attached on the motors in centimeters.", userVisible = LinearSystem.FULL_DEBUG)
    public double WheelDiameter() {
        return this.wheelDiameter;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void ReverseDirection(boolean reversed) {
        try {
            setOutputDirection("ReverseDirection", 0, this.motorPortBitField, reversed ? -1 : 1);
            this.directionReversed = reversed;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "ReverseDirection", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ReverseDirection");
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "It specifies if the direction of the motors is reversed.")
    public boolean ReverseDirection() {
        return this.directionReversed;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void EnableSpeedRegulation(boolean enabled) {
        this.regulationEnabled = enabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The robot adjusts the power to maintain the speed if speed regulation is enabled.")
    public boolean EnableSpeedRegulation() {
        return this.regulationEnabled;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to stop the motor before disconnecting.")
    public boolean StopBeforeDisconnect() {
        return this.stopBeforeDisconnect;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void StopBeforeDisconnect(boolean stopBeforeDisconnect) {
        this.stopBeforeDisconnect = stopBeforeDisconnect;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the TachoCountChanged event should fire when the angle is changed.")
    public boolean TachoCountChangedEventEnabled() {
        return this.tachoCountChangedEventEnabled;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void TachoCountChangedEventEnabled(boolean enabled) {
        this.tachoCountChangedEventEnabled = enabled;
    }

    @SimpleFunction(description = "Start to rotate the motors.")
    public void RotateIndefinitely(int power) {
        try {
            if (this.regulationEnabled) {
                setOutputPower("RotateIndefinitely", 0, this.motorPortBitField, power);
            } else {
                setOutputSpeed("RotateIndefinitely", 0, this.motorPortBitField, power);
            }
            startOutput("RotateIndefinitely", 0, this.motorPortBitField);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateIndefinitely", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateIndefinitely");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a number of tacho counts.")
    public void RotateInTachoCounts(int power, int tachoCounts, boolean useBrake) {
        try {
            if (this.regulationEnabled) {
                outputStepSpeed("RotateInTachoCounts", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
            } else {
                outputStepPower("RotateInTachoCounts", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInTachoCounts", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInTachoCounts");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a period of time.")
    public void RotateInDuration(int power, int milliseconds, boolean useBrake) {
        try {
            if (this.regulationEnabled) {
                outputTimeSpeed("RotateInDuration", 0, this.motorPortBitField, power, 0, milliseconds, 0, useBrake);
            } else {
                outputTimePower("RotateInDuration", 0, this.motorPortBitField, power, 0, milliseconds, 0, useBrake);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a distance.")
    public void RotateInDistance(int power, double distance, boolean useBrake) {
        int tachoCounts = (int) (((360.0d * distance) / this.wheelDiameter) / 3.141592653589793d);
        try {
            if (this.regulationEnabled) {
                outputStepSpeed("RotateInDistance", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
            } else {
                outputStepPower("RotateInDistance", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInDistance", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInDistance");
        }
    }

    @SimpleFunction(description = "Start to rotate the motors at the same speed.")
    public void RotateSyncIndefinitely(int power, int turnRatio) {
        try {
            int i = this.motorPortBitField;
            if (i != 0) {
                if (isOneShotInteger(i)) {
                    setOutputSpeed("RotateSyncIndefinitely", 0, this.motorPortBitField, power);
                } else {
                    outputStepSync("RotateSyncIndefinitely", 0, this.motorPortBitField, power, turnRatio, 0, true);
                }
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncIndefinitely", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncIndefinitely");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed for a distance in cm.")
    public void RotateSyncInDistance(int power, int distance, int turnRatio, boolean useBrake) {
        double d = distance;
        Double.isNaN(d);
        int tachoCounts = (int) (((d * 360.0d) / this.wheelDiameter) / 3.141592653589793d);
        try {
            int i = this.motorPortBitField;
            if (i != 0) {
                if (isOneShotInteger(i)) {
                    outputStepSpeed("RotateSyncInDuration", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
                } else {
                    outputStepSync("RotateSyncInDuration", 0, this.motorPortBitField, power, turnRatio, tachoCounts, useBrake);
                }
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a period of time.")
    public void RotateSyncInDuration(int power, int milliseconds, int turnRatio, boolean useBrake) {
        try {
            int i = this.motorPortBitField;
            if (i != 0) {
                if (isOneShotInteger(i)) {
                    outputTimeSpeed("RotateSyncInDuration", 0, this.motorPortBitField, power, 0, milliseconds, 0, useBrake);
                } else {
                    outputTimeSync("RotateSyncInDuration", 0, this.motorPortBitField, power, turnRatio, milliseconds, useBrake);
                }
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a number of tacho counts.")
    public void RotateSyncInTachoCounts(int power, int tachoCounts, int turnRatio, boolean useBrake) {
        try {
            int i = this.motorPortBitField;
            if (i != 0) {
                if (isOneShotInteger(i)) {
                    outputStepSpeed("RotateSyncInTachoCounts", 0, this.motorPortBitField, power, 0, tachoCounts, 0, useBrake);
                } else {
                    outputStepSync("RotateSyncInTachoCounts", 0, this.motorPortBitField, power, turnRatio, tachoCounts, useBrake);
                }
            }
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInTachoCounts", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInTachoCounts");
        }
    }

    @SimpleFunction(description = "Stop the motors of the robot.")
    public void Stop(boolean useBrake) {
        try {
            stopOutput("Stop", 0, this.motorPortBitField, useBrake);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "Stop", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "Stop");
        }
    }

    @SimpleFunction(description = "Toggle the direction of motors.")
    public void ToggleDirection() {
        try {
            setOutputDirection("ToggleDirection", 0, this.motorPortBitField, 0);
            this.directionReversed = !this.directionReversed;
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "ToggleDirection", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ToggleDirection");
        }
    }

    @SimpleFunction(description = "Set the current tacho count to zero.")
    public void ResetTachoCount() {
        try {
            clearOutputCount("ResetTachoCount", 0, this.motorPortBitField);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "ResetTachoCount", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ResetTachoCount");
        }
    }

    @SimpleFunction(description = "Get the current tacho count.")
    public int GetTachoCount() {
        try {
            return getOutputCount("GetTachoCount", 0, this.motorPortBitField);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "GetTachoCount", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "GetTachoCount");
            return 0;
        }
    }

    @SimpleEvent(description = "Called when the tacho count has changed.")
    public void TachoCountChanged(int tachoCount) {
        EventDispatcher.dispatchEvent(this, "TachoCountChanged", Integer.valueOf(tachoCount));
    }

    private int roundValue(int value, int minimum, int maximum) {
        return value < minimum ? minimum : value > maximum ? maximum : value;
    }

    private boolean isOneShotInteger(int value) {
        return value != 0 && ((((value + (-1)) ^ value) ^ (-1)) & value) == 0;
    }

    private void resetOutput(String functionName, int layer, int nos) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        this.ifReset = true;
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_RESET, false, 0, 0, "cc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos));
        sendCommand(functionName, command, false);
    }

    private void startOutput(String functionName, int layer, int nos) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_START, false, 0, 0, "cc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos));
        sendCommand(functionName, command, false);
    }

    private void stopOutput(String str, int i, int i2, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STOP, false, 0, 0, "ccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputStepPower(String str, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i4 < 0 || i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_POWER, false, 0, 0, "ccccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputStepSpeed(String str, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i4 < 0 || i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SPEED, false, 0, 0, "ccccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputStepSync(String str, int i, int i2, int i3, int i4, int i5, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i4 < -200 || i4 > 200) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SYNC, false, 0, 0, "cccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Short.valueOf((short) i4), Integer.valueOf(i5), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputTimePower(String str, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i4 < 0 || i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_POWER, false, 0, 0, "ccccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputTimeSpeed(String str, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i4 < 0 || i5 < 0 || i6 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SPEED, false, 0, 0, "ccccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void outputTimeSync(String str, int i, int i2, int i3, int i4, int i5, boolean z) {
        if (i < 0 || i > 3 || i2 < 0 || i2 > 15 || i5 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SYNC, false, 0, 0, "cccccc", Byte.valueOf((byte) i), Byte.valueOf((byte) i2), Byte.valueOf((byte) roundValue(i3, -100, 100)), Short.valueOf((short) i4), Integer.valueOf(i5), Byte.valueOf(z ? (byte) 1 : (byte) 0)), false);
    }

    private void setOutputDirection(String functionName, int layer, int nos, int direction) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15 || direction < -1 || direction > 1) {
            throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POLARITY, false, 0, 0, "ccc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos), Byte.valueOf((byte) direction));
        sendCommand(functionName, command, false);
    }

    private void setOutputSpeed(String functionName, int layer, int nos, int speed) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_SPEED, false, 0, 0, "ccc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos), Byte.valueOf((byte) roundValue(speed, -100, 100)));
        sendCommand(functionName, command, false);
    }

    private void setOutputPower(String functionName, int layer, int nos, int power) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POWER, false, 0, 0, "ccc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos), Byte.valueOf((byte) roundValue(power, -100, 100)));
        sendCommand(functionName, command, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getOutputCount(String functionName, int layer, int nos) {
        int portNumber;
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        switch ((((nos - 1) ^ nos) + 1) >>> 1) {
            case 1:
                portNumber = 0;
                break;
            case 2:
                portNumber = 1;
                break;
            case 4:
                portNumber = 2;
                break;
            case 8:
                portNumber = 3;
                break;
            default:
                throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_GET_COUNT, true, 4, 0, "ccg", Byte.valueOf((byte) layer), Byte.valueOf((byte) portNumber), (byte) 0);
        byte[] reply = sendCommand(functionName, command, true);
        if (reply == null || reply.length != 5 || reply[0] != 2) {
            return 0;
        }
        Object[] values = Ev3BinaryParser.unpack("xi", reply);
        return ((Integer) values[0]).intValue();
    }

    private void clearOutputCount(String functionName, int layer, int nos) {
        if (layer < 0 || layer > 3 || nos < 0 || nos > 15) {
            throw new IllegalArgumentException();
        }
        byte[] command = Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_CLR_COUNT, false, 0, 0, "cc", Byte.valueOf((byte) layer), Byte.valueOf((byte) nos));
        sendCommand(functionName, command, false);
    }

    @Override // com.google.appinventor.components.runtime.LegoMindstormsEv3Base, com.google.appinventor.components.runtime.BluetoothConnectionListener
    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnection) {
        if (this.stopBeforeDisconnect) {
            try {
                stopOutput("beforeDisconnect", 0, this.motorPortBitField, true);
            } catch (IllegalArgumentException e) {
                this.form.dispatchErrorOccurredEvent(this, "beforeDisconnect", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "beforeDisconnect");
            }
        }
    }
}
