package com.jnn.fractal;
import com.jnn.expressions.Expression;

import android.graphics.Color;
import android.os.Message;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Number_OnFocusChangeListener implements OnFocusChangeListener
{


public static String validateData( String data){
	
	
	
	if (data.length() == 0)
		return "Number expected";
	
	
	try{
		double d = Double.parseDouble(data);
	}
	catch (Exception e){
		return "Number expected";
	}
	
	
	
		
		
	
	return "";
	
}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		EditText et = (EditText)v;
		
		if (!hasFocus) {
			String ret =  validateData( et.getText().toString());
			if (ret.length() > 0)
			{
				CustomizeActivity.lastError = ret;
				et.setBackgroundColor(Color.MAGENTA);
				CustomizeActivity.showMessage.sendEmptyMessage(0);
				return;
			}
			}
			et.setBackgroundColor(Color.WHITE);
		
			
		
	}
}

