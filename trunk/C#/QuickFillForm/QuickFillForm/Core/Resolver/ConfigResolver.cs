using System.Collections.Generic;
using System.IO;
using System.Xml.XPath;
using System.Reflection;
using QuickFillForm.Core.Provider;

namespace QuickFillForm.Core.Resolver
{
    public class ConfigResolver
    {
        private static readonly ConfigResolver instance = new ConfigResolver();

        private List<string> popup = new List<string>();

        private List<string> wrap = new List<string>();

        private List<string> rewrite = new List<string>();

        private List<string> tms = new List<string>();

        private List<string> pms = new List<string>();

        private List<string> dms = new List<string>();

        private IDataProvider dataProvider;

        private bool isDebug;

        private bool showDetail;

        private ConfigResolver()
        {
            this.initializ();
        }

        public static ConfigResolver GetInstance()
        {
            return instance;
        }

        public List<string> GetPopupPatterns()
        {
            return this.popup;
        }

        public List<string> GetWrapPatterns()
        {
            return this.wrap;
        }

        public List<string> GetRewritePatterns()
        {
            return this.rewrite;
        }

        public List<string> GetPmsPatterns()
        {
            return this.pms;
        }

        public List<string> GetTmsPatterns()
        {
            return this.tms;
        }

        public List<string> GetDmsPatterns()
        {
            return this.dms;
        }

        public IDataProvider GetDataProvider()
        {
            return this.dataProvider;
        }

        public bool IsDebug()
        {
            return this.isDebug;
        }

        public bool IsShowDetail()
        {
            return this.showDetail;
        }

        private void initializ()
        {
            FileInfo fileIofo = new FileInfo(Assembly.GetExecutingAssembly().Location);
            string path = fileIofo.Directory.FullName + "\\Config\\config.xml";
            StreamReader reader = new StreamReader(path);
            XPathDocument doc = new XPathDocument(reader);
            XPathNavigator nav = doc.CreateNavigator();
            XPathNodeIterator iterator = nav.Select("/config/popup-mapping/pattern");

            XPathNavigator node;
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.popup.Add(node.InnerXml);
            }

            iterator = nav.Select("/config/wrap-mapping/pattern");
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.wrap.Add(node.InnerXml);
            }

            iterator = nav.Select("/config/rewrite-mapping/pattern");
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.rewrite.Add(node.InnerXml);
            }

            iterator = nav.Select("/config/tms-mapping/pattern");
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.tms.Add(node.InnerXml);
            }

            iterator = nav.Select("/config/pms-mapping/pattern");
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.pms.Add(node.InnerXml);
            }

            iterator = nav.Select("/config/dms-mapping/pattern");
            while (iterator.MoveNext())
            {
                node = iterator.Current;
                this.dms.Add(node.InnerXml);
            }

            node = nav.SelectSingleNode("/config/data-provider/type");
            XPathNavigator desc = nav.SelectSingleNode("/config/data-provider/desc");

            if ("http".Equals(node.InnerXml))
            {
                this.dataProvider = new HttpProvider(desc.InnerXml);
            }
            else if ("sqlite".Equals(node.InnerXml))
            {
                this.dataProvider = new SQLiteProvider(desc.InnerXml);
            }

            node = nav.SelectSingleNode("/config/is-debug");

            if (null != node && "on".Equals(node.InnerXml))
            {
                this.isDebug = true;
            }
            else
            {
                this.isDebug = false;
            }

            node = nav.SelectSingleNode("/config/show-detail");

            if (null != node && "on".Equals(node.InnerXml))
            {
                this.showDetail = true;
            }
            else
            {
                this.showDetail = false;
            }

            reader.Close();
        }
    }
}
