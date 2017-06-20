/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.questions;

import java.util.Arrays;
import java.util.List;

/**
 * Created by testuser on 17-4-11.
 */
public class BinaryTreeNode {
    private Integer value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(123, 23, 434, 543, 564565, 57, 6, 7, 879, 875634, 456, 56, 8, 6 - 345, 243445, 34, 54, 6456, 575);
        BinaryTreeNode node = new BinaryTreeNode();
        node.populate(numbers);
        System.out.println(node.getMaxValue());
    }

    public Integer getValue() {
        return value;
    }

    public void populate(List<Integer> numbers) {
        if (numbers != null) {
            numbers.forEach(number -> {
                if (number != null) {
                    putNode(BinaryTreeNode.this, number);
                }
            });
        }
    }

    private void putNode(BinaryTreeNode node, Integer number) {
        if (node.value == null) {
            node.value = number;
        } else if (node.value > number) {
            if (node.left == null) {
                node.left = new BinaryTreeNode();
            }
            putNode(node.left, number);
        } else {
            if (node.right == null) {
                node.right = new BinaryTreeNode();
            }
            putNode(node.right, number);
        }

    }

    public Integer getMaxValue() {
        BinaryTreeNode node = this;
        Integer value = node.value;
        while (node.right != null) {
            node = node.right;
            value = node.value;
        }
        return value;
    }
}
