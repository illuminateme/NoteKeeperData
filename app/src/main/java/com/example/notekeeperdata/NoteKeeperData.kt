package com.example.notekeeperdata

data class CourseInfo (val courseId: String, var title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo (var courses: CourseInfo? = null, var title: String? = null, var text: String? = null)

