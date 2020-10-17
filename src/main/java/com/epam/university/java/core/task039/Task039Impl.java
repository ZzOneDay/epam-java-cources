package com.epam.university.java.core.task039;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task039Impl implements Task039 {
    @Override
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies) {
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for (Character c : charFrequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, charFrequencies.get(c)));
        }
        CodeTreeNode treeNodes = CodeTreeNode.huffman(codeTreeNodes);

        HashMap<Character, String> map = new HashMap<>();
        for (Character c : charFrequencies.keySet()) {
            map.put(c, treeNodes.getCodeOfCharacter(c, ""));
        }

        return map;
    }

    @Override
    public String getEncodedString(Map<Character, Integer> charFrequencies, String string) {
        Map<Character, String> codeTable = getEncoding(charFrequencies);
        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : string.toCharArray()) {
            String code = codeTable.get(character);
            stringBuilder.append(code);
        }

        return stringBuilder.toString();
    }

    @Override
    public String getDecodedString(Map<Character, Integer> charFrequencies, String encodedString) {
        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();

        for (Character c : charFrequencies.keySet()) {
            codeTreeNodes.add(new CodeTreeNode(c, charFrequencies.get(c)));
        }

        CodeTreeNode treeNodes = CodeTreeNode.huffman(codeTreeNodes);

        return CodeTreeNode.getDecodingString(treeNodes, encodedString);
    }
}
