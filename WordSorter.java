/************************************************************
 * Brian Banfield
 * 5/17/2022
 * Text analyzer that reads a file and outputs statistics. 
 ***********************************************************/


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WordSorter {

	private Map<String, Long> sharedCounter;
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	public static String Main(String args) throws IOException {
		String text = "" ;
		org.jsoup.nodes.Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
		
		Elements newsHeadlines = doc.select("p");
		for (Element headline : newsHeadlines) {

			text += headline.getElementsByTag("p").text() + " ";

		}

		newsHeadlines = doc.select("h1");
		for (Element headline : newsHeadlines) {

			text += headline.getElementsByTag("h1").text() + " ";

		}

		Map<String, Long> sharedCounter = new ConcurrentHashMap<>();

		text = text.toUpperCase();
		String[] arr = text.split(" ");
		for(String word : arr) {

			word = word.replaceAll("[^A-Z]", "");

			Long v = sharedCounter.get(word);
			if (v == null)
				v = sharedCounter.put(word, 1l);
			else {
				sharedCounter.put(word, v + 1);
			}
		}

		Map<String, Long> reverseSortedMap = new ConcurrentHashMap<>();

		reverseSortedMap = sortByValue(sharedCounter);
		String result = "";
		for(Entry e : reverseSortedMap.entrySet()){
			result += (e.getKey() + " = " + e.getValue()) + "\n";
			System.out.println(e.getKey() + " = " + e.getValue());
		}
		return result;
	}
} 


