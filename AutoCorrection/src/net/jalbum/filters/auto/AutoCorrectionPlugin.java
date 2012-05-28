package net.jalbum.filters.auto;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.jalbum.filterManager.FilterProperties.FilterCategory;
import se.datadosen.jalbum.Msg;

/**
 *
 * @author Sarah Schölzel
 */
public class AutoCorrectionPlugin extends BasicFilter {


 
    private AutoCorrectionControl autoCorrControl;
    private AutoCorrectionFilter autoCorrFilter;

    /**
     * Constructor
     */
    public AutoCorrectionPlugin() {
        this.name = Msg.getString(this, "filters.auto.name");
        this.shortName = Msg.getString(this, "filters.auto.shortName");
        this.icon = new ImageIcon(this.getClass().getResource("auto.png"));
        this.author = "Sarah Schölzel";
        this.version = "1.0";
        this.description = Msg.getString(this, "filters.auto.description");
        this.category = FilterCategory.ADVANCED;
        this.prescale = false;
        this.postscale = true;
        this.autoCorrControl = new AutoCorrectionControl(this);
        this.autoCorrFilter = new AutoCorrectionFilter();
    }

    @Override
    public JPanel getControls() {
        return this.autoCorrControl;
    }

    @Override
    public BufferedImage renderImage(final BufferedImage bi) {
        return autoCorrFilter.filter(bi, null);
        
    }

    public void setContrast(Boolean contrast) {
        autoCorrFilter.setContrast(contrast);
    }

    public void setLevels(Boolean levels) {
        autoCorrFilter.setLevels(levels);
    }

    public void setColors(boolean colors) {
        autoCorrFilter.setColors(colors);
    }

    public void setEnhanceAll(boolean b) {
        setContrast(b);
        setLevels(b);
        setColors(b);
    }

    public boolean isColorsPossible() {
        return autoCorrFilter.isColorsPossible();
    }

}
