package com.jnn.ebook;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class TutorViewer extends Activity {
//	
//	Intent myIntent = new Intent(BotLoader.mContext, CodeViewActivity.class);
//	myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	mContext.startActivity(myIntent);

	public static int currentPage=1;
	public static final int lastPage=4;
	public static WebView contentView=null;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       // setContentView(R.layout.main);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	        
	          
	        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View thisView = inflater.inflate(com.jnn.fraczi.R.layout.local_web_view, null); 
	        setContentView(thisView);
	           
	               
	        contentView = (WebView) thisView.findViewById(com.jnn.fraczi.R.id.webview);
	          
	       // loadDataWithBaseURL (String baseUrl, String data, String mimeType, String encoding, String historyUrl)
 	  //      thisView.setBackgroundResource(R.drawable.field);
	        contentView.loadUrl("file:///android_asset/index.html");
	     //   contentView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, failUrl)
	         
	              
	            
	        
//	        String text=ANR.getRawResourceAsStringByName(getResources(),"tutor_1");
//	      
//	        
//	        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	        View thisView = inflater.inflate(R.layout.documentation_view, null);
//	        
//	        thisView.setBackgroundResource(R.drawable.field);
//	        setContentView(thisView);
//	        
//	          
//	        contentView = (TextView) thisView.findViewById(R.id.display_text);
//	        
//	        contentView.setTextColor(Color.WHITE);
//	        
//	      //  t.setMovementMethod(ScrollingMovementMethod.getInstance()); 
//	        contentView.setText(text);
//	    
//	        
//	        Button next = (Button) thisView.findViewById(R.id.doc_next_button);
//	        next.setOnClickListener(new View.OnClickListener() {
//	        	    public void onClick(View view) {
//	        	    	currentPage += 1;
//	        	    	if (currentPage > lastPage)
//	        	    		currentPage=1;
//	        	    	
//	        	    		contentView.setText(ANR.getRawResourceAsStringByName(getResources(),"tutor_"+Integer.toString(currentPage)));
//	        	    		contentView.invalidate();
//	        			
//	        			
//	        	            }
//	        	        });
//	        next = (Button) thisView.findViewById(R.id.doc_prev_button);
//	        next.setOnClickListener(new View.OnClickListener() {
//	        	    public void onClick(View view) {
//	        	    	currentPage -= 1;
//	        	    	if (currentPage < 1)
//	        	    		currentPage=lastPage;
//	        	    	
//	        	    		contentView.setText(ANR.getRawResourceAsStringByName(getResources(),"tutor_"+Integer.toString(currentPage)));
//	        	    		contentView.invalidate();
//	        			
//	        			
//	        	            }
//	        	        });
	    }
}
