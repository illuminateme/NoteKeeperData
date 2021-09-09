package com.example.notekeeperdata

class CourseInfo (val courseId: String, var title: String)

class NoteInfo (var courses: CourseInfo, var title: String, var text: String)