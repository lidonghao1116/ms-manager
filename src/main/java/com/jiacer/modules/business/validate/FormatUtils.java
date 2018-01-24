package com.jiacer.modules.business.validate;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/***
 * 验证工具类
 * @author ZK-BOND
 *
 */
public class FormatUtils {
	
	/***
	 * 验证金额格式
	 * @param str
	 * @return
	 */
	public static boolean isAmountOfMoney(String str){   
		Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
		Matcher match=pattern.matcher(str);   
		if(match.matches()==false){   
			return false;   
		}else{   
			return true;   
		}   
	}
	public static boolean isAmountOfMoney(BigDecimal str){   
		Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
		Matcher match=pattern.matcher(str+"");
		if(match.matches()==false){   
			return false;   
		}else{   
			return true;   
		}   
	}
	
	/** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {  
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str) || isPhone(str);  
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-8])|(14[0-9]))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
    /** 
     * 电话号码验证 
     * @author ：shijing 
     * 2016年12月5日下午4:34:21 
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isPhone(final String str) {  
        Pattern p1 = null, p2 = null;  
        Matcher m = null;  
        boolean b = false;  
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if (str.length() > 9) {  
           m = p1.matcher(str);  
           b = m.matches();  
        } else {  
            m = p2.matcher(str);  
           b = m.matches();  
        }  
        return b;  
    }  
}
