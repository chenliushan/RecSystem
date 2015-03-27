package org.shan.recommender;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.AveragingPreferenceInferrer;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.Rescorer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.shan.model.CourseModel;

public class UserBasedRecommender implements Recommender {

	private final Recommender recommender;

	/**
	 * @throws IOException
	 *             if an error occurs while creating the
	 *             {@link GroupLensDataModel}
	 * @throws TasteException
	 *             if an error occurs while initializing this
	 *             {@link GroupLensRecommender}
	 */
	public UserBasedRecommender() throws IOException, TasteException {
		
		// this(new CourseModel());
		DataModel model = new CourseModel();
//		EuclideanDistanceSimilarity eds=new EuclideanDistanceSimilarity(model);
		//基于谷本系数的相似性计算
		TanimotoCoefficientSimilarity tcs = new TanimotoCoefficientSimilarity(
				model);
		//近邻计算
		UserNeighborhood tcsneighborhood = new NearestNUserNeighborhood(3,
				tcs, model);
		//推荐
		recommender = new CachingRecommender(new GenericUserBasedRecommender(
				model, tcsneighborhood, tcs));
		
//		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
//		userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(
//				model));
//		System.out.println("userSimilarity:" + userSimilarity.toString());
//		
//		
//		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3,
//				userSimilarity, model);
//
//		recommender = new CachingRecommender(new GenericUserBasedRecommender(
//				model, neighborhood, userSimilarity));
	}

	/**
	 * <p>
	 * Alternate constructor that takes a {@link DataModel} argument, which
	 * allows this {@link Recommender} to be used with the
	 * {@link org.apache.mahout.cf.taste.eval.RecommenderEvaluator} framework.
	 * </p>
	 *
	 * @param dataModel
	 *            data model
	 * @throws TasteException
	 *             if an error occurs while initializing this
	 *             {@link GroupLensRecommender}
	 */
	public UserBasedRecommender(DataModel model) throws TasteException {
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
		userSimilarity.setPreferenceInferrer(new AveragingPreferenceInferrer(
				model));
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3,
				userSimilarity, model);

		recommender = new CachingRecommender(new GenericUserBasedRecommender(
				model, neighborhood, userSimilarity));
	}

	public List<RecommendedItem> recommend(long userID, int howMany)
			throws TasteException {
		return recommender.recommend(userID, howMany);
	}

	public List<RecommendedItem> recommend(long userID, int howMany,
			Rescorer<Long> rescorer) throws TasteException {
		return recommender.recommend(userID, howMany, rescorer);
	}

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
		return "UserBasedRecommender[recommender:" + recommender + ']';
	}

}
