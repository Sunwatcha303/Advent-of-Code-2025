import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day06/input.txt"))) {
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
        // System.out.println(list);
        List<String[]> probs = new ArrayList<>();
        for (String l : list) {
            String[] str = l.trim().split("\\s+");

            probs.add(str);
        }

        int len = probs.size();

        for (int i = 0; i < probs.get(0).length; i++) {
            long total = probs.get(len - 1)[i].equals("*") ? 1 : 0;
            for (int j = 0; j < len - 1; j++) {
                // System.out.println(probs.get(j)[i]);
                if (probs.get(len - 1)[i].equals("*")) {
                    total *= Long.parseLong(probs.get(j)[i]);
                } else {
                    total += Long.parseLong(probs.get(j)[i]);
                }
            }
            // System.out.println(total);
            sum += total;
        }

        return sum;
    }

}