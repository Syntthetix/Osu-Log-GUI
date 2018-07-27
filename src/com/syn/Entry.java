package com.syn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Entry {
	
	private int pp;
	private int globalRank;
	private int usRank;
	private int combo;
	private String song;
	private File save;
	
	// Used for rank entries
	public Entry(int pp, int globalRank, int usRank, File save) {
		this.pp = pp;
		this.globalRank = globalRank;
		this.usRank = usRank;
		this.save = save;
	}
	
	// Used for FC entries
	public Entry(String song, int combo, int pp, File save) {
		this.song = song;
		this.combo = combo;
		this.pp = pp;
		this.save = save;
	}
	
	public void addToFile() throws FileNotFoundException {
		// Opens file in append mode, making it possible to add new text to the file rather than replacing it
		PrintWriter pw = new PrintWriter(new FileOutputStream(save, true));
		Calendar now = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("M/d/yyyy");
		
		pw.println(df.format(now.getTime()));
		pw.println("- " + pp + " PP");
		pw.println("- #" + globalRank);
		pw.println("- #" + usRank);
		pw.close();
	}
	
	public void addFcToFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(save, true));
		Calendar now = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("M/d/yyyy");
		
		pw.println(df.format(now.getTime()));
		pw.println("- " + song);
		pw.println("- " + combo + " combo");
		pw.println("- " + pp + " PP");
		pw.close();
	}
}
