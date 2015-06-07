package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Recommend {
	
	/**
	 * 
	 * @Title getUserSimilar
	 * @Class testRecommend
	 * @return double
	 * @param pm1
	 * @param pm2
	 * @return
	 * @Description获取两个用户之间的皮尔逊相似度,相关系数的绝对值越大,相关度越大
	 */
	public static double getUserSimilar(Map<String, Integer> pm1,
			Map<String, Integer> pm2) {
		int n = 0;// 数量n
		int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
		int sx = 0;// Σx=x1+x2+....xn
		int sy = 0;// Σy=y1+y2+...yn
		int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
		int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
		for (Entry<String, Integer> pme : pm1.entrySet()) {
			String key = pme.getKey();
			Integer x = pme.getValue();
			Integer y = pm2.get(key);
			if (x != null && y != null) {
				n++;
				sxy += x * y;
				sx += x;
				sy += y;
				sx2 += Math.pow(x, 2);
				sy2 += Math.pow(y, 2);
			}
		}
		// p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
		double sd = sxy - sx * sy / n;
		double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n)
				* (sy2 - Math.pow(sy, 2) / n));
		return Math.abs(sm == 0 ? 1 : sd / sm);
	}

	/**
	 * 
	 * @Title getEuclidDistance
	 * @Class testRecommend
	 * @return double
	 * @param pm1
	 * @param pm2
	 * @return
	 * @Description获取两个用户之间的欧几里得距离,距离越小越好
	 */
	public static double getEuclidDistance(Map<String, Integer> pm1,
			Map<String, Integer> pm2) {
		double totalscore = 0.0;
		for (Entry<String, Integer> test : pm1.entrySet()) {
			String key = test.getKey();
			Integer a1 = pm1.get(key);
			Integer b1 = pm2.get(key);
			if (a1 != null && b1 != null) {
				double a = Math.pow(a1 - b1, 2);
				totalscore += Math.abs(a);
			}
		}
		return Math.sqrt(totalscore);
	}

	/**
	 * 
	 * @Title getRecommend
	 * @Class testRecommend
	 * @return String
	 * @param simUserObjMap
	 * @param simUserSimMap
	 * @return
	 * @Description根据相关系数得到推荐物品
	 */
	public static String getRecommend(
			Map<String, Map<String, Integer>> simUserObjMap,
			Map<String, Double> simUserSimMap) {
		Map<String, Double> objScoreMap = new HashMap<String, Double>();
		for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap
				.entrySet()) {//遍历用户与物品关系表
			String user = simUserEn.getKey();//拿到用户名
			double sim = simUserSimMap.get(user);//拿到用户相似度
			for (Entry<String, Integer> simObjEn : simUserEn.getValue()
					.entrySet()) {//遍历物品关系
				double objScore = sim * simObjEn.getValue();//计算物品得分
				String objName = simObjEn.getKey();
				if (objScoreMap.get(objName) == null) {
					objScoreMap.put(objName, objScore);
				} else {
					double totalScore = objScoreMap.get(objName);
					objScoreMap.put(objName, totalScore + objScore);
				}
			}
		}
		List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(
				objScoreMap.entrySet());
		Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {//按照物品得分排序
			public int compare(Map.Entry<String, Double> o1,
					Map.Entry<String, Double> o2) {
				Double a = o1.getValue() - o2.getValue();
				if (a == 0) {
					return 0;
				} else if (a > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return enList.get(enList.size() - 1).getKey();
	}
}
