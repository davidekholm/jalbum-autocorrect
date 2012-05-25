package net.jalbum.filter.auto.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.datadosen.component.RiverLayout;

public class ExtendedSlider extends JPanel implements ActionListener, ChangeListener, FocusListener {

    /**
     * 
     */
    private static final long serialVersionUID = -5260376230055299702L;
    protected JSlider slider;
    protected JTextField textfield;
    protected JButton reset;
    protected int max;
    protected int min;
    protected int value;
    protected int initialValue;
    protected int orientation = JSlider.HORIZONTAL;
    protected String textReset = "Reset";
    protected boolean showReset = true;
    protected Vector<ActionListener> actionListeners;

    public ExtendedSlider() {
    }

    public ExtendedSlider(int orientation) {
        this(JSlider.HORIZONTAL, 0, 100, 0);
    }

    public ExtendedSlider(int min, int max) {
        this(JSlider.HORIZONTAL, min, max, (min + max) / 2);
    }

    public ExtendedSlider(int min, int max, int value) {
        this(JSlider.HORIZONTAL, min, max, value);
    }

    public ExtendedSlider(int orientation, int min, int max, int value) {
        this.min = min;
        this.max = max;
        this.value = value;
        this.initialValue = value;
        actionListeners = new Vector<ActionListener>();
        slider = new JSlider(min, max, value);
        if (orientation == JSlider.VERTICAL) {
            this.orientation = JSlider.VERTICAL;
            slider.setOrientation(orientation);
        } else {

            this.orientation = JSlider.HORIZONTAL;
            slider.setOrientation(orientation);
        }
        textfield = new JTextField(3);
        textfield.setText(Integer.toString(slider.getValue()));
        reset = new JButton(textReset);

        slider.addChangeListener(this);
        textfield.addActionListener(this);
        textfield.addFocusListener(this);
        reset.addActionListener(this);

        slider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setMinorTickSpacing((max - min) / 8);
        slider.setPaintLabels(true);

        this.setLayout(new RiverLayout(0, 0));
        this.add(slider);
        if (orientation == JSlider.VERTICAL) {
            this.add("br", textfield);
            if (showReset) {
                this.add("br", reset);
            }
        } else {
            this.add("", textfield);
            if (showReset) {
                this.add(reset);
            }
        }
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        for (Component c : getComponents()) {
            c.setEnabled(b);
        }
    }

    public void addActionListener(ActionListener al) {
        actionListeners.add(al);
    }

    public ActionListener[] getActionListeners() {
        return (ActionListener[]) actionListeners.toArray();
    }

    public void postActionEvent() {
        for (int i = 0; i < actionListeners.size(); i++) {
            actionListeners.get(i).actionPerformed(new ActionEvent(this, 0, ""));
        }
    }

    public void removeActionListener(ActionListener l) {
        actionListeners.remove(l);
    }

    public JTextField getTextField() {
        return textfield;
    }

    public JSlider getSlider() {
        return slider;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setResetText(String resetText) {
        this.textReset = resetText;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textfield) {
            readTextfieldValue();
        } else if (e.getSource() == reset) {
            reset();
            postActionEvent();
        }

    }

    private void readTextfieldValue() {
        try {
            int value = Integer.parseInt(textfield.getText());
            if (value < min) {
                value = min;
            } else if (value > max) {
                value = max;
            }
            this.value = value;
            textfield.setText(Integer.toString(this.value));
            slider.setValue(this.value);
        } catch (Exception ex) {
            textfield.setText(Integer.toString(slider.getValue()));
        }
    }

    public void reset() {
        textfield.setText(Integer.toString(this.initialValue));
        slider.setValue(this.initialValue);
    }

    public void removeResetButton() {
        this.remove(reset);
    }

    public void stateChanged(ChangeEvent arg0) {
        if (arg0.getSource() == slider) {
            value = slider.getValue();
            textfield.setText(Integer.toString(value));
            postActionEvent();
        }

    }

    public void setShowReset(boolean showReset) {
        this.showReset = showReset;
    }

    public JTextField getTextfield() {
        return textfield;
    }

    public void setTextfield(JTextField textfield) {
        this.textfield = textfield;
    }

    public JButton getReset() {
        return reset;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getTextReset() {
        return textReset;
    }

    public void setTextReset(String textReset) {
        this.textReset = textReset;
    }

    public boolean isShowReset() {
        return showReset;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }

    public void setActionListeners(Vector<ActionListener> actionListeners) {
        this.actionListeners = actionListeners;
    }

    public void setSliderWidth(int width) {
        slider.setPreferredSize(new Dimension(width, slider.getPreferredSize().height));
    }

    public void setSliderHeight(int height) {
        slider.setPreferredSize(new Dimension(slider.getPreferredSize().width, height));
    }

    public int getSliderWidth() {
        return slider.getPreferredSize().width;
    }

    public int getSliderHeight() {
        return slider.getPreferredSize().height;
    }

    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == textfield) {
            readTextfieldValue();
        }
    }
}
