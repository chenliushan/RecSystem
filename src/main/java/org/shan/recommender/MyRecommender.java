package org.shan.recommender;

import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.Rescorer;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MyRecommender implements Recommender {
	private DataModel dataModel;

	private Recommender recommender;
	private ItemSimilarity itemSimilarity;

	private UserSimilarity userSimilarity;
	private UserNeighborhood userNeighborhood;

	public MyRecommender(DataModel dataModel) throws TasteException {
		this.dataModel = dataModel;

	}
	public boolean itemBasedRecommender() throws TasteException {
		// 基于物品的推荐
		if(dataModel!=null&&itemSimilarity!=null){
			recommender = new CachingRecommender(new EmbededItemBasedRecommender(
					new GenericItemBasedRecommender(dataModel, itemSimilarity)));
			return true;
		}
		return false;
	}
	
	public boolean userBasedRecommender() throws TasteException {
		
		if(dataModel!=null&&userSimilarity!=null&&userNeighborhood!=null){
//			userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(
//					dataModel));

			recommender = new CachingRecommender(new GenericUserBasedRecommender(
					dataModel, userNeighborhood, userSimilarity));
			return true;
		}
		return false;
	}
	
	

	public void setItemSimilarityTanimotoCoefficient(){
		// 基于谷本系数的相似性计算
		this.itemSimilarity=new TanimotoCoefficientSimilarity(
				dataModel);
	}
	public void setItemSimilarityLogLikelihood(){
		// 基于对数似然相似度的相似性计算
		this.itemSimilarity=new LogLikelihoodSimilarity(
				dataModel);
	}
	public void setItemSimilarityEuclideanDistance() throws TasteException{
		// 基于欧几里德距离的相似性计算
		this.itemSimilarity=new EuclideanDistanceSimilarity(
				dataModel);
	}
	public void setItemSimilarityPearsonCorrelation() throws TasteException{
		// 基于皮尔逊相关系数的相似性计算
		this.itemSimilarity=new PearsonCorrelationSimilarity(
				dataModel);
	}
	
	
	public void setUserSimilarityTanimotoCoefficient() {
		// 基于基于谷本系数的相似性计算
		this.userSimilarity=new TanimotoCoefficientSimilarity(
				dataModel);
	}
	public void setUserSimilarityLogLikelihood(){
		// 基于对数似然相似度的相似性计算
		this.userSimilarity=new LogLikelihoodSimilarity(
				dataModel);
	}
	public void setUserSimilarityEuclideanDistance() throws TasteException{
		// 基于欧几里德距离的相似性计算
		this.userSimilarity=new EuclideanDistanceSimilarity(
				dataModel);
	}
	public void setUserSimilarityPearsonCorrelation() throws TasteException{
		// 基于皮尔逊相关系数的相似性计算
		this.userSimilarity=new PearsonCorrelationSimilarity(
				dataModel);
	}
	
	public void setUserNeighbourhoodThreshold(int i){
		// 基于相似度门槛的邻居
		this.userNeighborhood=new ThresholdUserNeighborhood(
				i, userSimilarity, dataModel);
	}
	public void setUserNeighbourhoodNearestN(int i){
		// 基于固定数量的邻居
		this.userNeighborhood=new NearestNUserNeighborhood(i,
				userSimilarity, dataModel);
	}
	
	
	
	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		return recommender.recommend(userID, howMany);
	}

	public List<RecommendedItem> recommend(long userID, int howMany,
			Rescorer<Long> rescorer) throws TasteException {
		return recommender.recommend(userID, howMany, rescorer);
	}

	// 估计喜欢程度
	public float estimatePreference(long userID, long itemID)
			throws TasteException {
		return recommender.estimatePreference(userID, itemID);
	}

	public void setPreference(long userID, long itemID, float value)
			throws TasteException {
		recommender.setPreference(userID, itemID, value);
	}

	public void removePreference(long userID, long itemID)
			throws TasteException {
		recommender.removePreference(userID, itemID);
	}

	public DataModel getDataModel() {
		return recommender.getDataModel();
	}

	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		recommender.refresh(alreadyRefreshed);
	}

	public String toString() {
		return "MovieRecommender[recommender:" + recommender + ']';
	}

	private static final class EmbededItemBasedRecommender implements
			Recommender {

		private final GenericItemBasedRecommender recommender;

		private EmbededItemBasedRecommender(
				GenericItemBasedRecommender recommender) {
			this.recommender = recommender;
		}

		public float estimatePreference(long userID, long itemID)
				throws TasteException {
			return recommender.estimatePreference(userID, itemID);
		}

		public DataModel getDataModel() {
			return recommender.getDataModel();
		}

		public List<RecommendedItem> recommend(long userID, int howMany)
				throws TasteException {
			return this.recommend(userID, howMany, null);
		}

		public List<RecommendedItem> recommend(long userID, int howMany,
				Rescorer<Long> rescorer) throws TasteException {
			FastIDSet itemIDs = recommender.getDataModel().getItemIDsFromUser(
					userID);
			return recommender.mostSimilarItems(itemIDs.toArray(), howMany,
					null);
		}

		public void removePreference(long userID, long itemID)
				throws TasteException {
			recommender.removePreference(userID, itemID);

		}

		public void setPreference(long userID, long itemID, float value)
				throws TasteException {
			recommender.setPreference(userID, itemID, value);

		}

		public void refresh(Collection<Refreshable> alreadyRefreshed) {
			recommender.refresh(alreadyRefreshed);
		}
	}
}
