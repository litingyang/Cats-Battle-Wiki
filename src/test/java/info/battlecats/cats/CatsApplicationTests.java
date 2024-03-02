package info.battlecats.cats;
import de.flapdoodle.embed.mongo.config.Net;
import info.battlecats.cats.CatRepository;
import static de.flapdoodle.embed.mongo.config.Defaults.runtimeConfigFor;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.jayway.jsonpath.Configuration;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.Defaults;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.packageresolver.Command;
import de.flapdoodle.embed.process.config.RuntimeConfig;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.json.*;

import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.MongodProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Array;
import java.util.*;


@SpringBootTest(classes=CatsApplication.class)
@RunWith(SpringRunner.class)
//@DataMongoTest
//@AutoConfigureDataMongo
//@ExtendWith(SpringExtension.class)
class CatsApplicationTests {
	private static JSONArray ja;
	private CatService catService;
	@Autowired
	private CatRepository catRepository;
	private static MongodProcess mongoProcess;
	private static Mongo mongo;
	private MongoClient mongoClient;
	private static List<String> list  = new ArrayList<>();
	private BasicDBList basicDBList = new BasicDBList();
	private static DBObject basicDBObject;
	@Autowired

	private MongoTemplate template;
	@BeforeAll
	public static void setUpAll() throws JSONException {

		list.add("Against aku: Insane Damage (inflicts 5.0x damage)");
		basicDBObject = BasicDBObject.parse("{\"id\":'652632a20d2317fda52cde4a',\"Speed\":20,\"Form\":\"Evolved\",\"Health\":18700,\"RechargingTime\":3.87,\"DPS\":612,\"TimeBetweenAttacks\":2.53,\"CatID\":\"719-2\",\"Image\":\"https://onestoppress.com/images/719-2_square.png\",\"Range\":170,\"Cost\":375,\"Name\":\"Cone Cat\",\"AttackAnimationTime\":0.57,\"Rarity\":\"Rare\",\"Target\":\"Single\",\"EnemyTypes\":[\"Relic\",\"Aku\"],\"Number\":719,\"ReviewIds\":[],\"KB\":1,\"Level\":30,\"Damage\":1530}");		// when

	}
	@BeforeEach
	public void setUp() throws IOException {
		catService = new CatService();
		catService.setRepository(catRepository);


	}
	@Test
	void nothing() {

	}
	@Test
	void unique(){
		List<Cat> cats = catService.allCats();
		Set<String>catIdSet = new HashSet<String>();
		for(Cat cat:cats){
			String CatID = cat.getCatID();
			ObjectId oID = cat.getId();
			System.out.println(CatID+":"+oID);
			if(catIdSet.contains(CatID)){
				catRepository.deleteCatById(oID);
			}else{
				catIdSet.add(CatID);
			}

		}

	}
	@Test
	void objectTest() throws IOException {
		RuntimeConfig config = runtimeConfigFor(Command.MongoD).build();
//		MongodStarter starter = MongodStarter.getInstance(config);
		MongodStarter starter = MongodStarter.getDefaultInstance();
		MongodConfig conf = MongodConfig.builder()
				.version(Version.Main.V6_0)
				.net(new Net("127.0.0.1", 1234, false))
				.build();
		MongodExecutable mongoExecutable = starter.prepare(conf);
//		MongodProcess process = mongodExe.start();
//		MongodExecutable mongoExecutable = starter.prepare(MongodConfig.builder().version(Version.V6_0_1).build());
		mongoProcess = mongoExecutable.start();
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:1234");
		mongoClient = MongoClients.create(connectionString);

//		Mongo mongo = new Mongo("127.0.0.1", 1234);
//		mongo.getDB("collection");
//
		template = new MongoTemplate(mongoClient,"collection");
		template.dropCollection("collection");

//		template.save(basicDBObject, "collection");
		List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
		databases.forEach(db -> System.out.println(db.toJson()));

//		catRepository = catService.getRepository();
		basicDBList.add(basicDBObject);
		template.save(basicDBObject, "collection");
		assertThat(template.findAll(DBObject.class, "collection"), is(equalToObject(basicDBList)));
	}
	@Test
	void notNull(){
		assertNotNull(catService);
		assertNotNull(catRepository);
	}



	@Test
	public void singleCatTest() throws IOException {
		RuntimeConfig config = runtimeConfigFor(Command.MongoD).build();
//		MongodStarter starter = MongodStarter.getInstance(config);
		MongodStarter starter = MongodStarter.getDefaultInstance();
		MongodConfig conf = MongodConfig.builder()
				.version(Version.Main.V6_0)
				.net(new Net("127.0.0.1", 1234, false))
				.build();
		MongodExecutable mongoExecutable = starter.prepare(conf);
//		MongodProcess process = mongodExe.start();
//		MongodExecutable mongoExecutable = starter.prepare(MongodConfig.builder().version(Version.V6_0_1).build());
		mongoProcess = mongoExecutable.start();
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:1234");
		mongoClient = MongoClients.create(connectionString);

//		Mongo mongo = new Mongo("127.0.0.1", 1234);
//		mongo.getDB("collection");
//
		template = new MongoTemplate(mongoClient,"collection");
		template.dropCollection("collection");

//		template.save(basicDBObject, "collection");
		List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
		databases.forEach(db -> System.out.println(db.toJson()));
		catService = new CatService();

		catService.setRepository(catRepository);
//		catRepository = catService.getRepository();
		basicDBList.add(basicDBObject);
		Cat cat = new Cat(new ObjectId("652632a20d2317fda52cde4a"),"719-2", 719, "https://onestoppress.com/images/719-2_square.png"
				,"Cone Cat", "Evolved", "Rare", 30, 18700, 1530, 170, 1, 20, 612, "Single"
				, 2.53, 0.57, 3.87, 375
				, Arrays.stream((new String[]{"Relic", "Aku"})).toList()
				, Arrays.stream((new String[]{"Against relic, aku: Resistant (only takes 0.25x damage) ", "General Abilities: Colossus Slayer",
				"Not affected by: Freeze, Curse"})).toList(), Arrays.stream(new Review[]{}).toList());
		List<Cat> lc = new ArrayList<Cat>();
		lc.add(cat);
		assertEquals(catService.singleCat("719-2"), lc);

	}
//    public void search() {
//
//
//        $("[data-test='search-input']").sendKeys("Selenium");
//        $("button[data-test='full-search-button']").click();
//
//        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
//    }

//    @Test
//    public void toolsMenu() {
//        mainPage.toolsMenu.click();
//
//        $("div[data-test='main-submenu']").shouldBe(visible);
//    }
//
//    @Test
//    public void navigationToAllTools() {
//        mainPage.seeDeveloperToolsButton.click();
//        mainPage.findYourToolsButton.click();
//
//        $("#products-page").shouldBe(visible);
//
//        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
//    }
	@AfterClass
	public void tearDown() throws Exception {
		template.dropCollection("collection");
//		mongo.close();

		mongoProcess.stop();
		mongoClient.close();

	}
}
//public class CatTemp{
//
//}
