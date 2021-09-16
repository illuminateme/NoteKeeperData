package com.example.notekeeperdata

class CourseInfo (val courseId: String, var title: String) {
    override fun toString(): String {
        return title
    }
}

class NoteInfo (var courses: CourseInfo, var title: String, var text: String)

