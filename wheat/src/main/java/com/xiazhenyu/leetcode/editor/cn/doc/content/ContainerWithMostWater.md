<p>给你 <code>n</code> 个非负整数 <code>a<sub>1</sub>，a<sub>2，</sub>...，a</code><sub><code>n</code>，</sub>每个数代表坐标中的一个点&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 。在坐标内画 <code>n</code> 条垂直线，垂直线 <code>i</code>&nbsp;的两个端点分别为&nbsp;<code>(i,&nbsp;a<sub>i</sub>)</code> 和 <code>(i, 0)</code> 。找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>

<p><strong>说明：</strong>你不能倾斜容器。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" style="height: 287px; width: 600px;" /></p>

<pre>
<strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
<strong>输出：</strong>49 
<strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [1,1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>height = [4,3,2,1,4]
<strong>输出：</strong>16
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>height = [1,2,1]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li></div></div><br><div><li>👍 3090</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员；算法可视化编辑器上线，[点击体验](https://labuladong.online/algo-visualize/)！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=container-with-most-water" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

这题前文 [接雨水问题详解](https://labuladong.github.io/article/fname.html?fname=接雨水) 讲过的 [42. 接雨水](/problems/trapping-rain-water) 几乎一模一样。

区别在于：接雨水问题给出的类似一幅直方图，每个横坐标都有宽度，而本题给出的每个坐标是一条竖线，没有宽度。

接雨水问题更难一些，每个坐标对应的矩形通过左右的最大高度的最小值推算自己能装多少水：

![](https://labuladong.github.io/pictures/接雨水/5.jpg)

本题可完全套用接雨水问题的思路，相对还更简单：

**用 `left` 和 `right` 两个指针从两端向中心收缩，一边收缩一边计算 `[left, right]` 之间的矩形面积，取最大的面积值即是答案**。

不过肯定有读者会问，下面这段 if 语句为什么要移动较低的一边：

```java
// 双指针技巧，移动较低的一边
if (height[left] < height[right]) {
    left++;
} else {
    right--;
}
```

**其实也好理解，因为矩形的高度是由 `min(height[left], height[right])` 即较低的一边决定的**：

你如果移动较低的那一边，那条边可能会变高，使得矩形的高度变大，进而就「有可能」使得矩形的面积变大；相反，如果你去移动较高的那一边，矩形的高度是无论如何都不会变大的，所以不可能使矩形的面积变得更大。

**详细题解：[如何高效解决接雨水问题](https://labuladong.github.io/article/fname.html?fname=接雨水)**

**标签：[数组双指针](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120601117519675393)**

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
    int maxArea(vector<int>& height) {
        int left = 0, right = height.size() - 1;
        int res = 0;
        while (left < right) {
            // [left, right] 之间的矩形面积
            int cur_area = min(height[left], height[right]) * (right - left);
            res = max(res, cur_area);
            // 双指针技巧，移动较低的一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
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
    def maxArea(self, height: List[int]) -> int:
        left, right = 0, len(height)-1
        res = 0
        while left < right:
            # [left, right] 之间的矩形面积
            cur_area = min(height[left], height[right]) * (right - left)
            res = max(res, cur_area)
            # 双指针技巧，移动较低的一边
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1
        return res
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            // [left, right] 之间的矩形面积
            int cur_area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, cur_area);
            // 双指针技巧，移动较低的一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
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

func maxArea(height []int) int {
    left, right := 0, len(height)-1
    res := 0
    for left < right {
        // [left, right] 之间的矩形面积
        curArea := func() int {
            if height[left] < height[right] {
                return height[left] * (right - left)
            }
            return height[right] * (right - left)
        }()
        res = func() int {
            if curArea > res {
                return curArea
            }
            return res
        }()
        // 双指针技巧，移动较低的一边
        if height[left] < height[right] {
            left++
        } else {
            right--
        }
    }
    return res
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function(height) {
    let left = 0, right = height.length - 1;
    let res = 0;
    while (left < right) {
        // [left, right] 之间的矩形面积
        const cur_area = Math.min(height[left], height[right]) * (right - left);
        res = Math.max(res, cur_area);
        // 双指针技巧，移动较低的一边
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }
    return res;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🥳🥳 算法可视化 🥳🥳</strong></summary><div id="data_container-with-most-water" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_container-with-most-water"></div></div>
</details><hr /><br />

**类似题目**：
  - [42. 接雨水 🔴](/problems/trapping-rain-water)

</details>
</div>



