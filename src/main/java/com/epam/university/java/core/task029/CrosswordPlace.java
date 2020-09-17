package com.epam.university.java.core.task029;

import com.epam.university.java.core.task029.fields.Cell;
import com.epam.university.java.core.task029.fields.Field;
import com.epam.university.java.core.task029.fields.HorizontalField;
import com.epam.university.java.core.task029.fields.VerticalField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

class CrosswordPlace {
    private ArrayList<Field> verticalFields;
    private ArrayList<Field> horizontalFields;

    private CrosswordPlace(ArrayList<Field> verticalFields, ArrayList<Field> horizontalFields) {
        this.verticalFields = verticalFields;
        this.horizontalFields = horizontalFields;
    }

    static CrosswordPlace getCrossword(Collection<String> source) {
        ArrayList<Cell> cells = getCells(source);
        ArrayList<Field> horizontalField = getHorizontalFields(cells);
        ArrayList<Field> verticalField = getVerticalFields(cells);

        for (Field verticalField1 : verticalField) {
            verticalField1 = getCorrectVerticalField((VerticalField) verticalField1);
        }

        for (Field horizontalField1 : horizontalField) {
            horizontalField1 = getCorrectHorizontalField((HorizontalField) horizontalField1);
        }

        ArrayList<Cell> connectCell = findConnectsCells(horizontalField, verticalField);
        for (Cell cell : connectCell) {
            cell.setHasConnect();
        }
        return new CrosswordPlace(verticalField, horizontalField);
    }

    ArrayList<Field> getSimpleVerticalFields() {
        return verticalFields;
    }

    ArrayList<Field> getSimpleHorizontalFields() {
        return horizontalFields;
    }

    ArrayList<String> getResult() {
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Character[]> list = new ArrayList<>();
        ArrayList<Cell> cells = new ArrayList<>();
        for (Field field : getSimpleVerticalFields()) {
            cells.addAll(field.getCells());
        }

        for (Field field : getSimpleHorizontalFields()) {
            cells.addAll(field.getCells());
        }

        for (int y = 0; y < 10; y++) {
            Character[] characters = new Character[10];
            for (int x = 0; x < 10; x++) {
                characters[x] = '+';
            }
            list.add(characters);
        }

        for (Cell cell : cells) {
            int indexOfList = list.size() - cell.getY() - 1;
            int indexOfArrays = cell.getX();
            Character letta = cell.getLetta();
            if (letta == null) {
                letta = ' ';
            }
            list.get(indexOfList)[indexOfArrays] = letta;
        }

        for (Character[] characters : list) {
            String string = Arrays.toString(characters).replaceAll("[^A-Z+]", "");
            values.add(string);
        }
        return values;
    }

    private static ArrayList<Field> getHorizontalFields(ArrayList<Cell> cells) {
        HashMap<Integer, ArrayList<Cell>> map = new HashMap<>();
        for (Cell cell : cells) {
            int y = cell.getY();
            if (map.containsKey(y)) {
                ArrayList<Cell> list = map.get(y);
                list.add(cell);
                map.put(cell.getY(), list);
            } else {
                ArrayList<Cell> list = new ArrayList<>();
                list.add(cell);
                map.put(y, list);
            }
        }

        ArrayList<Field> horizontalFields = new ArrayList<>();
        for (Integer y : map.keySet()) {
            ArrayList<Cell> field = map.get(y);
            if (field.size() > 2) {
                horizontalFields.add(new HorizontalField(field));
            }
        }
        return horizontalFields;
    }

    private static ArrayList<Field> getVerticalFields(ArrayList<Cell> cells) {
        HashMap<Integer, ArrayList<Cell>> map = new HashMap<>();
        for (Cell cell : cells) {
            int x = cell.getX();
            if (map.containsKey(x)) {
                ArrayList<Cell> list = map.get(x);
                list.add(cell);
                map.put(cell.getX(), list);
            } else {
                ArrayList<Cell> list = new ArrayList<>();
                list.add(cell);
                map.put(x, list);
            }
        }

        ArrayList<Field> verticalFields = new ArrayList<>();
        for (Integer x : map.keySet()) {
            ArrayList<Cell> field = map.get(x);
            if (field.size() > 2) {
                verticalFields.add(new VerticalField(field));
            }
        }
        return verticalFields;
    }

    private static ArrayList<Cell> getCells(Collection<String> source) {
        ArrayList<Cell> cells = new ArrayList<>();

        int y = source.size() - 1; //Start of y = 0;
        for (String horizontalLine : source) {
            for (int x = 0; x < horizontalLine.length(); x++) {
                String cellValue = String.valueOf(horizontalLine.charAt(x));
                if (cellValue.equals("-")) {
                    Cell cell = new Cell(x, y);
                    cells.add(cell);
                }
            }
            y--;
        }
        return cells;
    }

    private static ArrayList<Cell> findConnectsCells(
            ArrayList<Field> horizontalFields,
            ArrayList<Field> verticalFields) {
        ArrayList<Cell> hasConnect = new ArrayList<>();
        for (Field horizontalField : horizontalFields) {
            for (Cell cell1 : horizontalField.getCells()) {
                for (Field verticalField : verticalFields) {
                    for (Cell cell2 : verticalField.getCells()) {
                        if (cell1.equals(cell2)) {
                            hasConnect.add(cell1);
                        }
                    }
                }
            }
        }
        return hasConnect;
    }

    private static Field getCorrectVerticalField(VerticalField verticalField) {
        //If 2 words of 1 lineY, this application will broken. Be careful.
        ArrayList<Cell> cells = verticalField.getCells();
        ArrayList<Cell> defectCells = new ArrayList<>();
        Cell cell1 = cells.get(cells.size() - 1);
        for (int i = cells.size() - 2; i > -1; i--) {
            Cell cell2 = cells.get(i);
            if (Math.abs(cell1.getY() - cell2.getY()) > 1) {
                defectCells.add(cell2);
            }
            cell1 = cell2;
        }
        for (Cell cell : defectCells) {
            cells.remove(cell);
        }
        return new VerticalField(cells);
    }

    private static Field getCorrectHorizontalField(HorizontalField verticalField) {
        //This method don't work. Need to rewritten as Vertical method.
        ArrayList<Cell> cells = verticalField.getCells();
        ArrayList<Cell> defectCells = new ArrayList<>();
        for (int i = 0; i < cells.size() - 1; i++) {
            if (Math.abs(cells.get(i).getX() - cells.get(++i).getX()) > 1) {
                defectCells.add(cells.get(i));
            }
        }
        for (Cell cell : defectCells) {
            cells.remove(cell);
        }
        return new VerticalField(cells);
    }
}
