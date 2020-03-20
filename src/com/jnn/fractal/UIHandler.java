package com.jnn.fractal;

import com.jnn.expressions.Expression;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;


public class UIHandler implements OnClickListener {

	public void onClick(View arg0) {
		
		
			try {
				
				//validate data
				
			//	dafadf
				
				//EditText et = null;
				String error = "";
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("A_SEED")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("A_SEED").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage.sendEmptyMessage(0);
					return;
				}else
				{
					CustomizeActivity.vStack.get("A_SEED").setBackgroundColor(Color.WHITE);
				}
				
				
				
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("B_SEED")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("B_SEED").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage.sendEmptyMessage(0);
					return;
				}else
				{
					CustomizeActivity.vStack.get("B_SEED").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("X1")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("X1").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage.sendEmptyMessage(0);
					return;
				}else
				{
					CustomizeActivity.vStack.get("X2").setBackgroundColor(Color.WHITE);
				}
				
				
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("X2")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("X2").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage.sendEmptyMessage(0);
					return;
				}else
				{
					CustomizeActivity.vStack.get("X2").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("Y1")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("Y1").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage.sendEmptyMessage(0);
					return;
				}else
				{
					CustomizeActivity.vStack.get("Y1").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("MAX_INF")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("MAX_INF").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("MAX_INF").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("MAX_INF").setBackgroundColor(Color.WHITE);
				}
				
				error = Number_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("MAX_ITS")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("MAX_ITS").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("MAX_ITS").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("MAX_ITS").setBackgroundColor(Color.WHITE);
				}
				
				
				
				error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("EQ")).getText().toString());
				if (error.length()>0)
				{
					//CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("EQ").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("EQ").requestFocus();
					
					return;
				}else
				{
					CustomizeActivity.vStack.get("EQ").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("START_INF")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("START_INF").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("START_INF").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("START_INF").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("INF_INC")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("INF_INC").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("INF_INC").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("INF_INC").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("START_ITS")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("START_ITS").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("START_ITS").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("START_ITS").setBackgroundColor(Color.WHITE);
				}
				
				
				error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("ITS_INC")).getText().toString());
				if (error.length()>0)
				{
					CustomizeActivity.lastError = error;
					CustomizeActivity.vStack.get("ITS_INC").setBackgroundColor(Color.MAGENTA);
					CustomizeActivity.showMessage(error);
					CustomizeActivity.vStack.get("ITS_INC").requestFocus();
					return;
				}else
				{
					CustomizeActivity.vStack.get("ITS_INC").setBackgroundColor(Color.WHITE);
				}
				
				
				switch(CustomizeActivity.getColorMode((RadioGroup) CustomizeActivity.vStack.get("COLOR_MODE")))
				{
					case RenderJob.RGB_COLOR_MODE:
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("RED_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("RED_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("RED_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("RED_EQ").setBackgroundColor(Color.WHITE);
						}
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("GREEN_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("GREEN_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("GREEN_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("GREEN_EQ").setBackgroundColor(Color.WHITE);
						}
						
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("BLUE_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("BLUE_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("BLUE_EQ").requestFocus();
							
							return;
						}else
						{
							CustomizeActivity.vStack.get("BLUE_EQ").setBackgroundColor(Color.WHITE);
						}
						
						
						
						
						break;
						
						
						
						
						
						case RenderJob.CYMK_COLOR_MODE:
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("CYAN_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("CYAN_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("CYAN_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("CYAN_EQ").setBackgroundColor(Color.WHITE);
						}
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("YELLOW_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("YELLOW_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("YELLOW_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("YELLOW_EQ").setBackgroundColor(Color.WHITE);
						}
						
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("MAGENTA_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("MAGENTA_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("MAGENTA_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("MAGENTA_EQ").setBackgroundColor(Color.WHITE);
						}
						
						error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("BLACK_EQ")).getText().toString());
						if (error.length()>0)
						{
							CustomizeActivity.lastError = error;
							CustomizeActivity.vStack.get("BLACK_EQ").setBackgroundColor(Color.MAGENTA);
							CustomizeActivity.showMessage(error);
							CustomizeActivity.vStack.get("BLACK_EQ").requestFocus();
							return;
						}else
						{
							CustomizeActivity.vStack.get("BLACK_EQ").setBackgroundColor(Color.WHITE);
						}
						
						
						
						
						break;
						
						
						
						case RenderJob.HSL_COLOR_MODE:
							
							error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("HUE_EQ")).getText().toString());
							if (error.length()>0)
							{
								CustomizeActivity.lastError = error;
								CustomizeActivity.vStack.get("HUE_EQ").setBackgroundColor(Color.MAGENTA);
								CustomizeActivity.showMessage(error);
								CustomizeActivity.vStack.get("HUE_EQ").requestFocus();
								return;
							}else
							{
								CustomizeActivity.vStack.get("HUE_EQ").setBackgroundColor(Color.WHITE);
							}
							
							error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("SATURATION_EQ")).getText().toString());
							if (error.length()>0)
							{
								CustomizeActivity.lastError = error;
								CustomizeActivity.vStack.get("SATURATION_EQ").setBackgroundColor(Color.MAGENTA);
								CustomizeActivity.showMessage(error);
								CustomizeActivity.vStack.get("SATURATION_EQ").requestFocus();
								return;
							}else
							{
								CustomizeActivity.vStack.get("SATURATION_EQ").setBackgroundColor(Color.WHITE);
							}
							
							
							error = Formula_OnFocusChangeListener.validateData(((EditText)CustomizeActivity.vStack.get("LEVEL_EQ")).getText().toString());
							if (error.length()>0)
							{
								CustomizeActivity.lastError = error;
								CustomizeActivity.vStack.get("LEVEL_EQ").setBackgroundColor(Color.MAGENTA);
								CustomizeActivity.showMessage(error);
								CustomizeActivity.vStack.get("LEVEL_EQ").requestFocus();
								return;
							}else
							{
								CustomizeActivity.vStack.get("LEVEL_EQ").setBackgroundColor(Color.WHITE);
							}
							
							
							
							
							break;						
						
						
						
						
						
						
				}
				
				
				
				
				
				
				
				
				MainActivity.rj.aSeed = CustomizeActivity.getDoubleValue("A_SEED");
				MainActivity.rj.bSeed = CustomizeActivity.getDoubleValue("B_SEED");
				
				MainActivity.rj.equation = CustomizeActivity.getTextValue("EQ");
				
				MainActivity.rj.x1 = CustomizeActivity.getDoubleValue("X1");
				MainActivity.rj.x2 = CustomizeActivity.getDoubleValue("X2");
				MainActivity.rj.y1 = CustomizeActivity.getDoubleValue("Y1");
				
				MainActivity.rj.y2 = MainActivity.getX2_d(MainActivity.rj.x1, MainActivity.rj.y1, MainActivity.rj.x2, MainActivity.rj.w,MainActivity.rj.h);
				
				MainActivity.rj.infinity = CustomizeActivity.getDoubleValue("MAX_INF");
				MainActivity.rj.startInfinity = CustomizeActivity.getTextValue("START_INF");
				MainActivity.rj.infinityIncrement = CustomizeActivity.getTextValue("INF_INC");
				
			//	MainActivity.rj.progressiveFlag = CustomizeActivity.getBooleanValue("PROG_FLAG");
				//MainActivity.rj.progWeight = CustomizeActivity.getDoubleValue("PROG_WEIGHT");
				MainActivity.rj.its = (int) CustomizeActivity.getDoubleValue("MAX_ITS");
				MainActivity.rj.startIts =  CustomizeActivity.getTextValue("START_ITS");
				//MainActivity.rj.startIts_Eq = 
				MainActivity.rj.itsIncEquation = CustomizeActivity.getTextValue("ITS_INC");
				
				
				
				
				MainActivity.rj.colorMode = CustomizeActivity.getColorMode((RadioGroup) CustomizeActivity.vStack.get("COLOR_MODE"));//(char) CustomizeActivity.getDoubleValue("COLOR_MODE");
				
				 
				MainActivity.rj.redEquation =CustomizeActivity.getTextValue("RED_EQ");
				MainActivity.rj.greenEquation =CustomizeActivity.getTextValue("GREEN_EQ");
				MainActivity.rj.blueEquation =CustomizeActivity.getTextValue("BLUE_EQ");

				
				MainActivity.rj.HueEquation =CustomizeActivity.getTextValue("HUE_EQ");
				MainActivity.rj.SaturationEquation =CustomizeActivity.getTextValue("SATURATION_EQ");
				MainActivity.rj.LevelEquation =CustomizeActivity.getTextValue("LEVEL_EQ");
				
				MainActivity.rj.CyanEquation =CustomizeActivity.getTextValue("CYAN_EQ");
				MainActivity.rj.YellowEquation =CustomizeActivity.getTextValue("YELLOW_EQ");
				MainActivity.rj.magentaEquation =CustomizeActivity.getTextValue("MAGENTA_EQ");				
				MainActivity.rj.blackEquation =CustomizeActivity.getTextValue("BLACK_EQ");
				
				
				MainActivity.rj.saveFlag = CustomizeActivity.getBooleanValue("SAVE_FLAG");
				
				       
			//     MainActivity.rj.averageWeight = (int) CustomizeActivity.getDoubleValue("AVG_WEIGHT");
			//     MainActivity.rj.averageRadius = (int) CustomizeActivity.getDoubleValue("AVG_RADIUS");
			//     MainActivity.rj.averagePasses = (int) CustomizeActivity.getDoubleValue("AVG_PASSES");
				if (arg0.getTag().equals("ESCAPE_RENDER"))
					MainActivity.mQueue.addTask(new RenderThread(MainActivity.rj));
				
				if (arg0.getTag().equals("ORBIT_RENDER"))
					MainActivity.mQueue.addTask(new OrbitRenderThread(MainActivity.rj));
				
				 CustomizeActivity.closeActivity.sendEmptyMessage(0);
				 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		
		
				

	}

}