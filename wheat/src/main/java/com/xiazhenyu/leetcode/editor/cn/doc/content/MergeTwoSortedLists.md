<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 </p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" />
<pre>
<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
<strong>输出：</strong>[1,1,2,3,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两个链表的节点数目范围是 <code>[0, 50]</code></li>
	<li><code>-100 <= Node.val <= 100</code></li>
	<li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li>
</ul>
<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2138</li><li>👎 0</li></div>

<div id="labuladong"><hr>

**通知：[数据结构精品课](https://aep.h5.xeknow.com/s/1XJHEO) 和 [递归算法专题课](https://aep.xet.tech/s/3YGcq3) 限时附赠网站会员！**



<p><strong><a href="https://labuladong.github.io/article/slug.html?slug=merge-two-sorted-lists" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>

## 基本思路

> 本文有视频版：[链表双指针技巧全面汇总](https://www.bilibili.com/video/BV1q94y1X7vy)

经典算法题了，[双指针技巧](https://labuladong.github.io/article/fname.html?fname=链表技巧) 用起来。

![](https://labuladong.github.io/pictures/链表技巧/1.gif)

这个算法的逻辑类似于「拉拉链」，`l1, l2` 类似于拉链两侧的锯齿，指针 `p` 就好像拉链的拉索，将两个有序链表合并。

**代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，也就是 `dummy` 节点**，它相当于是个占位符，可以避免处理空指针的情况，降低代码的复杂性。

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
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        // 虚拟头结点
        ListNode* dummy = new ListNode(-1), *p = dummy;
        ListNode* p1 = l1, *p2 = l2;

        while (p1 != nullptr && p2 != nullptr) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1->val > p2->val) {
                p->next = p2;
                p2 = p2->next;
            } else {
                p->next = p1;
                p1 = p1->next;
            }
            // p 指针不断前进
            p = p->next;
        }

        if (p1 != nullptr) {
            p->next = p1;
        }

        if (p2 != nullptr) {
            p->next = p2;
        }

        return dummy->next;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
# 本代码已经通过力扣的测试用例，应该可直接成功提交。

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        # 虚拟头结点
        dummy = ListNode(-1)
        p = dummy
        p1 = l1
        p2 = l2

        while p1 and p2: # <extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) #
            # 比较 p1 和 p2 两个指针
            # 将值较小的的节点接到 p 指针
            if p1.val > p2.val:
                p.next = p2
                p2 = p2.next
            else:
                p.next = p1
                p1 = p1.next
            # p 指针不断前进
            p = p.next

        if p1:
            p.next = p1

        if p2:
            p.next = p2

        return dummy.next
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    // 虚拟头结点
    dummy := &ListNode{-1, nil}
    p := dummy
    p1 := l1
    p2 := l2

    for p1 != nil && p2 != nil {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
        // 比较 p1 和 p2 两个指针
        // 将值较小的的节点接到 p 指针
        if p1.Val > p2.Val {
            p.Next = p2
            p2 = p2.Next
        } else {
            p.Next = p1
            p1 = p1.Next
        }
        // p 指针不断前进
        p = p.Next
    }

    if p1 != nil {
        p.Next = p1
    }

    if p2 != nil {
        p.Next = p2
    }

    return dummy.Next
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译，旨在帮助不同背景的读者理解算法逻辑。
// 本代码已经通过力扣的测试用例，应该可直接成功提交。

/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function(l1, l2) {
    // 虚拟头结点
    var dummy = new ListNode(-1), p = dummy;
    var p1 = l1, p2 = l2;

    while (p1 !== null && p2 !== null) {/**<extend down -200>![](https://labuladong.github.io/pictures/链表技巧/1.gif) */
        // 比较 p1 和 p2 两个指针
        // 将值较小的的节点接到 p 指针
        if (p1.val > p2.val) {
            p.next = p2;
            p2 = p2.next;
        } else {
            p.next = p1;
            p1 = p1.next;
        }
        // p 指针不断前进
        p = p.next;
    }

    if (p1 !== null) {
        p.next = p1;
    }

    if (p2 !== null) {
        p.next = p2;
    }

    return dummy.next;
};
```

</div></div>
</div></div>

<details open><summary><strong>🥳🥳 算法可视化 🥳🥳</strong></summary><div id="data_merge-two-sorted-lists" data="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"></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_merge-two-sorted-lists"></div></div>
</details><hr /><br />

**类似题目**：
  - [1305. 两棵二叉搜索树中的所有元素 🟠](/problems/all-elements-in-two-binary-search-trees)
  - [141. 环形链表 🟢](/problems/linked-list-cycle)
  - [142. 环形链表 II 🟠](/problems/linked-list-cycle-ii)
  - [160. 相交链表 🟢](/problems/intersection-of-two-linked-lists)
  - [19. 删除链表的倒数第 N 个结点 🟠](/problems/remove-nth-node-from-end-of-list)
  - [23. 合并K个升序链表 🔴](/problems/merge-k-sorted-lists)
  - [264. 丑数 II 🟠](/problems/ugly-number-ii)
  - [313. 超级丑数 🟠](/problems/super-ugly-number)
  - [86. 分隔链表 🟠](/problems/partition-list)
  - [876. 链表的中间结点 🟢](/problems/middle-of-the-linked-list)
  - [88. 合并两个有序数组 🟢](/problems/merge-sorted-array)
  - [97. 交错字符串 🟠](/problems/interleaving-string)
  - [977. 有序数组的平方 🟢](/problems/squares-of-a-sorted-array)
  - [剑指 Offer 22. 链表中倒数第k个节点 🟢](/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof)
  - [剑指 Offer 25. 合并两个排序的链表 🟢](/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof)
  - [剑指 Offer 49. 丑数 🟠](/problems/chou-shu-lcof)
  - [剑指 Offer 52. 两个链表的第一个公共节点 🟢](/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof)
  - [剑指 Offer II 021. 删除链表的倒数第 n 个结点 🟠](/problems/SLwz0R)
  - [剑指 Offer II 022. 链表中环的入口节点 🟠](/problems/c32eOV)
  - [剑指 Offer II 023. 两个链表的第一个重合节点 🟢](/problems/3u1WK4)
  - [剑指 Offer II 078. 合并排序链表 🔴](/problems/vvXgSW)

</details>
</div>









