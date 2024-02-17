<p>给你一个整数 <code>x</code> ，如果 <code>x</code> 是一个回文整数，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，<code>121</code> 是回文，而 <code>123</code> 不是。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 121
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = -121
<strong>输出：</strong>false
<strong>解释：</strong>从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 10
<strong>输出：</strong>false
<strong>解释：</strong>从右向左读, 为 01 。因此它不是一个回文数。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>x = -101
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= x <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能不将整数转为字符串来解决这个问题吗？</p>
<div><div>Related Topics</div><div><li>数学</li></div></div><br><div><li>👍 1762</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员；算法可视化编辑器上线，[点击体验](https://labuladong.online/algo-visualize/)！**

<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

如果让你判断回文串应该很简单，我在 [数组双指针技巧汇总](https://labuladong.github.io/article/fname.html?fname=双指针技巧) 中讲过。

操作数字没办法像操作字符串那么简单粗暴，但只要你要知道我在 [Rabin Karp 算法详解](https://labuladong.github.io/article/fname.html?fname=rabinkarp) 中讲到的从最高位开始生成数字的技巧，就能轻松解决这个问题：

```java
String s = "8264";
int number = 0;
for (int i = 0; i < s.size(); i++) {
    // 将字符转化成数字
    number = 10 * number + (s[i] - '0');
    print(number);
}
// 打印输出：
// 8
// 82
// 826
// 8264
```

你**从后往前**把 `x` 的每一位拿出来，用这个技巧生成一个数字 `y`，如果 `y` 和 `x` 相等，则说明 `x` 是回文数字。

如何**从后往前**拿出一个数字的每一位？和 10 求余数就行了呗。看代码吧。

**标签：[数学](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2122023604245659649)**

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
    bool isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        // y 是 x 翻转后的数字
        int y = 0;
        while (temp > 0) {
            int last_num = temp % 10;
            temp = temp / 10;
            // 从最高位生成数字的技巧
            y = y * 10 + last_num;
        }
        return y == x;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        temp = x
        # y 是 x 翻转后的数字
        y = 0
        while temp > 0:
            last_num = temp % 10
            temp = temp // 10
            # 从最高位生成数字的技巧
            y = y * 10 + last_num
        return y == x
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        // y 是 x 翻转后的数字
        int y = 0;
        while (temp > 0) {
            int last_num = temp % 10;
            temp = temp / 10;
            // 从最高位生成数字的技巧
            y = y * 10 + last_num;
        }
        return y == x;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码不保证正确性，仅供参考。如有疑惑，可以参照我写的 java 代码对比查看。

// IsPalindrome 是判断一个整数是否为回文数的函数
func IsPalindrome(x int) bool {
	// 如果 x 是负数，那么它不可能为回文数，直接返回 false
	if x < 0 {
		return false
	}

	// temp 是 x 的副本
	temp := x
	// y 是 x 翻转后的数字
	y := 0

	// 将 temp 逆序生成 y
	for temp > 0 {
		lastNum := temp % 10
		temp = temp / 10
		y = y*10 + lastNum
	}

	// 如果 x 和 y 相等，那么 x 就是回文数
	return y == x
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function(x) {
    if (x < 0) {
        return false;
    }
    var temp = x;
    // y 是 x 翻转后的数字
    var y = 0;
    while (temp > 0) {
        var last_num = temp % 10;
        temp = Math.floor(temp / 10);
        // 从最高位生成数字的技巧
        y = y * 10 + last_num;
    }
    return y == x;
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🍭🍭 算法可视化 🍭🍭</strong></summary><div id="data_palindrome-number" data="GxoYACwKbGPayGEXFEZXNj+GGE6a8nFULeJWfr5BXrjwrrdUFYXAu5sa/73KJgimCMVm3bRm7pd7O13zcmpXiVhvu14RGSRAGAa+mbWsFaViJjTSVf1jFM5spt7zkR/6/7/2P/XmmO1FKmIDFTK/QWjMOfe+jepF3GYOphIq5EKkNrFhGZsGKD6S2EYZ8/BaRnYaeUteHKyjhETczdzpLww8ui37osEhljTWp+qhrcDU8le8r1Rfhxc7AZH8UMQKJsxhHK9qvpedXABaksn2iHCod/J8gpq/UwLwSStcMonY5l1qjgbaKzlMhfHOI/7env1SQQXqyfoZCcrUxozD32jmL201MGDl/CY7Xb4IkXN0jKddEshQkBhNijbVUhNAoLNhEH3isyOLILA2A5nU4D5GlMss0J6OOPo+DZYC1+sxQnoOV6qufLCKM9JEop+d2ZB3CmHkGpaPOsR3xVCoOrI/NlQNB4oPM8UbyBrM8lhFiMyiHv3ezNN52Hgh2jpkBP3Y01+XPnjZ1fAKTz5BtQGpPqwPbgTDtB/XH7n68ad/zm48e/g8QRRD8Dz2Xh6VAWKo86TABmOfH5xPsQIfrfWLklv+MzYDlaXX+0rxHYvvQmQEbVbZZ5XyV325ct5VZFe7gaqlWaHMLGQmIDU5Hbv1Z9JW7tckLPe5X5/7+G+GWFiZV42NqMOnU3qgrhjZc4x6tyV5FFtvDBRirkvrS4qm3zqNx/n/LyFENfbZ6Xt0hUPM3DLXyc2s1FDQO31Qi203/wzOBNBV9ffUQ4Iw305XEkSlhFN8NYxv1iF4X1/d5jI7zFrCbtGdXSusyoil5wfwiIktsnab8Oy6mCBkrlwPR+BgZaFFkahaJ1npGBDsMPV186Y8nQf+YgBksG35LlnhQCmEtHX84M8QUAEZncF0xjGT2GEUvC1d5calcE39UUkhJA5YFq6UvlJoTvCei8zVc80OUAVNWn6JArNBNDWLwBmWYLDllRSBM8zBGbSk5akMCVE0WeTsmRAWOMt8/giawvKIadH85cLzsejqnOkab3F7dSjg20H3wuyiESJihlLwrhQCpJDdD2mKL0gVsya9+K81lEwcmJeKcSnFt8iKicIAhtpX3FqatokNsfOanfrQfBMfOoeOL5XV587W6Ow0h2KTCz6A84gi703MA7db9FYVWhK4fR5h2pQzEXX5ggTuwMOf8gvBRyXtYVmZugk/rZ5MQBsJsvbd0mkOBbhvwEnZOuEX0hhjVuGx6BSiyYsqxrwEyiuixWG44Fp1CI4zklKOKWCboKjuvWnK3UZdtvXMgScuY6MzRYq+cn/2MK3mauVfS8BP9ZXjJ4BsGuruvUPb/kr1wMAZTpOKx7jo9urQHMoVVJWy8CU7BMW4jVSERc7+YOgUAQvE2jFnqeDy6s9fakbGELITWrjnNMKEqRiCnfxdStSqDabS0nSyuKGzzgOVmWd4U2CdDbxxlbofNtPCGLDtwnvFi4iKDbI5xXzS82Jj8sEb0S5auluUahHR3OJUwkpdfub7p08vL5QYacfjBGEGL9ghI88nnyF8gTB+147tkTDEUysn6ReOL6V9YjkoOPKblklIvLZMBqPejwo/KAbZkboQkfmXKmG/b/c9p/e7a7V85J1kSbdjWdV4938IK7yTIxXeRW7rYkaCSBdqAECmHCVJXrvqjnVWJ1+ko3D+O2Wno/tyyDPa/CrSOsSEuj2ICSfaHsIM0JOlIx0SDCWx74Ni3O5BPMxHNX3N3skYC9vsAXG5JQ7P7C9UzTCeEV8/fyo5+F0U+d+8lorPIQuhgoxnI2e7cDocrzM="></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_palindrome-number"></div></div>
</details><hr /><br />

</details>
</div>



