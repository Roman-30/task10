package ru.vsu.cs.task10.console;

import utils.ArrayUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Double> coordinates = readFileAndSetList();
        ArrayPoints points = new ArrayPoints(setCoordinates(coordinates).getX(), setCoordinates(coordinates).getY());
        CoordinateAndSquare coordinateAndSquare = new CoordinateAndSquare(
                calculateSquare(points).getFirstPoint(),
                calculateSquare(points).getSecondPoint(),
                calculateSquare(points).getThirdPoint(),
                calculateSquare(points).getSquare());
        writeFile(coordinateAndSquare);
        printAnswer(coordinateAndSquare);
    }

    public static CoordinateAndSquare calculateSquare(ArrayPoints point) {
        double[] first = new double[2];
        double[] second = new double[2];
        double[] third = new double[2];
        double maxSquare = 0;
        for (int i = 0; i < point.getX().length - 2; i++) {
            for (int j = 0; j < point.getX().length - 1; j++) {
                for (int k = 0; k < point.getX().length; k++) {
                    double square = Math.abs(((point.getX()[i] - point.getX()[k]) * (point.getY()[j] - point.getY()[k])
                            - (point.getX()[j] - point.getX()[k]) * (point.getY()[i] - point.getY()[k])) / 2);
                    if (square > maxSquare) {
                        maxSquare = square;
                        first[0] = point.getX()[i];
                        first[1] = point.getY()[i];
                        second[0] = point.getX()[j];
                        second[1] = point.getY()[j];
                        third[0] = point.getX()[k];
                        third[1] = point.getY()[k];
                    }
                }
            }
        }
        return new CoordinateAndSquare(new Point(first[0], first[1]), new Point(second[0], second[1]), new Point(third[0], third[1]), maxSquare);
    }

    public static List<Double> readFileAndSetList() throws Exception {
        int counter = 0;
        List<Double> list = new ArrayList<>();
        double[] coordinates;
        FileReader reader = new FileReader(readSomething("Enter file name: "));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String numbers = scanner.nextLine();
            if (numbers.equals("")) break;
            coordinates = ArrayUtils.toDoubleArray(numbers);
            if (!verifyTheLine(coordinates)) System.exit(1);
            for (double coordinate : coordinates) {
                list.add(counter, coordinate);
                counter++;
            }
        }
        reader.close();
        return list;
    }

    public static boolean verifyTheLine(double[] arr) {
        if (arr.length == 2) {
            return true;
        } else {
            System.out.println("Incorrectly entered coordinates!");
            return false;
        }
    }

    public static ArrayPoints setCoordinates(List<Double> list) {
        int n = 0;
        double[] coordinateX = new double[list.size() / 2];
        double[] coordinateY = new double[list.size() / 2];
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                coordinateX[n] = list.get(i);
            } else {
                coordinateY[n] = list.get(i);
                n++;
            }
        }
        return new ArrayPoints(coordinateX, coordinateY);
    }

    public static void writeFile(CoordinateAndSquare coordinateAndSquare) throws Exception {
        FileWriter writer = new FileWriter("answer");
        writer.write("Maximum area = " + coordinateAndSquare.getSquare() + " cm^2\n");
        writer.write("For coordinates:\nA (" + coordinateAndSquare.getFirstPoint().getX() + ": " + coordinateAndSquare.getFirstPoint().getY() + ");" +
                "\nB (" + coordinateAndSquare.getSecondPoint().getX() + ": " + coordinateAndSquare.getSecondPoint().getY() + ");" +
                "\nC (" + coordinateAndSquare.getThirdPoint().getX() + ": " + coordinateAndSquare.getThirdPoint().getY() + ");");
        writer.close();
    }

    public static void printAnswer(CoordinateAndSquare coordinateAndSquare) {
        System.out.printf("Maximum area = %s cm^2\nFor coordinates:\nA (%s: %s);\nB (%s: %s);\nC (%s: %s);\n ",
                coordinateAndSquare.getSquare(),
                coordinateAndSquare.getFirstPoint().getX(),
                coordinateAndSquare.getFirstPoint().getY(),
                coordinateAndSquare.getSecondPoint().getX(),
                coordinateAndSquare.getSecondPoint().getY(),
                coordinateAndSquare.getThirdPoint().getX(),
                coordinateAndSquare.getThirdPoint().getY());
    }

    public static String readSomething(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(name);
        return scanner.nextLine();
    }
}
