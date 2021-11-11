package com.danjinae.vo

class NoticeModel {
    var catId: Int = 0
    var content: String? = null
    var id: Int = 0
    var startData: List<Date>? = null
    var endData: List<Date>? = null
}

class Date {
    var date: Int = 0
    var day: Int = 0
    var hours: Int = 0
    var minutes: Int = 0
    var month: Int = 0
    var nanos: Int = 0
    var seconds: Int = 0
    var time: Int = 0
    var timezoneOffset: Int = 0
    var year: Int = 0
}
