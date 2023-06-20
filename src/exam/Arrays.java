package exam;

public class Arrays {
    public static void main(String[] args) {
        int[] a = {2,4,6,8,10,15,16,17,18,19};
        int[] b = {1,5,11,12,13,19};
        int[] c = combinarArrays(a, b);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }

    public static int[] combinarArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < c.length && j < b.length && i < a.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                j++;
            }
            k++;
        }
        while (j < b.length) {
            c[k] = b[j];
            j++;
            k++;
        }
        while (i < a.length) {
            c[k] = a[i];
            i++;
            k++;
        }
        return c;
    }
}
