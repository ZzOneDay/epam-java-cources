package com.epam.university.java.core.task043;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class CodeMorse {
    HashMap<String, String> stringOfCode;
    HashMap<String, String> codeOfString;


    public CodeMorse(HashMap<String, String> stringOfCode,
                     HashMap<String, String> codeOfString) {
        this.stringOfCode = stringOfCode;
        this.codeOfString = codeOfString;
    }

    static CodeMorse getCodeMorse() {
        HashMap<String, String> stringOfCode = new HashMap<>();
        HashMap<String, String> codeOfString = new HashMap<>();
        try {
            File file = new File(CodeMorse.class.getResource("/Task043/code.txt").toURI());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] strings = scanner.nextLine().split(";");
                codeOfString.put(strings[0], strings[1]);
                stringOfCode.put(strings[1], strings[0]);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("code file didn't find");
        }
        return new CodeMorse(stringOfCode, codeOfString);
    }

    public String getCharOfCode(String code) {
        return stringOfCode.get(code);
    }

    public String getCodeOfChar(String string) {
        return codeOfString.get(string);
    }
}
