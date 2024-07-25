from GUI import Ui_MainWindow
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QFileDialog, QTableWidgetItem, QApplication, QMainWindow, QMessageBox
from PyQt5.QtCore import QUrl
import threading
import requests
import sys
import os


def Update_table(nums_parts):
    num_parts = nums_parts
    current_rows = ui.Parts_Table.rowCount()
    if num_parts > current_rows:
        diff = num_parts - current_rows
        for i in range(diff):
            ui.Parts_Table.insertRow(current_rows + i)
            ui.Parts_Table.setItem(current_rows + i, 0, QTableWidgetItem(f"part{current_rows + i + 1}.tmp"))

    elif num_parts < current_rows:
        diff = current_rows - num_parts
        for i in range(diff):
            ui.Parts_Table.removeRow(current_rows - i - 1)


def fetching(show):
        ui.Console_Text.clear()
        image_url = ui.URL_Box.text()
        ui.Download_Bar.setValue(0)
        # 1. Getting file info via response Headers
        response = requests.head(image_url)
        status_code = response.status_code
        HTTP_version = response.raw.version
        content_length = int(response.headers.get('Content-Length', 0))    # default value = None
        content_type = response.headers.get('Content-Type', None)
        accept_ranges = response.headers.get('Accept-Ranges', False)
        accept_ranges_color = "#00aa7f" if accept_ranges != False else "#fc4a44"

        # 2. Load WebView
        if status_code == 200:
            ui.Web_View.load(QUrl(image_url))

        # 3. Parsing table
        num_parts = int(ui.Parts_Spinner.value())
        part_percentages = []

        for i in range(num_parts):
            if ui.Parts_Table.item(i, 1) != None:
                percentage = int(ui.Parts_Table.item(i, 1).text())
                part_percentages.append(percentage)

        # 4. Do we remove .tmp file?
        Remove_tmp = ui.Remove_Check.isChecked()

        # 5. Print results
        if show == True:
            ui.Console_Text.insertHtml(f'''<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/REC-html40/strict.dtd">
            <html><head><meta name="qrichtext" content="1" /><style type="text/css">p, li ( white-space: pre-wrap; ) </style></head><body style=" font-family:'Hack'; font-size:14pt; font-weight:400; font-style:normal;"> <p style=" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;"><span style=" font-weight:600; font-style:italic;">HTTP/{HTTP_version} {status_code}</span></p>
            <p style=" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;">Content-Type   : <span style=" font-weight:600;">{content_type}</span></p>
            <p style=" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;">Accept-Ranges  : <span style=" font-weight:600; color:{accept_ranges_color};">{accept_ranges}</span></p>
            <p style=" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;">Content-Length : <span style=" font-weight:600;">{content_length} </span></p></body></html>
            ''')
            ui.Console_Text.append(f'Parts percentages   : {part_percentages}%')
            ui.Console_Text.append(f'Removing .tmp files : {Remove_tmp}')

        return [status_code,         # HTTP status           (Integer)
                content_type,        # Content Type          (String)
                accept_ranges,       # Accept Ranges?        (String / False)
                content_length,      # Content length        (bytes)
                part_percentages,    # Parts percentages     (List)
                Remove_tmp]          # Removing .tmp files?  (Boolean)



def download_image_parts(url):
    try:
        # 1. Get information
        Fetching_result = fetching(False)
        status_code = Fetching_result[0]
        content_type = Fetching_result[1]
        accept_ranges = Fetching_result[2]
        content_length = Fetching_result[3]
        part_percentages = Fetching_result[4]
        Remove_tmp = Fetching_result[5]

        FileExtension = content_type.split("/")[1] or url.split(".")[2]
        total_parts = len(part_percentages)
        total_percentage = sum(part_percentages)

        print("------------------ FILE INFO ------------------")
        print(f'[~] Content-Length : {content_length}')
        print(f'[~] Content-Type   : {content_type}')
        print(f'[~] Accept-Ranges  : {accept_ranges} {type(accept_ranges)}')
        print(f'[~] Extension      : {FileExtension}')
        print(f'[~] Total Parts    : {total_parts}')
        print(f'[~] Percentages    : {part_percentages}')
        print("-----------------------------------------------")

        # 2. Checking before downloading
        if total_percentage != 100 :
            raise ValueError("Total percentage should be 100%")
        elif status_code != 200:
            raise ValueError(f"Server respond with: {status_code}")

        byte_ranges = []
        last_end = 0

        # 3. Calculating Bytes ranges
        for i, percentage in enumerate(part_percentages):
            start_byte = last_end
            end_byte = start_byte + (percentage / 100) * content_length - 1
            byte_ranges.append((start_byte, end_byte))
            last_end = end_byte + 1

        # 4. Downloading...
        for i, (start_byte, end_byte) in enumerate(byte_ranges):
            headers = {"Range": f'bytes={int(start_byte)}-{int(end_byte)}'}
            #headers = {"Range": f'bytes=203343-271123'}
            response = requests.get(url, headers=headers, stream=True)

            with open(f'part0{i + 1}.tmp', 'wb') as file:
                for chunk in response.iter_content(chunk_size=1024):
                    file.write(chunk)
            ui.Console_Text.append(f'[+] Range : {"{message: <7}".format(message=int(start_byte))} > {"{message: <7}".format(message=int(end_byte))} bytes (part {i + 1}.tmp)')
            ui.Download_Bar.setValue(part_percentages[i]+ui.Download_Bar.value())
            print(part_percentages[i])

        # 5. Concatenate parts into the final image file
        with open(f'myimage.{FileExtension}', 'wb') as outfile:
            for i in range(total_parts):
                with open(f'part0{i + 1}.tmp', 'rb') as infile:
                    outfile.write(infile.read())

        ui.Console_Text.append('[+] Image parts downloaded and concatenated successfully. \n')

        # 6. Clean up temporary part files
        if Remove_tmp == True:
            for i in range(total_parts):
                os.remove(f'part0{i + 1}.tmp')

    except requests.RequestException as e:
        ui.Console_Text.append(f'<span style=" font-weight:600; color:#fc4a44;">[!] Error:</span> {e}')
    except ValueError as e:
        ui.Console_Text.append(f'<span style=" font-weight:600; color:#fc4a44;">[!] Error:</span> {e}')


os.environ["QT_QPA_PLATFORMTHEME"] = "gtk3"
os.environ["QT_STYLE_OVERRIDE"] = "Fusion"

app = QtWidgets.QApplication(sys.argv)
MainWindow = QtWidgets.QMainWindow()
ui = Ui_MainWindow()
ui.setupUi(MainWindow)
MainWindow.show()


if __name__ == "__main__":
    ui.Console_Text.clear()
    ui.Download_Button.setEnabled(True)
    ui.Fetch_Button.clicked.connect(lambda: fetching(True))
    ui.Parts_Spinner.valueChanged.connect(lambda: Update_table(int(ui.Parts_Spinner.value())))
    ui.Download_Button.clicked.connect(lambda: download_image_parts(ui.URL_Box.text()))
    banner = input("------------- Content-Range Interface -------------")



