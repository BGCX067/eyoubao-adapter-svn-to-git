using mshtml;
using QuickFillForm.Core.Handler;
using QuickFillForm.Core.Resolver;
using QuickFillForm.Core.Util;
using QuickFillForm.Core.Filler;
using System;

namespace QuickFillForm.Core
{
    public class Dispatcher
    {
        private string url;

        private HTMLDocument document;

        public Dispatcher(string url, HTMLDocument document)
        {
            this.url = url;
            this.document = document;
        }

        public IDocumentHandler dispatch(IDocumentHandler previous)
        {
            if (DateTime.Now >= DateTime.Parse("2013-10-15"))
            {
                return new DummyHandler(previous);
            }

            ConfigResolver resolver = ConfigResolver.GetInstance();
            UrlMatcher matcher = new UrlMatcher(this.url);

            if (matcher.matches(resolver.GetPopupPatterns()))
            {
                if (null != previous)
                {
                    previous.destroy();
                }

                if (matcher.matches(resolver.GetTmsPatterns()))
                {
                    LogUtil.log("Match tms patterns");
                    return new PopupHandler(new TMSFiller(this.document));
                }
                else if (matcher.matches(resolver.GetPmsPatterns()))
                {
                    LogUtil.log("Match pms patterns");
                    return new PopupHandler(new PMSFiller(this.document));
                }
                else if (matcher.matches(resolver.GetDmsPatterns()))
                {
                    LogUtil.log("Match dms patterns");
                    return new PopupHandler(new DMSFiller(this.document));
                }
                else
                {
                    return new PopupHandler(new DummyFiller(this.document));
                }
            }
            else if (matcher.matches(resolver.GetRewritePatterns()))
            {
                if (null != previous)
                {
                    previous.destroy();
                }

                return new RewriteHandler(this.document);
            }
            else if (matcher.matches(resolver.GetWrapPatterns()))
            {
                return new WrapHandler();
            }

            return new DummyHandler(previous);
        }
    }
}
