package mongo;

public enum PaperScoreWeights {
	AUTHORPOPULARITY(0.2), KEYWORDMATCH(0.5), YEAR(0.2), PAPERRANK(0.1);

	private double value;

	PaperScoreWeights(double value) {
		this.value = value;
	}

	public double getWeight() {
		return value;
	}

	public static void main(String[] args) {
		System.out.println(PaperScoreWeights.KEYWORDMATCH.getWeight());
	}
}
