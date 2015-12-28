using System;
using System.Collections.Generic;
using System.Text;

namespace EYouBaoAdapter.Core
{
    public class Pagination
    {
        public int PageSize { get; set; }

        public int PageNo { get; set; }

        public int TotalCount { get; set; }

        public List<object> Records { get; set; }

        public Pagination(int pageNo, int pageSize, int totalCount)
        {
            this.PageNo = pageNo;
            this.PageSize = pageSize;
            this.TotalCount = totalCount;
        }

        public int getTotalPage()
        {
            int pageCount = TotalCount / PageSize;

            if (pageCount == 0 || TotalCount % PageSize != 0)
            {
                pageCount++;
            }

            return pageCount;
        }
    }
}
