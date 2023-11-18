<p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的 <strong>最长子串 </strong>的长度。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s = "abcabcbb"
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "bbbbb"
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = "pwwkew"
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是 <code>"wke"</code>，所以其长度为 3。
     请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code> 是一个<em>子序列，</em>不是子串。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入: </strong>s = ""
<strong>输出: </strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由英文字母、数字、符号和空格组成</li>
</ul>
<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 6722</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=longest-substring-without-repeating-characters" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[滑动窗口算法核心模板框架](https://www.bilibili.com/video/BV1AV4y1n7Zt)

PS：这道题在[《算法小抄》](https://item.jd.com/12759911.html) 的第 85 页。

这题比其他滑动窗口的题目简单，连 `need` 和 `valid` 都不需要，而且更新窗口内数据也只需要简单的更新计数器 `window` 即可。

当 `window[c]` 值大于 1 时，说明窗口中存在重复字符，不符合条件，就该移动 `left` 缩小窗口了。

另外，要在收缩窗口完成后更新 `res`，因为窗口收缩的 while 条件是存在重复元素，换句话说收缩完成后一定保证窗口中没有重复。

**详细题解：[我写了首诗，把滑动窗口算法变成了默写题](https://labuladong.github.io/article/fname.html?fname=滑动窗口技巧进阶)**

**标签：[滑动窗口](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120601117519675393)**

## 解法代码

提示：🟢 标记的是我写的解法代码，🤖 标记的是 chatGPT 翻译的多语言解法代码。如有错误，可以 [点这里](https://github.com/labuladong/fucking-algorithm/issues/1113) 反馈和修正。

<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">cpp🟢</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">java🤖</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item active" data-tab-group="default"><div class="highlight">

```cpp
class Solution {
    public:
    int lengthOfLongestSubstring(string s) {
        unordered_map<char, int> window;

        int left = 0, right = 0;
        int res = 0; // 记录结果
        while (right < s.size()) {
            char c = s[right];
            right++;
            // 进行窗口内数据的一系列更新
            window[c]++;
            // 判断左侧窗口是否要收缩
            while (window[c] > 1) {
                char d = s[left];
                left++;
                // 进行窗口内数据的一系列更新
                window[d]--;
            }
            // 在这里更新答案
            res = max(res, right - left);
        }
        return res;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        window = {}

        left = right = 0
        res = 0 # 记录结果
        while right < len(s):
            c = s[right]
            right += 1
            # 进行窗口内数据的一系列更新
            window[c] = window.get(c, 0) + 1
            # 判断左侧窗口是否要收缩
            while window[c] > 1:
                d = s[left]
                left += 1
                # 进行窗口内数据的一系列更新
                window[d] -= 1
            # 在这里更新答案
            res = max(res, right - left)
        return res
```

</div></div>

<div data-tab-item="java" class="tab-item " data-tab-group="default"><div class="highlight">

```java
// 注意：java 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0; // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(d, window.get(d) - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func lengthOfLongestSubstring(s string) int {
    window := make(map[byte]int)

    left, right := 0, 0
    res := 0 // 记录结果
    for right < len(s) {
        c := s[right]
        right++
        // 进行窗口内数据的一系列更新
        window[c]++
        // 判断左侧窗口是否要收缩
        for window[c] > 1 {
            d := s[left]
            left++
            // 进行窗口内数据的一系列更新
            window[d]--
        }
        // 在这里更新答案
        res = max(res, right - left)
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
// 注意：javascript 代码由 chatGPT🤖 根据我的 cpp 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

var lengthOfLongestSubstring = function(s) {
    let window = new Map();
    let left = 0, right = 0, res = 0;
    while (right < s.length) {
        let c = s[right];
        right++;
        // 进行窗口内数据的一系列更新
        window.set(c, (window.get(c) || 0) + 1);
        // 判断左侧窗口是否要收缩
        while (window.get(c) > 1) {
            let d = s[left];
            left++;
            // 进行窗口内数据的一系列更新
            window.set(d, window.get(d) - 1);
        }
        // 在这里更新答案
        res = Math.max(res, right - left);
    }
    return res;
};
```

</div></div>
</div></div>

<details open><summary><strong>👾👾 算法可视化 👾👾</strong></summary><div id="data_longest-substring-without-repeating-characters" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_longest-substring-without-repeating-characters"></div></div>
</details><hr /><br />

**类似题目**：
  - [438. 找到字符串中所有字母异位词 🟠](/problems/find-all-anagrams-in-a-string)
  - [567. 字符串的排列 🟠](/problems/permutation-in-string)
  - [76. 最小覆盖子串 🔴](/problems/minimum-window-substring)
  - [剑指 Offer 48. 最长不含重复字符的子字符串 🟠](/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof)
  - [剑指 Offer II 014. 字符串中的变位词 🟠](/problems/MPnaiL)
  - [剑指 Offer II 015. 字符串中的所有变位词 🟠](/problems/VabMRr)
  - [剑指 Offer II 016. 不含重复字符的最长子字符串 🟠](/problems/wtcaE1)
  - [剑指 Offer II 017. 含有所有字符的最短字符串 🔴](/problems/M1oyTv)

</details>
</div>

