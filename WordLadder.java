/***
 * Author: Min(Arun) Pyae Sone
 * Class: CSCI340
 *
 * Description:  Program that finds the shortest word ladder between two words. Your instructor has
 * created multiple word files. Each contains words of a given length. words.3 contains three letter words.
 * words.4 contains four letter words, etc. If the words are of different lengths, simply print an error and
 * terminate. Otherwise read the appropriate file. If either of the input words is not in the file, print an error
 * and terminate. If no word ladder can be found, print an appropriate message and terminate. If a word ladder
 * can be found, print the word ladder in a format similar to that above.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class WordLadder {

    /**
     * created WordNode class that creates nodes and holt the
     * String word, the depth and the WordNoded type prev. also
     * has a constructor that takes word String para, int para
     * and WordNode para and assigns it to the Nodes
     */
    class WordNode {
        public String word;
        public int depth;
        public WordNode prev;
        public WordNode(String word, int depth, WordNode prev){
            this.word=word;
            this.depth=depth;
            this.prev=prev;
        }
    }

    /**
     * findLadders method that takes three parameter of String beginWord, String endWord, and
     * List<String> wordList. It will look for the beginWord and end with the endWord and the
     * method will go BFS through the wordList. Firsst of all created List<List<String>> result of
     * ArrayList that will hold and return the final result that will be returned by the method. And
     * HashSet<String> unvisited that will contempy the unvisited nodes so  it will not run forever.
     * then added all the wordList to the unvisited HashSet. The BFS will search throgugh the graph and
     * when the conditions are met it will put the right elemetnts to the result ArrayList. Finally the
     * ArrayList will be returned by the method.
     * @param beginWord String that the user will input, which will start the search from
     * @param endWord String that the user wull input, which will end the search when got to it
     * @param wordList List that the professor provided
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashSet<String> unvisited = new HashSet<>();
        unvisited.addAll(wordList);
        LinkedList<WordNode> queue = new LinkedList<>();
        WordNode node = new WordNode(beginWord,0,null);
        queue.offer(node);
        int minLen = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            WordNode top = queue.poll();
            //top if have shorter result already
            if(result.size()>0 && top.depth>minLen){
                return result;
            }
            for(int i=0; i<top.word.length(); i++){
                char c = top.word.charAt(i);
                char[] arr = top.word.toCharArray();
                for(char j='z'; j>='a'; j--){
                    if(j==c){
                        continue;
                    }
                    arr[i]=j;
                    String t = new String(arr);
                    if(t.equals(endWord)){
                        //add to result
                        List<String> aResult = new ArrayList<>();
                        aResult.add(endWord);
                        WordNode p = top;
                        while(p!=null){
                            aResult.add(p.word);
                            p = p.prev;
                        }
                        Collections.reverse(aResult);
                        result.add(aResult);
                        //stop if get shorter result
                        if(top.depth<=minLen){
                            minLen=top.depth;
                        }else{
                            return result;
                        }
                    }
                    if(unvisited.contains(t)){
                        WordNode n=new WordNode(t,top.depth+1,top);
                        queue.offer(n);
                        unvisited.remove(t);
                    }
                }
            }
        }
        return result;
    }

    /**
     * readFile method will read accordingly to the length of the words of the user input. used
     * if-else statements to determine the size of the word and read the file accordingly. and
     * after the correct file is read, all the words in the wordList will be transferred to
     * a new List
     * @param wordlist List that will hold the words
     * @param start user input word that will be used to determine the size of the array
     * @throws FileNotFoundException
     */
    private static void readFile(List<String> wordlist, String start) throws FileNotFoundException {
        Scanner input;
        File file;
        if(start.length()==3) {
            file = new File("words.3.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==4){
            file = new File("words.4.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==5){
            file = new File("words.5.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==6){
            file = new File("words.6.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==7){
            file = new File("words.7.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==8){
            file = new File("words.8.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }else if(start.length()==9){
            file = new File("words.9.txt");
            input = new Scanner(file);
            while(input.hasNext()){
                wordlist.add(input.next());
            }
        }
    }

    /**
     * main method copied from the one professor provided us. Added if statements and while loop
     * to see if the word ladder can be found, if not returns an error message, if yes then the while
     * loop will run and print accordingly.
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {

        String start, end; // the words on which the ladder is based
        Scanner in = new Scanner(System.in);

        // the words in the file, WordNode is class to represent nodes in our graph
        HashMap<String, WordNode> wordlist = new HashMap<String, WordNode>();

        List<String> li = new LinkedList<>();
        List<List<String>> fi = new LinkedList<>();
        // Read in the two words
        System.out.println("Enter the beginning word");
        start = in.next();
        System.out.println("Enter the ending word");

        end = in.next();
        // Check length of the words
        int length = start.length();
        if (length != end.length()) {
            System.err.println("ERROR! Words not of the same length.");
            System.exit(1);
        }
        // Read in the appropriate file of words based on the length of start
        readFile(li, start);
        // Search the graph

        WordLadder t = new WordLadder();
        // System.out.println("this is: " + t.findLadders(start, end, li).get(0));

        fi = t.findLadders(start, end, li);

        if(fi.isEmpty()){
            System.out.println("No word ladder found");
        }else {
            int i = 0;
            while (i < fi.get(0).size()) {
                System.out.print(fi.get(0).get(i));
                if (i < fi.get(0).size() - 1)
                    System.out.print("->");
                i++;
            }
        }
    }
}