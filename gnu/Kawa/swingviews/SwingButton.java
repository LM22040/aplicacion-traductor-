package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Button;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import java.awt.Color;
import javax.swing.JButton;

/* loaded from: classes2.dex */
public class SwingButton extends JButton implements ModelListener {
    Button model;

    public SwingButton(Button model) {
        super(model.getText());
        setModel(new SwModel(model));
        this.model = model;
        Object action = model.getAction();
        if (action != null) {
            addActionListener(SwingDisplay.makeActionListener(action));
        }
        model.addListener(this);
        Color fg = model.getForeground();
        if (fg != null) {
            super.setBackground(fg);
        }
        Color bg = model.getBackground();
        if (bg != null) {
            super.setBackground(bg);
        }
    }

    public void setText(String text) {
        Button button = this.model;
        if (button == null) {
            super.setText(text);
        } else {
            button.setText(text);
        }
    }

    public void setForeground(Color fg) {
        Button button = this.model;
        if (button == null) {
            super.setForeground(fg);
        } else {
            button.setForeground(fg);
        }
    }

    public void setBackground(Color bg) {
        Button button = this.model;
        if (button == null) {
            super.setBackground(bg);
        } else {
            button.setBackground(bg);
        }
    }

    @Override // gnu.kawa.models.ModelListener
    public void modelUpdated(Model model, Object key) {
        Button button;
        Button button2;
        Button button3;
        if (key == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model == (button3 = this.model)) {
            super.setText(button3.getText());
            return;
        }
        if (key == "foreground" && model == (button2 = this.model)) {
            super.setForeground(button2.getForeground());
        } else if (key == "background" && model == (button = this.model)) {
            super.setBackground(button.getBackground());
        }
    }
}
