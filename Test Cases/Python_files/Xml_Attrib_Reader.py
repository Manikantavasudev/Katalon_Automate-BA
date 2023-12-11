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
        for node in tree.iter():
            count = 0
            dict_key = node.tag.split('}')[1].replace("_", " ")
            dict_value = node.attrib
            if dict_key in self.data_names and count == 0:
                if dict_key in self.sub_key:
                    count = 1
                else:
                    temp = self.data_names[dict_key]
                    self.sub_key.append(dict_key)
                    self.data_names[dict_key] = list()
                    self.data_names[dict_key].append(temp)
                    count = 0
                if dict_key not in self.skip:
                    self.data_names[dict_key].append(dict_value)
            elif dict_key not in self.skip:
                self.data_names[dict_key] = dict_value
        for key, value in self.data_names.items():
            count_flag = count_flag + 1
            if isinstance(value, list):
                for item in value:
                    count_flag = count_flag + 1
                    print("{}={},".format(key, item))
            else:
                print("{}={},".format(key, value))
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
