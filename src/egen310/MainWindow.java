package egen310;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Scale;

/**
 * This is the main (and only) class for the prototype 2 graphical user
 * interface. This GUI utilizes the built-in java SWT libraries to build
 * the separate components on a JFrame.
 * 
 * @author Nathan Rubino
 *
 */
public class MainWindow {

	protected Shell shlPrototypeControllerApp;
	private Text txtSlowest;
	private Text txtFasteset;
	private Text txtStatus;

	/**
	 * Launch the application.
	 * 
	 * @param args-command-line arguments
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPrototypeControllerApp.open();
		shlPrototypeControllerApp.layout();
		while (!shlPrototypeControllerApp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPrototypeControllerApp = new Shell();
		shlPrototypeControllerApp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlPrototypeControllerApp.setSize(1280, 720);
		shlPrototypeControllerApp.setText("Cat's Conundrum Prototype 2 App");

		boolean isConnected = false;
		boolean isReversed = false;
		String statusReversed = "STATUS: The Reverse Direction option has been selected.\n "
				+ "The vehicle's controls are now reversed...";
		
		// Variables for the various SWT objects in use
		Menu menu;
		MenuItem mntmNewItem, mntmOptions;
		Label separator, lblSpeedSlider;
		//error console for the app
		Text textSatus;
		//various directional buttons and buttons to connect and reverse
		Button btnForward, btnRight, btnLeft, btnConnect, btnRadioButton, btnBack;

		menu = new Menu(shlPrototypeControllerApp, SWT.BAR);
		shlPrototypeControllerApp.setMenuBar(menu);
		mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("File");
		mntmOptions = new MenuItem(menu, SWT.NONE);
		mntmOptions.setText("Options");

		// create a vertical separator that splits the window in half
		separator = new Label(shlPrototypeControllerApp, SWT.SEPARATOR | SWT.VERTICAL);
		separator.setBounds(640, 0, 2, 651);

		// initialize directional Buttons
		btnForward = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnForward.setBounds(283, 113, 75, 25);
		btnForward.setText("^");
		btnRight = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnRight.setText(">");
		btnRight.setBounds(391, 197, 75, 25);
		btnLeft = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnLeft.setText("<");
		btnLeft.setBounds(169, 197, 75, 25);
		btnBack = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnBack.setText("V");
		btnBack.setBounds(283, 272, 75, 25);

		// initialize the speed slider and its labels
		lblSpeedSlider = new Label(shlPrototypeControllerApp, SWT.NONE);
		lblSpeedSlider.setBounds(936, 180, 88, 15);
		lblSpeedSlider.setText("Speed Control");
		txtSlowest = new Text(shlPrototypeControllerApp, SWT.BORDER);
		txtSlowest.setText("Slowest");
		txtSlowest.setBounds(798, 211, 76, 21);
		txtFasteset = new Text(shlPrototypeControllerApp, SWT.BORDER);
		txtFasteset.setText("Fastest");
		txtFasteset.setBounds(1068, 211, 76, 21);

		// initialize the "connect" button and its functions
		btnConnect = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnConnect.setBounds(169, 510, 75, 25);
		btnConnect.setText("Connect");
		btnConnect.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				boolean connected = isConnected;
				System.out.println(connected);
				if (connected == false) {
					txtStatus.setText("STATUS: Vehicle is not connected. Try again.");
					// connectVehicle();
					// isConnected = true;
				} else
					txtStatus.setText("STATUS: Vehicle is already connected.");

			}
		});

		// initialize the "flash button and its functions
		Button btnFlash = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnFlash.setText("Flash");
		btnFlash.setBounds(283, 510, 75, 25);
		btnFlash.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				txtStatus.setText("STATUS: Vehicle is not connected. Try again.");
			}
		});

		// initialize the "disconnect" button and its functions
		Button btnDisconnect = new Button(shlPrototypeControllerApp, SWT.NONE);
		btnDisconnect.setText("Disconnect");
		btnDisconnect.setBounds(391, 510, 75, 25);
		btnDisconnect.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (isReversed == true)
					txtStatus.setText(statusReversed);
				txtStatus.setText("STATUS: Vehicle is already disconnected.");
			}
		});

		txtStatus = new Text(shlPrototypeControllerApp, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		txtStatus.setEditable(false);
		txtStatus.setText("STATUS:");
		txtStatus.setBounds(798, 330, 350, 213);

		Scale scale = new Scale(shlPrototypeControllerApp, SWT.NONE);
		scale.setBounds(892, 204, 170, 42);

		// initialize the radio button, which is used to reverse the cehicle's controls
		btnRadioButton = new Button(shlPrototypeControllerApp, SWT.RADIO);
		btnRadioButton.setBounds(934, 276, 90, 16);
		btnRadioButton.setText("Reverse Direction");
		
		
		btnRadioButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				boolean reversed = isReversed;
				reversed = true;
				txtStatus.setText(statusReversed);
			}
		});

	}
}
