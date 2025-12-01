import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day01/input.txt"))) {
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
        long pointer = 50;
        long cnt = 0;
        for (String l : list) {
            char dir = l.charAt(0);
            long val = Long.parseLong(l.substring(1));
            if (dir == 'R') {
                pointer = (pointer + val) % 100;

            } else {
                if (pointer - (val % 100) < 0) {
                    pointer = 100 - ((val % 100) - pointer);
                } else {
                    pointer -= (val % 100);
                }
            }
            cnt += pointer == 0 ? 1 : 0;
        }
        return cnt;
    }
}