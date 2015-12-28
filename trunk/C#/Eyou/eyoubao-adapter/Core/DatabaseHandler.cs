using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace EYouBaoAdapter.Core
{
    public class DatabaseHandler
    {
        private string db;

        public DatabaseHandler(string db)
        {
            this.db = db;
        }

        public DataSet Find(string sql)
        {
            DataSet dataSet = new DataSet();
            SqlCommand command = new SqlCommand(sql,new SqlConnection(db));
            SqlDataAdapter adapter = new SqlDataAdapter(command);
            adapter.Fill(dataSet);
            DataTable table = dataSet.Tables[0];
            return dataSet;
        }

        public int Execute(string sql)
        {
            SqlConnection conn = new SqlConnection(db);

            try
            {
                SqlCommand command = new SqlCommand(sql, conn);
                conn.Open();
                return command.ExecuteNonQuery();
            }
            finally
            {
                conn.Dispose();
                conn.Close();
            }
        }
    }
}
