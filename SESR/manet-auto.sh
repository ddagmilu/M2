#!/bin/bash

echo """
   _____________________________________________________
 / \                             			\.
|   |  		MANET-Routing-Comapre  AUTO   		|.
 \_ |                            			|.
    |  [1] Place manet-auto.sh at (ns3) folder    	|.
    |                                                   |.
    |  [2] Place the new manet-routing-compare.cc       |.
    |       under ../ns3/examples/routing/, (backup     |.
    |       the original)		          	|.
    |                                                   |.
    |  [3] Before your first run        		|.
    |	     $ chmod +x manet-auto.sh       		|.
    |                                                   |.
    |                                                   |.
    |  [*] Help and examples?                           |.
    |          $ ./main.sh -h           		|.
    |                            			|.
    |   ________________________________________________|.
    |  /                            			/.
    \_/________________________________________________/.
""";
#BLUE='\033[0;44m'
#RED='\033[0;41m'
#CLEAR='\033[0m'

# ---------------------------  OPTIONS
# ------------------  Defaults
times=30
setrun=5        # Start with SetRun = 5
#setrun=$(shuf -i 1-7 -n1)
#setseed=$(shuf -i 1-3 -n1)
protocol="OLSR"
trans_power=7.5
density=50
speed=25
filename=Iteration_
CSVavg=RESULTS
# ------------------  Flag Options
while getopts ":t:r:s:p:f:a:d:m:v:h" flag
do
    case "${flag}" in
        t) times=${OPTARG};;        # Repeating, Default = 30
        r) setrun=${OPTARG};;       # SetRun, Default = Random 1 ~ 7
        s) setseed=${OPTARG};;      # SetSeed, Default = Random 1 ~ 3
        p) protocol=${OPTARG};;     # NO DEFAULT
        f) filename=${OPTARG};;     # CSV Filename, Default = CSV_Dyali_$protocol_$i.csv
        a) trans_power=${OPTARG};;  # Transmission Power (tx), Default = 7.5
        d) density=${OPTARG};; 	    # Density, Node number (nWifis in .cc script), Default = 50
        m) speed=${OPTARG};;        # Mobility Speed, speed of nodes, Default = 25
        v) CSVavg=${OPTARG};;       # CSV filename that contain each iteration average
        h)
            echo "Options:"
            echo "-h: Display help text"
            echo "-t: Repeating, Default = 30"
            echo "-r: SetRun, Default = Start: 5, End: 35, Step: 1"
            echo "-s: SetSeed, (WON'T NEED IT)"
            echo "-p: Protocol Default = OLSR"
            echo "-f: CSV Filename for each iteration, Default = Iteration_(protocol)_(i).csv"
            echo "-a: Transmission power (tx)"
            echo "-d: Density, Node number (nWifis in .cc script)"
            echo "-m: Mobility Speed, Speed of nodes (mobilityspeed in .cc script)";
            echo "-v: CSV filename that contains each iteration average (VERY IMPORTANT)"
            echo "";
            echo "Examples :";
            echo "./main.sh -t 30 -p OLSR -d 20 -f Iteration -v RESULT";
            echo """
            		Iteratations       : 30
            		Protocol           : OLSR
            		Density            : 20
            		Iteration filename : Iteration
            		Averages filename  : RESULT
            	""";
            echo "";
            echo "./main.sh -t 10 -r 1 -p AODV -a 9.5 -d 50 -m 25 -f Iteration -v RESULT";
            echo """
            		Iterations         : 10
            		SetRun      	   : 1 (Start with 1)
            		Protocol    	   : AODV
            		Trans Power 	   : 9.5
            		Density     	   : 50
            		Speed              : 25
            		Iteration filename : Iteration
            		Averages filename  : RESULT

            """;
            exit 0
            ;;
        :)
            echo "Option -$OPTARG requires an argument."
            exit 1
            ;;
        \?)
            echo "Invalid option: -$OPTARG. Use -h for help."
            exit 1
            ;;
    esac
done

# ------------------  ECHOing your options
echo "[+] Repeat            : $times";                                      # Default = 30
echo "[+] Set Run           : Start: 5, End: 35, Step: 1 "  # Default 5:35:1 (30 times)
#echo "[+] Set Seed	    : ${BLUE} RANDOM In each iteration ${CLEAR}"    # Default
echo "[+] Protocol          : $protocol";                                   # NO DEFAULT
echo "[+] Transmition Power : $trans_power";                                # NO DEFAULT
echo "[+] Density	    : $density";				    # Density
echo "[+] Filename	    : $filename";                                   # Default = CSV_Dyali_$protocol_$i.csv
echo "[+] Averages CSV name : $CSVavg";					    # Default = Father_CSV_$protocol.csv

#./main.sh -t 30 -p OSLR -d 50 -f Result -v All_Results


# ---------------------------  EXECUTING
mkdir Results   # Make Results folder


# --------- In (command) you set ns3 flags

# Flags in the new manet-routing-compare.cc (line 244)

#CSVfileName	: The name of the CSV output file name
#protocol	: Routing protocol (OLSR, AODV, DSDV, DSR)
#CSVavg		: The name of the CSV containing all averages
#density		: Number of nodes
#mobilityspeed	: Speed of nodes
#power		: Transmission Power
#SetSeed		: Change RNG seed (WON T NEED IT)
#SetRun		: Change RNG run

command='./ns3 run "manet-routing-compare --SetRun=$setrun --protocol=$protocol --power=$trans_power --density=$density --mobilityspeed=$speed --CSVfileName=${filename}_${protocol}_${i}.csv --CSVavg=${CSVavg}_${protocol}.csv" 1> /dev/null'

# -------------------------------------------

for i in $(seq 1 $times)
do
   echo "[ -------------------------------- ($i) --------------------------------]"
   #setrun=$(shuf -i 1-7 -n1)
   #setseed=$(shuf -i 1-3 -n1)

   echo "[~]  RngSeedManager::SetRun($setrun);";
   #echo "[~] ${RED} RngSeedManager::SetSeed($setseed); ${CLEAR}";

   echo "[~] Executing --> {$command}";
   eval "$command"

   setrun=$((setrun + 1)) # setrun++
   mv ${filename}_${protocol}_${i}.csv Results
done
mv ${CSVavg}_${protocol}.csv Results
