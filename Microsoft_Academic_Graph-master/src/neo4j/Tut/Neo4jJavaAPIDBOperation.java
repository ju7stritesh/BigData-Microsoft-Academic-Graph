package neo4j.Tut;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jJavaAPIDBOperation {
	public static void main(String[] args) {
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("F:/SampleDB");
		try (Transaction tx = db.beginTx()) {

			Node javaNode = db.createNode(Tutorials.JAVA);
			javaNode.setProperty("TutorialID", "JAVA001");
			javaNode.setProperty("Title", "Learn Java");
			javaNode.setProperty("NoOfChapters", "25");
			javaNode.setProperty("Status", "Completed");

			Node scalaNode = db.createNode(Tutorials.SCALA);
			scalaNode.setProperty("TutorialID", "SCALA001");
			scalaNode.setProperty("Title", "Learn Scala");
			scalaNode.setProperty("NoOfChapters", "20");
			scalaNode.setProperty("Status", "Completed");

			Relationship relationship = javaNode.createRelationshipTo(
					scalaNode, TutorialRelationships.JVM_LANGIAGES);
			relationship.setProperty("Id", "1234");
			relationship.setProperty("OOPS", "YES");
			relationship.setProperty("FP", "YES");

			tx.success();
		}
		System.out.println("Done successfully");
	}
}
