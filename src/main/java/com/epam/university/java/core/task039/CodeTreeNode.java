package com.epam.university.java.core.task039;

import java.util.ArrayList;
import java.util.Collections;

public class CodeTreeNode implements Comparable<CodeTreeNode> {
    private Character character;
    private int weight;

    private CodeTreeNode left;
    private CodeTreeNode right;

    public CodeTreeNode(Character character, int weight) {
        this.character = character;
        this.weight = weight;
    }


    /**
     * Simple node of Tree.
     * <p>
     * This node has some params for connect this other node,
     * and contain character and weight.
     * Because this params need for create Tree.
     *
     * </p>
     *
     * @param character our character for this node.
     * @param weight    count replay this character in text
     * @param left      connect this left Node (code path 0)
     * @param right     connect this right Node (code path 1)
     */
    public CodeTreeNode(Character character, int weight, CodeTreeNode left, CodeTreeNode right) {
        this.character = character;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    /**
     * Huffman coding.
     * <p>
     * We used this method for save memory size.
     * Because, this method don't use full sector for character,
     * only required size. This method create tree this note,
     * and end of way is character, way is the path this 0 and 1.
     * </p>
     *
     * @param codeTreeNodes list of nodes this characters.
     * @return main node that has full connect this  others.
     */
    public static CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);

            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            if (left.getWeight() == right.getWeight() && left.getCharacter() != null
                    && right.getCharacter() != null) {
                if (left.getCharacter() > right.getCharacter()) {
                    CodeTreeNode temp = left;
                    left = right;
                    right = temp;
                }
            }

            int wight = left.getWeight() + right.getWeight();

            CodeTreeNode parent = new CodeTreeNode(null, wight, left, right);
            codeTreeNodes.add(parent);
        }
        return codeTreeNodes.get(0);
    }


    /**
     * Decoding message.
     * <p>
     * Use CodeTreeNode, we are moving of tree,
     * and find correct character of encoding string.
     * We try to find end of some way of tree.
     * </p>
     *
     * @param treeNodes main node of Huffman
     * @param encoding  text encoding message
     * @return correct text of message
     */
    public static String getDecodingString(CodeTreeNode treeNodes, String encoding) {
        StringBuilder stringBuilder = new StringBuilder();

        CodeTreeNode codeTreeNode = treeNodes;
        for (int i = 0; i < encoding.length(); i++) {
            if (encoding.charAt(i) == '0') {
                codeTreeNode = codeTreeNode.left;
            } else {
                codeTreeNode = codeTreeNode.right;
            }

            if (codeTreeNode.getCharacter() != null) {
                stringBuilder.append(codeTreeNode.getCharacter());
                codeTreeNode = treeNodes;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Character of code.
     * <p>
     * Find correct character in tree,
     * and create path for this Character this 0 and 1.
     * </p>
     *
     * @param character  that we want to find.
     * @param parentPath start path for method (default empty)
     * @return path for character in tree
     */
    public String getCodeOfCharacter(Character character, String parentPath) {
        if (character == this.character) {
            return parentPath;
        } else {
            if (left != null) {
                String path = left.getCodeOfCharacter(character, parentPath + '0');
                if (path != null) {
                    return path;
                }
            }

            if (right != null) {
                String path = right.getCodeOfCharacter(character, parentPath + '1');
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

    public Character getCharacter() {
        return character;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(CodeTreeNode o) {
        return o.weight - weight;
    }

    @Override
    public String toString() {
        return "Character: " + character + " weight: " + weight;

    }
}
