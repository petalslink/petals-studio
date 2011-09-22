/****************************************************************************
 *
 * Copyright (c) 2009-2011, EBM WebSourcing
 *
 * This source code is available under agreement available at
 * http://www.petalslink.com/legal/licenses/petals-studio
 *
 * You should have received a copy of the agreement along with this program.
 * If not, write to EBM WebSourcing (4, rue Amelie - 31200 Toulouse, France).
 *
 *****************************************************************************/

package com.ebmwebsourcing.petals.studio.welcome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.part.IntroPart;

import com.ebmwebsourcing.petals.studio.PetalsStudioPlugin;
import com.ebmwebsourcing.petals.studio.utils.Base64;
import com.ebmwebsourcing.petals.studio.utils.SwtJFaceUtils;
import com.ebmwebsourcing.petals.studio.utils.VersionUtils;

/**
 * This class creates the welcome page GUI.
 * @author Vincent Zurczak - EBM WebSourcing
 */
public class PetalsStudioWelcomePage extends IntroPart implements IPartListener {

	private final static String EMAIL_PATTERN = "[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*";
	private final static int TEXT_FIELD_WIDTH_HINT = 260;

	private Image backgroundImage, logoImage, iconImage;
	private Image validImg, warnedImg;
	private Image eOkButton, eFinishButton, dOkButton;

	private Font titleFont, subTitleFont;
	private Color orangeColor, purpleColor;

	private String email, name, company, phone, language;
	private boolean subsNews, subsNotif, subsTain;

	private String proxyHost, proxyUser, proxyPassword;
	private int proxyPort;


	/**
	 * Constructor.
	 */
	public PetalsStudioWelcomePage() {
		try {
			ImageDescriptor desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/background_theme.png" );
			this.backgroundImage = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/background_theme_logo.png" );
			this.logoImage = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/pstudio_16x16_2.png" );
			this.iconImage = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/obj16/Warning.gif" );
			this.warnedImg = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/obj16/Validated.gif" );
			this.validImg = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/bouton_eclipse_contact_ok.png" );
			this.eOkButton = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/bouton_eclipse_contact_finish.png" );
			this.eFinishButton = desc.createImage();

			desc = PetalsStudioPlugin.getImageDescriptor( "icons/prod/bouton_eclipse_contact_ok_gris.png" );
			this.dOkButton = desc.createImage();

		} catch( Exception e ) {
			PetalsStudioPlugin.log( e, IStatus.WARNING );
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#dispose()
	 */
	@Override
	public void dispose() {

		if( this.eOkButton != null )
			this.eOkButton.dispose();

		if( this.dOkButton != null )
			this.dOkButton.dispose();

		if( this.eFinishButton != null )
			this.eFinishButton.dispose();

		if( this.backgroundImage != null )
			this.backgroundImage.dispose();

		if( this.logoImage != null )
			this.logoImage.dispose();

		if( this.iconImage != null )
			this.iconImage.dispose();

		if( this.warnedImg != null )
			this.warnedImg.dispose();

		if( this.validImg != null )
			this.validImg.dispose();

		if( this.titleFont != null )
			this.titleFont.dispose();

		if( this.subTitleFont != null )
			this.subTitleFont.dispose();

		if( this.orangeColor != null )
			this.orangeColor.dispose();

		if( this.purpleColor != null )
			this.purpleColor.dispose();

		getIntroSite().getPage().removePartListener( this );
		super.dispose();
	}


	/**
	 * Creates the content of the home page.
	 * @param container the container of the content home page
	 */
	@Override
	public void createPartControl( Composite container ) {

		PetalsStudioPlugin.getDefault().setWelcomePageAlreadyShown( true );
		getIntroSite().getPage().addPartListener( this );

		final ScrolledComposite scrollComposite = new ScrolledComposite( container, SWT.V_SCROLL | SWT.H_SCROLL );
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		scrollComposite.setLayout( layout );
		scrollComposite.setLayoutData( new GridData( GridData.FILL_BOTH ));

		final Composite outerContainer = new Composite( scrollComposite, SWT.NONE );
		outerContainer.setLayout( new GridLayout());
		outerContainer.setBackgroundMode( SWT.INHERIT_DEFAULT );

		layout = new GridLayout();
		layout.marginWidth = 20;
		outerContainer.setLayout( layout );
		outerContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		scrollComposite.setContent( outerContainer );
		scrollComposite.setExpandVertical( true );
		scrollComposite.setExpandHorizontal( true );
		scrollComposite.addControlListener( new ControlAdapter() {
			@Override
			public void controlResized( ControlEvent e ) {
				scrollComposite.setMinSize( outerContainer.computeSize( SWT.DEFAULT, SWT.DEFAULT ));
			}
		});


		// Get the group to display
		final Label logoLabel = new Label( outerContainer, SWT.NONE );
		logoLabel.setImage( this.logoImage );

		GridData layoutData = new GridData( SWT.CENTER, SWT.CENTER, true, false );
		logoLabel.setLayoutData( layoutData );

		FontData[] fs = logoLabel.getFont().getFontData();
		fs = SwtJFaceUtils.changeFontDataSize( fs, 14 );
		fs = SwtJFaceUtils.getModifiedFontData( fs, SWT.BOLD );
		this.titleFont = new Font( logoLabel.getDisplay(), fs );
		this.purpleColor = new Color( logoLabel.getDisplay(), 115, 46, 131 );

		if( RegistrationManager.needsRegistration())
			addRegistrationWidgetPart1( outerContainer );
		else
			addPresentationWidget( outerContainer );

		// Resize listener
		final Listener resizeListener = new Listener() {
			Image oldImage;

			public void handleEvent( Event event ) {

				// Background image
				if( this.oldImage != null )
					this.oldImage.dispose();

				Rectangle rect = outerContainer.getClientArea();
				Image scaled = new Image( Display.getDefault(), rect.width, rect.height );
				GC gc = new GC( scaled );
				gc.setAntialias( SWT.ON );
				gc.setInterpolation( SWT.HIGH );
				gc.drawImage(
							PetalsStudioWelcomePage.this.backgroundImage,
							0, 0,
							PetalsStudioWelcomePage.this.backgroundImage.getBounds().width,
							PetalsStudioWelcomePage.this.backgroundImage.getBounds().height,
							0, 0,
							rect.width, rect.height );

				gc.dispose();
				outerContainer.setBackgroundImage( scaled );
				this.oldImage = scaled;

				// Update the location of the label
				GridData layoutData = (GridData) logoLabel.getLayoutData();
				layoutData.verticalIndent = rect.height / 7;
				logoLabel.setLayoutData( layoutData );
			}
		};
		outerContainer.addListener( SWT.Resize, resizeListener );
	}


	/**
	 * Adds the registration group.
	 * @param outerContainer
	 */
	private void addRegistrationWidgetPart1( final Composite outerContainer ) {

		Composite container = new Composite( outerContainer, SWT.BORDER );
		container.setBackground( outerContainer.getDisplay().getSystemColor( SWT.COLOR_WHITE ));
		container.setLayout( new GridLayout());

		GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
		layoutData.verticalIndent = 20;
		container.setLayoutData( layoutData );

		// Restore values ?
		try {
			if( this.email == null ) {
				RegistrationBean bean = RegistrationManager.getInstance().restoreRegistrationData();
				this.email = bean.getEmail();
				this.company = bean.getCompany();
				this.name = bean.getName();
				this.phone = bean.getPhone();
				this.language = bean.getLanguage();

				this.proxyHost = bean.getProxyHost();
				this.proxyPassword = bean.getProxyPassword();
				this.proxyPort = bean.getProxyPort();
				this.proxyUser = bean.getProxyUser();
			}

		} catch( IOException e1 ) {
			// nothing

		} catch( GeneralSecurityException e1 ) {
			// nothing
		}

		// Populate container
		Label label = new Label( container, SWT.NONE );
		label.setText( "Let us know about you!" );
		label.setFont( this.titleFont );
		label.setForeground( this.purpleColor );

		layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 10;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.NONE );
		label.setText( "We are willing to know you." );

		layoutData = new GridData();
		layoutData.verticalIndent = 14;
		layoutData.horizontalIndent = 20;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.NONE );
		label.setText( "Please, take two minutes to fill-in this two-step form." );

		layoutData = new GridData();
		layoutData.horizontalIndent = 20;
		label.setLayoutData( layoutData );

		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		layout.marginHeight = 10;
		layout.marginWidth = 20;
		layout.marginRight = 7;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));

		// OK "button"
		final Label okLabel = new Label( container, SWT.NONE );
		okLabel.setImage( this.dOkButton );
		okLabel.setToolTipText( "Move on the next step" );
		okLabel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown( MouseEvent e ) {

				if( PetalsStudioWelcomePage.this.eOkButton.equals( okLabel.getImage())) {
					cleanContainer( outerContainer );
					addRegistrationWidgetPart2( outerContainer );
					outerContainer.layout();
				}
			}
		});

		layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 10;
		okLabel.setLayoutData( layoutData );

		// No, thanks...
		Link link = new Link( container, SWT.NONE );
		link.setText( "<A>No, thanks. I will register later. \u00bb </A>" );

		layoutData = new GridData( SWT.END, SWT.END, true, false );
		layoutData.verticalIndent = 23;
		link.setLayoutData( layoutData );
		link.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				closeWelComePage();
			}
		});

		// Populate subContainer
		// Email
		label = new Label( subContainer, SWT.NONE );
		label.setText( "Email*:" );

		Text text = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );

		// No need to set the width hint on the other text fields
		// They will fill the remaining space anyway
		layoutData.widthHint = TEXT_FIELD_WIDTH_HINT;
		text.setLayoutData( layoutData );
		final Label emailDecoratorLabel = new Label( subContainer, SWT.NONE );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				PetalsStudioWelcomePage.this.email = ((Text) e.widget).getText().trim();

				if( PetalsStudioWelcomePage.this.email.length() == 0 ) {
					emailDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					emailDecoratorLabel.setToolTipText( "Please fill-in your email address." );
				}
				else if( PetalsStudioWelcomePage.this.email.length() > 79 ) {
					emailDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					emailDecoratorLabel.setToolTipText( "Your email address can not exceed 80 characters." );
				}
				else if( ! PetalsStudioWelcomePage.this.email.matches( EMAIL_PATTERN )) {
					emailDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					emailDecoratorLabel.setToolTipText( "This email address is not valid." );
				}
				else {
					emailDecoratorLabel.setImage( PetalsStudioWelcomePage.this.validImg );
					emailDecoratorLabel.setToolTipText( null );
				}

				emailDecoratorLabel.update();
				emailDecoratorLabel.getParent().layout();
				checkNextButton( okLabel );
			}
		});

		if( this.email != null && this.email.trim().length() > 0 )
			text.setText( this.email );

		// Company
		label = new Label( subContainer, SWT.NONE );
		label.setText( "Company / Organization*:" );

		text = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		final Label companyDecoratorLabel = new Label( subContainer, SWT.NONE );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				PetalsStudioWelcomePage.this.company = ((Text) e.widget).getText().trim();

				if( PetalsStudioWelcomePage.this.company.length() == 0 ) {
					companyDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					companyDecoratorLabel.setToolTipText( "Please, fill-in your company or organization name (write none if you do not have one)." );
				}
				else if( PetalsStudioWelcomePage.this.company.length() > 39 ) {
					companyDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					companyDecoratorLabel.setToolTipText( "Your company name can not exceed 40 characters." );
				}
				else {
					companyDecoratorLabel.setImage( PetalsStudioWelcomePage.this.validImg );
					companyDecoratorLabel.setToolTipText( null );
				}

				companyDecoratorLabel.update();
				companyDecoratorLabel.getParent().layout();
				checkNextButton( okLabel );
			}
		});

		if( this.company != null && this.company.trim().length() > 0 )
			text.setText( this.company );

		// Name
		label = new Label( subContainer, SWT.NONE );
		label.setText( "Name (First, Last)*:" );

		text = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		final Label nameDecoratorLabel = new Label( subContainer, SWT.NONE );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {

				PetalsStudioWelcomePage.this.name = ((Text) e.widget).getText().trim();
				if( PetalsStudioWelcomePage.this.name.length() == 0 ) {
					nameDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					nameDecoratorLabel.setToolTipText( "Please, fill-in your name." );
				}
				else if( PetalsStudioWelcomePage.this.name.length() > 39 ) {
					nameDecoratorLabel.setImage( PetalsStudioWelcomePage.this.warnedImg );
					nameDecoratorLabel.setToolTipText( "Your name should not be longer than 40 characters (you can use initials)." );
				}
				else {
					nameDecoratorLabel.setImage( PetalsStudioWelcomePage.this.validImg );
					nameDecoratorLabel.setToolTipText( null );
				}

				nameDecoratorLabel.update();
				nameDecoratorLabel.getParent().layout();
				checkNextButton( okLabel );
			}
		});

		if( this.name != null && this.name.trim().length() > 0 )
			text.setText( this.name );

		// Phone
		label = new Label( subContainer, SWT.NONE );
		label.setText( "Phone:" );

		text = new Text( subContainer, SWT.SINGLE | SWT.BORDER );
		text.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));
		new Label( subContainer, SWT.NONE );

		text.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent e ) {
				PetalsStudioWelcomePage.this.phone = ((Text) e.widget).getText();
			}
		});

		if( this.phone != null )
			text.setText( this.phone );

		// Favorite language
		label = new Label( subContainer, SWT.NONE );
		label.setText( "Favorite language:" );

		final String[] languages = new String[] {
					"English", "Fran\u00e7ais", "Espan\u00f5l",
					"Deutsche", "Portug\u00eas", "Chinese"
		};

		Combo combo = new Combo( subContainer, SWT.BORDER | SWT.READ_ONLY );
		combo.setLayoutData( new GridData( 120, SWT.DEFAULT ));
		combo.setItems( languages );
		combo.setVisibleItemCount( languages.length );
		combo.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				int selection = ((Combo) e.widget).getSelectionIndex();
				if( selection >= 0 )
					PetalsStudioWelcomePage.this.language = languages[ selection ];
			}
		});

		if( this.language == null || this.language.trim().length() == 0 ) {
			combo.select( 0 );
			combo.notifyListeners( SWT.Selection, new Event());
		}
		else {
			int index = combo.indexOf( this.language );
			combo.select( index );
		}
	}


	/**
	 * Enables or disables the first form label.
	 * @param nextButton
	 */
	private void checkNextButton( Label label ) {

		boolean enable = this.email != null
		&& this.email.length() > 0
		&& this.email.length() <= 79
		&& this.email.matches( EMAIL_PATTERN )
		&& this.company != null
		&& this.company.length() > 0
		&& this.company.length() <= 39
		&& this.name != null
		&& this.name.length() > 0
		&& this.name.length() <= 39;

		if( enable )
			label.setImage( this.eOkButton );
		else
			label.setImage( this.dOkButton );
	}


	/**
	 * Adds the registration group.
	 * @param outerContainer
	 */
	private void addRegistrationWidgetPart2( final Composite outerContainer ) {

		Composite container = new Composite( outerContainer, SWT.BORDER );
		container.setBackground( outerContainer.getDisplay().getSystemColor( SWT.COLOR_WHITE ));
		container.setLayout( new GridLayout());

		GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
		layoutData.verticalIndent = 20;
		container.setLayoutData( layoutData );

		// Populate the container
		Label label = new Label( container, SWT.WRAP );
		label.setText( "Stay tuned!" );
		label.setFont( this.titleFont );
		label.setForeground( this.purpleColor );

		layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 10;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.WRAP );
		label.setText( "Would you be interested in receiving our newsletters?" );

		layoutData = new GridData();
		layoutData.verticalIndent = 14;
		layoutData.horizontalIndent = 20;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.WRAP );
		label.setText( "Our goal is not to spam you. You can unsubscribe anytime." );

		layoutData = new GridData();
		layoutData.horizontalIndent = 20;
		label.setLayoutData( layoutData );

		Composite subContainer = new Composite( container, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginTop = 10;
		layout.marginWidth = 20;
		layout.marginRight = 7;
		layout.verticalSpacing = 3;
		layout.marginLeft = 22;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_BOTH ));


		// Newsletter
		Button button = new Button( subContainer, SWT.CHECK );
		button.setText( "Send me Petals Newsletter, every two-month." );
		button.setSelection( this.subsNews );
		button.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsNews = ((Button) e.widget).getSelection();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsNews = ((Button) e.widget).getSelection();
			}
		});


		// Software updates
		button = new Button( subContainer, SWT.CHECK );
		button.setText( "Notify me about Petals Software updates and releases." );
		button.setSelection( this.subsNotif );
		button.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsNotif = ((Button) e.widget).getSelection();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsNotif = ((Button) e.widget).getSelection();
			}
		});

		layoutData = new GridData();
		layoutData.verticalIndent = 5;
		button.setLayoutData( layoutData );


		// Training
		button = new Button( subContainer, SWT.CHECK );
		button.setText( "Inform me when new training sessions are organized." );
		button.setSelection( this.subsTain );
		button.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsTain = ((Button) e.widget).getSelection();
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				PetalsStudioWelcomePage.this.subsTain = ((Button) e.widget).getSelection();
			}
		});

		layoutData = new GridData();
		layoutData.verticalIndent = 5;
		button.setLayoutData( layoutData );


		// Proxy
		subContainer = new Composite( container, SWT.NONE );
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginRight = 7;
		layout.marginLeft = 22;
		subContainer.setLayout( layout );
		subContainer.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		StringBuilder sb = new StringBuilder();
		sb.append( "<form><p>");
		sb.append( "Before completing the registration, you might want to <a>configure proxy settings</a>." );
		sb.append( "<br />Click finish to complete your registration." );
		sb.append( "</p></form>");

		FormText formText = new FormText( subContainer, SWT.NO_FOCUS );
		formText.setText( sb.toString(), true, false );
		formText.setLayoutData( new GridData( GridData.FILL_BOTH ));
		formText.addHyperlinkListener( new HyperlinkAdapter() {
			@Override
			public void linkActivated( HyperlinkEvent e ) {

				ProxyDialog dlg = new ProxyDialog( outerContainer.getShell());
				dlg.setProxyHost( PetalsStudioWelcomePage.this.proxyHost );
				dlg.setProxyPassword( PetalsStudioWelcomePage.this.proxyPassword );
				dlg.setProxyPort( PetalsStudioWelcomePage.this.proxyPort );
				dlg.setProxyUser( PetalsStudioWelcomePage.this.proxyUser );

				if( dlg.open() == Window.OK ) {
					PetalsStudioWelcomePage.this.proxyHost = dlg.getProxyHost();
					PetalsStudioWelcomePage.this.proxyPort = dlg.getProxyPort();
					PetalsStudioWelcomePage.this.proxyUser = dlg.getProxyUser();
					PetalsStudioWelcomePage.this.proxyPassword = dlg.getProxyPassword();
				}
			}
		});


		// Send
		Label finishLabel = new Label( container, SWT.PUSH );
		finishLabel.setImage( this.eFinishButton );
		finishLabel.setToolTipText( "Complete registration" );
		finishLabel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseDown( MouseEvent e ) {

				new Job( "Registration process" ) {
					@Override
					protected IStatus run( IProgressMonitor monitor ) {

						try {
							monitor.beginTask( "Registration in progress...", IProgressMonitor.UNKNOWN );
							register( monitor );

						} finally {
							monitor.done();
							outerContainer.getDisplay().asyncExec( new Runnable() {
								public void run() {
									closeWelComePage();
								}
							});
						}

						return Status.OK_STATUS;
					}
				}.schedule();
			}
		});

		layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 15;
		finishLabel.setLayoutData( layoutData );

		// Back...
		Link link = new Link( container, SWT.NONE );
		link.setText( "<A> \u00ab Back</A>" );

		layoutData = new GridData( SWT.BEGINNING, SWT.END, true, false );
		layoutData.verticalIndent = 20;
		link.setLayoutData( layoutData );
		link.addSelectionListener( new SelectionListener() {
			public void widgetSelected( SelectionEvent e ) {
				widgetDefaultSelected( e );
			}

			public void widgetDefaultSelected( SelectionEvent e ) {
				cleanContainer( outerContainer );
				addRegistrationWidgetPart1( outerContainer );
				outerContainer.layout();
			}
		});
	}


	/**
	 * Removes all the widgets, except the first one, contained in this container.
	 * @param container
	 */
	private void cleanContainer( Composite container ) {

		// Do not kill the first child - the logo label
		boolean first = true;
		for( Control control : container.getChildren()) {
			if( first )
				first = false;
			else
				control.dispose();
		}
	}


	/**
	 * Adds the presentation group.
	 * @param outerContainer
	 */
	private void addPresentationWidget( Composite outerContainer ) {

		Composite container = new Composite( outerContainer, SWT.BORDER );
		container.setBackground( outerContainer.getDisplay().getSystemColor( SWT.COLOR_WHITE ));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 15;
		container.setLayout( layout );

		GridData layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, true );
		layoutData.verticalIndent = 20;
		container.setLayoutData( layoutData );

		// Title & introduction
		Label label = new Label( container, SWT.NONE );
		label.setText( "Welcome into the Petals Studio!" );
		label.setFont( this.titleFont );
		label.setForeground( this.purpleColor );

		layoutData = new GridData( SWT.CENTER, SWT.DEFAULT, true, false );
		layoutData.verticalIndent = 10;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.WRAP );
		label.setText( "Petals Studio is a set of tools for Petals ESB." );

		layoutData = new GridData();
		layoutData.verticalIndent = 15;
		label.setLayoutData( layoutData );

		label = new Label( container, SWT.WRAP );
		label.setText( "Petals ESB is a distributed platform to interconnect heteregenous applications and services." );

		// Features
		Composite section = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginTop = 11;
		section.setLayout( layout );
		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		FontData[] fs = label.getFont().getFontData();
		fs = SwtJFaceUtils.changeFontDataSize( fs, 12 );
		fs = SwtJFaceUtils.getModifiedFontData( fs, SWT.BOLD );
		this.subTitleFont = new Font( label.getDisplay(), fs );

		label = new Label( section, SWT.NONE );
		label.setText( "Features" );
		label.setFont( this.subTitleFont );
		label.setForeground( this.purpleColor );

		label = new Label( section, SWT.SEPARATOR | SWT.HORIZONTAL );
		label.setLayoutData( new GridData( SWT.FILL, SWT.END, true, true ));

		//
		StringBuilder sb = new StringBuilder();
		sb.append( "<form>");
		sb.append( "Petals Studio provides tools to:" );
		sb.append( "<li indent=\"15\">Create and export configurations for Petals components.</li>" );
		sb.append( "<li indent=\"15\">Create and export BPEL processes and SCA applications for Petals ESB.</li>" );
		sb.append( "<li indent=\"15\">Create and debug Petals components.</li>" );
		sb.append( "</form>");

		FormText formText = new FormText( container, SWT.WRAP | SWT.NO_FOCUS );
		formText.setText( sb.toString(), true, false );

		// Links
		this.orangeColor = new Color( label.getDisplay(), 228, 98, 17 );
		section = new Composite( container, SWT.NONE );
		layout = new GridLayout( 2, false );
		layout.marginWidth = 0;
		layout.marginTop = 14;
		section.setLayout( layout );
		section.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ));

		label = new Label( section, SWT.NONE );
		label.setText( "Links" );
		label.setFont( this.subTitleFont );
		label.setForeground( this.purpleColor );

		label = new Label( section, SWT.SEPARATOR | SWT.HORIZONTAL );
		label.setLayoutData( new GridData( SWT.FILL, SWT.END, true, true ));

		//
		sb = new StringBuilder();
		sb.append( "<form>");
		sb.append( "<li indent=\"15\">Find documentation on <a href=\"http://doc.petalslink.com/display/petalsstudio\">Petals Link's wiki</a>.</li>" );
		sb.append( "<li indent=\"15\">Ask community help on <a href=\"http://www.ebmwebsourcing.com/forum/\">Petals Link's forum</a>.</li>" );
		sb.append( "<li indent=\"15\">Get training, expertise and support on <a href=\"http://petalslink.com/en/services\">Petals Link's website</a>.</li>" );
		sb.append( "</form>");

		HyperlinkSettings settings = new HyperlinkSettings( label.getDisplay());
		settings.setActiveForeground( this.orangeColor );
		settings.setForeground( this.orangeColor );
		settings.setHyperlinkUnderlineMode( HyperlinkSettings.UNDERLINE_HOVER );

		formText = new FormText( container, SWT.WRAP | SWT.NO_FOCUS );
		formText.setHyperlinkSettings( settings );
		formText.setText( sb.toString(), true, true );
		formText.addHyperlinkListener( new HyperlinkAdapter() {

			@Override
			public void linkActivated( HyperlinkEvent e ) {
				try {
					URL url = new URL((String) e.getHref());
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL( url );

				} catch( Exception e1 ) {
					PetalsStudioPlugin.log( e1, IStatus.ERROR );
				}
			}

			@Override
			public void linkEntered( HyperlinkEvent e ) {
				IStatusLineManager manager = getIntroSite().getActionBars().getStatusLineManager();
				manager.setMessage( PetalsStudioWelcomePage.this.iconImage, (String) e.getHref());
			}

			@Override
			public void linkExited( HyperlinkEvent e ) {
				IStatusLineManager manager = getIntroSite().getActionBars().getStatusLineManager();
				manager.setMessage( null, "" );
			}
		});
	}


	/**
	 * Registers the product with the form information.
	 * @param monitor
	 */
	private void register( IProgressMonitor monitor ) {

		boolean registrationWorked = false;
		try {
			monitor.subTask( "Preparing the registration message..." );

			//
			// Build the response
			StringBuilder sb = new StringBuilder();

			// Hidden fields
			sb.append( URLEncoder.encode( "FORM_ID", "UTF-8" ) + "=" + URLEncoder.encode( "042281da-6c39-499b-8eef-12676669cf55 ", "UTF-8" ));
			sb.append( "&" );
			sb.append( URLEncoder.encode( "REVIEW_ID", "UTF-8" ) + "=" + URLEncoder.encode( "9948", "UTF-8" ));
			sb.append( "&" );
			sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Lead", "UTF-8" ));
			sb.append( "&" );
			sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Petals Studio", "UTF-8" ));
			sb.append( "&" );
			sb.append( URLEncoder.encode( "CUSTOMFIELD[Origine-categorie]", "UTF-8" ) + "=" + URLEncoder.encode( "Web - download", "UTF-8" ));

			// Version and Build ID
			String studioVersion = "Version: " + VersionUtils.getProductVersion( true );
			studioVersion += " // Build ID: " + VersionUtils.getProductBuildId();

			// User information
			if( this.company == null )
				this.company = "";
			sb.append( "&" );
			sb.append( URLEncoder.encode( "ORGANISATION_NAME", "UTF-8" ) + "=" + URLEncoder.encode( this.company, "UTF-8" ));

			if( this.email == null )
				this.email = "";
			sb.append( "&" );
			sb.append( URLEncoder.encode( "EMAIL", "UTF-8" ) + "=" + URLEncoder.encode( this.email, "UTF-8" ));

			if( this.name == null )
				this.name = "";
			sb.append( "&" );
			sb.append( URLEncoder.encode( "PERSON_NAME", "UTF-8" ) + "=" + URLEncoder.encode( this.name, "UTF-8" ));

			if( this.phone == null )
				this.phone = "";
			sb.append( "&" );
			sb.append( URLEncoder.encode( "PHONE", "UTF-8" ) + "=" + URLEncoder.encode( this.phone, "UTF-8" ));

			if( this.language == null )
				this.language = "English";
			sb.append( "&" );
			sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( this.language, "UTF-8" ));

			// Mailing-lists
			String tags = "";
			if( this.subsNews ) {
				tags += "Info-Newsletter";

				sb.append( "&" );
				sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Info-Newsletter", "UTF-8" ));
			}

			if( this.subsNotif ) {
				if( tags.length() > 0 )
					tags += ", ";
				tags += "Info-Releases";

				sb.append( "&" );
				sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Info-Releases", "UTF-8" ));
			}

			if( this.subsTain ) {
				if( tags.length() > 0 )
					tags += ", ";
				tags += "Info-Trainings";

				sb.append( "&" );
				sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Info-Trainings", "UTF-8" ));
			}

			if( tags.length() == 0 ) {
				tags = "Pas d'email";
				sb.append( "&" );
				sb.append( URLEncoder.encode( "TAG", "UTF-8" ) + "=" + URLEncoder.encode( "Pas d'email", "UTF-8" ));
			}

			tags = "Pr\u00e9f\u00e9rences : " + tags + ", " + this.language;
			sb.append( "&" );
			sb.append( URLEncoder.encode( "NOTE", "UTF-8" ) + "=" );
			sb.append( URLEncoder.encode( "Enregistr\u00e9 depuis Petals Studio " + studioVersion + "\n" + tags, "UTF-8" ));

			//
			// Is there a proxy to use?
			Proxy proxy = null;
			if( this.proxyHost != null )
				proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress( this.proxyHost, this.proxyPort ));

			//
			// Send data
			monitor.subTask( "Sending the registration message..." );
			URL url = new URL( "https://service.capsulecrm.com/service/newlead" );
			HttpURLConnection urlConnection = null;
			if( proxy == null )
				urlConnection = (HttpURLConnection) url.openConnection();
			else
				urlConnection = (HttpURLConnection) url.openConnection( proxy );

			urlConnection.setRequestMethod( "POST" );
			urlConnection.setDoOutput( true );
			urlConnection.setDoInput( true );
			urlConnection.setUseCaches( false );
			urlConnection.setAllowUserInteraction( false );

			if( proxy != null
						&& this.proxyUser != null
						&& this.proxyUser.length() > 0
						&& this.proxyPassword != null ) {
				String encoded = new String( Base64.encode(( this.proxyUser + ":" + this.proxyPassword ).getBytes()));
				urlConnection.setRequestProperty( "Proxy-Authorization", "Basic " + encoded );
			}

			OutputStreamWriter wr = new OutputStreamWriter( urlConnection.getOutputStream());
			wr.write( sb.toString());
			wr.flush();

			// Get the response
			BufferedReader  rd = new BufferedReader( new InputStreamReader( urlConnection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println( line );
			}

			wr.close();
			rd.close();

			// Mark the registration as successful
			registrationWorked = true;

		} catch( Exception e ) {
			PetalsStudioPlugin.log( e, IStatus.ERROR, "The registration of Petals Studio failed." );

		} finally {

			// Keep a trace of the registration
			saveRegistrationTrace( registrationWorked );

			// Indicate to the user if the registration process worked
			if( registrationWorked ) {
				Display.getDefault().syncExec( new Runnable() {
					public void run() {
						MessageDialog.openInformation( new Shell(), "Successful Registration",
						"The registration process concluded successfully." );
					}
				});
			}
			else {
				Display.getDefault().syncExec( new Runnable() {
					public void run() {
						MessageDialog.openError( new Shell(), "Registration Error",
						"The registration process failed, probably because of network problems." );
					}
				});
			}
		}
	}


	/**
	 * Writes a file on the disk which indicates the registration status.
	 * <p>
	 * This file is used to determine if someone registered its version.
	 * </p>
	 *
	 * @param registrationWorked true if the online registration was done, false otherwise
	 */
	private void saveRegistrationTrace( boolean registrationWorked ) {

		boolean traceSaved = false;
		try {
			RegistrationManager mng = RegistrationManager.getInstance();

			// Keep the registration tentative
			RegistrationBean bean = new RegistrationBean();
			bean.setName( this.name );
			bean.setEmail( this.email );
			bean.setCompany( this.company );
			bean.setLanguage( this.language );
			bean.setPhone( this.phone );
			bean.setRegistered( registrationWorked );
			bean.setLastRegisteredVersion( VersionUtils.getProductVersion( true ));

			if( this.proxyHost != null )
				bean.setProxyHost( this.proxyHost );
			if( this.proxyPassword != null )
				bean.setProxyPassword( this.proxyPassword );
			if( this.proxyPort != 0 )
				bean.setProxyPort( this.proxyPort );
			if( this.proxyUser != null )
				bean.setProxyUser( this.proxyUser );

			traceSaved = mng.backupRegistrationData( bean );

		} catch( IOException e ) {
			PetalsStudioPlugin.log( e, IStatus.WARNING );

		} catch( GeneralSecurityException e ) {
			PetalsStudioPlugin.log( e, IStatus.WARNING );
		}

		if( ! traceSaved )
			PetalsStudioPlugin.log( "The registration process trace could not be saved.", IStatus.INFO );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.part.IntroPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.intro.IIntroPart#standbyStateChanged(boolean)
	 */
	public void standbyStateChanged( boolean standby ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener
	 * #partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partActivated( IWorkbenchPart part ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener
	 * #partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partBroughtToTop( IWorkbenchPart part ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener
	 * #partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partClosed( IWorkbenchPart part ) {
		// nothing
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener
	 * #partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partDeactivated( IWorkbenchPart part ) {

		if( "welcome".equalsIgnoreCase( part.getTitle()))
			PlatformUI.getWorkbench().getIntroManager().closeIntro( this );
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener
	 * #partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partOpened( IWorkbenchPart part ) {
		// nothing
	}


	/**
	 * Closes the welcome page and checks if projects need to be migrated.
	 */
	private void closeWelComePage() {

		try {
			PlatformUI.getWorkbench().getIntroManager().closeIntro( PetalsStudioWelcomePage.this );

		} catch( Exception e ) {
			PetalsStudioPlugin.log( e, IStatus.ERROR );
		}
	}
}
