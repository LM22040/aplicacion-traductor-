package com.google.appinventor.components.runtime.util;

import android.R;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.provider.Contacts;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class MediaUtil {
    private static final String LOG_TAG = "MediaUtil";
    private static final Map<String, File> tempFileMap = new HashMap();
    private static ConcurrentHashMap<String, String> pathCache = new ConcurrentHashMap<>(2);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum MediaSource {
        ASSET,
        REPL_ASSET,
        SDCARD,
        FILE_URL,
        URL,
        CONTENT_URI,
        CONTACT_URI,
        PRIVATE_DATA
    }

    private MediaUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String fileUrlToFilePath(String mediaPath) throws IOException {
        try {
            return new File(new URL(mediaPath).toURI()).getAbsolutePath();
        } catch (IllegalArgumentException e) {
            throw new IOException("Unable to determine file path of file url " + mediaPath);
        } catch (Exception e2) {
            throw new IOException("Unable to determine file path of file url " + mediaPath);
        }
    }

    private static MediaSource determineMediaSource(Form form, String mediaPath) {
        if (mediaPath.startsWith(QUtil.getExternalStoragePath(form)) || mediaPath.startsWith("/sdcard/")) {
            return MediaSource.SDCARD;
        }
        if (mediaPath.startsWith("content://contacts/")) {
            return MediaSource.CONTACT_URI;
        }
        if (mediaPath.startsWith("content://")) {
            return MediaSource.CONTENT_URI;
        }
        if (mediaPath.startsWith("/data/")) {
            return MediaSource.PRIVATE_DATA;
        }
        try {
            URL url = new URL(mediaPath);
            if (mediaPath.startsWith("file:")) {
                if (url.getPath().startsWith("/android_asset/")) {
                    return MediaSource.ASSET;
                }
                return MediaSource.FILE_URL;
            }
            return MediaSource.URL;
        } catch (MalformedURLException e) {
            if (form instanceof ReplForm) {
                if (((ReplForm) form).isAssetsLoaded()) {
                    return MediaSource.REPL_ASSET;
                }
                return MediaSource.ASSET;
            }
            return MediaSource.ASSET;
        }
    }

    @Deprecated
    public static boolean isExternalFileUrl(String mediaPath) {
        Log.w(LOG_TAG, "Calling deprecated version of isExternalFileUrl", new IllegalAccessException());
        return mediaPath.startsWith(new StringBuilder().append("file://").append(QUtil.getExternalStoragePath(Form.getActiveForm())).toString()) || mediaPath.startsWith("file:///sdcard/");
    }

    public static boolean isExternalFileUrl(Context context, String mediaPath) {
        if (Build.VERSION.SDK_INT >= 29) {
            return false;
        }
        return mediaPath.startsWith(new StringBuilder().append("file://").append(QUtil.getExternalStorageDir(context)).toString()) || mediaPath.startsWith("file:///sdcard");
    }

    @Deprecated
    public static boolean isExternalFile(String mediaPath) {
        Log.w(LOG_TAG, "Calling deprecated version of isExternalFile", new IllegalAccessException());
        return mediaPath.startsWith(QUtil.getExternalStoragePath(Form.getActiveForm())) || mediaPath.startsWith("/sdcard/") || isExternalFileUrl(Form.getActiveForm(), mediaPath);
    }

    public static boolean isExternalFile(Context context, String mediaPath) {
        if (Build.VERSION.SDK_INT >= 29) {
            return false;
        }
        return mediaPath.startsWith(QUtil.getExternalStoragePath(context)) || mediaPath.startsWith("/sdcard/") || isExternalFileUrl(context, mediaPath);
    }

    private static String findCaseinsensitivePath(Form form, String mediaPath) throws IOException {
        if (!pathCache.containsKey(mediaPath)) {
            String newPath = findCaseinsensitivePathWithoutCache(form, mediaPath);
            if (newPath == null) {
                return null;
            }
            pathCache.put(mediaPath, newPath);
        }
        return pathCache.get(mediaPath);
    }

    private static String findCaseinsensitivePathWithoutCache(Form form, String mediaPath) throws IOException {
        String[] mediaPathlist = form.getAssets().list("");
        int l = Array.getLength(mediaPathlist);
        for (int i = 0; i < l; i++) {
            String temp = mediaPathlist[i];
            if (temp.equalsIgnoreCase(mediaPath)) {
                return temp;
            }
        }
        return null;
    }

    private static InputStream getAssetsIgnoreCaseInputStream(Form form, String mediaPath) throws IOException {
        try {
            return form.getAssets().open(mediaPath);
        } catch (IOException e) {
            String path = findCaseinsensitivePath(form, mediaPath);
            if (path == null) {
                throw e;
            }
            return form.getAssets().open(path);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource;

        static {
            int[] iArr = new int[MediaSource.values().length];
            $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource = iArr;
            try {
                iArr[MediaSource.ASSET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.PRIVATE_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.REPL_ASSET.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.SDCARD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.FILE_URL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.URL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.CONTENT_URI.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.CONTACT_URI.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000d. Please report as an issue. */
    public static InputStream openMedia(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        InputStream is;
        switch (AnonymousClass3.$SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[mediaSource.ordinal()]) {
            case 1:
                if (mediaPath.startsWith("file:")) {
                    mediaPath = mediaPath.substring(mediaPath.indexOf("/android_asset/") + "/android_asset/".length());
                }
                return getAssetsIgnoreCaseInputStream(form, mediaPath);
            case 2:
                return new FileInputStream(mediaPath);
            case 3:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                try {
                    return new FileInputStream(new File(URI.create(form.getAssetPath(mediaPath))));
                } catch (Exception e) {
                    if (SdkLevel.getLevel() < 9) {
                        Log.d(LOG_TAG, "Error in REPL_ASSET Fetching: " + Log.getStackTraceString(e));
                        FroyoUtil.throwIOException(e);
                    } else {
                        throw new IOException(e);
                    }
                }
            case 4:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return new FileInputStream(mediaPath);
            case 5:
                if (isExternalFileUrl(form, mediaPath) && RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                break;
            case 6:
                return new URL(mediaPath).openStream();
            case 7:
                return form.getContentResolver().openInputStream(Uri.parse(mediaPath));
            case 8:
                if (SdkLevel.getLevel() >= 12) {
                    is = HoneycombMR1Util.openContactPhotoInputStreamHelper(form.getContentResolver(), Uri.parse(mediaPath));
                } else {
                    is = Contacts.People.openContactPhotoInputStream(form.getContentResolver(), Uri.parse(mediaPath));
                }
                if (is != null) {
                    return is;
                }
                throw new IOException("Unable to open contact photo " + mediaPath + ".");
            default:
                throw new IOException("Unable to open media " + mediaPath + ".");
        }
    }

    public static InputStream openMedia(Form form, String mediaPath) throws IOException {
        return openMedia(form, mediaPath, determineMediaSource(form, mediaPath));
    }

    public static File copyMediaToTempFile(Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        return copyMediaToTempFile(form, mediaPath, mediaSource);
    }

    private static File copyMediaToTempFile(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        InputStream in = openMedia(form, mediaPath, mediaSource);
        File file = null;
        try {
            try {
                file = File.createTempFile("AI_Media_", null);
                file.deleteOnExit();
                FileUtil.writeStreamToFile(in, file.getAbsolutePath());
                return file;
            } catch (IOException e) {
                if (file == null) {
                    Log.e(LOG_TAG, "Could not copy media " + mediaPath + " to temp file.");
                } else {
                    Log.e(LOG_TAG, "Could not copy media " + mediaPath + " to temp file " + file.getAbsolutePath());
                    file.delete();
                }
                throw e;
            }
        } finally {
            in.close();
        }
    }

    private static File cacheMediaTempFile(Form form, String mediaPath, MediaSource mediaSource) throws IOException {
        Map<String, File> map = tempFileMap;
        File tempFile = map.get(mediaPath);
        if (tempFile == null || !tempFile.exists()) {
            Log.i(LOG_TAG, "Copying media " + mediaPath + " to temp file...");
            File tempFile2 = copyMediaToTempFile(form, mediaPath, mediaSource);
            Log.i(LOG_TAG, "Finished copying media " + mediaPath + " to temp file " + tempFile2.getAbsolutePath());
            map.put(mediaPath, tempFile2);
            return tempFile2;
        }
        return tempFile;
    }

    public static BitmapDrawable getBitmapDrawable(Form form, String mediaPath) throws IOException {
        if (mediaPath == null || mediaPath.length() == 0) {
            return null;
        }
        final Synchronizer<BitmapDrawable> syncer = new Synchronizer<>();
        AsyncCallbackPair<BitmapDrawable> continuation = new AsyncCallbackPair<BitmapDrawable>() { // from class: com.google.appinventor.components.runtime.util.MediaUtil.1
            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onFailure(String message) {
                Synchronizer.this.error(message);
            }

            @Override // com.google.appinventor.components.runtime.util.AsyncCallbackPair
            public void onSuccess(BitmapDrawable result) {
                Synchronizer.this.wakeup(result);
            }
        };
        getBitmapDrawableAsync(form, mediaPath, continuation);
        syncer.waitfor();
        BitmapDrawable result = syncer.getResult();
        if (result == null) {
            String error = syncer.getError();
            if (error.startsWith("PERMISSION_DENIED:")) {
                throw new PermissionException(error.split(":")[1]);
            }
            throw new IOException(error);
        }
        return result;
    }

    public static void getBitmapDrawableAsync(Form form, String mediaPath, AsyncCallbackPair<BitmapDrawable> continuation) {
        getBitmapDrawableAsync(form, mediaPath, -1, -1, continuation);
    }

    public static void getBitmapDrawableAsync(final Form form, final String mediaPath, final int desiredWidth, final int desiredHeight, final AsyncCallbackPair<BitmapDrawable> continuation) {
        if (mediaPath == null || mediaPath.length() == 0) {
            continuation.onSuccess(null);
            return;
        }
        final MediaSource mediaSource = determineMediaSource(form, mediaPath);
        Runnable loadImage = new Runnable() { // from class: com.google.appinventor.components.runtime.util.MediaUtil.2
            @Override // java.lang.Runnable
            public void run() {
                BitmapFactory.Options options;
                BitmapDrawable originalBitmapDrawable;
                boolean needsResize;
                Log.d(MediaUtil.LOG_TAG, "mediaPath = " + mediaPath);
                InputStream is = null;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                try {
                    try {
                        is = MediaUtil.openMedia(form, mediaPath, mediaSource);
                        while (true) {
                            int read = is.read(buf);
                            if (read <= 0) {
                                break;
                            } else {
                                bos.write(buf, 0, read);
                            }
                        }
                        byte[] buf2 = bos.toByteArray();
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e);
                            }
                        }
                        try {
                            bos.close();
                        } catch (IOException e2) {
                        }
                        ByteArrayInputStream bis = new ByteArrayInputStream(buf2);
                        try {
                            try {
                                try {
                                    bis.mark(buf2.length);
                                    options = MediaUtil.getBitmapOptions(form, bis, mediaPath);
                                    bis.reset();
                                    originalBitmapDrawable = new BitmapDrawable(form.getResources(), MediaUtil.decodeStream(bis, null, options));
                                    originalBitmapDrawable.setTargetDensity(form.getResources().getDisplayMetrics());
                                    needsResize = desiredWidth > 0 && desiredHeight >= 0;
                                } catch (Exception e3) {
                                    Log.w(MediaUtil.LOG_TAG, "Exception while loading media.", e3);
                                    continuation.onFailure(e3.getMessage());
                                    bis.close();
                                }
                                if (!needsResize && (options.inSampleSize != 1 || form.deviceDensity() == 1.0f)) {
                                    continuation.onSuccess(originalBitmapDrawable);
                                    try {
                                        bis.close();
                                        return;
                                    } catch (IOException e4) {
                                        Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e4);
                                        return;
                                    }
                                }
                                float deviceDensity = form.deviceDensity();
                                int i = desiredWidth;
                                if (i <= 0) {
                                    i = originalBitmapDrawable.getIntrinsicWidth();
                                }
                                int scaledWidth = (int) (deviceDensity * i);
                                float deviceDensity2 = form.deviceDensity();
                                int i2 = desiredHeight;
                                if (i2 <= 0) {
                                    i2 = originalBitmapDrawable.getIntrinsicHeight();
                                }
                                int scaledHeight = (int) (deviceDensity2 * i2);
                                Log.d(MediaUtil.LOG_TAG, "form.deviceDensity() = " + form.deviceDensity());
                                Log.d(MediaUtil.LOG_TAG, "originalBitmapDrawable.getIntrinsicWidth() = " + originalBitmapDrawable.getIntrinsicWidth());
                                Log.d(MediaUtil.LOG_TAG, "originalBitmapDrawable.getIntrinsicHeight() = " + originalBitmapDrawable.getIntrinsicHeight());
                                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmapDrawable.getBitmap(), scaledWidth, scaledHeight, false);
                                BitmapDrawable scaledBitmapDrawable = new BitmapDrawable(form.getResources(), scaledBitmap);
                                scaledBitmapDrawable.setTargetDensity(form.getResources().getDisplayMetrics());
                                System.gc();
                                continuation.onSuccess(scaledBitmapDrawable);
                                bis.close();
                            } finally {
                            }
                        } catch (IOException e5) {
                            Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e5);
                        }
                    } catch (PermissionException e6) {
                        continuation.onFailure("PERMISSION_DENIED:" + e6.getPermissionNeeded());
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e7) {
                                Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e7);
                            }
                        }
                        try {
                            bos.close();
                        } catch (IOException e8) {
                        }
                    } catch (IOException e9) {
                        if (mediaSource == MediaSource.CONTACT_URI) {
                            BitmapDrawable drawable = new BitmapDrawable(form.getResources(), BitmapFactory.decodeResource(form.getResources(), R.drawable.picture_frame, null));
                            continuation.onSuccess(drawable);
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e10) {
                                    Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e10);
                                }
                            }
                            try {
                                bos.close();
                            } catch (IOException e11) {
                            }
                            return;
                        }
                        Log.d(MediaUtil.LOG_TAG, "IOException reading file.", e9);
                        continuation.onFailure(e9.getMessage());
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e12) {
                                Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e12);
                            }
                        }
                        try {
                            bos.close();
                        } catch (IOException e13) {
                        }
                    }
                } catch (Throwable th) {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e14) {
                            Log.w(MediaUtil.LOG_TAG, "Unexpected error on close", e14);
                        }
                    }
                    try {
                        bos.close();
                    } catch (IOException e15) {
                    }
                    throw th;
                }
            }
        };
        AsynchUtil.runAsynchronously(loadImage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap decodeStream(InputStream is, Rect outPadding, BitmapFactory.Options opts) {
        return BitmapFactory.decodeStream(new FlushedInputStream(is), outPadding, opts);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0;
            while (totalBytesSkipped < n) {
                long bytesSkipped = this.in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0) {
                    if (read() < 0) {
                        break;
                    }
                    bytesSkipped = 1;
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BitmapFactory.Options getBitmapOptions(Form form, InputStream is, String mediaPath) {
        int maxWidth;
        int maxHeight;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeStream(is, null, options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        Display display = ((WindowManager) form.getSystemService("window")).getDefaultDisplay();
        if (Form.getCompatibilityMode()) {
            maxWidth = 720;
            maxHeight = 840;
        } else {
            int maxWidth2 = display.getWidth();
            maxWidth = (int) (maxWidth2 / form.deviceDensity());
            maxHeight = (int) (display.getHeight() / form.deviceDensity());
        }
        int sampleSize = 1;
        while (imageWidth / sampleSize > maxWidth && imageHeight / sampleSize > maxHeight) {
            sampleSize *= 2;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        Log.d(LOG_TAG, "getBitmapOptions: sampleSize = " + sampleSize + " mediaPath = " + mediaPath + " maxWidth = " + maxWidth + " maxHeight = " + maxHeight + " display width = " + display.getWidth() + " display height = " + display.getHeight());
        options2.inSampleSize = sampleSize;
        return options2;
    }

    private static AssetFileDescriptor getAssetsIgnoreCaseAfd(Form form, String mediaPath) throws IOException {
        try {
            return form.getAssets().openFd(mediaPath);
        } catch (IOException e) {
            String path = findCaseinsensitivePath(form, mediaPath);
            if (path == null) {
                throw e;
            }
            return form.getAssets().openFd(path);
        }
    }

    public static int loadSoundPool(SoundPool soundPool, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (AnonymousClass3.$SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[mediaSource.ordinal()]) {
            case 1:
                return soundPool.load(getAssetsIgnoreCaseAfd(form, mediaPath), 1);
            case 2:
            default:
                throw new IOException("Unable to load audio " + mediaPath + ".");
            case 3:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return soundPool.load(fileUrlToFilePath(form.getAssetPath(mediaPath)), 1);
            case 4:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_AUDIO");
                }
                return soundPool.load(mediaPath, 1);
            case 5:
                if (isExternalFileUrl(form, mediaPath) || RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_AUDIO");
                }
                return soundPool.load(fileUrlToFilePath(mediaPath), 1);
            case 6:
            case 7:
                File tempFile = cacheMediaTempFile(form, mediaPath, mediaSource);
                return soundPool.load(tempFile.getAbsolutePath(), 1);
            case 8:
                throw new IOException("Unable to load audio for contact " + mediaPath + ".");
        }
    }

    public static void loadMediaPlayer(MediaPlayer mediaPlayer, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (AnonymousClass3.$SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[mediaSource.ordinal()]) {
            case 1:
                AssetFileDescriptor afd = getAssetsIgnoreCaseAfd(form, mediaPath);
                try {
                    FileDescriptor fd = afd.getFileDescriptor();
                    long offset = afd.getStartOffset();
                    long length = afd.getLength();
                    mediaPlayer.setDataSource(fd, offset, length);
                    return;
                } finally {
                    afd.close();
                }
            case 2:
            default:
                throw new IOException("Unable to load audio or video " + mediaPath + ".");
            case 3:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                mediaPlayer.setDataSource(fileUrlToFilePath(form.getAssetPath(mediaPath)));
                return;
            case 4:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_AUDIO");
                }
                mediaPlayer.setDataSource(mediaPath);
                return;
            case 5:
                if (isExternalFileUrl(form, mediaPath) || RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_AUDIO");
                }
                mediaPlayer.setDataSource(fileUrlToFilePath(mediaPath));
                return;
            case 6:
                mediaPlayer.setDataSource(mediaPath);
                return;
            case 7:
                mediaPlayer.setDataSource(form, Uri.parse(mediaPath));
                return;
            case 8:
                throw new IOException("Unable to load audio or video for contact " + mediaPath + ".");
        }
    }

    public static void loadVideoView(VideoView videoView, Form form, String mediaPath) throws IOException {
        MediaSource mediaSource = determineMediaSource(form, mediaPath);
        switch (AnonymousClass3.$SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[mediaSource.ordinal()]) {
            case 1:
            case 6:
                File tempFile = cacheMediaTempFile(form, mediaPath, mediaSource);
                videoView.setVideoPath(tempFile.getAbsolutePath());
                return;
            case 2:
            default:
                throw new IOException("Unable to load video " + mediaPath + ".");
            case 3:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                videoView.setVideoPath(fileUrlToFilePath(form.getAssetPath(mediaPath)));
                return;
            case 4:
                if (RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_VIDEO");
                }
                videoView.setVideoPath(mediaPath);
                return;
            case 5:
                if (isExternalFileUrl(form, mediaPath) || RUtil.needsFilePermission(form, mediaPath, null)) {
                    form.assertPermission(Build.VERSION.SDK_INT < 33 ? "android.permission.READ_EXTERNAL_STORAGE" : "android.permission.READ_MEDIA_VIDEO");
                }
                videoView.setVideoPath(fileUrlToFilePath(mediaPath));
                return;
            case 7:
                videoView.setVideoURI(Uri.parse(mediaPath));
                return;
            case 8:
                throw new IOException("Unable to load video for contact " + mediaPath + ".");
        }
    }
}
