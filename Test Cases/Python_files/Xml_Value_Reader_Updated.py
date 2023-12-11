import sys
from xml.etree import ElementTree
import argparse


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
        count_flag=0
        #print(tree)
        for node in tree.iter():
            #print(node.tag)  
            count = 0
            dict_key = node.tag.split('}')[1].replace("_", " ")
            #print(dict_key)
            dict_text_value = node.text
            dict_value_atr=node.attrib
             
            if dict_text_value and dict_value_atr:
              #dict_value_atr.split(':')[1].split.replace("}","")
              #dict_value_atr.split(':')[1].replace("}", "")
              #print(dict_value_atr)
              dict_value=("{}:{}".format(dict_value_atr,dict_text_value))
              dict_value=dict_value.split("{'value':")[1].replace("}","").replace("'","").strip()
              
              #print(dict_value_atr)
             
            elif (node.text== None):
             dict_value=("{}:".format(dict_value_atr))
             dict_value=dict_value.split(":")[1].replace("}","").replace("'","").strip()
             #print(dict_value)             
            else:           
              #dict_value=node.attrib.split("{'value':")[1].replace("}", "")
              dict_value=dict_text_value
              #dict_value=dict_value.split("{'value':")[0].replace("}", "")
             
                        
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
                    #print("{}={},".format(key, item))
            else:
                pass
                #print("{}={},".format(key, value))
        print(self.data_names)
        return self.data_names


def main():
    '''
    parser = argparse.ArgumentParser()
    parser.add_argument("-VIF_FILE", "--PC_Load_VIF_Location", metavar='',
                        required=False, help="--This is parsing vif file from CLI")
    args = parser.parse_args()
    filename = args.PC_Load_VIF_Location
    '''
    filename = sys.argv[1]
    reader = XMLReader(filename)



if __name__ == "__main__":
    main()
