import java.util.HashSet;

public class Task3WordPuzzle {
    public String flipWord(String word){
        StringBuilder flippedWord = new StringBuilder();
        flippedWord.append(word).reverse();
        return flippedWord.toString();
    }

    public void findWords(String[] l, String[] d){
        HashSet<String> prefixes = new HashSet<>();

        int max = d[0].length();

        int row;
        for(row = 0; row < d.length; ++row) {
            prefixes.add(d[row]);
            if (d[row].length() > max) {
                max = d[row].length();
            }
        }

        for(row = 0; row < l.length; ++row) {
            System.out.println("horizontal");

            int x;
            String word;
            int r;
            for(x = 0; x < l[row].length(); ++x) {
                word = "";

                for(r = x; r < max; ++r) {
                    word = word + l[row].charAt(r);
                    if (prefixes.contains(word)) {
                        System.out.println(word);
                    } else if (prefixes.contains(flipWord(word))) {
                        System.out.println(flipWord(word));
                    }
                }
            }

            System.out.println("vertical");

            for(x = 0; x < l[row].length(); ++x) {
                word = "";

                for(r = row; r < l.length && r - row < max; ++r) {
                    word = word + l[r].charAt(x);
                    if (prefixes.contains(word)) {
                        System.out.println(word);
                    } else if (prefixes.contains(flipWord(word))) {
                        System.out.println(flipWord(word));
                    }
                }
            }

            System.out.println("diagonal");

            for(x = 1; x < l.length; ++x) {
                r = row;

                for(word = ""; r <= x; ++r) {
                    word = word + l[x - r].charAt(r);
                    if (prefixes.contains(word)) {
                        System.out.println(word);
                    } else if (prefixes.contains(flipWord(word))) {
                        System.out.println(flipWord(word));
                    }
                }
            }

            for(x = 1; x < l[l.length - 1].length() - 1; ++x) {
                r = l.length - 1;
                word = "";

                for(int c = x; c < l[l.length - 1].length(); ++c) {
                    word = word + l[r].charAt(c);
                    if (prefixes.contains(word)) {
                        System.out.println(word);
                    } else if (prefixes.contains(flipWord(word))) {
                        System.out.println(flipWord(word));
                    }

                    --r;
                }
            }
        }
    }
}
