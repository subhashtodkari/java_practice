package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.

Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression 4*(5-(7+2)) are represented in the array postfix = ["4","5","7","2","+","-","*"].

The class Node is an interface you should use to implement the binary expression tree. The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.

A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations are valid (i.e., no division by zero).

Follow up: Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing evaluate implementation?



Example 1:


Input: s = ["3","4","+","2","*","7","/"]
Output: 2
Explanation: this expression evaluates to the above binary tree with expression ((3+4)*2)/7) = 14/7 = 2.
Example 2:


Input: s = ["4","5","2","7","+","-","*"]
Output: -16
Explanation: this expression evaluates to the above binary tree with expression 4*(5-(2+7)) = 4*(-4) = -16.


Constraints:

1 <= s.length < 100
s.length is odd.
s consists of numbers and the characters '+', '-', '*', and '/'.
If s[i] is a number, its integer representation is no more than 105.
It is guaranteed that s is a valid expression.
The absolute value of the result and intermediate values will not exceed 109.
It is guaranteed that no expression will include division by zero.
 */
public class DesignAnExpressionTreeWithEvaluateFunction_1628 {

    public static void main(String[] args) {
        TreeBuilder treeBuilder = new TreeBuilder();
        Node node = treeBuilder.buildTree(new String [] {"3","4","+","2","*","7","/"});
        Assertions.assertEquals(2, node.evaluate());

        treeBuilder = new TreeBuilder();
        node = treeBuilder.buildTree(new String [] {"4","5","2","7","+","-","*"});
        Assertions.assertEquals(-16, node.evaluate());

        // % operator not registered yet
        Assertions.assertThrows(RuntimeException.class, () -> new TreeBuilder().buildTree(new String [] {"30","4","%"}).evaluate());

        OperatorEvaluatorFactory.register('%', nodes -> {
            assert nodes.length == 2;
            return nodes[0].evaluate() % nodes[1].evaluate();
        });
        treeBuilder = new TreeBuilder();
        node = treeBuilder.buildTree(new String [] {"30","4","%"});
        Assertions.assertEquals(2, node.evaluate());

    }

}


abstract class Node {

    public abstract int evaluate();
    // define your fields here
};


class Operand extends Node {

    Integer val;

    public Operand(int v) {
        val = v;
    }

    @Override
    public int evaluate() {
        return val;
    }
}


class Operator extends Node {

    char op;
    Node left, right;

    public Operator(char o) {
        op = o;
    }

    @Override
    public int evaluate() {
        return OperatorEvaluatorFactory.get(op).evaluate(left, right);
    }
}

interface OperatorEvaluator {
    int evaluate(Node ...nodes);
}

class PlusEvaluator implements OperatorEvaluator {
    public int evaluate(Node ...nodes) {
        assert nodes.length == 2;
        return nodes[0].evaluate() + nodes[1].evaluate();
    }
}

class MinusEvaluator implements OperatorEvaluator {
    public int evaluate(Node ...nodes) {
        assert nodes.length == 2;
        return nodes[0].evaluate() - nodes[1].evaluate();
    }
}

class MultiplyEvaluator implements OperatorEvaluator {
    public int evaluate(Node ...nodes) {
        assert nodes.length == 2;
        return nodes[0].evaluate() * nodes[1].evaluate();
    }
}

class DivisionEvaluator implements OperatorEvaluator {
    public int evaluate(Node ...nodes) {
        assert nodes.length == 2;
        return nodes[0].evaluate() / nodes[1].evaluate();
    }
}

class OperatorEvaluatorFactory {

    private static final Map<Character, OperatorEvaluator> map = new HashMap<>();

    static void register(char op, OperatorEvaluator evaluator) {
        map.put(op, evaluator);
    }

    static OperatorEvaluator get(char op) {
        if(map.containsKey(op))
            return map.get(op);
        throw new RuntimeException("Operation '"+op+"' is not supported yet");
    }

}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {

    static {
        //build context
        OperatorEvaluatorFactory.register('+', new PlusEvaluator());
        OperatorEvaluatorFactory.register('-', new MinusEvaluator());
        OperatorEvaluatorFactory.register('*', new MultiplyEvaluator());
        OperatorEvaluatorFactory.register('/', new DivisionEvaluator());

        //register more operators as you develop over the time
    }

    int idx = Integer.MAX_VALUE;

    Node buildTree(String[] postfix) {
        idx = idx == Integer.MAX_VALUE ? postfix.length-1 : idx;

        if(Character.isDigit(postfix[idx].charAt(0))) {
            return new Operand(Integer.parseInt(postfix[idx]));
        }
        Operator o = new Operator(postfix[idx].charAt(0));
        idx--;
        o.right = buildTree(postfix);
        idx--;
        o.left = buildTree(postfix);
        return o;
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */