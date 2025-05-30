/*
❗ 문제 핵심
처음엔 좌측 톱니 회전과 우측 톱니 회전을 각각 따로 처리했는데,
이렇게 하면 좌측 회전 중 기어를 돌려버리면, 그 상태가 우측 회전 처리에 영향을 준다는 문제가 생긴다.

즉, 기어 회전은 전파 여부 판단 → 실제 회전을 분리해서 진행해야 한다.
*/

import java.util.*;
import java.io.*;

class Main {
  static int N, result;
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    LinkedList<Character>[] gears = new LinkedList[4];
    for (int i = 0; i < 4; i++) {
      gears[i] = new LinkedList<>();
    }

    for (int i = 0; i < 4; i++) {
      String line = br.readLine();
      for (char c : line.toCharArray()) {
        gears[i].add(c);
      }
    }

    int rotationCount = Integer.parseInt(br.readLine());
    for (int i = 0; i < rotationCount; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int gearIndex = Integer.parseInt(st.nextToken()) - 1;
      int direction = Integer.parseInt(st.nextToken());

      int[] directions = new int[4];
      directions[gearIndex] = direction;

      // 왼쪽으로 전파
      for (int j = gearIndex; j > 0; j--) {
        char currentGear = gears[j].get(6);
        char leftGear = gears[j - 1].get(2);

        if (currentGear != leftGear) {
          directions[j - 1] = -directions[j];
        } else {
          break;
        }
      }

      // 오른쪽으로 전파
      for (int j = gearIndex; j < 3; j++) {
        char currentGear = gears[j].get(2);
        char rightGear = gears[j + 1].get(6);

        if (currentGear != rightGear) {
          directions[j + 1] = -directions[j];
        } else {
          break;
        }
      }

      // 회전
      for (int j = 0; j < 4; j++) {
        rotate(gears, j, directions[j]);
      }
    }

    // 점수 계산
    for (int i = 0; i < 4; i++) {
      result += gears[i].get(0) == '1' ? (int) Math.pow(2, i) : 0;
    }

    System.out.println(result);
  }

  private static void rotate(LinkedList<Character>[] gears, int gearIdx, int direction) {
    if (direction == 1) {
      char last = gears[gearIdx].removeLast();
      gears[gearIdx].addFirst(last);
    } else if (direction == -1) {
      char first = gears[gearIdx].removeFirst();
      gears[gearIdx].addLast(first);
    } else {
      // 방향이 0인 경우 회전하지 않음
      return;
    }
  }
}
