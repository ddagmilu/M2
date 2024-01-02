
## Manet-Routing-Compare Automater for *ns3*

1. Download the new modified `manet-routing-compare.cc`, place it under `/ns-3.39/examples/routing/`

2. Place the automation script with `ns3` executable (in `/ns-3.39/`)

3. Give the script execute permission (`[Command-line] $ sudo chmod +x manet-auto.sh`)

4. Examples
    
    ```
    ./manet-auto.sh -t 30 -p OLSR -d 20 -f Iteration -v RESULT
    Iteratations       : 30
    Protocol           : OLSR
    Density            : 20
    Iteration filename : Iteration
    Averages filename  : RESULT
    
    ./manet-auto.sh -t 10 -r 1 -p AODV -a 9.5 -d 50 -m 25 -f Iteration -v RESULT
    Iterations         : 10
    SetRun             : 1 (Start with 1, Default = 5)
    Protocol           : AODV (Default = OLSR)
    Trans Power        : 9.5  (Default = 7.5)
    Density            : 50   (Default = 50)
    Speed              : 25   (Default = 25)
    Iteration filename : Iteration ( >> Iteration_{protocol}_{i}.csv)
    Averages filename  : RESULT (RESULT_{protocol}.csv)
    ```
    
	* See script flags : `./manet-auto.sh -h`
[![image](https://private-user-images.githubusercontent.com/29633070/293454638-ee1208fa-23f6-41ca-aaa1-c62a405d6874.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDM5MzE0OTksIm5iZiI6MTcwMzkzMTE5OSwicGF0aCI6Ii8yOTYzMzA3MC8yOTM0NTQ2MzgtZWUxMjA4ZmEtMjNmNi00MWNhLWFhYTEtYzYyYTQwNWQ2ODc0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyMzEyMzAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMjMwVDEwMTMxOVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTA3ZjgxMGU5NGYzZTA2ZTYyOGQ0ZjUxODJjNDkwZjRlYTY1NmQ5OGM2MWRmYjBiNzI3MmQzMzdhZmRiYmE2MzYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.yqHJcvAtQKtqKVBN98_fI9WHND6Msl9kM4nQHX_cesk)](https://private-user-images.githubusercontent.com/29633070/293454638-ee1208fa-23f6-41ca-aaa1-c62a405d6874.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDM5MzE0OTksIm5iZiI6MTcwMzkzMTE5OSwicGF0aCI6Ii8yOTYzMzA3MC8yOTM0NTQ2MzgtZWUxMjA4ZmEtMjNmNi00MWNhLWFhYTEtYzYyYTQwNWQ2ODc0LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyMzEyMzAlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMjMwVDEwMTMxOVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTA3ZjgxMGU5NGYzZTA2ZTYyOGQ0ZjUxODJjNDkwZjRlYTY1NmQ5OGM2MWRmYjBiNzI3MmQzMzdhZmRiYmE2MzYmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.yqHJcvAtQKtqKVBN98_fI9WHND6Msl9kM4nQHX_cesk)

5. After finishing the simulation, you will get detailed `CSV`s under `/ns-3.39/Result/` 
* For : `./manet-auto.sh -t 30 -r 2 -p OLSR -d 20 -f Iteration_d20 -v Result_d20` >
* Bottom output of the modified `.cc` script
![1](https://github.com/ddagmilu/M2/assets/29633070/43b54f82-6787-422a-a2f4-8be7e4737a2f)

* Result folder (*don't forget to move it so it won't be crushed in the next simulation*)
![2](https://github.com/ddagmilu/M2/assets/29633070/974582e9-16cd-4f54-b048-6c79ce0165c3)


* `Result_d20_OLSR.csv` : averages from all 30 iterations
![4](https://github.com/ddagmilu/M2/assets/29633070/2cc247c7-3d71-4adb-bb32-9875fb6a8bb6)


* `Iteration_d20_OLSR_7.csv` : This file contains data for the 7th iteration flows, You can obtain averages from it also using _Panda_ in Python or _Excel_ formulas
![3](https://github.com/ddagmilu/M2/assets/29633070/69dd5232-4694-44e0-abe6-5f83706f910a)

 
---

### Plotter.py
* Try to set `showfliers` (line 62,63) to `True` or `False` and see what works for you
![Figure_1](https://github.com/ddagmilu/M2/assets/29633070/f14172b6-d6da-4368-a96e-d08f5ced0363)


