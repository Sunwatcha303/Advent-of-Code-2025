import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
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
            char[] arr = l.toCharArray();
            int len = arr.length;
            int max = Integer.parseInt("" + arr[0] + "" + arr[1]);
            for (int i = 0; i < len - 1; i++) {
                if (i != 0 && arr[i] <= max / 10) {
                    continue;
                }
                for (int j = i + 1; j < len; j++) {
                    int temp = Integer.parseInt("" + arr[i] + "" + arr[j]);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }

            sum += max;
        }
        return sum;
    }

}