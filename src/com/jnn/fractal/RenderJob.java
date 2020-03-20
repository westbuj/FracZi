package com.jnn.fractal;

import java.io.IOException;
import java.io.StringReader;

import com.jnn.expressions.Expression;

import com.jnn.util.ANR;
import com.jnn.util.SimpleDOMParser;
import com.jnn.util.SimpleElement;

import android.graphics.Canvas;
import android.os.Handler;

public class RenderJob {
	
	//temp Vars
	
	public double currentInfinity; //VAR M
	public double currentIts; //VAR L
	public double currentMaxIts; //VAR P
	public double currentZa; // A or part of Z
	public double currentZb; // A or part of Z
	public double currentX; // x or part of c	
	public double currentY; // y or part of c
	public double currentDist; // y or part of c
	
	public int currentRX = 0; // x or part of c
	public int currentRY = 0; // x or part of c

	public Expression iExp =null;
	public double x1,y1,x2,y2, infinity,aSeed,bSeed;
	public int w,h,its;
	public char target = 'S';
	public String equation = "";
	public boolean averageFlag=false;
	public int averageRadius=1;
	public double averageWeight=1;
	public boolean progressiveFlag=false;
	public boolean saveFlag=false;
	public String startInfinity="4";
	public Expression startInf_Eq=null;
	//public double infinity=Double.MAX_VALUE;
	public String startIts="4";
	public Expression startIts_Eq=null;
	public String infinityIncrement="";
	public Expression infIncExp = null;
	public String targetFilename="";
	public int[] imageArray=null;
	public Handler updateHandler=null;
	public Canvas bufferCanvas=null;
	public double progWeight=1;
	
	public String redEquation = "";
	public Expression rExp = null;
	public String blueEquation = "";
	public Expression bExp = null;
	public String greenEquation = "";
	public Expression gExp = null;
	
	public String itsIncEquation="";
	public Expression itsIncExp = null;
	
	public int averagePasses=1;
	
	public static final char RGB_COLOR_MODE = 0;
	public static final char CYMK_COLOR_MODE = 1;
	public static final char HSL_COLOR_MODE = 2;
	
	public char colorMode = RenderJob.RGB_COLOR_MODE;
	
	public String CyanEquation = "";
	public Expression cyanExp = null;
	public String YellowEquation = "";
	public Expression yellowExp = null;
	public String magentaEquation = "";
	public Expression magentaExp = null;
	public String blackEquation = "";
	public Expression blackExp = null;
	
	public String HueEquation = "";
	public Expression HueExp = null;
	public String SaturationEquation = "";
	public Expression SaturationExp = null;
	public String LevelEquation = "";
	public Expression LevelExp = null;
	
	public boolean resumeJob = false;
	
	
	
	
	public RenderJob(){
		
	}
	public RenderJob(double ix1, double iy1, double ix2, double iy2, int iIterations, double iInfinity,
			double iAseed, double iBseed, int renderWidth, int renderHeight, char iTarget, int[] iImageArray,
			Handler iUpdateHandler, Canvas iBufferCanvas, String iEquation, boolean averaging, int avgPass, int iRadius, double originalWeight,
			boolean iProgressiveFlag, double iProgWeight,String istartIts, String iItsIncEquation, String iStartInfinity, String iInfinityIncrementEquation,
			String iRedEquation,String iGreenEquation,String iBlueEquation,
			String iTargetFilename)
	{
		
		this.updateHandler = iUpdateHandler;
		this.x1 = ix1;
		this.y1 = iy1;
		this.x2 = ix2;
		this.y2 = iy2;
		this.infinity = iInfinity;
		this.aSeed = iAseed;
		this.bSeed = iBseed;
		this.w =renderWidth;
		this.h =renderHeight;
		this.its = iIterations;
		this.target = iTarget;
		this.equation=iEquation;
		this.averageFlag =averaging;
		this.averageRadius=iRadius;
		this.averageWeight=originalWeight;
		this.progressiveFlag=iProgressiveFlag;
		this.startInfinity=iStartInfinity;
		this.infinityIncrement=iInfinityIncrementEquation;
		this.targetFilename=iTargetFilename;
		this.startIts = istartIts;
		this.bufferCanvas = iBufferCanvas;
		this.imageArray = iImageArray;
		this.progWeight=iProgWeight;
		this.redEquation = iRedEquation;
		this.greenEquation = iGreenEquation;
		this.blueEquation = iBlueEquation;
		this.itsIncEquation = iItsIncEquation;
		
		this.y2=MainActivity.getX2_d(x1, y1, x2, w,h);
		
		
		this.averagePasses=avgPass;
		
			
	}
	
	public String getXML_String(){
		String oString = "";
		
		oString +="<fracZi>\n";
		

		oString += "<A_SEED value='"+Double.toString(this.aSeed)+"'/>\n";//MainActivity.rj.aSeed = CustomizeActivity.getDoubleValue("A_SEED");
		
		oString += "<B_SEED value='"+Double.toString(this.bSeed)+"'/>\n";//MainActivity.rj.aSeed = CustomizeActivity.getDoubleValue("A_SEED");MainActivity.rj.bSeed = CustomizeActivity.getDoubleValue("B_SEED");
		
		oString += "<EQ value='"+this.equation+"'/>\n";//MainActivity.rj.equation = CustomizeActivity.getTextValue("EQ");
		oString += "<HEIGHT value='"+Double.toString(this.h)+"'/>\n";//MainActivity.rj.equation = CustomizeActivity.getTextValue("EQ");
		oString += "<WIDTH value='"+Double.toString(this.w)+"'/>\n";//MainActivity.rj.equation = CustomizeActivity.getTextValue("EQ");
		
		oString += "<X1 value='"+Double.toString(this.x1)+"'/>\n";//MainActivity.rj.x1 = CustomizeActivity.getDoubleValue("X1");
		oString += "<X2 value='"+Double.toString(this.x2)+"'/>\n";//MainActivity.rj.x2 = CustomizeActivity.getDoubleValue("X2");
		oString += "<Y1 value='"+Double.toString(this.y1)+"'/>\n";//MainActivity.rj.y1 = CustomizeActivity.getDoubleValue("Y1");
		oString += "<Y2 value='"+Double.toString(this.y2)+"'/>\n";//MainActivity.rj.y2 = MainActivity.getX2_d(MainActivity.rj.x1, MainActivity.rj.y1, MainActivity.rj.x2, MainActivity.rj.w,MainActivity.rj.h);
		 
		oString += "<MAX_INF value='"+Double.toString(this.infinity)+"'/>\n";//MainActivity.rj.infinity = CustomizeActivity.getDoubleValue("MAX_INF");
		oString += "<START_INF value='"+this.startInfinity+"'/>\n";//MainActivity.rj.startInfinity = CustomizeActivity.getDoubleValue("START_INF");
		oString += "<INF_INC value='"+this.infinityIncrement+"'/>\n";//MainActivity.rj.infinityIncrement = CustomizeActivity.getTextValue("INF_INC");
		  
		 
		oString += "<SAVE_FLAG value='"+Boolean.toString(this.saveFlag)+"'/>\n";//MainActivity.rj.progressiveFlag = CustomizeActivity.getBooleanValue("PROG_FLAG");
		oString += "<PROG_WEIGHT value='"+Double.toString(this.progWeight)+"'/>\n";//MainActivity.rj.progWeight = CustomizeActivity.getDoubleValue("PROG_WEIGHT");
		oString += "<MAX_ITS value='"+Double.toString(this.its)+"'/>\n";//MainActivity.rj.its = (int) CustomizeActivity.getDoubleValue("MAX_ITS");
		oString += "<START_ITS value='"+this.startIts+"'/>\n";//MainActivity.rj.startIts = (int) CustomizeActivity.getDoubleValue("START_ITS");
		oString += "<ITS_INC value='"+this.itsIncEquation+"'/>\n";//MainActivity.rj.itsIncEquation = CustomizeActivity.getTextValue("ITS_INC");
		
		oString += "<COLOR_MODE value='"+ Integer.toString(this.colorMode)+"'/>\n";//MainActivity.rj.redEquation =CustomizeActivity.getTextValue("RED_EQ");
		
		
		oString += "<RED_EQ value='"+this.redEquation+"'/>\n";//MainActivity.rj.redEquation =CustomizeActivity.getTextValue("RED_EQ");
		oString += "<GREEN_EQ value='"+this.greenEquation+"'/>\n";//MainActivity.rj.greenEquation =CustomizeActivity.getTextValue("GREEN_EQ");
		oString += "<BLUE_EQ value='"+this.blueEquation+"'/>\n";//MainActivity.rj.blueEquation =CustomizeActivity.getTextValue("BLUE_EQ");
		
		oString += "<CYAN_EQ value='"+this.CyanEquation+"'/>\n";//MainActivity.rj.redEquation =CustomizeActivity.getTextValue("RED_EQ");
		oString += "<YELLOW_EQ value='"+this.YellowEquation+"'/>\n";//MainActivity.rj.greenEquation =CustomizeActivity.getTextValue("GREEN_EQ");
		oString += "<MAGENTA_EQ value='"+this.magentaEquation+"'/>\n";//MainActivity.rj.blueEquation =CustomizeActivity.getTextValue("BLUE_EQ");
		oString += "<BLACK_EQ value='"+this.blackEquation+"'/>\n";//MainActivity.rj.blueEquation =CustomizeActivity.getTextValue("BLUE_EQ");
		
		oString += "<HUE_EQ value='"+this.HueEquation+"'/>\n";//MainActivity.rj.redEquation =CustomizeActivity.getTextValue("RED_EQ");
		oString += "<SATURATION_EQ value='"+this.SaturationEquation+"'/>\n";//MainActivity.rj.greenEquation =CustomizeActivity.getTextValue("GREEN_EQ");
		oString += "<LEVEL_EQ value='"+this.LevelEquation+"'/>\n";//MainActivity.rj.blueEquation =CustomizeActivity.getTextValue("BLUE_EQ");
		
		
		
		oString += "<AVG_FLAG value='"+Boolean.toString(this.averageFlag)+"'/>\n";//MainActivity.rj.averageFlag = CustomizeActivity.getBooleanValue("AVG_FLAG");
		
		       
		oString += "<AVG_WEIGHT value='"+Double.toString(this.averageWeight)+"'/>\n";// MainActivity.rj.averageWeight = (int) CustomizeActivity.getDoubleValue("AVG_WEIGHT");
		oString += "<AVG_RADIUS value='"+Double.toString(this.averageRadius)+"'/>\n";//MainActivity.rj.averageRadius = (int) CustomizeActivity.getDoubleValue("AVG_RADIUS");
		oString += "<AVG_PASSES value='"+Double.toString(this.averagePasses)+"'/>\n";//MainActivity.rj.averagePasses = (int) CustomizeActivity.getDoubleValue("AVG_PASSES");
		
		
		oString += "<CUR_INF value='" + Double.toString(currentInfinity) + "'/>\n";
		oString += "<CUR_ITS value='" + Double.toString(currentMaxIts) + "'/>\n";
		
		oString += "<CUR_X value='" + Integer.toString(currentRX) + "'/>\n";
		oString += "<CUR_Y value='" + Integer.toString(currentRY) + "'/>\n";
		
		oString += "<RESUME value='"+Boolean.toString(this.resumeJob)+"'/>\n";//MainActivity.rj.averageFlag = CustomizeActivity.getBooleanValue("AVG_FLAG");
		
		
		oString +="</fracZi>";
			
		return oString;
	}
	
	public String getDetail_String(){
		String oString = "";
		
		oString +="fracZi render details\n";
		oString += "Z' = "+this.equation+"\n";
		oString += "A = "+Double.toString(this.aSeed)+"\n";
		oString += "B = "+Double.toString(this.bSeed)+"\n";
		

		oString += "X1= "+Double.toString(this.x1)+"\n";
		oString += "Y1= "+Double.toString(this.y1)+"\n";
		oString += "X2= "+Double.toString(this.x2)+"\n";
		oString += "Y2= "+Double.toString(this.y2)+"\n";
		
		oString += "ITS="+Double.toString(this.currentIts)+"\n";
		oString += "INF="+Double.toString(this.currentInfinity)+"\n";
		
		if (this.colorMode == RenderJob.RGB_COLOR_MODE)
		{
			oString += "R = "+this.redEquation+"\n";
			oString += "G = "+this.greenEquation+"\n";
			oString += "B = "+this.blueEquation+"\n";
			
		}
		
		if (this.colorMode == RenderJob.CYMK_COLOR_MODE)
		{
			oString += "C = "+this.CyanEquation+"\n";
			oString += "Y = "+this.YellowEquation+"\n";
			oString += "M = "+this.magentaEquation+"\n";
			oString += "K = "+this.blackEquation+"\n";
			
		}
		if (this.colorMode == RenderJob.HSL_COLOR_MODE)
		{
			oString += "H = "+this.HueEquation+"\n";
			oString += "S = "+this.SaturationEquation+"\n";
			oString += "L = "+this.LevelEquation+"\n";
			
			
		}
			
		return oString;
	}

	
	public static RenderJob createFromXmlString(String XML){
		StringReader sR = new StringReader(XML);
		SimpleDOMParser dP = new SimpleDOMParser();

		SimpleElement cDoc = null;
		RenderJob rJob = new RenderJob();

		try {
			cDoc = dP.parse(sR);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		Object[] e = cDoc.getChildElements();// .getDocumentElement();//.getFirstChild();



		for (int j = 0; j < e.length; j++) {

			SimpleElement n = (SimpleElement) e[j];



			if (n.getTagName().equals("EQ")) 		rJob.equation = n.getAttribute("value");
			if (n.getTagName().equals("A_SEED")) 		rJob.aSeed = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("B_SEED")) 		rJob.bSeed = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("X1")) 		rJob.x1 = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("X2")) 		rJob.x2 = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("Y1")) 		rJob.y1 = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("Y2")) 		rJob.y2 = Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("MAX_INF")) 		rJob.infinity = Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("START_INF")) 		rJob.startInfinity = n.getAttribute("value");
		
			if (n.getTagName().equals("INF_INC")) 		rJob.infinityIncrement = n.getAttribute("value");
			
			if (n.getTagName().equals("SAVE_FLAG")) 		rJob.saveFlag = Boolean.parseBoolean(n.getAttribute("value"));
			
			if (n.getTagName().equals("PROG_FLAG")) 		rJob.progressiveFlag = Boolean.parseBoolean(n.getAttribute("value"));
			if (n.getTagName().equals("PROG_WEIGHT")) 		rJob.progWeight = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("MAX_ITS")) 		rJob.its = (int)Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("START_ITS")) 		rJob.startIts = n.getAttribute("value");
			if (n.getTagName().equals("ITS_INC")) 		rJob.itsIncEquation = n.getAttribute("value");
			
			
			if (n.getTagName().equals("COLOR_MODE")) 		rJob.colorMode = (char)Double.parseDouble(n.getAttribute("value"));
			
			
			if (n.getTagName().equals("RED_EQ")) 		rJob.redEquation = n.getAttribute("value");
			if (n.getTagName().equals("BLUE_EQ")) 		rJob.blueEquation = n.getAttribute("value");
			if (n.getTagName().equals("GREEN_EQ")) 		rJob.greenEquation = n.getAttribute("value");
			
			if (n.getTagName().equals("HUE_EQ")) 		rJob.HueEquation = n.getAttribute("value");
			if (n.getTagName().equals("SATURATION_EQ")) 		rJob.SaturationEquation = n.getAttribute("value");
			if (n.getTagName().equals("LEVEL_EQ")) 		rJob.LevelEquation = n.getAttribute("value");
			
			if (n.getTagName().equals("CYAN_EQ")) 		rJob.CyanEquation = n.getAttribute("value");
			if (n.getTagName().equals("MAGENTA_EQ")) 		rJob.magentaEquation = n.getAttribute("value");
			if (n.getTagName().equals("YELLOW_EQ")) 		rJob.YellowEquation = n.getAttribute("value");
			if (n.getTagName().equals("BLACK_EQ")) 		rJob.blackEquation = n.getAttribute("value");
			
			
			if (n.getTagName().equals("AVG_FLAG")) 		rJob.averageFlag = Boolean.parseBoolean(n.getAttribute("value"));
			
			if (n.getTagName().equals("AVG_WEIGHT")) 		rJob.averageWeight = Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("AVG_RADIUS")) 		rJob.averageRadius = (int)Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("AVG_PASSES")) 		rJob.averagePasses = (int)Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("HEIGHT")) 		rJob.h = (int)Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("WIDTH")) 		rJob.w = (int)Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("CUR_INF")) 		rJob.currentInfinity = (int)Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("CUR_ITS")) 		rJob.currentMaxIts = (int)Double.parseDouble(n.getAttribute("value"));
			
			if (n.getTagName().equals("CUR_X")) 		rJob.currentRX = (int)Double.parseDouble(n.getAttribute("value"));
			if (n.getTagName().equals("CUR_Y")) 		rJob.currentRY = (int)Double.parseDouble(n.getAttribute("value"));		
			
			
			
				
			
		}
		
		
		
		return rJob;
	}
}
