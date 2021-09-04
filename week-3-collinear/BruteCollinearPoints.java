/* *****************************************************************************
 *  Name:              Vinicius Corbellini
 *  Last modified:     8/7/2022
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> collinear = new ArrayList<>();
    private int n;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (Arrays.asList(points).contains(null)) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            for (int k = i + 1; k < points.length; k++) {
                if (points[i].equals(points[k]))
                    throw new IllegalArgumentException("duplicate points");
            }
        }
        Point[] jCopy = points.clone();
        Arrays.sort(jCopy);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    boolean b = jCopy[i].slopeTo(jCopy[j]) == jCopy[i].slopeTo(jCopy[k]);
                    if (b) {
                        for (int g = k + 1; g < points.length; g++) {
                            boolean c = jCopy[i].slopeTo(jCopy[j]) == jCopy[i]
                                    .slopeTo(jCopy[g]);
                            if (c) {
                                collinear.add(new LineSegment(jCopy[i], jCopy[g]));
                                n++;
                            }
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        return collinear.toArray(new LineSegment[collinear.size()]);
    }
}
