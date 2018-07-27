package com.syn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DisplayLog {
	
	private Shell shlDisplayLog;
	private Text textLog;
	
	public DisplayLog() {
		shlDisplayLog = new Shell(Display.getCurrent());
		shlDisplayLog.setSize(260, 543);
		shlDisplayLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlDisplayLog.setText("Display Log");
		
		textLog = new Text(shlDisplayLog, SWT.READ_ONLY | SWT.V_SCROLL);
		textLog.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textLog.setEditable(false);
		textLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		textLog.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textLog.setBounds(0, 0, 244, 504);
	}
	
	public void open() {
		shlDisplayLog.open();
	}
	
	public void close() {
		shlDisplayLog.setVisible(false);
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
		textLog.setText(data);
	}
}
