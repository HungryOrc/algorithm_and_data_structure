import java.io.*;
import java.util.*;

public class Solution {
    
    public static void main(String args[] ) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        ArrayList<int[]> idsAndPoints = new ArrayList<>();
        
        while (s != null) {
            
            while (!s.equals("PRINT")) {
                
                String[] elements = s.split(",");
                String id = elements[0];
                String rush = elements[1];
                String passY = elements[2];
                String passT = elements[3];
                String inter = elements[4];

                int point = Integer.valueOf(rush) * 2 + Integer.valueOf(passY) +
                    Integer.valueOf(passT) * 6 - Integer.valueOf(inter);

                int[] idAndPoint = new int[2];
                idAndPoint[0] = Integer.valueOf(id);
                idAndPoint[1] = point;

                idsAndPoints.add(idAndPoint);

                Collections.sort(idsAndPoints, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] array1, int[] array2) {
                        if (array1[1] > array2[1]) {
                            return -1;
                        } else if (array1[1] < array2[1]) {
                            return 1;
                        } else { // ==
                            if (array1[0] > array2[0]) {
                                return -1;
                            } else { // <=
                                return 1;
                            }
                        }
                    }
                });

                if (idsAndPoints.size() > 10) {
                    idsAndPoints.remove(10);
                }

                s = br.readLine();
            }
            
            System.out.println("Leaders");
            for (int i = 1; i <= idsAndPoints.size(); i++) {
                System.out.print(i + ",");
                System.out.print(idsAndPoints.get(i-1)[0] + ",");
                System.out.print(idsAndPoints.get(i-1)[1]);
                System.out.println();
            }

            s = br.readLine();
        }
       
        br.close();
    }
}
