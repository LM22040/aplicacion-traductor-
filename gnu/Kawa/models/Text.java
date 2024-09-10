package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.lists.CharBuffer;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class Text extends Model implements Viewable, Serializable {
    public final CharBuffer buffer;

    public Text() {
        this("");
    }

    public Text(String text) {
        CharBuffer charBuffer = new CharBuffer(100);
        this.buffer = charBuffer;
        charBuffer.gapEnd = 99;
        charBuffer.getArray()[charBuffer.gapEnd] = '\n';
        setText(text);
    }

    @Override // gnu.kawa.models.Viewable
    public void makeView(Display display, Object where) {
        display.addText(this, where);
    }

    public String getText() {
        int len = this.buffer.size() - 1;
        int start = this.buffer.getSegment(0, len);
        return new String(this.buffer.getArray(), start, len);
    }

    public void setText(String text) {
        int size = this.buffer.size() - 1;
        if (size > 0) {
            this.buffer.delete(0, size);
        }
        this.buffer.insert(0, text, false);
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }

    public CharBuffer getBuffer() {
        return this.buffer;
    }
}
