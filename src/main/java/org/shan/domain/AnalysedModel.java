package org.shan.domain;

public class AnalysedModel {
	
	
	private String similarity;
	private String neighbourhoods;
	private String collaborative_filtering;
	
	
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public String getNeighbourhoods() {
		return neighbourhoods;
	}
	public void setNeighbourhoods(String neighbourhoods) {
		this.neighbourhoods = neighbourhoods;
	}
	
	public String getCollaborative_filtering() {
		return collaborative_filtering;
	}
	public void setCollaborative_filtering(String collaborative_filtering) {
		this.collaborative_filtering = collaborative_filtering;
	}
	


	public static class Keys{
		public final static String EUCLIDEANDISTANCE = "Euclidean Distance";
		public final static String PEARSONCORRELATIONCOEFFICIENT = "Pearson Correlation Coefficient";
		public final static String LOGLIKELIHOODSIMILARITY = "Log-likelihood Similarity";
		public final static String TANIMOTOCOEFFICIENT = "Tanimoto Coefficient";
		public final static String KNEIGHBORHOODS = "K-neighbourhoods";
		public final static String FIXSIZENEIGHBORHOODS = "Fix-size neighbourhoods";
		public final static String THRESHOLDBASEDNEIGHBORHOODS = "Threshold-based neighbourhoods";
		public final static String USERCF = "User CF";
		public final static String ITEMCF = "Item CF";
		
		
	}
}
