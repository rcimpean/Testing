package TestSoftvision.TestApi.pages;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

	@DataProvider(name = "New Users")
	public static Object[][] dataProviderForNewUserField() {
		return new Object[][] { 
									{ "Ion Creanga", "writer" , 201}, 
									{ "Mihai Eminescu", "poet" ,201},
									{ "Simona Halep", "tennis player",201 },
									{ "Klaus Iohannis", "president" ,201},
									{ "Dancila", "minister", 201},
								/*	{ "","",400},
									{ "2","",400},
									{ "","2",400},
									{ "../..","",400},
									{ "select * from users","",400},
									{ "","System.out.println(\"parola\")",400},
									{ "-1","",400},
									{ "123654987654532149/784562139864732132154678451321321354648765131","",400},
									{ "","1213232656545213232654512232564513132654121232265612213232266456",400},
							  */};
	}
	
	@DataProvider(name = "Autentichation")
	public static Object[][] dataProviderForAutentichationCredentials()
	{
		return new Object[][]{
			{"ToolsQA", "TestPassword"}, {"",""}
		};
	}
}
