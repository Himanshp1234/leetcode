import java.util.*;

class Solution {

    Map<Integer, List<String>> memo = new HashMap<>();
    Set<String> dict = new HashSet<>();

    public List<String> wordBreak(String s, List<String> wordDict) {

        dict.addAll(wordDict);

        return solve(s, 0);
    }

    private List<String> solve(String s, int index) {

        
        if (index == s.length()) {
            return new ArrayList<>(Arrays.asList(""));
        }

        
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        List<String> result = new ArrayList<>();

        
        for (int end = index + 1; end <= s.length(); end++) {

            String word = s.substring(index, end);

            
            if (dict.contains(word)) {

                
                List<String> remaining = solve(s, end);

                
                for (String sentence : remaining) {

                    if (sentence.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + sentence);
                    }
                }
            }
        }

        memo.put(index, result);

        return result;
    }
}