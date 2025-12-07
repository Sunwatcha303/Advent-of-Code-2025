import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PartI {
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
        Set<Tile> tachyonPos = new HashSet<>();
        for (int i = 0; i < size; i++) {
            char[] c = list.get(i).toCharArray();
            for (int j = 0; j < len; j++) {
                if (c[j] == 'S') {
                    tachyonPos.add(new Tile(i + 1, j));
                } else if (c[j] == '^') {
                    if (tachyonPos.contains(new Tile(i - 1, j))) {
                        sum++;
                        tachyonPos.add(new Tile(i, j - 1));
                        tachyonPos.add(new Tile(i, j + 1));
                    }
                } else {
                    if (tachyonPos.contains(new Tile(i - 1, j))) {
                        tachyonPos.add(new Tile(i, j));
                    }
                }
            }
        }

        return sum;
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