import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartI {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day05/input.txt"))) {
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
        List<Long[]> ids = new ArrayList<>();
        List<Long> igds = new ArrayList<>();

        boolean isIdg = false;
        for (String l : list) {
            if (l.isEmpty()) {
                isIdg = true;
                continue;
            }
            if (isIdg) {
                igds.add(Long.parseLong(l));
            } else {
                String[] str = l.split("-");
                Long[] tmp = { Long.parseLong(str[0]), Long.parseLong(str[1]) };
                ids.add(tmp);
            }
        }

        for (long igd : igds) {
            for (Long[] id : ids) {
                if (igd >= id[0] && igd <= id[1]) {
                    sum++;
                    break;
                }
            }
        }

        return sum;
    }

}