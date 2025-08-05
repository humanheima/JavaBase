package com.hm.base.interview.android;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并以字符串形式返回结果。
 * <p>
 * 你不能使用任何内建的处理大整数的库（比如 BigInteger），
 * 也不能使用语言库内建的方法直接将输入的字符串转换为整数形式。 使用Java实现。给出测试代码。
 * review
 */
public class StringAddition {

    // 测试代码
    public static void main(String[] args) {
        // 测试用例
        String[][] tests = {
                {"123", "456"},      // 普通数字
                {"999", "1"},        // 有进位
                {"0", "0"},          // 零
                {"12345", "678"},    // 长度不同
                {"999999", "1"}      // 多位进位
        };

        // 预期结果
        String[] expected = {
                "579",
                "1000",
                "0",
                "13023",
                "1000000"
        };

        // 运行测试
        for (int k = 0; k < tests.length; k++) {
            String num1 = tests[k][0];
            String num2 = tests[k][1];
            String result = addStrings(num1, num2);
            String expect = expected[k];
            System.out.printf("Test %d: %s + %s = %s, Expected: %s, %s%n",
                    k + 1, num1, num2, result, expect,
                    result.equals(expect) ? "PASS" : "FAIL");
        }
    }

    public static String addStrings(String num1, String num2) {
        // 使用 StringBuilder 构建结果
        StringBuilder result = new StringBuilder();

        // 注释1处，注意从右向左遍历，记录进位
        int i = num1.length() - 1;  // num1 的索引
        int j = num2.length() - 1;  // num2 的索引
        int carry = 0;              // 进位

        // 当还有数字或进位时继续循环
        while (i >= 0 || j >= 0 || carry > 0) {
            // 注释2处，获取当前位的数字，若无则为 0
            //TODO 注意，这里的 num1.charAt(i) - '0' 是将 char 转换为 int
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            // 计算当前位和进位
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;        // 更新进位
            int currentDigit = sum % 10;  // 当前位结果

            // 注释3处，将当前位插入结果开头，后面就不用翻转了
            result.insert(0, currentDigit);

            // 移动索引
            i--;
            j--;
        }

        return result.toString();
    }

}