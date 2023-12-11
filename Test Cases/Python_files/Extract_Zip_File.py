import zipfile
import sys
import argparse
import os

class ExtractZip:
    def __init__(self, filename,extractlocation):
        self.filepath = filename
        self.extarctpath = extractlocation
        self.extarct(self.filepath,self.extarctpath)
    
    def extarct(self,filename,extrtactpath):
        with zipfile.ZipFile(filename, 'r') as zip_ref:
            zip_ref.extractall(extrtactpath)
    
def main():
    parser = argparse.ArgumentParser()
    filename = sys.argv[1]
    extractlocation = sys.argv[2]
    reader = ExtractZip(filename,extractlocation)

if __name__ == "__main__":
    main()
