public class Console {
    static void writeLine(String string) {
        System.out.println(string);
    }

    static void writeLine(Integer string) {
        System.out.println(string);
    }

    static void write(String string, String color) {
        write(color);
        write(string);
        write(ConsoleColor.RESET);
    }

    static void write(Integer string, String color) {
        write(color);
        write(string);
        write(ConsoleColor.RESET);
    }

    static void write(String string) {
        System.out.print(string);
    }

    static void write(Integer string) {
        System.out.print(string);
    }

    static void writeLine() {
        System.out.println();
    }

    static void writeLine(String string, String color) {
        System.out.print(color);
        writeLine(string);
        System.out.print(ConsoleColor.RESET);
    }

    static void writeLine(Integer string, String color) {
        System.out.print(color);
        writeLine(string);
        System.out.print(ConsoleColor.RESET);
    }


}
