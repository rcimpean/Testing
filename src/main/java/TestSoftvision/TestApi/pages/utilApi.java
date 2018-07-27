package TestSoftvision.TestApi.pages;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class utilApi {
	public static String getUserDataFromJson(String jsonString, String fieldName) {
		String value = "";

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonObject = mapper.readTree(jsonString);
			value = jsonObject.findValue(fieldName).textValue();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		return value;

	}

	public static Object getUserDataFromJsonGeneric(String jsonString, String fieldName) {
		Object value = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonObject = mapper.readTree(jsonString);
			value = jsonObject.findValue(fieldName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return value;

	}

	@SuppressWarnings("unchecked")
	public static <T> T getUserDataFromJsonGenericT(String jsonString, String fieldName) {
		T value = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonObject = mapper.readTree(jsonString);
			value = (T) jsonObject.findValue(fieldName).toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return value;

	}

	public static String createUserJson(String name, String job) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("name", name);
		rootNode.put("job", job);

		return rootNode.toString();
	}
}
