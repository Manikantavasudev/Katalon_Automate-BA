using System;
using System.IO;
using System.Xml;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using static System.Net.Mime.MediaTypeNames;
using Microsoft.XmlDiffPatch;
using System.Xml.Schema;
using C2Testing.XMLHelper;
using System.Text.Json.Nodes;
using Newtonsoft.Json.Linq;

namespace C2Testing.XMLValidator
{
    /// <summary>
    /// Author - Yog 
    /// Tool to compare 2 XML files 
    /// </summary>
    public class XmlValidation
    {
        public string GoldenXMLPath;
        public string CurrentXMLPath;

        public XMLHlper objHelper = null;
        public XmlValidation(string goldenXMLPath, string currentXMLPath)
        {
            GoldenXMLPath = goldenXMLPath;
            CurrentXMLPath = currentXMLPath;
            objHelper = new XMLHlper(goldenXMLPath, currentXMLPath);
        }

        /// <summary>
        /// Yog 
        /// Method to validate the XML files 
        /// </summary>
        /// <returns></returns>
        public bool ValidateXML()
        {
            bool retVal = true;
            try
            {
                //XmlDiff xmldiff = new XmlDiff(XmlDiffOptions.IgnoreComments |
                //                  XmlDiffOptions.IgnoreNamespaces |
                //                  XmlDiffOptions.IgnorePrefixes | XmlDiffOptions.IgnoreWhitespace);
                //XmlTextWriter diffgram = new XmlTextWriter(Console.Out);
                //bool bIdentical = xmldiff.Compare(GoldenXMLPath, CurrentXMLPath, false, diffgram);

                if (GoldenXMLPath == String.Empty || CurrentXMLPath == String.Empty)
                {
                    StaticHelper.outputString($"Check the Report Paths and re-run", true);

                }
                else
                {
                    List<XElement> ListGoldenTestNodes = objHelper.StreamElements(GoldenXMLPath);
                    List<XElement> ListCurrentTestNodes = objHelper.StreamElements(CurrentXMLPath);
                    objHelper.DeepCompare(ListGoldenTestNodes, ListCurrentTestNodes);
                }

            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
                retVal = false;
            }
            return retVal;
        }

        public void GenerateReport(string reportPath)
        {
            try
            {
                if (Directory.Exists(reportPath))
                {
                    //continue 
                }
                else
                {
                    Directory.CreateDirectory(reportPath);
                }

                string outPufilePath = Path.Combine(reportPath, "XMLReport.CSV");
                using (StreamWriter sw = new StreamWriter(outPufilePath, false))
                {
                    //Actual Test Result Compared data 
                    foreach (var data in StaticHelper.TestResult)
                    {
                        sw.WriteLine(data);
                    }

                    for (int i = 0; i < 10; i++)
                        sw.WriteLine("");


                    //Error case - Exceptions 
                    sw.WriteLine("Error Case ");

                    foreach (var data in StaticHelper.ErrorResults)
                    {
                        sw.WriteLine(data);
                    }
                    sw.Close();
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }


        private string FindXMLFile(string actualReportPath)
        {
            string xmlFile = "";
            string reportName = "GRLReport.XML";
            try
            {
                string[] files = Directory.GetFiles(actualReportPath, reportName, SearchOption.AllDirectories);
                xmlFile = files[0];
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
            return xmlFile;
        }

        public void GetInputPaths(out string PathGoldenReportXML, out string PathCurrentReportXML, out string PathReport)
        {
            PathGoldenReportXML = "";
            PathCurrentReportXML = "";
            PathReport = "";
            try
            {
                //Get the JSON file
                //TODO :: automate it ::
                string text = File.ReadAllText(@"C:\Users\GRL\C2AutomationBuild_0.9.2Version\C2AutomationTest_Kat\C2_TestResult_AggregationTool\Config.json");
                JObject values = JObject.Parse(text);
                foreach (JProperty property in values.Properties())
                {
                    string data = property.Name;
                    JToken Value = property.Value;
                    if (data == "FileLocation")
                    {
                        PathCurrentReportXML = Value.ToString();
                        PathCurrentReportXML = FindXMLFile(PathCurrentReportXML);
                    }
                    else if (data == "GoldenCSVLocation")
                    {
                        PathGoldenReportXML = Value.ToString();
                        PathGoldenReportXML = FindXMLFile(PathGoldenReportXML);
                    }
                    else if (data == "ReportFilePath")
                    {
                        PathReport = Value.ToString();
                    }
                    else
                    {

                    }
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }
    }
}
