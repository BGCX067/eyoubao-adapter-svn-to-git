using System;
using System.Collections.Generic;
using System.Text;
using EYouBaoAdapter.Model;
using System.ComponentModel;

namespace EYouBaoAdapter.Core
{
    public interface CoreService
    {
        Pagination FindSystemOrderList(int pageNo, int pageSize);

        List<PlatformOrder> FindPlatformOrderList(string systemOrderType, string systemOrderNo);

        void ShipSystemOrder(SystemOrder systemOrder, BackgroundWorker worker, DoWorkEventArgs e);
    }
}
