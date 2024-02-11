import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger beautifulWordsLength3 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsLength4 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsLength5 = new AtomicInteger(0);

    public static void main(String[] args) {
        String[] texts = generateTexts(100_000);

        Thread threadPalindrome = new Thread(() -> checkPalindrome(texts));
        Thread threadSingleLetter = new Thread(() -> checkSingleLetter(texts));
        Thread threadIncreasingOrder = new Thread(() -> checkIncreasingOrder(texts));

        threadPalindrome.start();
        threadSingleLetter.start();
        threadIncreasingOrder.start();

        try {
            threadPalindrome.join();
            threadSingleLetter.join();
            threadIncreasingOrder.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + beautifulWordsLength3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + beautifulWordsLength4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + beautifulWordsLength5 + " шт");
    }

    public static String[] generateTexts(int count) {
        Random random = new Random();
        String[] texts = new String[count];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void checkPalindrome(String[] texts) {
        for (String text : texts) {
            if (isPalindrome(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                }
            }
        }
    }

    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void checkSingleLetter(String[] texts) {
        for (String text : texts) {
            if (isSingleLetter(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                }
            }
        }
    }

    public static boolean isSingleLetter(String text) {
        char firstChar = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static void checkIncreasingOrder(String[] texts) {
        for (String text : texts) {
            if (isIncreasingOrder(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                }
            }
        }
    }

    public static boolean isIncreasingOrder(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}