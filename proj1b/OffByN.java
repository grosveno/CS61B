public class OffByN implements CharacterComparator {
    private int fiexdDiff;

    public OffByN(int x) {
        fiexdDiff = x;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == fiexdDiff;
    }
}
