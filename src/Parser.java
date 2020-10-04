import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    static BufferedReader reader;
    static List<Packet> packets;
    static CommandLine cmd;

    public static void main(String[] args) {

        Options options = new Options();

        Option lookupPacketType = new Option("p", "packet", true, "Packet type to scan for");
        lookupPacketType.setRequired(false);
        options.addOption(lookupPacketType);

        Option inputFile = new Option("f", "file", true, "input file path");
        inputFile.setRequired(false);
        options.addOption(inputFile);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            Console.writeLine("парсинг параметров не удался");
            Console.writeLine(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        packets = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(
                    cmd.hasOption('f') ?
                            cmd.getOptionValue('f') :
                            "/var/log/iptraf-ng/ip_traffic-4122.log"
            ));
            packets = retreiveData(reader);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            Console.writeLine("Файл не найден", ConsoleColor.RED_BOLD);
            System.exit(1);
        }
        if (cmd.hasOption("p")) {
            packageStatistics(cmd.getOptionValue('p'));
        } else {
            for (String pck : new String[]{"TCP", "UDP", "ARP", "ICMP"}) {
                packageStatistics(pck);
            }
        }
        totalStat();
    }

    public static int getPacketsCount(String packetType) {
        int count = 0;
        for (Packet packet : packets) {
            if (packet.getType().equals(packetType.toUpperCase())) {
                count++;
            }
        }
        return count;
    }

    public static void packageStatistics(String pck) {
        Console.write("< " + pck + " > ", ConsoleColor.BLUE_BOLD);
        Console.writeLine(getPacketsCount(pck) + " пакетов", ConsoleColor.BLUE_BRIGHT);
    }

    public static void totalStat() {
        Console.write("Всего: ", ConsoleColor.CYAN_BOLD);
        Console.writeLine(packets.size() + " пакетов", ConsoleColor.BLUE_BRIGHT);
    }

    public static List<Packet> retreiveData(BufferedReader reader) {
        String line;
        List<Packet> packets = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                if (!line.endsWith("********") && !line.isEmpty()) {
                    packets.add(Packet.build(line));
                }
            }
        } catch (IOException e) {
            Console.writeLine("Файл занят", ConsoleColor.RED_BOLD);
            System.exit(1);
            //e.printStackTrace();
        }
        return packets;
    }
}
