drop alias if exists TO_DATE;  
create alias TO_DATE as $$  
java.util.Date toDate(String s,String pattern) throws Exception {  
    pattern = pattern.replaceAll("mm","MM");  
    pattern =  pattern.replaceAll("hh24","HH");
	pattern =  pattern.replaceAll("HH24","HH"); 	
    pattern =  pattern.replaceAll("mi","mm");
	pattern =  pattern.replaceAll("MI","mm");	
    return new java.text.SimpleDateFormat(pattern).parse(s);  
}  
$$;  
  
drop alias if exists TO_CHAR;  
create alias TO_CHAR as $$  
String toChar(BigDecimal x, String pattern) throws Exception {  
    return new java.text.DecimalFormat(pattern).format(x);  
}  
$$;