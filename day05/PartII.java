import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartII {
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
                if (ids.isEmpty()) {
                    ids.add(tmp);
                    continue;
                }
                List<Long[]> q = new ArrayList<>();
                q.add(tmp);

                while (!q.isEmpty()) {
                    boolean isSplit = false;
                    tmp = q.remove(0);
                    for (int i = 0; i < ids.size(); i++) {
                        if (tmp[0] >= ids.get(i)[0] && tmp[0] <= ids.get(i)[1]) {
                            tmp[0] = ids.get(i)[1] + 1;
                        }
                        if (tmp[1] >= ids.get(i)[0] && tmp[1] <= ids.get(i)[1]) {
                            tmp[1] = ids.get(i)[0] - 1;
                        }
                        if (tmp[0] < ids.get(i)[0] && tmp[1] > ids.get(i)[1]) {
                            q.add(new Long[] { tmp[0], ids.get(i)[0] - 1 });
                            q.add(new Long[] { ids.get(i)[0] + 1, tmp[1] });
                            isSplit = true;
                            break;
                        }
                        if (tmp[1] - tmp[0] < 0) {
                            break;
                        }
                    }
                    if (!isSplit && tmp[1] - tmp[0] >= 0) {
                        ids.add(tmp);
                    }
                }
            }
        }
        for (Long[] id : ids) {
            sum += id[1] - id[0] + 1;
        }

        return sum;
    }

}