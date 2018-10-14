import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    int n = 4, j = 0, k = 2;
    double arr[][] = new double[6][5], min = Double.MAX_VALUE;
    for (int i = 0; i < 5; i++) {
      for (int l = 0; l < 4; l++) {
        arr[i][l] = -1;
      }
    }
    for (int i = 1; i <= n; i++) {
      arr[i][i - 1] = 0;
    }
    arr[1][1] = 3 / 8.;
    arr[2][2] = 3 / 8.;
    arr[3][3] = 1 / 8.;
    arr[4][4] = 1 / 8.;
    // for(int diagonal=1;diagonal<=n-1;diagonal++) {
    // for(int i=1;i<=n-diagonal;i++) {
    // j=i+diagonal;min=Double.MAX_VALUE;
    // for(int m=1;m<=k;m++) {
    // System.out.println(i+" "+j+" "+(arr[i][m-1]+arr[m+1][j]));
    // if((arr[i][m-1]+arr[m+1][j])<min&&(arr[i][m-1])!=-1&&(arr[m+1][j])!=-1) {
    // min=arr[i][m-1]+arr[m+1][j];
    // double tot=0.0;
    // for(int o=i;o<=j;o++)
    // tot+=arr[o][o];
    // arr[i][j]=(arr[i][m-1]+arr[m+1][j])+tot;
    // System.out.println((arr[i][m-1]+arr[m+1][j])+tot);
    // }
    // }
    // System.out.println();
    // }k++;
    // }

    for (int diagonal = 1; diagonal <= n - 1; diagonal++) {
      for (int i = 1; i <= n - diagonal; i++) {
        j = i + diagonal;
        min = Double.MAX_VALUE;
        for (int m = i; m <= j; m++) {
          System.out.println(i + " " + j + "  " + (arr[i][m - 1] + arr[m + 1][j]));
          if ((arr[i][m - 1] + arr[m + 1][j]) < min && (arr[i][m - 1]) != -1 && (arr[m + 1][j]) != -1) {
            min = arr[i][m - 1] + arr[m + 1][j];
            double tot = 0.0;
            for (int o = i; o <= j; o++)
              tot += arr[o][o];
            arr[i][j] = (arr[i][m - 1] + arr[m + 1][j]) + tot;
            System.out.println((arr[i][m - 1] + arr[m + 1][j]) + tot);
          }
        }
        System.out.println();
      }
    }

    for (int i = 0; i < 6; i++) {
      for (int a = 0; a < 5; a++) {
        System.out.print(arr[i][a] + "       ");
      }
      System.out.println();
    }

  }

  public static void optsearchtree(int n, float p[], float minAvg) {

  }

}
