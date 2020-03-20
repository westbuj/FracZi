package com.jnn.fractal;






import java.util.Hashtable;

import com.jnn.expressions.Expression;
import com.jnn.fraczi.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CustomizeActivity extends Activity {
	
	public static String lastError = "";
	
	public static UIHandler butHand = new UIHandler();
	public static Context mContext = null;
//	public static EditText formulaEntry = null;
	public static Hashtable<String, View> vStack = new Hashtable<String, View>();
	
	public static Formula_OnFocusChangeListener cListen = new Formula_OnFocusChangeListener();
	public static Number_OnFocusChangeListener nListen = new Number_OnFocusChangeListener();
	public static Activity thisActivity = null;
	
	public OnCheckedChangeListener colorModeListener=null;
	
	public static Handler showMessage = new Handler() {

		public void handleMessage(Message msg) {
			//MainActivity.updateImage();
			//MainActivity.mView.invalidate();
			Toast.makeText(CustomizeActivity.mContext, (CharSequence) CustomizeActivity.lastError,0).show();
		}
	};
	
	public static Handler closeActivity = new Handler() {

		public void handleMessage(Message msg) {
			//MainActivity.updateImage();
			//MainActivity.mView.invalidate();
			CustomizeActivity.thisActivity.finish();
			
		}
	};

	public static Handler showMessageViaToast = new Handler() {

		public void handleMessage(Message msg) {
			//StringBuffer objectRcvd = (StringBuffer) msg.getData().getParcelable("MyObject");
			String data = (String) msg.obj;
			//MainActivity.updateImage();
			//MainActivity.mView.invalidate();
			Toast.makeText(CustomizeActivity.mContext, data, Toast.LENGTH_LONG).show();
			
		}
	};
	
	public static void showMessage(String m){
		Message msg = new Message();
		msg.obj = m;
		showMessageViaToast.sendMessage(msg);
	}

	public void addView_Interface(String tag, View tView ){
		
		addView_Interface(tag, tView,false);	
		
		
	}
	
	public void addView_Interface(String tag, View tView, boolean formulaEdit){
		
		if (formulaEdit)
			tView.setOnFocusChangeListener(cListen);
		
		tView.setTag(tag);
		vStack.put(tag,tView);
		
		
	}
	
public void addView_Interface(String tag, View tView,Number_OnFocusChangeListener nl){
	
	tView.setOnFocusChangeListener(nl);
	addView_Interface(tag, tView,false);		
		
	}
	
	public void setTextValue(String Key, String value){
	
	EditText txt=(EditText) vStack.get(Key);
	txt.setText(value);
	
	
}
public static String getTextValue(String Key){
	
	EditText txt=(EditText) vStack.get(Key);
	
	return txt.getText().toString();
	//txt.setText(value);
	
	
}

public static boolean getBooleanValue(String Key){
	
	CheckBox txt=(CheckBox) vStack.get(Key);
	
	return txt.isChecked();
	//txt.setText(value);
	
	
}

public static double getDoubleValue(String Key){
	
	EditText txt=(EditText) vStack.get(Key);
	
	return Double.parseDouble(txt.getText().toString());
	//txt.setText(value);
	
	
}

public void setOnColorModeListener(){
	this.colorModeListener=new OnCheckedChangeListener() {
		//CustomizeActivity.getColorMode((RadioGroup) CustomizeActivity.vStack.get("COLOR_MODE"));
		@Override
		public void onCheckedChanged(RadioGroup rg, int checkedId) {
			// TODO Auto-generated method stub
			
			//RadioGroup rg = (RadioGroup)vStack.get("COLOR_MODE");
						
			switch (getColorMode(rg))
			{
			case RenderJob.RGB_COLOR_MODE:
				vStack.get("RED_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("GREEN_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("BLUE_EQ").setBackgroundColor(Color.WHITE);
				
				vStack.get("CYAN_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("YELLOW_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("MAGENTA_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("BLACK_EQ").setBackgroundColor(Color.LTGRAY);
				
				vStack.get("HUE_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("SATURATION_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("LEVEL_EQ").setBackgroundColor(Color.LTGRAY);
				
				break;
			case RenderJob.CYMK_COLOR_MODE:
				vStack.get("RED_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("GREEN_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("BLUE_EQ").setBackgroundColor(Color.LTGRAY);
				
				vStack.get("CYAN_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("YELLOW_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("MAGENTA_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("BLACK_EQ").setBackgroundColor(Color.WHITE);
				
				vStack.get("HUE_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("SATURATION_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("LEVEL_EQ").setBackgroundColor(Color.LTGRAY);
						
				break;
			case RenderJob.HSL_COLOR_MODE:
				vStack.get("RED_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("GREEN_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("BLUE_EQ").setBackgroundColor(Color.LTGRAY);
				
				vStack.get("CYAN_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("YELLOW_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("MAGENTA_EQ").setBackgroundColor(Color.LTGRAY);
				vStack.get("BLACK_EQ").setBackgroundColor(Color.LTGRAY);
			
				
				vStack.get("HUE_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("SATURATION_EQ").setBackgroundColor(Color.WHITE);
				vStack.get("LEVEL_EQ").setBackgroundColor(Color.WHITE);
				
				break;
			}

			
		}
 
	};

}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
        View dbV=null;
        this.mContext = this;
        CustomizeActivity.thisActivity = this;
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbV = inflater.inflate(R.layout.parameter_entry, null);
    
        setOnColorModeListener();
        
       
        addView_Interface("X1", dbV.findViewById(R.id.x1Entry),CustomizeActivity.nListen);
        addView_Interface("Y1", dbV.findViewById(R.id.y1Entry),CustomizeActivity.nListen);
        addView_Interface("X2", dbV.findViewById(R.id.x2Entry),CustomizeActivity.nListen);
        addView_Interface("Y2", dbV.findViewById(R.id.y2Entry),CustomizeActivity.nListen);
        
        addView_Interface("A_SEED", dbV.findViewById(R.id.aSeedEntry),CustomizeActivity.nListen);
        addView_Interface("B_SEED", dbV.findViewById(R.id.bSeedEntry),CustomizeActivity.nListen);
        
        addView_Interface("EQ", dbV.findViewById(R.id.equation_entry), true);
        
        addView_Interface("MAX_INF", dbV.findViewById(R.id.MaxInf_Entry),CustomizeActivity.nListen);
        addView_Interface("MAX_ITS", dbV.findViewById(R.id.Max_Its_Entry),CustomizeActivity.nListen);
        
//        addView_Interface("PROG_WEIGHT", dbV.findViewById(R.id.progressive_weight));
        addView_Interface("START_INF", dbV.findViewById(R.id.startInfEntry), true);
        addView_Interface("INF_INC", dbV.findViewById(R.id.InfIncEntry), true);
        addView_Interface("START_ITS", dbV.findViewById(R.id.StartItsEntry), true);
        addView_Interface("ITS_INC", dbV.findViewById(R.id.ItsIncEntry), true);
        
        RadioGroup rg = (RadioGroup) dbV.findViewById(R.id.colorModeGroup);
        rg.setOnCheckedChangeListener(this.colorModeListener);
        addView_Interface("COLOR_MODE", rg);
        
        
        addView_Interface("RED_EQ", dbV.findViewById(R.id.RedEquationEntry), true);
        addView_Interface("GREEN_EQ", dbV.findViewById(R.id.GreenEquationEntry), true);
        addView_Interface("BLUE_EQ", dbV.findViewById(R.id.BlueEquationEntry), true);
        
        addView_Interface("HUE_EQ", dbV.findViewById(R.id.HueEquationEntry), true);
        addView_Interface("SATURATION_EQ", dbV.findViewById(R.id.SaturationEquationEntry), true);
        addView_Interface("LEVEL_EQ", dbV.findViewById(R.id.LevelEquationEntry), true);
        
        addView_Interface("CYAN_EQ", dbV.findViewById(R.id.CyanEquationEntry), true);
        addView_Interface("MAGENTA_EQ", dbV.findViewById(R.id.MagentaEquationEntry), true);
        addView_Interface("YELLOW_EQ", dbV.findViewById(R.id.YellowEquationEntry), true);
        addView_Interface("BLACK_EQ", dbV.findViewById(R.id.BlackEquationEntry), true);
        
        
        addView_Interface("SAVE_FLAG", dbV.findViewById(R.id.saveEveryRenderOption));
        
//        addView_Interface("PROG_FLAG", dbV.findViewById(R.id.progressive_flag));
        
        
        
        
       /* addView_Interface("AVG_FLAG", dbV.findViewById(R.id.UserAveragecheckBox1));
        addView_Interface("AVG_WEIGHT", dbV.findViewById(R.id.averagingweightEntry));
        addView_Interface("AVG_RADIUS", dbV.findViewById(R.id.averagingRadiusEntry));
        addView_Interface("AVG_PASSES", dbV.findViewById(R.id.averagingPassesEntry));
      */  
        
        
        
        
        setTextValue("X1",Double.toString(MainActivity.rj.x1));
        setTextValue("Y1",Double.toString(MainActivity.rj.y1));
        setTextValue("X2",Double.toString(MainActivity.rj.x2));
        setTextValue("Y2",Double.toString(MainActivity.rj.y2));
        
       
        setTextValue("A_SEED",Double.toString(MainActivity.rj.aSeed));
        setTextValue("B_SEED",Double.toString(MainActivity.rj.bSeed));
       
        setTextValue("EQ",MainActivity.rj.equation);
        
        setTextValue("MAX_INF",Double.toString(MainActivity.rj.infinity));
        setTextValue("MAX_ITS",Double.toString(MainActivity.rj.its));
        
         
        
        
        //setTextValue("SAVE_FLAG",Double.toString(MainActivity.rj.aSeed.progWeight));
        
        ((CheckBox) vStack.get("SAVE_FLAG")).setChecked(MainActivity.rj.saveFlag);
        
      //  ((CheckBox) vStack.get("AVG_FLAG")).setChecked(MainActivity.rj.averageFlag);
        
        /*setTextValue("AVG_WEIGHT", Double.toString(MainActivity.rj.averageWeight));
        setTextValue("AVG_RADIUS", Integer.toString(MainActivity.rj.averageRadius));
        setTextValue("AVG_PASSES", Integer.toString(MainActivity.rj.averagePasses));
      */  
        	
        
        setTextValue("START_INF", MainActivity.rj.startInfinity);
        setTextValue("INF_INC", MainActivity.rj.infinityIncrement);
        setTextValue("START_ITS", MainActivity.rj.startIts);
        setTextValue("ITS_INC", MainActivity.rj.itsIncEquation);
        
        
        
        //setTextValue("COLOR_MODE", Integer.toString(MainActivity.rj.colorMode));
        
        this.setColorMode((RadioGroup)vStack.get("COLOR_MODE"), MainActivity.rj.colorMode);
        
        
        setTextValue("RED_EQ", MainActivity.rj.redEquation);
        setTextValue("GREEN_EQ",  MainActivity.rj.greenEquation);
        setTextValue("BLUE_EQ",  MainActivity.rj.blueEquation);
        
        setTextValue("HUE_EQ", MainActivity.rj.HueEquation);
        setTextValue("SATURATION_EQ",  MainActivity.rj.SaturationEquation);
        setTextValue("LEVEL_EQ",  MainActivity.rj.LevelEquation);
        
        setTextValue("CYAN_EQ", MainActivity.rj.CyanEquation);
        setTextValue("MAGENTA_EQ",  MainActivity.rj.magentaEquation);
        setTextValue("YELLOW_EQ",  MainActivity.rj.YellowEquation);        
        setTextValue("BLACK_EQ", MainActivity.rj.blackEquation);
        
        
        
        
        Button hideView = (Button) dbV.findViewById(R.id.screen_render_button);
        hideView.setTag("ESCAPE_RENDER");
        hideView.setOnClickListener(butHand);
        
        
        hideView = (Button) dbV.findViewById(R.id.screen_render_orbit_button);
        hideView.setTag("ORBIT_RENDER");
        hideView.setOnClickListener(butHand);
        
        
     
        addContentView(dbV, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		
          
        
        
         
		super.onCreate(savedInstanceState);
	}
	
	
	public static char getColorMode(RadioGroup rg)
	{
		
		RadioButton rb = (RadioButton) rg.findViewById(rg.getCheckedRadioButtonId());
		String mode = rb.getText().toString().toUpperCase();
		if (mode.equals("RGB")) return RenderJob.RGB_COLOR_MODE; 
		if (mode.equals("CYMK")) return RenderJob.CYMK_COLOR_MODE;
		if (mode.equals("HSL")) return RenderJob.HSL_COLOR_MODE; 
		
		return 0;
		
	}
	
	public void setColorMode(RadioGroup rg, char mode)
	{
		
		RadioButton rb = null;
		
		switch (mode)
		{
		case RenderJob.RGB_COLOR_MODE:
			rb = (RadioButton) rg.findViewById(R.id.colorMode_RGB);
			rb.setChecked(true);
			break;
			
		case RenderJob.CYMK_COLOR_MODE:
			rb = (RadioButton) rg.findViewById(R.id.ColorMode_CYMK);
			rb.setChecked(true);
			break;			
			
		case RenderJob.HSL_COLOR_MODE:
			rb = (RadioButton) rg.findViewById(R.id.ColorMode_HSL);
			rb.setChecked(true);
			break;
			
			
		}
	
		
	}
	

}
