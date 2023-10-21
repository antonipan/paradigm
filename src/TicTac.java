package core2;

import java.util.Random;
import java.util.Scanner;

public class TicTac {
    public static final char MOVE_HUMAN = 'x';
    public static final char MOVE_ROBOT = '0';
    public static final char EMPTY_FIELD = '-';
    public static char[][] gamefield;
    public static Scanner scanner;
    /**
     * Длина строки
     */
    public static int fieldLength_X;
    /**
     * Количество строк
     */
    public static int fieldLength_Y;
    public static int combinationVim;
    public static Random random = new Random();


    public static void main(String[] args) {
        initGame();
        while (true) {
            printGameField();
            moveHuman();
            if (isVictory(MOVE_HUMAN)) {
                break;
            }
            ;
            printGameField();
            moveRobot();
            if (isVictory(MOVE_ROBOT)) {
                break;
            }
        }
    }

    /**
     * Метод инициализирует игровое поле. Пользователь должен ввести размер игрового поля.
     */
    public static void initGame() {
        scanner = new Scanner(System.in);
        System.out.println("Введите размеры игрового поля.");
        System.out.print("Длина строки: ");
        fieldLength_X = scanner.nextInt();
        System.out.print("Количество строк: ");
        fieldLength_Y = scanner.nextInt();
        do {
            System.out.print("Из скольки ячеек будет состоять победная комбинация? ");
            combinationVim = scanner.nextInt();
        } while (!isCombination(combinationVim));
        System.out.println("------------");
        System.out.println("Игровое поле размера " + fieldLength_X + "х" + fieldLength_Y + ", выиграшная комбинация = " + combinationVim);
        gamefield = new char[fieldLength_Y][fieldLength_X];
        for (int i = 0; i < fieldLength_Y; i++) {
            for (int j = 0; j < fieldLength_X; j++) {
                gamefield[i][j] = EMPTY_FIELD;
            }
        }
    }

    /**
     * Метод распечатывает игровое поле, с учётом хода игроков
     */
    public static void printGameField() {
        System.out.println("-------");
        System.out.print("=");
        for (int i = 0; i < fieldLength_X; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < fieldLength_Y; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < fieldLength_X; j++) {
                System.out.print("|" + gamefield[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    /**
     * Метод хода Игрока.
     */
    public static void moveHuman() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты ячейки: ");
            System.out.print("po X");
            x = scanner.nextInt() - 1;
            System.out.print("po Y");
            y = scanner.nextInt() - 1;
        } while (!isOutsideEnter(x, y) || !isBusyField(x, y));

        gamefield[y][x] = MOVE_HUMAN;
    }

    private static void moveRobot() {
        int x1 = 0;
        int y1 = 0;
        do {
            if (isHorizonCheck()) {
                int count = 0;
                for (int y = 0; y < fieldLength_Y; y++) {
                    for (int x = 0; x < fieldLength_X; x++) {
                        if (gamefield[y][x] == MOVE_HUMAN) {
                            count++;
                            if (count == combinationVim - 1) {
                                if (x + 1 < fieldLength_X) {
                                    for (int i = x + 1; i >= 0; i--) {
                                        if (gamefield[y][i] == EMPTY_FIELD) {
                                            System.out.println("Здесь " + y + " " + i);
                                            x1 = i;
                                            y1 = y;
                                            System.out.println("Здесь " + y + " " + i);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int i = x; i >= 0; i--) {
                                        if (gamefield[y][i] == EMPTY_FIELD) {
                                            System.out.println("Здесь " + y + " " + i);
                                            x1 = i;
                                            y1 = y;
                                            System.out.println("Здесь " + y + " " + i);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            count = 0;
                        }
                    }
                    count = 0;
                }
            } else {
                x1 = random.nextInt(fieldLength_X);
                y1 = random.nextInt(fieldLength_Y);
            }
        } while (!isOutsideEnter(x1, y1) || !isBusyField(x1, y1));
        gamefield[y1][x1] = MOVE_ROBOT;
    }

    /**
     * @param x - координата по Х
     * @param y - координата по У
     * @return - true, если пользователь ввёл координаты не выходящие за пределы игрового поля.
     */
    private static boolean isOutsideEnter(int x, int y) {
        if (x >= 0 && x < fieldLength_X && y >= 0 && y < fieldLength_Y) {
            return true;
        }
        System.out.println("Введены некорректные данные. Попробуйте снова. ");
        return false;
    }

    private static boolean isBusyField(int x, int y) {
        if (gamefield[y][x] == EMPTY_FIELD) {
            return true;
        } else {
            System.out.println("Ячейка занята! ");
            return false;
        }
    }

    public static boolean isCombination(int x) {
        int min;
        if (fieldLength_X > fieldLength_Y) {
            min = fieldLength_Y;
        } else {
            min = fieldLength_X;
        }
        if (x > 2 && x <= min) {
            return true;
        } else {
            System.out.println("Значение должно быть от 3 до " + min);
            return false;
        }
    }

    /**
     * Интегральный метод, использующий все возможные проверки победных комбинаций.
     *
     * @param movePlayer - переменная, которая обозначает ход игроа.
     * @return - истину, если есть победная комбинация; ложь - если нет.
     */
    public static boolean isVictory(char movePlayer) {
        if (isHorizon(movePlayer) || isVertical(movePlayer) ||
                isDiagonalRightUp(movePlayer) || isDiagonalRightDown(movePlayer)) {
            System.out.println(gameOver(movePlayer));
            return true;
        }
        return false;
    }

    /**
     * Метод подставляет строку о победе в зависимости от того, кто совершает ход
     *
     * @param movePlayer - ход игрока или компьютера.
     * @return - возращает строку о победе.
     */
    private static String gameOver(char movePlayer) {
        if (movePlayer == MOVE_HUMAN) {
            printGameField();
            return "Поздравляю, вы победили. ";
        } else {
            printGameField();
            return "В этот раз победил компьютер. ";
        }
    }

    /**
     * Метод проверяет выигрышные комбинации по горизонтали.
     *
     * @param movePlayer
     * @return
     */
    private static boolean isHorizon(char movePlayer) {
        int count = 0;
        for (int y = 0; y < fieldLength_Y; y++) {
            for (int x = 0; x < fieldLength_X; x++) {
                if (gamefield[y][x] == movePlayer) {
                    count++;
                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    /**
     * Метод проверяет выигрышные комбинации по вертилкалит.
     *
     * @param movePlayer
     * @return
     */
    private static boolean isVertical(char movePlayer) {
        int count = 0;
        for (int x = 0; x < fieldLength_X; x++) {
            for (int y = 0; y < fieldLength_Y; y++) {
                if (gamefield[y][x] == movePlayer) {
                    count++;
                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }

    /**
     * Метод проверяет выигрышные комбинации по диагноали - лево-верх - право-низ.
     *
     * @param movePlayer
     * @return
     */
    public static boolean isDiagonalRightUp(char movePlayer) {
        int countX;
        int countY;
        int count = 0;
        for (int i = fieldLength_Y - 1; i >= 0; i--) {
            countX = 0;
            countY = i;
            while (countY < fieldLength_Y && countX < fieldLength_X) {
                if (gamefield[countY][countX] == movePlayer) {
                    count++;
                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
                countX++;
                countY++;
            }
            count = 0;
        }
        for (int i = 1; i < fieldLength_X; i++) {
            count = 0;
            countY = 0;
            countX = i;
            while (countY < fieldLength_Y && countX < fieldLength_X) {
                if (gamefield[countY][countX] == movePlayer) {
                    count++;
                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
                countX++;
                countY++;
            }
        }
        return false;
    }

    /**
     * Метод проверяет выигрышные комбинации по диагноали - лево-низ - право-верх.
     *
     * @param movePlayer
     * @return
     */
    public static boolean isDiagonalRightDown(char movePlayer) {
        int countX;
        int countY;
        int count = 0;
        for (int i = 0; i < fieldLength_X; i++) {
            countX = i;
            countY = 0;
            while (countY < fieldLength_Y && countX >= 0) {
                if (gamefield[countY][countX] == movePlayer) {
                    count++;

                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
                countX--;
                countY++;
            }
        }
        for (int i = 1; i < fieldLength_Y; i++) {
            count = 0;
            countY = 1;
            countX = fieldLength_X - 1;
            while (countY < fieldLength_Y && countX >= 0) {
                if (gamefield[countY][countX] == movePlayer) {
                    count++;

                    if (count == combinationVim) {
                        return true;
                    }
                } else {
                    count = 0;
                }
                countX--;
                countY++;
            }
        }
        return false;
    }

    private static boolean isHorizonCheck() {
        int count = 0;
        int countEmpty = 0;
        for (int y = 0; y < fieldLength_Y; y++) {
            // Это цикл проверяет являются ли ячейки по горизонтали в строке - пустыми.
            for (int i = 0; i < fieldLength_X; i++) {
                if (gamefield[y][i] != EMPTY_FIELD) {
                    countEmpty++;
                }
            }
            if (countEmpty == fieldLength_X) {
                return false;
            }
            for (int x = 0; x < fieldLength_X; x++) {
                if (gamefield[y][x] == MOVE_HUMAN) {
                    count++;
                    if (count == combinationVim - 1) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
            count = 0;
        }
        return false;
    }
}