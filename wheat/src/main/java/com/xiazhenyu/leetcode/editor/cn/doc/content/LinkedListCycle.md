<p>给你一个链表的头节点 <code>head</code> ，判断链表中是否有环。</p>

<p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（索引从 0 开始）。<strong>注意：<code>pos</code> 不作为参数进行传递&nbsp;</strong>。仅仅是为了标识链表的实际情况。</p>

<p><em>如果链表中存在环</em>&nbsp;，则返回 <code>true</code> 。 否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" /></p>

<pre>
<strong>输入：</strong>head = [3,2,0,-4], pos = 1
<strong>输出：</strong>true
<strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" /></p>

<pre>
<strong>输入：</strong>head = [1,2], pos = 0
<strong>输出：</strong>true
<strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" /></p>

<pre>
<strong>输入：</strong>head = [1], pos = -1
<strong>输出：</strong>false
<strong>解释：</strong>链表中没有环。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目范围是 <code>[0, 10<sup>4</sup>]</code></li> 
 <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li><code>pos</code> 为 <code>-1</code> 或者链表中的一个 <strong>有效索引</strong> 。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能用 <code>O(1)</code>（即，常量）内存解决此问题吗？</p>

<details><summary><strong>Related Topics</strong></summary>哈希表 | 链表 | 双指针</details><br>

<div>👍 1937, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/discussions/939' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.gitee.io/article/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.github.io/algo/images/others/%E5%85%A8%E5%AE%B6%E6%A1%B6.jpg' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=linked-list-cycle" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[链表双指针技巧全面汇总](https://www.bilibili.com/video/BV1q94y1X7vy)

PS：这道题在[《算法小抄》](https://item.jd.com/12759911.html) 的第 64 页。

经典题目了，要使用双指针技巧中的快慢指针，每当慢指针 `slow` 前进一步，快指针 `fast` 就前进两步。

如果 `fast` 最终遇到空指针，说明链表中没有环；如果 `fast` 最终和 `slow` 相遇，那肯定是 `fast` 超过了 `slow` 一圈，说明链表中含有环。

**详细题解：[双指针技巧秒杀七道链表题目](https://labuladong.github.io/article/fname.html?fname=链表技巧)**

**标签：[数据结构](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=1318892385270808576)，[链表](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)，[链表双指针](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzAxODQxMDM0Mw==&action=getalbum&album_id=2120596033251475465)**

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
    bool hasCycle(ListNode *head) {
        // 快慢指针初始化指向 head
        ListNode *slow = head, *fast = head;
        // 快指针走到末尾时停止
        while (fast != nullptr && fast->next != nullptr) {
            // 慢指针走一步，快指针走两步
            slow = slow->next;
            fast = fast->next->next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        # 快慢指针初始化指向 head
        slow, fast = head, head
        # 快指针走到末尾时停止
        while fast and fast.next:
            # 慢指针走一步，快指针走两步
            slow = slow.next
            fast = fast.next.next
            # 快慢指针相遇，说明含有环
            if slow == fast:
                return True
        # 不包含环
        return False
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

func hasCycle(head *ListNode) bool {
    // 快慢指针初始化指向 head
    slow, fast := head, head
    // 快指针走到末尾时停止
    for fast != nil && fast.Next != nil {
        // 慢指针走一步，快指针走两步
        slow = slow.Next
        fast = fast.Next.Next
        // 快慢指针相遇，说明含有环
        if slow == fast {
            return true
        }
    }
    // 不包含环
    return false
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {ListNode} head
 * @return {boolean}
 */
var hasCycle = function(head) {
    // 快慢指针初始化指向 head
    let slow = head, fast = head;
    // 快指针走到末尾时停止
    while (fast !== null && fast.next !== null) {
        // 慢指针走一步，快指针走两步
        slow = slow.next;
        fast = fast.next.next;
        // 快慢指针相遇，说明含有环
        if (slow === fast) {
            return true;
        }
    }
    // 不包含环
    return false;
};
```

</div></div>
</div></div>

<details open><summary><strong>🌟🌟 算法可视化 🌟🌟</strong></summary><div id="data_linked-list-cycle" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_linked-list-cycle"></div></div>
</details><hr /><br />

**类似题目**：
  - [142. 环形链表 II 🟠](/problems/linked-list-cycle-ii)
  - [160. 相交链表 🟢](/problems/intersection-of-two-linked-lists)
  - [19. 删除链表的倒数第 N 个结点 🟠](/problems/remove-nth-node-from-end-of-list)
  - [21. 合并两个有序链表 🟢](/problems/merge-two-sorted-lists)
  - [23. 合并K个升序链表 🔴](/problems/merge-k-sorted-lists)
  - [86. 分隔链表 🟠](/problems/partition-list)
  - [876. 链表的中间结点 🟢](/problems/middle-of-the-linked-list)
  - [剑指 Offer 22. 链表中倒数第k个节点 🟢](/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof)
  - [剑指 Offer 25. 合并两个排序的链表 🟢](/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof)
  - [剑指 Offer 52. 两个链表的第一个公共节点 🟢](/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof)
  - [剑指 Offer II 021. 删除链表的倒数第 n 个结点 🟠](/problems/SLwz0R)
  - [剑指 Offer II 022. 链表中环的入口节点 🟠](/problems/c32eOV)
  - [剑指 Offer II 023. 两个链表的第一个重合节点 🟢](/problems/3u1WK4)
  - [剑指 Offer II 078. 合并排序链表 🔴](/problems/vvXgSW)

</details>
</div>









