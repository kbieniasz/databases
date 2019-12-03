package pl.edu.agh.bd.mongo;

import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoLab {

	private MongoClient mongoClient;
	private DB db;
	private MongoDatabase database;
	public MongoLab() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDB("DataSet");
		database = mongoClient.getDatabase("DataSet");
	}

	private void showCollections() {
		for (String name : db.getCollectionNames()) {
			System.out.println("colname: " + name);
		}
	}

	/*
	a. Zwróć bez powtórzeń wszystkie nazwy miast w których znajdują się firmy
			(business). Wynik posortuj na podstawie nazwy miasta alfabetycznie.
	db.getCollection('Business').distinct("city")
	*/

	private List<String> exerciseA(){
		return db.getCollection("Business").distinct("city");
	}

	/*
	b. Zwróć liczbę wszystkich recenzji, które pojawiły się w roku 2011 i 2012.
	db.getCollection('Review').count( {$or:
    [{'date': new RegExp("2011")}, {'date': new RegExp("2012")} ]
	} )
	Odp. 382376
	 */

	private long exerciseB(){
		Pattern regex2011 = Pattern.compile("2011");
		DBObject clause1 = new BasicDBObject("date", regex2011);
		Pattern regex2012 = Pattern.compile("2012");
		DBObject clause2 = new BasicDBObject("date", regex2012);
		BasicDBList or = new BasicDBList();
		or.add(clause1);
		or.add(clause2);
		DBObject query = new BasicDBObject("$or", or);
		return db.getCollection("Review").count(query);
	}

	/*
	c. Zwróć dane wszystkich otwartych (open) firm (business) z pól: id, nazwa, adres.

	db.getCollection('Business').find({"open":true}, {"_id":1, "name":1, "full_address":1})
	 */
	private void exerciseC()
	{
		DBCursor cursor = db.getCollection("Business")
				.find(	new BasicDBObject("open", true),
						new BasicDBObject().append("businnes_id",1)
								.append("name",1)
								.append("full_address",1));
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	/*
	d. Zwróć dane wszystkich użytkowników (user), którzy uzyskali przynajmniej jeden
	pozytywny głos z jednej z kategorii (funny, useful, cool), wynik posortuj
	alfabetycznie na podstawie imienia użytkownika.
	db.getCollection('User').find({ $or: [
    {'votes.funny': {$gte: 1}},
    {'votes.useful': {$gte: 1}},
    {'votes.cool': {$gte: 1}}
    ]}
    ).sort({'name': 1})
	 */
	private void exerciseD()
	{
		DBObject clause1 = new BasicDBObject("votes.funny",new BasicDBObject("$gte", 1));
		DBObject clause2 = new BasicDBObject("votes.useful",new BasicDBObject("$gte", 1));
		DBObject clause3 = new BasicDBObject("votes.cool",new BasicDBObject("$gte", 1));
		BasicDBList or = new BasicDBList();
		or.add(clause1);
		or.add(clause2);
		or.add(clause3);
		DBObject query = new BasicDBObject("$or", or);
		DBCollection collection = db.getCollection("User");
		collection.createIndex(new BasicDBObject("name",1));
		DBCursor cursor =  db.getCollection("User").
				find(query).sort(new BasicDBObject("name",1));
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/*
	e) Określ, ile każde przedsiębiorstwo
	 otrzymało wskazówek/napiwków (tip) w 2013.
	 Wynik posortuj alfabetycznie na podstawie nazwy firmy.
	 */


	private AggregateIterable<Document> exerciseE()
	{


		database.getCollection("Tip").aggregate(Arrays.asList(
				new Document("$match",new Document("date",Pattern.compile("2013"))),
				new Document("$group", new Document("_id","$business_id")
						.append("tips_amount", new Document("$sum",1))),
				new Document("$out", "tips_amount")
		));

		AggregateIterable<Document> documents2 = database.getCollection("Business").aggregate(Arrays.asList(
				new Document("$lookup", new Document("from", "tips_amount")
						.append("localField","business_id").append("foreignField","_id").append("as","tips")),
				new Document("$unwind", new Document("path","$tips").append("preserveNullAndEmptyArrays",true)),
				new Document("$project",new Document("_id","$_id")
						.append("business_id","$business_id")
						.append("name","$name")
						.append("tips", "$tips.tips_amount")),
				new Document("$sort", new Document("name",1))
		));
		database.getCollection("tips_amount").drop();
		return documents2;
	}


	private AggregateIterable<Document> exerciseF() {


		AggregateIterable<Document> documents1 = database.getCollection("Review").aggregate(Arrays.asList(
				new Document("$group", new Document("_id", "$business_id")
						.append("average_stars", new Document("$avg", "$stars"))),
				new Document("$out", "reviews_avareges")
		));

		AggregateIterable<Document> documents2 = database.getCollection("Business").aggregate(Arrays.asList(
				new Document("$lookup", new Document("from", "reviews_avareges")
						.append("localField","business_id").append("foreignField","_id").append("as","avarages")),
				new Document("$unwind", new Document("path","$avarages").append("preserveNullAndEmptyArrays",true)),
				new Document("$project",new Document("_id","$_id")
						.append("business_id","$business_id")
						.append("name","$name")
						.append("average_stars", "$avarages.average_stars")),
				new Document("$sort", new Document("average_stars",-1))
		));
		database.getCollection("reviews_avareges").drop();
		return documents2;



	}


/*

f) Wyznacz, jaką średnia ocen (stars) uzyskała każda firma (business) na podstawie wszystkich recenzji,
 wynik posortuj on najwyższego uzyskanego wyniku.

*/

	private void exerciseG()
	{
		database.getCollection("Business").deleteMany(new Document("stars", new Document("$eq",1.5)));
	}




	public static void main(String[] args) throws UnknownHostException {
		MongoLab mongoLab = new MongoLab();
		mongoLab.showCollections();

		System.out.println(mongoLab.exerciseA());
		System.out.println(mongoLab.exerciseB());
		mongoLab.exerciseC();
		mongoLab.exerciseD();
		mongoLab.exerciseE();
		mongoLab.exerciseF();
		mongoLab.exerciseG();

	}
}