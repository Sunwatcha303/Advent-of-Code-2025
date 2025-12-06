import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
        int size = list.size();
        int len = list.get(0).length();

        boolean isCurMul = false;
        boolean prevIsSpaceCol = true;

        long total = 0;
        for (int i = 0; i < len; i++) {
            int cntSpace = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size - 1; j++) {
                char c = list.get(j).charAt(i);
                if (c == ' ') {
                    cntSpace++;
                    continue;
                }
                sb.append(c);
            }

            if (prevIsSpaceCol) {
                prevIsSpaceCol = false;
                isCurMul = list.get(size - 1).charAt(i) == '*';
                total = isCurMul ? 1 : 0;
            }

            if (cntSpace == size - 1) {
                prevIsSpaceCol = true;
                sum += total;
                continue;
            }

            long num = Long.parseLong(sb.toString());
            if (isCurMul) {
                total *= num;
            } else {
                total += num;
            }

        }

        sum += total;

        return sum;
    }

}