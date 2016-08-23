package mongo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Prediction {

	public static File file_pred_5 = new File("F:/Pred_5.csv");
	public static File file_pred_10 = new File("F:/Pred_10.csv");
	public static File file_pred_15 = new File("F:/Pred_15.csv");
	public static File file_pred_15_withscore = new File(
			"F:/Pred_15_WithScore.csv");

	public static void main(String[] args) {
		ArrayList<String> inputPaperIds = new ArrayList<String>();
		// inputPaperIds.add("0404A0F1");
		inputPaperIds.add("58FDA5BF");
		inputPaperIds.add("592DE9D2");
		inputPaperIds.add("5D33ABBE");
		inputPaperIds.add("5D9D02CD");
		inputPaperIds.add("5DD6439E");
		inputPaperIds.add("5FA5178A");
		inputPaperIds.add("6D8436DF");
		inputPaperIds.add("09F3625D");
		inputPaperIds.add("5F8BC29B");
		inputPaperIds.add("5FE5C811");
		inputPaperIds.add("5B78E9DE");
		inputPaperIds.add("6DF65C47");
		inputPaperIds.add("2DE433FA");
		inputPaperIds.add("5B78E9DE");
		inputPaperIds.add("704F5E20");
		inputPaperIds.add("6B9E13C5");
		inputPaperIds.add("6062053E");
		inputPaperIds.add("645493B3");
		inputPaperIds.add("12E5DA8F");
		Recommendation.initDBConn();
		for (String currPaperId : inputPaperIds) {
			Paper currPaper = Recommendation.getPaperDetails(currPaperId);
			ArrayList<PaperScore> recoPapers = Recommendation
					.getPaperRecommendations(currPaper);
			Recommendation.calcNormVars(currPaper, recoPapers);
			Recommendation.applyRanking(currPaper, recoPapers);

			// sort as per the score in descending order
			Collections.sort(recoPapers, new Comparator<PaperScore>() {
				@Override
				public int compare(PaperScore arg0, PaperScore arg1) {
					return Double.compare(arg1.getFinalScore(),
							arg0.getFinalScore());
				}
			});

			// Top 15 recommendations
			ArrayList<String> top_15 = new ArrayList<String>();
			ArrayList<String> top_15_WithScore = new ArrayList<String>();
			System.out.println("Top 15 recommendations...");
			for (int i = 0; i < 15; i++) {
				top_15.add(recoPapers.get(i).getPaperId());
				top_15_WithScore.add(recoPapers.get(i).getPaperId() + "="
						+ recoPapers.get(i).getFinalScore());
				System.out.println(recoPapers.get(i).getPaperId() + "="
						+ recoPapers.get(i).getFinalScore());
				try {
					// Write the predictions to files
					if (i == 4) {
						writeToFile(file_pred_5, top_15, currPaperId);
					}
					if (i == 9) {
						writeToFile(file_pred_10, top_15, currPaperId);
					}
					if (i == 14) {
						writeToFile(file_pred_15, top_15, currPaperId);
						writeToFile(file_pred_15_withscore, top_15_WithScore,
								currPaperId);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		Recommendation.closeDBConn();

		System.exit(0);
	}

	public static void writeToFile(File file, ArrayList<String> predictions,
			String paperId) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		String line = "";
		for (int i = 0; i < predictions.size() - 1; i++) {
			line += predictions.get(i) + ",";
		}
		line += predictions.get(predictions.size() - 1);
		fw.write(paperId + ":" + line + "\n");
		fw.close();
	}

}
