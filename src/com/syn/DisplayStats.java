package com.syn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class DisplayStats {

private Shell shlDisplayStatsLog;
private Text textStatsLog;
	
	public DisplayStats() {
		shlDisplayStatsLog = new Shell(Display.getCurrent());
		shlDisplayStatsLog.setText("Display Stats");
		shlDisplayStatsLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlDisplayStatsLog.setSize(611, 431);
		
		textStatsLog = new Text(shlDisplayStatsLog, SWT.READ_ONLY | SWT.V_SCROLL);
		textStatsLog.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textStatsLog.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textStatsLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		textStatsLog.setBounds(0, 0, 595, 392);
	}
	
	public void open() {
		shlDisplayStatsLog.open();
	}
	
	public void close() {
		shlDisplayStatsLog.setVisible(false);
	}
	
	/**
	 * @param save The save file to be displayed
	 * @throws FileNotFoundException
	 */
	public void update(File save) throws FileNotFoundException {
		Scanner inFile = new Scanner(save);
		String data = "";
		
		int i = 0;
		while (inFile.hasNextLine()) {
			String token = inFile.nextLine();
			data += token + "\n";
			
			i++;
			if (i == 4) {
				data += "\n";
				i = 0;
			}
		}
		textStatsLog.setText(data);
	}
	
}
