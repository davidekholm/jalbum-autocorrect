package net.jalbum.filter.auto.util;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import net.jalbum.filterManager.FilterManagerInterface;
import net.jalbum.filterManager.FilterProperties;
import net.jalbum.filterManager.JAlbumImageFilter;
import net.jalbum.filterManager.FilterProperties.FilterCategory;

/**
 * 
 * @author David Fichtmueller
 * @version 1.0
 * 
 * This is a basic image filter, which will not change the image, but can be inherited by other filters and handles most of the basic 
 * functionality, so the author of the inheriting filter doesn't need to take care of that.
 *
 */
public class BasicFilter implements JAlbumImageFilter {
	private static final long serialVersionUID = 4118395418676584221L;

	protected String name = "";
	protected String shortName = "";
	protected ImageIcon icon = null;
	protected String author = "";
	protected String version = "";
	protected String description = "";
	protected String help = "";
	protected String other = "";
	protected FilterCategory category = FilterCategory.OTHER;
	protected boolean prescale = true;
	protected boolean postscale = true;
	protected BufferedImage inputImage;
	protected BufferedImage outputImage;
	protected FilterManagerInterface filterManager;
	protected int width;
	protected int height;

	/**
	 * The default constructor in which the strings for the filter info, like "name", "version" or "author" are set.
	 */
	public BasicFilter() {
		this.name = "JAlbum Image Filter";
		this.author = "David Fichtmueller";
		this.version = "1.0";
		this.description = "This is the default filter class, all other filters must inherite from.";
		this.prescale = true;
		this.postscale = true;
	}

	/**
	 * This method is called by the filter manager after creating the filter and before getting its controls or setting image. It is for
	 * initializing objects which can not be initialized in the constructor. 
	 */
	public void init() {
	}

	public JPanel getControls() {
		return null;
	}

	public void setPreviewImage(final BufferedImage bi) {
		this.inputImage = this.cloneBufferedImage(bi);
		this.width = this.inputImage.getWidth();
		this.height = this.inputImage.getHeight();
		this.renderPreview();
	}

	/**
	 * Filters that doesn't affect original image can override this one to gain speed
	 * 
	 */
	protected BufferedImage cloneBufferedImage(final BufferedImage bi) {
		return this.filterManager.cloneBufferedImage(bi);
	}

	public void renderImage() {
		this.outputImage = this.renderImage(this.cloneBufferedImage(this.inputImage));
		this.filterManager.setGUIImage(this.outputImage);
	}

	public BufferedImage renderImage(final BufferedImage bi) {

		// inheriting filters should apply their changes to the image here

		return bi;
	}

	public void renderPreview() {
		this.outputImage = this.renderImage(this.cloneBufferedImage(this.inputImage));
		this.paint(this.outputImage.getGraphics());
		this.filterManager.setGUIImage(this.outputImage);
	}

	public void paint(final Graphics g) {
	}

	public void dispose() {
		this.inputImage = null;
		this.outputImage = null;
		this.filterManager = null;
	}

	public String getName() {
		return this.name;
	}

	public FilterProperties getProperties() {
		final FilterProperties properties = new FilterProperties();
		properties.setAuthor(this.author);
		properties.setCategory(this.category);
		properties.setDescription(this.description);
		properties.setHelp(this.help);
		properties.setIcon(this.icon);
		properties.setOther(this.other);
		properties.setShortName(this.shortName);
		properties.setVersion(this.version);
		return properties;
	}

	public boolean isPrescale() {
		return this.prescale;
	}

	public boolean isPostscale() {
		return this.postscale;
	}

	public void setFilterManager(final FilterManagerInterface filterManager) {
		this.filterManager = filterManager;
	}

	public void keyPressed(final KeyEvent e) {
	}

	public void keyReleased(final KeyEvent e) {
	}

	public void keyTyped(final KeyEvent e) {
	}

	public void mouseClicked(final MouseEvent e) {
	}

	public void mouseEntered(final MouseEvent e) {
	}

	public void mouseExited(final MouseEvent e) {
	}

	public void mousePressed(final MouseEvent e) {
	}

	public void mouseReleased(final MouseEvent e) {
	}

	public void mouseDragged(final MouseEvent e) {
	}

	public void mouseMoved(final MouseEvent e) {
	}

	public void mouseWheelMoved(final MouseWheelEvent e) {
	}

}
