package com.jnn.fractal;

import com.jnn.expressions.Expression;import com.jnn.expressions.MathOperand;import com.jnn.util.ANR;import android.graphics.Bitmap;import android.graphics.Color;





public class SleepThread extends Thread implements Runnable {
		int sX = 0;	int sY = 0;	int tStart_its = 0;	double tStart_inf = 0;		public int cCount,newColor, startIts=0;	public static   double K1;	public static double tR;	public static double tG;	public static double tB;	  		int r,g,b;	int r2,g2,b2;	double cFracX,cFracY;		public static int RedFromCYMK=0;	public static int GreenFromCYMK=0;	public static int BlueFromCYMK=0;	public int tColor;		public float[] hsv = new float[]{0f,0f,0f,0f};
	public static boolean runRenderThread=true;
	static boolean renderingStopped=false;	double startInf = Double.MAX_VALUE;	//public RenderJob rj;
			public SleepThread(){			}		public void run(){			try {		Thread.sleep(10000);		MainView.splashLogo=false;			} catch (InterruptedException e) {		// TODO Auto-generated catch block		MainView.splashLogo=false;	}				}	
}