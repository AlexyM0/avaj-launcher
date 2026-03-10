package simulator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Logger {
    private static PrintWriter writer;

    public static void open() throws IOException {
        writer = new PrintWriter(new FileWriter("simulation.txt"));
    }

    public static void write(String message) {
        writer.println(message);
    }

    public static void close() {
        writer.close();
    }
}
