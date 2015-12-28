using System;
using QuickFillForm.Core.Model;
using QuickFillForm.Core.Resolver;
using System.Net;
using System.Text;
using System.Xml;
using System.IO;

namespace QuickFillForm.Core.Provider
{
    public class HttpProvider : IDataProvider
    {
        private string url;

        public HttpProvider(string url)
        { 
            this.url = url;
        }

        public Pagination Provide(SearchModel searcher)
        {
            ConfigResolver resolver = ConfigResolver.GetInstance();
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";
            request.ContentType = "application/xml;charset=UTF-8";
            Encoding encoding = Encoding.GetEncoding("utf-8");
            byte[] postdata = encoding.GetBytes(GetRequestParameters(searcher));
            request.ContentLength = postdata.Length;
            Stream requestStream = request.GetRequestStream();
            requestStream.Write(postdata, 0, postdata.Length);
            requestStream.Close();
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
            DataResolver dataResolver = new DataResolver();
            Pagination result = dataResolver.resolve(reader);
            reader.Close();
            return result;
        }

        private string GetRequestParameters(SearchModel searcher)
        {
            XmlDocument document = new XmlDocument();
            document.AppendChild(document.CreateXmlDeclaration("1.0", "UTF-8", null));
            XmlElement root = document.CreateElement("parameters");
            document.AppendChild(root);
            XmlElement element = document.CreateElement("page-no");
            element.InnerText = Convert.ToString(searcher.pageNo);
            root.AppendChild(element);
            element = document.CreateElement("page-size");
            element.InnerText = Convert.ToString(searcher.pageSize);
            root.AppendChild(element);
            XmlWriterSettings settings = new XmlWriterSettings();
            settings.Indent = true;
            settings.Encoding = Encoding.UTF8;
            StringBuilder output = new StringBuilder();
            XmlWriter writer = XmlWriter.Create(output, settings);
            document.WriteContentTo(writer);
            writer.Flush();
            writer.Close();
            return output.ToString();
        }
    }
}
