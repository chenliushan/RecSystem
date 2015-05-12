package org.shan.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.shan.domain.DbSetting;
import org.shan.domain.AnalysedModel;
import org.shan.model.CourseModel;
import org.shan.recommender.ItemBaseRecommender;
import org.shan.recommender.UserBasedRecommender;

public class DatabaseService {

	private DbSetting dbSetting;
	private DataModel model;

	public DatabaseService(DbSetting dbSetting) {
		super();
		this.dbSetting = dbSetting;
	}
	public DataModel newModel(){
		try {
			model = new CourseModel(dbSetting.getDatabaseDriver(),dbSetting.getDatabaseURL(),dbSetting.getDatabaseUser(),dbSetting.getDatabasePassword());
				
		} catch (TasteException e) {
			e.printStackTrace();
		}
		return model;
	}
	public String newRecommend(int userID,int howMany){
		try {
				Recommender recommender = new ItemBaseRecommender(newModel());
				System.out.println("recommender"+recommender);
				List<RecommendedItem> items = recommender
						.recommend(userID, howMany);
				return items.toString();
		} catch (TasteException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}