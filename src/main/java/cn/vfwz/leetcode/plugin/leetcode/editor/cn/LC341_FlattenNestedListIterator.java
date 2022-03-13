package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.NestedInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * author: vfwz
 * date: 2022-03-13 15:58:11
 * title: [341]扁平化嵌套列表迭代器
 */
@Slf4j
public class LC341_FlattenNestedListIterator {

    /**
     * [[],[3]]
     * [[],[[]],-4,[[[]]],[[],2,[[]],[[-3],1],[[],-1]]]
     */

    @Test
    public void testSolution() {
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    /**
     * 方法二：在构造器里就确定所有元素
     */
    public class NestedIterator implements Iterator<Integer> {

        private final Iterator<Integer> innerIterator;

        public NestedIterator(List<NestedInteger> nestedList) {
            innerIterator = getAllInteger(nestedList).iterator();
        }

        private List<Integer> getAllInteger(List<NestedInteger> nestedList) {
            List<Integer> allList = new ArrayList<>();
            for (NestedInteger nestedInteger : nestedList) {
                if(nestedInteger.isInteger()) {
                    allList.add(nestedInteger.getInteger());
                } else {
                    allList.addAll(getAllInteger(nestedInteger.getList()));
                }
            }
            return allList;
        }

        @Override
        public Integer next() {
            return this.innerIterator.next();

        }

        @Override
        public boolean hasNext() {
            return this.innerIterator.hasNext();
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    /**
     * 方法一：使用嵌套迭代实现
     * 满足迭代器的理念，不对所有值做存储，只提供访问途径
     */
    public class NestedIterator1 implements Iterator<Integer> {

        private List<NestedInteger> innerList;
        private int nextIndex;
        private NestedIterator nextIterator;

        public NestedIterator1(List<NestedInteger> nestedList) {
            innerList = nestedList;
            nextIndex = 0;
        }

        @Override
        public Integer next() {
            Integer nextInteger = null;
            NestedInteger nextNestedInteger = innerList.get(nextIndex);
            if (nextNestedInteger.isInteger()) {
                nextInteger = nextNestedInteger.getInteger();
                nextIndex++;
            } else {
                // 严谨起见此处应该先调用hasNext()函数，防止外部只调用next()方法使得成员变量没有得到更新
                if(hasNext()) {
                    nextInteger = nextIterator.next();
                }
            }
            return nextInteger;

        }

        @Override
        public boolean hasNext() {
            if (nextIndex >= innerList.size()) {
                return false;
            }
            NestedInteger nextNestedInteger = innerList.get(nextIndex);
            if (nextNestedInteger.isInteger()) {
                return true;
            } else {
                if (nextIterator == null) {
                    nextIterator = new NestedIterator(nextNestedInteger.getList());
                }
                boolean hasSubNext = nextIterator.hasNext();
                // 嵌套迭代器可能hasNext()为false，需要上层向后一个元素
                if (hasSubNext) {
                    return true;
                } else {
                    nextIterator = null;
                    nextIndex++;
                    return hasNext(); // 尾递归可以改造成遍历方式
                }
            }
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */

}
   