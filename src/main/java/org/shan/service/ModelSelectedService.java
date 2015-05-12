package org.shan.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.shan.domain.AnalysedModel;
import org.shan.recommender.MyRecommender;

import com.google.gson.Gson;

public class ModelSelectedService {

	private AnalysedModel analysedModel;
	private DataModel dataModel;

	public ModelSelectedService(AnalysedModel analysedModel,DataModel dataModel) {
		super();
		this.analysedModel = analysedModel;
		this.dataModel=dataModel;
	}
//	public String newRecommend(int userID,int howMany){
//		try {
//				Recommender recommender = new ItemBaseRecommender(dataModel);
//				
//				List<RecommendedItem> items = recommender
//						.recommend(userID, howMany);
//				return items.toString();
//		} catch (TasteException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	public String  newRecommend(int userID,int howMany){
		try {
			MyRecommender myRecommender=new MyRecommender(dataModel);
			String cf=analysedModel.getCollaborative_filtering();
			String similarity=analysedModel.getSimilarity();
			
			Gson gson=new Gson();
			if(cf.equalsIgnoreCase(AnalysedModel.Keys.USERCF)){
				String neighbourhood=analysedModel.getNeighbourhoods();
				
				//设置相似度
				if(similarity.equals(AnalysedModel.Keys.PEARSONCORRELATIONCOEFFICIENT)){
					myRecommender.setUserSimilarityPearsonCorrelation();
				}else if(similarity.equals(AnalysedModel.Keys.TANIMOTOCOEFFICIENT)){
					myRecommender.setUserSimilarityTanimotoCoefficient();
				}else if(similarity.equals(AnalysedModel.Keys.EUCLIDEANDISTANCE)){
					myRecommender.setUserSimilarityEuclideanDistance();
				}else if(similarity.equals(AnalysedModel.Keys.LOGLIKELIHOODSIMILARITY)){
					myRecommender.setUserSimilarityLogLikelihood();;
				}
				//设置近邻
				if(neighbourhood.equals(AnalysedModel.Keys.THRESHOLDBASEDNEIGHBORHOODS)){
					myRecommender.setUserNeighbourhoodThreshold(0);
				}else{
					myRecommender.setUserNeighbourhoodNearestN(howMany);
				}
				//进行推荐
				if(myRecommender.userBasedRecommender()){
					List<RecommendedItem> items = myRecommender
							.recommend(userID, howMany);
					return gson.toJson(items);
//					return items.toString();
				}
			}else if(cf.equalsIgnoreCase(AnalysedModel.Keys.ITEMCF)){
				if(similarity.equals(AnalysedModel.Keys.PEARSONCORRELATIONCOEFFICIENT)){
					myRecommender.setItemSimilarityPearsonCorrelation();
				}else if(similarity.equals(AnalysedModel.Keys.TANIMOTOCOEFFICIENT)){
					myRecommender.setItemSimilarityTanimotoCoefficient();
				}else if(similarity.equals(AnalysedModel.Keys.EUCLIDEANDISTANCE)){
					myRecommender.setItemSimilarityEuclideanDistance();
				}else if(similarity.equals(AnalysedModel.Keys.LOGLIKELIHOODSIMILARITY)){
					myRecommender.setItemSimilarityLogLikelihood();;
				}
				
				if(myRecommender.itemBasedRecommender()){
					List<RecommendedItem> items = myRecommender
							.recommend(userID, howMany);
					return gson.toJson(items);
//					return items.toString();
				}
			}
			
			
		} catch (TasteException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
