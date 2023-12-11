using C2Testing.XMLValidator;
using C2Testing.XMLHelper;

string PathGoldenReportXML = "";
string PathCurrentReportXML = "";
string PathReportOutput = "";
XmlValidation TempObj = new XmlValidation(PathGoldenReportXML, PathCurrentReportXML);
TempObj.GetInputPaths(out PathGoldenReportXML, out PathCurrentReportXML, out PathReportOutput);


XmlValidation objXMLValidation = new XmlValidation(PathGoldenReportXML, PathCurrentReportXML);
objXMLValidation.ValidateXML();
objXMLValidation.GenerateReport(PathReportOutput);

