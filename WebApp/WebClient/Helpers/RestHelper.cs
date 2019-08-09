using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace WebClient.Helpers
{
    public class RestClient
    {
        public HttpClient Client()
        {
            var Client = new HttpClient();
            Client.BaseAddress = new Uri("https://localhost:44380/");
            return Client;
        }
        
    }
}
