package org.cloudbus.cloudsim.network.datacenter;
import java.util.HashMap;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.core.CloudSim;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author amyr
 */
public class ClientServerICMP  extends AppCloudlet {
    ////////////////////////////////////////////////////////////
    private List<ICMPPacketInfo> icmpPacketInfo;  // ICMP Packet Info List
    long client;                                  // ID of client machine
    long server;
    long numpacket = 100;                         // Number of Packet for sending to server
    long packetsize = 400;                        // Size of each packet
    ///////////////////////////// CLASS METHODS ////////////////////////////////
    public ClientServerICMP (int Type, int AppID, double Deadline, int numbervm, int userId) {
        //int Type = Type.toString();
        //double dd = Integer.decode(Deadline);
        super(Type, AppID, Deadline, numbervm, userId);
        exeTime = 100;
	this.numbervm = 2;
        //this.numbervm = 4;
        icmpPacketInfo = new ArrayList<>();
    }
    public long calculatePacketSize(int header, int payload) {
        // Calculating ICMP packet size
        int payloadSize = 200; // 200 bytes *nrmlmn 500 bytes
        int headerSize = 50;   // 50 bytes
        return payloadSize + headerSize;
    }

    public void recordICMPPacketInfo(int sourceVMId,
            int destinationVMId,
            String stage,
            double startTime,
            double endTime,
            long packetSize) {
        icmpPacketInfo.add(new ICMPPacketInfo(sourceVMId,
                destinationVMId,
                stage,
                startTime,
                endTime,
                packetSize));
    }

    public List<ICMPPacketInfo> getIcmpPacketInfo() {
        return icmpPacketInfo;
    }

    /////////////////////////////////////////////////////////////

    public void CloadlistGenerating(List<Integer> vmIdList) {
        // SET VMs IDs
        int vm_client = vmIdList.get(0);
        int vm_server = vmIdList.get(1);
        // Output and Input SIZE (bytes)
        long FileSize_bytes = NetworkConstants.FILE_SIZE;
        long OutputSize_bytes = NetworkConstants.OUTPUT_SIZE;
        // Memory MB
        int memory = 200;
        //UtilizationModel utilizationModel1 = new UtilizationModelFull();
        UtilizationModel utilizationModel = new UtilizationModelFull();

        /// CLIENT TASKS
        NetworkCloudlet Client_Cloudlet = new NetworkCloudlet(
                NetworkConstants.currentCloudletId,
                0, // 1,
                1,
                FileSize_bytes,
                OutputSize_bytes,
                memory,
                utilizationModel,
                utilizationModel,
                utilizationModel);

        Client_Cloudlet.setVmId(vm_client);

        Client_Cloudlet.submittime = CloudSim.clock();

        Client_Cloudlet.numStage = 1 + 2 * numpacket;
        //Client_Cloudlet.numStage = 2 * numpacket;

        NetworkConstants.currentCloudletId = NetworkConstants.currentCloudletId + 1;

        Client_Cloudlet.setUserId(userId);

        // Task stages definition
        Client_Cloudlet.stages.add(new TaskStage( //// Execution stage of this Cloudlet
                NetworkConstants.EXECUTION,
                0,
                1000 * 0.8, // Bytes
                0,
                memory,
                vm_client,
                Client_Cloudlet.getCloudletId()));

        // STAGES OF ,
        // VM1 ------------PING------------> VM2
        double startTime = CloudSim.clock(); // Get start time
        for (int i = 1; i <= numpacket; i++) {
           Client_Cloudlet.stages.add(new TaskStage(
                   NetworkConstants.WAIT_SEND,
                   PacketType.ICMP_REQUEST,
                   0.0,
                   i,
                   memory,
                   vm_server,
                   Client_Cloudlet.getCloudletId() + 1)); // Wahd l RECEV
            double endTime = CloudSim.clock(); // Get end time
            //long packetSize = calculatePacketSize(); // Calculate packet size
            //recordICMPPacketInfo(vm_client, vm_server, "ICMP_REQUEST_SENT", startTime, endTime, packetSize);
        }

        // Cloadlets waiting for reply
        // VM1                      (WAIT) VM2
        for(int i = (int) (numpacket+1); i <= 2 * numpacket; i++) {
            Client_Cloudlet.stages.add(new TaskStage(
                    NetworkConstants.WAIT_RECV,
                    PacketType.ICMP_REPLY,
                    0,
                    i,
                    memory,
                    vm_server,
                    Client_Cloudlet.getCloudletId() + 1));
            //recordICMPPacketStage("ICMP_REPLY_RECEIVED_AT_VM_FROM_ANOTHER_VM");
        }
        // Add Cloadlet of client
        clist.add(Client_Cloudlet);

        /////////////////////////////////////////////////////////////////
        /// Setting SERVER TASK
        NetworkCloudlet Server_Cloudlet = new NetworkCloudlet(
                NetworkConstants.currentCloudletId,
                0, //1,
                1,
                FileSize_bytes,
                OutputSize_bytes,
                memory,
                utilizationModel,
                utilizationModel,
                utilizationModel);

        Server_Cloudlet.numStage = 2 * numpacket + 1;
        //Server_Cloudlet.currStagenum = -1;
        //Server_Cloudlet.setVmId(1);
        //Server_Cloudlet.setVmId(2);
        //Server_Cloudlet.setVmId(3);
        //Server_Cloudlet.setVmId(4);
        Server_Cloudlet.setVmId(vm_server);    // Dama vm nmachiw TASK?
        NetworkConstants.currentCloudletId++;
        Server_Cloudlet.submittime = CloudSim.clock();
        Server_Cloudlet.setUserId(userId);

        // STAGE1: COMPUTE
        for (int i = 0; i < numpacket; i++) {
            Server_Cloudlet.stages.add(new TaskStage(
                    NetworkConstants.WAIT_RECV,
                    PacketType.ICMP_REQUEST,
                    0,
                    i,
                    memory,
                    vm_client,
                    Client_Cloudlet.getCloudletId()));
             }

        Server_Cloudlet.stages.add(new TaskStage(
                NetworkConstants.EXECUTION,
                0, 1000 * 0.8,
                numpacket,
                memory,
                vm_server,
                Server_Cloudlet.getCloudletId()));

        //STAGE2: REPLY from SERVER(VM_i)
        for (int k = (int) (numpacket + 1); k <= 2 * numpacket; k++) {
            Server_Cloudlet.stages.add(new TaskStage(
                    NetworkConstants.WAIT_SEND,
                    PacketType.ICMP_REPLY,
                    0,
                    k,
                    memory,
                    vm_client,
                    Client_Cloudlet.getCloudletId()));
                }
        clist.add(Server_Cloudlet);
    }

    public List<NetworkCloudlet> getGeneratedCloudlets() {
        return clist;
    }

    class ICMPPacketInfo {
    private int sourceVMId;
    private int destinationVMId;
    private String stage;
    private double startTime;
    private double endTime;
    private long packetSize;

    public ICMPPacketInfo(int sourceVMId, int destinationVMId, String stage, double startTime, double endTime, long packetSize) {
        this.sourceVMId = sourceVMId;
        this.destinationVMId = destinationVMId;
        this.stage = stage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.packetSize = packetSize;
    }
    }
}
