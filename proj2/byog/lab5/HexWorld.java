package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import javafx.geometry.Pos;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
        public int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** draw a row */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int posX = p.x; posX < p.x + width; posX++) {
            world[posX][p.y] = t;
        }
    }

    /** determine offset */
    public static int hexRowOffSet(int h, int s) {
        if (h >= s) {    // 为什么不返回开始坐标，因为还要传入坐标。
            return 2 * s - 1 - h;
        }
        return h;
    }

    /** determine width */
    public static int hexRowWidth(int h, int s) {
        return 2 * hexRowOffSet(h, s) + s;
    }

    /** draw a hexagon, p represents the lower left point, s represents length */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < 2 * s; i++) {
            int posX = p.x - hexRowOffSet(i, s);
            int posY = p.y + i;
            int width = hexRowWidth(i, s);
            Position newP = new Position(posX, posY);
            addRow(world, newP, width, t);
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            case 3: return Tileset.LOCKED_DOOR;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.GRASS;
        }
    }

    /** determine the top neighbour hex */
    public static Position topNeighbour(Position p, int s) {
        return new Position(p.x, p.y + 2 * s);
    }

    /** determine the top right neighbour hex */
    public static Position topRightNeighbour(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y - s);
    }

    /** determine the bottom right neighbour hex */
    public static Position bottomRightNeighbour(Position p, int s) {
        return new Position(p.x + 2 * s - 1, p.y + s);
    }

    /** draw a column of hexes */
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int n, int s) {
        Position start = new Position(p.x, p.y);
        for (int i = 0; i < n; i++) {
            start = topNeighbour(start, s);
            addHexagon(world, start, s, randomTile());
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }

        Position p = new Position(4, 20);
        drawRandomVerticalHexes(world, p, 3, 3);
        p = topRightNeighbour(p, 3);
        drawRandomVerticalHexes(world, p, 4, 3);
        p = topRightNeighbour(p, 3);
        drawRandomVerticalHexes(world, p, 5, 3);
        p = bottomRightNeighbour(p, 3);
        drawRandomVerticalHexes(world, p, 4, 3);
        p = bottomRightNeighbour(p, 3);
        drawRandomVerticalHexes(world, p, 3, 3);

        ter.renderFrame(world);
    }
}
