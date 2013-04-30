/****************************************************************************
 *
 * Copyright (c) 2009-2013, Linagora
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.studio.splash;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.DisplayAccess;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.splash.BasicSplashHandler;

import com.ebmwebsourcing.petals.studio.PetalsStudioPlugin;
import com.ebmwebsourcing.petals.studio.utils.VersionUtils;

/**
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsSplashHandler extends BasicSplashHandler {

	private int alphaValue = 20;
	private Image img;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.splash.AbstractSplashHandler
	 * #init(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	public void init( final Shell splash ) {
		super.init( splash );

		// Get product properties
		String progressRectString = null;
		String messageRectString = null;
		String foregroundColorString = null;
		IProduct product = Platform.getProduct();
		if( product != null ) {
			progressRectString = product.getProperty( IProductConstants.STARTUP_PROGRESS_RECT );
			messageRectString = product.getProperty( IProductConstants.STARTUP_MESSAGE_RECT );
			foregroundColorString = product.getProperty( IProductConstants.STARTUP_FOREGROUND_COLOR );
		}

		Rectangle progressRect = StringConverter.asRectangle(
					progressRectString,
					new Rectangle(10, 10, 300, 15));
		setProgressRect( progressRect );

		Rectangle messageRect = StringConverter.asRectangle(
					messageRectString,
					new Rectangle(10, 35, 300, 15));
		setMessageRect( messageRect );

		int foregroundColorInteger;
		try {
			if( foregroundColorString != null )
				foregroundColorInteger = Integer.parseInt( foregroundColorString, 16 );
			else
				foregroundColorInteger = 0xD2D7FF; // off white

		} catch( Exception ex ) {
			foregroundColorInteger = 0xD2D7FF; // off white
		}

		setForeground( new RGB(
					(foregroundColorInteger & 0xFF0000) >> 16,
					(foregroundColorInteger & 0xFF00) >> 8,
					foregroundColorInteger & 0xFF));

		final Point buildIdPoint = new Point( 350, 230 );
		ImageDescriptor desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/studio.png" );
		final ImageData imgData = desc.getImageData();

		// Get the version to display
		final StringBuilder sb = new StringBuilder( VersionUtils.getProductVersion( false ));
		if( ! sb.toString().contains( " " ))	// No RC, milestone... Display a prefix.
			sb.insert( 0, "Version " );

		// Paint it
		getContent().addPaintListener( new PaintListener() {

			public void paintControl( PaintEvent e ) {
				e.gc.setForeground( getForeground());
				e.gc.drawText( sb.toString(), buildIdPoint.x, buildIdPoint.y, true);

				imgData.alpha = PetalsSplashHandler.this.alphaValue;
				Image previousImg = PetalsSplashHandler.this.img;
				PetalsSplashHandler.this.img = new Image( e.display, imgData );

				if( previousImg != null ) {
					previousImg.dispose();
					previousImg = null;
				}

				e.gc.drawImage( PetalsSplashHandler.this.img, 233, 175 );
			}
		});

		Thread worker = new Thread() {
			@Override
			public void run() {
				DisplayAccess.accessDisplayDuringStartup();
				getContent().getDisplay().syncExec( new Runnable() {
					public void run() {

						// 235: 255 - 20
						while( PetalsSplashHandler.this.alphaValue < 235 ) {
							try {
								Thread.sleep( 200 );
							} catch( InterruptedException e ) {
								// nothing
							}

							PetalsSplashHandler.this.alphaValue += 20;
							getContent().redraw();
							getContent().update();
						}
					}
				});
			}
		};
		worker.start();
	}
}
