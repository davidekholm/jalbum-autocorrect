package net.jalbum.filters.auto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import se.datadosen.jalbum.Msg;
import se.datadosen.component.RiverLayout;

/**
 *
 * @author Sarah Schölzel
 */
public class AutoCorrectionControl extends FilterControl {

    private AutoCorrectionPlugin autoCorrPlugin;
    private JCheckBox enhanceAll;
    private JCheckBox contrast;
    private JCheckBox levels;
    private JCheckBox colors;
    
    /**
     * Constructor
     * @param plugin 
     */
    public AutoCorrectionControl(AutoCorrectionPlugin plugin) {
        this.autoCorrPlugin = plugin;
        addControls();
    }

    /**
     * initializes and adds components to the Panel 
     */
    private void addControls() {

        contrast = new JCheckBox(Msg.getString(this, "filters.auto.controls.contrast"));
        contrast.setToolTipText(Msg.getString(this, "filters.auto.controls.contrast.tooltip"));
        contrast.setSelected(true);
        contrast.setEnabled(false);
        contrast.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                autoCorrPlugin.setContrast(contrast.isSelected());
                autoCorrPlugin.renderImage();
            }
        });

        levels = new JCheckBox(Msg.getString(this, "filters.auto.controls.levels"));
        levels.setToolTipText(Msg.getString(this, "filters.auto.controls.levels.tooltip"));
        levels.setSelected(true);
        levels.setEnabled(false);
        levels.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                autoCorrPlugin.setLevels(levels.isSelected());
                autoCorrPlugin.renderImage();
            }
        });

        colors = new JCheckBox(Msg.getString(this, "filters.auto.controls.colors"));
        colors.setToolTipText(Msg.getString(this, "filters.auto.controls.colors.tooltip"));
        colors.setSelected(true);
        colors.setEnabled(false);
        colors.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                autoCorrPlugin.setColors(colors.isSelected());
                autoCorrPlugin.renderImage();
            }
        });

        
        
        enhanceAll = new JCheckBox(Msg.getString(this, "filters.auto.controls.ownsettings"));
        enhanceAll.setToolTipText(Msg.getString(this, "filters.auto.controls.ownsettings.tooltip"));
        enhanceAll.setSelected(true);
        enhanceAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                boolean b = enhanceAll.isSelected();
                contrast.setSelected(b);
                levels.setSelected(b);
                colors.setSelected(b);
                contrast.setEnabled(!b);
                levels.setEnabled(!b);
                colors.setEnabled(!b);
                colors.setToolTipText(Msg.getString(this, "filters.auto.controls.colors.tooltip"));
                if (!autoCorrPlugin.isColorsPossible() && !b) {
                    colors.setEnabled(false);
                    colors.setToolTipText(Msg.getString(this, "filters.auto.controls.tooltip.false"));
                }
                autoCorrPlugin.setEnhanceAll(b);
                autoCorrPlugin.renderImage();
            }
        });
        
        JPanel controlPanel = new JPanel(new RiverLayout());
        controlPanel.add( colors);
        controlPanel.add("br", contrast);
        controlPanel.add("br ", levels);
        add("br", enhanceAll);
        add("br", controlPanel);
    }
}
