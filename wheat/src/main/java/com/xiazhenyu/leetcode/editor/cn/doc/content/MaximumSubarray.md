<p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>

<p><strong>子数组 </strong>是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong>6
<strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,-1,7,8]
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>
<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 4380</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员；算法可视化编辑器上线，[点击体验](https://labuladong.online/algo-visualize/)！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=maximum-subarray" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

PS：这道题在[《算法小抄》](https://item.jd.com/12759911.html) 的第 108 页。

这题类似 [300. 最长递增子序列](/problems/longest-increasing-subsequence)，`dp` 数组的含义：

**以 `nums[i]` 为结尾的「最大子数组和」为 `dp[i]`**。

`dp[i]` 有两种「选择」，要么与前面的相邻子数组连接，形成一个和更大的子数组；要么不与前面的子数组连接，自成一派，自己作为一个子数组。

在这两种选择中择优，就可以计算出最大子数组，而且空间复杂度还有优化空间，见详细题解。

**详细题解：[动态规划设计：最大子数组](https://labuladong.github.io/article/fname.html?fname=最大子数组)**

**标签：[一维动态规划](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122007027366395905)，[动态规划](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=1318881141113536512)，[数组](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120601117519675393)**

## 解法代码

提示：🟢 标记的是我写的解法代码，🤖 标记的是 chatGPT 翻译的多语言解法代码。如有错误，可以 [点这里](https://github.com/labuladong/fucking-algorithm/issues/1113) 反馈和修正。

<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int n = nums.size();
        if (n == 0) return 0;
        vector<int> dp(n);
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            dp[i] = max(nums[i], nums[i] + dp[i - 1]);
        }
        // 得到 nums 的最大子数组
        int res = INT_MIN;
        for (int i = 0; i < n; i++) {
            res = max(res, dp[i]);
        }
        return res;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 0:
            return 0
        dp = [0] * n
        # base case
        # 第一个元素前面没有子数组
        dp[0] = nums[0]
        # 状态转移方程
        for i in range(1, n):
            dp[i] = max(nums[i], nums[i] + dp[i - 1])
        # 得到 nums 的最大子数组
        res = float('-inf')
        for i in range(n):
            res = max(res, dp[i])
        return res
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func maxSubArray(nums []int) int {
    n := len(nums)
    if n == 0 {
        return 0
    }
    dp := make([]int, n)
    // base case
    // 第一个元素前面没有子数组
    dp[0] = nums[0]
    // 状态转移方程
    for i := 1; i < n; i++ {
        dp[i] = max(nums[i], nums[i] + dp[i - 1])
    }
    // 得到 nums 的最大子数组
    res := math.MinInt32
    for i := 0; i < n; i++ {
        res = max(res, dp[i])
    }
    return res
}
   
func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function(nums) {
    const n = nums.length;
    if (n === 0) return 0;
    const dp = new Array(n);
    // base case
    // 第一个元素前面没有子数组
    dp[0] = nums[0];
    // 状态转移方程
    for (let i = 1; i < n; i++) {
        dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
    }
    // 得到 nums 的最大子数组
    let res = -Infinity;
    for (let i = 0; i < n; i++) {
        res = Math.max(res, dp[i]);
    }
    return res;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>👾👾 算法可视化 👾👾</strong></summary><div id="data_maximum-subarray" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_maximum-subarray"></div></div>
</details><hr /><br />

**类似题目**：
  - [152. 乘积最大子数组 🟠](/problems/maximum-product-subarray)
  - [209. 长度最小的子数组 🟠](/problems/minimum-size-subarray-sum)
  - [918. 环形子数组的最大和 🟠](/problems/maximum-sum-circular-subarray)
  - [剑指 Offer 42. 连续子数组的最大和 🟢](/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof)
  - [剑指 Offer II 008. 和大于等于 target 的最短子数组 🟠](/problems/2VG8Kg)

</details>
</div>



