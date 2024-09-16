public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    private boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        if (wordDeque.removeFirst() != wordDeque.removeLast()) {
            return false;
        }
        return isPalindrome(wordDeque);
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.isEmpty()) {
            return true;
        }
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
