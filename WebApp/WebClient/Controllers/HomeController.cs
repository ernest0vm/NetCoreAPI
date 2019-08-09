using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using WebClient.Helpers;
using WebClient.Models;

namespace WebClient.Controllers
{
    public class HomeController : Controller
    {
        RestClient _api = new RestClient();
        List<Student> list = new List<Student>();
        //public async Task<IActionResult> Index()
        //{
            
        //    HttpClient client = _api.Client();
        //    HttpResponseMessage res = await client.GetAsync("api/students");

        //    if (res.IsSuccessStatusCode)
        //    {
        //        var result = res.Content.ReadAsStringAsync().Result;
        //        list = JsonConvert.DeserializeObject<List<Student>>(result);
        //    }

        //    return View(list);
        //}

        public async Task<IActionResult> Index(string searchName)
        {
            HttpClient client = _api.Client();
            HttpResponseMessage res;

            if (string.IsNullOrEmpty(searchName))
            {
                res = await client.GetAsync($"api/students");
            }
            else
            {
                res = await client.GetAsync($"api/students/byname?name={searchName}");
            }

            if (res.IsSuccessStatusCode)
            {
                var result = res.Content.ReadAsStringAsync().Result;
                list = JsonConvert.DeserializeObject<List<Student>>(result);
            }

            return View(list);
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
