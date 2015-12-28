using QuickFillForm.Core.Model;

namespace QuickFillForm.Core.Provider
{
    public interface IDataProvider
    {
        Pagination Provide(SearchModel searcher);
    }
}
