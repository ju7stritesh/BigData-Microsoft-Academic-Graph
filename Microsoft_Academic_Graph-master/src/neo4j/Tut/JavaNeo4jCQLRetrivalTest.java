package neo4j.Tut;

import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class JavaNeo4jCQLRetrivalTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
		GraphDatabaseService graphDb = graphDbFactory
				.newEmbeddedDatabase("F:/MicrosoftDB");

		ExecutionEngine execEngine = new ExecutionEngine(graphDb);
		ExecutionResult execResult = execEngine
				.execute("MATCH (p:Affiliation) DELETE p");
		// String rows = "";
		// while (execResult.hasNext()) {
		// Map<String, Object> row = execResult.next();
		// for (Entry<String, Object> column : row.entrySet()) {
		//
		// rows += column.getKey() + ": " + column.getValue() + ";";
		// }
		// rows += "\n";
		// }
		// // String results = execResult.dumpToString();
		// System.out.println(rows);

	}
}
