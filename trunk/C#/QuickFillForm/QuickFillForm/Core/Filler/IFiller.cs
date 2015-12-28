using mshtml;

namespace QuickFillForm.Core.Filler
{
    public interface IFiller
    {
        void Fill(object data);

        HTMLDocument GetDocument();
    }
}
