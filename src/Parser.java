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
        Option input = new Option("f", "file", true, "input file path");
        input.setRequired(false);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        packets = new ArrayList<>();
        try {
            String line;
            reader = new BufferedReader(new FileReader(
                    cmd.hasOption('f')?
                            cmd.getOptionValue('f'):
                            "/var/log/iptraf-ng/ip_traffic-4122.log"
            ));
            while ((line = reader.readLine()) != null) {
                if(!line.endsWith("********")  && !line.isBlank()) {
                    packets.add(Packet.build(line));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Файл не найден");
        } catch (IOException e){
            System.out.println("Файл занят");
            //e.printStackTrace();
        }
        System.out.println(packets.size()+" пакетов");
    }
}
