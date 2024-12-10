import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static StringBuilder sb = new StringBuilder();
  static int n;
  static int[][] board;
  static int[] dx = {1, 0};
  static int[] dy = {0, 1};

  public static void main(String[] args) throws IOException {
    setUp();

    sb.append(bfs() ? "HaruHaru" : "Hing");

    output();
  }

  private static boolean bfs() {
    boolean[][] visited = new boolean[n][n];
    Deque<int[]> que = new ArrayDeque<>();

    visited[0][0] = true;
    que.add(new int[]{0, 0});

    while (!que.isEmpty()) {
      int[] curr = que.pollFirst();
      int currY = curr[0];
      int currX = curr[1];

      if (board[currY][currX] == -1) {
        return true;
      }

      for (int i = 0; i < 2; i++) {
        int ny = currY + board[currY][currX] * dy[i];
        int nx = currX + board[currY][currX] * dx[i];

        if (!checkRange(ny, nx) || visited[ny][nx]) {
          continue;
        }

        visited[ny][nx] = true;
        que.add(new int[]{ny, nx});
      }
    }

    return false;
  }

  private static boolean checkRange(int y, int x) {
    return 0 <= y && y < n && 0 <= x && x < n;
  }

  private static void setUp() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    n = Integer.parseInt(br.readLine());
    board = new int[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  private static void output() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }

}