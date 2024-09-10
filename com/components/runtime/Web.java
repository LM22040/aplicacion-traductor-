package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.RequestTimeoutException;
import com.google.appinventor.components.runtime.repackaged.org.json.XML;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.ChartDataSourceUtil;
import com.google.appinventor.components.runtime.util.CsvUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.NanoHTTPD;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.XmlParser;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import kawa.lang.SyntaxForms;
import org.json.JSONException;
import org.xml.sax.InputSource;

@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Non-visible component that provides functions for HTTP GET, POST, PUT, and DELETE requests.", iconName = "images/web.png", nonVisible = SyntaxForms.DEBUGGING, version = 9)
@UsesLibraries(libraries = "json.jar")
/* loaded from: classes.dex */
public class Web extends AndroidNonvisibleComponent implements Component, ObservableDataSource<YailList, Future<YailList>> {
    private static final String LOG_TAG = "Web";
    private static final java.util.Map<String, String> mimeTypeToExtension;
    private final Activity activity;
    private boolean allowCookies;
    private YailList columns;
    private final CookieHandler cookieHandler;
    private HashSet<DataSourceChangeListener> dataSourceObservers;
    private boolean haveReadPermission;
    private boolean haveWritePermission;
    private FutureTask<Void> lastTask;
    private YailList requestHeaders;
    private String responseFileName;
    private String responseTextEncoding;
    private boolean saveResponse;
    private int timeout;
    private String urlString;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InvalidRequestHeadersException extends Exception {
        final int errorNumber;
        final int index;

        InvalidRequestHeadersException(int errorNumber, int index) {
            this.errorNumber = errorNumber;
            this.index = index;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class BuildRequestDataException extends Exception {
        final int errorNumber;
        final int index;

        BuildRequestDataException(int errorNumber, int index) {
            this.errorNumber = errorNumber;
            this.index = index;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CapturedProperties {
        final boolean allowCookies;
        final java.util.Map<String, List<String>> cookies;
        final java.util.Map<String, List<String>> requestHeaders;
        final String responseFileName;
        final boolean saveResponse;
        final int timeout;
        final URL url;
        final String urlString;

        CapturedProperties(Web web) throws MalformedURLException, InvalidRequestHeadersException {
            String str = web.urlString;
            this.urlString = str;
            URL url = new URL(str);
            this.url = url;
            boolean z = web.allowCookies;
            this.allowCookies = z;
            this.saveResponse = web.saveResponse;
            this.responseFileName = web.responseFileName;
            this.timeout = web.timeout;
            java.util.Map<String, List<String>> processRequestHeaders = Web.processRequestHeaders(web.requestHeaders);
            this.requestHeaders = processRequestHeaders;
            java.util.Map<String, List<String>> cookiesTemp = null;
            if (z && web.cookieHandler != null) {
                try {
                    cookiesTemp = web.cookieHandler.get(url.toURI(), processRequestHeaders);
                } catch (IOException e) {
                } catch (URISyntaxException e2) {
                }
            }
            this.cookies = cookiesTemp;
        }
    }

    static {
        HashMap newHashMap = Maps.newHashMap();
        mimeTypeToExtension = newHashMap;
        newHashMap.put("application/pdf", "pdf");
        newHashMap.put("application/zip", "zip");
        newHashMap.put("audio/mpeg", "mpeg");
        newHashMap.put("audio/mp3", "mp3");
        newHashMap.put("audio/mp4", "mp4");
        newHashMap.put("image/gif", "gif");
        newHashMap.put("image/jpeg", "jpg");
        newHashMap.put("image/png", "png");
        newHashMap.put("image/tiff", "tiff");
        newHashMap.put(NanoHTTPD.MIME_PLAINTEXT, "txt");
        newHashMap.put(NanoHTTPD.MIME_HTML, "html");
        newHashMap.put(NanoHTTPD.MIME_XML, "xml");
    }

    public Web(ComponentContainer container) {
        super(container.$form());
        this.urlString = "";
        this.requestHeaders = new YailList();
        this.responseFileName = "";
        this.timeout = 0;
        this.haveReadPermission = false;
        this.haveWritePermission = false;
        this.lastTask = null;
        this.columns = new YailList();
        this.dataSourceObservers = new HashSet<>();
        this.responseTextEncoding = "UTF-8";
        this.activity = container.$context();
        this.cookieHandler = SdkLevel.getLevel() >= 9 ? GingerbreadUtil.newCookieManager() : null;
    }

    protected Web() {
        super(null);
        this.urlString = "";
        this.requestHeaders = new YailList();
        this.responseFileName = "";
        this.timeout = 0;
        this.haveReadPermission = false;
        this.haveWritePermission = false;
        this.lastTask = null;
        this.columns = new YailList();
        this.dataSourceObservers = new HashSet<>();
        this.responseTextEncoding = "UTF-8";
        this.activity = null;
        this.cookieHandler = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL for the web request.")
    public String Url() {
        return this.urlString;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void Url(String url) {
        this.urlString = url;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "User-specified character encoding for web response.")
    public String ResponseTextEncoding() {
        return this.responseTextEncoding;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "UTF-8", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void ResponseTextEncoding(String encoding) {
        this.responseTextEncoding = encoding;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The request headers, as a list of two-element sublists. The first element of each sublist represents the request header field name. The second element of each sublist represents the request header field values, either a single value or a list containing multiple values.")
    public YailList RequestHeaders() {
        return this.requestHeaders;
    }

    @SimpleProperty
    public void RequestHeaders(YailList list) {
        try {
            processRequestHeaders(list);
            this.requestHeaders = list;
        } catch (InvalidRequestHeadersException e) {
            this.form.dispatchErrorOccurredEvent(this, "RequestHeaders", e.errorNumber, Integer.valueOf(e.index));
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the cookies from a response should be saved and used in subsequent requests. Cookies are only supported on Android version 2.3 or greater.")
    public boolean AllowCookies() {
        return this.allowCookies;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "false", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void AllowCookies(boolean allowCookies) {
        this.allowCookies = allowCookies;
        if (allowCookies && this.cookieHandler == null) {
            this.form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the response should be saved in a file.")
    public boolean SaveResponse() {
        return this.saveResponse;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "false", editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)
    public void SaveResponse(boolean saveResponse) {
        this.saveResponse = saveResponse;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file where the response should be saved. If SaveResponse is true and ResponseFileName is empty, then a new file name will be generated.")
    public String ResponseFileName() {
        return this.responseFileName;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = "", editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    public void ResponseFileName(String responseFileName) {
        this.responseFileName = responseFileName;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of milliseconds that a web request will wait for a response before giving up. If set to 0, then there is no time limit on how long the request will wait.")
    public int Timeout() {
        return this.timeout;
    }

    @SimpleProperty
    @DesignerProperty(defaultValue = Component.TYPEFACE_DEFAULT, editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)
    public void Timeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentError("Web Timeout must be a non-negative integer.");
        }
        this.timeout = timeout;
    }

    @SimpleFunction(description = "Clears all cookies for this Web component.")
    public void ClearCookies() {
        CookieHandler cookieHandler = this.cookieHandler;
        if (cookieHandler != null) {
            GingerbreadUtil.clearCookies(cookieHandler);
        } else {
            this.form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
        }
    }

    @SimpleFunction
    public void Get() {
        final CapturedProperties webProps = capturePropertyValues("Get");
        if (webProps == null) {
            return;
        }
        FutureTask<Void> futureTask = new FutureTask<>(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.1
            @Override // java.lang.Runnable
            public void run() {
                Web.this.performRequest(webProps, null, null, "GET", "Get");
            }
        }, null);
        this.lastTask = futureTask;
        AsynchUtil.runAsynchronously(futureTask);
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.\nThe characters of the text are encoded using UTF-8 encoding.\nIf the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.\nIf the SaveResponse property is false, the GotText event will be triggered.")
    public void PostText(String text) {
        requestTextImpl(text, "UTF-8", "PostText", "POST");
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.\nThe characters of the text are encoded using the given encoding.\nIf the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.\nIf the SaveResponse property is false, the GotText event will be triggered.")
    public void PostTextWithEncoding(String text, String encoding) {
        requestTextImpl(text, encoding, "PostTextWithEncoding", "POST");
    }

    @SimpleFunction(description = "Performs an HTTP POST request using the Url property and data from the specified file.\nIf the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.\nIf the SaveResponse property is false, the GotText event will be triggered.")
    public void PostFile(final String path) {
        final CapturedProperties webProps = capturePropertyValues("PostFile");
        if (webProps == null) {
            return;
        }
        FutureTask<Void> futureTask = new FutureTask<>(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.2
            @Override // java.lang.Runnable
            public void run() {
                Web.this.performRequest(webProps, null, path, "POST", "PostFile");
            }
        }, null);
        this.lastTask = futureTask;
        AsynchUtil.runAsynchronously(futureTask);
    }

    @SimpleFunction(description = "Performs an HTTP PATCH request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PatchText(String text) {
        requestTextImpl(text, "UTF-8", "PatchText", "PATCH");
    }

    @SimpleFunction(description = "Performs an HTTP PATCH request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PatchTextWithEncoding(String text, String encoding) {
        requestTextImpl(text, encoding, "PatchTextWithEncoding", "PATCH");
    }

    @SimpleFunction(description = "Performs an HTTP PATCH request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PatchFile(final String path) {
        final CapturedProperties webProps = capturePropertyValues("PatchFile");
        if (webProps == null) {
            return;
        }
        AsynchUtil.runAsynchronously(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.3
            @Override // java.lang.Runnable
            public void run() {
                Web.this.performRequest(webProps, null, path, "PATCH", "PatchFile");
            }
        });
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutText(String text) {
        requestTextImpl(text, "UTF-8", "PutText", "PUT");
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutTextWithEncoding(String text, String encoding) {
        requestTextImpl(text, encoding, "PutTextWithEncoding", "PUT");
    }

    @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
    public void PutFile(final String path) {
        final CapturedProperties webProps = capturePropertyValues("PutFile");
        if (webProps == null) {
            return;
        }
        FutureTask<Void> futureTask = new FutureTask<>(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.4
            @Override // java.lang.Runnable
            public void run() {
                Web.this.performRequest(webProps, null, path, "PUT", "PutFile");
            }
        }, null);
        this.lastTask = futureTask;
        AsynchUtil.runAsynchronously(futureTask);
    }

    @SimpleFunction
    public void Delete() {
        final CapturedProperties webProps = capturePropertyValues("Delete");
        if (webProps == null) {
            return;
        }
        FutureTask<Void> futureTask = new FutureTask<>(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.5
            @Override // java.lang.Runnable
            public void run() {
                Web.this.performRequest(webProps, null, null, "DELETE", "Delete");
            }
        }, null);
        this.lastTask = futureTask;
        AsynchUtil.runAsynchronously(futureTask);
    }

    private void requestTextImpl(final String text, final String encoding, final String functionName, final String httpVerb) {
        final CapturedProperties webProps = capturePropertyValues(functionName);
        if (webProps == null) {
            return;
        }
        FutureTask<Void> futureTask = new FutureTask<>(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.6
            @Override // java.lang.Runnable
            public void run() {
                byte[] requestData;
                try {
                    String str = encoding;
                    if (str != null && str.length() != 0) {
                        requestData = text.getBytes(encoding);
                        Web.this.performRequest(webProps, requestData, null, httpVerb, functionName);
                    }
                    requestData = text.getBytes("UTF-8");
                    Web.this.performRequest(webProps, requestData, null, httpVerb, functionName);
                } catch (UnsupportedEncodingException e) {
                    Web.this.form.dispatchErrorOccurredEvent(Web.this, functionName, ErrorMessages.ERROR_WEB_UNSUPPORTED_ENCODING, encoding);
                }
            }
        }, null);
        this.lastTask = futureTask;
        AsynchUtil.runAsynchronously(futureTask);
    }

    @SimpleEvent
    public void GotText(String url, int responseCode, String responseType, String responseContent) {
        EventDispatcher.dispatchEvent(this, "GotText", url, Integer.valueOf(responseCode), responseType, responseContent);
    }

    @SimpleEvent
    public void GotFile(String url, int responseCode, String responseType, String fileName) {
        EventDispatcher.dispatchEvent(this, "GotFile", url, Integer.valueOf(responseCode), responseType, fileName);
    }

    @SimpleEvent
    public void TimedOut(String url) {
        EventDispatcher.dispatchEvent(this, "TimedOut", url);
    }

    @SimpleFunction
    public String BuildRequestData(YailList list) {
        try {
            return buildRequestData(list);
        } catch (BuildRequestDataException e) {
            this.form.dispatchErrorOccurredEvent(this, "BuildRequestData", e.errorNumber, Integer.valueOf(e.index));
            return "";
        }
    }

    String buildRequestData(YailList list) throws BuildRequestDataException {
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        for (int i = 0; i < list.size(); i++) {
            Object item = list.getObject(i);
            if (item instanceof YailList) {
                YailList sublist = (YailList) item;
                if (sublist.size() == 2) {
                    String name = sublist.getObject(0).toString();
                    String value = sublist.getObject(1).toString();
                    sb.append(delimiter).append(UriEncode(name)).append('=').append(UriEncode(value));
                    delimiter = "&";
                } else {
                    throw new BuildRequestDataException(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_TWO_ELEMENTS, i + 1);
                }
            } else {
                throw new BuildRequestDataException(ErrorMessages.ERROR_WEB_BUILD_REQUEST_DATA_NOT_LIST, i + 1);
            }
        }
        return sb.toString();
    }

    @SimpleFunction
    public String UriEncode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public String UriDecode(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "UTF-8 is unsupported?", e);
            return "";
        }
    }

    @SimpleFunction
    public Object JsonTextDecode(String jsonText) {
        try {
            return decodeJsonText(jsonText, false);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "JsonTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, jsonText);
            return "";
        }
    }

    @SimpleFunction
    public Object JsonTextDecodeWithDictionaries(String jsonText) {
        try {
            return decodeJsonText(jsonText, true);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "JsonTextDecodeWithDictionaries", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, jsonText);
            return "";
        }
    }

    static Object decodeJsonText(String jsonText, boolean useDicts) throws IllegalArgumentException {
        try {
            return JsonUtil.getObjectFromJson(jsonText, useDicts);
        } catch (JSONException e) {
            throw new IllegalArgumentException("jsonText is not a legal JSON value");
        }
    }

    @Deprecated
    static Object decodeJsonText(String jsonText) throws IllegalArgumentException {
        return decodeJsonText(jsonText, false);
    }

    @SimpleFunction
    public String JsonObjectEncode(Object jsonObject) {
        try {
            return JsonUtil.encodeJsonObject(jsonObject);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "JsonObjectEncode", ErrorMessages.ERROR_WEB_JSON_TEXT_ENCODE_FAILED, jsonObject);
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given XML into a set of nested dictionaries that capture the structure and data contained in the XML. See the help for more details.")
    public Object XMLTextDecodeAsDictionary(String XmlText) {
        try {
            XmlParser p = new XmlParser();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            InputSource is = new InputSource(new StringReader(XmlText));
            is.setEncoding("UTF-8");
            parser.parse(is, p);
            return p.getRoot();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            this.form.dispatchErrorOccurredEvent(this, "XMLTextDecodeAsDictionary", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, e.getMessage());
            return new YailDictionary();
        }
    }

    @SimpleFunction(description = "Decodes the given XML string to produce a dictionary structure. See the App Inventor documentation on \"Other topics, notes, and details\" for information.")
    public Object XMLTextDecode(String XmlText) {
        try {
            return JsonTextDecode(XML.toJSONObject(XmlText).toString());
        } catch (com.google.appinventor.components.runtime.repackaged.org.json.JSONException e) {
            Log.e(LOG_TAG, e.getMessage());
            this.form.dispatchErrorOccurredEvent(this, "XMLTextDecode", ErrorMessages.ERROR_WEB_JSON_TEXT_DECODE_FAILED, e.getMessage());
            return YailList.makeEmptyList();
        }
    }

    @SimpleFunction(description = "Decodes the given HTML text value. HTML character entities such as `&`, `<`, `>`, `'`, and `\"` are changed to &, <, >, ', and \". Entities such as &#xhhhh, and &#nnnn are changed to the appropriate characters.")
    public String HtmlTextDecode(String htmlText) {
        try {
            return HtmlEntities.decodeHtmlText(htmlText);
        } catch (IllegalArgumentException e) {
            this.form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", ErrorMessages.ERROR_WEB_HTML_TEXT_DECODE_FAILED, htmlText);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void performRequest(final CapturedProperties webProps, final byte[] postData, final String postFile, final String httpVerb, final String str) {
        int message;
        String[] args;
        Web web;
        Web web2;
        Web web3 = str;
        final List<String> neededPermissions = new ArrayList<>();
        if (postFile != null && FileUtil.needsReadPermission(this.form, postFile) && !this.haveReadPermission) {
            neededPermissions.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (this.saveResponse) {
            String target = FileUtil.resolveFileName(this.form, webProps.responseFileName, this.form.DefaultFileScope());
            if (FileUtil.needsWritePermission(this.form, target) && !this.haveWritePermission) {
                neededPermissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (neededPermissions.size() > 0 && !this.haveReadPermission) {
            this.form.askPermission(new BulkPermissionRequest(this, str, (String[]) neededPermissions.toArray(new String[0])) { // from class: com.google.appinventor.components.runtime.Web.7
                @Override // com.google.appinventor.components.runtime.util.BulkPermissionRequest
                public void onGranted() {
                    if (neededPermissions.contains("android.permission.READ_EXTERNAL_STORAGE")) {
                        me.haveReadPermission = true;
                    }
                    if (neededPermissions.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        me.haveWritePermission = true;
                    }
                    AsynchUtil.runAsynchronously(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            me.performRequest(webProps, postData, postFile, httpVerb, str);
                        }
                    });
                }
            });
            return;
        }
        try {
            HttpURLConnection connection = openConnection(webProps, httpVerb);
            if (connection != null) {
                try {
                    try {
                        if (postData != null) {
                            try {
                                writeRequestData(connection, postData);
                                web2 = this;
                            } catch (SocketTimeoutException e) {
                                web = this;
                                web.activity.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.10
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Web.this.TimedOut(webProps.urlString);
                                    }
                                });
                                throw new RequestTimeoutException();
                            } catch (Throwable th) {
                                e = th;
                                connection.disconnect();
                                throw e;
                            }
                        } else if (postFile != null) {
                            web2 = this;
                            try {
                                web2.writeRequestFile(connection, postFile);
                            } catch (SocketTimeoutException e2) {
                                web = web2;
                                web.activity.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.10
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Web.this.TimedOut(webProps.urlString);
                                    }
                                });
                                throw new RequestTimeoutException();
                            } catch (Throwable th2) {
                                e = th2;
                                connection.disconnect();
                                throw e;
                            }
                        } else {
                            web2 = this;
                        }
                        final int responseCode = connection.getResponseCode();
                        final String responseType = getResponseType(connection);
                        web2.processResponseCookies(connection);
                        if (web2.saveResponse) {
                            final String path = web2.saveResponseContent(connection, webProps.responseFileName, responseType);
                            web2.activity.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    Web.this.GotFile(webProps.urlString, responseCode, responseType, path);
                                }
                            });
                        } else {
                            final String responseContent = getResponseContent(connection, web2.responseTextEncoding);
                            web = web2;
                            try {
                                web2.activity.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.9
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Web.this.GotText(webProps.urlString, responseCode, responseType, responseContent);
                                    }
                                });
                                web.updateColumns(responseContent, responseType);
                                web.notifyDataObservers((YailList) null, (Object) null);
                            } catch (SocketTimeoutException e3) {
                                web.activity.runOnUiThread(new Runnable() { // from class: com.google.appinventor.components.runtime.Web.10
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Web.this.TimedOut(webProps.urlString);
                                    }
                                });
                                throw new RequestTimeoutException();
                            }
                        }
                        connection.disconnect();
                    } catch (DispatchableError e4) {
                        e = e4;
                        web3.form.dispatchErrorOccurredEvent(web3, str, e.getErrorCode(), e.getArguments());
                    } catch (PermissionException e5) {
                        e = e5;
                        web3.form.dispatchPermissionDeniedEvent(web3, str, e);
                    } catch (RequestTimeoutException e6) {
                        web3.form.dispatchErrorOccurredEvent(web3, str, ErrorMessages.ERROR_WEB_REQUEST_TIMED_OUT, webProps.urlString);
                    } catch (FileUtil.FileException e7) {
                        e = e7;
                        web3.form.dispatchErrorOccurredEvent(web3, str, e.getErrorMessageNumber(), new Object[0]);
                    } catch (Exception e8) {
                        e = e8;
                        if (str.equals("Get")) {
                            message = ErrorMessages.ERROR_WEB_UNABLE_TO_GET;
                            args = new String[]{webProps.urlString};
                        } else if (str.equals("Delete")) {
                            message = ErrorMessages.ERROR_WEB_UNABLE_TO_DELETE;
                            args = new String[]{webProps.urlString};
                        } else if (str.equals("PostFile") || str.equals("PutFile") || str.equals("PatchFile")) {
                            message = 1104;
                            args = new String[]{postFile, webProps.urlString};
                        } else {
                            String content = "";
                            if (postData != null) {
                                try {
                                    content = new String(postData, "UTF-8");
                                } catch (UnsupportedEncodingException e9) {
                                    Log.e(LOG_TAG, "UTF-8 is the default charset for Android but not available???");
                                }
                            }
                            args = new String[]{content, webProps.urlString};
                            message = 1103;
                        }
                        web3.form.dispatchErrorOccurredEvent(web3, str, message, args);
                    }
                } catch (Throwable th3) {
                    e = th3;
                }
            }
        } catch (DispatchableError e10) {
            e = e10;
            web3 = this;
        } catch (PermissionException e11) {
            e = e11;
            web3 = this;
        } catch (RequestTimeoutException e12) {
            web3 = this;
        } catch (FileUtil.FileException e13) {
            e = e13;
            web3 = this;
        } catch (Exception e14) {
            e = e14;
            web3 = this;
        }
    }

    private static HttpURLConnection openConnection(CapturedProperties webProps, String httpVerb) throws IOException, ClassCastException, ProtocolException {
        HttpURLConnection connection = (HttpURLConnection) webProps.url.openConnection();
        connection.setConnectTimeout(webProps.timeout);
        connection.setReadTimeout(webProps.timeout);
        if (httpVerb.equals("PUT") || httpVerb.equals("PATCH") || httpVerb.equals("DELETE")) {
            connection.setRequestMethod(httpVerb);
        }
        for (Map.Entry<String, List<String>> header : webProps.requestHeaders.entrySet()) {
            String name = header.getKey();
            for (String value : header.getValue()) {
                connection.addRequestProperty(name, value);
            }
        }
        if (webProps.cookies != null) {
            for (Map.Entry<String, List<String>> cookie : webProps.cookies.entrySet()) {
                String name2 = cookie.getKey();
                for (String value2 : cookie.getValue()) {
                    connection.addRequestProperty(name2, value2);
                }
            }
        }
        return connection;
    }

    private static void writeRequestData(HttpURLConnection connection, byte[] postData) throws IOException {
        connection.setDoOutput(true);
        connection.setFixedLengthStreamingMode(postData.length);
        BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
        try {
            out.write(postData, 0, postData.length);
            out.flush();
        } finally {
            out.close();
        }
    }

    private void writeRequestFile(HttpURLConnection connection, String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(MediaUtil.openMedia(this.form, path));
        try {
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);
            BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
            while (true) {
                try {
                    int b = in.read();
                    if (b != -1) {
                        out.write(b);
                    } else {
                        out.flush();
                        return;
                    }
                } finally {
                    out.close();
                }
            }
        } finally {
            in.close();
        }
    }

    private static String getResponseType(HttpURLConnection connection) {
        String responseType = connection.getContentType();
        return responseType != null ? responseType : "";
    }

    private void processResponseCookies(HttpURLConnection connection) {
        if (this.allowCookies && this.cookieHandler != null) {
            try {
                java.util.Map<String, List<String>> headerFields = connection.getHeaderFields();
                this.cookieHandler.put(connection.getURL().toURI(), headerFields);
            } catch (IOException e) {
            } catch (URISyntaxException e2) {
            }
        }
    }

    private static String getResponseContent(HttpURLConnection connection, String encodingProperty) throws IOException {
        StringBuilder sb;
        String encoding = connection.getContentEncoding();
        if (encoding == null) {
            if (encodingProperty == null || encodingProperty.isEmpty()) {
                encoding = "UTF-8";
            } else {
                encoding = encodingProperty;
            }
        }
        InputStreamReader reader = new InputStreamReader(getConnectionStream(connection), encoding);
        try {
            int contentLength = connection.getContentLength();
            if (contentLength != -1) {
                sb = new StringBuilder(contentLength);
            } else {
                sb = new StringBuilder();
            }
            char[] buf = new char[1024];
            while (true) {
                int read = reader.read(buf);
                if (read != -1) {
                    sb.append(buf, 0, read);
                } else {
                    return sb.toString();
                }
            }
        } finally {
            reader.close();
        }
    }

    private String saveResponseContent(HttpURLConnection connection, String responseFileName, String responseType) throws IOException {
        java.io.File file = createFile(responseFileName, responseType);
        java.io.File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new DispatchableError(ErrorMessages.ERROR_CANNOT_MAKE_DIRECTORY, parent.getAbsolutePath());
        }
        BufferedInputStream in = new BufferedInputStream(getConnectionStream(connection), 4096);
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file), 4096);
            while (true) {
                try {
                    int b = in.read();
                    if (b != -1) {
                        out.write(b);
                    } else {
                        out.flush();
                        in.close();
                        return file.getAbsolutePath();
                    }
                } finally {
                    out.close();
                }
            }
        } catch (Throwable th) {
            in.close();
            throw th;
        }
    }

    private static InputStream getConnectionStream(HttpURLConnection connection) throws SocketTimeoutException {
        try {
            return connection.getInputStream();
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (IOException e2) {
            return connection.getErrorStream();
        }
    }

    private java.io.File createFile(String fileName, String responseType) throws IOException, FileUtil.FileException {
        if (!TextUtils.isEmpty(fileName)) {
            return FileUtil.getExternalFile(this.form, fileName);
        }
        int indexOfSemicolon = responseType.indexOf(59);
        if (indexOfSemicolon != -1) {
            responseType = responseType.substring(0, indexOfSemicolon);
        }
        String extension = mimeTypeToExtension.get(responseType);
        if (extension == null) {
            extension = "tmp";
        }
        return FileUtil.getDownloadFile(this.form, extension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static java.util.Map<String, List<String>> processRequestHeaders(YailList list) throws InvalidRequestHeadersException {
        java.util.Map<String, List<String>> requestHeadersMap = Maps.newHashMap();
        for (int i = 0; i < list.size(); i++) {
            Object item = list.getObject(i);
            if (item instanceof YailList) {
                YailList sublist = (YailList) item;
                if (sublist.size() == 2) {
                    String fieldName = sublist.getObject(0).toString();
                    Object fieldValues = sublist.getObject(1);
                    List<String> values = Lists.newArrayList();
                    if (fieldValues instanceof YailList) {
                        YailList multipleFieldsValues = (YailList) fieldValues;
                        for (int j = 0; j < multipleFieldsValues.size(); j++) {
                            Object value = multipleFieldsValues.getObject(j);
                            values.add(value.toString());
                        }
                    } else {
                        values.add(fieldValues.toString());
                    }
                    requestHeadersMap.put(fieldName, values);
                } else {
                    throw new InvalidRequestHeadersException(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_TWO_ELEMENTS, i + 1);
                }
            } else {
                throw new InvalidRequestHeadersException(ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_LIST, i + 1);
            }
        }
        return requestHeadersMap;
    }

    private CapturedProperties capturePropertyValues(String functionName) {
        try {
            return new CapturedProperties(this);
        } catch (InvalidRequestHeadersException e) {
            this.form.dispatchErrorOccurredEvent(this, functionName, e.errorNumber, Integer.valueOf(e.index));
            return null;
        } catch (MalformedURLException e2) {
            this.form.dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_WEB_MALFORMED_URL, this.urlString);
            return null;
        }
    }

    @Override // com.google.appinventor.components.runtime.DataSource
    public Future<YailList> getDataValue(final YailList key) {
        final FutureTask<Void> currentTask = this.lastTask;
        FutureTask<YailList> getDataValueTask = new FutureTask<>(new Callable<YailList>() { // from class: com.google.appinventor.components.runtime.Web.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public YailList call() throws Exception {
                FutureTask futureTask = currentTask;
                if (futureTask != null && !futureTask.isDone() && !currentTask.isCancelled()) {
                    try {
                        currentTask.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e2) {
                        e2.printStackTrace();
                    }
                }
                return Web.this.getColumns(key);
            }
        });
        AsynchUtil.runAsynchronously(getDataValueTask);
        return getDataValueTask;
    }

    private void updateColumns(String responseContent, String responseType) {
        if (responseType.contains("json")) {
            try {
                this.columns = JsonUtil.getColumnsFromJson(responseContent);
            } catch (JSONException e) {
            }
        } else if (responseType.contains("csv") || responseType.startsWith("text/")) {
            try {
                YailList fromCsvTable = CsvUtil.fromCsvTable(responseContent);
                this.columns = fromCsvTable;
                this.columns = ChartDataSourceUtil.getTranspose(fromCsvTable);
            } catch (Exception e2) {
                this.columns = new YailList();
            }
        }
    }

    public YailList getColumn(String column) {
        for (int i = 0; i < this.columns.size(); i++) {
            YailList list = (YailList) this.columns.getObject(i);
            if (!list.isEmpty() && list.getString(0).equals(column)) {
                return list;
            }
        }
        return new YailList();
    }

    public YailList getColumns(YailList keyColumns) {
        ArrayList<YailList> resultingColumns = new ArrayList<>();
        for (int i = 0; i < keyColumns.size(); i++) {
            String columnName = keyColumns.getString(i);
            YailList column = getColumn(columnName);
            resultingColumns.add(column);
        }
        return YailList.makeList((List) resultingColumns);
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
    public void notifyDataObservers(YailList key, Object newValue) {
        Iterator<DataSourceChangeListener> it = this.dataSourceObservers.iterator();
        while (it.hasNext()) {
            DataSourceChangeListener dataComponent = it.next();
            dataComponent.onDataSourceValueChange(this, null, this.columns);
        }
    }
}
