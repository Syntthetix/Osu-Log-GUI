package com.syn;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class AboutPage {
	
	private Shell shlAboutPage;
	
	public AboutPage() {
		shlAboutPage = new Shell(Display.getCurrent());
		shlAboutPage.setText("About Syn's Osu Log");
		shlAboutPage.setSize(434, 235);
		shlAboutPage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		
		Label lblVersion = new Label(shlAboutPage, SWT.NONE);
		lblVersion.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblVersion.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblVersion.setBounds(10, 10, 56, 15);
		lblVersion.setText("Version 1.0");
		
		Label lblOriginalCredit = new Label(shlAboutPage, SWT.CENTER);
		lblOriginalCredit.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOriginalCredit.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblOriginalCredit.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblOriginalCredit.setBounds(54, 43, 298, 48);
		lblOriginalCredit.setText("Based off of original Osu Log program by El Nera, written in C#");
		
		Label lblWrittenAndDesigned = new Label(shlAboutPage, SWT.CENTER);
		lblWrittenAndDesigned.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblWrittenAndDesigned.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWrittenAndDesigned.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblWrittenAndDesigned.setBounds(54, 97, 298, 21);
		lblWrittenAndDesigned.setText("Written and designed by Dorito");
		
		Label lblIdkLol = new Label(shlAboutPage, SWT.NONE);
		lblIdkLol.setAlignment(SWT.CENTER);
		lblIdkLol.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblIdkLol.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblIdkLol.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblIdkLol.setBounds(54, 124, 298, 42);
		lblIdkLol.setText("Maybe I'll put this up for download in the future, idk lol");
	}
	
	public void open() {
		shlAboutPage.open();
	}
	
	public void close() {
		shlAboutPage.setVisible(false);
	}
}
