import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day02/input.txt"))) {
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
            String[] ranges = l.split(",");
            for (String r : ranges) {
                String[] ids = r.split("-");
                long start = Long.parseLong(ids[0]);
                long end = Long.parseLong(ids[1]);
                for (long i = start; i <= end; i++) {
                    if (isInvalidId(i)) {
                        sum += i;
                    }
                }
            }
        }
        return sum;
    }

    private static boolean isInvalidId(long i) {
        String str = "" + i;
        int len = str.length();
        for (int j = len / 2; j > 0; j--) {
            if (len % j != 0)
                continue;

            String ref = str.substring(0, j);

            int cnt = 1;
            for (int k = j; k < len; k += j) {
                if (ref.equals(str.substring(k, k + j))) {
                    cnt++;
                }
            }

            if (cnt == len / j) {
                return true;
            }
        }
        return false;
    }
}