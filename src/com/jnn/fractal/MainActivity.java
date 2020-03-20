package com.jnn.fractal;



import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.jnn.ebook.TutorViewer;
import com.jnn.expressions.Expression;
import com.jnn.expressions.MathOperand;
import com.jnn.util.ANR;

import com.jnn.util.StateObjectWrapper;
import com.jnn.util.TaskQueue;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static AlertDialog actionMenu = null;
	public static String[] items = null;
	public static boolean trackingMove = false;
	public static  int TrackMouseSX=0;
	public static  int TrackMouseSY=0;
	
	public static RenderJob rj = null;
	 
	public static Bitmap splashMap;
	public static Bitmap DesktopMap	;
	
	public static Context mContext = null;
	
	static int mouseSX=0;
	static int mouseSY=0;
	
//	static double bigX=0;
//	static double bigY=0;
	
//	static double smallX=0;
//	static double smallY=0;
	
	static boolean slowThread = false;
	

	//public static Bitmap model;
	public static Bitmap renderHolder;
	public static Canvas renderCanvas;
	public static Bitmap tempHolder;
	public static int[] rawImage;
	//public static ImageView i;
	
		// For speed
	static float tHsv[] = new float[4];
	static float sHsv[] = new float[4];
	static float fHsv[] = new float[4];

	// For the rendering thread
	public static RenderThread mRT = null;
	public static boolean isRendering = false;
	public static boolean resetRendering = false;
	
//	public static FracView mFrac= null;//new FracView();
	public static View mView = null;//new MainView(this);
	
	AlertDialog.Builder alert = null;//new AlertDialog.Builder(this);

	
	public static Handler updateImage = new Handler() {

		public void handleMessage(Message msg) {
			//MainActivity.updateImage();
			MainActivity.mView.invalidate();
		}
	};
	//Toast.makeText(this, "Look saved...",0).show();
	// //Create an anonymous implementation of OnClickListener
	private OnTouchListener mTouchListener = new OnTouchListener()
	{    	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return handleOnTouch(v,event);
		
	}
	};
	

	public static ProgressDialog mProgress;//=new ProgressDialog(MainActivity.mContext);
	
	public static Handler showProgress = new Handler() {

		public void handleMessage(Message msg) {
			MainActivity.mProgress.setMessage("....downloading....");
			MainActivity.mProgress.show();
		}
	};
	
	public static Handler showSharing = new Handler() {

		public void handleMessage(Message msg) {
			MainActivity.mProgress.setMessage("....preparing....");
			MainActivity.mProgress.show();
		}
	};
	
	
	public static Handler hideProgress = new Handler() {

		public void handleMessage(Message msg) {
			MainActivity.mProgress.hide();
		}
	};

	
	public static Handler showMessageViaToast = new Handler() {

		public void handleMessage(Message msg) {
			//StringBuffer objectRcvd = (StringBuffer) msg.getData().getParcelable("MyObject");
			String data = (String) msg.obj;
			//MainActivity.updateImage();
			//MainActivity.mView.invalidate();
			Toast.makeText(MainActivity.mContext, data, Toast.LENGTH_LONG).show();
			
		}
	};
	
	public static void showMessage(String m){
		Message msg = new Message();
		msg.obj = m;
		MainActivity.showMessageViaToast.sendMessage(msg);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	public static TaskQueue mQueue = new TaskQueue();
	
	//for start up control
	public static boolean LOAD_DEFAULT=true;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
		mContext = this;  
		ANR.setupStorage("fraczi",getResources());
		
		
		
	/*	Expression ex = new Expression("-1*z*z+@com(-3,4) - c");
		ex = new Expression("(n/2)/p");
		ex = new Expression("(n/ 2 ) /p");
		ex = new Expression("  ( n/2) / p");
		ex = new Expression("-1*z *z+ @com(-3, 4 ) - c");
		ex = new Expression("z*z+c");
		ex = new Expression("z*(@com(@abs(a),@abs(b))^2)+@com(x,y)");
		ex = new Expression("(Z*Z)^(@sin(@bound(a,-.5,.5))*@com(@sin(x),@cos(y))");
		ex = new Expression("(Z*Z)^(@sin(@bound(a,-.5,.5))*@com(@sin(x),@cos(y)))");
		ex.toString();
	*/
		
		
		MainActivity.rj=null;
		
		/*Date tDate = new Date(System.currentTimeMillis());
		Date expDate = null;
		
		try {
			expDate = new SimpleDateFormat("MM/dd/yyyy").parse("08/01/2013");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tDate.after(expDate))
		{
			
			this.finish();
			return;
		}*/
		
		MainActivity.splashMap = ANR.getRawResourceAsBitmapByName(getResources(), "frac_splash");
		MainActivity.DesktopMap = ANR.getRawResourceAsBitmapByName(getResources(), "desktop_g");
		
		mView = new MainView(this); 
		mView.setOnTouchListener(this.mTouchListener);
		setContentView(mView);
		LoadThread.loadActionMenu();
		
		
		LoadThread l = new LoadThread();
		l.start();
		
		
		MainActivity.slowThread=false;
		

	}

	public static double getX2_d(double x1,double y1, double x2, int w ,int h){
		if (x1 > x2)
		{
			double t=x1;
			x1=x2;
			x2=t;
		}
		
		double ratio =(double)h/(double)w;
		
		double delta = (x2-x1) * ratio;
		return y1 + delta;
				
	}
	public static int getX2_i(int x1,int y1, int x2, int w ,int h){
		if (x1 > x2)
		{
			int t=x1;
			x1=x2;
			x2=t;
		}
		
		double ratio =(double)h/(double)w;
		
		double delta = (x2-x1) * ratio;
		return (int) (y1 + delta);
				
	}
	public String getRawResource(int id) {
		String oString = "";
		InputStream is = this.getResources().openRawResource(id);
		try {
			int size = is.available();

			// Read the entire resource into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			oString = new String(buffer);
		} catch (Exception e) {

		}

		return oString;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, StaticResources.MENU_CUSTOM, 0, "Custom");
		//menu.add(0, StaticResources.MENU_RESET, 0, "Reset");
		
		menu.add(0, StaticResources.MENU_OPEN, 0, "Open");
		
		menu.add(0, StaticResources.MENU_SAVE_FRAC, 0, "Save");
		
		menu.add(0, StaticResources.MENU_SHARE, 0, "Share");
		menu.add(0, StaticResources.MENU_SAVE_IMG, 0, "PNG");
		menu.add(0, StaticResources.MENU_HELP, 0, "Help");
		
		//menu.add(0, StaticResources.MENU_SHARE, 0, "Share");
		// menu.add(0, StaticResources.MENU_SAVE, 0, "Save");
		//menu.add(0, StaticResources.MENU_ABOUT, 0, "More");
		return true;
	}
	
	
	public Object onRetainNonConfigurationInstance() 
	{   
		
		StateObjectWrapper so = new StateObjectWrapper();

//set items to remember		
		return so;
		}

	protected void onResume() {
		MainActivity.slowThread = false;
		super.onResume();
		
	
	
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item)

	{
		try {
			switch (item.getItemId())

			{
			
			case StaticResources.MENU_OPEN:
				AlertDialog.Builder fileOpen = new AlertDialog.Builder(this);
				fileOpen.setTitle("Select File");
				
				ArrayList<CharSequence> al = new ArrayList<CharSequence>();
				 File appRoot = new File(ANR.appStoragePath);
		    	  
		    	 
		          File[] files= appRoot.listFiles();  
		      	
		        	//.clear();
		        	        	
		            for (int j = 0; j < files.length ;j++) 
		            {
		            	
		            	
		            	if (files[j].isFile() && files[j].getName().toUpperCase().endsWith(".FZI"))
		            	{
		            	
		            		al.add(files[j].getName());
		            	}
		            		
		  
		            }
		            
		        items = new String[al.size() + 12];
		        int j=0;
		        for(j=0;j<al.size();j++)		        
		        {
		        	items[j] = (String) al.get(j);
		        	 
		        }
		       
		    	items[j++]="APP:mandelbrot_default";
		    	items[j++]="APP:the_ship";
		    	items[j++]="APP:planet_event";
		    	items[j++]="APP:plush";
		    	items[j++]="APP:heart";
		    	items[j++]="APP:rings";
		    	items[j++]="APP:swirly";
		    	items[j++]="APP:orange_slice";
		    	items[j++]="APP:spray_painter";
		    	items[j++]="APP:trig";
		    	items[j++]="APP:lifecycle";
		    	items[j++]="APP:third_eye";
		    	
		        
		        
		            
				fileOpen.setItems(items, 
						new DialogInterface.OnClickListener(){
							public void onClick(DialogInterface dialog, int which)
							{
								// The 'which' argument contains the index position   
								// of the selected item
								
								
								//MainActivity.mQueue.addTask(new RenderThread(rj));
								String XML = ANR.getFileContentsAsString(MainActivity.items[which]);
								
								RenderJob tRj = RenderJob.createFromXmlString(XML);
								tRj.w= MainActivity.rj.w;
								tRj.h =MainActivity.rj.h;
								tRj.updateHandler = MainActivity.updateImage;
								tRj.imageArray = MainActivity.rawImage;
								tRj.bufferCanvas = MainActivity.renderCanvas ;
								tRj.y2 = MainActivity.getX2_d(tRj.x1, tRj.y1,tRj.x2, tRj.w,tRj.h);
								
								MainActivity.rj = tRj;
								
								MainActivity.mQueue.addTask(new RenderThread(rj));
								
							}});
				
				
				fileOpen.create().show();

				
				
				break;
				
			case StaticResources.MENU_SAVE_FRAC:
				
				MainActivity.slowThread = true;
				
				
				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle("Name your Fractal");
				alert.setMessage("Enter a valid filename");

				// Set an EditText view to get user input 
				final EditText input = new EditText(this);
				alert.setView(input);

				alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				  String value = input.getText().toString()+".fZi";
				  // Do something with value!
				  if (ANR.isFile(value))
				  	{
					  int c = 1;
					  while (ANR.isFile(input.getText().toString()+"_"+Integer.toString(c)+".fZi"))
						  c++;
					  
					  value = input.getText().toString()+"_"+Integer.toString(c)+".fZi";
					  
					  Toast.makeText(MainActivity.mContext,"File exists!\nSaving as "+ value+".", Toast.LENGTH_LONG).show();
				  	}
				  
					  if (ANR.saveXMLFile(MainActivity.rj.getXML_String(), value))
						  Toast.makeText(MainActivity.mContext,"File saved.\n"+ value, Toast.LENGTH_LONG).show();
					  else
						  Toast.makeText(MainActivity.mContext,"File NOT saved!\n"+ value+" is not valid.", Toast.LENGTH_LONG).show();
					  
				  
				  
				  
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
				  }
				});

				alert.show();
				
				MainActivity.slowThread = false;
				
				
				return true;
			case StaticResources.MENU_CUSTOM:
				Intent myIntent = new Intent(MainActivity.mContext,CustomizeActivity.class);
		    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.mContext.startActivity(myIntent);
				
				return true;
				
			case StaticResources.MENU_RESET:

			//	Intent kitIntent = new Intent(this, KitActivity.class);
			//	startActivity(kitIntent);
				
				/* while (MainActivity.mQueue.tasks.size()>0)
			         	MainActivity.resetRendering=true;
				 
				 MainActivity.resetRendering=false;
		*/		 
				 
				int w=rj.w;
				int h=rj.h;
				
				 rj.x1= -2; 
				 rj.x2 = 2;
				 rj.y1 = -2;
				 
				 rj.y2 = MainActivity.getX2_d(rj.x1, rj.y1, rj.x2, rj.w,rj.h);
				 
				 
/*				  
				 while (MainActivity.mQueue.tasks.size()>0)
			         	MainActivity.resetRendering=true;
				 
				 MainActivity.resetRendering=false;
*/				
		
				 
				 MainActivity.mQueue.addTask(new RenderThread(rj));

				return true;

			case StaticResources.MENU_HELP:
				
				myIntent = new Intent(MainActivity.mContext,TutorViewer.class);
		    	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.mContext.startActivity(myIntent);
				
				return true;
			
			case StaticResources.MENU_SHARE:
				MainActivity.slowThread=true;
				
				ContentValues values = new ContentValues();
				values.put(Images.Media.TITLE, MainActivity.rj.iExp.toString());
				values.put(Images.Media.DESCRIPTION, MainActivity.rj.getXML_String());
				values.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
				values.put(Images.Media.MIME_TYPE, "image/jpeg");

				Uri url = getContentResolver().insert(
						Images.Media.EXTERNAL_CONTENT_URI, values);
				try {
					OutputStream outStream = getContentResolver()
							.openOutputStream(url);
					MainActivity.renderHolder.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
					//MainActivity.renderHolder.compress(Bitmap.CompressFormat.PNG, 100, outStream); 
				
					outStream.flush();
					outStream.close();
				//	Log.d("done", "done");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}
				// String url = Images.Media.insertImage(getContentResolver(),
				// MainActivity.tempHolder, "title", null);
				MainActivity.slowThread=false;
				Intent i = new Intent(Intent.ACTION_SEND);
				i.putExtra(Intent.EXTRA_STREAM, url);
				i.putExtra(Intent.EXTRA_SUBJECT, "fracZi Image");
				i.putExtra(Intent.EXTRA_TITLE,  MainActivity.rj.iExp.toString());
				i.putExtra(Intent.EXTRA_TEXT, MainActivity.rj.getDetail_String());
						
				i.setType("image/jpeg");
				i.putExtra("sms_body", MainActivity.rj.getDetail_String());
				//i.putExtra(Intent.EXTRA_SUBJECT,"Look What I made with jwColorLab");

				startActivity(Intent.createChooser(i, "Send Image To:"));

				
				return true;
				
				
			case StaticResources.MENU_SAVE_IMG:
					
				MainActivity.slowThread=true;
					
				ANR.setupStorage("fraczi");
				File root = new File(ANR.appStoragePath);
				
				SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
				Date now = new Date();
				String fileName = "fZ_"+formatter.format(now) + ".png";
				
				if (ANR.isFile(fileName))
				{ int c=1;
					fileName = "fZ_"+formatter.format(now)+"_"+Integer.toString(c) + ".png";
					while (ANR.isFile(fileName))
						c++;
				}
				
				File gpxfile = new File(root,fileName);
				gpxfile.toURI().toASCIIString();
				
				try {
					FileOutputStream outStream = new FileOutputStream(gpxfile);
					MainActivity.renderHolder.compress(Bitmap.CompressFormat.PNG, 100, outStream);
					 
				 
					outStream.flush();
					outStream.close();
				//	Log.d("done", "done");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}
				
				MainActivity.slowThread=false;
				Toast.makeText(this.mContext,"File saved as "+ fileName+".", Toast.LENGTH_LONG).show();
				
				return true;
			
			
			}
		} catch (Exception e) {

			ANR.alert(this, e.getMessage());
		}
		return false;
	}

	
	public static void updateImage() {
	//	MainActivity.i.setImageBitmap(tempHolder);
		//MainActivity.i.invalidate();
		
	}

	public static int[] getBitArray(Bitmap iMap) {
		int[] pix = new int[iMap.getWidth() * iMap.getHeight()];
		iMap.getPixels(pix, 0, iMap.getWidth(), 0, 0, iMap.getWidth(), iMap
				.getHeight());
		return pix;
	}

	public static void NotifyReload() {
	//	MainActivity.i.setImageBitmap(model);
		//MainActivity.i.invalidate();
		
		String saveString = MainActivity.rj.getXML_String();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		MainActivity.slowThread = true;
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MainActivity.resetRendering=true;
		
		MainActivity.mQueue.stop();
		MainActivity.rj.resumeJob=true; 
		
		ANR.saveXMLFile(MainActivity.rj.getXML_String(), "fz_state");
		
		
	//	MainActivity.slowThread=true;
		
		ANR.setupStorage("fraczi");
		File root = new File(ANR.appStoragePath);
		
		
		String fileName = "fZ_img_state.png";
		
		
		
		File gpxfile = new File(root,fileName);
		gpxfile.toURI().toASCIIString();
		
		try {
			FileOutputStream outStream = new FileOutputStream(gpxfile);
			
			MainActivity.renderCanvas.drawBitmap(MainActivity.rj.imageArray, 0, MainActivity.rj.w, 0, 0, MainActivity.rj.w,MainActivity.rj.h,false, null);
			MainActivity.renderHolder.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			 
		 
			outStream.flush();
			outStream.close();
		//	Log.d("done", "done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		
		
		
		
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		MainActivity.slowThread = false;
		
		super.onRestart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		MainActivity.slowThread = true;
		
		//String saveString = MainActivity.rj.getXML_String();
		
		
		
		
		super.onStop();
	}

	public  boolean handleOnTouch(View view, MotionEvent event) { 
        
        
        int eventaction = event.getAction(); 

        int X = (int)event.getX(); 
           int Y = (int)event.getY(); 
           
         //  double deltaFracX = FracView.maxX - FracView.minX;
         //  double deltaFracY = FracView.maxY - FracView.minY;
           
        //   double test=(double)(Y/(double)FracView.imgHeight);

           double smallX = 0;
           double smallY = 0;
           double bigX = 0;
           switch (eventaction ) { 
           			       					
           					
                         case MotionEvent.ACTION_UP:
                        	
                        	 MainActivity.trackingMove = false;
                         	 MainActivity.slowThread =true;
                         	 //MainActivity.TrackMouseSX = X;
                         	 //MainActivity.TrackMouseSY = Y;
                         	MainActivity.updateImage.sendEmptyMessage(0); 
                         	 this.actionMenu.show();
                         	 return true;
                         	
                            
                         case MotionEvent.ACTION_MOVE:
            				//	MainActivity.mouseSX=X;
            				//	MainActivity.mouseSY=Y;
                        	// MainActivity.TrackMouseSX=X;
                        	 
                      
                        	 MainActivity.TrackMouseSX = X;
                        	 MainActivity.TrackMouseSY = Y;
                        	 
                        	  
                        	 //MainActivity.TrackMouseSY=MainActivity.getX2_i(mouseSX, mouseSY, TrackMouseSX, rj.w, rj.h); 
                        	 MainActivity.updateImage.sendEmptyMessage(0); 
            					break; 
                            
                     	case MotionEvent.ACTION_DOWN: 
           					MainActivity.mouseSX=X;
           					MainActivity.mouseSY=Y;
           					MainActivity.TrackMouseSX=X;
           					MainActivity.TrackMouseSY=Y;
           					MainActivity.trackingMove = true;
           					MainActivity.slowThread = true;
           					
           					break;
                }
		return true;//true;////true; 
} 
	
	public static Rect normalize(Rect r){
		if (r.right < r.left)
		{
			int t = r.left;
			r.left = r.right;
			r.right = t;
		}
		
		if (r.bottom < r.top)
		{
			int t = r.top;
			r.top = r.bottom;
			r.bottom = t;
		}
		return r;
	}
	

}