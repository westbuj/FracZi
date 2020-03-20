package com.jnn.fractal;
import com.jnn.expressions.Expression;

import android.graphics.Color;
import android.os.Message;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class Formula_OnFocusChangeListener implements OnFocusChangeListener
{
//public static String lastError = "";
	static Expression tExp = null;
public static String validateData( String data){
	
	
	
	
	
	
	
	
		try {
			tExp = new Expression(data);
			if (tExp.isError)
				return tExp.lastError;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Error in expression.";
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
			CustomizeActivity.showMessage(v.getTag().toString()+"="+tExp.toString());
			}
			
			et.setBackgroundColor(Color.WHITE);
			//CustomizeActivity.showMessage(v.getTag().toString()+"="+tExp.toString());		
			
		
	}
}

