package neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jCQLRetrievalTest {
	public static void main(String[] args) {
		GraphDatabaseService graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase(Neo4jJavaAPIDBOperation.DBPATH);

		try (Transaction tx = graphDb.beginTx()) {
			// Perform DB operations
			ResourceIterator<Node> affiliations = graphDb
					.findNodes(NodeTypes.AFFILIATION);
			System.out.println("Affiliations: ");
			int i = 0;
			// affiliations.remove();
			while (affiliations.hasNext()) {
				Node affiliation = affiliations.next();
				System.out.print("\t" + affiliation.getProperty("affId"));
				System.out.println(":\t" + affiliation.getProperty("affName"));
				i++;
			}

			tx.success();
			System.out.println("Found " + i + " nodes");
			System.out.println("Done successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
