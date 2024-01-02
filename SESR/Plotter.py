import pandas as pd
from pathlib import Path
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import csv
import sys
import time
import os

Data_OLSR = []
Data_AODV = []

delays_OLSR_Trans = []
delays_AODV_Trans = []

csv_files_list_OLSR = sorted(Path('.').glob('*_OLSR.csv'))
csv_files_list_AODV = sorted(Path('.').glob('*_AODV.csv'))

for filename in csv_files_list_OLSR:
    df = pd.read_csv(f'{filename}')
    delays_OLSR = [float(delay.replace('ns', '').replace('+', '').replace('e', 'e+')) for delay in df['Total_Delay']]
    for d in delays_OLSR:
        delays_OLSR_Trans.append(d / 1e9)
    # Uncomment HERE
    Data_OLSR.append(list(df["AVG_Throughput_Kbps"]))
    #Data_OLSR.append(list(delays_OLSR))
    #Data_OLSR.append(list(df["PDR_%"]))

    #print('\n')
    #print(f'######################### {filename} #########################')
    #print('\n Throughput ')
    #print('Averege Throughput :   ' + str(df['AVG_Throughput_Kbps'].mean()))
    #print('Max Throughput     :   ' + str(df['AVG_Throughput_Kbps'].max()))
    #print('Min Throughput     :   ' + str(df['AVG_Throughput_Kbps'].min()))
    #print('Median Throughput  :   ' + str(df['AVG_Throughput_Kbps'].median()))
    #print('Std of Throughput  :   ' + str(df['AVG_Throughput_Kbps'].std()))
    #print('Var of Throughput  :   ' + str(df['AVG_Throughput_Kbps'].var()))
    #print('Count of ReceiveRates: ' + str(df['AVG_Throughput_Kbps'].count()))
    #print('Sum of ReceiveRates:   ' + str(df['AVG_Throughput_Kbps'].sum()))

for filename in csv_files_list_AODV:
    df = pd.read_csv(f'{filename}')
    delays_AODV = [float(delay.replace('ns', '').replace('+', '').replace('e', 'e+')) for delay in df['Total_Delay']]
    for d in delays_AODV:
        delays_AODV_Trans.append(d / 1e9)
    # Uncomment HERE
    Data_AODV.append(list(df["AVG_Throughput_Kbps"]))
    #Data_AODV.append(list(delays_AODV))
    #Data_AODV.append(list(df["PDR_%"]))


x_values = [20, 40, 60, 80]  # Density


df1 = pd.DataFrame(Data_OLSR)
df2 = pd.DataFrame(Data_AODV)


fig, axes = plt.subplots(1, 2, figsize=(10, 20))

sns.boxplot(data=Data_OLSR, color="white", ax=axes[0], showmeans=True, showfliers=True)
sns.boxplot(data=Data_AODV, color="white", ax=axes[1], showmeans=True, showfliers=True)

# OLSR subplot
axes[0].set_xticklabels(x_values)
#axes[0].set_yticklabels()
axes[0].set_xlabel('Density')
axes[0].set_ylabel('Throughput Kbps')

# AODV subplot
axes[1].set_xticklabels(x_values)
#axes[1].set_yticklabels()
axes[1].set_xlabel('Density')
axes[1].set_ylabel('Throughput Kbps')


# Set titles for the subplots
axes[0].set_title('OLSR')
axes[1].set_title('AODV')


# Show the plots
plt.tight_layout()
plt.show()
