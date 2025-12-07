import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day07/input.txt"))) {
            while (in.hasNext()) {
                String input = in.nextLine();
                list.add(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long output = solve(list);
        System.out.println(output);
    }

    private static long solve(List<String> list) {
        long sum = 0;
        int size = list.size();
        int len = list.get(0).length();
        char[][] map = new char[size][len];
        Tile start = null;
        for (int i = 0; i < size; i++) {
            char[] c = list.get(i).toCharArray();
            map[i] = c;
            for (int j = 0; j < len && start == null; j++) {
                if (c[j] == 'S') {
                    start = new Tile(i, j);
                }
            }
        }
        sum = recursive(map, new Tile(start.row + 1, start.col));
        return sum;
    }

    static Map<Tile, Long> memo = new HashMap<>();

    private static long recursive(char[][] map, Tile start) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        if (start.row == map.length - 1) {
            return 1;
        }
        if (map[start.row + 1][start.col] == '.') {
            long cnt = recursive(map, new Tile(start.row + 1, start.col));
            memo.put(start, cnt);
            return cnt;
        }

        long cnt = recursive(map, new Tile(start.row + 1, start.col - 1))
                + recursive(map, new Tile(start.row + 1, start.col + 1));
        memo.put(start, cnt);
        return cnt;
    }

}

class Tile {
    int row;
    int col;

    Tile(int i, int j) {
        row = i;
        col = j;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tile [row=" + row + ", col=" + col + "]";
    }

}