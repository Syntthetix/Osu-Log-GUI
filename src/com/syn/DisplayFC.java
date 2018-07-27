package com.syn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class DisplayFC {

	private Shell shlDisplayFC;
	private Text textFcLog;

	public DisplayFC() {
		shlDisplayFC = new Shell(Display.getCurrent());
		shlDisplayFC.setSize(348, 543);
		shlDisplayFC.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlDisplayFC.setText("Display FC Log");
		
		textFcLog = new Text(shlDisplayFC, SWT.READ_ONLY | SWT.V_SCROLL);
		textFcLog.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		textFcLog.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textFcLog.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		textFcLog.setBounds(0, 0, 332, 504);
	}

	public void open() {
		shlDisplayFC.open();
	}

	public void close() {
		shlDisplayFC.setVisible(false);
	}
	
	/**
	 * @param save The save file to be displayed
	 * @throws FileNotFoundException
	 */
	public void update(File save) throws FileNotFoundException {
		Scanner inFile = new Scanner(save);
		String data = "";
		
		// Used for handling spacing between entries. Yes Noah there is a variable declared for a while loop here.
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
		
		textFcLog.setText(data);
	}
}
