package com.google.appinventor.components.runtime;

import android.util.Log;
import androidx.constraintlayout.solver.LinearSystem;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.NxtMotorMode;
import com.google.appinventor.components.common.NxtMotorPort;
import com.google.appinventor.components.common.NxtRegulationMode;
import com.google.appinventor.components.common.NxtRunState;
import com.google.appinventor.components.common.NxtSensorMode;
import com.google.appinventor.components.common.NxtSensorPort;
import com.google.appinventor.components.common.NxtSensorType;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import kawa.Telnet;

@SimpleObject
/* loaded from: classes.dex */
public class LegoMindstormsNxtBase extends AndroidNonvisibleComponent implements BluetoothConnectionListener, Component, Deleteable {
    private static final java.util.Map<Integer, String> ERROR_MESSAGES;
    private static final int TOY_ROBOT = 2052;
    protected BluetoothClient bluetooth;
    protected final String logTag;

    static {
        HashMap hashMap = new HashMap();
        ERROR_MESSAGES = hashMap;
        hashMap.put(32, "Pending communication transaction in progress");
        hashMap.put(64, "Specified mailbox queue is empty");
        hashMap.put(129, "No more handles");
        hashMap.put(130, "No space");
        hashMap.put(131, "No more files");
        hashMap.put(132, "End of file expected");
        hashMap.put(133, "End of file");
        hashMap.put(134, "Not a linear file");
        hashMap.put(135, "File not found");
        hashMap.put(136, "Handle already closed");
        hashMap.put(137, "No linear space");
        hashMap.put(138, "Undefined error");
        hashMap.put(139, "File is busy");
        hashMap.put(140, "No write buffers");
        hashMap.put(141, "Append not possible");
        hashMap.put(142, "File is full");
        hashMap.put(143, "File exists");
        hashMap.put(144, "Module not found");
        hashMap.put(145, "Out of boundary");
        hashMap.put(146, "Illegal file name");
        hashMap.put(147, "Illegal handle");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG), "Request failed (i.e. specified file not found)");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK), "Unknown command opcode");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY), "Insane packet");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE), "Data contains out-of-range values");
        hashMap.put(221, "Communication bus error");
        hashMap.put(222, "No free memory in communication buffer");
        hashMap.put(223, "Specified channel/connection is not valid");
        hashMap.put(224, "Specified channel/connection not configured or busy");
        hashMap.put(236, "No active program");
        hashMap.put(237, "Illegal size specified");
        hashMap.put(238, "Illegal mailbox queue ID specified");
        hashMap.put(239, "Attempted to access invalid field of a structure");
        hashMap.put(240, "Bad input or output specified");
        hashMap.put(Integer.valueOf(Telnet.WILL), "Insufficient memory available");
        hashMap.put(255, "Bad arguments");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LegoMindstormsNxtBase(ComponentContainer container, String logTag) {
        super(container.$form());
        this.logTag = logTag;
    }

    protected LegoMindstormsNxtBase() {
        super(null);
        this.logTag = null;
    }

    public final void Initialize() {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.", userVisible = LinearSystem.FULL_DEBUG)
    public BluetoothClient BluetoothClient() {
        return this.bluetooth;
    }

    @SimpleProperty(userVisible = LinearSystem.FULL_DEBUG)
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_BLUETOOTHCLIENT)
    public void BluetoothClient(BluetoothClient bluetoothClient) {
        BluetoothClient bluetoothClient2 = this.bluetooth;
        if (bluetoothClient2 != null) {
            bluetoothClient2.removeBluetoothConnectionListener(this);
            this.bluetooth.detachComponent(this);
            this.bluetooth = null;
        }
        if (bluetoothClient != null) {
            this.bluetooth = bluetoothClient;
            bluetoothClient.attachComponent(this, Collections.singleton(Integer.valueOf(TOY_ROBOT)));
            this.bluetooth.addBluetoothConnectionListener(this);
            if (this.bluetooth.IsConnected()) {
                afterConnect(this.bluetooth);
            }
        }
    }

    protected final void setOutputState(String functionName, int port, int power, int mode, int regulationMode, int turnRatio, int runState, long tachoLimit) {
        int power2 = sanitizePower(power);
        int turnRatio2 = sanitizeTurnRatio(turnRatio);
        byte[] command = new byte[12];
        command[0] = Byte.MIN_VALUE;
        command[1] = 4;
        copyUBYTEValueToBytes(port, command, 2);
        copySBYTEValueToBytes(power2, command, 3);
        copyUBYTEValueToBytes(mode, command, 4);
        copyUBYTEValueToBytes(regulationMode, command, 5);
        copySBYTEValueToBytes(turnRatio2, command, 6);
        copyUBYTEValueToBytes(runState, command, 7);
        copyULONGValueToBytes(tachoLimit, command, 8);
        sendCommand(functionName, command);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setOutputState(String functionName, NxtMotorPort port, int power, NxtMotorMode mode, NxtRegulationMode regulationMode, int turnRatio, NxtRunState runState, long tachoLimit) {
        setOutputState(functionName, port.toInt().intValue(), power, mode.toUnderlyingValue().intValue(), regulationMode.toUnderlyingValue().intValue(), turnRatio, runState.toUnderlyingValue().intValue(), tachoLimit);
    }

    protected final void setInputMode(String functionName, int port, int sensorType, int sensorMode) {
        byte[] command = {Byte.MIN_VALUE, 5};
        copyUBYTEValueToBytes(port, command, 2);
        copyUBYTEValueToBytes(sensorType, command, 3);
        copyUBYTEValueToBytes(sensorMode, command, 4);
        sendCommand(functionName, command);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setInputMode(String functionName, NxtSensorPort port, NxtSensorType type, NxtSensorMode mode) {
        setInputMode(functionName, port.toInt().intValue(), type.toUnderlyingValue().intValue(), mode.toUnderlyingValue().intValue());
    }

    protected final byte[] getInputValues(String functionName, int port) {
        byte[] command = {0, 7};
        copyUBYTEValueToBytes(port, command, 2);
        byte[] returnPackage = sendCommandAndReceiveReturnPackage(functionName, command);
        if (evaluateStatus(functionName, returnPackage, command[1])) {
            if (returnPackage.length == 16) {
                return returnPackage;
            }
            Log.w(this.logTag, functionName + ": unexpected return package length " + returnPackage.length + " (expected 16)");
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final byte[] getInputValues(String functionName, NxtSensorPort port) {
        return getInputValues(functionName, port.toInt().intValue());
    }

    protected final void resetInputScaledValue(String functionName, int port) {
        byte[] command = {Byte.MIN_VALUE, 8};
        copyUBYTEValueToBytes(port, command, 2);
        sendCommand(functionName, command);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void resetInputScaledValue(String functionName, NxtSensorPort port) {
        resetInputScaledValue(functionName, port.toInt().intValue());
    }

    protected final int lsGetStatus(String functionName, int port) {
        byte[] command = {0, 14};
        copyUBYTEValueToBytes(port, command, 2);
        byte[] returnPackage = sendCommandAndReceiveReturnPackage(functionName, command);
        if (evaluateStatus(functionName, returnPackage, command[1])) {
            if (returnPackage.length == 4) {
                return getUBYTEValueFromBytes(returnPackage, 3);
            }
            Log.w(this.logTag, functionName + ": unexpected return package length " + returnPackage.length + " (expected 4)");
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int lsGetStatus(String functionName, NxtSensorPort port) {
        return lsGetStatus(functionName, port.toInt().intValue());
    }

    protected final void lsWrite(String functionName, int port, byte[] data, int rxDataLength) {
        if (data.length > 16) {
            throw new IllegalArgumentException("length must be <= 16");
        }
        byte[] command = new byte[data.length + 5];
        command[0] = 0;
        command[1] = 15;
        copyUBYTEValueToBytes(port, command, 2);
        copyUBYTEValueToBytes(data.length, command, 3);
        copyUBYTEValueToBytes(rxDataLength, command, 4);
        System.arraycopy(data, 0, command, 5, data.length);
        byte[] returnPackage = sendCommandAndReceiveReturnPackage(functionName, command);
        evaluateStatus(functionName, returnPackage, command[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void lsWrite(String functionName, NxtSensorPort port, byte[] data, int rxDataLength) {
        lsWrite(functionName, port.toInt().intValue(), data, rxDataLength);
    }

    protected final byte[] lsRead(String functionName, int port) {
        byte[] command = {0, 16};
        copyUBYTEValueToBytes(port, command, 2);
        byte[] returnPackage = sendCommandAndReceiveReturnPackage(functionName, command);
        if (evaluateStatus(functionName, returnPackage, command[1])) {
            if (returnPackage.length == 20) {
                return returnPackage;
            }
            Log.w(this.logTag, functionName + ": unexpected return package length " + returnPackage.length + " (expected 20)");
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final byte[] lsRead(String functionName, NxtSensorPort port) {
        return lsRead(functionName, port.toInt().intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean checkBluetooth(String functionName) {
        BluetoothClient bluetoothClient = this.bluetooth;
        if (bluetoothClient == null) {
            this.form.dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_NXT_BLUETOOTH_NOT_SET, new Object[0]);
            return false;
        }
        if (!bluetoothClient.IsConnected()) {
            this.form.dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_NXT_NOT_CONNECTED_TO_ROBOT, new Object[0]);
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final byte[] sendCommandAndReceiveReturnPackage(String functionName, byte[] command) {
        sendCommand(functionName, command);
        return receiveReturnPackage(functionName);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sendCommand(String functionName, byte[] command) {
        byte[] header = new byte[2];
        copyUWORDValueToBytes(command.length, header, 0);
        this.bluetooth.write(functionName, header);
        this.bluetooth.write(functionName, command);
    }

    private byte[] receiveReturnPackage(String functionName) {
        byte[] header = this.bluetooth.read(functionName, 2);
        if (header.length == 2) {
            int length = getUWORDValueFromBytes(header, 0);
            byte[] returnPackage = this.bluetooth.read(functionName, length);
            if (returnPackage.length >= 3) {
                return returnPackage;
            }
        }
        this.form.dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_NXT_INVALID_RETURN_PACKAGE, new Object[0]);
        return new byte[0];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean evaluateStatus(String functionName, byte[] returnPackage, byte command) {
        int status = getStatus(functionName, returnPackage, command);
        if (status == 0) {
            return true;
        }
        handleError(functionName, status);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getStatus(String functionName, byte[] returnPackage, byte command) {
        if (returnPackage.length >= 3) {
            if (returnPackage[0] != 2) {
                Log.w(this.logTag, functionName + ": unexpected return package byte 0: 0x" + Integer.toHexString(returnPackage[0] & Ev3Constants.Opcode.TST) + " (expected 0x02)");
            }
            if (returnPackage[1] != command) {
                Log.w(this.logTag, functionName + ": unexpected return package byte 1: 0x" + Integer.toHexString(returnPackage[1] & Ev3Constants.Opcode.TST) + " (expected 0x" + Integer.toHexString(command & Ev3Constants.Opcode.TST) + ")");
            }
            return getUBYTEValueFromBytes(returnPackage, 2);
        }
        Log.w(this.logTag, functionName + ": unexpected return package length " + returnPackage.length + " (expected >= 3)");
        return -1;
    }

    private void handleError(String functionName, int status) {
        if (status >= 0) {
            String errorMessage = ERROR_MESSAGES.get(Integer.valueOf(status));
            if (errorMessage != null) {
                this.form.dispatchErrorOccurredEvent(this, functionName, 404, errorMessage);
            } else {
                this.form.dispatchErrorOccurredEvent(this, functionName, 404, "Error code 0x" + Integer.toHexString(status & 255));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void copyBooleanValueToBytes(boolean z, byte[] bArr, int i) {
        bArr[i] = z ? (byte) 1 : (byte) 0;
    }

    protected final void copySBYTEValueToBytes(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void copyUBYTEValueToBytes(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) value;
    }

    protected final void copySWORDValueToBytes(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) (value & 255);
        bytes[offset + 1] = (byte) ((value >> 8) & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void copyUWORDValueToBytes(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) (value & 255);
        bytes[offset + 1] = (byte) ((value >> 8) & 255);
    }

    protected final void copySLONGValueToBytes(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) (value & 255);
        int value2 = value >> 8;
        bytes[offset + 1] = (byte) (value2 & 255);
        int value3 = value2 >> 8;
        bytes[offset + 2] = (byte) (value3 & 255);
        bytes[offset + 3] = (byte) ((value3 >> 8) & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void copyULONGValueToBytes(long value, byte[] bytes, int offset) {
        bytes[offset] = (byte) (value & 255);
        bytes[offset + 1] = (byte) (r7 & 255);
        long value2 = (value >> 8) >> 8;
        bytes[offset + 2] = (byte) (value2 & 255);
        bytes[offset + 3] = (byte) (255 & (value2 >> 8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void copyStringValueToBytes(String value, byte[] bytes, int offset, int maxCount) {
        byte[] valueBytes;
        if (value.length() > maxCount) {
            value = value.substring(0, maxCount);
        }
        try {
            valueBytes = value.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.w(this.logTag, "UnsupportedEncodingException: " + e.getMessage());
            valueBytes = value.getBytes();
        }
        int lengthToCopy = Math.min(maxCount, valueBytes.length);
        System.arraycopy(valueBytes, 0, bytes, offset, lengthToCopy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getBooleanValueFromBytes(byte[] bytes, int offset) {
        return bytes[offset] != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getSBYTEValueFromBytes(byte[] bytes, int offset) {
        return bytes[offset];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getUBYTEValueFromBytes(byte[] bytes, int offset) {
        return bytes[offset] & Ev3Constants.Opcode.TST;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getSWORDValueFromBytes(byte[] bytes, int offset) {
        return (bytes[offset] & Ev3Constants.Opcode.TST) | (bytes[offset + 1] << 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getUWORDValueFromBytes(byte[] bytes, int offset) {
        return (bytes[offset] & Ev3Constants.Opcode.TST) | ((bytes[offset + 1] & Ev3Constants.Opcode.TST) << 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getSLONGValueFromBytes(byte[] bytes, int offset) {
        return (bytes[offset] & Ev3Constants.Opcode.TST) | ((bytes[offset + 1] & Ev3Constants.Opcode.TST) << 8) | ((bytes[offset + 2] & Ev3Constants.Opcode.TST) << 16) | (bytes[offset + 3] << 24);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long getULONGValueFromBytes(byte[] bytes, int offset) {
        return (bytes[offset] & 255) | ((bytes[offset + 1] & 255) << 8) | ((bytes[offset + 2] & 255) << 16) | ((255 & bytes[offset + 3]) << 24);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bytes, int offset) {
        int length = 0;
        int i = offset;
        while (true) {
            if (i >= bytes.length) {
                break;
            }
            if (bytes[i] != 0) {
                i++;
            } else {
                length = i - offset;
                break;
            }
        }
        return getStringValueFromBytes(bytes, offset, length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bytes, int offset, int count) {
        try {
            return new String(bytes, offset, count, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            Log.w(this.logTag, "UnsupportedEncodingException: " + e.getMessage());
            return new String(bytes, offset, count);
        }
    }

    protected final int convertMotorPortLetterToNumber(String motorPortLetter) {
        if (motorPortLetter.length() == 1) {
            return convertMotorPortLetterToNumber(motorPortLetter.charAt(0));
        }
        throw new IllegalArgumentException("Illegal motor port letter " + motorPortLetter);
    }

    protected final int convertMotorPortLetterToNumber(char motorPortLetter) {
        if (motorPortLetter == 'A' || motorPortLetter == 'a') {
            return 0;
        }
        if (motorPortLetter == 'B' || motorPortLetter == 'b') {
            return 1;
        }
        if (motorPortLetter == 'C' || motorPortLetter == 'c') {
            return 2;
        }
        throw new IllegalArgumentException("Illegal motor port letter " + motorPortLetter);
    }

    protected final int convertSensorPortLetterToNumber(String sensorPortLetter) {
        if (sensorPortLetter.length() == 1) {
            return convertSensorPortLetterToNumber(sensorPortLetter.charAt(0));
        }
        throw new IllegalArgumentException("Illegal sensor port letter " + sensorPortLetter);
    }

    protected final int convertSensorPortLetterToNumber(char sensorPortLetter) {
        if (sensorPortLetter == '1') {
            return 0;
        }
        if (sensorPortLetter == '2') {
            return 1;
        }
        if (sensorPortLetter == '3') {
            return 2;
        }
        if (sensorPortLetter == '4') {
            return 3;
        }
        throw new IllegalArgumentException("Illegal sensor port letter " + sensorPortLetter);
    }

    protected final int sanitizePower(int power) {
        if (power < -100) {
            Log.w(this.logTag, "power " + power + " is invalid, using -100.");
            power = -100;
        }
        if (power > 100) {
            Log.w(this.logTag, "power " + power + " is invalid, using 100.");
            return 100;
        }
        return power;
    }

    protected final int sanitizeTurnRatio(int turnRatio) {
        if (turnRatio < -100) {
            Log.w(this.logTag, "turnRatio " + turnRatio + " is invalid, using -100.");
            turnRatio = -100;
        }
        if (turnRatio > 100) {
            Log.w(this.logTag, "turnRatio " + turnRatio + " is invalid, using 100.");
            return 100;
        }
        return turnRatio;
    }

    @Override // com.google.appinventor.components.runtime.BluetoothConnectionListener
    public void afterConnect(BluetoothConnectionBase bluetoothConnection) {
    }

    @Override // com.google.appinventor.components.runtime.BluetoothConnectionListener
    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnection) {
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        BluetoothClient bluetoothClient = this.bluetooth;
        if (bluetoothClient != null) {
            bluetoothClient.removeBluetoothConnectionListener(this);
            this.bluetooth.detachComponent(this);
            this.bluetooth = null;
        }
    }
}
