from xml.etree import ElementTree
import sys

class XMLReader:

    def __init__(self, filename):
        self.filename = filename
        self.data_names = {}
        self.sub_key = []
        self.count_flag = 0
        self.count = 0
        self.skip = ['VIF', 'VIF App', 'Product', 'Component',
                     'SrcPdoList', 'SrcPDO', 'SOPSVID',
                     'SnkPDO', 'SnkPdoList',
                     'SOPSVIDList', 'SOPSVIDModeList', 'SOPSVIDMode']
        self.xml_call(self.filename)

    def xml_call(self, file):
        with open(file, 'rt') as f:
            tree = ElementTree.parse(f)
        #tree = ElementTree.parse(file)
        count_flag = 0
        count = 0
        dict_of_device = {}
        for node in tree.iter():
            line = node.tag.split('}')
            dict_of_device[str(line)] = node.attrib
        dict_key = dict_of_device
        for i in dict_key :
            temp = str(i).replace("[","").replace("]","")
            index = str(temp).replace("'", "").replace("_", " ")
            if(index not in self.skip):
                temp = index.replace("{http://usb.org/VendorInfoFile.xsd, ","")
                print(temp+"="+str(dict_key[i])+',')
        return count_flag


def main():
    #parser = argparse.ArgumentParser()
    #parser.add_argument("-VIF_FILE", "--PC_Load_VIF_Location", metavar='',
                        #required=False, help="--This is parsing vif file from CLI")
    #args = parser.parse_args()
    #filename = args.PC_Load_VIF_Location
    filename = sys.argv[1]
    reader = XMLReader(filename)


if __name__ == "__main__":
    main()
