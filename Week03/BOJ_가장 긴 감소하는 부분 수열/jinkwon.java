/*
백준 11722번 - 가장 긴 감소하는 부분 수열 문제는 LDS (Longest Decreasing Subsequence) 알고리즘 문제입니다.
보통 DP로 해결하며 시간 복잡도는 O(N²)입니다.

✅ 문제 링크
https://www.acmicpc.net/problem/11722

✅ 핵심 아이디어
dp[i] = i번째 수를 마지막 원소로 하는 감소하는 부분 수열의 최대 길이

앞에 있는 수 j(0 ≤ j < i)를 확인하면서,
A[j] > A[i]인 경우 dp[i] = max(dp[i], dp[j] + 1)
*/

import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static int N, max;
  static int[] arr, dp;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    dp = new int[N];

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      dp[i] = 1;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] < arr[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    for (int i : dp) {
      max = Math.max(max, i);
    }

    System.out.println(max);
  }
}

