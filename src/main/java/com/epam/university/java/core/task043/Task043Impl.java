package com.epam.university.java.core.task043;

public class Task043Impl implements Task043 {

    @Override
    public String encode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        CodeMorse codeMorse = CodeMorse.getCodeMorse();
        StringBuilder code = new StringBuilder();
        String[] words = sourceString.split(" ");
        for (String word : words) {
            String[] characters = word.split("");
            for (String s : characters) {
                code.append(codeMorse.getCodeOfChar(s));
                code.append(" ");
            }
            code.append("   ");
        }
        return code.toString().replaceAll("    ", "   ").trim();
    }


    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        CodeMorse codeMorse = CodeMorse.getCodeMorse();
        StringBuilder text = new StringBuilder();
        String[] words = sourceString.split("   ");
        for (String word : words) {
            String[] characters = word.split(" ");
            for (String s : characters) {
                text.append(codeMorse.getCharOfCode(s));
            }
            text.append(" ");
        }
        return text.toString().trim();
    }
}
