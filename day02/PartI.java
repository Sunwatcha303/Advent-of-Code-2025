import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
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
        return str.substring(0, len / 2).equals(str.substring(len / 2));
    }
}