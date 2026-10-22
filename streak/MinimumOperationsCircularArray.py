
class Solution:
    def minOperations(self, nums: list[int]) -> int:
        n = len(nums)
        idx0 = nums.index(0)

        is_inc = True
        for i in range(n):
            if nums[(idx0 + i) % n] != i:
                is_inc = False
                break

        is_dec = True
        for i in range(n):
            if nums[(idx0 - i + n) % n] != i:
                is_dec = False
                break

        ans = float('inf')

        if is_inc:
            ans = min(ans, idx0, n - idx0 + 2)

        if is_dec:
            ans = min(ans, n - idx0, idx0 + 2)

        return ans if ans != float('inf') else -1


if __name__ == "__main__":
    solution = Solution()
    print(solution.minOperations([0, 1, 2, 3]))
