package net.jalbum.filters.auto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import se.datadosen.component.ControlPanel;

public class FilterControl extends ControlPanel implements ActionListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7656131153335252486L;
	protected BasicFilter basicFilter;

	protected final static int SLIDER_WIDTH = 132;

	public FilterControl() {
	}

	protected void init(final BasicFilter basicFilter) {
		this.basicFilter = basicFilter;
		// add various listeners
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addKeyListener(basicFilter);
		this.addMouseListener(this);
	}

	public void actionPerformed(final ActionEvent e) {
	}

	public void keyPressed(final KeyEvent e) {
	}

	public void keyReleased(final KeyEvent e) {
	}

	public void keyTyped(final KeyEvent e) {
	}

	public void mouseClicked(final MouseEvent e) {
		// Make JInstantTextFields lose focus and trigger updates
		this.requestFocus();
	}

	public void mouseEntered(final MouseEvent e) {
	}

	public void mouseExited(final MouseEvent e) {
	}

	public void mousePressed(final MouseEvent e) {
	}

	public void mouseReleased(final MouseEvent e) {
	}

	public BasicFilter getBasicFilter() {
		return this.basicFilter;
	}

	public void setBasicFilter(final BasicFilter basicFilter) {
		this.basicFilter = basicFilter;
	}
}
