import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day03/input.txt"))) {
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
        for (String l : list) {
            int len = l.length();

            StringBuilder sb = new StringBuilder();
            int i = 0;
            int remain = 12;
            while (i < len && remain > 0) {
                int size = len - remain - i + 1;
                int max = Integer.parseInt("" + l.charAt(i));
                int idx = i;
                for (int j = i; j < i + size; j++) {
                    int temp = Integer.parseInt("" + l.charAt(j));
                    if (temp > max) {
                        max = temp;
                        idx = j;
                    }
                }
                i = idx == i ? i + 1 : idx + 1;
                remain--;

                sb.append(max);
            }
            sum += sb.isEmpty() ? 0 : Long.parseLong(sb.toString());
        }
        return sum;
    }

}