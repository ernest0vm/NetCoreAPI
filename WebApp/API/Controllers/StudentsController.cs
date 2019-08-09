using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using API.Data;
using API.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class StudentsController : ControllerBase
    {
        private readonly Context _context;
        public StudentsController(Context context)
        {
            _context = context;
        }

        //api/students
        [HttpGet]
        public IEnumerable<Student> GET()
        {
            return _context.Students.ToList();
        }

        //api/students/getbyid?id=
        [HttpGet("byid")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public IEnumerable<Student> GetStudentById(int id)
        {
            var studentList = _context.Students.ToList();
            List<Student> student = new List<Student>();

            foreach (var item in studentList)
            {
                if (item.Id == id)
                {
                    student.Add(item);
                }
            }

            return student;
        }

        //api/students/getbyname?name=
        [HttpGet("byname")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public IEnumerable<Student> GetStudentByName(string name)
        {
            var studentList = _context.Students.ToList();
            List<Student> student = new List<Student>();

            foreach (var item in studentList)
            {
                if (item.Name == name)
                {
                    student.Add(item);
                }
            }

            return student;
        }

        //api/students/byage?age=
        [HttpGet("byage")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public IEnumerable<Student> GetStudentByAge(int age)
        {
            var studentList = _context.Students.ToList();
            List<Student> student = new List<Student>();

            foreach (var item in studentList)
            {
                if (item.Age == age)
                {
                    student.Add(item);
                }
            }

            return student;
        }
    }
}