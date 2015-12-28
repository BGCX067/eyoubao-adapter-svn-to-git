using System.Collections.Generic;

namespace QuickFillForm.Core.Util
{
    public class UrlMatcher
    {
        private string url;

        public UrlMatcher(string url)
        {
            this.url = url;
        }

        public bool matches(List<string> patterns)
        {
            if (null != patterns && patterns.Count > 0)
            { 
                foreach (string pattern in patterns)
                {
                    if (url.Contains(pattern))
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
