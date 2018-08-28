import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	/*
    * "apple", {"a": ["@", "4"], "p": ["%"], "z": ["^"]}
    * ["apple", "@pple", "4pple", "a%ple", "ap%le", "@%ple", "4%ple", "@p%le"..."@%%le", "4%%le"]
    */
	public static void main(String args[] ) throws Exception {
        Map<String, List<String>> replacements = new HashMap<String, List<String>>();
        replacements.put("a", new ArrayList<String>());
        replacements.get("a").add("@");
        replacements.get("a").add("4");
        
        replacements.put("p", new ArrayList<String>());
        replacements.get("p").add("%");
        
        replacements.put("z", new ArrayList<String>());
        replacements.get("z").add("^");
        
        System.out.println("apple: \n" + generatePasswords("apple", replacements));
    }
	
	public static List<String> generatePasswords(String pass, Map<String, List<String>> replacements) {
		Set<String> set = new TreeSet<String>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
		Set<String> firstPass = firstPass(pass, replacements); 
		set.add(pass);
		set.addAll(firstPass);
		set.addAll(secondPass(set, replacements));
		
		return new ArrayList<String>(set);
	}
	
	public static Set<String> secondPass(Set<String> pass, Map<String, List<String>> replacements) {
		Set<String> set = new HashSet<String>();
		
		for(String text: pass) {
			set.addAll(firstPass(text, replacements));
		}
		
		return set;
	}
	
	public static Set<String> firstPass(String pass, Map<String, List<String>> replacements) {
		Set<String> set = new HashSet<String>();
		for (int idx = 0; idx < pass.length(); idx++) {
			String target = pass.substring(idx);
			
			for (String key: replacements.keySet()) {
				if (target.contains(key)) {
					for(String combination: combinations(target, key, replacements.get(key))) {
						String text = pass.substring(0, idx) + combination;
						set.add(text);
						
					}
				}
			}
		}
		
		return set;
	}
	
	public static Set<String> combinations(String text, String target, List<String> replacements) {
		Set<String> set = new HashSet<String>();
		
		for (String value: replacements) {
			set.add(text.replaceFirst(target, value));
		}
		
		return set;
	}
}
