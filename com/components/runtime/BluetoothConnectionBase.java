package com.google.appinventor.components.runtime;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.solver.LinearSystem;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.SUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
/* loaded from: classes.dex */
public abstract class BluetoothConnectionBase extends AndroidNonvisibleComponent implements Component, OnDestroyListener, Deleteable {
    protected final BluetoothAdapter adapter;
    private final List<BluetoothConnectionListener> bluetoothConnectionListeners;
    private ByteOrder byteOrder;
    private byte delimiter;
    protected boolean disconnectOnError;
    private String encoding;
    private InputStream inputStream;
    protected final String logTag;
    private OutputStream outputStream;
    protected boolean secure;
    private BluetoothSocket socket;

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothConnectionBase(ComponentContainer container, String logTag) {
        this(container.$form(), logTag);
        this.form.registerForOnDestroy(this);
    }

    private BluetoothConnectionBase(Form form, String logTag) {
        super(form);
        this.bluetoothConnectionListeners = new ArrayList();
        this.logTag = logTag;
        this.disconnectOnError = false;
        this.adapter = SUtil.getAdapter(form);
        HighByteFirst(false);
        CharacterEncoding("UTF-8");
        DelimiterByte(0);
        Secure(true);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    protected BluetoothConnectionBase(OutputStream outputStream, InputStream inputStream) {
        this((Form) null, (String) null);
        this.socket = null;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBluetoothConnectionListener(BluetoothConnectionListener listener) {
        this.bluetoothConnectionListeners.add(listener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeBluetoothConnectionListener(BluetoothConnectionListener listener) {
        this.bluetoothConnectionListeners.remove(listener);
    }

    private void fireAfterConnectEvent() {
        for (BluetoothConnectionListener listener : this.bluetoothConnectionListeners) {
            listener.afterConnect(this);
        }
    }

    private void fireBeforeDisconnectEvent() {
        for (BluetoothConnectionListener listener : this.bluetoothConnectionListeners) {
            listener.beforeDisconnect(this);
        }
    }

    public final void Initialize() {
    }

    @SimpleEvent(description = "The BluetoothError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = LinearSystem.FULL_DEBUG)
    public void BluetoothError(String functionName, String message) {
    }

    protected void bluetoothError(String functionName, int errorNumber, Object... messageArgs) {
        this.form.dispatchErrorOccurredEvent(this, functionName, errorNumber, messageArgs);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is available on the device")
    public boolean Available() {
        return this.adapter != null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is enabled")
    public boolean Enabled() {
        BluetoothAdapter bluetoothAdapter = this.adapter;
        if (bluetoothAdapter == null) {
            return false;
        }
        return bluetoothAdapter.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setConnection(BluetoothSocket bluetoothSocket) throws IOException {
        this.socket = bluetoothSocket;
        this.inputStream = new BufferedInputStream(this.socket.getInputStream());
        this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
        fireAfterConnectEvent();
    }

    @SimpleFunction(description = "Disconnect from the connected Bluetooth device.")
    public final void Disconnect() {
        if (this.socket != null) {
            fireBeforeDisconnectEvent();
            try {
                this.socket.close();
                Log.i(this.logTag, "Disconnected from Bluetooth device.");
            } catch (IOException e) {
                Log.w(this.logTag, "Error while disconnecting: " + e.getMessage());
            }
            this.socket = null;
        }
        this.inputStream = null;
        this.outputStream = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "On devices with API level 14 (LEVEL_ICE_CREAM_SANDWICH) or higher, this property returned is accurate. But on old devices with API level lower than 14, it may not return the current state of connection(e.g., it might be disconnected but you may not know until you attempt to read/write the socket.")
    public boolean IsConnected() {
        if (Build.VERSION.SDK_INT < 14) {
            return this.socket != null;
        }
        BluetoothSocket bluetoothSocket = this.socket;
        return bluetoothSocket != null && bluetoothSocket.isConnected();
    }

    protected boolean DisconnectOnError() {
        return this.disconnectOnError;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to invoke SSP (Simple Secure Pairing), which is supported on devices with Bluetooth v2.1 or higher. When working with embedded Bluetooth devices, this property may need to be set to False. For Android 2.0-2.2, this property setting will be ignored.")
    public boolean Secure() {
        return this.secure;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "True", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void Secure(boolean secure) {
        this.secure = secure;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HighByteFirst() {
        return this.byteOrder == ByteOrder.BIG_ENDIAN;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "False", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void HighByteFirst(boolean highByteFirst) {
        this.byteOrder = highByteFirst ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "UTF-8", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void CharacterEncoding(String encoding) {
        try {
            "check".getBytes(encoding);
            this.encoding = encoding;
        } catch (UnsupportedEncodingException e) {
            bluetoothError("CharacterEncoding", ErrorMessages.ERROR_BLUETOOTH_UNSUPPORTED_ENCODING, encoding);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String CharacterEncoding() {
        return this.encoding;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.TYPEFACE_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void DelimiterByte(int number) {
        byte b = (byte) number;
        int n = number >> 8;
        if (n != 0 && n != -1) {
            bluetoothError("DelimiterByte", 511, Integer.valueOf(number));
        } else {
            this.delimiter = b;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int DelimiterByte() {
        return this.delimiter;
    }

    @SimpleFunction(description = "Send text to the connected Bluetooth device.")
    public void SendText(String text) {
        byte[] bytes;
        try {
            bytes = text.getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            Log.w(this.logTag, "UnsupportedEncodingException: " + e.getMessage());
            bytes = text.getBytes();
        }
        write("SendText", bytes);
    }

    @SimpleFunction(description = "Send a 1-byte number to the connected Bluetooth device.")
    public void Send1ByteNumber(String number) {
        try {
            int n = Integer.decode(number).intValue();
            byte b = (byte) n;
            int n2 = n >> 8;
            if (n2 != 0 && n2 != -1) {
                bluetoothError("Send1ByteNumber", 511, number);
            } else {
                write("Send1ByteNumber", b);
            }
        } catch (NumberFormatException e) {
            bluetoothError("Send1ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, number);
        }
    }

    @SimpleFunction(description = "Send a 2-byte number to the connected Bluetooth device.")
    public void Send2ByteNumber(String number) {
        int n;
        try {
            int n2 = Integer.decode(number).intValue();
            byte[] bytes = new byte[2];
            if (this.byteOrder != ByteOrder.BIG_ENDIAN) {
                bytes[0] = (byte) (n2 & 255);
                n = n2 >> 8;
                bytes[1] = (byte) (n & 255);
            } else {
                bytes[1] = (byte) (n2 & 255);
                n = n2 >> 8;
                bytes[0] = (byte) (n & 255);
            }
            int n3 = n >> 8;
            if (n3 != 0 && n3 != -1) {
                bluetoothError("Send2ByteNumber", 512, number, 2);
            } else {
                write("Send2ByteNumber", bytes);
            }
        } catch (NumberFormatException e) {
            bluetoothError("Send2ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, number);
        }
    }

    @SimpleFunction(description = "Send a 4-byte number to the connected Bluetooth device.")
    public void Send4ByteNumber(String number) {
        long n;
        try {
            long n2 = Long.decode(number).longValue();
            byte[] bytes = new byte[4];
            if (this.byteOrder != ByteOrder.BIG_ENDIAN) {
                bytes[0] = (byte) (n2 & 255);
                bytes[1] = (byte) (r3 & 255);
                bytes[2] = (byte) (r3 & 255);
                n = ((n2 >> 8) >> 8) >> 8;
                bytes[3] = (byte) (n & 255);
            } else {
                bytes[3] = (byte) (n2 & 255);
                bytes[2] = (byte) (r3 & 255);
                bytes[1] = (byte) (r3 & 255);
                n = ((n2 >> 8) >> 8) >> 8;
                bytes[0] = (byte) (n & 255);
            }
            long n3 = n >> 8;
            if (n3 != 0 && n3 != -1) {
                bluetoothError("Send4ByteNumber", 512, number, 4);
            } else {
                write("Send4ByteNumber", bytes);
            }
        } catch (NumberFormatException e) {
            bluetoothError("Send4ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, number);
        }
    }

    @SimpleFunction(description = "Send a list of byte values to the connected Bluetooth device.")
    public void SendBytes(YailList list) {
        Object[] array = list.toArray();
        byte[] bytes = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            Object element = array[i];
            String s = element.toString();
            try {
                int n = Integer.decode(s).intValue();
                bytes[i] = (byte) (n & 255);
                int n2 = n >> 8;
                if (n2 != 0 && n2 != -1) {
                    bluetoothError("SendBytes", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_FIT_ELEMENT_IN_BYTE, Integer.valueOf(i + 1));
                    return;
                }
            } catch (NumberFormatException e) {
                bluetoothError("SendBytes", 513, Integer.valueOf(i + 1));
                return;
            }
        }
        write("SendBytes", bytes);
    }

    protected void write(String functionName, byte b) {
        if (!IsConnected()) {
            bluetoothError(functionName, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(b);
            this.outputStream.flush();
        } catch (IOException e) {
            Log.e(this.logTag, "IO Exception during Writing" + e.getMessage());
            if (this.disconnectOnError) {
                Disconnect();
            }
            bluetoothError(functionName, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void write(String functionName, byte[] bytes) {
        if (!IsConnected()) {
            bluetoothError(functionName, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(bytes);
            this.outputStream.flush();
        } catch (IOException e) {
            Log.e(this.logTag, "IO Exception during Writing" + e.getMessage());
            if (this.disconnectOnError) {
                Disconnect();
            }
            bluetoothError(functionName, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, e.getMessage());
        }
    }

    @SimpleFunction(description = "Returns an estimate of the number of bytes that can be received without blocking")
    public int BytesAvailableToReceive() {
        if (!IsConnected()) {
            bluetoothError("BytesAvailableToReceive", ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return 0;
        }
        try {
            return this.inputStream.available();
        } catch (IOException e) {
            Log.e(this.logTag, "IO Exception during Getting Receive Availability " + e.getMessage());
            if (this.disconnectOnError) {
                Disconnect();
            }
            bluetoothError("BytesAvailableToReceive", ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, e.getMessage());
            return 0;
        }
    }

    @SimpleFunction(description = "Receive text from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public String ReceiveText(int numberOfBytes) {
        byte[] bytes = read("ReceiveText", numberOfBytes);
        try {
            if (numberOfBytes < 0) {
                return new String(bytes, 0, bytes.length - 1, this.encoding);
            }
            return new String(bytes, this.encoding);
        } catch (UnsupportedEncodingException e) {
            Log.w(this.logTag, "UnsupportedEncodingException: " + e.getMessage());
            return new String(bytes);
        }
    }

    @SimpleFunction(description = "Receive a signed 1-byte number from the connected Bluetooth device.")
    public int ReceiveSigned1ByteNumber() {
        byte[] bytes = read("ReceiveSigned1ByteNumber", 1);
        if (bytes.length != 1) {
            return 0;
        }
        return bytes[0];
    }

    @SimpleFunction(description = "Receive an unsigned 1-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned1ByteNumber() {
        byte[] bytes = read("ReceiveUnsigned1ByteNumber", 1);
        if (bytes.length != 1) {
            return 0;
        }
        return bytes[0] & Ev3Constants.Opcode.TST;
    }

    @SimpleFunction(description = "Receive a signed 2-byte number from the connected Bluetooth device.")
    public int ReceiveSigned2ByteNumber() {
        byte[] bytes = read("ReceiveSigned2ByteNumber", 2);
        if (bytes.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bytes[1] & Ev3Constants.Opcode.TST) | (bytes[0] << 8);
        }
        return (bytes[0] & Ev3Constants.Opcode.TST) | (bytes[1] << 8);
    }

    @SimpleFunction(description = "Receive a unsigned 2-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned2ByteNumber() {
        byte[] bytes = read("ReceiveUnsigned2ByteNumber", 2);
        if (bytes.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bytes[1] & Ev3Constants.Opcode.TST) | ((bytes[0] & Ev3Constants.Opcode.TST) << 8);
        }
        return (bytes[0] & Ev3Constants.Opcode.TST) | ((bytes[1] & Ev3Constants.Opcode.TST) << 8);
    }

    @SimpleFunction(description = "Receive a signed 4-byte number from the connected Bluetooth device.")
    public long ReceiveSigned4ByteNumber() {
        byte[] bytes = read("ReceiveSigned4ByteNumber", 4);
        if (bytes.length != 4) {
            return 0L;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bytes[3] & Ev3Constants.Opcode.TST) | ((bytes[2] & Ev3Constants.Opcode.TST) << 8) | ((bytes[1] & Ev3Constants.Opcode.TST) << 16) | (bytes[0] << 24);
        }
        return (bytes[0] & Ev3Constants.Opcode.TST) | ((bytes[1] & Ev3Constants.Opcode.TST) << 8) | ((bytes[2] & Ev3Constants.Opcode.TST) << 16) | (bytes[3] << 24);
    }

    @SimpleFunction(description = "Receive a unsigned 4-byte number from the connected Bluetooth device.")
    public long ReceiveUnsigned4ByteNumber() {
        byte[] bytes = read("ReceiveUnsigned4ByteNumber", 4);
        if (bytes.length != 4) {
            return 0L;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            return (bytes[3] & 255) | ((bytes[2] & 255) << 8) | ((bytes[1] & 255) << 16) | ((bytes[0] & 255) << 24);
        }
        return (bytes[0] & 255) | ((bytes[1] & 255) << 8) | ((bytes[2] & 255) << 16) | ((bytes[3] & 255) << 24);
    }

    @SimpleFunction(description = "Receive multiple signed byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveSignedBytes(int numberOfBytes) {
        byte[] bytes = read("ReceiveSignedBytes", numberOfBytes);
        List<Integer> list = new ArrayList<>();
        for (int n : bytes) {
            list.add(Integer.valueOf(n));
        }
        return list;
    }

    @SimpleFunction(description = "Receive multiple unsigned byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveUnsignedBytes(int numberOfBytes) {
        byte[] bytes = read("ReceiveUnsignedBytes", numberOfBytes);
        List<Integer> list = new ArrayList<>();
        for (byte b : bytes) {
            int n = b & Ev3Constants.Opcode.TST;
            list.add(Integer.valueOf(n));
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        bluetoothError(r12, com.google.appinventor.components.runtime.util.ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0071, code lost:
    
        bluetoothError(r12, com.google.appinventor.components.runtime.util.ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] read(java.lang.String r12, int r13) {
        /*
            r11 = this;
            boolean r0 = r11.IsConnected()
            r1 = 0
            if (r0 != 0) goto L11
            r0 = 515(0x203, float:7.22E-43)
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r11.bluetoothError(r12, r0, r2)
            byte[] r0 = new byte[r1]
            return r0
        L11:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r2 = 518(0x206, float:7.26E-43)
            r3 = 1
            r4 = 517(0x205, float:7.24E-43)
            r5 = -1
            java.lang.String r6 = "IO Exception during Reading "
            if (r13 < 0) goto L69
            byte[] r7 = new byte[r13]
            r8 = 0
        L23:
            if (r8 >= r13) goto L65
            java.io.InputStream r9 = r11.inputStream     // Catch: java.io.IOException -> L37
            int r10 = r7.length     // Catch: java.io.IOException -> L37
            int r10 = r10 - r8
            int r9 = r9.read(r7, r8, r10)     // Catch: java.io.IOException -> L37
            if (r9 != r5) goto L35
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.io.IOException -> L37
            r11.bluetoothError(r12, r2, r5)     // Catch: java.io.IOException -> L37
            goto L65
        L35:
            int r8 = r8 + r9
            goto L23
        L37:
            r2 = move-exception
            java.lang.String r5 = r11.logTag
            java.lang.String r9 = r2.getMessage()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.StringBuilder r6 = r10.append(r6)
            java.lang.StringBuilder r6 = r6.append(r9)
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r5, r6)
            boolean r5 = r11.disconnectOnError
            if (r5 == 0) goto L59
            r11.Disconnect()
        L59:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r5 = r2.getMessage()
            r3[r1] = r5
            r11.bluetoothError(r12, r4, r3)
        L65:
            r0.write(r7, r1, r8)
            goto Lae
        L69:
            java.io.InputStream r7 = r11.inputStream     // Catch: java.io.IOException -> L80
            int r7 = r7.read()     // Catch: java.io.IOException -> L80
            if (r7 != r5) goto L77
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.io.IOException -> L80
            r11.bluetoothError(r12, r2, r5)     // Catch: java.io.IOException -> L80
            goto Lae
        L77:
            r0.write(r7)     // Catch: java.io.IOException -> L80
            byte r8 = r11.delimiter     // Catch: java.io.IOException -> L80
            if (r7 != r8) goto L7f
            goto Lae
        L7f:
            goto L69
        L80:
            r2 = move-exception
            java.lang.String r5 = r11.logTag
            java.lang.String r7 = r2.getMessage()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r6 = r8.append(r6)
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r5, r6)
            boolean r5 = r11.disconnectOnError
            if (r5 == 0) goto La2
            r11.Disconnect()
        La2:
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r5 = r2.getMessage()
            r3[r1] = r5
            r11.bluetoothError(r12, r4, r3)
        Lae:
            byte[] r1 = r0.toByteArray()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.BluetoothConnectionBase.read(java.lang.String, int):byte[]");
    }

    @Override // com.google.appinventor.components.runtime.OnDestroyListener
    public void onDestroy() {
        prepareToDie();
    }

    @Override // com.google.appinventor.components.runtime.Deleteable
    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.socket != null) {
            Disconnect();
        }
    }
}
