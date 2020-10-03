public class Packet {
    private String type;
    private int size;
    private String adapter;
    private String destAddress;
    private String destPort;
    private String sourceAddress;
    private String sourcePort;
    private Packet(String type){
        this.type = type;
    }

    public static Packet build(String packetStringRepresentation){
        System.out.println(packetStringRepresentation);
        String[] columns = packetStringRepresentation.split("; ");
        Packet packet = new Packet(columns[1]);
        packet.setAdapter( columns[2] );
        packet.setSize(Integer.parseInt( columns[3].replace(" bytes","") ));

        packet.sourceAddress = columns[4].split(" to ")[0].replace("from ","");
        packet.sourcePort = packet.sourceAddress.split(":")[1];

        packet.destAddress = columns[4].split(" to ")[1].replace("from ","");
        packet.destPort = packet.destAddress.split(":")[1];
        return packet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }
}
