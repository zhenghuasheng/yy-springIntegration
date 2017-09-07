package com.etong.sms.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;
/**
 * List元素通过指定字符串分割，拼接
 * @author Administrator
 *
 */
public class StringUtil {
	public static String listString(List<String> list,String split){
		StringBuffer sb=new StringBuffer();
		if(list==null||list.size()<=0){
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if(i<list.size()-1){
				sb.append(split);
			}
		}
		return sb.toString();
	}
	/**
	 * List元素通过指定编码格式拼接，并以指定字符分割
	 * @param list
	 * @param split
	 * @param charSet
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String listCharSetString(List<String>list,String split,String charset){
		StringBuffer sbf=new StringBuffer();
		if(list==null||list.size()<=0){
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			try {
				String temp=new String(list.get(i).getBytes(),charset);
				String temp1=URLEncoder.encode(temp);
				sbf.append(temp1);
				if(i<list.size()-1){
					sbf.append(split);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sbf.toString();
	}
	/**
	 * 判断号码是否为联通
	 * @param str
	 * @return
	 */
	public static boolean getMoblieType(String mobile){
		return Pattern.matches("^(134|135|136|137|138|139|147|150|151|152|157|181|182|183|187|188)\\d{8}$", mobile ); 
	}
	/**
	 * 检测短信内容是否一样,因此来判定提交短信的方式
	 * @param map
	 * @return
	 */
	public static boolean checkContent(List list){
		boolean f=false;
		
		Object str=list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if(str.equals(list.get(i))){
				f=true;continue;
			}else{
				f=false;return f;
			}
		}
		return f;
	}

	public static void main(String[] args) {
//		String pre_lat = "28.2320730282";
//		String pre_lon = "112.9549992044";
//
//		System.out.println(Float.valueOf(pre_lat) - 28.2300730282);

		String[] a = {"1","2","3","4","5"};

		List<String> list = Arrays.asList(a);

		LinkedList<String> set = new LinkedList<>();
		set.addAll(list);
		for (String index :list) {
			if (index.equals("3") || index.equals("5")) {
				list.remove(index);
				set.addFirst(index);
			}
		}

		for (String index :set) {
			System.out.println(index);
		}
	}
}
