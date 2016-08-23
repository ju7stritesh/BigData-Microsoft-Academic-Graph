package neo4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jJavaAPIDBOperation {
	public static String DBPATH = "F:/MicrosoftDB";
	public static String FILEPATH = "F:/MicrosoftDataset";

	public static void main(String[] args) throws FileNotFoundException {
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		// GraphDatabaseService db =
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase(DBPATH);
		File logFile = new File(FILEPATH + "/Logs/");
		File folder = new File(FILEPATH + "/Authors");
		File[] listofFiles = folder.listFiles();

		try (Transaction tx = db.beginTx()) {
			// Perform DB operations
			for (int i = 0; i < 1; i++) {
				FileInputStream fis = new FileInputStream(listofFiles[i]);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						fis));
				String line = null;
				try {
					while ((line = br.readLine()) != null) {
						String[] lineCols = line.split("\t");
						// Node authorNode = db.createNode(NodeTypes.AUTHOR);
						Node affiliationNode = db.createNode(NodeTypes.AUTHOR);

						affiliationNode.setProperty("authId", lineCols[0]);
						affiliationNode.setProperty("authName", lineCols[1]);

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if ((i + 1) % 5 == 0) {
					System.out.println("Processed FileNumber " + i + " : File "
							+ listofFiles[i].getName());
				}
				br.close();
				fis.close();
			}
			tx.success();
			System.out.println("Done successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
