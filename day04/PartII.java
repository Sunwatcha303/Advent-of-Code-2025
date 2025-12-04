import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day04/input.txt"))) {
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

        int len = list.size();
        char[][] map = new char[len][list.get(0).length()];
        for (int i = 0; i < list.size(); i++) {
            map[i] = list.get(i).toCharArray();
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '@')
                    continue;
                int cnt = 0;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (map[i - 1][j - 1] == '@') {
                        cnt++;
                    }
                }
                if (i - 1 >= 0) {
                    if (map[i - 1][j] == '@') {
                        cnt++;
                    }
                }
                if (i - 1 >= 0 && j + 1 < map[0].length) {
                    if (map[i - 1][j + 1] == '@') {
                        cnt++;
                    }
                }
                if (j + 1 < map[0].length) {
                    if (map[i][j + 1] == '@') {
                        cnt++;
                    }
                }
                if (i + 1 < len && j + 1 < map[0].length) {
                    if (map[i + 1][j + 1] == '@') {
                        cnt++;
                    }
                }
                if (i + 1 < len) {
                    if (map[i + 1][j] == '@') {
                        cnt++;
                    }
                }
                if (i + 1 < len && j - 1 >= 0) {
                    if (map[i + 1][j - 1] == '@') {
                        cnt++;
                    }
                }
                if (j - 1 >= 0) {
                    if (map[i][j - 1] == '@') {
                        cnt++;
                    }
                }
                if (map[i][j] == '@' && cnt < 4) {
                    sum++;
                    String str = list.get(i);
                    list.set(i, str.substring(0, j) + "." + str.substring(j + 1, map[0].length));
                }
            }
        }

        if (sum == 0) {
            return 0;
        }

        return sum + solve(list);
    }

}