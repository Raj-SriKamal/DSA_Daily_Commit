from typing import List

class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        MOD = 10**9 + 7

        memo_r = {}
        memo_d = {}

        def get_next_right(r, c):
            if (r, c) in memo_r:
                return memo_r[(r, c)]

            nc = c + 1

            if nc >= n:
                res = (-1, -1)
            elif grid[r][nc] == 0:
                res = (r, nc)
            else:
                res = get_next_down_from_mirror(r + 1, nc)

            memo_r[(r, c)] = res
            return res

        def get_next_down(r, c):
            if (r, c) in memo_d:
                return memo_d[(r, c)]

            nr = r + 1

            if nr >= m:
                res = (-1, -1)
            elif grid[nr][c] == 0:
                res = (nr, c)
            else:
                res = get_next_right_from_mirror(nr, c + 1)

            memo_d[(r, c)] = res
            return res

        def get_next_down_from_mirror(r, c):
            if r >= m or c >= n:
                return (-1, -1)

            if grid[r][c] == 0:
                return (r, c)

            return get_next_right_from_mirror(r, c + 1)

        def get_next_right_from_mirror(r, c):
            if r >= m or c >= n:
                return (-1, -1)

            if grid[r][c] == 0:
                return (r, c)

            return get_next_down_from_mirror(r + 1, c)

        dp_r = [[0] * n for _ in range(m)]
        dp_d = [[0] * n for _ in range(m)]

        dp_r[0][0] = 1
        dp_d[0][0] = 1

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    continue

                if dp_r[i][j] > 0:
                    ni, nj = get_next_right(i, j)

                    if ni != -1:
                        dp_r[ni][nj] = (
                            dp_r[ni][nj] + dp_r[i][j]
                        ) % MOD

                        dp_d[ni][nj] = (
                            dp_d[ni][nj] + dp_r[i][j]
                        ) % MOD

                if dp_d[i][j] > 0:
                    ni, nj = get_next_down(i, j)

                    if ni != -1:
                        dp_r[ni][nj] = (
                            dp_r[ni][nj] + dp_d[i][j]
                        ) % MOD

                        dp_d[ni][nj] = (
                            dp_d[ni][nj] + dp_d[i][j]
                        ) % MOD

        ans = 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    continue

                if dp_r[i][j] > 0 and get_next_right(i, j) == (m - 1, n - 1):
                    ans = (ans + dp_r[i][j]) % MOD

                if dp_d[i][j] > 0 and get_next_down(i, j) == (m - 1, n - 1):
                    ans = (ans + dp_d[i][j]) % MOD

        return ans