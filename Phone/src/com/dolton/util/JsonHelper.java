package com.dolton.util;




import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class JsonHelper {
	
	private JsonHelper(){}
	
	public static String object2str(Object obj){

		JSONObject jso = JSONObject.fromObject(obj);

		return jso.toString();

	}
	
	
	public static Object str2Object(String str, Class cls){
		JSONObject jso = JSONObject.fromObject(str);
		Object obj = JSONObject.toBean(jso,cls);
		return obj;

	}
	

	public static Map<String, Object> str2Map(String json) throws IOException {

		ObjectMapper om = new ObjectMapper();
		Map<String, Object> map = om.readValue(json, Map.class);
		return map;
	}
	
	public static String map2Str(Map<String, Object> map){
		JSONObject jso = JSONObject.fromObject(map);
		return jso.toString();
	}


	public static void main(String[] args){

//		Map<String, Object> map = new HashMap<String, Object>();
//		FpLxVo fplx = new FpLxVo();
//		fplx.setFpdm("sdfsdf");
//		fplx.setFphm("sdfsdfsdf");
//
//		map.put("code", "00000");
//		map.put("fplx", fplx);
//		String str = JsonHelper.map2Str(map);
//		System.out.println(JsonHelper.map2Str(map));
//
////		String xx = "{\"fpdm\":\"sdfsdf\",\"fphm\":\"sdfsdfsdf\"}";
//		String str2 = "{\"code\":\"00000\"}";
//		FplxJsonVo f = (FplxJsonVo)JsonHelper.str2Object(str2, FplxJsonVo.class);
//		System.out.println(f.getFplx() == null);
	}

}
