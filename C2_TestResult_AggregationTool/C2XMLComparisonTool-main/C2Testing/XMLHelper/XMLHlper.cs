using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;

namespace C2Testing.XMLHelper
{
    public class XMLHlper
    {
        public string GoldenXMLPath;
        public string CurrentXMLPath;
        public XMLHlper(string goldenXMLPath, string currentXMLPath)
        {
            GoldenXMLPath = goldenXMLPath;
            CurrentXMLPath = currentXMLPath;
        }


        #region Element Verification 

        /// <summary>
        /// Author - Yog 
        /// Method to veiry the <Conditions></Conditions>
        /// </summary>
        /// <param name="currentEle"></param>
        /// <param name="goldenEle"></param>
        public void VerifyConditionsElement(XElement currentEle, XElement goldenEle)
        {
            try
            {
                List<XElement> elConditionCurrentReport, elConditionGoldenReport;
                GetXMLElements(currentEle, goldenEle, "conditions", out elConditionCurrentReport, out elConditionGoldenReport);
                if (elConditionGoldenReport != null)
                {
                    if (elConditionCurrentReport != null)
                    {
                        if (elConditionCurrentReport.Count == elConditionGoldenReport.Count)
                        {
                            //Condition
                            List<XElement> elActualConditionCurrentReport = null;
                            List<XElement> elActualConditionGoldenReport = null;
                            GetXMLElementsFromElementList(elConditionCurrentReport, elConditionGoldenReport, "condition", out elActualConditionCurrentReport, out elActualConditionGoldenReport);
                            VerifyAllContionElements(elActualConditionCurrentReport, elActualConditionGoldenReport);
                        }
                        else
                        {
                            StaticHelper.outputString($"Mimatch conditions count :" +
                                $" {StaticHelper.GoldenReportValue} {elConditionGoldenReport.Count} - {StaticHelper.CurrentReportValue} {elConditionCurrentReport.Count}");
                        }
                    }
                    else
                    {
                        StaticHelper.outputString($"conditions missing in the {StaticHelper.CurrentReportValue}");
                    }
                }
                else
                {
                    StaticHelper.outputString($"conditions missing in the {StaticHelper.GoldenReportValue}");
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        /// <summary>
        /// Author - Yog 
        /// Method to veiry the <Condition></Condition>
        /// </summary>
        /// <param name="elActualConditionCurrentReport"></param>
        /// <param name="elActualConditionGoldenReport"></param>
        public void VerifyAllContionElements(List<XElement> elActualConditionCurrentReport, List<XElement> elActualConditionGoldenReport)
        {
            try
            {
                if (elActualConditionGoldenReport != null)
                {
                    if (elActualConditionCurrentReport != null)
                    {
                        if (elActualConditionCurrentReport.Count == elActualConditionGoldenReport.Count)
                        {
                            //TODO :: Check the DESC check using the Deep check 
                            for (int x = 0; x < elActualConditionCurrentReport.Count; x++)
                            {
                                if (XNode.DeepEquals(elActualConditionCurrentReport[x], elActualConditionGoldenReport[x]) == false)
                                {
                                    //Check the Condition -> Score 
                                    CompareScoreElement(elActualConditionCurrentReport[x], elActualConditionGoldenReport[x], StaticHelper.ScoreNode);
                                    VerifyOverallChecks(elActualConditionCurrentReport, elActualConditionGoldenReport);
                                }
                                else
                                {
                                    // All the test conditions are proper 
                                }
                            }
                        }
                    }
                    else
                    {
                        StaticHelper.outputString($"condition missing in the {StaticHelper.CurrentReportValue}");
                    }
                }
                else
                {
                    StaticHelper.outputString($"condition missing in the {StaticHelper.GoldenReportValue}");
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        /// <summary>
        /// Author - Yog 
        /// Method to veiry the <Checks></Checks>
        /// </summary>
        /// <param name="elActualConditionCurrentReport"></param>
        /// <param name="elActualConditionGoldenReport"></param>
        public void VerifyOverallChecks(List<XElement> elActualConditionCurrentReport, List<XElement> elActualConditionGoldenReport)
        {
            try
            {
                List<XElement> elActualOverallCheckCurrentReport = null; List<XElement> elActualOverallChecksGoldenReport = null;
                GetXMLElementsFromElementList(elActualConditionCurrentReport, elActualConditionGoldenReport, "checks", out elActualOverallCheckCurrentReport, out elActualOverallChecksGoldenReport);
                if (elActualOverallChecksGoldenReport != null)
                {
                    if (elActualOverallCheckCurrentReport != null)
                    {
                        if (elActualOverallChecksGoldenReport.Count != elActualOverallCheckCurrentReport.Count)
                        {
                            StaticHelper.outputString($"Mimatch checks count :" + $" {StaticHelper.GoldenReportValue} {elActualOverallChecksGoldenReport.Count} - {StaticHelper.CurrentReportValue} {elActualOverallCheckCurrentReport.Count}");
                        }
                        else
                        {
                            // proper // check in Deep 
                            VerifyChecks(elActualOverallCheckCurrentReport, elActualOverallChecksGoldenReport);

                        }
                    }
                    else
                    {
                        StaticHelper.outputString($"checks missing in the {StaticHelper.CurrentReportValue}");

                    }
                }
                else
                {
                    StaticHelper.outputString($"checks missing in the {StaticHelper.GoldenReportValue}");

                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        /// <summary>
        /// Author - Yog 
        /// Method to veiry the <Check></Check>
        /// </summary>
        /// <param name="elActualOverallCheckCurrentReport"></param>
        /// <param name="elActualOverallChecksGoldenReport"></param>
        public void VerifyChecks(List<XElement> elActualOverallCheckCurrentReport, List<XElement> elActualOverallChecksGoldenReport)
        {
            try
            {
                List<XElement> elActualCheckCurrentReport = null;
                List<XElement> elActualChecksGoldenReport = null;
                GetXMLElementsFromElementList(elActualOverallCheckCurrentReport, elActualOverallChecksGoldenReport, StaticHelper.checkNode, out elActualCheckCurrentReport, out elActualChecksGoldenReport);
                if (elActualChecksGoldenReport != null)
                {
                    if (elActualCheckCurrentReport != null)
                    {
                        if (elActualChecksGoldenReport.Count == elActualCheckCurrentReport.Count)
                        {
                            for (int z = 0; z < elActualChecksGoldenReport.Count; z++)
                            {
                                if (XNode.DeepEquals(elActualChecksGoldenReport[z], elActualCheckCurrentReport[z]) == false)
                                {
                                    //iterate all the check and perform the deep check - if mismatch then take the next attrbute and verify
                                    for (int i = 0; i < elActualChecksGoldenReport.Count; i++)
                                    {
                                        Check_Score(elActualCheckCurrentReport, elActualChecksGoldenReport, z);
                                        Check_Comment(elActualCheckCurrentReport, elActualChecksGoldenReport, z);
                                    }
                                }
                                else
                                {
                                    // same 
                                }
                            }
                        }
                        else
                        {
                            StaticHelper.outputString($"Mimatch check count :" + $" {StaticHelper.GoldenReportValue} {elActualChecksGoldenReport.Count} - {StaticHelper.CurrentReportValue} {elActualOverallCheckCurrentReport.Count}");
                        }
                    }
                    else
                    {
                        StaticHelper.outputString($"check missing in the {StaticHelper.GoldenReportValue}");
                    }
                }
                else
                {
                    StaticHelper.outputString($"check missing in the {StaticHelper.GoldenReportValue}");
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        private void Check_Score(List<XElement> elActualCheckCurrentReport, List<XElement> elActualChecksGoldenReport, int z)
        {
            try
            {
                List<XElement> elScoresCurrentCheck = null;
                List<XElement> elScoresGoldenCheck = null;
                GetXMLElements(elActualChecksGoldenReport[z], elActualCheckCurrentReport[z], StaticHelper.ScoreNode, out elScoresCurrentCheck, out elScoresGoldenCheck);
                if (elScoresCurrentCheck.Count == elScoresGoldenCheck.Count)
                {
                    ElementCompare(elScoresGoldenCheck, elScoresCurrentCheck);
                }
                else
                {
                    StaticHelper.outputString($"Score count mismatch {StaticHelper.GoldenReportValue} : {elScoresGoldenCheck.Count} , " +
                        $"{StaticHelper.CurrentReportValue} : {elScoresCurrentCheck.Count}");
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        private void Check_Comment(List<XElement> elActualCheckCurrentReport, List<XElement> elActualChecksGoldenReport, int z)
        {
            try
            {
                List<XElement> elScoresCurrentCheck = null;
                List<XElement> elScoresGoldenCheck = null;
                GetXMLElements(elActualChecksGoldenReport[z], elActualCheckCurrentReport[z], StaticHelper.commentNode, out elScoresCurrentCheck, out elScoresGoldenCheck);
                if (elScoresCurrentCheck.Count == elScoresGoldenCheck.Count)
                {
                    ElementCompare(elScoresGoldenCheck, elScoresCurrentCheck);
                }
                else
                {
                    StaticHelper.outputString($"Score count mismatch {StaticHelper.GoldenReportValue} : {elScoresGoldenCheck.Count} , " +
                        $"{StaticHelper.CurrentReportValue} : {elScoresCurrentCheck.Count}");
                }
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        #endregion

        #region Helpers 

        /// <summary>
        /// Yog 
        /// Method to Itera all the Elements in the XML and get the list of XElements - Case to get all the Test Cases  
        /// </summary>
        /// <param name="inputUrl"></param>
        /// <returns></returns>
        public List<XElement> StreamElements(string inputUrl)
        {
            List<XElement> lsElements = new List<XElement>();
            using (XmlReader reader = XmlReader.Create(inputUrl))
            {
                reader.MoveToContent();
                while (reader.Read())
                {
                    if (reader.NodeType == XmlNodeType.Element)
                    {
                        if (reader.Name == StaticHelper.TestNode)
                        {
                            XElement el = XNode.ReadFrom(reader) as XElement;
                            if (el != null)
                            {
                                lsElements.Add(el);
                            }
                        }
                    }
                }
            }
            return lsElements;
        }

        /// <summary>
        /// Author - Yog 
        /// Method to compare the Golden report elements and Current run report elements
        /// </summary>
        /// <param name="ListGoldenTestNodes"></param>
        /// <param name="ListCurrentTestNodes"></param>
        public void DeepCompare(List<XElement> ListGoldenTestNodes, List<XElement> ListCurrentTestNodes)
        {
            try
            {
                //TODO :: check all the test cases in the golden report matches with the current run report 
                for (int i = 0; i < ListCurrentTestNodes.Count; i++)
                {
                    XElement elCurrent = ListCurrentTestNodes[i];
                    //check the current node (current/each  test case detail) in the {GoldenReportValue}
                    // if mean proceed else add to the error case 
                    XElement goldenEle = ListGoldenTestNodes.Find(x => x.FirstAttribute.Value == elCurrent.FirstAttribute.Value);
                    if (goldenEle != null)
                    {
                        bool deepCompare = XNode.DeepEquals(goldenEle, elCurrent);
                        if (deepCompare == false)
                        {
                            StaticHelper.outputString($"{elCurrent.FirstAttribute.Value} - MisMatch Found");
                            //If mismatch found - check in detail all nodes 

                            //get the current test case equivalent from the {GoldenReportValue}                            

                            //Overall Test Score - Compare
                            CompareScoreElement(elCurrent, goldenEle, StaticHelper.ScoreNode);
                            //Conditions 
                            VerifyConditionsElement(elCurrent, goldenEle);
                        }
                        else
                        {
                            StaticHelper.outputString($"{elCurrent.FirstAttribute.Value} - Expected");
                        }
                    }
                    else
                    {
                        StaticHelper.outputString($"Missing {elCurrent.FirstAttribute.Value} in the {StaticHelper.GoldenReportValue}");
                    }
                }

                //foreach (XElement currentEle in ListCurrentTestNodes)
                //{
                //    //check the current node (current/each  test case detail) in the {GoldenReportValue}
                //    // if mean proceed else add to the error case 
                //    XElement goldenEle = ListGoldenTestNodes.Find(x => x.FirstAttribute.Value == currentEle.FirstAttribute.Value);
                //    if (goldenEle != null)
                //    {
                //        bool deepCompare = XNode.DeepEquals(goldenEle, currentEle);
                //        if (deepCompare == false)
                //        {
                //            StaticHelper.outputString($"{currentEle.FirstAttribute.Value} - MisMatch Found");
                //            //If mismatch found - check in detail all nodes 

                //            //get the current test case equivalent from the {GoldenReportValue}
                //            foreach (var GoldenEle in ListGoldenTestNodes)
                //            {
                //                //Overall Test Score - Compare
                //                CompareScoreElement(currentEle, GoldenEle, StaticHelper.ScoreNode);

                //                //Conditions 
                //                VerifyConditionsElement(currentEle, goldenEle);
                //            }
                //        }
                //        else
                //        {
                //            StaticHelper.outputString($"{currentEle.FirstAttribute.Value} - Expected");
                //        }
                //    }
                //    else
                //    {
                //        StaticHelper.outputString($"Missing {currentEle.FirstAttribute.Value} in the {StaticHelper.GoldenReportValue}");
                //    }
                //}
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        /// <summary>
        /// Author - Yog 
        /// Method to get the list of Elements based on the Element name -example "score" | "checks"
        /// <param name="currentEle"></param>
        /// <param name="goldenEle"></param>
        /// <param name="ElementName"></param>
        /// <param name="elCurrentReport"></param>
        /// <param name="elGoldenReport"></param>
        public void GetXMLElements(XElement currentEle, XElement goldenEle, string ElementName, out List<XElement> elCurrentReport, out List<XElement> elGoldenReport)
        {
            elCurrentReport = null;
            elGoldenReport = null;
            try
            {
                elCurrentReport = currentEle.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
                elGoldenReport = goldenEle.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex.ToString()}", true);
            }
        }

        /// <summary>
        /// Method to get the list of Elements based on the Element name -example "score" | "checks" 
        /// </summary>
        /// <param name="currentEle"></param>
        /// <param name="goldenEle"></param>
        /// <param name="ElementName"></param>
        /// <param name="elCurrentReport"></param>
        /// <param name="elGoldenReport"></param>
        public void GetXMLElementsFromElementList(List<XElement> currentEle, List<XElement> goldenEle, string ElementName, out List<XElement> elCurrentReport, out List<XElement> elGoldenReport)
        {
            elCurrentReport = null;
            elGoldenReport = null;
            try
            {
                elCurrentReport = currentEle.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
                elGoldenReport = goldenEle.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
            }
            catch (Exception ex)
            {
                StaticHelper.outputString($"{System.Reflection.MethodInfo.GetCurrentMethod().Name} - {ex}");
            }
        }

        /// <summary>
        /// Method to compare the score Elements 
        /// </summary>
        /// <param name="currentReportElement"></param>
        /// <param name="GoldenReportElement"></param>
        /// <param name="ElementName"></param>
        /// <returns></returns>
        public bool CompareScoreElement(XElement currentReportElement, XElement GoldenReportElement, string ElementName = "")
        {
            bool retVal = true;
            List<XElement> elScoreGoldenReport = GoldenReportElement.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
            if (elScoreGoldenReport != null)
            {
                List<XElement> elScoreCurrentReport = currentReportElement.Elements().Where(x => x.NodeType == XmlNodeType.Element && x.Name == ElementName).ToList();
                if (elScoreCurrentReport != null)
                {
                    if (elScoreGoldenReport.Count != elScoreCurrentReport.Count)
                    {
                        StaticHelper.outputString($"Mismatch in the TestResult");
                        retVal = false;
                    }
                    else
                    {
                        retVal = ElementCompare(elScoreGoldenReport, elScoreCurrentReport);
                    }
                }
            }
            return retVal;
        }

        string GetParent_FirstAttribute(XElement ElementNode)
        {
            string retData = "";

            if (ElementNode.FirstAttribute != null)
            {
                retData = ElementNode.FirstAttribute.Value;
            }
            else
            {
                if (ElementNode.Parent != null)
                {
                    retData = ElementNode.Parent.Name.ToString();
                }
                else
                {
                    retData = ""; // Element not found need to check manually
                }
            }

            return retData;
        }

        /// <summary>
        /// Method to perform the DeepCheck of 2 XML element object
        /// </summary>
        /// <param name="GoldenReportElement"></param>
        /// <param name="CurrentReportElement"></param>
        /// <returns></returns>
        public bool ElementCompare(List<XElement> GoldenReportElement, List<XElement> CurrentReportElement)
        {
            bool retVal = true;
            string goldenReportFirstAtt = "";
            string currentReportFirstAtt = "";
            for (int x = 0, y = 0; x < CurrentReportElement.Count && y < CurrentReportElement.Count; x++, y++)
            {
                if (XNode.DeepEquals(CurrentReportElement[x], GoldenReportElement[y]) == false)
                {
                    goldenReportFirstAtt = GetParent_FirstAttribute(GoldenReportElement[x]);
                    currentReportFirstAtt = GetParent_FirstAttribute(CurrentReportElement[x]);

                    string ParentAttrValue = currentReportFirstAtt;
                    if (CurrentReportElement[x].Parent != null)
                    {
                        ParentAttrValue = CurrentReportElement[x].Parent.FirstAttribute.Value;
                    }

                    StaticHelper.outputString($"{ParentAttrValue} {StaticHelper.GoldenReportValue} {GoldenReportElement[x].Name} : {goldenReportFirstAtt} " +
                      $"{StaticHelper.CurrentReportValue} {CurrentReportElement[x].Name} : {currentReportFirstAtt} ");
                    retVal = false;                                        
                }
                else
                {
                    //same 
                }
            }
            return retVal;
        }

        #endregion
    }
}
