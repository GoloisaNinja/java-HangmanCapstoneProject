import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
 class GameArrays {
    private final char[] letters;
    private final ArrayList<Character> misses;
    public GameArrays(char[] letters, ArrayList<Character> misses) {
        this.letters = letters;
        this.misses = misses;
    }
    public char[] getLetters() {
        return this.letters;
    }
    public ArrayList<Character> getMisses() {
        return this.misses;
    }
}

public class Hangman {
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};
    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};
    public static String[] getRandomWord() {
        int random = new Random().nextInt(words.length);
        return words[random].split("");
    }
    public static void displayGame(String guess, char[] letters, ArrayList<Character> misses) {
        System.out.println();
        if (!guess.equals("")) {
            System.out.println("Guess: " + guess);
        }
        System.out.println(gallows[misses.size()]);
        if (misses.size() > 0) {
            System.out.print("Misses:\t");
            for (int i = 0; i < misses.size(); i++) {
                System.out.print(misses.get(i));
            }
            System.out.println();
        }
        System.out.print("Word:\t");
        for (int i = 0; i < letters.length; i++) {
            System.out.print(letters[i] + " ");
        }
        System.out.println();
    }
    public static GameArrays makeGuess(String guess, ArrayList<Character> misses, char[] letters,
                                   String[] secretLetterArr) {
        char guessAsChar = guess.charAt(0);
        boolean correctGuess = false;
        for (int i = 0; i < secretLetterArr.length; i++) {
            if (secretLetterArr[i].equals(guess)) {
                correctGuess = true;
                letters[i] = guessAsChar;
            }
        }
        if (!correctGuess) {
            misses.add(guessAsChar);
        }
        return new GameArrays(letters, misses);
    }
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Java Hangman! Press any key to get started!");
        sc.nextLine();
        System.out.println("Fetching word...");
        String[] secretLetterArr = getRandomWord();
        char[] letters = new char[secretLetterArr.length];
        Arrays.fill(letters, '_');
        ArrayList<Character> misses = new ArrayList<>();
        String guess = "";
        displayGame(guess, letters, misses);
        while(misses.size() < 6) {
            System.out.print("Guess:\t");
            guess = sc.next();
            GameArrays results = makeGuess(guess, misses, letters, secretLetterArr);
            letters = results.getLetters();
            misses = results.getMisses();
            displayGame(guess, letters, misses);
            if (!Arrays.toString(letters).contains("_")) {
                break;
            }
        }
        String endMessage = "";
        if (misses.size() == 6) {
            String secretWord = String.join("", secretLetterArr);
            endMessage = "\n*** You Lost! :( ***" +
                         "\n*** the word was: " + secretWord +
                        " ***";
        } else {
            endMessage = "\n*** GREAT WORK! You solved it! ***";
        }
        System.out.println(endMessage);
        sc.close();
    }
}