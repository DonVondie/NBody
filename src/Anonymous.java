import java.util.HashMap;

public class Anonymous {

	public int howMany(String[] headlines, String[] messages){
		
		HashMap<Character, Integer> headlineMap = new HashMap<Character,Integer>();
		HashMap<Character, Integer> messageMap = new HashMap<Character,Integer>();
		int numberMessages = messages.length;
		
		for (String headline0 : headlines) {
			headlineMap = mapToChars(headline0, headlineMap);
			
		} // for each headline
		
		for (String message0: messages){
			messageMap = mapToChars(message0, messageMap);
			
			for (Character key: messageMap.keySet()){
				if ( headlineMap.get(key) == null || headlineMap.get(key) < messageMap.get(key) ) {
					numberMessages -= 1;
					break;
				}
			}
			messageMap.clear();
		}
		
		return numberMessages;
	}// End howMany
	
	public HashMap<Character, Integer> mapToChars(String headline0, 
			HashMap<Character, Integer> headlineMap){
		
		headline0 = headline0.replace(" ", "");
		headline0 = headline0.toUpperCase();
		
		char[] chars = headline0.toCharArray();
		
		for (char ch0 : chars){
			if (headlineMap.containsKey(ch0)){
				headlineMap.put(ch0, headlineMap.get(ch0) +1);
			}
			
			else {
				headlineMap.put(ch0, 1);
			}
		
	}
		return headlineMap;
		
	}// End mapToChars
	
}// End Class
