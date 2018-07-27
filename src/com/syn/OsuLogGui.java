package com.syn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class OsuLogGui {

	protected static Shell shlSynsOsuLog;

	public static final String VERSION = "v1.7";
	public static final String HOME_DIR = System.getProperty("user.home");
	
	public static final File SAVE_DIR = new File(HOME_DIR + "/Documents/Osu Log GUI/save");
	public static final File TEMP_DIR = new File(HOME_DIR + "/Documents/Osu Log GUI/temp");
	public static final File TEMP_LOG = new File(HOME_DIR + "/Documents/Osu Log GUI/temp/tempLog.txt");
	public static final File TEMP_STATS = new File(HOME_DIR + "/Documents/Osu Log GUI/temp/tempStats.txt");
	public static final File TEMP_FC = new File(HOME_DIR + "/Documents/Osu Log GUI/temp/tempFC.txt");
	public static final File SAVE = new File(HOME_DIR + "/Documents/Osu Log GUI/save/save.txt");
	public static final File STATS = new File(HOME_DIR + "/Documents/Osu Log GUI/save/stats.txt");
	public static final File FC = new File(HOME_DIR + "/Documents/Osu Log GUI/save/fc.txt");
	public static final File LOG = new File(HOME_DIR + "/Documents/Osu Log GUI/log.txt");
	
	private Text txtbPP;
	private Text txtbGlobalRank;
	private Text txtbCountryRank;
	private Text txtbFcSong;
	private Text txtbFcCombo;
	private Text txtbFcPP;
	
	/**
	 * Launch the application.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter logWrite = new PrintWriter(new FileOutputStream(LOG, true));
		Calendar now = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("M/d/yyyy");
		
		try {
			SAVE_DIR.mkdirs();
			TEMP_DIR.mkdirs();
			SAVE.createNewFile();
			STATS.createNewFile();
			FC.createNewFile();
			TEMP_LOG.createNewFile();
			TEMP_STATS.createNewFile();
			TEMP_FC.createNewFile();
			LOG.createNewFile();
			
		} catch (Exception e) {
			MessageBox mb = new MessageBox(shlSynsOsuLog);
			mb.setMessage("Creation of directory " + SAVE_DIR.getPath() + " or " + TEMP_DIR.getPath() + " failed");
			mb.open();
			logWrite.println(df.format(now.getTime()) + " - Creation of directory " + SAVE_DIR.getPath() + " or " + TEMP_DIR.getPath() + " failed");
			System.exit(0);
		}
		
		try {
			OsuLogGui window = new OsuLogGui();
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
		shlSynsOsuLog.open();
		shlSynsOsuLog.layout();
		while (!shlSynsOsuLog.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSynsOsuLog = new Shell();
		shlSynsOsuLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlSynsOsuLog.setSize(644, 428);
		shlSynsOsuLog.setText("Syn's Osu Log");
		shlSynsOsuLog.setLayout(null);
		
		txtbPP = new Text(shlSynsOsuLog, SWT.BORDER | SWT.CENTER);
		txtbPP.setBounds(10, 82, 76, 21);
		
		txtbGlobalRank = new Text(shlSynsOsuLog, SWT.BORDER | SWT.CENTER);
		txtbGlobalRank.setBounds(133, 82, 76, 21);
		
		txtbCountryRank = new Text(shlSynsOsuLog, SWT.BORDER | SWT.CENTER);
		txtbCountryRank.setBounds(241, 82, 76, 21);
		
		txtbFcSong = new Text(shlSynsOsuLog, SWT.BORDER | SWT.WRAP | SWT.CENTER);
		txtbFcSong.setBounds(10, 188, 123, 37);
		
		txtbFcCombo = new Text(shlSynsOsuLog, SWT.BORDER | SWT.CENTER);
		txtbFcCombo.setBounds(145, 188, 76, 37);
		
		txtbFcPP = new Text(shlSynsOsuLog, SWT.BORDER | SWT.CENTER);
		txtbFcPP.setBounds(233, 188, 76, 37);
		
		Button btnSubmit1 = new Button(shlSynsOsuLog, SWT.NONE);
		btnSubmit1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int pp = 0, global = 0, country = 0;
				
				// Gets the needed user inputted values from the textboxes
				try {
				pp = Integer.parseInt(txtbPP.getText());
				global = Integer.parseInt(txtbGlobalRank.getText());
				country = Integer.parseInt(txtbCountryRank.getText());
				} catch (NumberFormatException nfe) {
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Error - Enter integer values into every textbox");
					mb.open();
					nfe.printStackTrace();
					System.exit(0);
				}
				
				// Adds a new entry with provided states
				Entry entry = new Entry(pp, global, country, SAVE);
				try {
					entry.addToFile();
					updateStats();
					
					// Resets textboxes
					txtbPP.setText("");
					txtbGlobalRank.setText("");
					txtbCountryRank.setText("");
					
					// Creates and opens a dialog box confirming that the entry was saved
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Entry was successfully saved");
					mb.open();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}				
			}
		});
		btnSubmit1.setBounds(340, 80, 75, 25);
		btnSubmit1.setText("Submit");
		
		Button btnSubmit2 = new Button(shlSynsOsuLog, SWT.NONE);
		btnSubmit2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int combo = 0, pp = 0;
				String song = "";
				
				try {
				song = txtbFcSong.getText();
				combo = Integer.parseInt(txtbFcCombo.getText());
				pp = Integer.parseInt(txtbFcPP.getText());
				} catch (NumberFormatException nfe) {
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Error - Enter values into every textbox");
					mb.open();
					nfe.printStackTrace();
					System.exit(0);
				}
				
				Entry entry = new Entry(song, combo, pp, FC);
				try {
					entry.addFcToFile();
					
					// Reset textboxes
					txtbFcSong.setText("");
					txtbFcCombo.setText("");
					txtbFcPP.setText("");
					
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Entry was successfully saved");
					mb.open();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}
			}
		});
		btnSubmit2.setBounds(340, 194, 75, 25);
		btnSubmit2.setText("Submit");
		
		Button btnViewLog = new Button(shlSynsOsuLog, SWT.NONE);
		btnViewLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplayLog displayLog = new DisplayLog();
				try {
					displayLog.update(SAVE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Error - Failed to open save file at " + SAVE_DIR);
					mb.open();
				}
				
				displayLog.open();
			}
		});
		btnViewLog.setBounds(421, 80, 75, 25);
		btnViewLog.setText("View Log");
		
		Button btnViewFcLog = new Button(shlSynsOsuLog, SWT.NONE);
		btnViewFcLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplayFC displayFC = new DisplayFC();
				try {
					displayFC.update(FC);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Error - Failed to open save file at " + SAVE_DIR);
					mb.open();
				}
				
				displayFC.open();				
			}
		});
		btnViewFcLog.setBounds(421, 194, 75, 25);
		btnViewFcLog.setText("View FC Log");
		
		Button btnViewStats = new Button(shlSynsOsuLog, SWT.NONE);
		btnViewStats.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplayStats displayStats = new DisplayStats();
				try {
					displayStats.update(STATS);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox mb = new MessageBox(shlSynsOsuLog);
					mb.setMessage("Error - Failed to open save file at " + SAVE_DIR);
					mb.open();
				}
				displayStats.open();
			}
		});
		btnViewStats.setBounds(543, 10, 75, 25);
		btnViewStats.setText("View Stats");
		
		Button btnAbout = new Button(shlSynsOsuLog, SWT.NONE);
		btnAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutPage aboutPage = new AboutPage();
				aboutPage.open();
			}
		});
		btnAbout.setBounds(10, 354, 75, 25);
		btnAbout.setText("About");
		
		Label lblNewRankEntry = new Label(shlSynsOsuLog, SWT.NONE);
		lblNewRankEntry.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewRankEntry.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewRankEntry.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewRankEntry.setBounds(10, 10, 211, 37);
		lblNewRankEntry.setText("New Rank Entry:");
		
		Label lblPP = new Label(shlSynsOsuLog, SWT.NONE);
		lblPP.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPP.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblPP.setBounds(92, 85, 21, 15);
		lblPP.setText("PP");
		
		Label lblNumberSign1 = new Label(shlSynsOsuLog, SWT.NONE);
		lblNumberSign1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNumberSign1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNumberSign1.setBounds(120, 85, 7, 15);
		lblNumberSign1.setText("#");
		
		Label lblNumberSign2 = new Label(shlSynsOsuLog, SWT.NONE);
		lblNumberSign2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNumberSign2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNumberSign2.setText("#");
		lblNumberSign2.setBounds(228, 85, 7, 15);
		
		Label lblGlobalRank = new Label(shlSynsOsuLog, SWT.NONE);
		lblGlobalRank.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblGlobalRank.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblGlobalRank.setAlignment(SWT.CENTER);
		lblGlobalRank.setBounds(133, 61, 76, 15);
		lblGlobalRank.setText("Global rank");
		
		Label lblCountryRank = new Label(shlSynsOsuLog, SWT.NONE);
		lblCountryRank.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCountryRank.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblCountryRank.setAlignment(SWT.CENTER);
		lblCountryRank.setBounds(241, 61, 76, 15);
		lblCountryRank.setText("Country rank");
		
		Label lblNewFcEntry = new Label(shlSynsOsuLog, SWT.NONE);
		lblNewFcEntry.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewFcEntry.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblNewFcEntry.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewFcEntry.setBounds(10, 119, 178, 37);
		lblNewFcEntry.setText("New FC Entry:");
		
		Label lblSong = new Label(shlSynsOsuLog, SWT.NONE);
		lblSong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblSong.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSong.setAlignment(SWT.CENTER);
		lblSong.setBounds(10, 167, 123, 15);
		lblSong.setText("Song");
		
		Label lblCombo = new Label(shlSynsOsuLog, SWT.NONE);
		lblCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCombo.setAlignment(SWT.CENTER);
		lblCombo.setBounds(145, 167, 76, 15);
		lblCombo.setText("Combo");
		
		Label lblPpValue = new Label(shlSynsOsuLog, SWT.NONE);
		lblPpValue.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblPpValue.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPpValue.setAlignment(SWT.CENTER);
		lblPpValue.setBounds(233, 167, 76, 15);
		lblPpValue.setText("PP Value");
		
		Label lblCopyright = new Label(shlSynsOsuLog, SWT.NONE);
		lblCopyright.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblCopyright.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCopyright.setBounds(440, 364, 178, 15);
		lblCopyright.setText("Copyright 2018 Plain Dorito, Inc.");

	}
	
	// Updates the stats file after any new entry is added (Usual method)
	public static void updateStats() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(STATS, true));
		ArrayList<Integer> pp = getPP();
		ArrayList<Integer> ranks = getRanks();
		ArrayList<String> dates = getDates();
		//Scanner inFile = new Scanner(SAVE);
		
		if (pp.size() > 1) {
			int ppChange = pp.get(pp.size() - 1) - pp.get(pp.size() - 2);
			int rankChange = ranks.get(ranks.size() - 1) - ranks.get(ranks.size() - 2);

			pw.println("From " + dates.get(dates.size() - 2) + " to " + dates.get(dates.size() - 1));
			pw.println("You earned " + ppChange + " PP" + " (" + pp.get(pp.size() - 2) + " -> " + pp.get(pp.size() - 1)
					+ ")");
			if (rankChange < 0) {
				pw.println("Your global rank increased by " + Math.abs(rankChange) + " (" + ranks.get(ranks.size() - 2)
						+ " -> " + ranks.get(ranks.size() - 1) + ")");
			} else {
				pw.println("Your global rank decreased by " + Math.abs(rankChange) + " (" + ranks.get(ranks.size() - 2)
						+ " -> " + ranks.get(ranks.size() - 1) + ")");
			}

			pw.close();
		}
	}

		// Adds every integer representing PP to an ArrayList, then returns that
		// ArrayList to updateStats()
		public static ArrayList<Integer> getPP() throws FileNotFoundException {
			Scanner inFile = new Scanner(SAVE);
			ArrayList<Integer> pp = new ArrayList<Integer>();

			while (inFile.hasNextLine()) {
				String token = inFile.nextLine();
				if (token.indexOf("PP") != -1) {
					// Adds only the integer portion of the line containing the PP for an entry
					int endIndex = token.indexOf("PP") - 1;
					pp.add(Integer.parseInt(token.substring(2, endIndex)));
				}
			}
			inFile.close();
			return pp;
		}

		// Adds every String representing a date to an ArrayList, then returns that
		// ArrayList to updateStates()
		public static ArrayList<String> getDates() throws FileNotFoundException {
			Scanner inFile = new Scanner(SAVE);
			ArrayList<String> dates = new ArrayList<String>();

			while (inFile.hasNextLine()) {
				String token = inFile.nextLine();
				if (token.indexOf("/") != -1) {
					dates.add(token);
				}
			}
			inFile.close();
			return dates;
		}

		// Adds every integer representing a global rank to an ArrayList, then
		// returns that ArrayList to updateStats()
		public static ArrayList<Integer> getRanks() throws FileNotFoundException {
			Scanner inFile = new Scanner(SAVE);
			ArrayList<Integer> ranks = new ArrayList<Integer>();
			boolean global = true;

			while (inFile.hasNextLine()) {

				if (global) {
					String token = inFile.nextLine();
					if (token.indexOf("#") != -1) {
						// Adds only the integer portion of the line containing the
						// global rank for an entry
						int index = token.indexOf("#") + 1;
						ranks.add(Integer.parseInt(token.substring(index)));
						global = false;
					}
				} else {
					String token = inFile.nextLine();
					global = true;
				}
			}
			inFile.close();
			return ranks;
		}
	
	public static void pause() {
		Scanner wait = new Scanner(System.in);
		wait.nextLine();
	}
}
