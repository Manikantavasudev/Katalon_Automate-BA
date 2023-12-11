using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;
using System.Security;

namespace C2Testing.XMLHelper
{
    public static class StaticHelper
    {
        //TODO :: Move this as Enum 
        public static string TestNode = "test";
        public static string ScoreNode = "score";
        public static string ConditionNode = "condition";
        public static string ConditionsNode = "conditions";
        public static string checkNode = "check";
        public static string checksNode = "checks";
        public static string commentNode = "comment";

        //TODO :: Move this as Enum 
        public static string GoldenReportValue = "Golden Report";
        public static string CurrentReportValue = "Current Report";

        public static List<string> ErrorResults = new List<string>();
        public static List<string> TestResult = new List<string>();

        public static void outputString(string data, bool isErrorCase = false)
        {
            Console.WriteLine(data);
            if (isErrorCase)
            {
                ErrorResults.Add(data);
            }
            else
            {
                TestResult.Add(data);
            }
        }
    }
}
