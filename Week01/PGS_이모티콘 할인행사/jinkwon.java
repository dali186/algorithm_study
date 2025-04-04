/*
  * DFS를 사용한 완전탐색을 해도 4^7 = 16,384 * 100(유저수) * 7(이모티콘)로 충분히 가능하다.

  1. 할인율 조합 만들기 (DFS or 재귀)
  각 이모티콘에 대해 [10, 20, 30, 40] 중 하나 선택
  모든 조합 만들기 → 예: [30, 10], [20, 40], [40, 40] 등

  2. 각 조합마다 시뮬레이션 실행
  모든 유저에 대해:
  유저가 지정한 비율 이상 할인된 이모티콘만 구매
  총합이 기준 가격 이상이면 → 구매 취소 + 서비스 가입
  아니면 → 그대로 구매

  3. 가입자 수 & 매출 계산
  모든 유저에 대해 위 기준으로 계산 후
  (가입자 수, 판매금액) 반환

  4. 가장 가입자 수가 많은 조합을 고름
  동일한 가입자 수일 경우 → 판매금액이 더 큰 조합 선택
*/

class Solution {
  int maxJoin = 0;
  int maxSales = 0;
  int[] discounts = {10, 20, 30, 40};

  public int[] solution(int[][] users, int[] emoticons) {
    dfs(0, new int[emoticons.length], users, emoticons);
    return new int[]{maxJoin, maxSales};
  }

  void dfs(int idx, int[] discountSet, int[][] users, int[] emoticons) {
    if (idx == emoticons.length) {
      simulate(discountSet, users, emoticons);
      return;
    }

    for (int d : discounts) {
      discountSet[idx] = d;
      dfs(idx + 1, discountSet, users, emoticons);
    }
  }

  void simulate(int[] discountSet, int[][] users, int[] emoticons) {
    int join = 0;
    int sales = 0;

    for (int[] user : users) {
      int rate = user[0];
      int limit = user[1];
      int total = 0;

      for (int i = 0; i < emoticons.length; i++) {
        if (discountSet[i] >= rate) {
          int price = emoticons[i] * (100 - discountSet[i]) / 100;
          total += price;
        }
      }

      if (total >= limit) {
        join++;
      } else {
        sales += total;
      }
    }

    if (join > maxJoin || (join == maxJoin && sales > maxSales)) {
      maxJoin = join;
      maxSales = sales;
    }
  }
}
