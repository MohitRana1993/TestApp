package com.mohit.bootesnull.models

import java.util.*

class GetAllEventsModel {
    var success = false
    var code = 0
    var message: String? = null
    var data: ArrayList<AllEventsDatum>? = null
    var metadata: Metadata? = null}

class AllEventsDatum {
    var _id: String? = null
    var title: String? = null
    var user_id: String? = null
    var category: String? = null
    var location: String? = null
    var start_date = 0
    var end_date = 0
    var privacy = 0
    var about: String? = null
    var profile_image: String? = null
    var cover_image: String? = null
    var status: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var __v = 0
    var id: String? = null



    var total_going = 0
    var total_interested = 0
    var is_going = 0
    var is_interested = 0
    var is_go_sent = 0
    var is_invite_recieved = 0
}

class Metadata {
    var limit = 0
    var currentPage = 0
    var totalDocs = 0
    var totalPages = 0.0
}