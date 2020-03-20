package com.jnn.fractal;

import com.jnn.expressions.Expression;
import com.jnn.expressions.MathOperand;
import com.jnn.util.ANR;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

public class MainView extends View {

	public static int vWidth = -1;//set bakc to -1
	public static int vHeight= -1;   //set back to -1
	public static int renderProgress=50;
	public static boolean splashLogo=true;
	public static boolean loading = true;
	
	public static Rect tRect = new Rect();
	
	static Rect renderBox =new Rect(0,5,310,25);
	static int renderBoxStartX=100;
	static int renderBoxLength=300;
	static Paint renderBoxPaint = new Paint();
	
	static Paint hiliteBoxPaint = new Paint();
	
	static Rect src = new Rect();
	static Rect dst = new Rect();
	
	

	public static Paint textPaint = new Paint();
	public MainView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(12);
		
		renderBoxPaint.setColor(Color.GRAY);		
		renderBoxPaint.setStyle(Style.FILL_AND_STROKE);
		renderBoxPaint.setStrokeWidth(1);
		
		hiliteBoxPaint.setColor(Color.GRAY);		
		hiliteBoxPaint.setStyle(Style.STROKE);
		hiliteBoxPaint.setStrokeWidth(2);
		
		MainView.splashLogo = true;
		
		
		/*RenderJob rj = new RenderJob(-2,-2,
				2, 2, 3500, Double.MAX_VALUE,
				0, 0, getWidth(), getHeight(), 'S', 
				MainActivity.rawImage,MainActivity.updateImage,MainActivity.renderCanvas ,
				"Z * Z + C",false, 1,1,
				true, 15, 4,"I + 200",
				"noFile");
		
		MainActivity.rj = rj;
		
		

		MainActivity.mQueue.addTask(new RenderThread(MainActivity.rj));
		*/
		
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (vWidth < 0 || vHeight < 0 )
			{vWidth = getWidth();
			vHeight = getHeight();
			}
		// TODO Auto-generated method stub
		
		//canvas.drawBitmap(MainActivity.tempHolder, 0,0,null);
		//canvas.drawBitmap(MainActivity.rawImage, 0, MainActivity.tempHolder.getWidth(), 0, 0, MainActivity.tempHolder.getWidth(), MainActivity.tempHolder.getHeight(),false, null);
		
		//canvas.drawBitmap(MainActivity.renderHolder, 0,0,null);
		if (loading){
			//canvas.drawBitmap(MainActivity.DesktopMap, 0, 0, null);
			LoadThread.drawBitmap_Fill(MainActivity.DesktopMap,canvas,vWidth,vHeight);
		}
		else
		{
		//	dst.right =renderBox.right;
//			src.right =renderBox.right;
			canvas.drawBitmap(MainActivity.renderHolder, src, dst, null);
		}
		
		tRect = new Rect(MainActivity.mouseSX,MainActivity.mouseSY,MainActivity.TrackMouseSX,MainActivity.TrackMouseSY);
		
		if(MainActivity.trackingMove)
		{
			if (MainActivity.TrackMouseSX < MainActivity.mouseSX)
			{
				tRect.left = MainActivity.TrackMouseSX;
				tRect.right = MainActivity.mouseSX;
			}
			
			
			if (MainActivity.TrackMouseSY < MainActivity.mouseSY)
			{
				tRect.top = MainActivity.TrackMouseSY;
				tRect.bottom = MainActivity.mouseSY;
			}
			
			tRect.bottom =MainActivity.getX2_i(tRect.left, tRect.top, tRect.right, vWidth, vHeight);
			//tRect = new Rect(MainActivity.mouseSX,MainActivity.mouseSY,MainActivity.TrackMouseSX,MainActivity.TrackMouseSY);
			//tRect.right = MainActivity.getX2_i(tRect.left, tRect.top, tRect.right, canvas.getWidth(), canvas.getHeight());
			
			canvas.drawRect(tRect, hiliteBoxPaint);
			
		}
		
		if (MainView.splashLogo){
			tRect.left = (getWidth()/2)  - (MainActivity.splashMap.getWidth()/2);
			tRect.top = (getHeight()/2)  - (MainActivity.splashMap.getHeight()/2);
			
		//	tRect.bottom = (canvas.getWidth()/2)  - (MainActivity.splashMap.getWidth()/2);
			canvas.drawBitmap(MainActivity.splashMap, tRect.left, tRect.top, null);
		}
		
		
			
		
		if (MainActivity.rj != null)
		{canvas.drawRect(renderBox, renderBoxPaint);
		canvas.drawText("Its = " + Double.toString(MainActivity.rj.currentMaxIts) +" , Inf="+ Double.toString(MainActivity.rj.currentInfinity), renderBox.left + 5 ,renderBox.bottom -3 ,textPaint);
		}
		
	
		
		super.onDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh); 
		
		
		
		vWidth = w;
		vHeight = h;
		
		
		return;
		
	/*	Toast.makeText(MainActivity.mContext,"Sized Changed - Changing to "+Integer.toString(w)+" ,"+Integer.toString(h), Toast.LENGTH_LONG).show();
		dst.set(0,0,w,h);
		
		
		
		//h=1024;
		
			
		
		MainActivity.rj.w = w;
		MainActivity.rj.h = h;
		
		MainActivity.rj.y2 = MainActivity.getX2_d(MainActivity.rj.x1, MainActivity.rj.y1, MainActivity.rj.x2, w,h);
		
		 
		 
			
		
		
	
		renderBox =new Rect(0,5,w,20);
		renderBoxLength = w;
		
		//w /=2;
		//h /=2;
		
		src.set(0,0,w,h);
		 
		
		
		MainActivity.tempHolder=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
		MainActivity.renderHolder=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
		
		
		
		
		MainActivity.renderCanvas = new Canvas(MainActivity.renderHolder);
		
		Rect src=new Rect(0,0,MainActivity.splashMap.getWidth(),MainActivity.splashMap.getHeight());
		Rect dst=new Rect(0,0,w,h);
		
		MainActivity.renderCanvas.drawBitmap(MainActivity.splashMap,src,dst,null);
		
		
		MainActivity.rawImage=ANR.getBitArray(MainActivity.tempHolder);
		MainActivity.rj.imageArray = MainActivity.rawImage;
		MainActivity.rj.w=w;
		MainActivity.rj.h = h;
		
		MainActivity.mQueue.addTask(new RenderThread(MainActivity.rj));
*/
	}
	


}
