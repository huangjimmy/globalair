import java.util.*
import javax.script.*;
import javax.swing.*;


class Strategy {
	
	String type
	double discount
	String rule
	String description
	
	static constraints = {
		rule(size:0..6000)
	}
	
	String toString()
	{
		return rule+"("+discount+")"
	}
	
	def executeStrategy(def params) 
	{
		println "into executeStrategy("+params+")"
		def s = new HashMap()
		s.reason = "Member Level"
		s.discount = 15
		
		return s
	}
	
	boolean isApplicable(params)
	{
		ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("javascript");
		try {
			def d = params.date
			def flight = params.flight
			def member = params.member
			
		  //jsEngine.put("travelDate", booking.date)
		  //println(d.year+"/"+d.month+"/"+d.date)
		  
		  println "Member is "+member+"\t - \t"+member.ffpLevel
		  jsEngine.put("ffpLevel", member.ffpLevel)
		  
		  try{
		  jsEngine.eval("travelDate = new Date('"+(d.year+1900)+"/" +(d.month+1)+"/"+d.date+"')")
		  jsEngine.eval("travelTime="+" new Date('2000/1/1 "+flight.STD+"')")
		  //println "travelTime="+" new Date('2000/1/1 "+flight.STD+"')"
		  }
		  catch(Exception e)
		  {
		   //ignore
		  }
		  String script = rule;
		  Boolean bool_a = (Boolean) jsEngine.eval("bool_a = ("+script+")")
		  System.out.println(bool_a.toString() + "=(" + script+")")
		  
		  return bool_a
		} catch (Exception ex) {
		     //ex.printStackTrace()
		     return false
		 }    
		
		return false
	}
	
	def getReason(def params)
	{
		return description
	}
	
	def getDiscount(def params)
	{
		return discount
	}
}	
