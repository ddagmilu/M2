/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cloudsim_tp02;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.cloudbus.cloudsim.network.datacenter.ClientServerICMP;
import org.cloudbus.cloudsim.network.datacenter.NetworkCloudlet;
import org.cloudbus.cloudsim.network.datacenter.NetworkDatacenter;
import org.cloudbus.cloudsim.network.datacenter.NetworkVmAllocationPolicy;

/**
 *
 * @author amyr
 */
public class MainSimulation {
    public static void main(String[] args) {

        List<Host> HostsList = new ArrayList<>();
        List<Pe> PE_list = new ArrayList<>();

        int NumberOfUsers = 1;
        int NumberOfVms = 2;
        //int NumberOfUsers = 2;
        //int NumberOfUsers = 3;
        boolean TraceFlag = false;
        CloudSim.init(NumberOfUsers, Calendar.getInstance(), TraceFlag);

        //////// Creating Datacenter
        int MIPS = 2000;
        int HostID = 0;
        int RAM_bytes = 2048;
        long storage = 1000000;
        int bw = 10000;

        ///////// Add a New Host
        Host My_Host = new Host(
                HostID,
                new RamProvisionerSimple(RAM_bytes),
                new BwProvisionerSimple(bw),
                storage,
                PE_list,
                new VmSchedulerSpaceShared(PE_list)
        );

        PE_list.add(new Pe(0, new PeProvisionerSimple(MIPS)));
        HostsList.add(My_Host);
        System.out.println("Creating Datacenter...");
        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                "x86",
                "Linux",
                "Xen",
                HostsList,
                00.00,
                1.0,
                5.0, // AVG 5
                2.0, // AVG 0.75
                1.0
        );

        NetworkDatacenter datacenter = null;
        try {
            datacenter = new NetworkDatacenter(
                    "Datacenter",
                    characteristics,
                    new NetworkVmAllocationPolicy(HostsList),
                    new ArrayList<>(),
                    0
            );
        } catch (Exception e) { e.printStackTrace(); }


        // Creating VMs
        System.out.println("Creating VMs...");
        List<Integer> vmIdList = new ArrayList<>();
        int vmid = 0;           //vm id
        //int MIPS = 250;       //number of operations
        long size = 10000;      //image size (MB)
        //int RAM_bytes = 512;  //vm memory (MB)
        //int MIPS = 250;       //number of operations
        //long bw = 1000;       //vm bandwidth
        int pesNumber = 1;      //number of cpus
        String vmm = "Xen";     //VMM name


        DatacenterBroker broker = createBroker();
	//int brokerId = broker.getId();
        int brokerId = 0;
        System.out.println("Broker ID :"+brokerId);


        ////////////////////////////////////////////
        Vm vm1 = new Vm(vmid, brokerId, MIPS, pesNumber, RAM_bytes, bw, size, vmm, new CloudletSchedulerTimeShared());
        Vm vm2 = new Vm(vmid, brokerId, MIPS, pesNumber, RAM_bytes, bw, size, vmm, new CloudletSchedulerTimeShared());
        //Vm vm3 = new Vm(vmid, brokerId, 250, 2, 8000, bw, size, vmm, new CloudletSchedulerTimeShared());
        for (int i = 0; i < NumberOfVms; i++) {
            vmIdList.add(i);
            vmIdList.add(i);
        }
        //vmIdList.add(0); // id
        //vmIdList.add(1);


        ClientServerICMP simulation = new ClientServerICMP(0, 1, 100.0, 2, brokerId);
        simulation.CloadlistGenerating(vmIdList);

        // Starting....
        System.out.println("Starting the Simulation...");
        CloudSim.startSimulation();

        List<NetworkCloudlet> cloudletList = simulation.getGeneratedCloudlets();
        broker.submitCloudletList(cloudletList);

        broker.bindCloudletToVm(cloudletList.get(0).getCloudletId(),vm1.getId());
        broker.bindCloudletToVm(cloudletList.get(1).getCloudletId(),vm2.getId());
        // Retrieve the results or perform any post-simulation analysis here if needed

                /*
        // Retrieve the list of generated cloudlets from the simulation
        List<NetworkCloudlet> generatedCloudlets = simulation.getGeneratedCloudlets();

        // Iterate through the cloudlets to analyze packet transmission stages
        for (NetworkCloudlet cloudlet : generatedCloudlets) {
            // Iterate through stages of each cloudlet
            for (TaskStage stage : cloudlet.stages) {
                if (stage. == NetworkConstants.WAIT_SEND && stage.getPacketType() == PacketType.ICMP_REQUEST) {
                    // Found an ICMP send stage
                    // Identify the sending VM and receiving VM (if needed) and other details
                    int sendingVMId = stage.getVmId();
                    int receivingVMId = stage.getDestVmId();
                    System.out.println("ICMP request sent from VM #" + sendingVMId + " to VM #" + receivingVMId);
                } else if (stage.getType() == NetworkConstants.WAIT_RECV && stage.getPacketType() == PacketType.ICMP_REPLY) {
                    // Found an ICMP receive stage
                    // Identify the receiving VM and sending VM (if needed) and other details
                    int receivingVMId = stage.getVmId();
                    int sendingVMId = stage.getSrcVmId();
                    System.out.println("ICMP reply received at VM #" + receivingVMId + " from VM #" + sendingVMId);
                }
                // Add more conditions or details as needed for comprehensive analysis
            }
        }
        */

        // After CloudSim simulation completes
        // Retrieve the ICMP packet stages recorded during the simulation
        //List<String> icmpStages = simulation.getIcmpPacketStages();

        // Analyze or print the recorded ICMP stages
        //System.out.println(icmpStages.length());
        /*
        for (String stage : icmpStages) {
            System.out.println("ICMP Stage: " + stage.length());
            // Perform analysis or metrics calculation based on these stages
        }
        */
        /*
        // Retrieve the detailed ICMP packet information recorded during the simulation
        List<ICMPPacketInfo> icmpInfo = simulation.getIcmpPacketInfo();
        // Analyze or print the recorded ICMP packet details
        for (ICMPPacketInfo info : icmpInfo) {
            System.out.println("Source VM: " + info.getSourceVMId());
            System.out.println("Destination VM: " + info.getDestinationVMId());
            System.out.println("Stage: " + info.getStage());
            System.out.println("Start Time: " + info.getStartTime());
            System.out.println("End Time: " + info.getEndTime());
            System.out.println("Packet Size: " + info.getPacketSize());
            // Perform more detailed analysis or calculations based on this information
        }
*/
        // Datacenter Metrics
        //System.out.println("Total energy consumption: " + datacenter.getPower() + " W");
        // Access other datacenter metrics or custom defined metrics for analysis

        // Accessing Cloudlet Execution Data

        //List<NetworkCloudlet> cloudletList = simulation.getGeneratedCloudlets();

        for (NetworkCloudlet cloudlet : cloudletList) {
            System.out.println("Cloudlet            -" + cloudlet.getCloudletId() + " Status: " + cloudlet.getCloudletStatus());
            System.out.println("Execution time      -" + cloudlet.getActualCPUTime());
            System.out.println("Cloudlet History    -" + cloudlet.getCloudletHistory());
            System.out.println("Resource Name       -" + cloudlet.getResourceName(vmid));
        }

        CloudSim.stopSimulation();

        //Datacenter datacenter = null;
        try {
            //datacenter = new Datacenter("Datacenter", characteristics, new VmAllocationPolicySimple(HostsList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DatacenterBroker createBroker(){
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }
}

