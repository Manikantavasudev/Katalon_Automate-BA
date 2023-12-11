#*******************************************************************************
## Unpublished Confidential Information of Granite River Labs 
# Do not disclose.# Copyright (c) 2020-2021 Granite River Labs.  All Rights Reserved.
#******************************************************************************

#*******************************************************************************
#Test ID : 4.1.1.4 C2 Test Case List Tests
#*******************************************************************************
#DESCRIPTION: 
# Back end algorithm that compares the C2 Test Case list derived from UI with the TestList in the json file. This file is derived from the C2 API's.
# The comparison is done based on supported DUT Type
#
# 1.Input TestData.json is provided as 1st commmand line argument.
# 2.Input UI data List is provided as 2nd command line argument
# 3.Output Output1.txt is saved in C:\Users\Suma KY\git\C2AutomationTest_Kat\Test Cases\Python_files. 
# 4.Outputfile stores the following data 
#   1.As to test passed or failed and also 
#   2.Number of   test cases that matched
#   3.In case of failure list of Test Cases missing in the UI list
#   4.MOI List Validation based  on Certificaiton Type

import sys
import json
import io
import enum
matchtestcount = 0
misstestcount  = 0
jsonmoi        = []
missingtestlist = []
outputstring   = ""

#Not in use
class DUTKeyValue(enum.Enum):
  IsCable = "IsCable"
  IsProv = "IsProv"
  IsCons = "IsCons"
  IsPC = "IsPC"
  IsCP = "IsCP"
  IsDRP = "IsDRP"
  IsAltModeUFP = "IsAltModeUFP"
  IsAltModeDFP = "IsAltModeDFP"
  IsType_C_SRC = "IsType_C_SRC"
  IsType_C_SNK = "IsType_C_SNK"
  IsType_C_DRP = "IsType_C_DRP"


#Resolves the failures due to unicode chars during  string compare 
def ApplyEncode( unicodestr):
 string_encode = unicodestr.encode("ascii", "ignore")
 string_decode = string_encode.decode().strip()
 return string_decode
# Conversion from string to key value for DUT Type
def GetDutKeyValue(dut_type):
 Key = ''
 if dut_type == "IsCable":
  Key = 'IsCable'
 if dut_type == "IsProv":
  Key = 'IsProv'
 if dut_type == "IsCons":
  Key = 'IsCons'
 if dut_type == "IsPC":
  Key = 'IsPC'
 if dut_type == "IsCP":
  Key = 'IsCP'
 if dut_type == "IsDRP":
  Key = 'IsDRP'
 if dut_type == "IsAltModeUFP":
  Key = 'IsAltModeUFP'
 if dut_type == "IsAltModeDFP":
  Key = 'IsAltModeDFP'
 if dut_type == "IsType_C_SRC":
  Key = 'IsType_C_SRC'
 if dut_type == "IsType_C_SNK":
  Key = 'IsType_C_SNK'
 if dut_type == "   ":
  Key = 'IsType_C_DRP'
 return Key
 
#To display the test cases names, count based on MOI's 
def Display_MOITestList(uitestcaselist,testlistjson,dut_type):
 global jsonmoi
 testlist = []
 if len(jsonmoi) == 0 :
  return testlist
 for moi in range(len(jsonmoi)):
   moiname = jsonmoi[moi]
   moiname = ApplyEncode(moiname)
   testlist = GetMOI_TestList(uitestcaselist,testlistjson,moiname,dut_type)

def GetMOIUITestList(testcaselist):
 uitestlist = []
 dataname = ""
 for testid in range(len(testcaselist)):
     dataname = testcaselist[testid]
 return   uitestlist  
 
#List of all the Test Cases's displayed in C2 UI along with json type MOI Names
def GetUIMOINames(uitestcaselist):
 uiMOIList = []
 dataname = ""
 for testid in range(len(uitestcaselist)):
     dataname = uitestcaselist[testid]
     if "MOI_" in dataname:
      dataname = dataname.replace("MOI_","")
      dataname = ConvertjsonMOIName(dataname)
      if dataname not in uiMOIList:
       uiMOIList.append(dataname)
 print("UI MOI List",uiMOIList)
 return   uiMOIList  
def ConvertjsonMOIName(uimoiname):
 moiname = ""
 if uimoiname ==  "Power Delivery 3.0 Tests-v1.19":
  moiname = "COMPLIANCE_TEST_PD3" 
 if uimoiname ==  "PD2 Communication Engine Tests-v1.09":
  moiname = "COMMUNICATION_ENGINE_TESTS"
 if uimoiname ==  "PD2 Deterministic Tests-v1.14":
  moiname = "DETERMINISTIC_TESTS"
 if uimoiname ==  "USB-C Functional Tests-v0.82":
  moiname = "FUNCTIONAL_TESTS"
 if uimoiname ==  "USB Power Delivery Compliance Test Specification-v1.3 RC12":
  moiname = "PD_Merged"
 if uimoiname ==  "Thunderbolt Power Tests-Rev1.5 Ver0.9":
  moiname = "TBT_POWER_TESTING"
 if uimoiname ==  "DisplayPort Alternate Mode Tests-v4":
  moiname = "DP_ALT_MODE_TESTS"
 if uimoiname ==  "BC1.2 DCP Sink Tests":
  moiname = "BC_1_2_TESTS"
 if uimoiname ==  "Quick Charge 4 Tests-v1.0":
  moiname = "QC4_TEST"
 if uimoiname ==  "Quick Charger 3.0 Tests-v1.4":
  moiname = "QC_LEGACY"
 if uimoiname ==  "Source Power Tests-v0.74":
  moiname = "USB_C_SOURCE_POWER_TEST"
 if uimoiname ==  "QC3+ Tests-v1.2":
  moiname = "QC3Plus"
 if uimoiname ==  "MFi Charger Tests-v1.0":
  moiname = "MFI_TESTS"
 return   moiname 

def GetMOI_TestListUI(testcaselist, moiname,dut_type):
 testlist = []
 for testid in range(len(testcaselist)):
     data = testcaselist[testid]
     #for key in data.keys():
     data =  testcaselist[testid]
     moi = str(data['MOI_Name'])
     moi = ApplyEncode(moi)
     #Issupported = "False"
     if moi == moiname:
      Key1 = GetDutKeyValue(dut_type)
      Issupported = str(data[Key1])
      if Issupported == "true":
       testlist.append(testname)
 print("DUT Type",dut_type,"***************************",moiname,"*****************", "Count:",len(testlist) )
 print ("************************************************************************************")
 print( testlist)
 print ("************************************************************************************")
 return testlist
 #List of all the MOI's in Test json file based on supported DUT
def GetMOI_TestList(uitestcaselist,testcaselist, moiname,dut_type):
 global matchtestcount
 global misstestcount
 uicount = 0
 missingtest = []
 testlist = []
 for testid in range(len(testcaselist)):
     data = testcaselist[testid]
     #for key in data.keys():
     data =  testcaselist[testid]
     moi = str(data['MOI_Name'])
     moi = ApplyEncode(moi)
     Issupported = "False"
     if moi == moiname:
      Key1 = GetDutKeyValue(dut_type)
      Issupported = str(data[Key1])
      if moi == "FUNCTIONAL_TESTS" or moi == "PD_Merged":
       if Key1 == 'IsCable':
         Issupported = str(data[Key1])
       else:
         Issupported = "True"
        
      testname = str(data['Test_Name'])
      testname = ApplyEncode(testname)
      if (moi == "MFI_TESTS"):
       print("MFI Test", testname,"Issupported", (Issupported), "Key1", str(Key1))
      if Issupported == "True":
       testlist.append(testname)
       found = 0
       for testid2 in range(len(uitestcaselist)):
        uidata = uitestcaselist[testid2]
        uidata = ApplyEncode(uidata)
        if testname  == uidata:
         uicount += 1
         found = 1
         break
       if found == 0:
         missingtest.append(testname)
 if (len(missingtest) > 0 ):        
  missingtestlist.append(missingtest)
 matchtestcount += len(testlist)
 misstestcount +=  len(missingtest)
 print("DUT Type",dut_type,"***************************",moiname,"*****************", "Count:",len(testlist), "UI Count:", uicount  )
 print ("************************************************************************************")
 print( testlist)
 print ("************************************************************************************")
 print ("missing cases","************************************************************", "missing Count", len(missingtest))
 print( missingtest)
 print ("************************************************************************************")
 return testlist
#Reading JSON file derived from C2 API to get the list of all  test cases mapped with MOI and supported DUT types       
def GetData(file ):
#Reading JSON file derived from C2 API to get the list of all supported test cases mapped with MOI and supported DUT types
  testlist = []
  #Open the json file get data
  with open(file, 'rt') as json_file:
   testlist = json.load(json_file)
   json_file.close()
  return testlist
  
def GetUITestList(uidata ):
   data = uidata['dut_testlist']
   #print(data)
   return data
#"****************************************"
#ValidateTestList validates the UI testlist against the backend json test list
#"****************************************"
def ValidateTestList(jsontestlist, uitestlist,dut_type ):
 flag           = 0
 matchcount     = 0
 missingcount   = 0
 testlist_dut   = []
 data           = []
 moiuilist      = []
 global outputstring
 for testid in range(len(jsontestlist)):
     data =  jsontestlist[testid]
     moi = str(data['MOI_Name'])
     #print("current moi", moi)
     if moi not in jsonmoi:
       continue
     #if key == dut_type:
     keyduttype = GetDutKeyValue(dut_type)
     Issupported =  str(data[keyduttype])
     if moi == "FUNCTIONAL_TESTS" or moi == "PD_Merged":
       if keyduttype == 'IsCable':
         Issupported = str(data[keyduttype])
       else:
         Issupported = "true"
    #Check for specfic DUT
     if  Issupported == "true":
      string_unicode = str(data['Test_Name'])
      string_encode = string_unicode.encode("ascii", "ignore")
      string_decode = string_encode.decode().strip()
      testlist_dut.append(string_decode)
 failcase = 0
 for testid in range(len(testlist_dut)):
    flag = 0
    for testid2 in range(len(uitestlist)):
       string_unicode = str(uitestlist[testid2])
       string_decode = ApplyEncode(string_unicode)
       jasondata = testlist_dut[testid]
       jasondata = ApplyEncode(jasondata)
       if jasondata == string_decode:
        data2 = testlist_dut[testid]
        flag = 1
        matchcount += 1;
        break
    if flag == 0:
       failcase = 1;
       missingcount += 1
       print ("Result: FAIL,Test Case not found:"+str(testlist_dut[testid]) )
       outputstring += "Result: FAIL,Test Case not found:"+str(testlist_dut[testid])
       outputstring += '\n'
 print ("json moi", jsonmoi)  

  
def UpdateValidMOIList(certdata):
 global jsonmoi
 for k in certdata.keys():
  if( str(certdata[k]) == "True" ): 
    jsonmoi.append(str(k))
 
def ValidateMOIList(uitestlist,certtype):
 global outputstring
 moierror = "False"
 #Get UI MOI List
 uimoilist = GetUIMOINames(uitestlist)
 #Get json file MOI based on DUT type
 #jsommoilist_dutbased = GetJsonMOINames_DUTBased()
 Certi_list = GetData( "Certification.json" )
 for certid in range(len(Certi_list)):
     data =  Certi_list[certid]
     #print("Certi_list_data", data)
     tempcerttype = str(data['MOI_Name'])
     if tempcerttype == certtype:
      print("tempcerttype", tempcerttype)
      print("inputcerttype", certtype)
      print("Certi_Data", data)
      UpdateValidMOIList(data)
      print("validmoilist,jsonmoi",jsonmoi)
 print("UI MOI List", uimoilist)
 for moiid in range(len(uimoilist)):
   moiname = uimoilist[moiid]
   if ( moiname not in jsonmoi):
    moierror = "True"
    print("Error, Mismatch MOI for Certification ", moiname)
    outputstring += "Result: FAIL, Mismatch MOI for Certification "+"MOI Name: " + moiname +", Certification Type: "+certtype
    outputstring += "\n"
 if (moierror == "False"):
    outputstring += "Result: PASS, MOI's for Certification Matched "+ "MOI Name: " + moiname+ ", Certification Type: "+certtype
    outputstring += "\n"
  
def main():
 filedata = ""
 global matchtestcount
 global misstestcount
 global outputstring
 global missingtestlist
 result = 0
 index = -1
 uilist_modi = []
  #Special Case 
 deletecase = "TD.4.12.1 Hub Port 3 1 Test"
 filename = sys.argv[1]#json file from C2 Api
 uifilename = sys.argv[2]#UI list from katalon
 print(uifilename)
 dut_type = "IsDRP"
 #uitestcaselist = ["MOI_Power Delivery 3.0 Tests", "TD.PD.LL3.E1 GoodCRC Specification Revision compatibility", "TD.PD.LL3.E2 Retransmission", "TD.PD.LL3.E3 GoodCRC Compatibility with PD2", "TD.PD.SNK3.E1 Request Fields Checks", "TD.PD.SNK3.E2 Unrecognized Message Received in Ready State", "TD.PD.SNK3.E3 Get Source Cap Extended Fields Checks", "TD.PD.SNK3.E4 SenderResponseTimer Deadline Source Capabilities Extended", "TD.PD.SNK3.E5 SenderResponseTimer Timeout Source Capabilities Extended", "TD.PD.SNK3.E6 Get Status Fields Checks", "TD.PD.SNK3.E7 Get Battery Status Fields Checks", "TD.PD.SNK3.E8 Status sent timely", "TD.PD.SNK3.E9 Manufacturer Info Sent Timely", "TD.PD.SNK3.E10 Source Capabilities Extended sent timely", "TD.PD.SNK3.E11 Receiving chunked extended message", "TD.PD.SNK3.E12 Soft Reset sent regardless of Rp value", "TD.PD.SNK3.E13 SinkPPSPeriodicTimer Timeout", "TD.PD.SNK3.E14 Request Fields Checks PPS", "TD.PD.SNK3.E15 Status Fields Checks", "TD.PD.SNK3.E16 Manufacturer Info Fields Checks", "TD.PD.SNK3.E17 Manufacturer Info Fields Checks Invalid Manufacturer Info Target", "TD.PD.SNK3.E18 Manufacturer Info Fields Checks Invalid Manufacturer Info Ref", "TD.PD.SNK3.E19 ChunkSenderResponseTimer Timeout", "TD.PD.VDM3.E1 Fields Checks Discover Identity", "TD.PD.VDM3.E2 Unrecognized VID in Unstructured VDM", "TD.PD.VNDI3.E2 Request", "TD.PD.VNDI3.E3 VDM Identity", "TD.PD.VNDI3.E4 Manufacturer Info", "TD.PD.VNDI3.E5 Chunking Implemented", "TD.PD.VNDI3.E6 Unchunked Extended Messages Supported", "TD.PD.VNDI3.E7 Security Msgs Supported", "TD.PD.VNDI3.E8 Sink Capabilities", "TD.PD.VNDI3.E11 PR Swap Sink", "MOI_PD2 Communication Engine Tests", "TDA.2.1.1.1 BMC PHY TX EYE", "TDA.2.1.1.2 BMC PHY TX BIT", "TDA.2.1.2.2 BMC PHY RX INT REJ", "TDA.2.1.2.1 BMC PHY RX BUSIDL", "TDA.2.1.3.1 BMC PHY TERM", "TDA.2.1.3.2 BMC PHY MSG", "TDA.2.2.1 BMC PROT SEQ GETCAPS", "TDA.2.2.3 BMC PROT SEQ DRSWAP", "TDA.2.2.4 BMC PROT SEQ VCSWAP DFP", "TDA.2.2.5 BMC PROT DISCOV", "TDA.2.2.6 BMC PROT SEQ PRSWAP", "TDA.2.2.8 BMC PROT REV NUM", "TDA.2.2.10 BMC PROT IGN PPS", "TDA.2.3.3.1 POW SNK TRANS C CP", "MOI_PD2 Deterministic Tests", "TD.PD.LL.E1 GoodCRC Timing", "TD.PD.LL.E2 Retransmission", "TD.PD.LL.E3 Soft Reset Usage", "TD.PD.LL.E4 Hard Reset Usage", "TD.PD.LL.E5 Soft Reset", "TD.PD.LL.E6 Ping", "TD.PD.SNK.E1 SinkWaitCapTimer Deadline", "TD.PD.SNK.E2 SinkWaitCapTimer Timeout", "TD.PD.SNK.E3 Request Sent Timely", "TD.PD.SNK.E4 Request Fields Checks", "TD.PD.SNK.E5 SenderResponseTimer Deadline Accept", "TD.PD.SNK.E6 SenderResponseTimer Timeout Accept", "TD.PD.SNK.E7 PSTransitionTimer Deadline", "TD.PD.SNK.E8 PSTransitionTimer Timeout", "TD.PD.SNK.E9 GetSinkCap in Place of Accept", "TD.PD.SNK.E10 GetSinkCap in Place of PS RDY", "TD.PD.SNK.E12 Compatibility with PD3 Source", "TD.PD.VDMU.E1 Fields Checks Discover Identity", "TD.PD.VDMU.E2 Fields Checks Discover SVIDs", "TD.PD.VDMU.E3 Fields Checks Discover Modes", "TD.PD.VDMU.E4 Fields Checks Enter Mode", "TD.PD.VDMU.E5 Fields Checks Exit Mode", "TD.PD.VDMU.E6 tVDMReceiverResponse Discover Identity", "TD.PD.VDMU.E7 tVDMReceiverResponse Discover SVIDs", "TD.PD.VDMU.E8 tVDMReceiverResponse Discover Modes", "TD.PD.VDMU.E9 tVDMReceiverResponse Enter and Exit Mode", "TD.PD.VDMU.E10 Incorrect SVID_Discover Identity", "TD.PD.VDMU.E11 Incorrect SVID Discover SVIDs", "TD.PD.VDMU.E12 Incorrect SVID Discover Modes", "TD.PD.VDMU.E13 Incorrect SVID Enter Mode", "TD.PD.VDMU.E14 Incorrect SVID Exit Mode", "TD.PD.VDMU.E15 Applicability", "TD.PD.VDMU.E16 Interruption by PD Command", "TD.PD.VDMU.E17 Interruption by VDM Command", "TD.PD.VDMU.E18 tDRSwapHardReset", "TD.PD.VDMU.E19 Version", "TD.PD.VNDI.E1 VDM Identity", "TD.PD.VNDI.E2 VDM SVIDs", "TD.PD.VNDI.E3 VDM Modes", "TD.PD.VNDI.E4 SOP Handling", "TD.PD.VNDI.E6 Sink Capabilities", "TD.PD.PHY.E1 BIST Test Data", "TD.PD.PHY.E2 BIST Receiver Mode", "TD.PD.PHY.E3 BIST Transmitter Mode", "TD.PD.PHY.E4 Transmitter Bit Rate and Bit Rate Drift", "TD.PD.PHY.E5 Transmitter Collision Avoidance", "TD.PD.PHY.E6 Receiver Swing Tolerance", "TD.PD.PHY.E7 Receiver Bit Rate Tolerance", "TD.PD.PHY.E8 Receiver Bit Rate Deviation Tolerance", "TD.PD.PHY.E9 Valid SOP Framing", "TD.PD.PHY.E10 Invalid SOP Framing", "TD.PD.PHY.E11 Valid SOP P Framing", "TD.PD.PHY.E12 Invalid SOP P Framing", "TD.PD.PHY.E13 Valid SOP PP Framing", "TD.PD.PHY.E14 Invalid SOP PP Framing", "TD.PD.PHY.E15 SOP P_Debug SOP PP Debug Framing", "TD.PD.PHY.E16 Valid Hard Reset Framing", "TD.PD.PHY.E17 Invalid Hard Reset Framing", "TD.PD.PHY.E18 Valid Cable Reset Framing", "TD.PD.PHY.E19 Invalid Cable Reset Framing", "TD.PD.PHY.E20 EOP Framing", "TD.PD.PHY.E21 Preamble", "MOI_USB-C Functional Tests", "TD.4.11.2 Sink Dead Battery Test", "TD.4.1.1 Initial Voltage Test", "TD.4.2.1 Source Connect Sink Test", "TD.4.2.2 Source Connect SNKAS Test", "TD.4.2.3 Source Connect DRP", "TD.4.2.4 Source Connect Try SRC DRP", "TD.4.2.5 Source Connect Try SNK DRP", "TD.4.2.6 Source Connect Audio Accessory", "TD.4.2.7 Source Connect Debug Accessory", "TD.4.2.8 Source Connect Vconn Accessory", "TD.4.3.1 Sink Connect Source Test", "TD.4.3.2 Sink Connect DRP Test", "TD.4.3.3 Sink Connect Try SRC DRP Test", "TD.4.3.4 Sink Connect Try SNK DRP Test", "TD.4.3.5 Sink.Connect.SNKAS.Test", "TD.4.3.6 Sink.Connect.Accessories.Test", "TD.4.4.1 SNKAS Connect Source Test", "TD.4.4.2 SNKAS Connect DRP Test", "TD.4.4.3 SNKAS Connect Try SRC DRP Test", "TD.4.4.4 SNKAS Connect Try SNK DRP Test", "TD.4.4.5 SNKAS Connect SNKAS Test", "TD.4.4.6 SNKAS Connect Audio Acc", "TD.4.4.7 SNKAS Connect Debug Accessory", "TD.4.4.8 SNKAS Connect PoweredAcc", "TD.4.5.1 DRP Connect Sink Test", "TD.4.5.2 DRP Connect SNKAS Test", "TD.4.5.3 DRP Connect Source Test", "TD.4.5.4 DRP Connect DRP Test", "TD.4.5.5 DRP Connect Try SRC DRP Test", "TD.4.5.6 DRP Connect Try SNK DRP Test", "TD.4.6.1 Try SRC DRP Connect Source Test", "TD.4.6.2 Try SRC DRP Connect DRP Test", "TD.4.6.3 Try SRC DRP Connect Try SRC DRP Test", "TD.4.6.4 Try SRC DRP Connect Try SNK DRP Test", "TD.4.6.5 Try SRC DRP Connect Sink Test", "TD.4.6.6 Try SRC DRP Connect SNKAS Test", "TD.4.7.1 Try SNK DRP Connect Source Test", "TD.4.7.2 Try SNK DRP Connect DRP Test", "TD.4.7.3 Try SNK DRP Connect Try SRC DRP Test", "TD.4.7.4 Try SNK DRP Connect Try SNK DRP Test", "TD.4.7.5 Try SNK DRP Connect Sink Test", "TD.4.7.6 Try SNK DRP Connect SNKAS Test", "TD.4.8.1 DRP Connect Audio Acc Test", "TD.4.8.2 DRP Connect Debug Acc Test", "TD.4.8.3 DRP Connect Vconn Accessory Test", "TD.4.9.1 Source Suspend Test", "TD.4.9.2 USB Type C Current Advertisement Test", "TD.4.9.3 Source PR Swap Test", "TD.4.9.4 Source Vconn Swap Test", "TD.4.9.5 Source Alternate Mode Test", "TD.4.10.1 Sink Power Sub States Test", "TD.4.10.2 Sink Power Precedence Test", "TD.4.10.3 Sink Suspend Test", "TD.4.10.4 Sink PR Swap Test", "TD.4.10.5 Sink.VCONN Swap Test", "TD.4.10.6 Sink Alternate Mode Test", "TD.4.11.1 DR Swap Test", "TD.4.12.2 Hub Port Types Test", "TD.4.1.2 Unpowered CC Voltage Test", "TD.4.13.5 Cable EnterUSB and Data Reset Test", "MOI_Source Power Tests", "SPT.1 Load Test", "MOI_PD Merged Tests (Beta)", "PDMER_PHY_TEST_ALL_UUT", "TEST.PD.PHY.ALL.1 Transmit Bit Rate and the Drift", "TEST.PD.PHY.ALL.2 Transmitter Eye Diagram", "TEST.PD.PHY.ALL.3 Collision Avoidance", "TEST.PD.PHY.ALL.4 Bus Idle Detection", "TEST.PD.PHY.ALL.5 Receiver Interference Rejection", "TEST.PD.PHY.ALL.6 Invalid SOP*", "TEST.PD.PHY.ALL.7 Valid SOP*", "TEST.PD.PHY.ALL.8 Incorrect CRC", "TEST.PD.PHY.ALL.9 Receiver Input Impedance", "PDMER_PHY_TEST_SRC_SNK_CAP_UUT", "TEST.PD.PHY.PORT.1 Invalid Reset Signals", "PDMER_PROT_TEST_ALL_PD2_PD3_Mode", "TEST.PD.PROT.ALL.1 Corrupted GoodCRC", "TEST.PD.PROT.ALL.2 Soft Reset and Hard Reset", "TEST.PD.PROT.ALL.3 Soft Reset response", "TEST.PD.PROT.ALL.4 Reset Signals and MessageID", "TEST.PD.PROT.ALL.5 Unrecognized Message", "PDMER_PROT_TEST_ALL_PD3_Mode", "TEST.PD.PROT.ALL3.1 Get_Status Response", "TEST.PD.PROT.ALL3.2 Get_Manufacturer_Info Response", "TEST.PD.PROT.ALL3.3 Invalid Manufacturer Info Target", "TEST.PD.PROT.ALL3.4 Invalid Manufacturer Info Ref", "TEST.PD.PROT.ALL3.5 Chunked Extended Message Response", "TEST.PD.PROT.ALL3.6 ChunkSenderResponseTimer Timeout", "TEST.PD.PROT.ALL3.7 Security Messages Supported", "PDMER_PROT_TEST_SNK_SRC_CAP_PD3_Mode", "TEST.PD.PROT.PORT3.1 Get Battery Status Response", "TEST.PD.PROT.PORT3.2 Invalid Battery Status", "TEST.PD.PROT.PORT3.3 Get Battery Cap Response", "TEST.PD.PROT.PORT3.4 Invalid Battery Capabilities Reference", "TEST.PD.PROT.PORT3.5 Get Country Codes Response", "TEST.PD.PROT.PORT3.6 Get Country Info Response", "TEST.PD.PROT.PORT3.7 Unchunked Extended Message Supported", "PDMER_PROT_TEST_SNK_CAP_PD2_PD3_Mode", "TEST.PD.PROT.SNK.1 Get_Sink_Cap Response", "TEST.PD.PROT.SNK.2 Get_Source_Cap Response", "TEST.PD.PROT.SNK.3 SinkWaitCapTimer Deadline", "TEST.PD.PROT.SNK.4 SinkWaitCapTimer Timeout", "TEST.PD.PROT.SNK.5 SenderResponseTimer Deadline", "TEST.PD.PROT.SNK.6 SenderResponseTimer Timeout", "TEST.PD.PROT.SNK.7 PSTransitionTimer Timeout", "TEST.PD.PROT.SNK.8 Atomic Message Sequence – Accept", "TEST.PD.PROT.SNK.9 Atomic Message Sequence – PS_RDY", "TEST.PD.PROT.SNK.10 DR_Swap Request", "TEST.PD.PROT.SNK.11 VCONN_Swap Request", "TEST.PD.PROT.SNK.12 PR_Swap – PSSourceOffTimer Timeout", "TEST.PD.PROT.SNK.13 PR_Swap – Request SenderResponseTimer Timeout", "PDMER_PROT_TEST_SNK_CAP_PD3_Mode", "TEST.PD.PROT.SNK3.1 Get_Source_Cap_Extended", "TEST.PD.PROT.SNK3.2 Alert Response Source Input Change", "TEST.PD.PROT.SNK3.3 Alert Response Battery Status Change", "TEST.PD.PROT.SNK3.4 Soft_Reset Sent Regardless of Rp Value", "TEST.PD.PROT.SNK3.5 Sink PPS Normal Operation", "TEST.PD.PROT.SNK3.6 Revision Number Test", "TEST.PD.PROT.SNK.3.7 GoodCRC Specification Revision Compatibility", "PDMER_PROT_VDM_SNK_CAP_PD2_PD3", "TEST.PD.VDM.SNK.1 Discovery Process and Enter Mode", "TEST.PD.VDM.SNK.2 Exit Mode without Entering", "TEST.PD.VDM.SNK.3 Interruption by PD Message", "TEST.PD.VDM.SNK.4 Interruption by VDM Message", "TEST.PD.VDM.SNK.5 DR Swap in Modal Operation", "TEST.PD.VDM.SNK.6 Structured VDM Revision Number Test", "TEST.PD.VDM.SNK.7 Unrecognized VID in Unstructured VDM", "PDMER_PROT_PS_SNK_CAP_UUT_PD2_PD3", "TEST.PD.PS.SNK.1 PDO Transition", "TEST.PD.PS.SNK.2 Initial Sink PDO Transition", "TEST.PD.PS.SNK.3 Multiple Request Load Test Post PRSwap", "PDMER_EPR_SNK", "TEST.PD.EPR.SNK3.1 EPR Entry Process UUT as VCONN Source", "TEST.PD.EPR.SNK3.2 EPR Entry Fail tEnterEPR Timer Timeout", "TEST.PD.EPR.SNK3.3 EPR Fail by EPR Enter Failed Message", "TEST.PD.EPR.SNK3.4 EPR Entry Fail tFirstSourceCap Timer Timeout", "TEST.PD.EPR.SNK3.5 EPR Exit by Incorrect EPR Source Cap", "TEST.PD.EPR.SNK3.6 EPR Exit by EPR Exit Message", "TEST.PD.EPR.SNK.7 EPR Fail by Wait Message", "TEST.PD.EPR.SNK.8 EPR Exit by Source Cap Message", "MOI_Thunderbolt Power Tests", "TBT.2.1 Power Capacity Host", "TBT.2.2 Power Capacity Device", "TBT.2.3 Current Limit VBUS", "TBT.2.4 Short Protection VBUS", "TBT.2.5 Current Limit VCONN", "TBT.2.6 Short Protection VCONN", "MOI_DisplayPort Alternate Mode Tests", "TC.10.2.1 Enter Mode ACK Response", "TC.10.2.2 Status Update Command", "TC.10.2.4 Time from HPD Event to PD Message (Informative)", "TC.10.2.5 Proper Pin Assignment Support for Receptacle Based Video Sinks", "TC.10.2.6 Proper Pin Assignment Support for C To DP Adaptor Cables", "TC.10.2.7 Proper Pin Assignment Support for Adaptor Cables With Protocol Converters", "TC.10.2.8 Receptacle based Power Consumer (UFP_U) Vconn_Swap Response Before DP Alt Mode Entry", "TC.10.2.9 Receptacle Power Consumer (UFP_U) Vconn_Swap Response after DP Alt Mode entry", "TC.10.3.1 Discover SVIDs ACK DP SID in Arbitrary Locations", "TC.10.3.2 Cable Determination", "TC.10.3.3 Enter Mode Sequence Fails ACK Response Not Sent", "TC.10.3.4 Enter Mode Sequence Fails NACK Response", "TC.10.3.5 DisplayPort Not Connected At UFP U Then Connected", "TC.10.3.6 Status Update", "TC.10.3.7 Status Update Port Resolution", "TC.10.3.8 DP Mode Not Compatible", "TC.10.3.9 Displayport Configure", "TC.10.3.11 Proper Pin Assignment Selection for DFP_U/DFP_D Connected To Receptacle", "TC.10.3.12 Proper Pin Assignment Selection for DFP_U/DFP_D Connected To Plug", "DP.TC.10.3.12 tFirstSourceCap tTypeCSrcCapMeasurements", "TC.10.3.13 Proper Pin Assignment Selection for DFP_U/UFP_D Connected To Receptacle", "TC.10.3.14 Proper Pin Assignment Selection for DFP_U/UFP_D Connected To Plug", "TC.10.3.15 Receptacle Power Provider Vconn_Swap Response Before DP Alt Mode Entry", "TC.10.3.16 Receptacle Power Provider Vconn_Swap Response After DP Alt Mode Entry", "TC.10.4.1 DP Connector Disconnected", "TC.10.4.2 DP Connector Attached to DP Source", "TC.10.4.3 DP Connector Attached to DP Sink or Disconnected", "TC.11.2.1 VBUS VCONN Before Attach Detection", "TC.11.2.2 DFP VBUS VCONN after Attach Detection", "TC.11.3.1 Adaptor VBUS and VCONN Tests", "MOI_BC1.2 DCP Sink Tests", "QC.BC.SNK.1 Initial Power-up Test - Weak Battery", "QC.BC.SNK.2 DCP Detection Test - Weak Battery", "QC.BC.SNK.3 CDP Detection Test - Weak Battery", "QC.BC.SNK.4 SDP Detection Test - Weak Battery", "QC.BC.SNK.5 QC Negotiation Test - Weak Battery"]
 testlist = GetData( filename )
 uidata =  GetData( uifilename )
 uidatalist = []
 uidatalist = GetUITestList( uidata )
 if (len(uidatalist) >= 3):
  #Get DUT Type selected in UI
  dut_type = uidatalist[0]
  #Get Certification Type selected in UI
  certtype = uidatalist[1]
  #Get the displayed UI Test List
  uitestcaselist=uidatalist[2]
  for testid in range(len(testlist)):
   data = testlist[testid]
   string_unicode = str(data['Test_Name'])
   string_encode = string_unicode.encode("ascii", "ignore")
   string_decode = string_encode.decode().strip()
   if string_decode == deletecase:
    index = testid
  if index > -1:
   del testlist[index]
  ValidateMOIList(uitestcaselist,certtype)
  #ValidateTestList(testlist,uitestcaselist,dut_type)
  Display_MOITestList(uitestcaselist,testlist,dut_type)
 
  if misstestcount == 0:
   print("***********************************")
   print ("Result: PASS, All Test Cases Matched")
   print( "Matched TestCase Count:",matchtestcount);
   print("***********************************")
   filedata += "Result: PASS, All Test Cases Matched"
   filedata += '\n'
   filedata += "Matched TestCase Count:"+ str(matchtestcount)
   filedata += '\n'
  else:
   print ("Result: FAIL, Mismatch in Testcases Occured")
   print( "Matched TestCase Count:",matchtestcount);
   filedata += '\n'
   print( "Missing TestCase Count:",misstestcount);
   filedata += '\n'
   print( "Missing TestCases:",missingtestlist);
   filedata += "Result: FAIL, Mismatch in Testcases Occured"
   filedata += '\n'
   filedata += "Matched TestCase Count:"+ str(matchtestcount)
   filedata += '\n'
   filedata += "Missing Test Case Count:" + str(misstestcount)
   filedata += '\n'
   filedata += "Missing Test Cases:"
   filedata += '\n'
   for testid in range(len(missingtestlist)):
    missingtestname = str(missingtestlist[testid])
    filedata += missingtestname
    filedata += '\n'
   
 outputstring += filedata
 with io.open('output1.txt', 'w', encoding='utf-8') as f:
   f.write(outputstring)
 return result
if __name__ == "__main__":
    main()
