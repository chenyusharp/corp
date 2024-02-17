<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值 </strong><em><code>target</code></em>&nbsp; 的那&nbsp;<strong>两个</strong>&nbsp;整数，并返回它们的数组下标。</p>

<p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>

<p>你可以按任意顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,7,11,15], target = 9
<strong>输出：</strong>[0,1]
<strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,4], target = 6
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3], target = 6
<strong>输出：</strong>[0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><strong>只会存在一个有效答案</strong></li>
</ul>

<p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>
<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 15805</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员；算法可视化编辑器上线，[点击体验](https://labuladong.online/algo-visualize/)！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=two-sum" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

大家都喜欢幽默的人，如果你想调侃自己经常拖延，可以这样调侃下自己（手动狗头）：

背单词背了半年还是 abandon, abandon，刷题刷了半年还是 two sum, two sum...

言归正传，这道题不难，但由于它是 LeetCode 第一题，所以名气比较大，解决这道题也可以有多种思路，我这里说两种最常见的思路。

第一种思路就是靠排序，把 `nums` 排序之后就可以用 [数组双指针技巧汇总](https://labuladong.github.io/article/fname.html?fname=双指针技巧) 中讲到的左右指针来求出和为 `target` 的两个数。

不过因为题目要求我们返回元素的索引，而排序会破坏元素的原始索引，所以要记录值和原始索引的映射。

进一步，如果题目拓展延伸一下，让你求三数之和、四数之和，你依然可以用双指针技巧，我在 [一个函数秒杀 nSum 问题](https://labuladong.github.io/article/fname.html?fname=nSum) 中写一个函数来解决所有 N 数之和问题。

第二种思路是用哈希表辅助判断。对于一个元素 `nums[i]`，你想知道有没有另一个元素 `nums[j]` 的值为 `target - nums[i]`，这很简单，我们用一个哈希表记录每个元素的值到索引的映射，这样就能快速判断数组中是否有一个值为 `target - nums[i]` 的元素了。

简单说，数组其实可以理解为一个「索引 -> 值」的哈希表映射，而我们又建立一个「值 -> 索引」的映射即可完成此题。

**详细题解：[一个方法团灭 nSum 问题](https://labuladong.github.io/article/fname.html?fname=nSum)**

**标签：[双指针](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)，哈希表，[数组](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120601117519675393)**

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
    vector<int> twoSum(vector<int>& nums, int target) {
        // 维护 val -> index 的映射
        unordered_map<int, int> valToIndex;
        for (int i = 0; i < nums.size(); i++) {
            // 查表，看看是否有能和 nums[i] 凑出 target 的元素
            int need = target - nums[i];
            if (valToIndex.count(need)) {
                return vector<int>{valToIndex[need], i};
            }
            // 存入 val -> index 的映射
            valToIndex[nums[i]] = i;
        }
        return vector<int>{};
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # 维护 val -> index 的映射
        valToIndex = {}
        for i in range(len(nums)):
            # 查表，看看是否有能和 nums[i] 凑出 target 的元素
            need = target - nums[i]
            if need in valToIndex:
                return [valToIndex[need], i]
            # 存入 val -> index 的映射
            valToIndex[nums[i]] = i
        return []
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 维护 val -> index 的映射
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 查表，看看是否有能和 nums[i] 凑出 target 的元素
            int need = target - nums[i];
            if (valToIndex.containsKey(need)) {
                return new int[]{valToIndex.get(need), i};
            }
            // 存入 val -> index 的映射
            valToIndex.put(nums[i], i);
        }
        return null;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func twoSum(nums []int, target int) []int {
    // 维护 val -> index 的映射
    valToIndex := make(map[int]int)

    for i, num := range nums {
        // 查表，看看是否有能和 nums[i] 凑出 target 的元素
        need := target - num
        if valToIndex[need] != 0 {
            return []int{valToIndex[need] - 1, i}
        }
        // 存入 val -> index 的映射
        valToIndex[num] = i + 1
    }

    return nil
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var twoSum = function(nums, target) {
    // 维护 val -> index 的映射
    var valToIndex = new Map();
    for (var i = 0; i < nums.length; i++) {
        // 查表，看看是否有能和 nums[i] 凑出 target 的元素
        var need = target - nums[i];
        if (valToIndex.has(need)) {
            return [valToIndex.get(need), i];
        }
        // 存入 val -> index 的映射
        valToIndex.set(nums[i], i);
    }
    return null;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>👾👾 算法可视化 👾👾</strong></summary><div id="data_two-sum" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_two-sum"></div></div>
</details><hr /><br />

**类似题目**：
  - [15. 三数之和 🟠](/problems/3sum)
  - [167. 两数之和 II - 输入有序数组 🟠](/problems/two-sum-ii-input-array-is-sorted)
  - [18. 四数之和 🟠](/problems/4sum)
  - [剑指 Offer 57. 和为s的两个数字 🟢](/problems/he-wei-sde-liang-ge-shu-zi-lcof)
  - [剑指 Offer II 007. 数组中和为 0 的三个数 🟠](/problems/1fGaJU)

</details>
</div>













