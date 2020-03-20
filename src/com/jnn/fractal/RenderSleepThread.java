package com.jnn.fractal;

import com.jnn.expressions.Expression;import com.jnn.expressions.MathOperand;import com.jnn.util.ANR;import android.graphics.Bitmap;import android.graphics.Color;





public class RenderSleepThread extends Thread implements Runnable {
			boolean sleeping = true;	int halfSecs=20;		public RenderSleepThread(int halfSecondPeriods){		this.halfSecs = halfSecondPeriods;	}		public void run(){			try {				int counts = 0;		while (sleeping && counts < this.halfSecs)		{						Thread.sleep(500);			if (!sleeping) return;						counts++;		}		this.sleeping=false;			} catch (InterruptedException e) {		// TODO Auto-generated catch block		this.sleeping=false;	}				}	
}