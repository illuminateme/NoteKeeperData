package com.example.notekeeperdata

data class CourseInfo (val courseId: String, var title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo (var courses: CourseInfo, var title: String, var text: String)

