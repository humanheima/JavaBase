我将详细解析验证二叉搜索树（BST）的两种主要算法：中序遍历和递归边界检查，包括原理和Java实现。

### 一、中序遍历方法

#### 算法原理
- BST的定义：对于任意节点，左子树所有节点值小于当前节点，右子树所有节点值大于当前节点
- BST的中序遍历（左-根-右）会得到一个严格递增的序列
- 通过检查相邻节点值是否满足递增关系来验证BST
- 核心思想：记录前一个访问的节点，当前节点值必须大于前一个节点值

#### Java实现
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    private TreeNode prev = null;  // 记录前一个访问的节点
    
    public boolean isValidBST(TreeNode root) {
        prev = null;  // 重置prev
        return inorder(root);
    }
    
    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;  // 空树是BST
        }
        
        // 遍历左子树
        if (!inorder(root.left)) {
            return false;
        }
        
        // 检查当前节点与前一个节点
        if (prev != null && root.val <= prev.val) {
            return false;  // 必须严格大于，不能等于
        }
        prev = root;  // 更新prev
        
        // 遍历右子树
        return inorder(root.right);
    }
}
```

#### 算法解析
1. **递归过程**：
    - 先访问左子树
    - 处理当前节点（与prev比较）
    - 再访问右子树
2. **验证逻辑**：
    - prev初始为null
    - 每次访问节点时，若prev不为空，检查当前值是否大于prev值
    - 更新prev为当前节点
3. **时间复杂度**：O(n)，访问每个节点一次
4. **空间复杂度**：O(h)，递归栈深度，h为树高

#### 优点与局限
- 优点：实现简单，利用BST天然性质
- 局限：需要额外变量prev，无法直接检查上下界

---

### 二、递归边界方法

#### 算法原理
- BST的每个节点都有一个有效值范围：
    - 左子节点必须小于当前节点值
    - 右子节点必须大于当前节点值
- 通过递归传递每个节点的上下界来验证
- 核心思想：为每个节点维护一个(min, max)范围，检查节点值是否在范围内

#### Java实现
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;  // 空树是BST
        }
        
        // 检查当前节点值是否在范围内
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // 递归检查左右子树，更新边界
        return validate(node.left, min, node.val) &&  // 左子树上限为当前值
               validate(node.right, node.val, max);   // 右子树下限为当前值
    }
}
```

#### 算法解析
1. **递归过程**：
    - 检查当前节点是否在(min, max)范围内
    - 左子树：上限变为当前节点值，下限不变
    - 右子树：下限变为当前节点值，上限不变
2. **边界处理**：
    - 使用long类型避免int溢出问题
    - 初始范围为(Long.MIN_VALUE, Long.MAX_VALUE)
3. **时间复杂度**：O(n)，访问每个节点一次
4. **空间复杂度**：O(h)，递归栈深度

#### 优点与局限
- 优点：直接体现BST定义，更直观，边界检查更严格
- 局限：需要注意边界值选择，避免溢出

---

### 示例与测试
```java
public class Main {
    public static void main(String[] args) {
        Solution binaryTreeTest = new Solution();
        
        // 测试用例1：有效BST
        //     5
        //    / \
        //   3   7
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(7);
        System.out.println("Test1: " + binaryTreeTest.isValidBST(root1));  // true
        
        // 测试用例2：无效BST
        //     5
        //    / \
        //   3   4
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(4);
        System.out.println("Test2: " + binaryTreeTest.isValidBST(root2));  // false
    }
}
```

---

### 两种方法的对比

| 特性             | 中序遍历             | 递归边界            |
|------------------|---------------------|---------------------|
| 核心思想         | 利用递增序列         | 利用上下界          |
| 空间复杂度       | O(h)               | O(h)               |
| 时间复杂度       | O(n)               | O(n)               |
| 实现复杂度       | 较简单             | 稍复杂             |
| 边界处理         | 无需特别处理        | 需用long避免溢出   |
| 可读性           | 较高               | 较高               |

---

### 总结
1. **中序遍历**适合快速验证，利用BST的天然性质，代码简洁。
2. **递归边界**更贴近BST定义，适合需要严格边界检查的场景。
3. 实际应用中：
    - 如果树中可能有极端值（如Integer.MIN_VALUE），推荐递归边界方法
    - 如果追求简单实现，中序遍历是不错的选择

希望这个详细解析对您理解BST验证算法有所帮助！如果有疑问，请随时提问。