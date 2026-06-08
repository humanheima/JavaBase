# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 仓库定位

这是一个个人 Java 学习/练习仓库（不是应用，也不是库）。里面汇集了独立的示例代码、手写的数据结构/算法实现、LeetCode 题解，以及配套的中文学习笔记。**没有业务逻辑，也没有统一的运行入口**——绝大多数文件彼此独立，各自通过 `public static void main` 单独运行。

## 构建与运行

**没有 Maven/Gradle 构建**，这是一个 IntelliJ IDEA 模块（`JavaBase.iml`）。

- **语言级别 / JDK：** Java 8（`JDK_1_8`）。
- **编译输出目录：** `out/production/JavaBase`（由 IDEA 管理）。
- **依赖** 是 classpath 上的裸 jar 包（见 `lib/` 与 `.idea/libraries/`）：`gson-2.8.5`、`fastjson-1.2.56`，以及 Kotlin 标准库 jar。模块实际引用的只有 `gson` 和 `fastjson`。

大多数类都带有 `main` 方法，设计为直接运行。命令行下编译/运行单个文件：

```bash
# 编译单个文件到 out/（仅当该文件 import 了 gson/fastjson 时才需在 -cp 加入对应 jar）
javac -encoding UTF-8 -d out/production/JavaBase -cp "lib/gson-2.8.5.jar:lib/fastjson-1.2.56.jar:out/production/JavaBase" \
  src/com/hm/leetcode/code1/LeetCode1.java

# 运行（使用全限定类名，而不是文件路径）
java -cp "out/production/JavaBase:lib/gson-2.8.5.jar:lib/fastjson-1.2.56.jar" com.hm.leetcode.code1.LeetCode1
```

常规做法是在 IntelliJ 里运行（点击 `main` 旁边的 ▶ 装订线按钮）。**没有测试框架 / 没有 `mvn test` / 没有 JUnit 套件**——这里的"测试"指的是用示例 `main` 方法打印结果来验证行为，而非断言。

## 代码组织

- 所有源码位于 `src/com/hm/<主题>/`，按概念分组，例如：`thread`、`lock`、`concurrent`、`atomic_test`、`collection`、`jvm`、`classloader`、`reflect`、`io`、`nio`、`generic`、`pattern`（设计模式）、`sort`、`algorithm`、`leetcode`、`effective_java`、`java8` 等。
- `src/com/hm/leetcode/` 是最大的区域。题解按 **一题一个子包** 组织，命名为 `codeN`（如 `code1`、`code539`），另有部分扁平的 `LeetCodeNN.java` 文件。`ListNode.java` 这类公共辅助类在各区域中重复出现。
- 仓库根目录存放 **学习笔记**（`*.md`）和大量被笔记引用的图示资源（`*.png`、`*.jpg`、`*.gliffy`）——这些是文档，不是代码。`算法解题思路/` 目录存放独立的算法讲解。

## 约定

- **文件头：** 类通常以 Javadoc 块 `Crete by dumingwei on <日期>` 开头，后接题目标题、`Desc:` 段落，以及内联的 `解题思路`。新增文件时请沿用此风格。
- **包名** 与目录完全对应（`package com.hm.leetcode.codeN;`）。
- 代码与注释中的讲解使用中文，新增内容请保持一致。

## 算法 / LeetCode 工作——必须附带解题思路文档

当你 **编写、重写或优化任何算法或 LeetCode 题解** 时，`algo-solution-doc` 技能（`.claude/skills/algo-solution-doc/SKILL.md`）是强制要求：必须在 **与源文件相同的目录** 下生成一份中文「解题思路」Markdown 文档，文件名用中文（如 `两数之和解题思路.md`）。若该题文档已存在，则更新它而非新建。必须包含的小节：题目描述 / 核心观察 / 解法（含思路、关键代码、时间·空间复杂度、优缺点）/ 解法对比表（多解法时）/ 易错点总结 / 关联代码。标准样例是 `src/com/hm/leetcode/code9/回文数解题思路.md`。写文档结论前，请先确认代码可编译并输出正确结果。
