package kovteba;

public class ChessHorse {

    public static String logicHorse(String startletter, int startNumber, String endletter, int endNumber) {
        int[] line = {1, 2, 3, 4, 5, 6, 7, 8};
        String[] collom = {"a", "b", "c", "d", "e", "f", "g", "h"};

        int startIndexLetter = 0;
        int startIndexNumber = 0;
        //ищем начальный индек для буквы start
        for (int i = 0; i < collom.length; i++) {
            if (collom[i].equals(startletter)) {
                startIndexLetter = i;
            }
        }
        //ищем начальный индек для цифры start
        for (int i = 0; i < line.length; i++) {
            if (line[i] == startNumber) {
                startIndexNumber = i;
            }
        }
        int endIndexLetter = 0;
        int endIndexNumber = 0;
        //ищем начальный индек для буквы end
        for (int i = 0; i < collom.length; i++) {
            if (collom[i].equals(endletter)) {
                endIndexLetter = i;
            }
        }
        //ищем начальный индек для цифры end
        for (int i = 0; i < line.length; i++) {
            if (line[i] == endNumber) {
                endIndexNumber = i;
            }
        }

        System.out.println("Start position : " + collom[startIndexLetter] + "" + line[startIndexNumber]);
        System.out.println("End position : " + collom[endIndexLetter] + "" + line[endIndexNumber]);
        String ansver = "its not possible";
        //////////
        if (startIndexLetter + 1 == endIndexLetter && startIndexNumber - 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 1 == endIndexLetter && startIndexNumber - 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter + 1 == endIndexLetter && startIndexNumber + 2 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 1 == endIndexLetter && startIndexNumber + 2 == endIndexNumber) {
            ansver = "its posibble";
        }
        //////////////////
        if (startIndexLetter + 2 == endIndexLetter && startIndexNumber - 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 2 == endIndexLetter && startIndexNumber + 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter + 2 == endIndexLetter && startIndexNumber + 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        if (startIndexLetter - 2 == endIndexLetter && startIndexNumber - 1 == endIndexNumber) {
            ansver = "its posibble";
        }

        return ansver;
    }


}
