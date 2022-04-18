package com.mohit.bootesnull.models

import java.util.*

class EventDetailModel {
    var success = false
    var code = 0
    var message: String? = null
    var data: Data? = null
    var metadata: Metadata? = null
}

class User_Id {
    var _id: String? = null
    var profile_image: String? = null
    var full_name: String? = null
    var id: String? = null
}

class Category {
    var _id: String? = null
    var title: String? = null
}

class Data {
    var _id: String? = null
    var title: String? = null
    var user_id: User_Id? = null
    var category: Category? = null
    var location: String? = null
    var start_date:Long = 0L
    var end_date:Long = 0L
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
    var is_go_sent = 0
    var is_invite_recieved = 0



    var is_interested = 0

}

