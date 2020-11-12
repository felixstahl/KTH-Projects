package q2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Comparator;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a program finding the optimal cutoff value (for both quicksort and mergesort) to insertion sort.
 *
 * 2^32 = 4294967296
 */
class Metric implements Comparable<Metric> { // measurement data
    public int n;   //cutoff value
    public long ms; // milliseconds

    public Metric(int x, long m) {
        n  = x;
        ms = m;
    }

    @Override
    public int compareTo(Metric o) {
        return 0;
    }
}

// compare for metric class
class MetricCompare implements Comparator<Metric>{
    public int compare(Metric s1, Metric s2) {
        if (s1.ms < s2.ms)  // if(sample1 sort time is smaller than sample2)
            return 1;

        else if (s1.ms > s2.ms) // if(sample1 sort time is bigger than sample2)
            return -1;

        return 0;
    }
}

public class FindingCutoff {

    private static final int DEFAULT_SEED = Instant.now().getNano();

    public static void main(String[] args) throws IOException {
        if(args.length == 2){
            System.out.println("This seed is: " + DEFAULT_SEED);
            int N = Integer.valueOf(args[0]);   // N number of integers between [0, K], K <= 2^32 == 4294967296
            long K = Long.parseLong(args[1]);   // [0,K], N number between 400000

            // this part is not used
            File result = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\komplettering\\src\\q2\\result.txt");
            result.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(result));

            Long[] intArray1 = makeRandomLongArray(N, K, DEFAULT_SEED);       // create a random array

            /** -------------------------------------------------------------------
                           QUICK SORT
                           optimal cutoff between 5 & 15 according to sedgewick.
             "Cutoff to insertion sort. As with mergesort, it pays to switch to insertion sort for tiny arrays.
             The optimum value of the cutoff is system-dependent, but any value between 5 and 15 is likely to work well
             in most situations."
             This is what i often get.
             ---------------------------------------------------------------------*/
            APriorityQueue<Metric> pq = new APriorityQueue<>(100, new MetricCompare());    // create a priority queue
            Stack<Metric> meta = new Stack<Metric>();   // only used for printing

            int l = 0;  // low
            int h = 100; // high
            long baseline = GetQuickSortSamples(h, intArray1.clone());  // first round on high cutoff (100)

            pq.insert(new Metric(h, baseline));
            meta.push(new Metric(h, baseline));
            while (h > l) {                     // binary search for optimal cutoff for quicksort
                int mid = (h + l) / 2;
                long sample = GetQuickSortSamples(mid, intArray1);
                pq.insert(new Metric(mid, sample));
                meta.push(new Metric(mid, sample));
                if (sample < baseline) {
                    baseline = sample;
                    h = mid;
                } else {
                    l = mid + 1;
                }
            }

            for (Metric m : meta) {
                System.out.println("milliseconds: " + m.ms + " cutoff: " + m.n);
            }
            System.out.println("----");
            writer.write(System.lineSeparator());
            while (!pq.isEmpty()) {
                Metric o = pq.delMin();
                System.out.println("milliseconds: " + o.ms + " cutoff: " + o.n);
                StringBuilder sb = new StringBuilder();
                sb.append(o.ms).append(", ").append(o.n);
                writer.write(sb.toString());
                writer.write(System.lineSeparator());
            }


            /** ----------------------------------------------------------------------
                            MERGE SORT
                            optimal cutoff between 5 & 15 according to sedgewick
             "Cutoff to insertion sort. As with mergesort, it pays to switch to insertion sort for tiny arrays.
             The optimum value of the cutoff is system-dependent, but any value between 5 and 15 is likely to work well
             in most situations."
             This is what i often get
             ------------------------------------------------------------------------*/
            APriorityQueue<Metric> pq2 = new APriorityQueue<>(100, new MetricCompare());    // create a priority queue
            Stack<Metric> meta2 = new Stack<Metric>();   // only used for printing

            int l2 = 0;  // low 2
            int h2 = 100; // high 2
            long baseline2 = GetMergeSortSamples(h2, intArray1.clone());  // first round on high cutoff (100)

            pq2.insert(new Metric(h2, baseline2));
            meta2.push(new Metric(h2, baseline2));
            while (h2 > l2) {                     // binary search for optimal cutoff for mergesort
                int mid = (h2 + l2) / 2;
                long sample = GetMergeSortSamples(mid, intArray1);
                pq2.insert(new Metric(mid, sample));
                meta2.push(new Metric(mid, sample));
                if (sample < baseline2) {
                    baseline2 = sample;
                    h2 = mid;
                } else {
                    l2 = mid + 1;
                }
            }

            System.out.println("mergesort");
            for (Metric m : meta2) {
                System.out.println("milliseconds: " + m.ms + " cutoff: " + m.n);
            }
            System.out.println("----");
            while (!pq2.isEmpty()) {
                Metric o = pq2.delMin();
                System.out.println("milliseconds: " + o.ms + " cutoff: " + o.n);
                StringBuilder sb = new StringBuilder();
                sb.append(o.ms).append(", ").append(o.n);
                writer.write(sb.toString());
                writer.write(System.lineSeparator());
            }
            writer.close();
        }
    }

    // runs quicksort as many times as sample is set to, adding it to array each time.
    // calculates the avarage time as a 'long' and returns it
    private static long GetQuickSortSamples(int c, Long a[]) {
        long arr[] = new long[20];  // change the integer to change the sample size
        long avg = 0;
        for (int i = 0; i < arr.length; i++) {
            QuickSort q = new QuickSort(c);
            Long b[] = a.clone();
            Instant timeBefore = Instant.now();
            q.sort(b);
            Instant timeAfter = Instant.now();
            long dur = Duration.between(timeBefore, timeAfter).toMillis();
            avg = ((avg * i) + dur) / (i + 1);  // calculates a new avarage each iteration
        }
        return avg; // avarage time
    }
    // runs mergesort as many times as sample is set to, adding it to array each time.
    // calculates the avarage time as a 'long' and returns it
    private static long GetMergeSortSamples(int c, Long a[]) {
        long arr[] = new long[20];  // change the integer to change the sample size
        long avg = 0;
        for (int i = 0; i < arr.length; i++) {
            MergeSort m = new MergeSort(c);
            Long b[] = a.clone();
            Instant timeBefore = Instant.now();
            m.sort(b);
            Instant timeAfter = Instant.now();
            long dur = Duration.between(timeBefore, timeAfter).toMillis();
            avg = ((avg * i) + dur) / (i + 1);  // calculates a new avarage each iteration
        }
        return avg; // avarage time
    }

    // create a random long array from user input
    private static Long[] makeRandomLongArray(int count, long max, int seed) {
        Long[] array = new Long[count];
        Random random = new Random(seed);
        for (int i = 0; i < count; i++) {
            array[i] = (long)(random.nextDouble()*max);
        }
        return array;
    }
}