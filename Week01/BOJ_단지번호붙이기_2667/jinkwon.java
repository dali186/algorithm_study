/*

* 주의할 점 :

1. BFS 할 때 visited[nx][ny] = true; 해주는 시기가 중요하다.
큐에 넣으면서 visited[nx][ny] = true; 해줘야한다.
만약 꺼내서 visited[nx][ny] = true; 해주려 하면 큐에 같은 좌표가 중복으로 들어갈 수 있다.

2. PriorityQueue 는 for-each로 꺼내면 순서가 보장되지 않는다.
PriorityQueue는 내부적으로 Heap(힙) 자료구조를 사용한다.
이 힙은 전체 요소들을 오름차순으로 정렬해두는 게 아니라,
가장 작은 값(루트 노드)만 빠르게 꺼낼 수 있게 구성돼 있다.

*/

import java.util.*;
import java.io.*;

class Main {
  static int N;
  static int[][] field;
  static boolean[][] visited;
  static PriorityQueue<Integer> pq = new PriorityQueue<>();
  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    field = new int[N][N];
    visited = new boolean[N][N];

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < N; j++) {
        field[i][j] = line.charAt(j) - '0';
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (field[i][j] == 1 && !visited[i][j]) {
          bfs(new int[] {i, j});
        }
      }
    }

    sb.append(pq.size()).append("\n");
    while (!pq.isEmpty()) {
      sb.append(pq.poll()).append("\n");
    }
    System.out.print(sb.toString());
  }

  private static void bfs(int[] start) {
    LinkedList<int[]> queue = new LinkedList<>();
    queue.add(start);
    visited[start[0]][start[1]] = true;
    int count = 0;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];

      count++;

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (checkValid(nx, ny) && field[nx][ny] == 1 && !visited[nx][ny]) {
          queue.add(new int[] {nx, ny});
          visited[nx][ny] = true;
        }
      }
    }

    pq.add(count);
  }

  // 범위를 벗어나지 않는지 확인하는 메서드
  private static boolean checkValid(int x, int y) {
    if (x < 0 || x >= N || y < 0 || y >= N) {
      return false;
    }
    return true;
  }
}