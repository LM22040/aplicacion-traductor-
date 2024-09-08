package androidx.core.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator() { // from class: androidx.core.provider.FontProvider$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return FontProvider.lambda$static$0((byte[]) obj, (byte[]) obj2);
        }
    };

    private FontProvider() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest request, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo providerInfo = getProvider(context.getPackageManager(), request, context.getResources());
        if (providerInfo == null) {
            return FontsContractCompat.FontFamilyResult.create(1, null);
        }
        FontsContractCompat.FontInfo[] fonts = query(context, request, providerInfo.authority, cancellationSignal);
        return FontsContractCompat.FontFamilyResult.create(0, fonts);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest request, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = request.getProviderAuthority();
        ProviderInfo info = packageManager.resolveContentProvider(providerAuthority, 0);
        if (info == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!info.packageName.equals(request.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + request.getProviderPackage());
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(info.packageName, 64);
        List<byte[]> signatures = convertToByteArrayList(packageInfo.signatures);
        Collections.sort(signatures, sByteArrayComparator);
        List<List<byte[]>> requestCertificatesList = getCertificates(request, resources);
        for (int i = 0; i < requestCertificatesList.size(); i++) {
            List<byte[]> requestSignatures = new ArrayList<>(requestCertificatesList.get(i));
            Collections.sort(requestSignatures, sByteArrayComparator);
            if (equalsByteArrayList(signatures, requestSignatures)) {
                return info;
            }
        }
        return null;
    }

    static FontsContractCompat.FontInfo[] query(Context context, FontRequest request, String authority, CancellationSignal cancellationSignal) {
        Cursor cursor;
        int i;
        int italicColumnIndex;
        Uri fileUri;
        int italicColumnIndex2;
        boolean italic;
        ArrayList<FontsContractCompat.FontInfo> result = new ArrayList<>();
        Uri uri = new Uri.Builder().scheme("content").authority(authority).build();
        Uri fileBaseUri = new Uri.Builder().scheme("content").authority(authority).appendPath("file").build();
        Cursor cursor2 = null;
        try {
            String[] projection = {"_id", FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE};
            ContentResolver resolver = context.getContentResolver();
            if (Build.VERSION.SDK_INT > 16) {
                cursor = Api16Impl.query(resolver, uri, projection, "query = ?", new String[]{request.getQuery()}, null, cancellationSignal);
            } else {
                cursor = resolver.query(uri, projection, "query = ?", new String[]{request.getQuery()}, null);
            }
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        int resultCodeColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE);
                        result = new ArrayList<>();
                        int idColumnIndex = cursor.getColumnIndex("_id");
                        int fileIdColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.FILE_ID);
                        int ttcIndexColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX);
                        int weightColumnIndex = cursor.getColumnIndex(FontsContractCompat.Columns.WEIGHT);
                        int weight = cursor.getColumnIndex(FontsContractCompat.Columns.ITALIC);
                        while (cursor.moveToNext()) {
                            if (resultCodeColumnIndex != -1) {
                                i = cursor.getInt(resultCodeColumnIndex);
                            } else {
                                i = 0;
                            }
                            int resultCode = i;
                            int ttcIndex = ttcIndexColumnIndex != -1 ? cursor.getInt(ttcIndexColumnIndex) : 0;
                            if (fileIdColumnIndex == -1) {
                                long id = cursor.getLong(idColumnIndex);
                                italicColumnIndex = weight;
                                fileUri = ContentUris.withAppendedId(uri, id);
                            } else {
                                italicColumnIndex = weight;
                                long id2 = cursor.getLong(fileIdColumnIndex);
                                fileUri = ContentUris.withAppendedId(fileBaseUri, id2);
                            }
                            int weight2 = weightColumnIndex != -1 ? cursor.getInt(weightColumnIndex) : 400;
                            int resultCodeColumnIndex2 = resultCodeColumnIndex;
                            int resultCodeColumnIndex3 = italicColumnIndex;
                            if (resultCodeColumnIndex3 != -1) {
                                italicColumnIndex2 = resultCodeColumnIndex3;
                                if (cursor.getInt(resultCodeColumnIndex3) == 1) {
                                    italic = true;
                                    result.add(FontsContractCompat.FontInfo.create(fileUri, ttcIndex, weight2, italic, resultCode));
                                    weight = italicColumnIndex2;
                                    resultCodeColumnIndex = resultCodeColumnIndex2;
                                }
                            } else {
                                italicColumnIndex2 = resultCodeColumnIndex3;
                            }
                            italic = false;
                            result.add(FontsContractCompat.FontInfo.create(fileUri, ttcIndex, weight2, italic, resultCode));
                            weight = italicColumnIndex2;
                            resultCodeColumnIndex = resultCodeColumnIndex2;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return (FontsContractCompat.FontInfo[]) result.toArray(new FontsContractCompat.FontInfo[0]);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest request, Resources resources) {
        if (request.getCertificates() != null) {
            return request.getCertificates();
        }
        int resourceId = request.getCertificatesArrayResId();
        return FontResourcesParserCompat.readCerts(resources, resourceId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(byte[] l, byte[] r) {
        if (l.length != r.length) {
            return l.length - r.length;
        }
        for (int i = 0; i < l.length; i++) {
            if (l[i] != r[i]) {
                return l[i] - r[i];
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> signatures, List<byte[]> requestSignatures) {
        if (signatures.size() != requestSignatures.size()) {
            return false;
        }
        for (int i = 0; i < signatures.size(); i++) {
            if (!Arrays.equals(signatures.get(i), requestSignatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatures) {
        List<byte[]> shaList = new ArrayList<>();
        for (Signature signature : signatures) {
            shaList.add(signature.toByteArray());
        }
        return shaList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api16Impl {
        private Api16Impl() {
        }

        static Cursor query(ContentResolver contentResolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, Object cancellationSignal) {
            return contentResolver.query(uri, projection, selection, selectionArgs, sortOrder, (CancellationSignal) cancellationSignal);
        }
    }
}
