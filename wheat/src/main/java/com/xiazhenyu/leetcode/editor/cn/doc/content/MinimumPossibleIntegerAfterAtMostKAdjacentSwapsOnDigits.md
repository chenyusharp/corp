<p>给你一个字符串&nbsp;<code>num</code> 和一个整数&nbsp;<code>k</code> 。其中，<code>num</code> 表示一个很大的整数，字符串中的每个字符依次对应整数上的各个 <strong>数位</strong> 。</p>

<p>你可以交换这个整数相邻数位的数字 <strong>最多</strong>&nbsp;<code>k</code>&nbsp;次。</p>

<p>请你返回你能得到的最小整数，并以字符串形式返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/06/17/q4_1.jpg" style="height:40px; width:500px" /></p>

<pre>
<strong>输入：</strong>num = "4321", k = 4
<strong>输出：</strong>"1342"
<strong>解释：</strong>4321 通过 4 次交换相邻数位得到最小整数的步骤如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = "100", k = 1
<strong>输出：</strong>"010"
<strong>解释：</strong>输出可以包含前导 0 ，但输入保证不会有前导 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>num = "36789", k = 1000
<strong>输出：</strong>"36789"
<strong>解释：</strong>不需要做任何交换。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>num = "22", k = 22
<strong>输出：</strong>"22"
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>num = "9438957234785635408", k = 23
<strong>输出：</strong>"0345989723478563548"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= num.length &lt;= 30000</code></li> 
 <li><code>num</code>&nbsp;只包含&nbsp;<strong>数字</strong>&nbsp;且不含有<strong>&nbsp;前导 0&nbsp;</strong>。</li> 
 <li><code>1 &lt;= k &lt;= 10^9</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>贪心 | 树状数组 | 线段树 | 字符串</details><br>

<div>👍 81, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员！**

</div>

