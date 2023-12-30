import pandas as pd
from pathlib import Path
import numpy as np
import csv
import sys
import time
import os

# Example 1 : Get the averages of ReceiveRate from the all the CSVs
ReceiveRate_List = []


csv_files_list = sorted(Path('.').glob('*.csv'))
print(f'[-] Number of CSV files: {len(csv_files_list)} \n')
print(f'[-] List of CSV files: {", ".join(str(x) for x in csv_files_list)} \n')

continue_order = input("[?] Would you like to continue? (y/n) : ")
if continue_order == "n":
    sys.exit(0)


# Create Result file
try:
    if not os.path.exists("Results"):
        # Create the folder if it doesn't exist
        os.makedirs("Results")
    timestr = time.strftime("%Y-%m-%d_%H-%M-%S")
    Result_filename = f'Result_{timestr}.csv'
    print(f'[+] Creating {Result_filename} ....')
    csv_file_path = os.path.join("Results", Result_filename)

    with open(csv_file_path, "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerow(["Filename", "Sum_Of_ReceiveRates", "Max_ReceiveRate", "Min_ReceiveRate", "Median_ReceiveRate"])
        f.close()

except FileNotFoundError as e:
    print(f'An error occurred: {e}')


for filename in csv_files_list:
    # Creating the dataframe for file number (i)
    df = pd.read_csv(f'{filename}')
    print('\n')
    print(f'######################### {filename} #########################')

    # print ReceiveRate column
    print('Mean ReceiveRate:      ' + str(df['ReceiveRate'].mean()))
    print('Sum of ReceiveRates:   ' + str(df['ReceiveRate'].sum()))
    print('Max ReceiveRate:       ' + str(df['ReceiveRate'].max()))
    print('Min ReceiveRate:       ' + str(df['ReceiveRate'].min()))
    print('Count of ReceiveRates: ' + str(df['ReceiveRate'].count()))
    print('Median ReceiveRate:    ' + str(df['ReceiveRate'].median()))
    print('Std of ReceiveRates:   ' + str(df['ReceiveRate'].std()))
    print('Var of ReceiveRates:   ' + str(df['ReceiveRate'].var()))

    # Example 1 : Add the ReceiveRate Avg to the list
    ReceiveRate_List.append(df['ReceiveRate'].sum())

    # Add to Result file
    with open(csv_file_path, "a", newline="") as f:
        writer = csv.writer(f)
        writer.writerow([f'{filename}', str(df['ReceiveRate'].sum()), str(df['ReceiveRate'].max()), str(df['ReceiveRate'].min()), str(df['ReceiveRate'].median())])
        f.close()


print(f"##############################################")
print(f"######### Result of the {len(csv_files_list)} CSV files #########")
print(f"##############################################")
print(f'ReceveRate List of the {len(csv_files_list)} files : {str(ReceiveRate_List)}')
# Example 1 : print the median of ReceveRate averages
print(f'ReceveRate Median of the {len(csv_files_list)} files : {np.median(np.array(ReceiveRate_List))}')
df = pd.read_csv(csv_file_path)
print(df)
