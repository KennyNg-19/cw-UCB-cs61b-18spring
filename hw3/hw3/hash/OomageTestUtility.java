package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();

        int[] bucketNums = new int[M]; // 记录backet的量，默认值为0!
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketNums[bucketNum] += 1;
            /* 【错解：要全部求完，才能比较】效率优化：先检测该iteration后，是否有超标的——避免以后还要再iterate：*/
        }

        for (int i = 0; i < bucketNums.length; i++) {
//            System.out.println(N/2.5 + " " + bucketNums[i] + " " + N/50);
            if (bucketNums[i] > N / 2.5 || bucketNums[i] < N / 50) return false;
        }

        return true;
    }
}
