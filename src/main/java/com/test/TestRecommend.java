package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TestRecommend {
	 public static String testRecommend() {  
		 //用户偏好表
		    Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();  
		    Map<String, Integer> pref1 = new HashMap<String, Integer>();  
		    pref1.put("A", 3);  
		    pref1.put("B", 4);  
		    pref1.put("C", 3);  
		    pref1.put("D", 5);  
		    pref1.put("E", 1);  
		    pref1.put("F", 4);  
		    userPerfMap.put("p1", pref1);  
		    Map<String, Integer> pref2 = new HashMap<String, Integer>();  
		    pref2.put("A", 2);  
		    pref2.put("B", 4);  
		    pref2.put("C", 4);  
		    pref2.put("D", 5);  
		    pref2.put("E", 3);  
		    pref2.put("F", 2);  
		    userPerfMap.put("p2", pref2);  
		    Map<String, Integer> pref3 = new HashMap<String, Integer>();  
		    pref3.put("A", 3);  
		    pref3.put("B", 5);  
		    pref3.put("C", 4);  
		    pref3.put("D", 5);  
		    pref3.put("E", 2);  
		    pref3.put("F", 1);  
		    userPerfMap.put("p3", pref3);  
		    Map<String, Integer> pref4 = new HashMap<String, Integer>();  
		    pref4.put("A", 2);  
		    pref4.put("B", 2);  
		    pref4.put("C", 3);  
		    pref4.put("D", 4);  
		    pref4.put("E", 3);  
		    pref4.put("F", 2);  
		    userPerfMap.put("p4", pref4);  
		    Map<String, Integer> pref5 = new HashMap<String, Integer>();  
		    pref5.put("A", 4);  
		    pref5.put("B", 4);  
		    pref5.put("C", 4);  
		    pref5.put("D", 5);  
		    pref5.put("E", 1);  
		    pref5.put("F", 0);  
		    userPerfMap.put("p5", pref5);  
		    Map<String, Double> simUserSimMap = new HashMap<String, Double>();
		    
		    String output1 = "皮尔逊相关系数:", output2 = "欧几里得距离:";  
		    //遍历用户偏好
		    for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {  
		        String userName = userPerfEn.getKey();  
		        if (!"p5".equals(userName)) {  //计算用户之间的相似度
		        double sim = Recommend.getUserSimilar(pref5, userPerfEn.getValue());  
		        double distance = Recommend.getEuclidDistance(pref5, userPerfEn.getValue());  
		        output1 += "p5与" + userName + "之间的相关系数:" + sim + ",";  
		        output2 += "p5与" + userName + "之间的距离:" + distance + ",";  
		        simUserSimMap.put(userName, sim);  //用simUserSimMap纪录用户相似度
		        }  
		    }  
		    System.out.println(output1);  
		    System.out.println(output2);  
		    //用户与物品的关系表
		    Map<String, Map<String, Integer>> simUserObjMap = new HashMap<String, Map<String, Integer>>();  
		    Map<String, Integer> pobjMap1 = new HashMap<String, Integer>();  
		    pobjMap1.put("一夜惊喜", 3);  
		    pobjMap1.put("环太平洋", 4);  
		    pobjMap1.put("变形金刚", 3);  
		    simUserObjMap.put("p1", pobjMap1);  
		    Map<String, Integer> pobjMap2 = new HashMap<String, Integer>();  
		    pobjMap2.put("一夜惊喜", 5);  
		    pobjMap2.put("环太平洋", 1);  
		    pobjMap2.put("变形金刚", 2);  
		    simUserObjMap.put("p2", pobjMap2);  
		    Map<String, Integer> pobjMap3 = new HashMap<String, Integer>();  
		    pobjMap3.put("一夜惊喜", 2);  
		    pobjMap3.put("环太平洋", 5);  
		    pobjMap3.put("变形金刚", 5);  
		    simUserObjMap.put("p3", pobjMap3);  
		    //得到推荐物品
		    System.out.println("根据系数推荐:" + Recommend.getRecommend(simUserObjMap, simUserSimMap)); 
		    return Recommend.getRecommend(simUserObjMap, simUserSimMap);
		    }  
}
