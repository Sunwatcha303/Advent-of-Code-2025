import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class PartII {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        try (Scanner in = new Scanner(Paths.get("./day08/input.txt"))) {
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
        List<JunctionBox> box = new ArrayList<>();
        int idx = 0;
        for (String l : list) {
            String[] str = l.split(",");
            box.add(new JunctionBox(idx++, Integer.parseInt(str[0]), Integer.parseInt(str[1]),
                    Integer.parseInt(str[2])));
        }
        int len = box.size();

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));

        List<Set<JunctionBox>> circuits = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            JunctionBox j1 = box.get(i);
            for (int j = i + 1; j < len; j++) {
                if (i == j)
                    continue;
                JunctionBox j2 = box.get(j);
                long dist = (long) (Math.pow(j1.x - j2.x, 2) + Math.pow(j1.y - j2.y, 2) + Math.pow(j1.z - j2.z, 2));
                priorityQueue.add(new Pair(j1, j2, dist));

            }

        }
        while (!priorityQueue.isEmpty()) {
            Pair p = priorityQueue.poll();
            boolean flag = false;
            for (Set<JunctionBox> set : circuits) {
                if (set.contains(p.j1) && set.contains(p.j2)) {
                    flag = true;
                    break;
                }
                if ((set.contains(p.j1) || set.contains(p.j2))) {
                    if (set.contains(p.j1)) {
                        List<Set<JunctionBox>> tmpToDel = new ArrayList<>();
                        for (Set<JunctionBox> tmp : circuits) {
                            if (tmp != set && tmp.contains(p.j2)) {
                                set.addAll(tmp);
                                tmpToDel.add(tmp);
                            }
                        }
                        circuits.removeAll(tmpToDel);
                    } else if (set.contains(p.j2)) {
                        List<Set<JunctionBox>> tmpToDel = new ArrayList<>();
                        for (Set<JunctionBox> tmp : circuits) {
                            if (tmp != set && tmp.contains(p.j1)) {
                                set.addAll(tmp);
                                tmpToDel.add(tmp);
                            }
                        }
                        circuits.removeAll(tmpToDel);
                    }
                    set.add(p.j1);
                    set.add(p.j2);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Set<JunctionBox> tmp = new HashSet<>();
                tmp.add(p.j1);
                tmp.add(p.j2);
                circuits.add(tmp);
            }

            if (circuits.get(0).size() == len) {
                sum = (long) p.j1.x * (long) p.j2.x;
                break;
            }

        }
        return sum;
    }

}

class Pair {
    JunctionBox j1;
    JunctionBox j2;
    long dist;

    public Pair(JunctionBox j1, JunctionBox j2, long dist) {
        this.j1 = j1;
        this.j2 = j2;
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Pair [j1=" + j1 + ", j2=" + j2 + ", dist=" + dist + "]";
    }

}

class JunctionBox {
    int idx;
    int x;
    int y;
    int z;

    public JunctionBox(int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JunctionBox other = (JunctionBox) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (z != other.z)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JunctionBox [idx=" + idx + ", x=" + x + ", y=" + y + ", z=" + z + "]";
    }

}
