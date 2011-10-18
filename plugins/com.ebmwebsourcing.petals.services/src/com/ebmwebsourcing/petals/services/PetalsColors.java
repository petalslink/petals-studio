package com.ebmwebsourcing.petals.services;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Color set of PetalsLink, from http://extranet.petalslink.com/display/interne/Charte+graphique+Petals
 * @author Mickael Istria
 *
 */
public class PetalsColors {

	private static Color purple;
	private static Color orange;
	private static Color grey;
	private static Color darkPurple;
	private static Color lightPurple;
	private static Color lightOrange;
	private static Color lightGrey;
	
	public synchronized static Color getPurple() {
		if (purple == null) {
			purple = new Color(Display.getDefault(), 120, 49, 135);
		}
		return purple;
	}

	public synchronized static Color getOrange() {
		if (orange == null) {
			orange = new Color(Display.getDefault(), 228, 100, 37);
		}
		return orange;
	}
	
	public synchronized static Color getGrey() {
		if (grey == null) {
			grey = new Color(Display.getDefault(), 51, 51, 51);
		}
		return grey;
	}
	
	public synchronized static Color getDarkPurple() {
		if (darkPurple == null) {
			darkPurple = new Color(Display.getDefault(), 73, 37, 98);
		}
		return darkPurple;
	}
	
	public synchronized static Color getLightPurple() {
		if (lightPurple == null) {
			lightPurple = new Color(Display.getDefault(), 193, 175, 198);
		}
		return lightPurple;
	}

	public synchronized static Color getLightOrange() {
		if (lightOrange == null) {
			lightOrange = new Color(Display.getDefault(), 250, 205, 170);
		}
		return lightOrange;
	}
	
	public synchronized static Color getLightGrey() {
		if (lightGrey == null) {
			lightGrey = new Color(Display.getDefault(), 206, 206, 206);
		}
		return lightGrey;
	}
}
