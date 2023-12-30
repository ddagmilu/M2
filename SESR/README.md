`manet-routing-compare.cc` automater for ns3

1. Download the new modified `manet-routing-compare.cc`, place it under `/ns-3.39/examples/routing/`

2. Place the automation script with `ns3` executable (`/ns-3.39/`)

3. Give the script executing permission (`[Command-line] $ sudo chmod +x manet-auto.sh`)

4. Examples
   ```
   ./manet-auto.sh -t 30 -p OLSR -d 20 -f Iteration -v RESULT
                Iteratations       : 30
            		Protocol           : OLSR
            		Density            : 20
            		Iteration filename : Iteration
            		Avereges filename  : RESULT

   ./manet-auto.sh -t 10 -r 1 -p AODV -a 9.5 -d 50 -m 25 -f Iteration -v RESULT
                Iterations         : 10
            		SetRun      	     : 1 (Start with 1)
            		Protocol    	     : AODV
            		Trans Power 	     : 9.5
            		Density     	     : 50
            		Speed              : 25
            		Iteration filename : Iteration
            		Avereges filename  : RESULT
   ```
   * See script flags : `./manet-auto.sh -h`
   * ![image](https://github.com/ddagmilu/M2/assets/29633070/ee1208fa-23f6-41ca-aaa1-c62a405d6874)

5. After finishing the Semulation, you will get detailed `CSV`s under `/ns-3.39/Result/`
   *  
   
